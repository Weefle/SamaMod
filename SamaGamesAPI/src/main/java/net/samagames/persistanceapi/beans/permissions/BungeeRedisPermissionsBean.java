package net.samagames.persistanceapi.beans.permissions;

import net.samagames.persistanceapi.utils.Perm;
import net.samagames.persistanceapi.utils.Transcoder;
import java.util.Map;

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
public class BungeeRedisPermissionsBean
{
    /* Database Structure

    Table : bungee_redis_permissions
    +-------------------------------+------------+------+-----+---------+----------------+
    | Field                         | Type       | Null | Key | Default | Extra          |
    +-------------------------------+------------+------+-----+---------+----------------+
    | groups_id                     | tinyint(4) | NO   | PRI | NULL    | auto_increment |
    | bungeecord_command_list       | bit(1)     | NO   |     | NULL    |                |
    | bungeecord_command_find       | bit(1)     | NO   |     | NULL    |                |
    | redisbungee_command_lastseen  | bit(1)     | NO   |     | NULL    |                |
    | bungeecord_command_ip         | bit(1)     | NO   |     | NULL    |                |
    | redisbungee_command_sendtoall | bit(1)     | NO   |     | NULL    |                |
    | redisbungee_command_serverid  | bit(1)     | NO   |     | NULL    |                |
    | redisbunge_command_serverids  | bit(1)     | NO   |     | NULL    |                |
    | redisbungee_command_pproxy    | bit(1)     | NO   |     | NULL    |                |
    | redisbungee_command_plist     | bit(1)     | NO   |     | NULL    |                |
    | bungeecord_command_server     | bit(1)     | NO   |     | NULL    |                |
    | bungeecord_command_send       | bit(1)     | NO   |     | NULL    |                |
    | bungeecord_command_end        | bit(1)     | NO   |     | NULL    |                |
    | bungeecord_command_alert      | bit(1)     | NO   |     | NULL    |                |
    +-------------------------------+------------+------+-----+---------+----------------+
    */

    // Defines
    private long groupsId;
    @Perm("bungeecord.command.list")
    private boolean bungeecordCommandList;
    @Perm("bungeecord.command.find")
    private boolean bungeecordCommandFind;
    @Perm("redisbungee.command.lastseen")
    private boolean redisbungeeCommandLastSeen;
    @Perm("redisbungee.command.sendtoall")
    private boolean redisbungeeCommandSendtoAll;
    @Perm("bungeecord.command.ip")
    private boolean bungeecordCommandIp;
    @Perm("redisbungee.command.serverid")
    private boolean redisbungeeCommandServerId;
    @Perm("redisbungee.command.serverids")
    private boolean redisbungeCommandServerIds;
    @Perm("redisbungee.command.pproxy")
    private boolean redisbungeeCommandPproxy;
    @Perm("redisbungee.command.plist")
    private boolean redisbungeeCommandPlist;
    @Perm("bungeecord.command.server")
    private boolean bungeecordCommandServer;
    @Perm("bungeecord.command.send")
    private boolean bungeecordCommandSend;
    @Perm("bungeecord.command.end")
    private boolean bungeecordCommandEnd;
    @Perm("bungeecord.command.alert")
    private boolean bungeecordCommandAlert;

    // Constructor
    public BungeeRedisPermissionsBean(long groupsId, boolean bungeecordCommandList, boolean bungeecordCommandFind, boolean redisbungeeCommandLastSeen, boolean redisbungeeCommandSendtoAll,
                                      boolean bungeecordCommandIp, boolean redisbungeeCommandServerId, boolean redisbungeCommandServerIds, boolean redisbungeeCommandPproxy,
                                      boolean redisbungeeCommandPlist, boolean bungeecordCommandServer, boolean bungeecordCommandSend, boolean bungeecordCommandEnd, boolean bungeecordCommandAlert)
    {
        this.groupsId = groupsId;
        this.bungeecordCommandList = bungeecordCommandList;
        this.bungeecordCommandFind = bungeecordCommandFind;
        this.redisbungeeCommandLastSeen = redisbungeeCommandLastSeen;
        this.redisbungeeCommandSendtoAll = redisbungeeCommandSendtoAll;
        this.bungeecordCommandIp = bungeecordCommandIp;
        this.redisbungeeCommandServerId = redisbungeeCommandServerId;
        this.redisbungeCommandServerIds = redisbungeCommandServerIds;
        this.redisbungeeCommandPproxy = redisbungeeCommandPproxy;
        this.redisbungeeCommandPlist = redisbungeeCommandPlist;
        this.bungeecordCommandServer = bungeecordCommandServer;
        this.bungeecordCommandSend = bungeecordCommandSend;
        this.bungeecordCommandEnd = bungeecordCommandEnd;
        this.bungeecordCommandAlert = bungeecordCommandAlert;
    }

    // Getters
    public long getGroupsId() { return this.groupsId; }
    public boolean isBungeecordCommandList() { return this.bungeecordCommandList; }
    public boolean isBungeecordCommandFind() { return this.bungeecordCommandFind; }
    public boolean isRedisbungeeCommandLastSeen() { return this.redisbungeeCommandLastSeen; }
    public boolean isRedisbungeeCommandSendtoAll() { return this.redisbungeeCommandSendtoAll; }
    public boolean isBungeecordCommandIp() { return this.bungeecordCommandIp; }
    public boolean isRedisbungeeCommandServerId() { return this.redisbungeeCommandServerId; }
    public boolean isRedisbungeCommandServerIds() { return this.redisbungeCommandServerIds; }
    public boolean isRedisbungeeCommandPproxy() { return this.redisbungeeCommandPproxy; }
    public boolean isRedisbungeeCommandPlist() { return this.redisbungeeCommandPlist; }
    public boolean isBungeecordCommandServer() { return this.bungeecordCommandServer; }
    public boolean isBungeecordCommandSend() { return this.bungeecordCommandSend; }
    public boolean isBungeecordCommandEnd() { return this.bungeecordCommandEnd; }
    public boolean isBungeecordCommandAlert() { return this.bungeecordCommandAlert; }

    // Setters
    public void setBungeecordCommandList(boolean bungeecordCommandList) { this.bungeecordCommandList = bungeecordCommandList; }
    public void setBungeecordCommandFind(boolean bungeecordCommandFind) { this.bungeecordCommandFind = bungeecordCommandFind; }
    public void setRedisbungeeCommandLastSeen(boolean redisbungeeCommandLastSeen) { this.redisbungeeCommandLastSeen = redisbungeeCommandLastSeen; }
    public void setRedisbungeeCommandSendtoAll(boolean redisbungeeCommandSendtoAll) { this.redisbungeeCommandSendtoAll = redisbungeeCommandSendtoAll; }
    public void setBungeecordCommandIp(boolean bungeecordCommandIp) { this.bungeecordCommandIp = bungeecordCommandIp; }
    public void setRedisbungeeCommandServerId(boolean redisbungeeCommandServerId) { this.redisbungeeCommandServerId = redisbungeeCommandServerId; }
    public void setRedisbungeCommandServerIds(boolean redisbungeCommandServerIds) { this.redisbungeCommandServerIds = redisbungeCommandServerIds; }
    public void setRedisbungeeCommandPproxy(boolean redisbungeeCommandPproxy) { this.redisbungeeCommandPproxy = redisbungeeCommandPproxy; }
    public void setRedisbungeeCommandPlist(boolean redisbungeeCommandPlist) { this.redisbungeeCommandPlist = redisbungeeCommandPlist; }
    public void setBungeecordCommandServer(boolean bungeecordCommandServer) { this.bungeecordCommandServer = bungeecordCommandServer; }
    public void setBungeecordCommandSend(boolean bungeecordCommandSend) { this.bungeecordCommandSend = bungeecordCommandSend; }
    public void setBungeecordCommandEnd(boolean bungeecordCommandEnd) { this.bungeecordCommandEnd = bungeecordCommandEnd; }
    public void setBungeecordCommandAlert(boolean bungeecordCommandAlert) { this.bungeecordCommandAlert = bungeecordCommandAlert; }

    // Reverse the bean to HashMap
    public Map<String, Boolean> getHashMap()
    {
        return Transcoder.getHashMapPerm(this);
    }

    // Set a value into the HashMap
    public void set(String key, Boolean value)
    {
        Transcoder.setAnnotationValue(this, key, value);
    }
}
