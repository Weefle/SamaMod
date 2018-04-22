package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.players.PlayerSettingsBean;
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
public class PlayerSettingsManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get the player ingame settings
    public PlayerSettingsBean getPlayerSettings(PlayerBean player, DataSource dataSource) throws Exception
    {
        // Defines
        PlayerSettingsBean playerSettingsBean;

        // Make the research of player by UUID
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "";
            sql += "select HEX(uuid) as uuid, jukebox_listen, group_demand_receive, friendship_demand_receive, notification_receive, private_message_receive, chat_visible, player_visible";
            sql += ", waiting_line_notification, other_player_interaction, click_on_me_activation, allow_statistic_onclick, allow_coins_onclick, allow_powders_onclick, allow_click_on_other, elytra_activated";
            sql += " from player_settings where uuid = UNHEX(?)";

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
                boolean jukeboxListen = resultset.getBoolean("jukebox_listen");
                boolean groupDemandReceive = resultset.getBoolean("group_demand_receive");
                boolean friendshipDemandReceive =  resultset.getBoolean("friendship_demand_receive");
                boolean notificationReceive = resultset.getBoolean("notification_receive");
                boolean privateMessageReceive = resultset.getBoolean("private_message_receive");
                boolean chatVisible = resultset.getBoolean("chat_visible");
                boolean playerVisible = resultset.getBoolean("player_visible");
                boolean waitingLineNotification = resultset.getBoolean("waiting_line_notification");
                boolean otherPlayerInteraction = resultset.getBoolean("other_player_interaction");
                boolean clickOnMeActivation = resultset.getBoolean("click_on_me_activation");
                boolean allowStatisticOnClick = resultset.getBoolean("allow_statistic_onclick");
                boolean allowCoinsOnClick = resultset.getBoolean("allow_coins_onclick");
                boolean allowPowdersOnClick = resultset.getBoolean("allow_powders_onclick");
                boolean allowClickOnOther = resultset.getBoolean("allow_click_on_other");
                boolean elytraActivated = resultset.getBoolean("elytra_activated");
                playerSettingsBean = new PlayerSettingsBean(uuid, jukeboxListen, groupDemandReceive, friendshipDemandReceive, notificationReceive, privateMessageReceive, chatVisible,
                        playerVisible, waitingLineNotification, otherPlayerInteraction, clickOnMeActivation, allowStatisticOnClick, allowCoinsOnClick, allowPowdersOnClick, allowClickOnOther, elytraActivated);
                return playerSettingsBean;
            }
            else
            {
                // If there no player settings for the uuid in database create a new player settings
                this.close();
                this.createDefaultPlayerSettings(player, dataSource);
                PlayerSettingsBean settings = this.getPlayerSettings(player, dataSource);
                this.close();
                return settings;
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

    // Set player ingame settings
    public void setPlayerSettings(PlayerBean player, PlayerSettingsBean settingsBeans, DataSource dataSource) throws Exception
    {
        // Update the players data
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "";
            sql += "update player_settings set jukebox_listen = ?, group_demand_receive = ?, friendship_demand_receive = ?, notification_receive = ?";
            sql += ", private_message_receive = ?, chat_visible = ?, player_visible = ?, waiting_line_notification = ?, other_player_interaction = ?";
            sql += ", click_on_me_activation = ?, allow_statistic_onclick = ?, allow_coins_onclick = ?, allow_powders_onclick = ?";
            sql += ", allow_click_on_other = ?, elytra_activated = ? where uuid = UNHEX(?)";

            statement = connection.prepareStatement(sql);
            statement.setBoolean(1, settingsBeans.isJukeboxListen());
            statement.setBoolean(2, settingsBeans.isGroupDemandReceive());
            statement.setBoolean(3, settingsBeans.isFriendshipDemandReceive());
            statement.setBoolean(4, settingsBeans.isNotificationReceive());
            statement.setBoolean(5, settingsBeans.isPrivateMessageReceive());
            statement.setBoolean(6, settingsBeans.isChatVisible());
            statement.setBoolean(7, settingsBeans.isPlayerVisible());
            statement.setBoolean(8, settingsBeans.isWaitingLineNotification());
            statement.setBoolean(9, settingsBeans.isOtherPlayerInteraction());
            statement.setBoolean(10, settingsBeans.isClickOnMeActivation());
            statement.setBoolean(11, settingsBeans.isAllowStatisticOnClick());
            statement.setBoolean(12, settingsBeans.isAllowCoinsOnClick());
            statement.setBoolean(13, settingsBeans.isAllowPowdersOnClick());
            statement.setBoolean(14, settingsBeans.isAllowClickOnOther());
            statement.setBoolean(15, settingsBeans.isElytraActivated());
            statement.setString(16, Transcoder.encode(player.getUuid().toString()));

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

    // Create default player settings
    public void createDefaultPlayerSettings(PlayerBean player, DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "insert into player_settings (uuid, jukebox_listen, group_demand_receive, friendship_demand_receive, notification_receive, private_message_receive";
            sql += ", chat_visible, player_visible, waiting_line_notification, other_player_interaction, click_on_me_activation, allow_statistic_onclick, allow_coins_onclick";
            sql += ", allow_powders_onclick, allow_click_on_other, elytra_activated)";
            sql += " values (UNHEX(?), true, true, true, true, true, true, true, true, true, true, true, true, true, true, true)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));

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
