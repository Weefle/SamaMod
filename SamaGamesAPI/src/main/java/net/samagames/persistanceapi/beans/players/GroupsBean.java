package net.samagames.persistanceapi.beans.players;

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
public class GroupsBean
{
    /* Database Structure

    Table : groups
    +------------+--------------+------+-----+---------+----------------+
    | Field      | Type         | Null | Key | Default | Extra          |
    +------------+--------------+------+-----+---------+----------------+
    | group_id   | tinyint(4)   | NO   | PRI | NULL    | auto_increment |
    | group_name | varchar(255) | NO   |     | NULL    |                |
    | rank       | int(11)      | NO   |     | NULL    |                |
    | tag        | varchar(255) | NO   |     | NULL    |                |
    | prefix     | varchar(255) | NO   |     | NULL    |                |
    | suffix     | varchar(255) | NO   |     | NULL    |                |
    | multiplier | int(11)      | NO   |     | NULL    |                |
    +------------+--------------+------+-----+---------+----------------+
    */

    // Defines
    private long groupId;
    private String groupName;
    private int rank;
    private String tag;
    private String prefix;
    private String suffix;
    private int multiplier;

    //Empty constructor because we fill it when needed
    public GroupsBean()
    {
        super();
    }

    // Constructor
    public GroupsBean(long groupId, String groupName, int rank, String tag, String prefix, String suffix, int multiplier)
    {
        this.groupId = groupId;
        this.groupName = groupName;
        this.rank = rank;
        this.tag = tag;
        this.prefix = prefix;
        this.suffix = suffix;
        this.multiplier = multiplier;
    }

    // Getters
    public long getGroupId() { return this.groupId; }
    public String getPgroupName() { return this.groupName; }
    public int getRank() { return this.rank; }
    public String getTag() { return this.tag; }
    public String getPrefix() { return this.prefix; }
    public String getSuffix() { return this.suffix; }
    public int getMultiplier() { return this.multiplier; }

    // Setters
    public void setPgroupName(String groupName) { this.groupName = groupName; }
    public void setRank(int rank) { this.rank = rank; }
    public void setTag(String tag) { this.tag = tag; }
    public void setPrefix(String prefix) { this.prefix = prefix; }
    public void setSuffix(String suffix) { this.suffix = suffix; }
    public void setMultiplier(int multiplier) { this.multiplier = multiplier; }
    public void setGroupId(long groupId) { this.groupId = groupId; }

}
