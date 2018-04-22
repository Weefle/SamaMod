package net.samagames.persistanceapi.beans.shop;

import java.beans.ConstructorProperties;
import java.sql.Timestamp;
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
public class TransactionBean
{
    /* Database structure

    Table : transaction_shop
    +------------------+------------+------+-----+---------------------+----------------+
    | Field            | Type       | Null | Key | Default             | Extra          |
    +------------------+------------+------+-----+---------------------+----------------+
    | transaction_id   | bigint(20) | NO   | PRI | NULL                | auto_increment |
    | item_id          | int(11)    | YES  | MUL | NULL                |                |
    | price_coins      | int(11)    | YES  |     | NULL                |                |
    | price_stars      | int(11)    | YES  |     | NULL                |                |
    | transaction_date | timestamp  | NO   |     | 0000-00-00 00:00:00 |                |
    | selected         | bit(1)     | NO   |     | NULL                |                |
    | uuid_buyer       | binary(16) | NO   |     | NULL                |                |
    +------------------+------------+------+-----+---------------------+----------------+
    */

    // Defines
    private long transactionId;
    private int itemId;
    private int priceCoins;
    private int priceStars;
    private Timestamp transactionDate;
    private boolean selected;
    private UUID uuidBuyer;

    // Constructor
    @ConstructorProperties({"transactionId", "itemId", "priceCoins", "priceStars", "transactionDate", "selected", "uuidBuyer"})
    public TransactionBean(long transactionId, int itemId, int priceCoins, int priceStars, Timestamp transactionDate, boolean selected, UUID uuidBuyer)
    {
        this.transactionId = transactionId;
        this.itemId = itemId;
        this.priceCoins = priceCoins;
        this.priceStars = priceStars;
        this.transactionDate = transactionDate;
        this.selected = selected;
        this.uuidBuyer = uuidBuyer;
    }

    // Getters
    public long getTransactionId() { return this.transactionId; }
    public int getItemId() { return this.itemId; }
    public int getPriceCoins() { return this.priceCoins; }
    public int getPriceStars() { return this.priceStars; }
    public Timestamp getTransactionDate() { return this.transactionDate; }
    public boolean isSelected() { return this.selected; }
    public UUID getUuidBuyer() { return this.uuidBuyer; }

    // Setters

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }
    public void setItemId(int itemId) { this.itemId = itemId; }
    public void setPriceCoins(int priceCoins) { this.priceCoins = priceCoins; }
    public void setPriceStars(int priceStars) { this.priceStars = priceStars; }
    public void setTransactionDate(Timestamp transactionDate) { this.transactionDate = transactionDate; }
    public void setSelected(boolean selected) { this.selected = selected; }
    public void setUuidBuyer(UUID uuidBuyer) { this.uuidBuyer = uuidBuyer; }
}
