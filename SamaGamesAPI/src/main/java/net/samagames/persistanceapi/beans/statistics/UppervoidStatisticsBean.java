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
public class UppervoidStatisticsBean
{
    /* Database Structure

    Table : uppervoid_stats
    +---------------+------------+------+-----+---------------------+-------+
    | Field         | Type       | Null | Key | Default             | Extra |
    +---------------+------------+------+-----+---------------------+-------+
    | uuid          | binary(16) | NO   | PRI | NULL                |       |
    | blocks        | int(11)    | NO   |     | NULL                |       |
    | grenades      | int(11)    | NO   |     | NULL                |       |
    | kills         | int(11)    | NO   |     | NULL                |       |
    | played_games  | int(11)    | NO   |     | NULL                |       |
    | tnt_launched  | int(11)    | NO   |     | NULL                |       |
    | wins          | int(11)    | NO   |     | NULL                |       |
    | creation_date | timestamp  | NO   |     | 0000-00-00 00:00:00 |       |
    | update_date   | timestamp  | NO   |     | 0000-00-00 00:00:00 |       |
    | played_time   | bigint(20) | NO   |     | NULL                |       |
    +---------------+------------+------+-----+---------------------+-------+
    */

    // Defines
    private UUID uuid;
    private int blocks;
    private int grenades;
    private int kills;
    private int playedGames;
    private int tntLaunched;
    private int wins;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private long playedTime;

    // Constructor
    @ConstructorProperties({"uuid", "blocks", "grenades", "kills", "playedGames", "tntLaunched", "wins", "creationDate", "updateDate", "playedTime"})
    public UppervoidStatisticsBean(UUID uuid, int blocks, int grenades, int kills, int playedGames, int tntLaunched, int wins, Timestamp creationDate, Timestamp updateDate, long playedTime)
    {
        this.setUuid(uuid);
        this.blocks = blocks;
        this.grenades = grenades;
        this.kills = kills;
        this.playedGames = playedGames;
        this.tntLaunched = tntLaunched;
        this.wins = wins;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.playedTime = playedTime;
    }

    // Getters
    public int getBlocks()
    {
        return this.blocks;
    }
    public int getGrenades()
    {
        return this.grenades;
    }
    public int getKills()
    {
        return this.kills;
    }
    public int getPlayedGames()
    {
        return this.playedGames;
    }
    public int getTntLaunched()
    {
        return this.tntLaunched;
    }
    public int getWins()
    {
        return this.wins;
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
    public void setBlocks(int blocks)
    {
        this.blocks = blocks;
    }
    public void setGrenades(int grenades)
    {
        this.grenades = grenades;
    }
    public void setKills(int kills)
    {
        this.kills = kills;
    }
    public void setPlayedGames(int playedGames)
    {
        this.playedGames = playedGames;
    }
    public void setTntLaunched(int tntLaunched)
    {
        this.tntLaunched = tntLaunched;
    }
    public void setWins(int wins)
    {
        this.wins = wins;
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
