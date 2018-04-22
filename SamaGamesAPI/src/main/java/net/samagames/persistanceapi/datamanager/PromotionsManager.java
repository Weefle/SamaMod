package net.samagames.persistanceapi.datamanager;

import net.samagames.persistanceapi.beans.shop.PromotionsBean;
import javax.sql.DataSource;
import java.sql.*;
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
public class PromotionsManager
{
    // Defines
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultset = null;

    // Get all the promotions
    public List<PromotionsBean> getAllActivePromotions(DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<PromotionsBean> promotionList = new ArrayList<>();

            // Query construction
            String sql = "select promotion_id, type_id, game, multiplier, message, start_date, end_date from promotions where end_date > now()";

            statement = connection.prepareStatement(sql);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while(resultset.next())
            {
                // Manage the result in a list of bean
                long promitionId = resultset.getLong("promotion_id");
                int typePromotion = resultset.getInt("type_id");
                int game = resultset.getInt("game");
                int multiplier = resultset.getInt("multiplier");
                String message = resultset.getString("message");
                Timestamp startDate = resultset.getTimestamp("start_date");
                Timestamp endDate = resultset.getTimestamp("end_date");
                PromotionsBean promotionsBean = new PromotionsBean(promitionId, typePromotion, game, multiplier, message, startDate, endDate);
                promotionList.add(promotionsBean);
            }
            return promotionList;
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

    // Get a special promotions
    public List<PromotionsBean> getPromotion(DataSource dataSource, int typeId, int game) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();
            List<PromotionsBean> promotionList = new ArrayList<>();

            // Query construction
            String sql = "select promotion_id, type_id, game, multiplier, message, start_date, end_date from promotions where end_date > now()";
            sql += " and type_id = ? and game = ?";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, typeId);
            statement.setInt(2, game);

            // Execute the query
            resultset = statement.executeQuery();

            // Manage the result in a bean
            while(resultset.next())
            {
                // Manage the result in a list of bean
                long promotionId = resultset.getLong("promotion_id");
                int multiplier = resultset.getInt("multiplier");
                String message = resultset.getString("message");
                Timestamp startDate = resultset.getTimestamp("start_date");
                Timestamp endDate = resultset.getTimestamp("end_date");
                PromotionsBean promotionsBean = new PromotionsBean(promotionId, typeId, game, multiplier, message, startDate, endDate);
                promotionList.add(promotionsBean);
            }
            return promotionList;
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

    // Create a promotion
    public void createPromotion(PromotionsBean promotionsBean, DataSource dataSource) throws Exception
    {
        try {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "insert into promotions (type_id, game, multiplier, message, start_date, end_date) values (?, ?, ?, ?, ?, ?)";

            statement = connection.prepareStatement(sql);
            statement.setInt(1, promotionsBean.getPromotionType());
            statement.setInt(2, promotionsBean.getGame());
            statement.setInt(3, promotionsBean.getMultiplier());
            statement.setString(4, promotionsBean.getMessage());
            statement.setString(5, promotionsBean.getStartDate().toString());
            statement.setString(6, promotionsBean.getEndDate().toString());

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


    // Delete a promotion
    public void deletePromotion(PromotionsBean promotionsBean, DataSource dataSource) throws Exception
    {
        try
        {
            // Set connection
            connection = dataSource.getConnection();

            // Query construction
            String sql = "delete from promotions where promotion_id = ?";

            statement = connection.prepareStatement(sql);
            statement.setLong(1, promotionsBean.getPromotionId());

            // Execute the query
            statement.executeUpdate(sql);
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
