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
public class JukeBoxStatisticsBean
{
    /* Database Structure

    Table : jukebox_stats
    +---------------+------------+------+-----+---------------------+-------+
    | Field         | Type       | Null | Key | Default             | Extra |
    +---------------+------------+------+-----+---------------------+-------+
    | uuid          | binary(16) | NO   | PRI | NULL                |       |
    | mehs          | int(11)    | NO   |     | NULL                |       |
    | woots         | int(11)    | NO   |     | NULL                |       |
    | woots_given   | int(11)    | NO   |     | NULL                |       |
    | creation_date | timestamp  | NO   |     | 0000-00-00 00:00:00 |       |
    | update_date   | timestamp  | NO   |     | 0000-00-00 00:00:00 |       |
    | played_time   | bigint(20) | NO   |     | NULL                |       |
    +---------------+------------+------+-----+---------------------+-------+
    */

    // Defines
    private UUID uuid;
    private int mehs;
    private int woots;
    private int wootsGiven;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private long playedTime;

    // Constructor
    @ConstructorProperties({"uuid", "mehs", "woots", "wootsGiven", "creationDate", "updateDate", "playedTime"})
    public JukeBoxStatisticsBean(UUID uuid, int mehs, int woots, int wootsGiven, Timestamp creationDate, Timestamp updateDate, long playedTime)
    {
        this.setUuid(uuid);
        this.mehs = mehs;
        this.wootsGiven = wootsGiven;
        this.woots = woots;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.playedTime = playedTime;
    }

    // Getters
    public int getMehs()
    {
        return this.mehs;
    }
    public int getWoots()
    {
        return this.woots;
    }
    public int getWootsGiven() {
        return this.wootsGiven;
    }
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
    public void setMehs(int mehs)
    {
        this.mehs = mehs;
    }
    public void setWoots(int woots)
    {
        this.woots = woots;
    }
    public void setWootsGiven(int wootsGiven) {
        this.wootsGiven = wootsGiven;
    }
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
