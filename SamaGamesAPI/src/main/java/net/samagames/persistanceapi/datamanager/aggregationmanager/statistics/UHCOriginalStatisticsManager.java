package net.samagames.persistanceapi.datamanager.aggregationmanager.statistics;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.statistics.LeaderboardBean;
import net.samagames.persistanceapi.beans.statistics.UHCOriginalStatisticsBean;
import net.samagames.persistanceapi.utils.Transcoder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * This file is part of PersistanceAPI.
 *
 * PersistanceAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * PersistanceAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with PersistanceAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public class UHCOriginalStatisticsManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get uhc player statistics
    public UHCOriginalStatisticsBean getUHCOriginalStatistics(PlayerBean player, DataSource dataSource) throws Exception
    {
        UHCOriginalStatisticsBean uhcStats = null;

        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select HEX(uuid) as uuid, damages, deaths, kills, max_damages, played_games, wins, creation_date, update_date, played_time from uhcoriginal_stats where uuid = UNHEX(?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if (resultset.next())
            {
                // There's a result
                String playerUuid = Transcoder.decode(resultset.getString("uuid"));
                UUID uuid = UUID.fromString(playerUuid);
                int damages = resultset.getInt("damages");
                int deaths = resultset.getInt("deaths");
                int kills = resultset.getInt("kills");
                int maxDamages = resultset.getInt("max_damages");
                int playedGames = resultset.getInt("played_games");
                int wins = resultset.getInt("wins");
                Timestamp creationDate = resultset.getTimestamp("creation_date");
                Timestamp updateDate = resultset.getTimestamp("update_date");
                long playedTime = resultset.getLong("played_time");

                uhcStats = new UHCOriginalStatisticsBean(uuid, damages, deaths, kills, maxDamages, playedGames, wins, creationDate, updateDate, playedTime);
            }
            else
            {
                // If there no uhc stats in the database create empty one
                this.close();
                this.createEmptyUHCOriginalStatistics(player, dataSource);
                this.close();

                UHCOriginalStatisticsBean newUHCStats = this.getUHCOriginalStatistics(player,dataSource);
                this.close();

                return newUHCStats;
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            this.close();
        }

        return uhcStats;
    }

    // Create an empty uhc statistics
    private void createEmptyUHCOriginalStatistics(PlayerBean player, DataSource dataSource) throws Exception
    {
        try
        {
            // Create an empty bean
            UHCOriginalStatisticsBean uhcStats = new UHCOriginalStatisticsBean(player.getUuid(), 0, 0, 0, 0, 0, 0, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 0);

            // Set connection
            connection = dataSource.getConnection();

            // Query construction for create
            String sql = "insert into uhcoriginal_stats (uuid, damages, deaths, kills, max_damages, played_games, wins, creation_date, update_date, played_time)";
            sql += " values (UNHEX(?), ?, ?, ?, ?, ?, ?, now(), now(), ?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setInt(2, uhcStats.getDamages());
            statement.setInt(3, uhcStats.getDeaths());
            statement.setInt(4, uhcStats.getKills());
            statement.setInt(5, uhcStats.getMaxDamages());
            statement.setInt(6, uhcStats.getPlayedGames());
            statement.setInt(7, uhcStats.getWins());
            statement.setLong(8, uhcStats.getPlayedTime());

            // Execute the query
            statement.executeUpdate();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            this.close();
        }
    }

    // Update uhc player statistics
    public void updateUHCOriginalStatistics(PlayerBean player, UHCOriginalStatisticsBean uhcStats, DataSource dataSource) throws Exception
    {
        try
        {
            // Check if a record exists
            if (this.getUHCOriginalStatistics(player, dataSource) == null)
            {
                // Create an empty uhc statistics
                this.createEmptyUHCOriginalStatistics(player, dataSource);
            }
            else
            {
                // Set connection
                connection = dataSource.getConnection();

                // Query construction for update
                String sql = "update uhcoriginal_stats set damages = ?, deaths = ?, kills = ?, max_damages = ?, played_games = ?, wins = ?, update_date = now(), played_time = ? where uuid = UNHEX(?)";

                statement = connection.prepareStatement(sql);
                statement.setInt(1, uhcStats.getDamages());
                statement.setInt(2, uhcStats.getDeaths());
                statement.setInt(3, uhcStats.getKills());
                statement.setInt(4, uhcStats.getMaxDamages());
                statement.setInt(5, uhcStats.getPlayedGames());
                statement.setInt(6, uhcStats.getWins());
                statement.setLong(7, uhcStats.getPlayedTime());
                statement.setString(8, Transcoder.encode(player.getUuid().toString()));

                // Execute the query
                statement.executeUpdate();
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            this.close();
        }
    }

    // Get the board for this game
    public List<LeaderboardBean> getLeaderBoard(String category, DataSource dataSource) throws Exception
    {
        List<LeaderboardBean> leaderBoard = new ArrayList<>();
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = String.format("select p.name as name, d.%1$s as score from players as p, uhcoriginal_stats as d where p.uuid = d.uuid order by d.%2$s desc limit 3", category, category);

            statement = connection.prepareStatement(sql);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while(resultset.next())
            {
                LeaderboardBean bean = new LeaderboardBean(resultset.getString("name"), resultset.getInt("score"));
                leaderBoard.add(bean);
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            this.close();
        }
        return leaderBoard;
    }

    // Close all connection
    public void close() throws Exception
    {
        // Close the query environment in order to prevent leaks
        try
        {
            if (resultset != null)
            {
                // Close the resulset
                resultset.close();
            }
            if (statement != null)
            {
                // Close the statement
                statement.close();
            }
            if (connection != null)
            {
                // Close the connection
                connection.close();
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
    }
}
