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
public class DenunciationBean
{
    /* Database Structure

    Table : denunciation
    +-----------------+--------------+------+-----+---------------------+----------------+
    | Field           | Type         | Null | Key | Default             | Extra          |
    +-----------------+--------------+------+-----+---------------------+----------------+
    | denouncement_id | int(11)      | NO   | PRI | NULL                | auto_increment |
    | denouncer       | binary(16)   | NO   |     | NULL                |                |
    | date            | timestamp    | NO   |     | 0000-00-00 00:00:00 |                |
    | reason          | varchar(255) | NO   |     | NULL                |                |
    | suspect_name    | varchar(255) | NO   |     | NULL                |                |
    +-----------------+--------------+------+-----+---------------------+----------------+
    */

    // Defines
    private long denouncementId;
    private UUID denouncer;
    private UUID suspect;
    private Timestamp date;
    private String reason;
    private String suspectName;

    // Constructor
    public DenunciationBean(UUID denouncer, Timestamp date, String reason, String suspectName)
    {
        this.denouncer = denouncer;
        this.date = date;
        this.reason = reason;
        this.suspectName = suspectName;
    }

    // Getters
    public long getDenouncementId() { return denouncementId; }
    public UUID getDenouncer() { return denouncer; }
    public Timestamp getDate() { return date; }
    public String getReason() { return reason; }
    public String getSuspectName() { return suspectName; }

    // Setters
    public void setDenouncer(UUID denouncer) { this.denouncer = denouncer; }
    public void setDate(Timestamp date) { this.date = date; }
    public void setReason(String reason) { this.reason = reason; }
    public void setSuspectName(String suspectName) { this.suspectName = suspectName; }

	public UUID getSuspect() {
		return suspect;
	}

	public void setSuspect(UUID suspect) {
		this.suspect = suspect;
	}

}
