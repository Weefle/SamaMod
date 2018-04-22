package net.samagames.persistanceapi.beans.permissions;

import java.util.HashMap;

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
public class PlayerPermissionsBean
{
    // Defines aggregation of permissions
    private APIPermissionsBean apiPermissions;
    private BukkitPermissionsBean bukkitPermissions;
    private BungeeRedisPermissionsBean bungeeRedisPermissions;
    private HubPermissionsBean hubPermissions;
    private ModerationPermissionsBean moderationPermissions;
    private ProxiesPermissionsBean proxiesPermissions;
    private StaffPermissionsBean staffPermissions;

    // Constructor
    public PlayerPermissionsBean(APIPermissionsBean apiPermissions, BukkitPermissionsBean bukkitPermissions, BungeeRedisPermissionsBean bungeeRedisPermissions, HubPermissionsBean hubPermissions,
                                ModerationPermissionsBean moderationPermissions, ProxiesPermissionsBean proxiesPermissions, StaffPermissionsBean staffPermissions)
    {
        this.apiPermissions = apiPermissions;
        this.bukkitPermissions = bukkitPermissions;
        this.bungeeRedisPermissions = bungeeRedisPermissions;
        this.hubPermissions = hubPermissions;
        this.moderationPermissions = moderationPermissions;
        this.proxiesPermissions = proxiesPermissions;
        this.staffPermissions = staffPermissions;
    }

    // Getters
    public APIPermissionsBean getApiPermissions() { return apiPermissions; }
    public BukkitPermissionsBean getBukkitPermissions() { return bukkitPermissions; }
    public BungeeRedisPermissionsBean getBungeeRedisPermissions() { return bungeeRedisPermissions; }
    public HubPermissionsBean getHubPermissions() { return hubPermissions; }
    public ModerationPermissionsBean getModerationPermissions() { return moderationPermissions; }
    public ProxiesPermissionsBean getProxiesPermissions() { return proxiesPermissions; }
    public StaffPermissionsBean getStaffPermissions() { return staffPermissions; }

    // Setters
    public void setApiPermissions(APIPermissionsBean apiPermissions) { this.apiPermissions = apiPermissions; }
    public void setBukkitPermissions(BukkitPermissionsBean bukkitPermissions) { this.bukkitPermissions = bukkitPermissions; }
    public void setBungeeRedisPermisions(BungeeRedisPermissionsBean bungeeRedisPermissions) { this.bungeeRedisPermissions = bungeeRedisPermissions; }
    public void setHubPermissions(HubPermissionsBean hubPermissions) { this.hubPermissions = hubPermissions; }
    public void setModerationPermissions(ModerationPermissionsBean moderationPermissions) { this.moderationPermissions = moderationPermissions; }
    public void setProxiesPermissions(ProxiesPermissionsBean proxiesPermissions) { this.proxiesPermissions = proxiesPermissions; }
    public void setStaffPermissions(StaffPermissionsBean staffPermissions) { this.staffPermissions = staffPermissions; }

    // Reverse the bean to HashMap
    public HashMap<String, Boolean> getHashMap()
    {
        HashMap<String, Boolean> permissionHashMap = new HashMap<>();
        permissionHashMap.putAll(this.apiPermissions.getHashMap());
        permissionHashMap.putAll(this.bukkitPermissions.getHashMap());
        permissionHashMap.putAll(this.bungeeRedisPermissions.getHashMap());
        permissionHashMap.putAll(this.hubPermissions.getHashMap());
        permissionHashMap.putAll(this.moderationPermissions.getHashMap());
        permissionHashMap.putAll(this.proxiesPermissions.getHashMap());
        permissionHashMap.putAll(this.staffPermissions.getHashMap());
        return permissionHashMap;
    }
}
