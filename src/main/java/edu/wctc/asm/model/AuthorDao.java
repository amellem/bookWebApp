/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aerius
 */
public class AuthorDao implements IAuthorDao {

    private DbAccessor db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;

    public AuthorDao(DbAccessor db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
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

        db.openConnection(driverClass, url, userName, password);
        db.createRecord(tableName, colNames, colValues);
        db.closeConnection();
    }

    @Override
    public List<Author> getAuthorDb(String tableName, int maxRecords)
            throws ClassNotFoundException, SQLException {

        List<Author> authorList = new ArrayList<>();
        db.openConnection(driverClass, url, userName, password);

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

        db.openConnection(driverClass, url, userName, password);
        db.updateRecord(tableName, colNames, colValues, colName, id);
        db.closeConnection();
    }

    @Override
    public void deleteAuthorFromDb(String tableName, String colName, Object id)
            throws ClassNotFoundException, SQLException {

        db.openConnection(driverClass, url, userName, password);
        db.deleteRecordById(tableName, colName, id);
        db.closeConnection();
    }

    @Override
    public String getDriverClass() {
        return driverClass;
    }

    @Override
    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public DbAccessor getDb() {
        return db;
    }

    @Override
    public void setDb(DbAccessor db) {
        this.db = db;
    }

    public static void main(String[] args) throws Exception {
        IAuthorDao dao = new AuthorDao(new MySqlDbAccessor(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");

        List<Author> authors = dao.getAuthorDb("author", 50);

        for (Author a : authors) {
            System.out.println(a);
        }
    }

}
