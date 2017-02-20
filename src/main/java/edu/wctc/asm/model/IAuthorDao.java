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

    public abstract List<Author> getAuthorList(String tableName, int maxRecords)
            throws ClassNotFoundException, SQLException;

    public abstract DbAccessor getDb();

    public abstract String getDriverClass();

    public abstract String getPassword();

    public abstract String getUrl();

    public abstract String getUserName();

    public abstract void setDb(DbAccessor db);

    public abstract void setDriverClass(String driverClass);

    public abstract void setPassword(String password);

    public abstract void setUrl(String url);

    public abstract void setUserName(String userName);

}
