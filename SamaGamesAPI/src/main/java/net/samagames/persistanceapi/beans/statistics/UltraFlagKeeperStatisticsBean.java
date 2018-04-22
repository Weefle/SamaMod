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
public class UltraFlagKeeperStatisticsBean
{
    /* Database Structure

    Table: ultraflagkeeper_stats
    +----------------+------------+------+-----+---------------------+-------+
    | Field          | Type       | Null | Key | Default             | Extra |
    +----------------+------------+------+-----+---------------------+-------+
    | uuid           | binary(16) | NO   | PRI | NULL                |       |
    | damages        | int(11)    | NO   |     | NULL                |       |
    | deaths         | int(11)    | NO   |     | NULL                |       |
    | kills          | int(11)    | NO   |     | NULL                |       |
    | max_damages    | int(11)    | NO   |     | NULL                |       |
    | played_games   | int(11)    | NO   |     | NULL                |       |
    | wins           | int(11)    | NO   |     | NULL                |       |
    | flags_captured | int(11)    | NO   |     | NULL                |       |
    | flags_returned | int(11)    | NO   |     | NULL                |       |
    | creation_date  | timestamp  | NO   |     | 0000-00-00 00:00:00 |       |
    | update_date    | timestamp  | NO   |     | 0000-00-00 00:00:00 |       |
    | played_time    | bigint(20) | NO   |     | NULL                |       |
    +----------------+------------+------+-----+---------------------+-------+
    */

    // Defines
    private UUID uuid;
    private int damages;
    private int deaths;
    private int kills;
    private int maxDamages;
    private int playedGames;
    private int wins;
    private int flagsCaptured;
    private int flagsReturned;
    private Timestamp creationDate;
    private Timestamp updateDate;
    private long playedTime;

    // Constructor
    @ConstructorProperties({"uuid", "damages", "deaths", "kills", "maxDamages", "playedGames", "wins", "flagsCaptured", "flagsReturned", "creationDate", "updateDate", "playedTime"})
    public UltraFlagKeeperStatisticsBean(UUID uuid, int damages, int deaths, int kills, int maxDamages, int playedGames, int wins, int flagsCaptured, int flagsReturned, Timestamp creationDate, Timestamp updateDate, long playedTime)
    {
        this.setUuid(uuid);
        this.damages = damages;
        this.deaths = deaths;
        this.kills = kills;
        this.maxDamages = maxDamages;
        this.playedGames = playedGames;
        this.wins = wins;
        this.flagsCaptured = flagsCaptured;
        this.flagsReturned = flagsReturned;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.playedTime = playedTime;
    }

    // Getters
    public int getDamages()
    {
        return this.damages;
    }
    public int getDeaths()
    {
        return this.deaths;
    }
    public int getKills()
    {
        return this.kills;
    }
    public int getMaxDamages()
    {
        return this.maxDamages;
    }
    public int getPlayedGames()
    {
        return this.playedGames;
    }
    public int getWins()
    {
        return this.wins;
    }
    public int getFlagsCaptured()
    {
        return this.flagsCaptured;
    }
    public int getFlagsReturned()
    {
        return this.flagsReturned;
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
    public void setDamages(int damages)
    {
        this.damages = damages;
    }
    public void setDeaths(int deaths)
    {
        this.deaths = deaths;
    }
    public void setKills(int kills)
    {
        this.kills = kills;
    }
    public void setMaxDamages(int maxDamages)
    {
        this.maxDamages = maxDamages;
    }
    public void setPlayedGames(int playedGames)
    {
        this.playedGames = playedGames;
    }
    public void setWins(int wins)
    {
        this.wins = wins;
    }
    public void setFlagsCaptured(int flagsCaptured)
    {
        this.flagsCaptured = flagsCaptured;
    }
    public void setFlagsReturned(int flagsReturned)
    {
        this.flagsReturned = flagsReturned;
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
