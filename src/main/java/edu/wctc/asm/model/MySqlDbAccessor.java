/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import javax.sql.DataSource;

/**
 *
 * @author Aerius
 */
public class MySqlDbAccessor implements DbAccessor {

    private Connection conn;
    private ResultSet rs;
    private Statement stmt;

    // consider creating a custom exception
    @Override
    public void openConnection(String driverClass, String url, String userName, String password)
            throws ClassNotFoundException, SQLException {

        // needs validation
        Class.forName(driverClass);
        conn = DriverManager.getConnection(url, userName, password);
    }
/**
     * Open a connection using a connection pool configured on server.
     *
     * @param ds - a reference to a connection pool via a JNDI name, producing
     * this object. Typically done in a servlet using InitalContext object.
     * @throws SQLException - if ds cannot be established
     */
    @Override
    public final void openConnection(DataSource ds) 
            throws SQLException {
        conn = ds.getConnection();
    }
    @Override
    public void closeConnection()
            throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Override
    public int createRecord(String tableName, List<String> colNames, List colValues)
            throws SQLException {
        // build String sql statement
        // validation
        // testing
        String sql = "INSERT INTO " + tableName + " ";
        StringJoiner sj = new StringJoiner(",", "(", ")");
        for (String col : colNames) {
            sj.add(col);
        }

        sql += sj.toString();
        sj = new StringJoiner(",", "(", ")");

        sql += " VALUES ";

        for (Object val : colValues) {
            sj.add("?");
        }
        sql += sj.toString();

        PreparedStatement pstm = conn.prepareStatement(sql);

        for (int i = 0; i < colValues.size(); i++) {
            pstm.setObject(i + 1, colValues.get(i));
        }

        return pstm.executeUpdate();
    }

    @Override
    public List<Map<String, Object>> findRecordsFor(String tableName, int maxRecords)
            throws SQLException {

        String sql = "";

        if (maxRecords > 0) {
            sql = "SELECT * FROM " + tableName + " LIMIT " + maxRecords;
        } else {
            sql = "SELECT * FROM " + tableName;
        }

        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);

        List<Map<String, Object>> results = new ArrayList<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        int colCount = rsmd.getColumnCount();
        Map<String, Object> record = null;

        while (rs.next()) {
            record = new LinkedHashMap<>();
            for (int colNo = 1; colNo <= colCount; colNo++) {
                String colName = rsmd.getColumnName(colNo);
                record.put(colName, rs.getObject(colNo));
            }
            results.add(record);
        }

        return results;
    }

    @Override
    public int updateRecord(String tableName, List<String> colNames, List colValues, String colName, Object id)
            throws SQLException {

        String sql = "UPDATE " + tableName + " SET ";

        StringJoiner sj = new StringJoiner(",");

        for (String col : colNames) {
            sj.add(col + "=?");
        }

        sql += sj.toString();

        sql += " WHERE " + colName + " = ";

        if (id instanceof String) {
            sql += id.toString();
        } else if (id instanceof Integer) {
            sql += (Integer) id;
        }

        PreparedStatement pstm = conn.prepareStatement(sql);

        for (int i = 0; i < colValues.size(); i++) {
            pstm.setObject(i + 1, colValues.get(i));
        }

        return pstm.executeUpdate();
    }

    @Override
    public int deleteRecordById(String tableName, String colName, Object id)
            throws SQLException {

        String sql = "DELETE FROM " + tableName + " WHERE " + colName + " = ";

        if (id instanceof String) {
            sql += id.toString();
        } else if (id instanceof Integer) {
            sql += (Integer) id;
        }

        stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }

    public static void main(String[] args) throws Exception {
        DbAccessor db = new MySqlDbAccessor();
        Date date = new Date();
        List<String> colNames = new ArrayList<>();
        colNames.add("author_name");
        colNames.add("date_added");
        List colValues = new ArrayList<>();
        colValues.add("Yolandi Visser");
        colValues.add(date);
        
        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book", "root", "admin");
//        db.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/bookdb", "root", "admin");

//        db.createRecord("author", colNames, colValues);
//        db.updateRecord("author", colNames, colValues, "author_id", 3);
//        db.deleteRecordById("author", "author_id", 5);
        List<Map<String, Object>> records = db.findRecordsFor("author", 50);

        
        db.closeConnection();

        for (Map<String, Object> record : records) {
            System.out.println(record);
        }

    }

}
