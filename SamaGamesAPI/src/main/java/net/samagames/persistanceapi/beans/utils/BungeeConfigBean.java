package net.samagames.persistanceapi.beans.utils;

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
public class BungeeConfigBean
{
    /* Database Structure

    Table : configuration
    +-----------------+--------------+------+-----+---------+-------+
    | Field           | Type         | Null | Key | Default | Extra |
    +-----------------+--------------+------+-----+---------+-------+
    | slots           | int(11)      | NO   |     | NULL    |       |
    | motd            | varchar(255) | NO   |     | NULL    |       |
    | close_type      | varchar(255) | NO   |     | NULL    |       |
    | server_line     | varchar(255) | NO   |     | NULL    |       |
    | max_players     | int(11)      | NO   |     | NULL    |       |
    | priority_title  | varchar(255) | NO   |     | NULL    |       |
    | welcome_message | varchar(255) | NO   |     | NULL    |       |
    +-----------------+--------------+------+-----+---------+-------+
    */

    // Define attributes
    private int slots;
    private String motd;
    private String closeType;
    private String serverLine;
    private int maxPlayers;
    private String priorityTitle;
    private String welcomeMessage;

    // Constructor
    public BungeeConfigBean(int slots, String motd, String closeType, String serverLine, int maxPlayers, String priorityTitle, String welcomeMessage)
    {
        this.slots = slots;
        this.motd = motd;
        this.closeType = closeType;
        this.serverLine = serverLine;
        this.maxPlayers = maxPlayers;
        this.priorityTitle = priorityTitle;
        this.welcomeMessage = welcomeMessage;
    }

    // Getters
    public int getSlots() { return slots; }
    public String getMotd() { return motd; }
    public String getCloseType() { return closeType; }
    public String getServerLine() { return serverLine; }
    public int getMaxPlayers() { return maxPlayers; }
    public String getPriorityTitle() { return priorityTitle; }
    public String getWelcomeMessage() { return welcomeMessage; }

    // Setters
    public void setSlots(int slots) { this.slots = slots; }
    public void setMotd(String motd) { this.motd = motd; }
    public void setCloseType(String closeType) { this.closeType = closeType; }
    public void setServerLine(String serverLine) { this.serverLine = serverLine; }
    public void setMaxPlayers(int maxPlayers) { this.maxPlayers = maxPlayers; }
    public void setPriorityTitle(String priorityTitle) { this.priorityTitle = priorityTitle; }
    public void setWelcomeMessage(String welcomeMessage) { this.welcomeMessage = welcomeMessage; }
}
