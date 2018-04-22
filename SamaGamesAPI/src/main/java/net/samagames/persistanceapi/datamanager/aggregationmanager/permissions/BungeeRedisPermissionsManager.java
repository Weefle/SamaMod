package net.samagames.persistanceapi.datamanager.aggregationmanager.permissions;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.permissions.BungeeRedisPermissionsBean;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
public class BungeeRedisPermissionsManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get the permissions for Bungee & Redis
    public BungeeRedisPermissionsBean getBungeeRedisPermissions(PlayerBean player, DataSource dataSource) throws Exception
    {
        BungeeRedisPermissionsBean bungeeRedisPermissionsBean = null;

        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select groups_id, bungeecord_command_list, bungeecord_command_find, redisbungee_command_lastseen, bungeecord_command_ip, redisbungee_command_sendtoall";
            sql += ", redisbungee_command_serverid, redisbunge_command_serverids, redisbungee_command_pproxy, redisbungee_command_plist, bungeecord_command_server, bungeecord_command_send";
            sql += ", bungeecord_command_end, bungeecord_command_alert";
            sql += " from bungee_redis_permissions where groups_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, player.getGroupId());

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                long groupId = resultset.getLong("groups_id");
                boolean bungeecordCommandList = resultset.getBoolean("bungeecord_command_list");
                boolean bungeecordCommandFind = resultset.getBoolean("bungeecord_command_find");
                boolean redisbungeeCommandLastSeen = resultset.getBoolean("redisbungee_command_lastseen");
                boolean redisbungeeCommandSendtoAll = resultset.getBoolean("bungeecord_command_ip");
                boolean bungeecordCommandIp = resultset.getBoolean("redisbungee_command_sendtoall");
                boolean redisbungeeCommandServerId = resultset.getBoolean("redisbungee_command_serverid");
                boolean redisbungeCommandServerIds = resultset.getBoolean("redisbunge_command_serverids");
                boolean redisbungeeCommandPproxy = resultset.getBoolean("redisbungee_command_pproxy");
                boolean redisbungeeCommandPlist = resultset.getBoolean("redisbungee_command_plist");
                boolean bungeecordCommandServer = resultset.getBoolean("bungeecord_command_server");
                boolean bungeecordCommandSend = resultset.getBoolean("bungeecord_command_send");
                boolean bungeecordCommandEnd = resultset.getBoolean("bungeecord_command_end");
                boolean bungeecordCommandAlert = resultset.getBoolean("bungeecord_command_alert");

                bungeeRedisPermissionsBean = new BungeeRedisPermissionsBean(groupId, bungeecordCommandList, bungeecordCommandFind, redisbungeeCommandLastSeen, redisbungeeCommandSendtoAll,
                        bungeecordCommandIp, redisbungeeCommandServerId, redisbungeCommandServerIds, redisbungeeCommandPproxy, redisbungeeCommandPlist,bungeecordCommandServer,
                        bungeecordCommandSend, bungeecordCommandEnd, bungeecordCommandAlert);
            }
            else
            {
                // If there no dimension stats int the database
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
            close();
        }

        return bungeeRedisPermissionsBean;
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
