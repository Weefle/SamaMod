package net.samagames.persistanceapi.beans.achievements;

import java.beans.ConstructorProperties;

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
public class AchievementBean
{
    /* Database Structure

    Table : achievements
    +-------------------------+--------------+------+-----+--------------+----------------+
    | Field                   | Type         | Null | Key | Default      | Extra          |
    +-------------------------+--------------+------+-----+--------------+----------------+
    | achievement_id          | int(11)      | NO   | PRI | NULL         | auto_increment |
    | achievement_name        | varchar(45)  | NO   |     | Non définie  |                |
    | achievement_description | varchar(255) | NO   |     | Non définie  |                |
    | progress_target         | int(11)      | NO   |     | Non définie  |                |
    | category_id             | int(11)      | NO   |     | Non définie  |                |
    +-------------------------+--------------+------+-----+--------------+----------------+
    */

    // Defines
    private int achievementId;
    private String achievementName;
    private String achievementDescription;
    private int progressTarget;
    private int categoryId;

    // Constructor
    @ConstructorProperties({"achievementId", "achievementName", "achievementDescription", "progressTarget", "categoryId"})
    public AchievementBean(int achievementId, String achievementName, String achievementDescription, int progressTarget, int categoryId)
    {
        this.achievementId = achievementId;
        this.achievementName = achievementName;
        this.achievementDescription = achievementDescription;
        this.progressTarget = progressTarget;
        this.categoryId = categoryId;
    }

    // Getters
    public int getAchievementId() { return this.achievementId; }
    public String getAchievementName() { return this.achievementName; }
    public String getAchievementDescription() { return this.achievementDescription; }
    public int getProgressTarget() { return this.progressTarget; }
    public int getCategoryId() { return this.categoryId; }

    // Setters
    public void setAchievementName(String achievementName) { this.achievementName = achievementName; }
    public void setAchievementDescription(String achievementDescription) { this.achievementDescription = achievementDescription; }
    public void setProgressTarget(int progressTarget) { this.progressTarget = progressTarget; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
