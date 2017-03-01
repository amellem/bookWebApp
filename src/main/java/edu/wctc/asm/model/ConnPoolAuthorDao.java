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

    public static void main(String[] args) throws Exception {
//        IAuthorDao dao = new ConnPoolAuthorDao(new MySqlDbAccessor(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//       
//        Date date = new Date();
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//        List colValues = new ArrayList<>();
//        colValues.add("Yolandi Visser");
//        colValues.add(date);
//        
////        dao.addAuthorToDb("author",colNames, colValues);
////        dao.updateAuthorDb("author", colNames, colValues, "author_id", 3);
////        dao.deleteAuthorFromDb("author", "author_id", 6);
//        List<Author> authors = dao.getAuthorDb("author", 50);
//
//        for (Author a : authors) {
//            System.out.println(a);
//        }
    }

}
