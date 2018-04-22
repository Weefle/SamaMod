package net.samagames.persistanceapi.beans.players;

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
public class FriendshipBean
{
    /*

    Table :friendship
    +------------------+------------+------+-----+---------------------+----------------+
    | Field            | Type       | Null | Key | Default             | Extra          |
    +------------------+------------+------+-----+---------------------+----------------+
    | friendship_id    | bigint(20) | NO   | PRI | NULL                | auto_increment |
    | requester_uuid   | binary(16) | NO   | MUL | NULL                |                |
    | recipient_uuid   | binary(16) | NO   | MUL | NULL                |                |
    | demand_date      | timestamp  | NO   |     | 0000-00-00 00:00:00 |                |
    | acceptation_date | timestamp  | YES  |     | NULL                |                |
    | active_status    | bit(1)     | NO   | MUL | NULL                |                |
    +------------------+------------+------+-----+---------------------+----------------+
    */

    // Define attributes
    private long friendshipId;
    private UUID requesterUUID;
    private UUID recipientUUID;
    private Timestamp demandDate;
    private Timestamp acceptationDate;
    private boolean activeStatus;

    // Constructor
    public FriendshipBean(long friendshipId, UUID requesterUUID, UUID recipientUUID, Timestamp demandDate, Timestamp acceptationDate, boolean activeStatus)
    {
        this.friendshipId = friendshipId;
        this.requesterUUID = requesterUUID;
        this.recipientUUID = recipientUUID;
        this.demandDate = demandDate;
        this.acceptationDate = acceptationDate;
        this.activeStatus = activeStatus;
    }

    // Constructor without id
    public FriendshipBean(UUID requesterUUID, UUID recipientUUID, Timestamp demandDate, Timestamp acceptationDate, boolean activeStatus)
    {
        this.requesterUUID = requesterUUID;
        this.recipientUUID = recipientUUID;
        this.demandDate = demandDate;
        this.acceptationDate = acceptationDate;
        this.activeStatus = activeStatus;
    }

    // Getters
    public long getFriendshipId() { return this.friendshipId; }
    public UUID getRequesterUUID() { return this.requesterUUID; }
    public UUID getRecipientUUID() { return this.recipientUUID; }
    public Timestamp getDemandDate() { return this.demandDate; }
    public Timestamp getAcceptationDate() { return this.acceptationDate; }
    public boolean isActiveStatus() { return this.activeStatus; }

    // Setters
    public void setRequesterUUID(UUID requesterUUID) { this.requesterUUID = requesterUUID; }
    public void setRecipientUUID(UUID recipientUUID) { this.recipientUUID = recipientUUID; }
    public void setDemandDate(Timestamp demandDate) { this.demandDate = demandDate; }
    public void setAcceptationDate(Timestamp acceptationDate) { this.acceptationDate = acceptationDate; }
    public void setActiveStatus(boolean activeStatus) { this.activeStatus = activeStatus; }
}
