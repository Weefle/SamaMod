package net.samagames.api.permissions;

import java.util.Map;
import java.util.UUID;

/*
 * This file is part of SamaGamesAPI.
 *
 * SamaGamesAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SamaGamesAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SamaGamesAPI.  If not, see <http://www.gnu.org/licenses/>.
 */
public interface IPermissionsEntity {

    UUID getUUID();

    long getGroupId();

    String getDisplayPrefix();

    String getPrefix();

    String getDisplaySuffix();

    String getSuffix();

    int getDisplayRank();

    int getRank();

    String getDisplayTag();

    String getTag();

    String getDisplayGroupName();

    String getGroupName();

    long getDisplayGroupId();

    int getMultiplier();

    Map<String, Boolean> getPermissions();

    boolean hasPermission(String name);

    void refresh();


}
