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
public class AchievementCategoryBean
{
    /* Database Structure

    Table : achievements_categories
    +----------------------+--------------+------+-----+--------------+----------------+
    | Field                | Type         | Null | Key | Default      | Extra          |
    +----------------------+--------------+------+-----+--------------+----------------+
    | category_id          | int(11)      | NO   | PRI | NULL         | auto_increment |
    | category_name        | varchar(45)  | NO   |     | Non définie  |                |
    | category_description | varchar(255) | NO   |     | Non définie  |                |
    | item_minecraft_id    | varchar(45)  | NO   |     | NULL         |                |
    | parent_id            | int(11)      | NO   |     | Non définie  |                |
    +----------------------+--------------+------+-----+--------------+----------------+
    */

    // Defines
    private int categoryId;
    private String categoryName;
    private String categoryDescription;
    private String itemMinecraftId;
    private int parentId;

    // Constructor
    @ConstructorProperties({"categoryId", "categoryName", "categoryDescription", "itemMinecraftId", "parentId"})
    public AchievementCategoryBean(int categoryId, String categoryName, String categoryDescription, String itemMinecraftId, int parentId)
    {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.itemMinecraftId = itemMinecraftId;
        this.parentId = parentId;
    }

    // Getters
    public int getCategoryId() { return this.categoryId; }
    public String getCategoryName() { return this.categoryName; }
    public String getCategoryDescription() { return this.categoryDescription; }
    public String getItemMinecraftId() { return this.itemMinecraftId; }
    public int getParentId() { return this.parentId; }

    // Setters
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
    public void setCategoryDescription(String categoryDescription) { this.categoryDescription = categoryDescription; }
    public void setItemMinecraftId(String itemMinecraftId) { this.itemMinecraftId = itemMinecraftId; }
    public void setParentId(int parentId) { this.parentId = parentId; }
}
