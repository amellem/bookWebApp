/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aerius
 */
public interface DbAccessor {
// consider creating a custom exception

    public abstract void openConnection(String driverClass, String url, String userName, String password)
            throws ClassNotFoundException, SQLException;

    public abstract void closeConnection()
            throws SQLException;

    public abstract int createRecord(String tableName, List<String> colNames, List colValues)
            throws SQLException;

    public abstract List<Map<String, Object>> findRecordsFor(String tableName, int maxRecords)
            throws SQLException;

    public abstract int updateRecord(String tableName, List<String> colNames, List colValues, String colName, Object id)
            throws SQLException;

    public abstract int deleteRecordById(String tableName, String colName, Object id)
            throws SQLException;
}
