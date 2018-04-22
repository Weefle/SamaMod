package net.samagames.persistanceapi.beans.achievements;

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
public class AchievementProgressBean
{
    /* Database structure

    Table : achievements_progress
    +----------------+------------+------+-----+---------------------+----------------+
    | Field          | Type       | Null | Key | Default             | Extra          |
    +----------------+------------+------+-----+---------------------+----------------+
    | progress_id    | bigint(20) | NO   | PRI | NULL                | auto_increment |
    | achievement_id | int(11)    | YES  | MUL | NULL                |                |
    | progress       | int(11)    | YES  |     | NULL                |                |
    | start_date     | timestamp  | NO   |     | 0000-00-00 00:00:00 |                |
    | unlock_date    | timestamp  | YES  |     | NULL                |                |
    | uuid_player    | binary(16) | NO   |     | NULL                |                |
    +----------------+------------+------+-----+---------------------+----------------+
    */

    // Defines
    private long progressId;
    private int achievementId;
    private int progress;
    private Timestamp startDate;
    private Timestamp unlockDate;
    private UUID uuidPlayer;

    // Constructor
    @ConstructorProperties({"progressId", "achievementId", "progress", "startDate", "unlockDate", "uuidPlayer"})
    public AchievementProgressBean(long progressId, int achievementId, int progress, Timestamp startDate, Timestamp unlockDate, UUID uuidPlayer)
    {
        this.progressId = progressId;
        this.achievementId = achievementId;
        this.progress = progress;
        this.startDate = startDate;
        this.unlockDate = unlockDate;
        this.uuidPlayer = uuidPlayer;
    }

    // Getters
    public long getProgressId() { return this.progressId; }
    public int getAchievementId() { return this.achievementId; }
    public int getProgress() { return this.progress; }
    public Timestamp getStartDate() { return this.startDate; }
    public Timestamp getUnlockDate() { return this.unlockDate; }
    public UUID getUuidPlayer() { return this.uuidPlayer; }

    // Setters

    public void setProgressId(long progressId) {
        this.progressId = progressId;
    }
    public void setAchievementId(int achievementId) { this.achievementId = achievementId; }
    public void setProgress(int progress) { this.progress = progress; }
    public void setStartDate(Timestamp startDate) { this.startDate = startDate; }
    public void setUnlockDate(Timestamp unlockDate) { this.unlockDate = unlockDate; }
    public void setUuidPlayer(UUID uuidPlayer) { this.uuidPlayer = uuidPlayer; }
}
