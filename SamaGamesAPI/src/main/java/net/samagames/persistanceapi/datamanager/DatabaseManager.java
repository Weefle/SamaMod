package net.samagames.persistanceapi.datamanager;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

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
public class DatabaseManager
{
    // Defines attributes
    public static volatile DatabaseManager instance = null;
    public DataSource dataSource = null;
    private String url;
    private String name;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;

    // Default constructor
    public DatabaseManager(String url, String name, String password, int minPoolSize, int maxPoolSize)
    {
        // Super constructor
        super();
        this.url = url;
        this.name = name;
        this.password = password;
        this.minPoolSize = minPoolSize;
        this.maxPoolSize = maxPoolSize;
        this.setupDataSource();
    }

    // Singleton generator
    public final static DatabaseManager getInstance(String url, String name, String password, int minPoolSize, int maxPoolSize)
    {
        if (DatabaseManager.instance == null)
        {
            synchronized(DatabaseManager.class)
            {
                if (DatabaseManager.instance == null)
                {
                    DatabaseManager.instance = new DatabaseManager(url, name, password, minPoolSize, maxPoolSize);
                }
            }
        }
        return DatabaseManager.instance;
    }

    // Initialize the data source
    public void setupDataSource()
    {
        // Set a JDBC/MySQL connection
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(this.url);
        dataSource.setUsername(this.name);
        dataSource.setPassword(this.password);
        dataSource.setInitialSize(this.minPoolSize);
        dataSource.setMaxTotal(this.maxPoolSize);
        this.dataSource = dataSource;
    }

    public DataSource getDataSource()
    {
        // Return the datasource
        //this.getSourcesStats(this.dataSource); // Fixme remove this trace
        return this.dataSource;
    }

    // Get the data sources stats
    public void getSourcesStats(DataSource dataSource)
    {
        BasicDataSource basicDataSource = (BasicDataSource) dataSource;
        System.out.println("Number of active: " + basicDataSource.getNumActive());
        System.out.println("Number of idle: " + basicDataSource.getNumIdle());
        System.out.println("================================================================================");
    }

    // Shutdown the data source
    public void shutdownDataSource(DataSource dataSource) throws Exception
    {
        BasicDataSource basicDataSource = (BasicDataSource) dataSource;
        basicDataSource.close();
    }
}
