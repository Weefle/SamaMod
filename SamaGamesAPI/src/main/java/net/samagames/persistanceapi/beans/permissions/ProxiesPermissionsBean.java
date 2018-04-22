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
public class ProxiesPermissionsBean
{
    /* Database Structure

    Table : proxies_permissions
    +--------------------+------------+------+-----+---------+-------+
    | Field              | Type       | Null | Key | Default | Extra |
    +--------------------+------------+------+-----+---------+-------+
    | groups_id          | tinyint(4) | NO   | PRI | NULL    |       |
    | proxies_dispatch   | bit(1)     | NO   |     | NULL    |       |
    | proxies_global     | bit(1)     | NO   |     | NULL    |       |
    | proxies_debug      | bit(1)     | NO   |     | NULL    |       |
    | proxies_set_option | bit(1)     | NO   |     | NULL    |       |
    | proxies_hydro      | bit(1)     | NO   |     | NULL    |       |
    +--------------------+------------+------+-----+---------+-------+
    */

    // Defines
    private long groupsId;
    @Perm("proxies.dispatch")
    private boolean proxiesDispatch;
    @Perm("proxies.global")
    private boolean proxiesGlobal;
    @Perm("proxies.debug")
    private boolean proxiesDebug;
    @Perm("proxies.setoption")
    private boolean proxiesSetOption;
    @Perm("proxies.hydro")
    private boolean proxiesHydro;

    // Constructor
    public ProxiesPermissionsBean(long groupsId, boolean proxiesDispatch, boolean proxiesGlobal,
                                  boolean proxiesDebug, boolean proxiesSetOption, boolean proxiesHydro)
    {
        this.groupsId = groupsId;
        this.proxiesDispatch = proxiesDispatch;
        this.proxiesGlobal = proxiesGlobal;
        this.proxiesDebug = proxiesDebug;
        this.proxiesSetOption = proxiesSetOption;
        this.proxiesHydro = proxiesHydro;
    }

    // Getters
    public long getGroupsId() { return this.groupsId; }
    public boolean isProxiesDispatch() { return this.proxiesDispatch; }
    public boolean isProxiesGlobal() { return this.proxiesGlobal; }
    public boolean isProxiesDebug() { return this.proxiesDebug; }
    public boolean isProxiesSetOption() { return this.proxiesSetOption; }
    public boolean isProxiesHydro() {
        return proxiesHydro;
    }

    // Setters
    public void setProxiesDispatch(boolean proxiesDispatch) { this.proxiesDispatch = proxiesDispatch; }
    public void setProxiesGlobal(boolean proxiesGlobal) { this.proxiesGlobal = proxiesGlobal; }
    public void setProxiesDebug(boolean proxiesDebug) { this.proxiesDebug = proxiesDebug; }
    public void setProxiesSetOption(boolean proxiesSetOption) { this.proxiesSetOption = proxiesSetOption; }
    public void setProxiesHydro(boolean proxiesHydro) {
        this.proxiesHydro = proxiesHydro;
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
