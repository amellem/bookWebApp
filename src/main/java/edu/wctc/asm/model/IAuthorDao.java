/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Aerius
 */
public interface IAuthorDao {

    public abstract void addAuthorToDb(String tableName, List<String> colNames, List colValues)
            throws ClassNotFoundException, SQLException;

    public abstract List<Author> getAuthorDb(String tableName, int maxRecords)
            throws ClassNotFoundException, SQLException;

    public abstract void updateAuthorDb(String tableName, List<String> colNames, List colValues, String colName, Object id)
            throws ClassNotFoundException, SQLException;

    public abstract void deleteAuthorFromDb(String tableName, String colName, Object id)
            throws ClassNotFoundException, SQLException;

    public abstract DbAccessor getDb();

    

}
