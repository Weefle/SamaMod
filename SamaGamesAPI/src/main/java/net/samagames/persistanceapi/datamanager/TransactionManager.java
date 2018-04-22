package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.players.PlayerBean;
import net.samagames.persistanceapi.beans.shop.TransactionBean;
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
public class TransactionManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get all the player transactions
    public List<TransactionBean> getPlayerTransactions(PlayerBean player, DataSource dataSource) throws Exception
    {
        // Get all transactions
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<TransactionBean> transactionList = new ArrayList<>();

            // Query construction
            String sql = "select transaction_id, item_id, price_coins, price_stars, transaction_date, selected, HEX(uuid_buyer) as buyer from transaction_shop";
            sql += " where uuid_buyer = UNHEX(?)";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                long transactionId = resultset.getLong("transaction_id");
                int item_id = resultset.getInt("item_id");
                int priceCoins = resultset.getInt("price_coins");
                int priceStars = resultset.getInt("price_stars");
                Timestamp transactionDate = resultset.getTimestamp("transaction_date");
                boolean selected = resultset.getBoolean("selected");
                String uuidBuyer = resultset.getString("buyer");
                UUID buyer = UUID.fromString(Transcoder.decode(uuidBuyer));
                TransactionBean transaction = new TransactionBean(transactionId, item_id, priceCoins, priceStars, transactionDate, selected, buyer);
                transactionList.add(transaction);
            }
            return transactionList;
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

    // Get all the selected player transactions
    public List<TransactionBean> getPlayerSelectedTransactions(PlayerBean player, DataSource dataSource) throws Exception
    {
        // Get all selected transactions
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<TransactionBean> transactionList = new ArrayList<>();

            // Query construction
            String sql = "select transaction_id, item_id, price_coins, price_stars, transaction_date, selected, (HEX(uuid_buyer)) as buyer from transaction_shop";
            sql += " where uuid_buyer = UNHEX(?) and selected = true";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                long transactionId = resultset.getLong("transaction_id");
                int item_id = resultset.getInt("item_id");
                int priceCoins = resultset.getInt("price_coins");
                int priceStars = resultset.getInt("price_stars");
                Timestamp transactionDate = resultset.getTimestamp("transaction_date");
                boolean selected = resultset.getBoolean("selected");
                String uuidBuyer = resultset.getString("buyer");
                UUID buyer = UUID.fromString(Transcoder.decode(uuidBuyer));
                TransactionBean transaction = new TransactionBean(transactionId, item_id, priceCoins, priceStars, transactionDate, selected, buyer);
                transactionList.add(transaction);
            }
            return transactionList;
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

    // Get all the selected player transactions for a game
    public List<TransactionBean> getPlayerGameSelectedTransactions(PlayerBean player, DataSource dataSource, int selectedGame) throws Exception
    {
        // Get all selected transactions for a game
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<TransactionBean> transactionList = new ArrayList<>();

            // Query construction
            String sql = "select transaction_shop.transaction_id, transaction_shop.item_id, transaction_shop.price_coins, transaction_shop.price_stars, transaction_date, selected, HEX(uuid_buyer) as buyer";
            sql += " from transaction_shop, item_description";
            sql += " where uuid_buyer = UNHEX(?) and selected = true and item_description.item_id = transaction_shop.item_id and item_description.game_category = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setInt(2, selectedGame);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                long transactionId = resultset.getLong("transaction_id");
                int item_id = resultset.getInt("item_id");
                int priceCoins = resultset.getInt("price_coins");
                int priceStars = resultset.getInt("price_stars");
                Timestamp transactionDate = resultset.getTimestamp("transaction_date");
                boolean selected = resultset.getBoolean("selected");
                String uuidBuyer = resultset.getString("buyer");
                UUID buyer = UUID.fromString(Transcoder.decode(uuidBuyer));
                TransactionBean transaction = new TransactionBean(transactionId, item_id, priceCoins, priceStars, transactionDate, selected, buyer);
                transactionList.add(transaction);
            }
            return transactionList;
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


    // Get all the player transactions for a selected game
    public List<TransactionBean> getPlayerGameTransactions(PlayerBean player, DataSource dataSource, int selectedGame) throws Exception
    {
        // Get all selected transactions for a game
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<TransactionBean> transactionList = new ArrayList<>();

            // Query construction
            String sql = "select transaction_shop.transaction_id, transaction_shop.item_id, transaction_shop.price_coins, transaction_shop.price_stars, transaction_date, selected, (HEX(uuid_buyer)) as buyer";
            sql += " from transaction_shop, item_description";
            sql += " where uuid_buyer = UNHEX(?) and item_description.item_id = transaction_shop.item_id and item_description.game_category = ?";

            statement = connection.prepareStatement(sql);
            statement.setString(1, Transcoder.encode(player.getUuid().toString()));
            statement.setInt(2, selectedGame);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while (resultset.next())
            {
                // There's a result
                long transactionId = resultset.getLong("transaction_id");
                int item_id = resultset.getInt("item_id");
                int priceCoins = resultset.getInt("price_coins");
                int priceStars = resultset.getInt("price_stars");
                Timestamp transactionDate = resultset.getTimestamp("transaction_date");
                boolean selected = resultset.getBoolean("selected");
                String uuidBuyer = resultset.getString("buyer");
                UUID buyer = UUID.fromString(Transcoder.decode(uuidBuyer));
                TransactionBean transaction = new TransactionBean(transactionId, item_id, priceCoins, priceStars, transactionDate, selected, buyer);
                transactionList.add(transaction);
            }
            return transactionList;
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


    // Create transaction
    public void createTransaction(PlayerBean player, DataSource dataSource, TransactionBean transaction) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "insert into transaction_shop (item_id, price_coins, price_stars, transaction_date, selected, uuid_buyer) values(?, ?, ?, now(), ?, UNHEX(?))";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, transaction.getItemId());
            statement.setInt(2, transaction.getPriceCoins());
            statement.setInt(3, transaction.getPriceStars());
            statement.setBoolean(4, transaction.isSelected());
            statement.setString(5, Transcoder.encode(player.getUuid().toString()));

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

    // Update a transaction
    public void updateTransaction(TransactionBean transaction, DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "update transaction_shop set price_coins = ?, price_stars = ?, transaction_date = ?, selected = ?, uuid_buyer = UNHEX(?) where transaction_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, transaction.getPriceCoins());
            statement.setInt(2, transaction.getPriceStars());
            statement.setString(3, transaction.getTransactionDate().toString());
            statement.setBoolean(4, transaction.isSelected());
            statement.setString(5, Transcoder.encode(transaction.getUuidBuyer().toString()));
            statement.setLong(6, transaction.getTransactionId());

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
