/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CloudAerius
 */
public class AuthorService {

    private IAuthorDao dao;

    public IAuthorDao getDao() {
        return dao;
    }

    public void setDao(IAuthorDao dao) {
        this.dao = dao;
    }

    public AuthorService(IAuthorDao dao) {
        this.dao = dao;
    }

    public void addAuthor(String tableName, List<String> colNames, List colValues)
            throws ClassNotFoundException, SQLException {

        dao.addAuthorToDb(tableName, colNames, colValues);
    }

    public List<Author> getAuthors(String tableName, int maxRecords)
            throws ClassNotFoundException, SQLException {

        return dao.getAuthorDb(tableName, maxRecords);
    }

    public void updateAuthor(String tableName, List<String> colNames, List colValues, String colName, Object id)
            throws ClassNotFoundException, SQLException {

        dao.updateAuthorDb(tableName, colNames, colValues, colName, id);
    }

    public void deleteAuthor(String tableName, String colName, Object id)
            throws ClassNotFoundException, SQLException {

        dao.deleteAuthorFromDb(tableName, colName, id);
    }

//    public static void main(String[] args)
//            throws ClassNotFoundException, SQLException {
//
//        AuthorService as = new AuthorService(
//                new AuthorDao(new MySqlDbAccessor(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin"));
////                new AuthorDao(new MySqlDbAccessor(), "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/bookdb", "root", "admin"));
//
//        Date date = new Date();
//        List<String> colNames = new ArrayList<>();
//        colNames.add("author_name");
//        colNames.add("date_added");
//        List colValues = new ArrayList<>();
//        colValues.add("Yolandi Visser");
//        colValues.add(date);
//        
////        as.addAuthor("author",colNames, colValues);
////        as.updateAuthor("author", colNames, colValues, "author_id", 3);
////        as.deleteAuthor("author", "author_id", 7);
//        List<Author> authors = as.getAuthors("author", 50);
//
//        for (Author a : authors) {
//            System.out.println(a);
//        }
//    }
}
