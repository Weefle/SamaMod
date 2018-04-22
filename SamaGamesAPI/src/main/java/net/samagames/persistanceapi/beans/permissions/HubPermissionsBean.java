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
public class HubPermissionsBean
{
    /* Database Structure

    Table : hub_permissions
    +----------------------------+------------+------+-----+---------+----------------+
    | Field                      | Type       | Null | Key | Default | Extra          |
    +----------------------------+------------+------+-----+---------+----------------+
    | groups_id                  | tinyint(4) | NO   | PRI | NULL    | auto_increment |
    | hub_jukebox_lock           | bit(1)     | NO   |     | NULL    |                |
    | hub_jukebox_next           | bit(1)     | NO   |     | NULL    |                |
    | hub_jukebox_clear          | bit(1)     | NO   |     | NULL    |                |
    | hub_mod_slow               | bit(1)     | NO   |     | NULL    |                |
    | hub_mod_shutup             | bit(1)     | NO   |     | NULL    |                |
    | hub_admin_npc              | bit(1)     | NO   |     | NULL    |                |
    | hub_admin_sign             | bit(1)     | NO   |     | NULL    |                |
    | hub_anguille               | bit(1)     | NO   |     | NULL    |                |
    | hub_jukebox_nbs            | bit(1)     | NO   |     | NULL    |                |
    | hub_admin_evacuate         | bit(1)     | NO   |     | NULL    |                |
    | hub_announce               | bit(1)     | NO   |     | NULL    |                |
    | hub_gadgets_cooldownbypass | bit(1)     | NO   |     | NULL    |                |
    | hub_gadgets_nuke           | bit(1)     | NO   |     | NULL    |                |
    | hub_jukebox_limitbypass    | bit(1)     | NO   |     | NULL    |                |
    | hub_jukebox_limitstaff     | bit(1)     | NO   |     | NULL    |                |
    | hub_bypassmute             | bit(1)     | NO   |     | NULL    |                |
    | hub_fly                    | bit(1)     | NO   |     | NULL    |                |
    | hub_debug_sign             | bit(1)     | NO   |     | NULL    |                |
    | hub_sign_selection         | bit(1)     | NO   |     | NULL    |                |
    | hub_beta_vip               | bit(1)     | NO   |     | NULL    |                |
    | hub_admin_pearl            | bit(1)     | NO   |     | NULL    |                |
    | hub_animating_event        | bit(1)     | NO   |     | NULL    |                |
    +----------------------------+------------+------+-----+---------+----------------+
    */

    // Defines
    private long groupsId;
    @Perm("hub.jukebox.lock")
    private boolean hubJukeboxLock;
    @Perm("hub.jukebox.next")
    private boolean hubJukeboxNext;
    @Perm("hub.jukebox.clear")
    private boolean hubJukeBoxClear;
    @Perm("hub.mod.slow")
    private boolean hubModSlow;
    @Perm("hub.mod.shutup")
    private boolean hubModShutup;
    @Perm("hub.admin.npc")
    private boolean hubAdminNpc;
    @Perm("hub.admin.sign")
    private boolean hubAdminSign;
    @Perm("hub.anguille")
    private boolean hubAnguille;
    @Perm("hub.jukebox.nbs")
    private boolean hubJukeboxNbs;
    @Perm("hub.admin.evacuate")
    private boolean hubAdminEvacuate;
    @Perm("hub.announce")
    private boolean hubAnnounce;
    @Perm("hub.gadgets.cooldownbypass")
    private boolean hubGadgetsCooldownbypass;
    @Perm("hub.gadgets.nuke")
    private boolean hubGadgetsNuke;
    @Perm("hub.jukebox.limitbypass")
    private boolean hubJukeboxLimitbypass;
    @Perm("hub.jukebox.limitstaff")
    private boolean hubJukeboxLimitstaff;
    @Perm("hub.bypassmute")
    private boolean hubBypassmute;
    @Perm("hub.fly")
    private boolean hubFly;
    @Perm("hub.debug.sign")
    private boolean hubDebugSign;
    @Perm("hub.sign.selection")
    private boolean hubSignSelection;
    @Perm("hub.beta.vip")
    private boolean hubBetaVIP;
    @Perm("hub.admin.pearl")
    private boolean hubAdminPearl;
    @Perm("hub.animating.event")
    private boolean hubAnimatingEvent;

    // Constructor
    public HubPermissionsBean(long groupsId, boolean hubJukeboxLock, boolean hubJukeboxNext, boolean hubJukeBoxClear, boolean hubModSlow, boolean hubModShutup, boolean hubAdminNpc,
                              boolean hubAdminSign, boolean hubAnguille, boolean hubJukeboxNbs, boolean hubAdminEvacuate, boolean hubAnnounce, boolean hubGadgetsCooldownbypass,
                              boolean hubGadgetsNuke, boolean hubJukeboxLimitbypass, boolean hubJukeboxLimitstaff, boolean hubBypassmute, boolean hubFly, boolean hubDebugSign,
                              boolean hubSignSelection, boolean hubBetaVIP, boolean hubAdminPearl, boolean hubAnimatingEvent)
    {
        this.groupsId = groupsId;
        this.hubJukeboxLock = hubJukeboxLock;
        this.hubJukeboxNext = hubJukeboxNext;
        this.hubJukeBoxClear = hubJukeBoxClear;
        this.hubModSlow = hubModSlow;
        this.hubModShutup = hubModShutup;
        this.hubAdminNpc = hubAdminNpc;
        this.hubAdminSign = hubAdminSign;
        this.hubAnguille = hubAnguille;
        this.hubJukeboxNbs = hubJukeboxNbs;
        this.hubAdminEvacuate = hubAdminEvacuate;
        this.hubAnnounce = hubAnnounce;
        this.hubGadgetsCooldownbypass = hubGadgetsCooldownbypass;
        this.hubGadgetsNuke = hubGadgetsNuke;
        this.hubJukeboxLimitbypass = hubJukeboxLimitbypass;
        this.hubJukeboxLimitstaff = hubJukeboxLimitstaff;
        this.hubBypassmute = hubBypassmute;
        this.hubFly = hubFly;
        this.hubDebugSign = hubDebugSign;
        this.hubSignSelection = hubSignSelection;
        this.hubBetaVIP = hubBetaVIP;
        this.hubAdminPearl = hubAdminPearl;
        this.hubAnimatingEvent = hubAnimatingEvent;
    }

    // Getters
    public long getGroupsId() { return this.groupsId; }
    public boolean isHubJukeboxLock() { return this.hubJukeboxLock; }
    public boolean isHubJukeboxNext() { return this.hubJukeboxNext; }
    public boolean isHubJukeBoxClear() { return this.hubJukeBoxClear; }
    public boolean isHubModSlow() { return this.hubModSlow; }
    public boolean isHubModShutup() { return this.hubModShutup; }
    public boolean isHubAdminSign() { return this.hubAdminSign; }
    public boolean isHubAdminNpc() { return this.hubAdminNpc; }
    public boolean isHubAnguille() { return this.hubAnguille; }
    public boolean isHubJukeboxNbs() { return this.hubJukeboxNbs; }
    public boolean isHubAdminEvacuate() { return this.hubAdminEvacuate; }
    public boolean isHubAnnounce() { return this.hubAnnounce; }
    public boolean isHubGadgetsCooldownbypass() { return this.hubGadgetsCooldownbypass; }
    public boolean isHubGadgetsNuke() { return this.hubGadgetsNuke; }
    public boolean isHubJukeboxLimitbypass() { return this.hubJukeboxLimitbypass; }
    public boolean isHubJukeboxLimitstaff() { return this.hubJukeboxLimitstaff; }
    public boolean isHubBypassmute() { return this.hubBypassmute; }
    public boolean isHubFly() { return this. hubFly; }
    public boolean isHubDebugSign() { return this.hubDebugSign; }
    public boolean isHubSignSelection() { return this.hubSignSelection; }
    public boolean isHubBetaVIP() { return this.hubBetaVIP; }
    public boolean isHubAdminPearl() { return this.hubAdminPearl; }
    public boolean isHubAnimatingEvent() { return this.hubAnimatingEvent; }

    // Setters
    public void setHubJukeboxLock(boolean hubJukeboxLock) { this.hubJukeboxLock = hubJukeboxLock; }
    public void setHubJukeboxNext(boolean hubJukeboxNext) { this.hubJukeboxNext = hubJukeboxNext; }
    public void setHubjukeboxClear(boolean hubJukeBoxClear) { this.hubJukeBoxClear = hubJukeBoxClear; }
    public void setHubModSlow(boolean hubModSlow) { this.hubModSlow = hubModSlow; }
    public void setHubModShutup(boolean hubModShutup) { this.hubModShutup = hubModShutup; }
    public void setHubAdminSign(boolean hubAdminSign) { this.hubAdminSign = hubAdminSign; }
    public void setHubAdminNpc(boolean hubAdminNpc) { this.hubAdminNpc = hubAdminNpc; }
    public void setHubAnguille(boolean hubAnguille) { this.hubAnguille = hubAnguille; }
    public void setHubJukeboxNbs(boolean hubJukeboxNbs) { this.hubJukeboxNbs = hubJukeboxNbs; }
    public void setHubAdminEvacuate(boolean hubAdminEvacuate) { this.hubAdminEvacuate = hubAdminEvacuate; }
    public void setHubAnnounce(boolean hubAnnounce) { this.hubAnnounce = hubAnnounce; }
    public void setHubGadgetsCooldownbypass(boolean hubGadgetsCooldownbypass) { this.hubGadgetsCooldownbypass = hubGadgetsCooldownbypass; }
    public void setHubGadgetsNuke(boolean hubGadgetsNuke) { this.hubGadgetsNuke = hubGadgetsNuke; }
    public void setHubJukeboxLimitbypass(boolean hubJukeboxLimitbypass) { this.hubJukeboxLimitbypass = hubJukeboxLimitbypass; }
    public void setHubJukeboxLimitstaff(boolean hubJukeboxLimitstaff) { this.hubJukeboxLimitstaff = hubJukeboxLimitstaff; }
    public void setHubBypassmute(boolean hubBypassmute) { this.hubBypassmute = hubBypassmute; }
    public void setHubFly(boolean hubFly) { this.hubFly = hubFly; }
    public void setHubDebugSign(boolean hubDebugSign) { this.hubDebugSign = hubDebugSign; }
    public void setHubSignSelection(boolean hubSignSelection) { this.hubSignSelection = hubSignSelection; }
    public void setHubBetaVIP(boolean hubBetaVIP) { this.hubBetaVIP = hubBetaVIP; }
    public void setHubAdminPearl(boolean hubAdminPearl) { this.hubAdminPearl = hubAdminPearl; }
    public void setHubAnimatingEvent(boolean hubAnimatingEvent) { this.hubAnimatingEvent = hubAnimatingEvent; }

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
