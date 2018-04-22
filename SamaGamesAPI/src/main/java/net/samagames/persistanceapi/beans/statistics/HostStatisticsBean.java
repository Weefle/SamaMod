package net.samagames.persistanceapi.beans.statistics;

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
public class HostStatisticsBean
{
    /* Database Structure

    Table : host_stats
    +--------------+--------------+------+-----+---------+----------------+
    | Field        | Type         | Null | Key | Default | Extra          |
    +--------------+--------------+------+-----+---------+----------------+
    | id           | double       | NO   | PRI | NULL    | auto_increment |
    | template_id  | varchar(255) | NO   |     | NULL    |                |
    | host_id      | varchar(255) | NO   |     | NULL    |                |
    | ip_address   | varchar(15)  | NO   | MUL | NULL    |                |
    | player_uuid  | binary(16)   | NO   | MUL | NULL    |                |
    | started_time | timestamp    | NO   | MUL | NULL    |                |
    | played_time  | bigint(20)   | NO   |     | NULL    |                |
    +--------------+--------------+------+-----+---------+----------------+

    */

    // Defines
    private long id;
    private String templateId;
    private String hostId;
    private String ipAddress;
    private UUID playerUuid;
    private long startedTime;
    private long playedTime;

    // Cosntructor
    public HostStatisticsBean(String templateId, String hostId, String ipAddress, UUID playerUuid, long startedTime, long playedTime)
    {
        this.templateId = templateId;
        this.hostId = hostId;
        this.ipAddress = ipAddress;
        this.playerUuid = playerUuid;
        this.startedTime = startedTime;
        this.playedTime = playedTime;
    }

    // Getter
    public long getId() { return id; }
    public String getHostId() { return hostId; }
    public String getIpAddress() { return ipAddress; }
    public UUID getPlayerUuid() { return playerUuid; }
    public long getPlayedTime() { return playedTime; }
    public long getStartedTime() { return startedTime; }
    public String getTemplateId() { return templateId; }

    // Setter
    public void setHostId(String hostId) { this.hostId = hostId; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public void setPlayerUuid(UUID playerUuid) { this.playerUuid = playerUuid; }
    public void setPlayedTime(long playedTime) { this.playedTime = playedTime; }
    public void setStartedTime(long startedTime) { this.startedTime = startedTime; }
    public void setTemplateId(String templateId) { this.templateId = templateId; }
}
