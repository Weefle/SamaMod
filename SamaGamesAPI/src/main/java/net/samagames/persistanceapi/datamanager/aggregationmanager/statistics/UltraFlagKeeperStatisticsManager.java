package net.samagames.persistanceapi.datamanager.aggregationmanager.statistics;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.statistics.LeaderboardBean;
import net.samagames.persistanceapi.beans.statistics.UltraFlagKeeperStatisticsBean;
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
public class UltraFlagKeeperStatisticsManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get ultraflagkeeper player statistics
    public UltraFlagKeeperStatisticsBean getUltraFlagKeeperStatistics(PlayerBean player, DataSource dataSource) throws Exception
    {
        UltraFlagKeeperStatisticsBean ultraFlagKeeperStats = null;

        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select (HEX(uuid)) as uuid, damages, deaths, kills, max_damages, played_games, wins, flags_captured, flags_returned, creation_date, update_date, played_time from ultraflagkeeper_stats where uuid = UNHEX(?)";

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
                int flagsCaptured = resultset.getInt("flags_captured");
                int flagsReturned = resultset.getInt("flags_returned");
                Timestamp creationDate = resultset.getTimestamp("creation_date");
                Timestamp updateDate = resultset.getTimestamp("update_date");
                long playedTime = resultset.getLong("played_time");

                ultraFlagKeeperStats = new UltraFlagKeeperStatisticsBean(uuid, damages, deaths, kills, maxDamages, playedGames, wins, flagsCaptured, flagsReturned, creationDate, updateDate, playedTime);
            }
            else
            {
                // If there no ultraflagkeeper stats in the database create empty one
                this.close();
                this.createEmptyUltraFlagKeeperStatistics(player, dataSource);
                this.close();

                UltraFlagKeeperStatisticsBean newUltraFlagKeeperStats = this.getUltraFlagKeeperStatistics(player,dataSource);
                this.close();

                return newUltraFlagKeeperStats;
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

        return ultraFlagKeeperStats;
    }

    // Create an empty ultraflagkeeper statistics
    private void createEmptyUltraFlagKeeperStatistics(PlayerBean player, DataSource dataSource) throws Exception
    {
        try
        {
            // Create an empty bean
            UltraFlagKeeperStatisticsBean ultraFlagKeeperStats = new UltraFlagKeeperStatisticsBean(player.getUuid(), 0, 0, 0, 0, 0, 0, 0, 0, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 0);

            // Set connection
            connection = dataSource.getConnection();

            // Query construction for create
            String sql = "insert into ultraflagkeeper_stats (uuid, damages, deaths, kills, max_damages, played_games, wins, flags_captured, flags_returned, creation_date, update_date, played_time)";
            sql += " values (UNHEX(?), ?, ?, ?, ?, ?, ?, ?, ?, now(), now(), ?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setInt(2, ultraFlagKeeperStats.getDamages());
            statement.setInt(3, ultraFlagKeeperStats.getDeaths());
            statement.setInt(4, ultraFlagKeeperStats.getKills());
            statement.setInt(5, ultraFlagKeeperStats.getMaxDamages());
            statement.setInt(6, ultraFlagKeeperStats.getPlayedGames());
            statement.setInt(7, ultraFlagKeeperStats.getWins());
            statement.setInt(8, ultraFlagKeeperStats.getFlagsCaptured());
            statement.setInt(9, ultraFlagKeeperStats.getFlagsReturned());
            statement.setLong(10, ultraFlagKeeperStats.getPlayedTime());

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

    // Update ultraflagkeeper player statistics
    public void updateUltraFlagKeeperStatistics(PlayerBean player, UltraFlagKeeperStatisticsBean ultraFlagKeeperStats, DataSource dataSource) throws Exception
    {
        try
        {
            // Check if a record exists
            if (this.getUltraFlagKeeperStatistics(player, dataSource) == null)
            {
                // Create an empty ultraflagkeeper statistics
                this.createEmptyUltraFlagKeeperStatistics(player, dataSource);
            }
            else
            {
                // Set connection
                connection = dataSource.getConnection();

                // Query construction for update
                String sql = "update ultraflagkeeper_stats set damages = ?, deaths = ?, kills = ?, max_damages = ?, played_games = ?, wins = ?, flags_captured = ?, flags_returned = ?, update_date = now(), played_time = ? where uuid = UNHEX(?)";

                statement = connection.prepareStatement(sql);
                statement.setInt(1, ultraFlagKeeperStats.getDamages());
                statement.setInt(2, ultraFlagKeeperStats.getDeaths());
                statement.setInt(3, ultraFlagKeeperStats.getKills());
                statement.setInt(4, ultraFlagKeeperStats.getMaxDamages());
                statement.setInt(5, ultraFlagKeeperStats.getPlayedGames());
                statement.setInt(6, ultraFlagKeeperStats.getWins());
                statement.setInt(7, ultraFlagKeeperStats.getFlagsCaptured());
                statement.setInt(8, ultraFlagKeeperStats.getFlagsReturned());
                statement.setLong(9, ultraFlagKeeperStats.getPlayedTime());
                statement.setString(10, Transcoder.encode(player.getUuid().toString()));

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
            String sql = String.format("select p.name as name, d.%1$s as score from players as p, ultraflagkeeper_stats as d where p.uuid = d.uuid order by d.%2$s desc limit 3", category, category);

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
