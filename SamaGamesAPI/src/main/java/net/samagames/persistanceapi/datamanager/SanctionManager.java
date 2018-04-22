package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.players.SanctionBean;
import net.samagames.persistanceapi.utils.Transcoder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
public class SanctionManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Create a sanction
    public void applySanction(int sanctionType, SanctionBean sanction, DataSource dataSource) throws Exception
    {
        // Create the sanction
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            Timestamp expirationTime = sanction.getExpirationTime();
            String expirationDate = "0000-00-00 00:00:00";

            if(expirationTime != null)
                expirationDate = expirationTime.toString();

            // Query construction
            String sql = "insert into sanctions (player_uuid, type_id, reason, punisher_uuid, expiration_date, is_deleted, creation_date, update_date)";
            sql += " values (UNHEX(?), ?, ?, UNHEX(?), ?, 0, now(), now())";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(sanction.getPlayerUuid().toString()));
            statement.setInt(2, sanctionType);
            statement.setString(3, sanction.getReason());
            statement.setString(4, Transcoder.encode(sanction.getPunisherUuid().toString()));
            statement.setString(5, expirationDate);

            // Execute the query
            statement.executeUpdate();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            close();
        }
    }

    // Remove a sanction
    public void removeSanction(int sanctionType, PlayerBean player, DataSource dataSource) throws Exception
    {
        // Remove the last active sanction
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "update sanctions set is_deleted=1, update_date = now() where type_id = ? and player_uuid = UNHEX(?)";
            sql += " and is_deleted = 0 order by creation_date desc limit 1";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, sanctionType);
            statement.setString(2, Transcoder.encode(player.getUuid().toString()));

            // Execute the query
            statement.executeUpdate();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            close();
        }
    }

    // Check if a player is banned
    public SanctionBean getPlayerBanned(PlayerBean player, DataSource dataSource) throws Exception
    {
        // Defines
        SanctionBean sanction;

        // Do the check of ban
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            Timestamp expirationTime;

            // Query construction
            String sql = "";
            sql += "select sanction_id, HEX(player_uuid) as uuid , type_id, reason, HEX(punisher_uuid) as punisher, expiration_date, is_deleted, creation_date, update_date from sanctions";
            sql += " where player_uuid=UNHEX(?) and type_id = ? and (expiration_date > now() or expiration_date= '0000-00-00 00:00:00') and is_deleted = 0";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setInt(2, SanctionBean.BAN);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result
            if (resultset.next())
            {
                // The player is banned
                long sanctionId =  resultset.getLong("sanction_id");
                String banPlayer = Transcoder.decode(resultset.getString("uuid"));
                UUID playerUuid = UUID.fromString(banPlayer);
                int typeId = resultset.getInt("type_id");
                String reason = resultset.getString("reason");
                String punisher = Transcoder.decode(resultset.getString("punisher"));
                UUID punisherUuid = UUID.fromString(punisher);

                try
                {
                    expirationTime = resultset.getTimestamp("expiration_date");
                }
                catch (Exception dateException)
                {
                    expirationTime = null;
                }

                boolean isDeleted = resultset.getBoolean("is_deleted");
                Timestamp creationDate = resultset.getTimestamp("creation_date");
                Timestamp updateDate = resultset.getTimestamp("update_date");
                sanction = new SanctionBean(sanctionId, playerUuid, typeId, reason, punisherUuid, expirationTime, isDeleted, creationDate, updateDate);
                return sanction;
            }
            else
            {
                // The player is not banned
                return null;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            close();
        }
    }

    // Check if a player is muted
    public SanctionBean getPlayerMuted(PlayerBean player, DataSource dataSource) throws Exception
    {
        // Defines
        SanctionBean sanction;

        // Do the check of mute
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            Timestamp expirationTime;

            // Query construction
            String sql = "";
            sql += "select sanction_id, HEX(player_uuid) as uuid , type_id, reason, HEX(punisher_uuid) as punisher, expiration_date, is_deleted, creation_date, update_date from sanctions";
            sql += " where player_uuid = UNHEX(?) and type_id = ? and expiration_date > now() and is_deleted = 0";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setInt(2, SanctionBean.MUTE);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result
            if (resultset.next())
            {
                // The player is muted
                long sanctionId = resultset.getLong("sanction_id");
                String mutePlayer = Transcoder.decode(resultset.getString("uuid"));
                UUID playerUuid = UUID.fromString(mutePlayer);
                int typeId = resultset.getInt("type_id");
                String reason = resultset.getString("reason");
                String punisher = Transcoder.decode(resultset.getString("punisher"));
                UUID punisherUuid = UUID.fromString(punisher);

                try
                {
                    expirationTime = resultset.getTimestamp("expiration_date");
                }
                catch (Exception dateException)
                {
                    expirationTime = null;
                }

                boolean isDeleted = resultset.getBoolean("is_deleted");
                Timestamp creationDate = resultset.getTimestamp("creation_date");
                Timestamp updateDate = resultset.getTimestamp("update_date");
                sanction = new SanctionBean(sanctionId, playerUuid, typeId, reason, punisherUuid, expirationTime, isDeleted, creationDate, updateDate);
                return sanction;
            }
            else
            {
                // The player is not banned
                return null;
            }
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            close();
        }
    }

    // Get all actives sanctions by type
    public List<SanctionBean> getAllSanctions(UUID uuid, int sanctionType, DataSource dataSource) throws Exception
    {
        // Get all sanctions
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<SanctionBean> sanctionList = new ArrayList<>();
            Timestamp expirationTime;

            // Query construction
            String sql = "select sanction_id, HEX(player_uuid) as player_uuid, type_id, reason, HEX(punisher_uuid) as punisher_uuid, expiration_date, is_deleted, creation_date, update_date from sanctions";
            sql += " where player_uuid=UNHEX(?) and type_id = ? order by creation_date desc";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(uuid.toString()));
            statement.setInt(2, sanctionType);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                long sanctionId = resultset.getLong("sanction_id");
                String playerUuid = Transcoder.decode(resultset.getString("player_uuid"));
                int typeId = resultset.getInt("type_id");
                String reason = resultset.getString("reason");
                String punisherUUID = Transcoder.decode(resultset.getString("punisher_uuid"));

                try
                {
                    expirationTime = resultset.getTimestamp("expiration_date");
                }
                catch (Exception dateException)
                {
                    expirationTime = null;
                }

                boolean isDeleted = resultset.getBoolean("is_deleted");
                Timestamp creationDate = resultset.getTimestamp("creation_date");
                Timestamp updateDate = resultset.getTimestamp("update_date");
                SanctionBean sanction = new SanctionBean(sanctionId, UUID.fromString(playerUuid), typeId, reason, UUID.fromString(punisherUUID), expirationTime, isDeleted, creationDate, updateDate);
                sanctionList.add(sanction);
            }
            return sanctionList;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            this.close();
        }
    }

    // Get all non actives sanctions by type
    public List<SanctionBean> getAllActiveSanctions(UUID uuid, int sanctionType, DataSource dataSource) throws Exception
    {
        // Get all sanctions
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<SanctionBean> sanctionList = new ArrayList<>();
            Timestamp expirationTime;

            // Query construction
            String sql = "select sanction_id, HEX(player_uuid) as player_uuid, type_id, reason, HEX(punisher_uuid) as punisher_uuid, expiration_date, is_deleted, creation_date, update_date from sanctions";
            sql += " where player_uuid = UNHEX(?) and type_id = ? and is_deleted = 0 order by creation_date desc";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(uuid.toString()));
            statement.setInt(2, sanctionType);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                long sanctionId = resultset.getLong("sanction_id");
                String playerUuid = Transcoder.decode(resultset.getString("player_uuid"));
                int typeId = resultset.getInt("type_id");
                String reason = resultset.getString("reason");
                String punisherUUID = Transcoder.decode(resultset.getString("punisher_uuid"));

                try
                {
                    expirationTime = resultset.getTimestamp("expiration_date");
                }
                catch (Exception dateException)
                {
                    expirationTime = null;
                }

                boolean isDeleted = resultset.getBoolean("is_deleted");
                Timestamp creationDate = resultset.getTimestamp("creation_date");
                Timestamp updateDate = resultset.getTimestamp("update_date");
                SanctionBean sanction = new SanctionBean(sanctionId, UUID.fromString(playerUuid), typeId, reason, UUID.fromString(punisherUUID), expirationTime, isDeleted, creationDate, updateDate);
                sanctionList.add(sanction);
            }
            return sanctionList;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            this.close();
        }
    }

    // Get all sanctions by type
    // Get all non actives sanctions by type
    public List<SanctionBean> getAllPassiveSanctions(UUID uuid, int sanctionType, DataSource dataSource) throws Exception
    {
        // Get all sanctions
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<SanctionBean> sanctionList = new ArrayList<>();
            Timestamp expirationTime;

            // Query construction
            String sql = "select sanction_id, (HEX(player_uuid)) as player_uuid, type_id, reason, (HEX(punisher_uuid)) as punisher_uuid, expiration_date, is_deleted, creation_date, update_date from sanctions";
            sql += " where player_uuid = UNHEX(?) and type_id = ? and is_deleted = 1 order by creation_date desc";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(uuid.toString()));
            statement.setInt(2, sanctionType);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                long sanctionId = resultset.getLong("sanction_id");
                String playerUuid = Transcoder.decode(resultset.getString("player_uuid"));
                int typeId = resultset.getInt("type_id");
                String reason = resultset.getString("reason");
                String punisherUUID = Transcoder.decode(resultset.getString("punisher_uuid"));

                try
                {
                    expirationTime = resultset.getTimestamp("expiration_date");
                }
                catch (Exception dateException)
                {
                    expirationTime = null;
                }

                boolean isDeleted = resultset.getBoolean("is_deleted");
                Timestamp creationDate = resultset.getTimestamp("creation_date");
                Timestamp updateDate = resultset.getTimestamp("update_date");
                SanctionBean sanction = new SanctionBean(sanctionId, UUID.fromString(playerUuid), typeId, reason, UUID.fromString(punisherUUID), expirationTime, isDeleted, creationDate, updateDate);
                sanctionList.add(sanction);
            }
            return sanctionList;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            this.close();
        }
    }

    // Update a sanction status
    public void updateSanctionStatus(long sanctionId, boolean status, DataSource dataSource) throws Exception
    {
        // Update the sanction status
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "update sanctions set is_deleted = ?, update_date = now() where sanction_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setBoolean(1, status);
            statement.setLong(2, sanctionId);

            // Execute the query
            statement.executeUpdate();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            close();
        }
    }

    // Get sanctions by UUID
    public List<SanctionBean> getAllModeratorSanctions(UUID uuid, DataSource dataSource) throws Exception
    {
        // Get all sanctions
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<SanctionBean> sanctionList = new ArrayList<>();
            Timestamp expirationTime;

            // Query construction
            String sql = "select sanction_id, HEX(player_uuid) as player_uuid, type_id, reason, HEX(punisher_uuid) as punisher_uuid, expiration_date, is_deleted, creation_date, update_date from sanctions";
            sql += " where punisher_uuid = UNHEX(?) order by creation_date desc";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(uuid.toString()));

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                long sanctionId = resultset.getLong("sanction_id");
                String playerUuid = Transcoder.decode(resultset.getString("player_uuid"));
                int typeId = resultset.getInt("type_id");
                String reason = resultset.getString("reason");
                String punisherUUID = Transcoder.decode(resultset.getString("punisher_uuid"));

                try
                {
                    expirationTime = resultset.getTimestamp("expiration_date");
                }
                catch (Exception dateException)
                {
                    expirationTime = null;
                }

                boolean isDeleted = resultset.getBoolean("is_deleted");
                Timestamp creationDate = resultset.getTimestamp("creation_date");
                Timestamp updateDate = resultset.getTimestamp("update_date");
                SanctionBean sanction = new SanctionBean(sanctionId, UUID.fromString(playerUuid), typeId, reason, UUID.fromString(punisherUUID), expirationTime, isDeleted, creationDate, updateDate);
                sanctionList.add(sanction);
            }
            return sanctionList;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
        finally
        {
            // Close the query environment in order to prevent leaks
            this.close();
        }
    }

    // Close the connection
    public void close() throws Exception
    {
        // Close the query environment in order to prevent leaks
        try
        {
            if (resultset != null)
            {
                // Close the resulset
                resultset.close();
            }
            if (statement != null)
            {
                // Close the statement
                statement.close();
            }
            if (connection != null)
            {
                // Close the connection
                connection.close();
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }
    }
}
