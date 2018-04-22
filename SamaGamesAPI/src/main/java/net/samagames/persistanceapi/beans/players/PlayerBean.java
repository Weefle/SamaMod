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
public class PlayerBean
{
    /* Database Structure

    Table : players
    +-------------+--------------+------+-----+---------------------+-------+
    | Field       | Type         | Null | Key | Default             | Extra |
    +-------------+--------------+------+-----+---------------------+-------+
    | uuid        | binary(16)   | NO   | PRI | NULL                |       |
    | name        | varchar(255) | NO   |     | NULL                |       |
    | nickname    | varchar(45)  | YES  |     | NULL                |       |
    | coins       | int(11)      | YES  |     | NULL                |       |
    | stars       | int(11)      | YES  |     | NULL                |       |
    | powders     | int(11)      | YES  |     | NULL                |       |
    | last_login  | timestamp    | NO   |     | 0000-00-00 00:00:00 |       |
    | first_login | timestamp    | NO   |     | 0000-00-00 00:00:00 |       |
    | last_ip     | varchar(15)  | YES  |     | NULL                |       |
    | toptp_key   | varchar(32)  | YES  |     | NULL                |       |
    | group_id    | bigint(20)   | NO   |     | NULL                |       |
    +-------------+--------------+------+-----+---------------------+-------+
    */

    // Define attributes
    private UUID uuid;
    private String name;
    private String nickName;
    private int coins;
    private int stars;
    private int powders;
    private Timestamp lastLogin;
    private Timestamp firstLogin;
    private String lastIP;
    private String topTpKey;
    private long groupId;

    //Empty constructor because we fill it when needed
    public PlayerBean()
    {
        super();
    }

    // Constructor
    public PlayerBean(UUID uuid, String name, String nickName, int coins, int stars, int powders, Timestamp lastLogin, Timestamp firstLogin, String lastIP, String topTpKey, long groupId)
    {
        this.uuid = uuid;
        this.name = name;
        this.nickName = nickName;
        this.coins = coins;
        this.stars = stars;
        this.powders = powders;
        this.lastLogin = lastLogin;
        this.firstLogin = firstLogin;
        this.lastIP = lastIP;
        this.topTpKey = topTpKey;
        this.groupId = groupId;
    }

    // Getters
    public UUID getUuid() {
        return this.uuid;
    }
    public String getName() {
        return this.name;
    }
    public String getNickName() { return this.nickName; }
    public int getCoins() {
        return this.coins;
    }
    public int getStars() {
        return this.stars;
    }
    public int getPowders() {
        return this.powders;
    }
    public Timestamp getLastLogin() {
        return this.lastLogin;
    }
    public Timestamp getFirstLogin() { return this.firstLogin; }
    public String getLastIP() { return this.lastIP; }
    public String getTopTpKey() { return this.topTpKey; }
    public long getGroupId() { return this.groupId; }

    // Setters
    public void setUuid(UUID uuid)
    {
        this.uuid = uuid;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public void setCoins(int coins) {
        this.coins = coins;
    }
    public void setStars(int stars) {
        this.stars = stars;
    }
    public void setPowders(int powders) {
        this.powders = powders;
    }
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
    public void setFirstLogin(Timestamp firstLogin) {
        this.firstLogin = firstLogin;
    }
    public void setLastIP(String lastIP) { this.lastIP = lastIP; }
    public void setTopTpKey(String topTpKey) { this.topTpKey = topTpKey; }
    public void setGroupId(long groupId) { this.groupId = groupId; }


}
