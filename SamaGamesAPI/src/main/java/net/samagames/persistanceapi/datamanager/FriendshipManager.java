package net.samagames.persistanceapi.datamanager;


import net.samagames.persistanceapi.beans.players.FriendshipBean;
import net.samagames.persistanceapi.beans.players.PlayerBean;
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
public class FriendshipManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Make a friendship demand
    public void postFriendshipDemand(FriendshipBean friendship, DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "insert into friendship (requester_uuid, recipient_uuid, demand_date, active_status) values (UNHEX(?), UNHEX(?), now(), false)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(friendship.getRequesterUUID().toString()));
            statement.setString(2, Transcoder.encode(friendship.getRecipientUUID().toString()));

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
            close();
        }
    }

    // Accept a friendship demand
    public void acceptFriendshipDemand(FriendshipBean friendship, DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "update friendship set active_status = true, acceptation_date = now() where friendship_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, friendship.getFriendshipId());

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
            close();
        }
    }

    // Refuse a firendship demand
    public void refuseFriendshipDemand(FriendshipBean friendship, DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "delete from friendship where friendship_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, friendship.getFriendshipId());

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
            close();
        }
    }

    // Get friendship demand list
    public List<FriendshipBean> getFriendshipDemandList(PlayerBean player, DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<FriendshipBean> friendshipList = new ArrayList<>();

            // Query construction
            String sql = "select friendship_id, HEX(requester_uuid) as requester, HEX(recipient_uuid) as recipient, demand_date, acceptation_date, active_status";
            sql += " from friendship where (recipient_uuid = UNHEX(?) or requester_uuid = UNHEX(?)) and active_status = false";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setString(2, Transcoder.encode(player.getUuid().toString()));

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a list of bean
            while(resultset.next())
            {
                long friendshipId = resultset.getLong("friendship_id");
                String requester = Transcoder.decode(resultset.getString("requester"));
                UUID requesterUuid = UUID.fromString(requester);
                String recipient = Transcoder.decode(resultset.getString("recipient"));
                UUID recipientUuid = UUID.fromString(recipient);
                Timestamp demandDate = resultset.getTimestamp("demand_date");
                Timestamp acceptationDate = resultset.getTimestamp("acceptation_date");
                boolean activeStatus = resultset.getBoolean("active_status");
                FriendshipBean friendshipBean = new FriendshipBean(friendshipId, requesterUuid, recipientUuid, demandDate, acceptationDate, activeStatus);
                friendshipList.add(friendshipBean);
            }
            return friendshipList;
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

    // Get friendship list for a player
    public List<FriendshipBean> getFriendshipList(PlayerBean player, DataSource dataSource) throws Exception // FIXME Make it bidirectionnal !
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<FriendshipBean> friendshipList = new ArrayList<>();

            // Query construction
            String sql = "select friendship_id, HEX(requester_uuid) as requester, HEX(recipient_uuid) as recipient, demand_date, acceptation_date, active_status";
            sql += " from friendship where (recipient_uuid = UNHEX(?) or requester_uuid = UNHEX(?)) and active_status=true";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setString(2, Transcoder.encode(player.getUuid().toString()));

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a list of bean
            while(resultset.next())
            {
                long friendshipId = resultset.getLong("friendship_id");
                String requester = Transcoder.decode(resultset.getString("requester"));
                UUID requesterUuid = UUID.fromString(requester);
                String recipient = Transcoder.decode(resultset.getString("recipient"));
                UUID recipientUuid = UUID.fromString(recipient);
                Timestamp demandDate = resultset.getTimestamp("demand_date");
                Timestamp acceptationDate = resultset.getTimestamp("acceptation_date");
                boolean activeStatus = resultset.getBoolean("active_status");
                FriendshipBean friendshipBean = new FriendshipBean(friendshipId, requesterUuid, recipientUuid, demandDate, acceptationDate, activeStatus);
                friendshipList.add(friendshipBean);
            }
            return friendshipList;
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

    // Get friendship demand list
    public FriendshipBean getFriendshipNamedList(PlayerBean requester, PlayerBean recipient, DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            FriendshipBean friendshipBean = null;

            // Query construction
            String sql = "select friendship_id, HEX(requester_uuid) as requester, HEX(recipient_uuid) as recipient, demand_date, acceptation_date, active_status";
            sql += " from friendship where recipient_uuid = UNHEX(?) and requester_uuid = UNHEX(?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(recipient.getUuid().toString()));
            statement.setString(2, Transcoder.encode(requester.getUuid().toString()));

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a list of bean
            if(resultset.next())
            {
                long friendshipId = resultset.getLong("friendship_id");
                String requesterName = Transcoder.decode(resultset.getString("requester"));
                UUID requesterUuid = UUID.fromString(requesterName);
                String recipientName = Transcoder.decode(resultset.getString("recipient"));
                UUID recipientUuid = UUID.fromString(recipientName);
                Timestamp demandDate = resultset.getTimestamp("demand_date");
                Timestamp acceptationDate = resultset.getTimestamp("acceptation_date");
                boolean activeStatus = resultset.getBoolean("active_status");
                friendshipBean = new FriendshipBean(friendshipId, requesterUuid, recipientUuid, demandDate, acceptationDate, activeStatus);
            }
            return friendshipBean;
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
