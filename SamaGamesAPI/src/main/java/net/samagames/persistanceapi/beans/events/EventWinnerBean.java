package net.samagames.persistanceapi.beans.events;

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
public class EventWinnerBean
{
    /* Database Structure

    Table : event_winners
    +-------------------------+--------------+------+-----+---------------------+----------------+
    | Field                   | Type         | Null | Key | Default             | Extra          |
    +-------------------------+--------------+------+-----+---------------------+----------------+
    | win_id                  | bigint(20)   | NO   | PRI | NULL                | auto_increment |
    | event_id                | bigint(20)   | NO   |     | NULL                |                |
    | event_winner            | binary(16)   | NO   |     | Non définie         |                |
    +-------------------------+--------------+------+-----+---------------------+----------------+
    */

    // Defines
    private long winId;
    private long eventId;
    private UUID eventWinner;

    // Constructor
    @ConstructorProperties({"winId", "eventId", "eventWinner"})
    public EventWinnerBean(long winId, long eventId, UUID eventWinner)
    {
        this.winId = winId;
        this.eventId = eventId;
        this.eventWinner = eventWinner;
    }

    // Getters
    public long getWinId() { return this.winId; }
    public long getEventId() { return this.eventId; }
    public UUID getEventWinner() { return this.eventWinner; }

    // Setters
    public void setEventId(long eventId) { this.eventId = eventId; }
    public void setEventWinner(UUID eventWinner) { this.eventWinner = eventWinner; }
}
