package net.samagames.persistanceapi.tests;

import net.samagames.persistanceapi.GameServiceManager;
import net.samagames.persistanceapi.beans.achievements.AchievementProgressBean;
import net.samagames.persistanceapi.beans.permissions.APIPermissionsBean;
import net.samagames.persistanceapi.beans.players.*;
import net.samagames.persistanceapi.beans.shop.PromotionsBean;
import net.samagames.persistanceapi.beans.shop.TransactionBean;
import net.samagames.persistanceapi.beans.statistics.*;
import net.samagames.persistanceapi.beans.utils.BungeeConfigBean;
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
public class Test
{
    public static void main (String[] args)
    {
        try {
            /*
                Pour la connetion à la Bdd en local ligne de commande :  /Applications/MAMP/Library/bin/mysql --host=localhost -uroot -proot
            */

            // Execution plateform
            String cible;
            cible = args[0];
            if (cible == null)
            {
                cible = "";
            }

            // Defines
            long startTime;
            DenunciationBean denunciationBean;
            UUID uuid;
            PlayerBean player;
            PlayerBean otherPlayer;
            DimensionsStatisticsBean dimensionsStats;
            JukeBoxStatisticsBean jukeBoxStats;
            NetworkStatisticsBean networkStats;
            QuakeStatisticsBean quakeStats;
            UHCOriginalStatisticsBean uhcOriginalStats;
            UHCRunStatisticsBean uhcRunStats;
            DoubleRunnerStatisticsBean doubleRunnerStats;
            UHCRandomStatisticsBean uhcRandomStats;
            RandomRunStatisticsBean randomRunStats;
            UltraFlagKeeperStatisticsBean ultraFlagKeeperStats;
            UppervoidStatisticsBean upperVoidStats;
            ChunkWarsStatisticsBean chunkWarsStats;
            GameServiceManager manager;

            // Initialize the manager
            System.out.println("Exécution du test");
            System.out.println("-----------------");
            startTime = System.currentTimeMillis();
            if (cible.equals("maison")) {
                // For MisterSatch only
                manager = new GameServiceManager("jdbc:mysql://127.0.0.1:8889/samagamesV3", "root", "root", 1, 10);
            } else {
                // For standard localhost configuration
                manager = new GameServiceManager("jdbc:mysql://127.0.0.1:3306/samagamesv3", "root", "", 1, 10);
            }
            System.out.println("Manager init time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Config loading
            startTime = System.currentTimeMillis();
            BungeeConfigBean config = manager.getBungeeConfig();
            System.out.println("Load config time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Config updating
            startTime = System.currentTimeMillis();
            config.setSlots(10);
            config.setMaxPlayers(10000);
            manager.updateBungeeConfig(config);
            System.out.println("Update config time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create the player
            startTime = System.currentTimeMillis();
            player = new PlayerBean(UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d"), "mistersatch", "the boss", 0, 0, 0, new Timestamp(System.currentTimeMillis()),
                    new Timestamp(System.currentTimeMillis()), "125.125.125.125", "TheUltimateKey", 1);
            manager.createPlayer(player);
            System.out.println("Create player process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Find a player test
            startTime = System.currentTimeMillis();
            player = manager.getPlayer(UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d"), null);
            System.out.println("Find player process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Find player test and self create one
            startTime = System.currentTimeMillis();
            PlayerBean selfPlayer = new PlayerBean(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeeeee"), "player_1", "the looser", 0, 0, 0, null, null, null, null, 1);
            manager.getPlayer(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeeeee"), selfPlayer);
            System.out.println("Find and self create player process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Data player update test
            player.setCoins(20);
            player.setStars(10);
            player.setLastIP("100.100.100.100");
            player.setTopTpKey("NewKey");
            startTime = System.currentTimeMillis();
            manager.updatePlayer(player);
            System.out.println("Update player process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a denunciation with existing player name
            startTime = System.currentTimeMillis();
            denunciationBean = new DenunciationBean(player.getUuid(), new Timestamp(System.currentTimeMillis()), "fly", "mistersatch");
            manager.denouncePlayer(player, denunciationBean);
            System.out.println("Denunciation with name process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a denunciation without existing player name
            startTime = System.currentTimeMillis();
            denunciationBean = new DenunciationBean(player.getUuid(), new Timestamp(System.currentTimeMillis()), "fly", "billyboy");
            manager.denouncePlayer(player, denunciationBean);
            System.out.println("Denunciation without process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create dimensions statistics test
            uuid = UUID.fromString("7b9ffe3f-96d0-41dc-bb2a-93b7c7ba2bcd");
            otherPlayer = new PlayerBean(uuid, "thegreatancien", "the killer", 0, 0, 0, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), "50.50.50.50", "AnotherKey", 1);
            dimensionsStats = new DimensionsStatisticsBean(uuid, 50, 60, 70, 80, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 0);
            startTime = System.currentTimeMillis();
            manager.updateDimensionsStatistics(otherPlayer, dimensionsStats);
            System.out.println("Create dimension statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update dimension statistics test
            dimensionsStats.setDeaths(0);
            dimensionsStats.setPlayedGames(0);
            startTime = System.currentTimeMillis();
            manager.updateDimensionsStatistics(otherPlayer, dimensionsStats);
            System.out.println("Update dimension statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read dimension statistics test
            startTime = System.currentTimeMillis();
            manager.getDimensionsStatistics(otherPlayer);
            System.out.println("Read dimension statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create jukebox statistics test
            jukeBoxStats = new JukeBoxStatisticsBean(uuid, 10, 20, 30, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1000);
            startTime = System.currentTimeMillis();
            manager.updateJukeBoxStatistics(otherPlayer, jukeBoxStats);
            System.out.println("Create jukebox statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update jukebox statistics test
            jukeBoxStats.setWoots(42);
            jukeBoxStats.setMehs(77);
            startTime = System.currentTimeMillis();
            manager.updateJukeBoxStatistics(otherPlayer, jukeBoxStats);
            System.out.println("Update jukebox statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read jukebox statistics test
            startTime = System.currentTimeMillis();
            manager.getJukeBoxStatistics(otherPlayer);
            System.out.println("Read jukebox statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create network statistics test
            networkStats = new NetworkStatisticsBean(uuid, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1000);
            startTime = System.currentTimeMillis();
            manager.updateNetworkStatistics(otherPlayer, networkStats);
            System.out.println("Create network statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update network statistics test
            networkStats.setPlayedTime(1042);
            startTime = System.currentTimeMillis();
            manager.updateNetworkStatistics(otherPlayer, networkStats);
            System.out.println("Update network statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read network statistics test
            startTime = System.currentTimeMillis();
            manager.getNetworkStatistics(otherPlayer);
            System.out.println("Read network statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create quake statistics test
            quakeStats = new QuakeStatisticsBean(uuid, 10, 20, 30, 5, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1000);
            startTime = System.currentTimeMillis();
            manager.updateQuakeStatistics(otherPlayer, quakeStats);
            System.out.println("Create quake statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update quake statistics test
            quakeStats.setDeaths(1000);
            quakeStats.setWins(1);
            startTime = System.currentTimeMillis();
            manager.updateQuakeStatistics(otherPlayer, quakeStats);
            System.out.println("Update quake statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read quake statistics test
            startTime = System.currentTimeMillis();
            manager.getQuakeStatistics(otherPlayer);
            System.out.println("Read quake statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create UHCOriginal statistics test
            uhcOriginalStats = new UHCOriginalStatisticsBean(uuid, 10, 20, 30, 50, 3, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 8000);
            startTime = System.currentTimeMillis();
            manager.updateUHCOriginalStatistics(otherPlayer, uhcOriginalStats);
            System.out.println("Create UHCOriginal statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update UHCOriginal statistics test
            uhcOriginalStats.setKills(1000);
            uhcOriginalStats.setPlayedGames(42);
            startTime = System.currentTimeMillis();
            manager.updateUHCOriginalStatistics(otherPlayer, uhcOriginalStats);
            System.out.println("Update UHCOriginal statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read UHCOriginal statistics test
            startTime = System.currentTimeMillis();
            manager.getUHCOriginalStatistics(otherPlayer);
            System.out.println("Read UHCOriginal statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create UHCRun statistics test
            uhcRunStats = new UHCRunStatisticsBean(uuid, 10, 20, 30, 50, 3, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 8000);
            startTime = System.currentTimeMillis();
            manager.updateUHCRunStatistics(otherPlayer, uhcRunStats);
            System.out.println("Create UHCRun statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update UHCRun statistics test
            uhcRunStats.setKills(1000);
            uhcRunStats.setPlayedGames(42);
            startTime = System.currentTimeMillis();
            manager.updateUHCRunStatistics(otherPlayer, uhcRunStats);
            System.out.println("Update UHCRun statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read UHCRun statistics test
            startTime = System.currentTimeMillis();
            manager.getUHCRunStatistics(otherPlayer);
            System.out.println("Read UHCRun statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create DoubleRunner statistics test
            doubleRunnerStats = new DoubleRunnerStatisticsBean(uuid, 10, 20, 30, 50, 3, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 8000);
            startTime = System.currentTimeMillis();
            manager.updateDoubleRunnerStatistics(otherPlayer, doubleRunnerStats);
            System.out.println("Create DoubleRunner statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update DoubleRunner statistics test
            doubleRunnerStats.setKills(1000);
            doubleRunnerStats.setPlayedGames(42);
            startTime = System.currentTimeMillis();
            manager.updateDoubleRunnerStatistics(otherPlayer, doubleRunnerStats);
            System.out.println("Update DoubleRunner statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read DoubleRunner statistics test
            startTime = System.currentTimeMillis();
            manager.getDoubleRunnerStatistics(otherPlayer);
            System.out.println("Read DoubleRunner statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create UHCRandom statistics test
            uhcRandomStats = new UHCRandomStatisticsBean(uuid, 10, 20, 30, 50, 3, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 8000);
            startTime = System.currentTimeMillis();
            manager.updateUHCRandomStatistics(otherPlayer, uhcRandomStats);
            System.out.println("Create UHCRandom statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update UHCRandom statistics test
            uhcRandomStats.setKills(1000);
            uhcRandomStats.setPlayedGames(42);
            startTime = System.currentTimeMillis();
            manager.updateUHCRandomStatistics(otherPlayer, uhcRandomStats);
            System.out.println("Update UHCRandom statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read UHCRandom statistics test
            startTime = System.currentTimeMillis();
            manager.getUHCRandomStatistics(otherPlayer);
            System.out.println("Read UHCRandom statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create RandomRun statistics test
            randomRunStats = new RandomRunStatisticsBean(uuid, 10, 20, 30, 50, 3, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 8000);
            startTime = System.currentTimeMillis();
            manager.updateRandomRunStatistics(otherPlayer, randomRunStats);
            System.out.println("Create RandomRun statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update RandomRun statistics test
            randomRunStats.setKills(1000);
            randomRunStats.setPlayedGames(42);
            startTime = System.currentTimeMillis();
            manager.updateRandomRunStatistics(otherPlayer, randomRunStats);
            System.out.println("Update RandomRun statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read RandomRun statistics test
            startTime = System.currentTimeMillis();
            manager.getRandomRunStatistics(otherPlayer);
            System.out.println("Read RandomRun statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create UltraFlagKeeper statistics test
            ultraFlagKeeperStats = new UltraFlagKeeperStatisticsBean(uuid, 10, 20, 30, 50, 3, 1, 5, 39, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 8000);
            startTime = System.currentTimeMillis();
            manager.updateUltraFlagKeeperStatistics(otherPlayer, ultraFlagKeeperStats);
            System.out.println("Create UltraFlagKeeper statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update UltraFlagKeeper statistics test
            ultraFlagKeeperStats.setKills(1000);
            ultraFlagKeeperStats.setPlayedGames(42);
            ultraFlagKeeperStats.setFlagsCaptured(10);
            ultraFlagKeeperStats.setFlagsReturned(64);
            startTime = System.currentTimeMillis();
            manager.updateUltraFlagKeeperStatistics(otherPlayer, ultraFlagKeeperStats);
            System.out.println("Update UltraFlagKeeper statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read UltraFlagKeeper statistics test
            startTime = System.currentTimeMillis();
            manager.getUltraFlagKeeperStatistics(otherPlayer);
            System.out.println("Read UltraFlagKeeper statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create UpperVoid statistics test
            upperVoidStats = new UppervoidStatisticsBean(uuid, 40, 60, 5, 120, 2, 10, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 6000);
            startTime = System.currentTimeMillis();
            manager.updateUppervoidStatistics(otherPlayer, upperVoidStats);
            System.out.println("Create UpperVoid statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update UpperVoid statistics test
            upperVoidStats.setPlayedGames(555);
            startTime = System.currentTimeMillis();
            manager.updateUppervoidStatistics(otherPlayer, upperVoidStats);
            System.out.println("Update UpperVoid statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read UpperVoid statistics test
            startTime = System.currentTimeMillis();
            manager.getUppervoidStatistics(otherPlayer);
            System.out.println("Read UpperVoid statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create ChunkWars statistics test
            chunkWarsStats = new ChunkWarsStatisticsBean(uuid, 40, 60, 5, 120, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 6000);
            startTime = System.currentTimeMillis();
            manager.updateChunkWarsStatistics(otherPlayer, chunkWarsStats);
            System.out.println("Create ChunkWars statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update UpperVoid statistics test
            chunkWarsStats.setPlayedGames(555);
            startTime = System.currentTimeMillis();
            manager.updateChunkWarsStatistics(otherPlayer, chunkWarsStats);
            System.out.println("Update ChunkWars statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Read UpperVoid statistics test
            startTime = System.currentTimeMillis();
            manager.getChunkWarsStatistics(otherPlayer);
            System.out.println("Read ChunkWars statistics process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // All statistics read for player test
            startTime = System.currentTimeMillis();
            manager.getAllStatistics(player);
            System.out.println("All statistics read process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a ban sanction test
            startTime = System.currentTimeMillis();
            SanctionBean sanction = new SanctionBean(1, UUID.fromString("7b9ffe3f-96d0-41dc-bb2a-93b7c7ba2bcd"), SanctionBean.BAN, "fly", UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d"),
                    new Timestamp(System.currentTimeMillis()), false, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
            manager.applySanction(SanctionBean.BAN, sanction);
            System.out.println("Create sanction ban process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a mute sanction test
            startTime = System.currentTimeMillis();
            sanction = new SanctionBean(2, UUID.fromString("7b9ffe3f-96d0-41dc-bb2a-93b7c7ba2bcd"), SanctionBean.MUTE, "trololol", UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d"),
                    new Timestamp(System.currentTimeMillis() + 800000), false, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
            manager.applySanction(SanctionBean.MUTE, sanction);
            System.out.println("Create sanction mute process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Remove ban sanction test
            startTime = System.currentTimeMillis();
            manager.removeSanction(SanctionBean.BAN, otherPlayer);
            System.out.println("Remove sanction ban process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Check if player is banned test
            startTime = System.currentTimeMillis();
            manager.getPlayerBanned(otherPlayer);
            System.out.println("Check isBanned process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Check if player is muted test
            startTime = System.currentTimeMillis();
            manager.getPlayerMuted(otherPlayer);
            System.out.println("Check isMute process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all sanctions test
            startTime = System.currentTimeMillis();
            manager.getAllSanctions(UUID.fromString("7b9ffe3f-96d0-41dc-bb2a-93b7c7ba2bcd"), 1);
            System.out.println("Check get all sanctions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all actives sanctions test
            startTime = System.currentTimeMillis();
            manager.getAllSanctions(UUID.fromString("7b9ffe3f-96d0-41dc-bb2a-93b7c7ba2bcd"), 1);
            System.out.println("Check get all actives sanctions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all passives sanctions test
            startTime = System.currentTimeMillis();
            manager.getAllSanctions(UUID.fromString("7b9ffe3f-96d0-41dc-bb2a-93b7c7ba2bcd"), 1);
            System.out.println("Check get all passives sanctions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all sanctions by uuid test
            startTime = System.currentTimeMillis();
            manager.getAllModeratorSanctions(UUID.fromString("7b9ffe3f-96d0-41dc-bb2a-93b7c7ba2bcd"));
            System.out.println("Check get all sanctions for uuid process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get API permissions
            startTime = System.currentTimeMillis();
            manager.getAPIPermissions(otherPlayer);
            System.out.println("Get API permissions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get Bukkit permissions
            startTime = System.currentTimeMillis();
            manager.getBukkitPermissions(otherPlayer);
            System.out.println("Get Bukkit permissions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get Bungee & Redis permissions
            startTime = System.currentTimeMillis();
            manager.getBungeeRedisPermissions(otherPlayer);
            System.out.println("Get Bungee & Redis permissions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get Hub permissions
            startTime = System.currentTimeMillis();
            manager.getHubPermissions(otherPlayer);
            System.out.println("Get Hub permissions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get moderation permissions
            startTime = System.currentTimeMillis();
            manager.getModerationPermissions(otherPlayer);
            System.out.println("Get moderation permissions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get proxies permissions
            startTime = System.currentTimeMillis();
            manager.getProxiesPermissions(otherPlayer);
            System.out.println("Get proxies permissions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get staff permissions
            startTime = System.currentTimeMillis();
            manager.getStaffPermissions(otherPlayer);
            System.out.println("Get staff permissions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all permissions
            startTime = System.currentTimeMillis();
            manager.getAllPlayerPermissions(otherPlayer);
            System.out.println("Get all permissions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get group permission for a player
            startTime = System.currentTimeMillis();
            manager.getPlayerGroup(otherPlayer);
            System.out.println("Get group permissions process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a friendship demand
            FriendshipBean friendhip = new FriendshipBean(otherPlayer.getUuid(), player.getUuid(), null, null, false);
            startTime = System.currentTimeMillis();
            manager.postFriendshipDemand(friendhip);
            System.out.println("Friendhip demand creation process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get friendship demand list
            startTime = System.currentTimeMillis();
            List<FriendshipBean> friendshipList = manager.getFriendshipDemandList(player);
            System.out.println("Friendhip get list process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Accept a friendship demand
            startTime = System.currentTimeMillis();
            FriendshipBean friendshipDemand = friendshipList.get(0);
            manager.acceptFriendshipDemand(friendshipDemand);
            System.out.println("Friendship accept demand process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get friendship  list
            startTime = System.currentTimeMillis();
            friendshipList = manager.getFriendshipList(player);
            System.out.println("Friendhip get list process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Refuse a friendship demand
            startTime = System.currentTimeMillis();
            manager.refuseFriendshipDemand(friendshipList.get(0));
            System.out.println("Friendhip refuse demand process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all the friendship for both player
            startTime = System.currentTimeMillis();
            manager.getFriendshipNamedList(otherPlayer, player);
            System.out.println("Friendhip between player list process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a promotion
            startTime = System.currentTimeMillis();
            long time = System.currentTimeMillis();
            PromotionsBean promotionsBean = new PromotionsBean(0, 1, 10, "super promo", new Timestamp(time), new Timestamp(time + 20000));
            PromotionsBean otherPromotionBean = new PromotionsBean(1, 4, 50, "promo", new Timestamp(time), new Timestamp(time + 20000));
            manager.createPromotion(promotionsBean);
            manager.createPromotion(otherPromotionBean);
            System.out.println("Promotion creation process time: " + (System.currentTimeMillis() - startTime) / 2 + " ms");

            // Get all active promotion
            startTime = System.currentTimeMillis();
            manager.getAllActivePromotions();
            System.out.println("Get all active promotion process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get specific promotion
            startTime = System.currentTimeMillis();
            otherPromotionBean = manager.getPromotion(0, 1).get(0);
            System.out.println("Get specific promotion process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Delete promotion
            startTime = System.currentTimeMillis();
            manager.deletePromotion(otherPromotionBean);
            System.out.println("Delete promotion process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Permission to hashmap test
            startTime = System.currentTimeMillis();
            APIPermissionsBean bean = new APIPermissionsBean(1, true, false, true, false, true, false, true, false, true, false, true, false, true, false, false);
            bean.getHashMap();
            System.out.println("Permission to hashmap process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // HashMap permission setup test
            startTime = System.currentTimeMillis();
            bean.set("api.servers.debug", new Boolean(false));
            System.out.println("HashMap permissions setup process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get dimensions leaderboard
            PlayerBean player_2 = new PlayerBean(UUID.fromString("aaaaaaaa-cccc-cccc-dddd-eeeeeeeeeeeeee"), "player_2", "the 2", 0, 0, 0, null, null, null, null, 1);
            manager.getPlayer(UUID.fromString("aaaaaaaa-cccc-cccc-dddd-eeeeeeeeeeeeee"), player_2);
            PlayerBean player_3 = new PlayerBean(UUID.fromString("aaaaaaaa-dddd-cccc-dddd-eeeeeeeeeeeeee"), "player_3", "the 3", 0, 0, 0, null, null, null, null, 1);
            manager.getPlayer(UUID.fromString("aaaaaaaa-dddd-cccc-dddd-eeeeeeeeeeeeee"), player_3);
            uuid = UUID.fromString("aaaaaaaa-cccc-cccc-dddd-eeeeeeeeeeeeee");
            dimensionsStats = new DimensionsStatisticsBean(uuid, 10, 20, 30, 40, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 0);
            manager.updateDimensionsStatistics(player_2, dimensionsStats);
            uuid = UUID.fromString("aaaaaaaa-dddd-cccc-dddd-eeeeeeeeeeeeee");
            dimensionsStats = new DimensionsStatisticsBean(uuid, 100, 200, 300, 400, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 0);
            manager.updateDimensionsStatistics(player_3, dimensionsStats);
            uuid = UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d");
            dimensionsStats = new DimensionsStatisticsBean(uuid, 1, 2, 3, 4, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 0);
            manager.updateDimensionsStatistics(player, dimensionsStats);
            startTime = System.currentTimeMillis();
            manager.getDimensionsLeaderBoard("deaths");
            System.out.println("Get dimensions leaderboard process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get jukebox leaderboard
            uuid = UUID.fromString("aaaaaaaa-cccc-cccc-dddd-eeeeeeeeeeeeee");
            jukeBoxStats = new JukeBoxStatisticsBean(uuid, 10, 20, 30, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1000);
            manager.updateJukeBoxStatistics(player_2, jukeBoxStats);
            uuid = UUID.fromString("aaaaaaaa-dddd-cccc-dddd-eeeeeeeeeeeeee");
            jukeBoxStats = new JukeBoxStatisticsBean(uuid, 5, 20, 40, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1000);
            manager.updateJukeBoxStatistics(player_3, jukeBoxStats);
            uuid = UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d");
            jukeBoxStats = new JukeBoxStatisticsBean(uuid, 15, 20, 20, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1000);
            manager.updateJukeBoxStatistics(player, jukeBoxStats);
            startTime = System.currentTimeMillis();
            manager.getJukeBoxLeaderBoard("mehs");
            System.out.println("Get jukebox leaderboard process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get quake leaderboard
            uuid = UUID.fromString("aaaaaaaa-cccc-cccc-dddd-eeeeeeeeeeeeee");
            quakeStats = new QuakeStatisticsBean(uuid, 10, 20, 30, 5, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1000);
            manager.updateQuakeStatistics(player_2, quakeStats);
            uuid = UUID.fromString("aaaaaaaa-dddd-cccc-dddd-eeeeeeeeeeeeee");
            quakeStats = new QuakeStatisticsBean(uuid, 10, 20, 40, 5, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1000);
            manager.updateQuakeStatistics(player_3, quakeStats);
            uuid = UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d");
            quakeStats = new QuakeStatisticsBean(uuid, 10, 20, 50, 5, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 1000);
            manager.updateQuakeStatistics(player, quakeStats);
            startTime = System.currentTimeMillis();
            manager.getQuakeLeaderBoard("wins");
            System.out.println("Get quake leaderboard process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get uhcrun leaderboard
            uuid = UUID.fromString("aaaaaaaa-cccc-cccc-dddd-eeeeeeeeeeeeee");
            uhcRunStats = new UHCRunStatisticsBean(uuid, 10, 20, 20, 30, 3, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 8000);
            manager.updateUHCRunStatistics(player_2, uhcRunStats);
            uuid = UUID.fromString("aaaaaaaa-dddd-cccc-dddd-eeeeeeeeeeeeee");
            uhcRunStats = new UHCRunStatisticsBean(uuid, 10, 20, 30, 40, 3, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 8000);
            manager.updateUHCRunStatistics(player_3, uhcRunStats);
            uuid = UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d");
            uhcRunStats = new UHCRunStatisticsBean(uuid, 10, 20, 10, 50, 3, 1, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 8000);
            manager.updateUHCRunStatistics(player, uhcRunStats);
            startTime = System.currentTimeMillis();
            manager.getUHCOriginalLeaderBoard("kills");
            System.out.println("Get uhcrun leaderboard process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get uppervoid leaderboard
            uuid = UUID.fromString("aaaaaaaa-cccc-cccc-dddd-eeeeeeeeeeeeee");
            upperVoidStats = new UppervoidStatisticsBean(uuid, 40, 60, 5, 120, 2, 10, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 6000);
            manager.updateUppervoidStatistics(player_2, upperVoidStats);
            uuid = UUID.fromString("aaaaaaaa-dddd-cccc-dddd-eeeeeeeeeeeeee");
            upperVoidStats = new UppervoidStatisticsBean(uuid, 40, 60, 6, 120, 2, 10, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 6000);
            manager.updateUppervoidStatistics(player_3, upperVoidStats);
            uuid = UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d");
            upperVoidStats = new UppervoidStatisticsBean(uuid, 50, 60, 4, 120, 2, 10, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), 6000);
            manager.updateUppervoidStatistics(player, upperVoidStats);
            startTime = System.currentTimeMillis();
            manager.getUppervoidLeaderBoard("kills");
            System.out.println("Get uppervoid leaderboard process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create player settings test
            startTime = System.currentTimeMillis();
            manager.createDefaultPlayerSettings(otherPlayer);
            System.out.println("Create default player settings process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get player settings test
            startTime = System.currentTimeMillis();
            PlayerSettingsBean settings = manager.getPlayerSettings(otherPlayer);
            System.out.println("Get player settings process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update player settings test
            settings.setAllowClickOnOther(false);
            settings.setClickOnMeActivation(false);
            startTime = System.currentTimeMillis();
            manager.setPlayerSettings(otherPlayer, settings);
            System.out.println("Update player settings process time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a default dimensions statistics test
            PlayerBean player_4 = new PlayerBean(UUID.fromString("aaaaaaaa-eeee-cccc-dddd-eeeeeeeeeeeeee"), "player_4", "the 4", 0, 0, 0, null, null, null, null, 1);
            manager.getPlayer(UUID.fromString("aaaaaaaa-eeee-cccc-dddd-eeeeeeeeeeeeee"), player_4);
            startTime = System.currentTimeMillis();
            manager.getDimensionsStatistics(player_4);
            System.out.println("Create a default dimensions statistics time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a default jukebox statistics test
            startTime = System.currentTimeMillis();
            manager.getJukeBoxStatistics(player_4);
            System.out.println("Create a default jukebox statistics time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a default jukebox statistics test
            startTime = System.currentTimeMillis();
            manager.getQuakeStatistics(player_4);
            System.out.println("Create a default quake statistics time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a default uhcRun statistics test
            startTime = System.currentTimeMillis();
            manager.getUHCRunStatistics(player_4);
            System.out.println("Create a default uhcRun statistics time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a default uppervoid statistics test
            startTime = System.currentTimeMillis();
            manager.getUppervoidStatistics(player_4);
            System.out.println("Create a default uppervoid statistics time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a transaction
            TransactionBean transaction = new TransactionBean(1, 1, 20, 30, new Timestamp(System.currentTimeMillis()), true, player.getUuid());
            startTime = System.currentTimeMillis();
            manager.createTransaction(player, transaction);
            System.out.println("Create transaction time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get transaction for a player
            transaction = new TransactionBean(2, 1, 40, 50, new Timestamp(System.currentTimeMillis()), true, otherPlayer.getUuid());
            manager.createTransaction(player_3, transaction);
            startTime = System.currentTimeMillis();
            manager.getPlayerTransactions(otherPlayer);
            System.out.println("Get transaction for a player time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all selected transactions for a player
            startTime = System.currentTimeMillis();
            manager.getPlayerSelectedTransactions(otherPlayer);
            System.out.println("Get all selected transaction for a player time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all selected transaction for a game and player
            startTime = System.currentTimeMillis();
            manager.getPlayerGameSelectedTransactions(otherPlayer, 1);
            System.out.println("Get all selected transaction for a player time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get the description of a item
            startTime = System.currentTimeMillis();
            manager.getItemDescription(1);
            System.out.println("Get item description time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all the desctiption items
            startTime = System.currentTimeMillis();
            manager.getAllItemDescription();
            System.out.println("Get item description time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update a transaction
            startTime = System.currentTimeMillis();
            transaction.setSelected(false);
            manager.updateTransaction(transaction);
            System.out.println("Update transaction time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get all transaction for a game and player
            startTime = System.currentTimeMillis();
            transaction.setSelected(false);
            manager.getPlayerGameTransactions(otherPlayer, 1);
            System.out.println("Update transaction time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get a random nickname
            startTime = System.currentTimeMillis();
            NicknameBean nicknameBean = manager.getRandomNickname();
            System.out.println("Get a random nickname time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Check if the nickname is blacklisted
            startTime = System.currentTimeMillis();
            manager.isNicknameBlacklisted(nicknameBean.getNickname());
            System.out.println("Reserve nickname time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Free a nickname
            startTime = System.currentTimeMillis();
            manager.freeNickname(nicknameBean.getNickname());
            System.out.println("Free nickname time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Insert a host statistic
            HostStatisticsBean hostStatisticsBean = new HostStatisticsBean("thtemplate", "the host", "127.0.0.1", UUID.fromString("a9ebd2f3-271d-4c6c-ba28-50f7ddd3465d"), System.currentTimeMillis(), 3500);
            startTime = System.currentTimeMillis();
            manager.createHostRecord(hostStatisticsBean);
            System.out.println("Host stat time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Create a achievement progress
            AchievementProgressBean achievementProgressBean = new AchievementProgressBean(0, 0, 0, null, null, player.getUuid());
            startTime = System.currentTimeMillis();
            manager.createAchievementProgress(player, achievementProgressBean);
            System.out.println("Achievement progress creation time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Update a achievement progress
            achievementProgressBean.setProgress(3);
            achievementProgressBean.setUnlockDate(new Timestamp(System.currentTimeMillis()));
            startTime = System.currentTimeMillis();
            manager.updateAchievementProgress(achievementProgressBean);
            System.out.println("Achievement progress update time: " + (System.currentTimeMillis() - startTime) + " ms");

            // Get a achievement progress
            startTime = System.currentTimeMillis();
            manager.getAchievementProgress(player, 0);
            System.out.println("Achievement progress get time: " + (System.currentTimeMillis() - startTime) + " ms");

        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }
}
