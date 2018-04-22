package net.samagames.persistanceapi.beans.statistics;

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
public class TheDropperStatisticsBean
{
    /* Database Structure

    Table: thedropper_stats
    +---------------+------------+------+-----+---------------------+-------+
    | Field         | Type       | Null | Key | Default             | Extra |
    +---------------+------------+------+-----+---------------------+-------+
    | uuid          | binary(16) | NO   | PRI | NULL                |       |
    | creation_date | timestamp  | NO   |     | 0000-00-00 00:00:00 |       |
    | update_date   | timestamp  | NO   |     | 0000-00-00 00:00:00 |       |
    | played_time   | bigint(20) | NO   |     | NULL                |       |
    +---------------+------------+------+-----+---------------------+-------+
    */

    // Defines
    private UUID uuid;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private long playedTime;

    // Constructor
    @ConstructorProperties({"uuid", "creationDate", "updateDate", "playedTime"})
    public TheDropperStatisticsBean(UUID uuid, Timestamp creationDate, Timestamp updateDate, long playedTime)
    {
        this.setUuid(uuid);
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.playedTime = playedTime;
    }

    // Getters
    public Timestamp getCreationDate()
    {
        return this.creationDate;
    }
    public Timestamp getUpdateDate()
    {
        return this.updateDate;
    }
    public long getPlayedTime() { return playedTime; }

    // Setters
    public void setCreationDate(Timestamp creationDate)
    {
        this.creationDate = creationDate;
    }
    public void setUpdateDate(Timestamp updateDate)
    {
        this.updateDate = updateDate;
    }
    public void setPlayedTime(long playedTime) { this.playedTime = playedTime; }

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
}
