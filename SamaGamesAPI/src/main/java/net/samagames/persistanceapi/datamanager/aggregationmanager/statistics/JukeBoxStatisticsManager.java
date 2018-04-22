package net.samagames.persistanceapi.datamanager.aggregationmanager.statistics;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.statistics.JukeBoxStatisticsBean;
import net.samagames.persistanceapi.beans.statistics.LeaderboardBean;
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
public class JukeBoxStatisticsManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get jukebox player statistics
    public JukeBoxStatisticsBean getJukeBoxStatistics(PlayerBean player, DataSource dataSource) throws Exception
    {
        JukeBoxStatisticsBean jukeBoxStats = null;

        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select HEX(uuid) as uuid, mehs, woots, woots_given, creation_date, update_date, played_time from jukebox_stats where uuid = UNHEX(?)";

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
                int mehs = resultset.getInt("mehs");
                int woots = resultset.getInt("woots");
                int wootsGiven = resultset.getInt("woots_given");
                Timestamp creationDate = resultset.getTimestamp("creation_date");
                Timestamp updateDate = resultset.getTimestamp("update_date");
                long playedTime = resultset.getLong("played_time");

                jukeBoxStats = new JukeBoxStatisticsBean(uuid, mehs, woots, wootsGiven, creationDate, updateDate, playedTime);
            }
            else
            {
                // If there no jukebox stats int the database create empty one
                this.close();
                this.createEmptyJukeBoxStatistics(player, dataSource);
                this.close();

                JukeBoxStatisticsBean newJukeBoxStats = this.getJukeBoxStatistics(player,dataSource);
                this.close();

                return newJukeBoxStats;
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
        return jukeBoxStats;
    }

    // Create an empty jukebox statistics
    private void createEmptyJukeBoxStatistics(PlayerBean player, DataSource dataSource) throws Exception
    {
        try
        {
            // Create an empty bean
            JukeBoxStatisticsBean jukeBoxStats = new JukeBoxStatisticsBean(player.getUuid(), 0, 0, 0, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 0);

            // Set connection
            connection = dataSource.getConnection();

            // Query construction for create
            String sql = "insert into jukebox_stats (uuid, mehs, woots, woots_given, creation_date, update_date, played_time) values (UNHEX(?), ?, ?, ?, now(), now(), played_time = ?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setInt(2, jukeBoxStats.getMehs());
            statement.setInt(3, jukeBoxStats.getWoots());
            statement.setInt(4, jukeBoxStats.getWootsGiven());
            statement.setLong(5, jukeBoxStats.getPlayedTime());

            // Execute the query
            statement.executeUpdate();
        }
        catch (Exception exception)
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

    // Update jukebox player statistics
    public void updateJukeBoxStatistics(PlayerBean player, JukeBoxStatisticsBean jukeBoxStats, DataSource dataSource) throws Exception
    {
        try
        {
            // Check if a record exists
            if (this.getJukeBoxStatistics(player, dataSource) == null)
            {
                // Create an empty jukebox statistics
                this.createEmptyJukeBoxStatistics(player, dataSource);
            }
            else
            {
                // Set connection
                connection = dataSource.getConnection();

                // Query construction for update
                String sql = "update jukebox_stats set mehs = ?, woots = ?, woots_given = ?, update_date = now(), played_time = ? where uuid = UNHEX(?)";

                statement = connection.prepareStatement(sql);
                statement.setInt(1, jukeBoxStats.getMehs());
                statement.setInt(2, jukeBoxStats.getWoots());
                statement.setInt(3, jukeBoxStats.getWootsGiven());
                statement.setLong(4, jukeBoxStats.getPlayedTime());
                statement.setString(5, Transcoder.encode(player.getUuid().toString()));

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
            String sql = String.format("select p.name as name, d.%1$s as score from players as p, jukebox_stats as d where p.uuid = d.uuid order by d.%2$s desc limit 3", category, category);

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
