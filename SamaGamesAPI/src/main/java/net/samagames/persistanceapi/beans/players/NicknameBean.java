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
public class NicknameBean
{
    /* Database Structure

    Table : Nickname
    +-------------+-------------+------+-----+---------+----------------+
    | Field       | Type        | Null | Key | Default | Extra          |
    +-------------+-------------+------+-----+---------+----------------+
    | nick_id     | int(11)     | NO   | PRI | NULL    | auto_increment |
    | nickname    | varchar(16) | NO   |     | NULL    |                |
    | blacklisted | bit(1)      | NO   |     | NULL    |                |
    | used        | bit(1)      | NO   |     | NULL    |                |
    +-------------+-------------+------+-----+---------+----------------+
    */

    // Defines
    private long nickId;
    private String nickname;
    private boolean blackListed;
    private boolean used;

    //Empty constructor because we fill it when needed
    public NicknameBean()
    {
        super();
    }

    // Constructor
    public NicknameBean(long nickId, String nickname, boolean blackListed, boolean used)
    {
        this.nickId = nickId;
        this.nickname = nickname;
        this.blackListed = blackListed;
        this.used = used;
    }

    // Getters
    public long getNickId() { return this.nickId; }
    public String getNickname() { return this.nickname; }
    public boolean isBlackListed() { return this.blackListed; }
    public boolean isUsed() { return this.used; }

    // Setters
    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setBlackListed(boolean blackListed) { this.blackListed = blackListed; }
    public void setUsed(boolean used) { this.used = used; }
}
