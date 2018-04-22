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
public class BukkitPermissionsBean
{
    /* Database Structure

    Table : bukkit_permissions
    +-------------------------+------------+------+-----+---------+----------------+
    | Field                   | Type       | Null | Key | Default | Extra          |
    +-------------------------+------------+------+-----+---------+----------------+
    | groups_id               | tinyint(4) | NO   | PRI | NULL    | auto_increment |
    | minecraft_command_op    | bit(1)     | NO   |     | NULL    |                |
    | bukkit_command_op_give  | bit(1)     | NO   |     | NULL    |                |
    | bukkit_command_effect   | bit(1)     | NO   |     | NULL    |                |
    | bukkit_command_gamemode | bit(1)     | NO   |     | NULL    |                |
    | bukkit_command_teleport | bit(1)     | NO   |     | NULL    |                |
    +-------------------------+------------+------+-----+---------+----------------+
    */

    // Defines
    private long groupsId;
    @Perm("minecraft.command.op")
    private boolean minecraftCommandOp;
    @Perm("bukkit.command.op.give")
    private boolean bukkitCommandOpGive;
    @Perm("minecraft.command.effect")
    private boolean bukkitCommandEffect;
    @Perm("minecraft.command.gamemode")
    private boolean bukkitCommandGamemode;
    @Perm("minecraft.command.tp")
    private boolean bukkitCommandTeleport;

    // Constructor
    public BukkitPermissionsBean(long groupsId, boolean minecraftCommandOp, boolean bukkitCommandOpGive,
                                 boolean bukkitCommandEffect, boolean bukkitCommandGamemode,
                                 boolean bukkitCommandTeleport)
    {
        this.groupsId = groupsId;
        this.minecraftCommandOp = minecraftCommandOp;
        this.bukkitCommandOpGive = bukkitCommandOpGive;
        this.bukkitCommandEffect = bukkitCommandEffect;
        this.bukkitCommandGamemode = bukkitCommandGamemode;
        this.bukkitCommandTeleport = bukkitCommandTeleport;
    }

    // Getters
    public long getGroupsId() { return groupsId; }
    public boolean isMinecraftCommandOp() { return this.minecraftCommandOp; }
    public boolean isBukkitCommandOpGive() { return this.bukkitCommandOpGive; }
    public boolean isBukkitCommandEffect() {
        return bukkitCommandEffect;
    }
    public boolean isBukkitCommandGamemode() {
        return bukkitCommandGamemode;
    }
    public boolean isBukkitCommandTeleport() {
        return bukkitCommandTeleport;
    }


    // Setters
    public void setMinecraftCommandOp(boolean minecraftCommandOp) { this.minecraftCommandOp = minecraftCommandOp; }
    public void setBukkitCommandOpGive(boolean bukkitCommandOpGive) { this.bukkitCommandOpGive = bukkitCommandOpGive; }
    public void setBukkitCommandEffect(boolean bukkitCommandEffect) {
        this.bukkitCommandEffect = bukkitCommandEffect;
    }
    public void setBukkitCommandGamemode(boolean bukkitCommandGamemode) {
        this.bukkitCommandGamemode = bukkitCommandGamemode;
    }
    public void setBukkitCommandTeleport(boolean bukkitCommandTeleport) {
        this.bukkitCommandTeleport = bukkitCommandTeleport;
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
