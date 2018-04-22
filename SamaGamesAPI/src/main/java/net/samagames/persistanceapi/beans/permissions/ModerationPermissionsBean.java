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
public class ModerationPermissionsBean
{
    /* Database Structure

    Table : moderation_permissions
    +--------------------+------------+------+-----+---------+----------------+
    | Field              | Type       | Null | Key | Default | Extra          |
    +--------------------+------------+------+-----+---------+----------------+
    | groups_id          | tinyint(4) | NO   | PRI | NULL    | auto_increment |
    | mod_ban            | bit(1)     | NO   |     | NULL    |                |
    | mod_tp             | bit(1)     | NO   |     | NULL    |                |
    | mod_kick           | bit(1)     | NO   |     | NULL    |                |
    | mod_pardon         | bit(1)     | NO   |     | NULL    |                |
    | mod_mute_longtime  | bit(1)     | NO   |     | NULL    |                |
    | mod_mute           | bit(1)     | NO   |     | NULL    |                |
    | mod_channel        | bit(1)     | NO   |     | NULL    |                |
    | mod_channel_report | bit(1)     | NO   |     | NULL    |                |
    | mod_quiet          | bit(1)     | NO   |     | NULL    |                |
    +--------------------+------------+------+-----+---------+----------------+
    */

    // Defines
    private long groupsId;
    @Perm("mod.ban")
    private boolean modBan;
    @Perm("mod.tp")
    private boolean modTp;
    @Perm("mod.kick")
    private boolean modKick;
    @Perm("mod.pardon")
    private boolean modPardon;
    @Perm("mod.mute.longtime")
    private boolean modMuteLongtime;
    @Perm("mod.mute")
    private boolean modMute;
    @Perm("mod.channel")
    private boolean modChannel;
    @Perm("mod.channel.report")
    private boolean modChannelReport;
    @Perm("mod.quiet")
    private boolean modQuiet;

    // Constructor
    public ModerationPermissionsBean(long groupsId, boolean modBan, boolean modTp, boolean modKick, boolean modPardon, boolean modMuteLongtime, boolean modMute, boolean modChannel, boolean modChannelReport, boolean modQuiet)
    {
        this.groupsId = groupsId;
        this.modBan = modBan;
        this.modTp = modTp;
        this.modKick = modKick;
        this.modPardon = modPardon;
        this.modMuteLongtime = modMuteLongtime;
        this.modMute = modMute;
        this.modChannel = modChannel;
        this.modChannelReport = modChannelReport;
        this.modQuiet = modQuiet;
    }

    // Getters
    public long getGroupsId() { return this.groupsId; }
    public boolean isModBan() { return this.modBan; }
    public boolean isModTp() { return this.modTp; }
    public boolean isModKick() { return this.modKick; }
    public boolean isModPardon() { return this.modPardon; }
    public boolean isModMuteLongtime() { return this.modMuteLongtime; }
    public boolean isModMute() { return this.modMute; }
    public boolean isModChannel() { return this.modChannel; }
    public boolean isModChannelReport() {
        return modChannelReport;
    }
    public boolean isModQuiet() {
        return modQuiet;
    }

    // Setters
    public void setModBan(boolean modBan) { this.modBan = modBan; }
    public void setModTp(boolean modTp) { this.modTp = modTp; }
    public void setModKick(boolean modKick) { this.modKick = modKick; }
    public void setModPardon(boolean modPardon) { this.modPardon = modPardon; }
    public void setModMuteLongtime(boolean modMuteLongtime) { this.modMuteLongtime = modMuteLongtime; }
    public void setModMute(boolean modMute) { this.modMute = modMute; }
    public void setModChannel(boolean modChannel) { this.modChannel = modChannel; }
    public void setModChannelReport(boolean modChannelReport) {
        this.modChannelReport = modChannelReport;
    }
    public void setModQuiet(boolean modQuiet) {
        this.modQuiet = modQuiet;
    }

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
