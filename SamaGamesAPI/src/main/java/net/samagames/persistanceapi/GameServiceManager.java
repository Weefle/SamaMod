package net.samagames.persistanceapi;

import net.samagames.persistanceapi.beans.achievements.AchievementBean;
import net.samagames.persistanceapi.beans.achievements.AchievementCategoryBean;
import net.samagames.persistanceapi.beans.achievements.AchievementProgressBean;
import net.samagames.persistanceapi.beans.events.EventBean;
import net.samagames.persistanceapi.beans.events.EventWinnerBean;
import net.samagames.persistanceapi.beans.messages.ScheduledMessageBean;
import net.samagames.persistanceapi.beans.permissions.*;
import net.samagames.persistanceapi.beans.players.*;
import net.samagames.persistanceapi.beans.shop.ItemDescriptionBean;
import net.samagames.persistanceapi.beans.shop.PromotionsBean;
import net.samagames.persistanceapi.beans.shop.TransactionBean;
import net.samagames.persistanceapi.beans.statistics.PlayerStatisticsBean;
import net.samagames.persistanceapi.beans.statistics.*;
import net.samagames.persistanceapi.beans.utils.BungeeConfigBean;
import net.samagames.persistanceapi.datamanager.*;

import java.sql.Timestamp;
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
public class GameServiceManager
{
    // Defines attributes
    private DatabaseManager databaseManager;
    private PlayerManager playerManager;
    private SanctionManager sanctionManager;
    private StatisticsManager statisticsManager;
    private ConfigurationManager configurationManager;
    private DenunciationManager denunciationManager;
    private PermissionsManager permissionsManager;
    private GroupsManager groupsManager;
    private FriendshipManager friendshipManager;
    private PromotionsManager promotionsManager;
    private PlayerSettingsManager playerSettingsManager;
    private TransactionManager transactionManager;
    private ItemManager itemManager;
    private NicknameManager nicknameManager;
    private HostManager hostManager;
    private AchievementManager achievementManager;
    private EventManager eventManager;
    private MessageManager messageManager;

    // Super constructor
    public GameServiceManager(String url, String name, String password, int minPoolSize, int maxPoolSize)
    {
        // Singleton of DatabaseManager
        this.databaseManager = DatabaseManager.getInstance(url, name, password, minPoolSize, maxPoolSize);
        this.playerManager = new PlayerManager();
        this.sanctionManager = new SanctionManager();
        this.statisticsManager = new StatisticsManager();
        this.configurationManager = new ConfigurationManager();
        this.denunciationManager = new DenunciationManager();
        this.permissionsManager = new PermissionsManager();
        this.groupsManager = new GroupsManager();
        this.friendshipManager = new FriendshipManager();
        this.promotionsManager = new PromotionsManager();
        this.playerSettingsManager = new PlayerSettingsManager();
        this.transactionManager = new TransactionManager();
        this.itemManager = new ItemManager();
        this.nicknameManager = new NicknameManager();
        this.hostManager = new HostManager();
        this.achievementManager = new AchievementManager();
        this.eventManager = new EventManager();
        this.messageManager = new MessageManager();
    }


    /*============================================
      Part of denunciation manager
    ============================================*/

    // Make a denunciation
    public synchronized  void denouncePlayer(PlayerBean player, DenunciationBean denunciation) throws Exception
    {
        this.denunciationManager.denouncePlayer(player, denunciation, this.databaseManager.getDataSource());
    }



    /*============================================
      Part of config manager
    ============================================*/

    // Get the bungee config
    public synchronized BungeeConfigBean getBungeeConfig()throws Exception
    {
        return this.configurationManager.getConfig(this.databaseManager.getDataSource());
    }

    // Update the bungee config
    public synchronized void updateBungeeConfig(BungeeConfigBean config)throws Exception
    {
        this.configurationManager.updateConfig(config, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of player manager
    ============================================*/

    // Get the player by UUID
    public synchronized PlayerBean getPlayer(UUID uuid, PlayerBean player)throws Exception
    {
       // Get the PlayerBean
        return this.playerManager.getPlayer(uuid, player, this.databaseManager.getDataSource());
    }

    // Update the player
    public synchronized void updatePlayer(PlayerBean player)throws Exception
    {
        // Update datas of player
        this.playerManager.updatePlayer(player, this.databaseManager.getDataSource());
    }

    // Create the player
    public synchronized void createPlayer(PlayerBean player)throws Exception
    {
        // Create the player
        this.playerManager.createPlayer(player, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of sanction manager
    ============================================*/

    // Apply a sanction to a player
    public synchronized void applySanction(int sanctionType, SanctionBean sanction)throws Exception
    {
        // Do the sanction
        this.sanctionManager.applySanction(sanctionType, sanction, this.databaseManager.getDataSource());
    }

    public synchronized void removeSanction(int sanctionType, PlayerBean player)throws Exception
    {
        // Remove the sanction
        this.sanctionManager.removeSanction(sanctionType, player, this.databaseManager.getDataSource());
    }

    // Check if a player is banned
    public synchronized SanctionBean getPlayerBanned(PlayerBean player)throws Exception
    {
        // Check the ban status
        return this.sanctionManager.getPlayerBanned(player, this.databaseManager.getDataSource());
    }

    // Check if a player is muted
    public synchronized SanctionBean getPlayerMuted(PlayerBean player)throws Exception
    {
        // Check the mute status
        return this.sanctionManager.getPlayerMuted(player, this.databaseManager.getDataSource());
    }

    // Get all sanctions for a player and type
    public synchronized List<SanctionBean> getAllSanctions(UUID uuid, int sanctionType) throws Exception
    {
        // Get sanctions
        return this.sanctionManager.getAllSanctions(uuid, sanctionType, this.databaseManager.getDataSource());
    }

    // Get all actives sanctions for a player and type
    public synchronized List<SanctionBean> getAllActiveSanctions(UUID uuid, int sanctionType) throws Exception
    {
        // Get sanctions
        return this.sanctionManager.getAllActiveSanctions(uuid, sanctionType, this.databaseManager.getDataSource());
    }

    // Get all passives sanctions for a player and type
    public synchronized List<SanctionBean> getAllPassiveSanctions(UUID uuid, int sanctionType) throws Exception
    {
        // Get sanctions
        return this.sanctionManager.getAllPassiveSanctions(uuid, sanctionType, this.databaseManager.getDataSource());
    }

    // Get all sanctions by a moderator
    public synchronized List<SanctionBean> getAllModeratorSanctions(UUID uuid) throws Exception
    {
        // Get sanctions
        return this.sanctionManager.getAllModeratorSanctions(uuid, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of statistics manager
    ============================================*/

    // Get Dimensions player statistics
    public synchronized DimensionsStatisticsBean getDimensionsStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.dimensionsStatsManager.getDimensionsStatistics(player, this.databaseManager.getDataSource());
    }

    // Get JukeBox player statistics
    public synchronized JukeBoxStatisticsBean getJukeBoxStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.jukeBoxStatsManager.getJukeBoxStatistics(player, this.databaseManager.getDataSource());
    }

    // Get Network player statistics
    public synchronized NetworkStatisticsBean getNetworkStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.networkStatisticsManager.getNetworkStatistics(player, this.databaseManager.getDataSource());
    }

    // Get Quake player statistics
    public synchronized QuakeStatisticsBean getQuakeStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.quakeStatsManager.getQuakeStatistics(player, this.databaseManager.getDataSource());
    }

    // Get uhcoriginal player statistics
    public synchronized UHCOriginalStatisticsBean getUHCOriginalStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.uhcOriginalStatsManager.getUHCOriginalStatistics(player, this.databaseManager.getDataSource());
    }

    // Get uhcrun player statistics
    public synchronized UHCRunStatisticsBean getUHCRunStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.uhcRunStatsManager.getUHCRunStatistics(player, this.databaseManager.getDataSource());
    }

    // Get doublerunner player statistics
    public synchronized DoubleRunnerStatisticsBean getDoubleRunnerStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.doubleRunnerStatsManager.getDoubleRunnerStatistics(player, this.databaseManager.getDataSource());
    }

    // Get uhcrandom player statistics
    public synchronized UHCRandomStatisticsBean getUHCRandomStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.uhcRandomStatsManager.getUHCRandomStatistics(player, this.databaseManager.getDataSource());
    }

    // Get randomrun player statistics
    public synchronized RandomRunStatisticsBean getRandomRunStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.randomRunStatsManager.getRandomRunStatistics(player, this.databaseManager.getDataSource());
    }

    // Get ultraflagkeeper player statistics
    public synchronized UltraFlagKeeperStatisticsBean getUltraFlagKeeperStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.ultraFlagKeeperStatsManager.getUltraFlagKeeperStatistics(player, this.databaseManager.getDataSource());
    }

    // Get uppervoid player statistics
    public synchronized UppervoidStatisticsBean getUppervoidStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.uppervoidStatsManager.getUppervoidStatistics(player, this.databaseManager.getDataSource());
    }

    // Get chunkwars player statistics
    public synchronized ChunkWarsStatisticsBean getChunkWarsStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.chunkWarsStatisticsManager.getChunkWarsStatistics(player, this.databaseManager.getDataSource());
    }

    // Get the dropper player statistics
    public synchronized TheDropperStatisticsBean getTheDropperStatistics(PlayerBean player) throws Exception
    {
        // Get the statistics
        return this.statisticsManager.theDropperStatisticsManager.getTheDropperStatistics(player, this.databaseManager.getDataSource());
    }

    // Get all player statistics
    public synchronized PlayerStatisticsBean getAllStatistics(PlayerBean player) throws Exception
    {
        // Get all the statistics
        return this.statisticsManager.getAllPlayerStatistics(player, this.databaseManager.getDataSource());
    }

    // Update all player statistics
    public synchronized void updateAllStatistics(PlayerBean player, PlayerStatisticsBean stats) throws Exception
    {
        // Update statistics
        this.statisticsManager.updateAllPlayerStatistics(player, stats, this.databaseManager.getDataSource());
    }

    // Update Dimensions statistics
    public synchronized void updateDimensionsStatistics(PlayerBean player, DimensionsStatisticsBean dimensionsStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.dimensionsStatsManager.updateDimensionsStatistics(player, dimensionsStats, this.databaseManager.getDataSource());
    }

    // Update JukeBox statistics
    public synchronized void updateJukeBoxStatistics(PlayerBean player, JukeBoxStatisticsBean jukeBoxStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.jukeBoxStatsManager.updateJukeBoxStatistics(player, jukeBoxStats, this.databaseManager.getDataSource());
    }

    // Update network statistics
    public synchronized void updateNetworkStatistics(PlayerBean player, NetworkStatisticsBean networkStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.networkStatisticsManager.updateNetworkStatistics(player, networkStats, this.databaseManager.getDataSource());
    }

    // Update Quake statistics
    public synchronized void updateQuakeStatistics(PlayerBean player, QuakeStatisticsBean quakeStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.quakeStatsManager.updateQuakeStatistics(player, quakeStats, this.databaseManager.getDataSource());
    }

    // Update uhcoriginal statistics
    public synchronized void updateUHCOriginalStatistics(PlayerBean player, UHCOriginalStatisticsBean uhcStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.uhcOriginalStatsManager.updateUHCOriginalStatistics(player, uhcStats, this.databaseManager.getDataSource());
    }

    // Update uhcrun statistics
    public synchronized void updateUHCRunStatistics(PlayerBean player, UHCRunStatisticsBean uhcRunStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.uhcRunStatsManager.updateUHCRunStatistics(player, uhcRunStats, this.databaseManager.getDataSource());
    }

    // Update doublerunner statistics
    public synchronized void updateDoubleRunnerStatistics(PlayerBean player, DoubleRunnerStatisticsBean doubleRunnerStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.doubleRunnerStatsManager.updateDoubleRunnerStatistics(player, doubleRunnerStats, this.databaseManager.getDataSource());
    }

    // Update uhcrandom statistics
    public synchronized void updateUHCRandomStatistics(PlayerBean player, UHCRandomStatisticsBean uhcRandomStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.uhcRandomStatsManager.updateUHCRandomStatistics(player, uhcRandomStats, this.databaseManager.getDataSource());
    }

    // Update randomrun statistics
    public synchronized void updateRandomRunStatistics(PlayerBean player, RandomRunStatisticsBean randomRunStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.randomRunStatsManager.updateRandomRunStatistics(player, randomRunStats, this.databaseManager.getDataSource());
    }

    // Update ultraflagkeeper statistics
    public synchronized void updateUltraFlagKeeperStatistics(PlayerBean player, UltraFlagKeeperStatisticsBean ultraFlagKeeperStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.ultraFlagKeeperStatsManager.updateUltraFlagKeeperStatistics(player, ultraFlagKeeperStats, this.databaseManager.getDataSource());
    }

    // Update uppervoid statistics
    public synchronized void updateUppervoidStatistics(PlayerBean player, UppervoidStatisticsBean uppervoidStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.uppervoidStatsManager.updateUppervoidStatistics(player, uppervoidStats, this.databaseManager.getDataSource());
    }

    // Update chunkwars statistics
    public synchronized void updateChunkWarsStatistics(PlayerBean player, ChunkWarsStatisticsBean chunkWarsStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.chunkWarsStatisticsManager.updateChunkWarsStatistics(player, chunkWarsStats, this.databaseManager.getDataSource());
    }

    // Update the dropper statistics
    public synchronized void updateTheDropperStatistics(PlayerBean player, TheDropperStatisticsBean theDropperStats) throws Exception
    {
        // Update statistics
        this.statisticsManager.theDropperStatisticsManager.updateTheDropperStatistics(player, theDropperStats, this.databaseManager.getDataSource());
    }

    // Get the dimensions leaderboard
    public synchronized List<LeaderboardBean> getDimensionsLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.dimensionsStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the jukebox leaderboard
    public synchronized List<LeaderboardBean> getJukeBoxLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.jukeBoxStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the quake leaderboard
    public synchronized List<LeaderboardBean> getQuakeLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.quakeStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the uhcoriginal leaderboard
    public synchronized List<LeaderboardBean> getUHCOriginalLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.uhcOriginalStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the uhcrun leaderboard
    public synchronized List<LeaderboardBean> getUHCRunLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.uhcRunStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the doublerunner leaderboard
    public synchronized List<LeaderboardBean> getDoubleRunnerLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.doubleRunnerStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the uhcrandom leaderboard
    public synchronized List<LeaderboardBean> getUHCRandomLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.uhcRandomStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the randomrun leaderboard
    public synchronized List<LeaderboardBean> getRandomRunLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.randomRunStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the ultraflagkeeper leaderboard
    public synchronized List<LeaderboardBean> getUltraFlagKeeperLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.ultraFlagKeeperStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the uppervoid leaderboard
    public synchronized List<LeaderboardBean> getUppervoidLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.uppervoidStatsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the chunkwars leaderboard
    public synchronized List<LeaderboardBean> getChunkWarsLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.chunkWarsStatisticsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }

    // Get the dropper leaderboard
    public synchronized List<LeaderboardBean> getTheDropperLeaderBoard(String category) throws Exception
    {
        // Get the leaderboard
        return this.statisticsManager.theDropperStatisticsManager.getLeaderBoard(category, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of permissions manager
    ============================================*/

    // Get API permissions
    public synchronized APIPermissionsBean getAPIPermissions(PlayerBean player)throws Exception
    {
        // Get the permissions
        return this.permissionsManager.apiPermissionsManager.getAPIPermissions(player, this.databaseManager.getDataSource());
    }

    // Get Bukkit permissions
    public synchronized BukkitPermissionsBean getBukkitPermissions(PlayerBean player)throws Exception
    {
        // Get the permissions
        return this.permissionsManager.bukkitPermissionsManager.getBukkitPermissions(player, this.databaseManager.getDataSource());
    }

    // Get Bungee & Redis permissions
    public synchronized BungeeRedisPermissionsBean getBungeeRedisPermissions(PlayerBean player)throws Exception
    {
        // Get the permissions
        return this.permissionsManager.bungeeRedisPermissionsManager.getBungeeRedisPermissions(player, this.databaseManager.getDataSource());
    }

    // Get Hub permissions
    public synchronized HubPermissionsBean getHubPermissions(PlayerBean player)throws Exception
    {
        // Get the permissions
        return this.permissionsManager.hubPermissionsManager.getHubPermissions(player, this.databaseManager.getDataSource());
    }

    // Get Moderation permissions
    public synchronized ModerationPermissionsBean getModerationPermissions(PlayerBean player)throws Exception
    {
        // Get the permissions
        return this.permissionsManager.moderationPermissionsManager.getModerationPermissions(player, this.databaseManager.getDataSource());
    }

    // Get Proxies permissions
    public synchronized ProxiesPermissionsBean getProxiesPermissions(PlayerBean player) throws Exception
    {
        // Get the permissions
        return this.permissionsManager.proxiesPermissionsManager.getProxiesPermissions(player, this.databaseManager.getDataSource());
    }

    // Get Staff permissions
    public synchronized StaffPermissionsBean getStaffPermissions(PlayerBean player) throws Exception
    {
        // Get the permissions
        return this.permissionsManager.staffPermissionsManager.getStaffPermissions(player, this.databaseManager.getDataSource());
    }

    // Get all player permissions
    public synchronized PlayerPermissionsBean getAllPlayerPermissions(PlayerBean player) throws Exception
    {
        // Get all the statistics
        return this.permissionsManager.getAllPlayerPermissions(player, this.databaseManager.getDataSource());
    }

    /*============================================
      Part of groups manager
    ============================================*/

    public synchronized GroupsBean getPlayerGroup(PlayerBean player)throws Exception
    {
        // Get the groups of a player
        return this.groupsManager.getPlayerGroup(player, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of groups manager
    ============================================*/

    // Post a friendship demand
    public synchronized void postFriendshipDemand(FriendshipBean friendship)throws Exception
    {
        // Post the friendship demand
        this.friendshipManager.postFriendshipDemand(friendship, this.databaseManager.getDataSource());
    }

    // Accept a friendship demand
    public synchronized void acceptFriendshipDemand(FriendshipBean friendship)throws Exception
    {
        // Accept the demand
        this.friendshipManager.acceptFriendshipDemand(friendship, this.databaseManager.getDataSource());
    }

    // Refuse a friendship demand
    public synchronized void refuseFriendshipDemand(FriendshipBean friendship)throws Exception
    {
        // Refuse the demand
        this.friendshipManager.refuseFriendshipDemand(friendship, this.databaseManager.getDataSource());
    }

    // Get the list of friendship demand
    public synchronized List<FriendshipBean> getFriendshipDemandList(PlayerBean player)throws Exception
    {
        // Get the list
        return this.friendshipManager.getFriendshipDemandList(player, this.databaseManager.getDataSource());
    }

    // Get the list of friendship
    public synchronized List<FriendshipBean> getFriendshipList(PlayerBean player)throws Exception
    {
        // Get the list
        return this.friendshipManager.getFriendshipList(player, this.databaseManager.getDataSource());
    }

    // Get the list of friendship with both requester/recipient
    public synchronized FriendshipBean getFriendshipNamedList(PlayerBean requester, PlayerBean recipient) throws Exception
    {
        // Get the list
        return this.friendshipManager.getFriendshipNamedList(requester, recipient, this.databaseManager.getDataSource());
    }

    /*============================================
      Part of promotions manager
    ============================================*/

    // Get all active promotions
    public synchronized List<PromotionsBean> getAllActivePromotions()throws Exception
    {
        // Get promotions list
        return this.promotionsManager.getAllActivePromotions(this.databaseManager.getDataSource());
    }

    // Get specific promotions
    public synchronized List<PromotionsBean> getPromotion(int typePromotion, int typeGame)throws Exception
    {
        // Get promotions
        return this.promotionsManager.getPromotion(this.databaseManager.getDataSource(), typePromotion, typeGame);
    }

    // Create promotion
    public synchronized void createPromotion(PromotionsBean promotionsBean)throws Exception
    {
        // Create promotion
        this.promotionsManager.createPromotion(promotionsBean, this.databaseManager.getDataSource());
    }

    // Delete promotion
    public synchronized void deletePromotion(PromotionsBean promotionsBean)throws Exception
    {
        // Delete promotion
        this.promotionsManager.deletePromotion(promotionsBean, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of player settings manager
    ============================================*/

    // Get the player settings
    public synchronized PlayerSettingsBean getPlayerSettings(PlayerBean player)throws Exception
    {
        // Get settings
        return this.playerSettingsManager.getPlayerSettings(player, this.databaseManager.getDataSource());
    }

    // Set the player settings
    public synchronized void setPlayerSettings(PlayerBean player, PlayerSettingsBean settingsBeans)throws Exception
    {
        // Set settings
        this.playerSettingsManager.setPlayerSettings(player, settingsBeans, this.databaseManager.getDataSource());
    }

    // Create default settings
    public synchronized void createDefaultPlayerSettings(PlayerBean player)throws Exception
    {
        // Create settings
        this.playerSettingsManager.createDefaultPlayerSettings(player, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of transaction manager
    ============================================*/

    // Get all the player transactions
    public synchronized List<TransactionBean> getPlayerTransactions(PlayerBean player) throws Exception
    {
        // Get transactions
        return this.transactionManager.getPlayerTransactions(player, this.databaseManager.getDataSource());
    }

    // Get all the player transactions with selected items
    public synchronized List<TransactionBean> getPlayerSelectedTransactions(PlayerBean player) throws Exception
    {
        // Get transactions
        return this.transactionManager.getPlayerSelectedTransactions(player, this.databaseManager.getDataSource());
    }

    // Get all the player transactions with selected items for a game
    public synchronized List<TransactionBean> getPlayerGameSelectedTransactions(PlayerBean player, int selectedGame) throws Exception
    {
        // Get transactions
        return this.transactionManager.getPlayerGameSelectedTransactions(player, this.databaseManager.getDataSource(), selectedGame);
    }

    // Get all the player transactions for a selected game
    public synchronized List<TransactionBean> getPlayerGameTransactions(PlayerBean player, int selectedGame) throws Exception
    {
        // Get transactions
        return this.transactionManager.getPlayerGameTransactions(player, this.databaseManager.getDataSource(), selectedGame);
    }

    // Create a transaction shop for a player
    public synchronized void createTransaction(PlayerBean player, TransactionBean transaction) throws Exception
    {
        // Write transaction
        this.transactionManager.createTransaction(player, this.databaseManager.getDataSource(), transaction);
    }

    // Update a specified transaction
    public synchronized void updateTransaction(TransactionBean transaction) throws Exception
    {
        // Do the update
        this.transactionManager.updateTransaction(transaction, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of item manager
    ============================================*/

    // Get description of an item
    public synchronized ItemDescriptionBean getItemDescription(int itemId) throws Exception
    {
        // Get description
        return this.itemManager.getItemDescription(itemId, this.databaseManager.getDataSource());
    }

    // Get all the items
    public synchronized List<ItemDescriptionBean> getAllItemDescription() throws Exception
    {
        // Get items
        return this.itemManager.getAllItemDescription(this.databaseManager.getDataSource());
    }


    /*============================================
      Part of nickname manager
    ============================================*/

    // Get a random nickname
    public synchronized NicknameBean getRandomNickname() throws Exception
    {
        // Get the nickname
        return this.nicknameManager.getRandomNickname(this.databaseManager.getDataSource());
    }

    // Check if a nickname is blacklisted
    public synchronized boolean isNicknameBlacklisted(String nickname) throws Exception
    {
        // Check the blacklist
        return this.nicknameManager.isNicknameBlacklisted(nickname, this.databaseManager.getDataSource());
    }

    // Free a nickname
    public synchronized void freeNickname(String nickname) throws Exception
    {
        // Free
        this.nicknameManager.freeNickname(nickname, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of host statistic manager
    ============================================*/

    // Creat a statistic host record
    public synchronized void createHostRecord(HostStatisticsBean hostStatisticsBean) throws Exception
    {
        // Create record
        this.hostManager.createHostRecord(hostStatisticsBean, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of achievement manager
    ============================================*/

    // Get a achievement category by id
    public synchronized AchievementCategoryBean getAchievementCategory(int categoryId) throws Exception
    {
        return this.achievementManager.getAchievementCategory(categoryId, this.databaseManager.getDataSource());
    }

    // Get all achievement categories
    public synchronized List<AchievementCategoryBean> getAchievementCategories() throws Exception
    {
        return this.achievementManager.getAchievementCategories(this.databaseManager.getDataSource());
    }

    // Get a achievement by id
    public synchronized AchievementBean getAchievement(int achievementId) throws Exception
    {
        return this.achievementManager.getAchievement(achievementId, this.databaseManager.getDataSource());
    }

    // Get all achievements
    public synchronized List<AchievementBean> getAchievements() throws Exception
    {
        return this.achievementManager.getAchievements(this.databaseManager.getDataSource());
    }

    // Create a achievement progress
    public synchronized void createAchievementProgress(PlayerBean player, AchievementProgressBean progress) throws Exception
    {
        this.achievementManager.createAchievementProgress(player, progress, this.databaseManager.getDataSource());
    }

    // Get a achievement progress
    public synchronized AchievementProgressBean getAchievementProgress(PlayerBean player, int achievementId) throws Exception
    {
        return this.achievementManager.getAchievementProgress(player, achievementId, this.databaseManager.getDataSource());
    }

    // Get a achievement progress
    public synchronized List<AchievementProgressBean> getAchievementProgresses(PlayerBean player) throws Exception
    {
        return this.achievementManager.getAchievementProgresses(player, this.databaseManager.getDataSource());
    }

    // Update a achievement progress
    public synchronized void updateAchievementProgress(AchievementProgressBean progress) throws Exception
    {
        this.achievementManager.updateAchievementProgress(progress, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of event manager
    ============================================*/

    // Get a event
    public synchronized EventBean getEvent(long eventId) throws Exception
    {
        return this.eventManager.getEvent(eventId, this.databaseManager.getDataSource());
    }

    // Get a event
    public synchronized EventBean getEvent(Timestamp eventDate) throws Exception
    {
        return this.eventManager.getEvent(eventDate, this.databaseManager.getDataSource());
    }

    // Get the events
    public synchronized List<EventBean> getEvents() throws Exception
    {
        return this.eventManager.getEvents(this.databaseManager.getDataSource());
    }

    // Get the winners of a event
    public synchronized List<EventWinnerBean> getEventWinners(long eventId) throws Exception
    {
        return this.eventManager.getEventWinners(eventId, this.databaseManager.getDataSource());
    }

    // Create an event
    public synchronized void createEvent(EventBean event) throws Exception
    {
        this.eventManager.createEvent(event, this.databaseManager.getDataSource());
    }

    // Create a winner entry for an event
    public synchronized void createEventWinner(EventWinnerBean eventWinner) throws Exception
    {
        this.eventManager.createWinnerEvent(eventWinner, this.databaseManager.getDataSource());
    }

    // Update an event
    public synchronized void updateEvent(EventBean event) throws Exception
    {
        this.eventManager.updateEvent(event, this.databaseManager.getDataSource());
    }

    // Update the winner entry of an event
    public synchronized void updateEventWinner(EventWinnerBean eventWinner) throws Exception
    {
        this.eventManager.updateEventWinner(eventWinner, this.databaseManager.getDataSource());
    }


    /*============================================
      Part of message manager
    ============================================*/

    // Get a scheduled message
    public synchronized ScheduledMessageBean getScheduledMessage(int messageId) throws Exception
    {
        return this.messageManager.getScheduledMessage(messageId, this.databaseManager.getDataSource());
    }

    // Get the scheduled messages
    public synchronized List<ScheduledMessageBean> getScheduledMessages() throws Exception
    {
        return this.messageManager.getScheduledMessages(this.databaseManager.getDataSource());
    }

    // Create a scheduled message
    public synchronized void createScheduledMessage(ScheduledMessageBean message) throws Exception
    {
        this.messageManager.updateScheduledMessage(message, this.databaseManager.getDataSource());
    }

    // Update a scheduled message
    public synchronized void updateScheduledMessage(ScheduledMessageBean message) throws Exception
    {
        this.messageManager.updateScheduledMessage(message, this.databaseManager.getDataSource());
    }
}
