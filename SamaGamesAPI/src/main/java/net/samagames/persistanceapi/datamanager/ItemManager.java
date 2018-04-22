package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.shop.ItemDescriptionBean;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
public class ItemManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get the item by ID
    public ItemDescriptionBean getItemDescription(int itemId, DataSource dataSource) throws Exception
    {
        // Get item
        try
        {
            // Defines
            ItemDescriptionBean itemDescription = null;

            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "select item_name, item_desc, price_coins, price_stars, game_category, item_minecraft_id, item_rarity, rank_accessibility";
            sql += " from item_description where item_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, itemId);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                String itemName = resultset.getString("item_name");
                String itemDesc = resultset.getString("item_desc");
                int priceCoins = resultset.getInt("price_coins");
                int priceStars = resultset.getInt("price_stars");
                int gameCategory = resultset.getInt("game_category");
                String itemMinecraftId = resultset.getString("item_minecraft_id");
                String itemRarity = resultset.getString("item_rarity");
                String rankAccessiblity = resultset.getString("rank_accessibility");
                itemDescription = new ItemDescriptionBean(itemId, itemName, itemDesc, priceCoins, priceStars, gameCategory, itemMinecraftId, itemRarity, rankAccessiblity);

            }
            return itemDescription;
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

    // Get all the item description
    public List<ItemDescriptionBean> getAllItemDescription(DataSource dataSource) throws Exception
    {
        // Get item
        try
        {
            // Defines
            ItemDescriptionBean itemDescription;

            // Set connection
            connection = dataSource.getConnection();
            List<ItemDescriptionBean> itemList = new ArrayList<>();

            // Query construction
            String sql = "select item_id, item_name, item_desc, price_coins, price_stars, game_category, item_minecraft_id, item_rarity, rank_accessibility";
            sql += " from item_description";

            statement = connection.prepareStatement(sql);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                int itemId = resultset.getInt("item_id");
                String itemName = resultset.getString("item_name");
                String itemDesc = resultset.getString("item_desc");
                int priceCoins = resultset.getInt("price_coins");
                int priceStars = resultset.getInt("price_stars");
                int gameCategory = resultset.getInt("game_category");
                String itemMinecraftId = resultset.getString("item_minecraft_id");
                String itemRarity = resultset.getString("item_rarity");
                String rankAccessiblity = resultset.getString("rank_accessibility");
                itemDescription = new ItemDescriptionBean(itemId, itemName, itemDesc, priceCoins, priceStars, gameCategory, itemMinecraftId, itemRarity, rankAccessiblity);
                itemList.add(itemDescription);
            }
            return itemList;
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
