package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.statistics.PlayerStatisticsBean;
import net.samagames.persistanceapi.beans.statistics.*;
import net.samagames.persistanceapi.datamanager.aggregationmanager.statistics.*;
import javax.sql.DataSource;

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
public class StatisticsManager
{
    // Defines
    public DimensionsStatisticsManager dimensionsStatsManager;
    public JukeBoxStatisticsManager jukeBoxStatsManager;
    public QuakeStatisticsManager quakeStatsManager;
    public UHCOriginalStatisticsManager uhcOriginalStatsManager;
    public UHCRunStatisticsManager uhcRunStatsManager;
    public DoubleRunnerStatisticsManager doubleRunnerStatsManager;
    public UHCRandomStatisticsManager uhcRandomStatsManager;
    public RandomRunStatisticsManager randomRunStatsManager;
    public UltraFlagKeeperStatisticsManager ultraFlagKeeperStatsManager;
    public UppervoidStatisticsManager uppervoidStatsManager;
    public ChunkWarsStatisticsManager chunkWarsStatisticsManager;
    public TheDropperStatisticsManager theDropperStatisticsManager;
    public NetworkStatisticsManager networkStatisticsManager;

    // Constructor
    public StatisticsManager()
    {
        this.dimensionsStatsManager = new DimensionsStatisticsManager();
        this.jukeBoxStatsManager = new JukeBoxStatisticsManager();
        this.quakeStatsManager = new QuakeStatisticsManager();
        this.uhcOriginalStatsManager = new UHCOriginalStatisticsManager();
        this.uhcRunStatsManager = new UHCRunStatisticsManager();
        this.doubleRunnerStatsManager = new DoubleRunnerStatisticsManager();
        this.uhcRandomStatsManager = new UHCRandomStatisticsManager();
        this.randomRunStatsManager = new RandomRunStatisticsManager();
        this.ultraFlagKeeperStatsManager = new UltraFlagKeeperStatisticsManager();
        this.uppervoidStatsManager = new UppervoidStatisticsManager();
        this.chunkWarsStatisticsManager = new ChunkWarsStatisticsManager();
        this.theDropperStatisticsManager = new TheDropperStatisticsManager();
        this.networkStatisticsManager = new NetworkStatisticsManager();
    }

    // Get all player statistics
    public PlayerStatisticsBean getAllPlayerStatistics(PlayerBean player, DataSource dataSource) throws Exception
    {
        // Declared bean
        DimensionsStatisticsBean dimensionsStats;
        JukeBoxStatisticsBean jukeBoxStats;
        QuakeStatisticsBean quakeStats;
        UHCOriginalStatisticsBean uhcOriginalStats;
        UHCRunStatisticsBean uhcRunStats;
        DoubleRunnerStatisticsBean doubleRunnerStats;
        UHCRandomStatisticsBean uhcRandomStats;
        RandomRunStatisticsBean randomRunStats;
        UltraFlagKeeperStatisticsBean ultraFlagKeeperStats;
        UppervoidStatisticsBean upperVoidStats;
        ChunkWarsStatisticsBean chunkWarsStats;
        TheDropperStatisticsBean theDropperStats;

        // Get the different statistics bean
        try
        {
            dimensionsStats = this.dimensionsStatsManager.getDimensionsStatistics(player, dataSource);
            jukeBoxStats = this.jukeBoxStatsManager.getJukeBoxStatistics(player, dataSource);
            quakeStats = this.quakeStatsManager.getQuakeStatistics(player, dataSource);
            uhcOriginalStats = this.uhcOriginalStatsManager.getUHCOriginalStatistics(player, dataSource);
            uhcRunStats = this.uhcRunStatsManager.getUHCRunStatistics(player, dataSource);
            doubleRunnerStats = this.doubleRunnerStatsManager.getDoubleRunnerStatistics(player, dataSource);
            uhcRandomStats = this.uhcRandomStatsManager.getUHCRandomStatistics(player, dataSource);
            randomRunStats = this.randomRunStatsManager.getRandomRunStatistics(player, dataSource);
            ultraFlagKeeperStats = this.ultraFlagKeeperStatsManager.getUltraFlagKeeperStatistics(player, dataSource);
            upperVoidStats = this.uppervoidStatsManager.getUppervoidStatistics(player, dataSource);
            chunkWarsStats = this.chunkWarsStatisticsManager.getChunkWarsStatistics(player, dataSource);
            theDropperStats = this.theDropperStatisticsManager.getTheDropperStatistics(player, dataSource);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }

        // Create the aggregation of different statistics bean
        return new PlayerStatisticsBean(dimensionsStats, jukeBoxStats, quakeStats, uhcOriginalStats, uhcRunStats, doubleRunnerStats, uhcRandomStats, randomRunStats, ultraFlagKeeperStats, upperVoidStats, chunkWarsStats, theDropperStats);
    }

    // Update all player statistics
    public void updateAllPlayerStatistics(PlayerBean player, PlayerStatisticsBean data, DataSource dataSource) throws Exception
    {
        // Update the different statistics bean
        try
        {
            this.dimensionsStatsManager.updateDimensionsStatistics(player, data.getDimensionsStats(),dataSource);
            this.jukeBoxStatsManager.updateJukeBoxStatistics(player, data.getJukeBoxStats(), dataSource);
            this.quakeStatsManager.updateQuakeStatistics(player, data.getQuakeStats(), dataSource);
            this.uhcOriginalStatsManager.updateUHCOriginalStatistics(player, data.getUHCOriginalStats(), dataSource);
            this.uhcRunStatsManager.updateUHCRunStatistics(player, data.getUHCRunStats(), dataSource);
            this.doubleRunnerStatsManager.updateDoubleRunnerStatistics(player, data.getDoubleRunnerStats(), dataSource);
            this.uhcRandomStatsManager.updateUHCRandomStatistics(player, data.getUHCRandomStats(), dataSource);
            this.randomRunStatsManager.updateRandomRunStatistics(player, data.getRandomRunStats(), dataSource);
            this.ultraFlagKeeperStatsManager.updateUltraFlagKeeperStatistics(player, data.getUltraFlagKeeperStats(), dataSource);
            this.uppervoidStatsManager.updateUppervoidStatistics(player, data.getUppervoidStats(), dataSource);
            this.chunkWarsStatisticsManager.updateChunkWarsStatistics(player, data.getChunkWarsStats(), dataSource);
            this.theDropperStatisticsManager.updateTheDropperStatistics(player, data.getTheDropperStats(), dataSource);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
            throw exception;
        }

    }

}
