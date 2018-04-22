package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.achievements.AchievementBean;
import net.samagames.persistanceapi.beans.achievements.AchievementCategoryBean;
import net.samagames.persistanceapi.beans.achievements.AchievementProgressBean;
import net.samagames.persistanceapi.beans.players.PlayerBean;
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
public class AchievementManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get the category by ID
    public AchievementCategoryBean getAchievementCategory(int categoryId, DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            AchievementCategoryBean achievementCategory = null;

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select category_name, category_description, item_minecraft_id, parent_id from achievement_categories where category_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, categoryId);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                String categoryName = resultset.getString("category_name");
                String categoryDescription = resultset.getString("category_description");
                String itemMinecraftId = resultset.getString("item_minecraft_id");
                int parentId = resultset.getInt("parent_id");
                achievementCategory = new AchievementCategoryBean(categoryId, categoryName, categoryDescription, itemMinecraftId, parentId);
            }

            return achievementCategory;
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

    // Get the categories
    public List<AchievementCategoryBean> getAchievementCategories(DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            List<AchievementCategoryBean> achievementCategories = new ArrayList<>();

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select category_id, category_name, category_description, item_minecraft_id, parent_id from achievement_categories";

            statement = connection.prepareStatement(sql);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                int categoryId = resultset.getInt("category_id");
                String categoryName = resultset.getString("category_name");
                String categoryDescription = resultset.getString("category_description");
                String itemMinecraftId = resultset.getString("item_minecraft_id");
                int parentId = resultset.getInt("parent_id");
                achievementCategories.add(new AchievementCategoryBean(categoryId, categoryName, categoryDescription, itemMinecraftId, parentId));
            }

            return achievementCategories;
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

    // Get the achievement by ID
    public AchievementBean getAchievement(int achievementId, DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            AchievementBean achievement = null;

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select achievement_name, achievement_description, progress_target, category_id from achievements where achievement_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, achievementId);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if(resultset.next())
            {
                // There's a result
                String achievementName = resultset.getString("achievement_name");
                String achievementDescription = resultset.getString("achievement_description");
                int progressTarget = resultset.getInt("progress_target");
                int categoryId = resultset.getInt("category_id");
                achievement = new AchievementBean(achievementId, achievementName, achievementDescription, progressTarget, categoryId);
            }

            return achievement;
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

    // Get the achievements
    public List<AchievementBean> getAchievements(DataSource dataSource) throws Exception
    {
        try
        {
            // Defines
            List<AchievementBean> achievements = new ArrayList<>();

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select achievement_id, achievement_name, achievement_description, progress_target, category_id from achievements";

            statement = connection.prepareStatement(sql);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                int achievementId = resultset.getInt("achievement_id");
                String achievementName = resultset.getString("achievement_name");
                String achievementDescription = resultset.getString("achievement_description");
                int progressTarget = resultset.getInt("progress_target");
                int categoryId = resultset.getInt("category_id");
                achievements.add(new AchievementBean(achievementId, achievementName, achievementDescription, progressTarget, categoryId));
            }

            return achievements;
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

    // Get achievement progress by UUID and achievement id
    public AchievementProgressBean getAchievementProgress(PlayerBean player, int achievementId, DataSource dataSource) throws Exception
    {
        // Make the research of player by UUID
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select (HEX(uuid_player)) as uuid_player, progress_id, achievement_id, progress, start_date, unlock_date from achievement_progresses where uuid_player = UNHEX(?) and achievement_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setInt(2, achievementId);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            if (resultset.next())
            {
                // There's a result
                String playerUuid = Transcoder.decode(resultset.getString("uuid_player"));
                long progressId = resultset.getLong("progress_id");
                int achievementId2 = resultset.getInt("achievement_id");
                int achievementProgress = resultset.getInt("progress");
                Timestamp startDate = resultset.getTimestamp("start_date");

                Timestamp unlockDate;

                try
                {
                    unlockDate = resultset.getTimestamp("unlock_date");
                }
                catch (Exception dateException)
                {
                    unlockDate = null;
                }

                return new AchievementProgressBean(progressId, achievementId2, achievementProgress, startDate, unlockDate, UUID.fromString(playerUuid));
            }
            else
            {
                // If there no player for the uuid in database create a new player
                this.close();
                AchievementProgressBean achievementProgressBean = new AchievementProgressBean(0, 0, achievementId, null, null, null);
                this.createAchievementProgress(player, achievementProgressBean, dataSource);
                achievementProgressBean = this.getAchievementProgress(player, achievementId, dataSource);
                this.close();
                return achievementProgressBean;
            }
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

    // Get achievement progresses by UUID
    public List<AchievementProgressBean> getAchievementProgresses(PlayerBean player, DataSource dataSource) throws Exception
    {
        // Make the research of player by UUID
        try
        {
            // Defines
            List<AchievementProgressBean> achievementProgresses = new ArrayList<>();

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select (HEX(uuid_player)) as uuid_player, progress_id, achievement_id, progress, start_date, unlock_date from achievement_progresses where uuid_player = UNHEX(?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                String playerUuid = Transcoder.decode(resultset.getString("uuid_player"));
                long progressId = resultset.getLong("progress_id");
                int achievementId = resultset.getInt("achievement_id");
                int achievementProgress = resultset.getInt("progress");
                Timestamp startDate = resultset.getTimestamp("start_date");

                Timestamp unlockDate;

                try
                {
                    unlockDate = resultset.getTimestamp("unlock_date");
                }
                catch (Exception dateException)
                {
                    unlockDate = null;
                }

                achievementProgresses.add(new AchievementProgressBean(progressId, achievementId, achievementProgress, startDate, unlockDate, UUID.fromString(playerUuid)));
            }

            return achievementProgresses;
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

    // Update the achievement progress data
    public void updateAchievementProgress(AchievementProgressBean progress, DataSource dataSource) throws Exception
    {
        // Update the players data
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            Timestamp unlockDate = progress.getUnlockDate();
            String unlockDateString = "0000-00-00 00:00:00";

            if(unlockDate != null)
                unlockDateString = unlockDate.toString();

            // Query construction
            String sql = "update achievement_progresses set progress = ?, start_date = ?, unlock_date = ? where progress_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, progress.getProgress());
            statement.setString(2, progress.getStartDate().toString());
            statement.setString(3, unlockDateString);
            statement.setLong(4, progress.getProgressId());

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
            this.close();
        }
    }

    // Create the achievement progress
    public void createAchievementProgress(PlayerBean player, AchievementProgressBean progress, DataSource dataSource) throws Exception
    {
        // Create the player
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            Timestamp unlockDate = progress.getUnlockDate();
            String unlockDateString = "0000-00-00 00:00:00";

            if(unlockDate != null)
            {
                unlockDateString = unlockDate.toString();
            }

            // Query construction
            String sql = "insert into achievement_progresses (achievement_id, progress, start_date, unlock_date, uuid_player) values (?, ?, now(), ?, UNHEX(?))";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, progress.getAchievementId());
            statement.setInt(2, progress.getProgress());
            statement.setString(3, unlockDateString);
            statement.setString(4, Transcoder.encode(player.getUuid().toString()));

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
            this.close();
        }
    }

    // Close all connection
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
