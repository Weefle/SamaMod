package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.players.GroupsBean;
import net.samagames.persistanceapi.beans.players.PlayerBean;
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
public class GroupsManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;
    private GroupsBean groupsBean = null;

    // Get the permission group for a player
    public GroupsBean getPlayerGroup(PlayerBean player, DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select group_id, group_name, rank, tag, prefix, suffix, multiplier from groups where group_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, player.getGroupId());

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                long groupId = resultset.getLong("group_id");
                String playerName = resultset.getString("group_name");
                int rank = resultset.getInt("rank");
                String tag = resultset.getString("tag");
                String prefix = resultset.getString("prefix");
                String suffix = resultset.getString("suffix");
                int multiplier = resultset.getInt("multiplier");
                groupsBean = new GroupsBean(groupId, playerName, rank, tag, prefix, suffix, multiplier);
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
        return groupsBean;
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
