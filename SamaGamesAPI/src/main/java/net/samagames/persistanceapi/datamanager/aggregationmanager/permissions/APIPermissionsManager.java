package net.samagames.persistanceapi.datamanager.aggregationmanager.permissions;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.permissions.APIPermissionsBean;
import javax.sql.DataSource;
import java.sql.*;

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
public class APIPermissionsManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get the permissions for the API
    public APIPermissionsBean getAPIPermissions(PlayerBean player, DataSource dataSource) throws Exception
    {
        APIPermissionsBean apiPermissionsBean = null;

        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select groups_id, api_servers_debug, api_permissions_refresh, api_coins_getother, api_coins_credit, api_coins_withdraw, api_inventory_show";
            sql += ", api_playerdata_show, api_playerdata_set, api_playerdata_del, api_modo_speakup, api_stars_getother, api_stars_credit, api_stars_withdraw, api_game_start, api_chat_bypass";
            sql += " from api_permissions where groups_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, player.getGroupId());

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                long groupId = resultset.getLong("groups_id");
                boolean apiServersDebug = resultset.getBoolean("api_servers_debug");
                boolean apiPermissionsRefresh = resultset.getBoolean("api_permissions_refresh");
                boolean apiCoinsGetOther = resultset.getBoolean("api_coins_getother");
                boolean apiCoinsCredit = resultset.getBoolean("api_coins_credit");
                boolean apiCoinsWithdraw = resultset.getBoolean("api_coins_withdraw");
                boolean apiInventoryShow = resultset.getBoolean("api_inventory_show");
                boolean apiPlayerDataShow = resultset.getBoolean("api_playerdata_show");
                boolean apiPlayerdataSet = resultset.getBoolean("api_playerdata_set");
                boolean apiPlayerdataDel = resultset.getBoolean("api_playerdata_del");
                boolean apiModoSpeakup = resultset.getBoolean("api_modo_speakup");
                boolean apiStarsGetother = resultset.getBoolean("api_stars_getother");
                boolean apiStarsCredit = resultset.getBoolean("api_stars_credit");
                boolean apiStarsWithdraw = resultset.getBoolean("api_stars_withdraw");
                boolean apiGameStart = resultset.getBoolean("api_game_start");
                boolean apiChatBypass = resultset.getBoolean("api_chat_bypass");

                apiPermissionsBean = new APIPermissionsBean(groupId, apiServersDebug, apiPermissionsRefresh, apiCoinsGetOther, apiCoinsCredit, apiCoinsWithdraw, apiInventoryShow,
                        apiPlayerDataShow, apiPlayerdataSet, apiPlayerdataDel, apiModoSpeakup, apiStarsGetother, apiStarsCredit, apiStarsWithdraw, apiGameStart, apiChatBypass);
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

        return apiPermissionsBean;
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
