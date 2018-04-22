package net.samagames.persistanceapi.beans.messages;

import java.beans.ConstructorProperties;

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
public class ScheduledMessageBean
{
    /* Database Structure

    Table : advertising_messages
    +-------------------------+--------------+------+-----+---------------------+----------------+
    | Field                   | Type         | Null | Key | Default             | Extra          |
    +-------------------------+--------------+------+-----+---------------------+----------------+
    | message_id              | int(11)      | NO   | PRI | NULL                | auto_increment |
    | message_text            | varchar(255) | NO   |     | Non définie         |                |
    | schedule_time           | int(11)      | NO   |     | Non définie         |                |
    +-------------------------+--------------+------+-----+---------------------+----------------+
    */

    // Defines
    private int messageId;
    private String messageText;
    private int scheduleTime;

    // Constructor
    @ConstructorProperties({"messageId", "messageText", "scheduleTime"})
    public ScheduledMessageBean(int messageId, String messageText, int scheduleTime)
    {
        this.messageId = messageId;
        this.messageText = messageText;
        this.scheduleTime = scheduleTime;
    }

    // Getters
    public int getMessageId() { return this.messageId; }
    public String getMessageText() { return this.messageText; }
    public int getScheduleTime() { return this.scheduleTime; }

    // Setters
    public void setMessageText(String messageText) { this.messageText = messageText; }
    public void setScheduleTime(int scheduleTime) { this.scheduleTime = scheduleTime; }
}
