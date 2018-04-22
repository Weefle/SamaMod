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
public class SanctionBean
{
    /* Database Structure

    Table : sanctions
    +-----------------+--------------+------+-----+---------------------+----------------+
    | Field           | Type         | Null | Key | Default             | Extra          |
    +-----------------+--------------+------+-----+---------------------+----------------+
    | sanction_id     | bigint(20)   | NO   | PRI | NULL                | auto_increment |
    | player_uuid     | binary(16)   | NO   |     | NULL                |                |
    | type_id         | tinyint(4)   | NO   |     | NULL                |                |
    | reason          | varchar(255) | NO   |     | NULL                |                |
    | punisher_uuid   | binary(16)   | NO   |     | NULL                |                |
    | expiration_date | timestamp    | NO   |     | 0000-00-00 00:00:00 |                |
    | is_deleted      | bit(1)       | NO   |     | NULL                |                |
    | creation_date   | timestamp    | NO   |     | 0000-00-00 00:00:00 |                |
    | update_date     | timestamp    | NO   |     | 0000-00-00 00:00:00 |                |
    +-----------------+--------------+------+-----+---------------------+----------------+
    */

    // Defines sanctions types // TODO make enum
    public static int AVERTISSEMENT = 1;
    public static int BAN = 2;
    public static int KICK = 3;
    public static int MUTE = 4;
    public static int TEXT = 5;

    // Defines
    private long sanctionId; // FIXME check if used in bean
    private UUID playerUuid;
    private int typeId;
    private String reason;
    private UUID punisherUuid;
    private Timestamp expirationTime;
    private boolean isDeleted;
    private Timestamp creationDate;
    private Timestamp updateDate;

    // Constructor
    public SanctionBean(long sanctionId, UUID playerUuid, int typeId, String reason, UUID punisherUuid, Timestamp expirationTime, boolean isDeleted, Timestamp creationDate, Timestamp updateDate)
    {
        this.setSanctionId(sanctionId);
        this.playerUuid = playerUuid;
        this.typeId = typeId;
        this.reason = reason;
        this.punisherUuid = punisherUuid;
        this.expirationTime = expirationTime;
        this.isDeleted = isDeleted;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    // Constructor without date
    public SanctionBean(UUID playerUuid, int typeId, String reason, UUID punisherUuid, Timestamp expirationTime, boolean isDeleted)
    {
        this.playerUuid = playerUuid;
        this.typeId = typeId;
        this.reason = reason;
        this.punisherUuid = punisherUuid;
        this.expirationTime = expirationTime;
        this.isDeleted = isDeleted;
    }

    public SanctionBean(UUID playerUuid, int typeId, String reason, UUID punisherUuid, boolean isDeleted)
    {
        this.playerUuid = playerUuid;
        this.typeId = typeId;
        this.reason = reason;
        this.punisherUuid = punisherUuid;
        this.isDeleted = isDeleted;
    }

    // Getters
    public UUID getPlayerUuid() { return this.playerUuid; }
    public int getTypeId() { return this.typeId; }
    public String getReason() { return this.reason; }
    public UUID getPunisherUuid() { return this.punisherUuid; }
    public Timestamp getExpirationTime() { return this.expirationTime; }
    public boolean isDeleted() { return this.isDeleted; }
    public Timestamp getCreationDate() { return this.creationDate; }
    public Timestamp getUpdateDate() { return this.updateDate; }

    // Setters
    public void setPlayerUuid(UUID playerUuid) { this.playerUuid = playerUuid; }
    public void setTypeId(int typeId) { this.typeId = typeId; }
    public void setReason(String reason) { this.reason = reason; }
    public void setPunisherUuid(UUID punisherUuid) { this.punisherUuid = punisherUuid; }
    public void setExpirationTime(Timestamp expirationTime) { this.expirationTime = expirationTime; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }
    public void setCreationDate(Timestamp creationDate) { this.creationDate = creationDate; }
    public void setUpdateDate(Timestamp updateDate) { this.updateDate = updateDate; }

	public long getSanctionId() {
		return sanctionId;
	}

	public void setSanctionId(long sanctionId) {
		this.sanctionId = sanctionId;
	}
}
