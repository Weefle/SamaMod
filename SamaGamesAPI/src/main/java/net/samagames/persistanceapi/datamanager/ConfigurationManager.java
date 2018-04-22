package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.utils.BungeeConfigBean;
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
public class ConfigurationManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get the bungee config
    public BungeeConfigBean getConfig(DataSource dataSource) throws Exception
    {
        // Make the research of player by UUID
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select slots, motd, close_type, server_line, max_players, priority_title, welcome_message from configuration";

            statement = connection.prepareStatement(sql);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if (resultset.next())
            {
                // There's a result
                int slots = resultset.getInt("slots");
                String motd = resultset.getString("motd");
                String closeType = resultset.getString("close_type");
                String serverLine = resultset.getString("server_line");
                int maxPlayers = resultset.getInt("max_players");
                String priorityTitle = resultset.getString("priority_title");
                String welcomeMessage = resultset.getString("welcome_message");

                BungeeConfigBean config = new BungeeConfigBean(slots, motd, closeType, serverLine, maxPlayers, priorityTitle, welcomeMessage);
                return config;
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
            close();
        }
    }

    // Update the bungee config
    public void updateConfig(BungeeConfigBean config, DataSource dataSource) throws Exception
    {
        // Update the config
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "update configuration set slots = ?, motd = ?, close_type = ?, server_line = ?, max_players = ?, priority_title = ?, welcome_message = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, config.getSlots());
            statement.setString(2, config.getMotd());
            statement.setString(3, config.getCloseType());
            statement.setString(4, config.getServerLine());
            statement.setInt(5, config.getMaxPlayers());
            statement.setString(6, config.getPriorityTitle());
            statement.setString(7, config.getWelcomeMessage());

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
            close();
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
