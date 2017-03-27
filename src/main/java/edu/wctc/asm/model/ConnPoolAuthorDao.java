/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.model;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import javax.naming.spi.InitialContextFactoryBuilder;
import javax.naming.spi.NamingManager;
import javax.sql.DataSource;

/**
 *
 * @author Aerius
 */
public class ConnPoolAuthorDao implements IAuthorDao {

    private final DataSource ds;
    private DbAccessor db;
    

    public ConnPoolAuthorDao(DataSource ds, DbAccessor db) {
        this.ds = ds;
        this.db = db;
    }

    /**
     *
     * @param tableName
     * @param colNames
     * @param colValues
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    @Override
    public void addAuthorToDb(String tableName, List<String> colNames, List colValues)
            throws ClassNotFoundException, SQLException {

        db.openConnection(ds);
        db.createRecord(tableName, colNames, colValues);
        db.closeConnection();
    }

    @Override
    public List<Author> getAuthorDb(String tableName, int maxRecords)
            throws ClassNotFoundException, SQLException {

        List<Author> authorList = new ArrayList<>();
        db.openConnection(ds);

        List<Map<String, Object>> rawData = db.findRecordsFor(tableName, maxRecords);
        db.closeConnection();

        for (Map<String, Object> recData : rawData) {
            Author author = new Author();
            Object objectAuthorId = recData.get("author_id");
            author.setAuthorId((Integer) objectAuthorId);
            // or
            // author.setauthorId((Integer)recData.get("author_id"));

            Object objName = recData.get("author_name");
            String name = objName != null ? objName.toString() : "";
            author.setAuthorName(name);
            Object objDate = recData.get("date_added");
            Date dateAdded = objDate != null ? (Date) objDate : null;
            author.setDateAdded(dateAdded);

            authorList.add(author);
        }
        return authorList;
    }

    @Override
    public void updateAuthorDb(String tableName, List<String> colNames, List colValues, String colName, Object id)
            throws ClassNotFoundException, SQLException {

        db.openConnection(ds);
        db.updateRecord(tableName, colNames, colValues, colName, id);
        db.closeConnection();
    }

    @Override
    public void deleteAuthorFromDb(String tableName, String colName, Object id)
            throws ClassNotFoundException, SQLException {

        db.openConnection(ds);
        db.deleteRecordById(tableName, colName, id);
        db.closeConnection();
    }

    @Override
    public DbAccessor getDb() {
        return db;
    }

 
    public void setDb(DbAccessor db) {
        this.db = db;
    }

    // Test harness - not used in production
    // Uses a ad-hoc connection pool and DataSource object to test the code
    public static void main(String[] args) throws Exception {
        
        // Sets up the connection pool and assigns it a JNDI name
        NamingManager.setInitialContextFactoryBuilder(new InitialContextFactoryBuilder() {

            @Override
            public InitialContextFactory createInitialContextFactory(Hashtable<?, ?> environment) throws NamingException {
                return new InitialContextFactory() {

                    @Override
                    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
                        return new InitialContext(){

                            private final Hashtable<String, DataSource> dataSources = new Hashtable<>();

                            @Override
                            public Object lookup(String name) throws NamingException {

                                if (dataSources.isEmpty()) { //init datasources
                                    MysqlConnectionPoolDataSource ds = new MysqlConnectionPoolDataSource();
                                    ds.setURL("jdbc:mysql://localhost:3306/book");
                                    ds.setUser("root");
                                    ds.setPassword("admin");
                                    // Association a JNDI name with the DataSource for our Database
                                    dataSources.put("jdbc/book", ds);

                                    //add more datasources to the list as necessary
                                }

                                if (dataSources.containsKey(name)) {
                                    return dataSources.get(name);
                                }

                                throw new NamingException("Unable to find datasource: "+name);
                            }
                        };
                    }

                };
            }

        });
        
        // Find the connection pool and create the DataSource     
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("jdbc/book");

        IAuthorDao dao = new ConnPoolAuthorDao(ds, new MySqlDbAccessor());

        List<Author> authors = dao.getAuthorDb("author", 50);
        for (Author a : authors) {
            System.out.println(a);
        }
    }
    }


