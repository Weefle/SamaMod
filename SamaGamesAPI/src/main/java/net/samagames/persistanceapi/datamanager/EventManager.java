package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.events.EventBean;
import net.samagames.persistanceapi.beans.events.EventWinnerBean;
import net.samagames.persistanceapi.utils.Transcoder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
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
public class EventManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get the event by ID
    public EventBean getEvent(long eventId, DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            EventBean eventBean = null;

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select event_organizer, event_template, reward_coins, reward_pearls, event_date from events where event_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, eventId);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                UUID eventOrganizer = UUID.fromString(Transcoder.decode(resultset.getString("event_organizer")));
                String eventTemplate = resultset.getString("event_template");
                int rewardCoins = resultset.getInt("reward_coins");
                int rewardPearls = resultset.getInt("reward_pearls");
                Timestamp eventDate = resultset.getTimestamp("event_date");

                eventBean = new EventBean(eventId, eventOrganizer, eventTemplate, rewardCoins, rewardPearls, eventDate);
            }

            return eventBean;
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

    // Get the event by date
    public EventBean getEvent(Timestamp eventDate, DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            EventBean eventBean = null;

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select event_id, event_organizer, event_template, reward_coins, reward_pearls from events where event_date = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, eventDate.toString());

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                long eventId = resultset.getLong("event_id");
                UUID eventOrganizer = UUID.fromString(Transcoder.decode(resultset.getString("event_organizer")));
                String eventTemplate = resultset.getString("event_template");
                int rewardCoins = resultset.getInt("reward_coins");
                int rewardPearls = resultset.getInt("reward_pearls");

                eventBean = new EventBean(eventId, eventOrganizer, eventTemplate, rewardCoins, rewardPearls, eventDate);
            }

            return eventBean;
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

    // Get the winners of an event
    public List<EventWinnerBean> getEventWinners(long eventId, DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            List<EventWinnerBean> eventWinners = new ArrayList<>();

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select win_id, event_winner from event_winners where event_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, eventId);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                long winId = resultset.getLong("win_id");
                UUID eventWinner = UUID.fromString(Transcoder.decode(resultset.getString("event_winner")));

                eventWinners.add(new EventWinnerBean(winId, eventId, eventWinner));
            }

            return eventWinners;
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

    // Get the events
    public List<EventBean> getEvents(DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            List<EventBean> events = new ArrayList<>();

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select event_id, event_organizer, event_template, reward_coins, reward_pearls, event_date from events";

            statement = connection.prepareStatement(sql);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                long eventId = resultset.getLong("event_id");
                UUID eventOrganizer = UUID.fromString(Transcoder.decode(resultset.getString("event_organizer")));
                String eventTemplate = resultset.getString("event_template");
                int rewardCoins = resultset.getInt("reward_coins");
                int rewardPearls = resultset.getInt("reward_pearls");
                Timestamp eventDate = resultset.getTimestamp("event_date");

                events.add(new EventBean(eventId, eventOrganizer, eventTemplate, rewardCoins, rewardPearls, eventDate));
            }

            return events;
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

    // Update the event
    public void updateEvent(EventBean eventBean, DataSource dataSource) throws Exception
    {
        // Update the players data
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            Timestamp eventDate = eventBean.getEventDate();
            String eventDateString = "0000-00-00 00:00:00";

            if(eventDate != null)
                eventDateString = eventDate.toString();

            // Query construction
            String sql = "update events set event_organizer = ?, event_template = ?, reward_coins = ?, reward_pearls = ?, event_date = ? where event_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(eventBean.getEventOrganizer().toString()));
            statement.setString(2, eventBean.getEventTemplate());
            statement.setInt(3, eventBean.getRewardCoins());
            statement.setInt(4, eventBean.getRewardPearls());
            statement.setString(5, eventDateString);
            statement.setLong(6, eventBean.getEventId());

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

    // Update the event
    public void updateEventWinner(EventWinnerBean eventWinnerBean, DataSource dataSource) throws Exception
    {
        // Update the players data
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "update event_winners set event_id = ?, event_winner = ? where win_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, eventWinnerBean.getEventId());
            statement.setString(2, Transcoder.encode(eventWinnerBean.getEventWinner().toString()));
            statement.setLong(3, eventWinnerBean.getWinId());

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

    // Create the event
    public void createEvent(EventBean eventBean, DataSource dataSource) throws Exception
    {
        // Create the player
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            Timestamp eventDate = eventBean.getEventDate();
            String eventDateString = "0000-00-00 00:00:00";

            if(eventDate != null)
                eventDateString = eventDate.toString();

            // Query construction
            String sql = "insert into events (event_organizer, event_template, reward_coins, reward_pearls, event_date) values (UNHEX(?), ?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(eventBean.getEventOrganizer().toString()));
            statement.setString(2, eventBean.getEventTemplate());
            statement.setInt(3, eventBean.getRewardCoins());
            statement.setInt(4, eventBean.getRewardPearls());
            statement.setString(5, eventDateString);

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

    // Create the event winner
    public void createWinnerEvent(EventWinnerBean eventWinnerBean, DataSource dataSource) throws Exception
    {
        // Create the player
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "insert into event_winners (event_id, event_winner) values (?, UNHEX(?))";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, eventWinnerBean.getEventId());
            statement.setString(2, Transcoder.encode(eventWinnerBean.getEventWinner().toString()));

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
