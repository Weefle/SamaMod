package net.samagames.persistanceapi.beans.events;

import java.beans.ConstructorProperties;
import java.sql.Timestamp;
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
public class EventBean
{
    /* Database Structure

    Table : events
    +-------------------------+--------------+------+-----+---------------------+----------------+
    | Field                   | Type         | Null | Key | Default             | Extra          |
    +-------------------------+--------------+------+-----+---------------------+----------------+
    | event_id                | bigint(20)   | NO   | PRI | NULL                | auto_increment |
    | event_organizer         | binary(16)   | NO   |     | Non définie         |                |
    | event_template          | varchar(255) | NO   |     | Non définie         |                |
    | reward_coins            | int(11)      | NO   |     | Non définie         |                |
    | reward_pearls           | int(11)      | NO   |     | Non définie         |                |
    | event_date              | timestamp    | NO   |     | 0000-00-00 00:00:00 |                |
    +-------------------------+--------------+------+-----+---------------------+----------------+
    */

    // Defines
    private long eventId;
    private UUID eventOrganizer;
    private String eventTemplate;
    private int rewardCoins;
    private int rewardPearls;
    private Timestamp eventDate;

    // Constructor
    @ConstructorProperties({"eventId", "eventOrganizer", "eventTemplate", "rewardCoins", "rewardPearls", "eventDate"})
    public EventBean(long eventId, UUID eventOrganizer, String eventTemplate, int rewardCoins, int rewardPearls, Timestamp eventDate)
    {
        this.eventId = eventId;
        this.eventOrganizer = eventOrganizer;
        this.eventTemplate = eventTemplate;
        this.rewardCoins = rewardCoins;
        this.rewardPearls = rewardPearls;
        this.eventDate = eventDate;
    }

    // Getters
    public long getEventId() { return this.eventId; }
    public UUID getEventOrganizer() { return this.eventOrganizer; }
    public String getEventTemplate() { return this.eventTemplate; }
    public int getRewardCoins() { return this.rewardCoins; }
    public int getRewardPearls() { return this.rewardPearls; }
    public Timestamp getEventDate() { return this.eventDate; }

    // Setters
    public void setEventOrganizer(UUID eventOrganizer) { this.eventOrganizer = eventOrganizer; }
    public void setEventTemplate(String eventTemplate) { this.eventTemplate = eventTemplate; }
    public void setRewardCoins(int rewardCoins) { this.rewardCoins = rewardCoins; }
    public void setRewardPearls(int rewardPearls) { this.rewardPearls = rewardPearls; }
    public void setEventDate(Timestamp eventDate) { this.eventDate = eventDate; }
}
