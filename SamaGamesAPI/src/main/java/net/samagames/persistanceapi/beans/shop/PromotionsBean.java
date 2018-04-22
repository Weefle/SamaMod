package net.samagames.persistanceapi.beans.shop;

import java.sql.Timestamp;

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
public class PromotionsBean
{
    /* Database structure

    Table : promotions
    +--------------+--------------+------+-----+---------+----------------+
    | Field        | Type         | Null | Key | Default | Extra          |
    +--------------+--------------+------+-----+---------+----------------+
    | promotion_id | bigint(20)   | NO   | PRI | NULL    | auto_increment |
    | type_id      | tinyint(4)   | NO   |     | NULL    |                |
    | game         | int(11)      | NO   |     | NULL    |                |
    | multiplier   | int(11)      | NO   |     | NULL    |                |
    | message      | varchar(255) | NO   |     | NULL    |                |
    | start_date   | timestamp    | YES  |     | NULL    |                |
    | end_date     | timestamp    | YES  |     | NULL    |                |
    +--------------+--------------+------+-----+---------+----------------+
    */

    // Defines promotions types // TODO make enum
    public static int GLOBAL = 0;
    public static int COINS = 1;

    // Defines games types // TODO make enum
    //public static int GLOBAL = 0; // TODO add in enum
    public static int HEROBATTLE = 1;
    public static int JUKEBOX = 2;
    public static int QUAKE = 3;
    public static int UHCRUN = 4;
    public static int UPPERVOID = 5;
    public static int DIMENSIONS = 6;

    // Defines
    private long promotionId;
    private int promotionType;
    private int game;
    private int multiplier;
    private String message;
    private Timestamp startDate;
    private Timestamp endDate;

    // Constructor
    public PromotionsBean(long promotionId, int promotionType, int game, int multiplier, String message, Timestamp startDate, Timestamp endDate)
    {
        this.promotionId = promotionId;
        this.promotionType = promotionType;
        this.game = game;
        this.multiplier = multiplier;
        this.message = message;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Constructor without id
    public PromotionsBean(int promotionType, int game, int multiplier, String message, Timestamp startDate, Timestamp endDate)
    {
        this.promotionType = promotionType;
        this.game = game;
        this.multiplier = multiplier;
        this.message = message;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters
    public long getPromotionId() { return this.promotionId; }
    public int getPromotionType() { return this.promotionType; }
    public int getGame() { return this.game; }
    public int getMultiplier() { return this.multiplier; }
    public String getMessage() { return this.message; }
    public Timestamp getStartDate() { return this.startDate; }
    public Timestamp getEndDate() { return this.endDate; }

    // Setters
    public void setPromotionId(long promotionId) { this.promotionId = promotionId; }
    public void setPromotionType(int promotionType) { this.promotionType = promotionType; }
    public void setGame(int game) { this.game = game; }
    public void setMultiplier(int multiplier) { this.multiplier = multiplier; }
    public void setMessage(String message) { this.message = message; }
    public void setStartDate(Timestamp startDate) { this.startDate = startDate; }
    public void setEndDate(Timestamp endDate) { this.endDate = endDate; }
}
