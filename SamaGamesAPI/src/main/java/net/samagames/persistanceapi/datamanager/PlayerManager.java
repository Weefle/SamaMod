package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.utils.Transcoder;
import javax.sql.DataSource;
import java.sql.*;
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
public class PlayerManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get player by UUID, create if unknown
    public PlayerBean getPlayer(UUID uuid, PlayerBean player, DataSource dataSource) throws Exception
    {
        // Make the research of player by UUID
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select HEX(uuid) as uuid, name, nickname, coins, stars, powders, last_login, first_login, last_ip, toptp_key, group_id from players where uuid = UNHEX(?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(uuid.toString()));

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if (resultset.next())
            {
                // There's a result
                String playerUuid = Transcoder.decode(resultset.getString("uuid"));
                String name = resultset.getString("name");
                String nickName = resultset.getString("nickname");
                int coins = resultset.getInt("coins");
                int stars = resultset.getInt("stars");
                int powders = resultset.getInt("powders");
                Timestamp lastLogin = resultset.getTimestamp("last_login");
                Timestamp firsLogin = resultset.getTimestamp("first_login");
                String lastIP = resultset.getString("last_ip");
                String toptpKey = resultset.getString("toptp_key");
                long groupId = resultset.getLong("group_id");
                player = new PlayerBean(UUID.fromString(playerUuid), name, nickName, coins, stars, powders, lastLogin, firsLogin, lastIP, toptpKey, groupId);
                return player;
            }
            else
            {
                // If there no player for the uuid in database create a new player
                this.close();
                this.createPlayer(player, dataSource);
                PlayerBean newPlayer = this.getPlayer(uuid, player, dataSource);
                this.close();
                return newPlayer;
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

    // Try to recover a suspect UUID by name
    public UUID recoverSuspect(String suspectName, DataSource dataSource) throws Exception
    {
        // Defines
        @SuppressWarnings("unused")
		PlayerBean player = null;
        UUID suspectUUID = null;

        // Try to find the player
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select HEX(uuid) as uuid from players where name = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, suspectName);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if (resultset.next())
            {
                // There's a result
                String playerUuid = Transcoder.decode(resultset.getString("uuid"));
                suspectUUID = UUID.fromString(playerUuid);
                return suspectUUID;
            }
            else
            {
                // If there no player for the uuid in database
                return null;
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

    // Update the player data
    public void updatePlayer(PlayerBean player, DataSource dataSource) throws Exception
    {
        // Update the players data
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "update players set coins = ?, name = ?, stars= ?, powders = ?, last_login = ?, last_ip = ?, toptp_key = ?, group_id = ?, nickname = ?";
            sql += " where uuid = UNHEX(?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, player.getCoins());
            statement.setString(2, player.getName());
            statement.setInt(3, player.getStars());
            statement.setInt(4, player.getPowders());
            statement.setString(5, player.getLastLogin().toString());
            statement.setString(6, player.getLastIP());
            statement.setString(7, player.getTopTpKey());
            statement.setLong(8, player.getGroupId());
            statement.setString(9, player.getNickName());
            statement.setString(10, Transcoder.encode(player.getUuid().toString()));

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

    // Create the player
    public void createPlayer(PlayerBean player, DataSource dataSource) throws Exception
    {
        // Create the player
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "insert into players (uuid, name, nickname, coins, stars, powders, last_login, first_login, last_ip, toptp_key, group_id)";
            sql += " values (UNHEX(?), ?, ?, ?, ?, ?, now(), now(), ?, ?, ?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setString(2, player.getName());
            statement.setString(3, player.getNickName());
            statement.setInt(4, player.getCoins());
            statement.setInt(5, player.getStars());
            statement.setInt(6, player.getPowders());
            statement.setString(7, player.getLastIP());
            statement.setString(8, player.getTopTpKey());
            statement.setLong(9, player.getGroupId());

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

    // Close the connection
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
