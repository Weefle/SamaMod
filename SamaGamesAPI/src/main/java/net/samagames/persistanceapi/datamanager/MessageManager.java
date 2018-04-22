package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.messages.ScheduledMessageBean;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
public class MessageManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get the scheduled message by ID
    public ScheduledMessageBean getScheduledMessage(int messageId, DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            ScheduledMessageBean scheduledMessageBean = null;

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select message_text, schedule_time from scheduled_messages where message_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, messageId);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                String messageText = resultset.getString("message_text");
                int scheduleTime = resultset.getInt("schedule_time");

                scheduledMessageBean = new ScheduledMessageBean(messageId, messageText, scheduleTime);
            }

            return scheduledMessageBean;
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

    // Get the scheduled messages
    public List<ScheduledMessageBean> getScheduledMessages(DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            List<ScheduledMessageBean> scheduledMessages = new ArrayList<>();

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select message_id, message_text, schedule_time from scheduled_messages";

            statement = connection.prepareStatement(sql);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                int messageId = resultset.getInt("message_id");
                String messageText = resultset.getString("message_text");
                int scheduleTime = resultset.getInt("schedule_time");

                scheduledMessages.add(new ScheduledMessageBean(messageId, messageText, scheduleTime));
            }

            return scheduledMessages;
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

    // Update the scheduled message
    public void updateScheduledMessage(ScheduledMessageBean scheduledMessageBean, DataSource dataSource) throws Exception
    {
        // Update the players data
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "update scheduled_messages set message_text = ?, schedule_time = ? where message_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, scheduledMessageBean.getMessageText());
            statement.setInt(2, scheduledMessageBean.getScheduleTime());
            statement.setInt(3, scheduledMessageBean.getMessageId());

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

    // Create the scheduled message
    public void createScheduledMessage(ScheduledMessageBean scheduledMessageBean, DataSource dataSource) throws Exception
    {
        // Create the player
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "insert into scheduled_messages (message_text, schedule_time) values (?, ?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, scheduledMessageBean.getMessageText());
            statement.setInt(2, scheduledMessageBean.getScheduleTime());

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
