package net.samagames.persistanceapi.beans.statistics;

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
public class PlayerStatisticsBean
{
    // Defines aggregation of statistics
    private DimensionsStatisticsBean dimensionsStats;
    private JukeBoxStatisticsBean jukeBoxStats;
    private QuakeStatisticsBean quakeStats;
    private UHCOriginalStatisticsBean uhcOriginalStats;
    private UHCRunStatisticsBean uhcRunStats;
    private DoubleRunnerStatisticsBean doubleRunnerStats;
    private UHCRandomStatisticsBean uhcRandomStats;
    private RandomRunStatisticsBean randomRunStats;
    private UltraFlagKeeperStatisticsBean ultraFlagKeeperStats;
    private UppervoidStatisticsBean uppervoidStats;
    private ChunkWarsStatisticsBean chunkWarsStats;
    private TheDropperStatisticsBean theDropperStats;

    // Empty constructor for reflection creation
    public PlayerStatisticsBean()
    {
        super();
    }

    // Constructor
    public PlayerStatisticsBean(DimensionsStatisticsBean dimensionsStats,
                                JukeBoxStatisticsBean jukeBoxStats,
                                QuakeStatisticsBean quakeStats,
                                UHCOriginalStatisticsBean uhcOriginalStats,
                                UHCRunStatisticsBean uhcRunStats,
                                DoubleRunnerStatisticsBean doubleRunnerStats,
                                UHCRandomStatisticsBean uhcRandomStats,
                                RandomRunStatisticsBean randomRunStats,
                                UltraFlagKeeperStatisticsBean ultraFlagKeeperStats,
                                UppervoidStatisticsBean uppervoidStats,
                                ChunkWarsStatisticsBean chunkWarsStats,
                                TheDropperStatisticsBean theDropperStats)
    {
        this.dimensionsStats = dimensionsStats;
        this.jukeBoxStats = jukeBoxStats;
        this.quakeStats = quakeStats;
        this.uhcOriginalStats = uhcOriginalStats;
        this.uhcRunStats = uhcRunStats;
        this.doubleRunnerStats = doubleRunnerStats;
        this.uhcRandomStats = uhcRandomStats;
        this.randomRunStats = randomRunStats;
        this.ultraFlagKeeperStats = ultraFlagKeeperStats;
        this.uppervoidStats = uppervoidStats;
        this.chunkWarsStats = chunkWarsStats;
        this.theDropperStats = theDropperStats;
    }

    // Getters
    public DimensionsStatisticsBean getDimensionsStats() {
        return dimensionsStats;
    }
    public JukeBoxStatisticsBean getJukeBoxStats() {
        return jukeBoxStats;
    }
    public QuakeStatisticsBean getQuakeStats() {
        return quakeStats;
    }
    public UHCOriginalStatisticsBean getUHCOriginalStats() {
        return uhcOriginalStats;
    }
    public UHCRunStatisticsBean getUHCRunStats() {
        return uhcRunStats;
    }
    public DoubleRunnerStatisticsBean getDoubleRunnerStats() {
        return doubleRunnerStats;
    }
    public UHCRandomStatisticsBean getUHCRandomStats() {
        return uhcRandomStats;
    }
    public RandomRunStatisticsBean getRandomRunStats() {
        return randomRunStats;
    }
    public UltraFlagKeeperStatisticsBean getUltraFlagKeeperStats() {
        return ultraFlagKeeperStats;
    }
    public UppervoidStatisticsBean getUppervoidStats() {
        return uppervoidStats;
    }
    public ChunkWarsStatisticsBean getChunkWarsStats() {
        return chunkWarsStats;
    }
    public TheDropperStatisticsBean getTheDropperStats() {
        return theDropperStats;
    }

    // Setters
    public void setDimensionsStats(DimensionsStatisticsBean dimensionsStats) {
        this.dimensionsStats = dimensionsStats;
    }
    public void setJukeBoxStats(JukeBoxStatisticsBean jukeBoxStats) {
        this.jukeBoxStats = jukeBoxStats;
    }
    public void setQuakeStats(QuakeStatisticsBean quakeStats) {
        this.quakeStats = quakeStats;
    }
    public void setUHCOriginalStats(UHCOriginalStatisticsBean uhcOriginalStats) {
        this.uhcOriginalStats = uhcOriginalStats;
    }
    public void setUHCRunStats(UHCRunStatisticsBean uhcRunStats) {
        this.uhcRunStats = uhcRunStats;
    }
    public void setDoubleRunnerStats(DoubleRunnerStatisticsBean doubleRunnerStats) {
        this.doubleRunnerStats = doubleRunnerStats;
    }
    public void setUHCRandomStats(UHCRandomStatisticsBean uhcRandomStats) {
        this.uhcRandomStats = uhcRandomStats;
    }
    public void setRandomRunStats(RandomRunStatisticsBean randomRunStats) {
        this.randomRunStats = randomRunStats;
    }
    public void setUltraFlagKeeperStats(UltraFlagKeeperStatisticsBean ultraFlagKeeperStats) {
        this.ultraFlagKeeperStats = ultraFlagKeeperStats;
    }
    public void setUppervoidStats(UppervoidStatisticsBean uppervoidStats) {
        this.uppervoidStats = uppervoidStats;
    }
    public void setChunkWarsStats(ChunkWarsStatisticsBean chunkWarsStats) {
        this.chunkWarsStats = chunkWarsStats;
    }
    public void setTheDropperStats(TheDropperStatisticsBean theDropperStats) {
        this.theDropperStats = theDropperStats;
    }
}
