package net.samagames.persistanceapi.datamanager.aggregationmanager.permissions;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.permissions.StaffPermissionsBean;
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
public class StaffPermissionsManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get the permissions for staff
    public StaffPermissionsBean getStaffPermissions(PlayerBean player, DataSource dataSource) throws Exception
    {
        StaffPermissionsBean staffPermissionsBean = null;

        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select groups_id, netjoin_closed, netjoin_vip, netjoin_full, tracker_famous, network_vip, network_vip_plus, network_staff, network_admin";
            sql += " from staff_permissions where groups_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, player.getGroupId());

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                long groupsId = resultset.getLong("groups_id");
                boolean netjoinClosed = resultset.getBoolean("netjoin_closed");
                boolean netjoinVip = resultset.getBoolean("netjoin_vip");
                boolean netjoinFull = resultset.getBoolean("netjoin_full");
                boolean trackerFamous = resultset.getBoolean("tracker_famous");
                boolean networkVip = resultset.getBoolean("network_vip");
                boolean networkVipplus = resultset.getBoolean("network_vip_plus");
                boolean networkStaff = resultset.getBoolean("network_staff");
                boolean networkAdmin = resultset.getBoolean("network_admin");
                staffPermissionsBean = new StaffPermissionsBean(groupsId, netjoinClosed, netjoinVip, netjoinFull, trackerFamous, networkVip, networkVipplus, networkStaff, networkAdmin);
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

        return staffPermissionsBean;
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
