package net.samagames.persistanceapi.beans.players;

import java.beans.ConstructorProperties;
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
public class PlayerSettingsBean
{
    /* Database structure

    Table : player_settings
    +---------------------------+------------+------+-----+---------+-------+
    | Field                     | Type       | Null | Key | Default | Extra |
    +---------------------------+------------+------+-----+---------+-------+
    | uuid                      | binary(16) | NO   | PRI | NULL    |       |
    | jukebox_listen            | bit(1)     | NO   |     | NULL    |       |
    | group_demand_receive      | bit(1)     | NO   |     | NULL    |       |
    | friendship_demand_receive | bit(1)     | NO   |     | NULL    |       |
    | notification_receive      | bit(1)     | NO   |     | NULL    |       |
    | private_message_receive   | bit(1)     | NO   |     | NULL    |       |
    | chat_visible              | bit(1)     | NO   |     | NULL    |       |
    | player_visible            | bit(1)     | NO   |     | NULL    |       |
    | waiting_line_notification | bit(1)     | NO   |     | NULL    |       |
    | other_player_interaction  | bit(1)     | NO   |     | NULL    |       |
    | click_on_me_activation    | bit(1)     | NO   |     | NULL    |       |
    | allow_statistic_onclick   | bit(1)     | NO   |     | NULL    |       |
    | allow_coins_onclick       | bit(1)     | NO   |     | NULL    |       |
    | allow_powders_onclick     | bit(1)     | NO   |     | NULL    |       |
    | allow_click_on_other      | bit(1)     | NO   |     | NULL    |       |
    | elytra_activated          | bit(1)     | NO   |     | NULL    |       |
    +---------------------------+------------+------+-----+---------+-------+
    */

    // Defines attributes
    private UUID uuid;
    private boolean jukeboxListen;
    private boolean groupDemandReceive;
    private boolean friendshipDemandReceive;
    private boolean notificationReceive;
    private boolean privateMessageReceive;
    private boolean chatVisible;
    private boolean playerVisible;
    private boolean waitingLineNotification;
    private boolean otherPlayerInteraction;
    private boolean clickOnMeActivation;
    private boolean allowStatisticOnClick;
    private boolean allowCoinsOnClick;
    private boolean allowPowdersOnClick;
    private boolean allowClickOnOther;
    private boolean elytraActivated;

    // Constructor
    @ConstructorProperties({"uuid", "jukeboxListen", "groupDemandReceive", "friendshipDemandReceive", "notificationReceive", "privateMessageReceive",
    "chatVisible", "playerVisible", "waitingLineNotification", "otherPlayerInteraction", "clickOnMeActivation", "allowStatisticOnClick", "allowCoinsOnClick",
    "allowPowdersOnClick", "allowClickOnOther", "elytraActivated"})
    public PlayerSettingsBean(UUID uuid, boolean jukeboxListen, boolean groupDemandReceive, boolean friendshipDemandReceive, boolean notificationReceive, boolean privateMessageReceive,
                              boolean chatVisible, boolean playerVisible, boolean waitingLineNotification, boolean otherPlayerInteraction, boolean clickOnMeActivation,
                              boolean allowStatisticOnClick, boolean allowCoinsOnClick, boolean allowPowdersOnClick, boolean allowClickOnOther, boolean elytraActivated)
    {
        this.uuid = uuid;
        this.jukeboxListen = jukeboxListen;
        this.groupDemandReceive = groupDemandReceive;
        this.friendshipDemandReceive = friendshipDemandReceive;
        this.notificationReceive = notificationReceive;
        this.privateMessageReceive = privateMessageReceive;
        this.chatVisible = chatVisible;
        this.playerVisible = playerVisible;
        this.waitingLineNotification = waitingLineNotification;
        this.otherPlayerInteraction = otherPlayerInteraction;
        this.clickOnMeActivation = clickOnMeActivation;
        this.allowStatisticOnClick = allowStatisticOnClick;
        this.allowCoinsOnClick = allowCoinsOnClick;
        this.allowPowdersOnClick = allowPowdersOnClick;
        this.allowClickOnOther = allowClickOnOther;
        this.elytraActivated = elytraActivated;
    }

    // Getters
    public UUID getUuid() { return this.uuid; }
    public boolean isJukeboxListen() { return this.jukeboxListen; }
    public boolean isGroupDemandReceive() { return this.groupDemandReceive; }
    public boolean isFriendshipDemandReceive() { return this.friendshipDemandReceive; }
    public boolean isNotificationReceive() { return this.notificationReceive; }
    public boolean isPrivateMessageReceive() { return this.privateMessageReceive; }
    public boolean isChatVisible() { return this.chatVisible; }
    public boolean isPlayerVisible() { return this.playerVisible; }
    public boolean isWaitingLineNotification() { return this.waitingLineNotification; }
    public boolean isOtherPlayerInteraction() { return this.otherPlayerInteraction; }
    public boolean isClickOnMeActivation() { return this.clickOnMeActivation; }
    public boolean isAllowStatisticOnClick() { return this.allowStatisticOnClick; }
    public boolean isAllowCoinsOnClick() { return this.allowCoinsOnClick; }
    public boolean isAllowPowdersOnClick() { return this.allowPowdersOnClick; }
    public boolean isAllowClickOnOther() { return this.allowClickOnOther; }
    public boolean isElytraActivated() { return elytraActivated; }

    // Setters
    public void setUuid(UUID uuid){ this.uuid = uuid; }
    public void setJukeboxListen(boolean jukeboxListen) { this.jukeboxListen = jukeboxListen; }
    public void setGroupDemandReceive(boolean groupDemandReceive) { this.groupDemandReceive = groupDemandReceive; }
    public void setFriendshipDemandReceive(boolean friendshipDemandReceive) { this.friendshipDemandReceive = friendshipDemandReceive; }
    public void setNotificationReceive(boolean notificationReceive) { this.notificationReceive = notificationReceive; }
    public void setPrivateMessageReceive(boolean privateMessageReceive) { this.privateMessageReceive = privateMessageReceive; }
    public void setChatVisible(boolean chatVisible) { this.chatVisible = chatVisible; }
    public void setPlayerVisible(boolean playerVisible) { this.playerVisible = playerVisible; }
    public void setWaitingLineNotification(boolean waitingLineNotification) { this.waitingLineNotification = waitingLineNotification; }
    public void setOtherPlayerInteraction(boolean otherPlayerInteraction) { this.otherPlayerInteraction = otherPlayerInteraction; }
    public void setClickOnMeActivation(boolean clickOnMeActivation) { this.clickOnMeActivation = clickOnMeActivation; }
    public void setAllowStatisticOnClick(boolean allowStatisticOnClick) { this.allowStatisticOnClick = allowStatisticOnClick; }
    public void setAllowCoinsOnClick(boolean allowCoinsOnClick) { this.allowCoinsOnClick = allowCoinsOnClick; }
    public void setAllowPowdersOnClick(boolean allowPowdersOnClick) { this.allowPowdersOnClick = allowPowdersOnClick; }
    public void setAllowClickOnOther(boolean allowClickOnOther) { this.allowClickOnOther = allowClickOnOther; }
    public void setElytraActivated(boolean elytraActivated) { this.elytraActivated = elytraActivated; }
}
