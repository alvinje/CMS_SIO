/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model.generic.database;

import cms_sio.model.TemplateConfiguration;
import cms_sio.model.PageData;
import cms_sio.model.PageDataElement;
import cms_sio.model.Page;
import cms_sio.model.ApplicationSetting;
import cms_sio.model.Template;
import cms_sio.model.TemplateVariableElement;
import cms_sio.model.generic.HasId;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sgoyet
 */
public class DBUtils {

    /**
     *
     */
    public static Connection connection;
    static HasId[] tablesObjects = new HasId[]{
        new TemplateConfiguration(),
        new PageData(),
        new PageDataElement(),
        new Page(),
        new ApplicationSetting(),
        new Template(),
        new TemplateVariableElement()};

    /**
     *
     */
    public static void createAllTables() {
        connect();
        for (HasId hasId : tablesObjects) {

            if (!checkTableExists(hasId)) {
                createTable(hasId);
            }
        }
    }
    /*
     * 
     * Suppression de toutes les tables et re création de celles-ci
     */

    /**
     *
     */
    public static void clearAllTables() {
        connect();
        for (HasId hasId : tablesObjects) {
            dropTable(hasId);
            if (!checkTableExists(hasId)) {
                createTable(hasId);
            }
        }
    }

    /**
     *
     * @throws SQLException
     */
    public static void disconnect() throws SQLException {
        connection.close();
    }

    /**
     *
     * @param hasId
     * @return
     * @throws SQLException
     */
    public static int getId(Object hasId) throws SQLException {

        int result;
        Statement stmt = null;
        String sql = "";
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            sql = "SELECT  MAX(id) FROM  " + hasId.getClass().getSimpleName();
            Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, "get new id " + sql);
            rs = stmt.executeQuery(sql);

            if (rs != null && rs.next()) {
                Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, "found id " + rs.getInt(1));
                result = rs.getInt(1) + 1;
            } else {
                Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, "get new id : not result so set to 1");
                result = 1;
            }
            Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, "id is " + result);

        } catch (SQLException ex) {
            Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, "Exception in getID " + ex.getMessage());
            result = 1;
        } finally {
            disconnect(stmt, rs);
        }
        return result;
    }

    static void disconnect(Statement stmt, ResultSet rs) {
        try {
            stmt.close();
        } catch (Exception e) { /* ignored */ }
        try {
            rs.close();
        } catch (Exception e) { /* ignored */ }
    }

    /**
     * Supprime une table identifiée par un objet hasId
     * @param hasId
     * @return
     */
    public static boolean dropTable(Object hasId) {
        String sql = "";
        Statement stmt = null;
        boolean result;
        try {
            stmt = connection.createStatement();
            sql = "DROP TABLE  " + hasId.getClass().getSimpleName() + " ;";
            stmt.executeUpdate(sql);
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(PageDataElement.class.getName()).log(Level.SEVERE, sql + ex.getMessage());
            result = false;
        } finally {
            disconnect(stmt, null);
        }

        return result;
    }

    /**
     * Crée une table identifiée par un objet hasId
     * @param hasId
     * @return
     */
    public static boolean createTable(Object hasId) {
        String sql = "";
        boolean result;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            sql = "CREATE TABLE IF NOT EXISTS " + hasId.getClass().getSimpleName() + " ";
            sql += "(ID INT PRIMARY KEY     NOT NULL";
            for (Field field : hasId.getClass().getFields()) {
                String name = field.getName();

                if (name.equals("id") || name.equals("connection")) {
                    continue;
                }
                if (field.getType().isAssignableFrom(String.class)) {
                    sql += ", " + name + " TEXT    NOT NULL ";
                } else if (field.getType().isAssignableFrom(int.class)) {
                    sql += " " + name + " INT    NOT NULL ";
                } else if (field.getType().isAssignableFrom(float.class)) {
                    sql += ", " + name + " REAL    NOT NULL ";
                } else if (field.getType().isAssignableFrom(double.class)) {
                    sql += ", " + name + " REAL    NOT NULL ";
                } else if (isHasId(field)) {
                    sql += ", " + name + "_id" + " INT    NOT NULL ";
                }
            }
            sql += ");";

            stmt.executeUpdate(sql);
            result = true;
        } catch (SQLException ex) {
            Logger.getLogger(PageDataElement.class.getName()).log(Level.SEVERE, sql + ex.getMessage());
            result = false;
        } finally {
            disconnect(stmt, null);
        }
        return result;
    }

    /**
     * Insert une ligne identifiée par un objet hasId
     * @param hasId
     * @return
     */
    public static boolean insertRow(HasId hasId) {
        String sql = "";
        boolean result;
        Statement stmt = null;
        try {

            if (hasId.getId() == 0) {
                hasId.setId();
            }

            stmt = connection.createStatement();
            sql = "INSERT INTO " + hasId.getClass().getSimpleName() + " VALUES (" + hasId.getId();
            for (Field field : hasId.getClass().getFields()) {
                String name = field.getName();

                if (name.equals("id") || name.equals("connection")) {
                    continue;
                }

                if (field.getType().isAssignableFrom(String.class)) {
                    sql += " , '" + (String) field.get(hasId) + "'";

                } else if (field.getType().isAssignableFrom(int.class)) {
                    sql += ", " + (int) field.get(hasId) + "";
                } else if (field.getType().isAssignableFrom(float.class)) {
                    sql += ", " + (float) field.get(hasId) + "";
                } else if (field.getType().isAssignableFrom(double.class)) {
                    sql += ", " + (double) field.get(hasId) + "";
                } else if (isHasId(field)) {
                    sql += ", " + ((HasId) field.get(hasId)).getId() + "";
                }
            }
            sql += ");";

            Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, sql);

            stmt.executeUpdate(sql);

            result = true;
        } catch (IllegalAccessException ex) {

            Logger.getLogger(PageDataElement.class.getName()).log(Level.SEVERE, ex.getMessage() + "on " + sql);
            result = false;
        } catch (SQLException ex) {
            Logger.getLogger(PageDataElement.class.getName()).log(Level.SEVERE, sql);
            Logger.getLogger(PageDataElement.class.getName()).log(Level.SEVERE, ex.getMessage() + "on " + sql);
            result = false;
        } finally {
            disconnect(stmt, null);
        }
        return result;
    }

    /**
     * Mets à jour une ligne
     * @param hasId
     * @return
     */
    public static boolean updateRow(HasId hasId) {
        boolean result;
        Statement stmt = null;
        String sql = "";
        try {
            stmt = connection.createStatement();
            sql = "UPDATE  " + hasId.getClass().getSimpleName() + " SET id =" + hasId.getId() + "  ";
            for (Field field : hasId.getClass().getFields()) {
                String name = field.getName();

                if (name.equals("id") || name.equals("connection")) {
                    continue;
                }
                if (field.getType().isAssignableFrom(String.class)) {
                    sql += ", " + name + " = '" + (String) field.get(hasId) + "' ";
                } else if (field.getType().isAssignableFrom(int.class)) {
                    sql += ", " + name + " = " + (int) field.get(hasId) + " ";
                } else if (field.getType().isAssignableFrom(float.class)) {
                    sql += ", " + name + " = " + (float) field.get(hasId) + " ";
                } else if (field.getType().isAssignableFrom(double.class)) {
                    sql += ", " + name + " = " + (double) field.get(hasId) + " ";
                } else if (isHasId(field)) {
                    sql += ", " + name + "_id " + "= " + ((HasId) field.get(hasId)).getId() + " ";
                } else {
                    Logger.getLogger(PageDataElement.class.getName()).log(Level.SEVERE, name + " type not found");
                }
            }
            sql += " WHERE ID = " + hasId.getId();
            Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, "Update " + sql);
            result = (stmt.executeUpdate(sql) == 1);

        } catch (IllegalAccessException ex) {
            Logger.getLogger(PageDataElement.class.getName()).log(Level.SEVERE, sql + ex.getMessage());
            result = false;
        } catch (SQLException ex) {
            Logger.getLogger(PageDataElement.class.getName()).log(Level.SEVERE, sql + ex.getMessage());
            result = false;
        } finally {
            disconnect(stmt, null);
        }
        return result;
    }

    /**
     * Charge des données depuis la base de donnée
     * @param hasId
     * @param field
     * @return
     * @throws Exception
     */
    public static HasId loadFromDB(HasId hasId, Field field) throws Exception {
        boolean result = false;
        Statement stmt = null;
        String sql;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            sql = "SELECT  * from " + hasId.getClass().getSimpleName() + " where " + field.getName() + "='" + (String) field.get(hasId) + "'";
            Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, sql);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                setHasId(hasId, rs);
            }
        } catch (IllegalAccessException ex) {
            throw (ex);

        } catch (SQLException ex) {
            throw (ex);

        } finally {
            disconnect(stmt, rs);
        }
        return hasId;
    }

    /**
     * La méthode vérifie la présence d'un objet hasId et de son Id dans la DB et met
     *  à jour l'objet hasId avec les informations de la DB.
     * @param hasId
     * @param id
     * @return
     * @throws Exception
     */
    public static boolean loadFromDB(HasId hasId, int id) throws Exception {
        boolean result = false;
        Statement stmt = null;
        String sql;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            sql = "SELECT  * from " + hasId.getClass().getSimpleName() + " where ID='" + id + "'";
            Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, sql);
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                setHasId(hasId, rs);

                result = true;
            } else {
                Logger.getLogger(HasId.class.getName()).log(Level.INFO, "No row found ");
                throw (new SQLException());
            }

        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBUtils.class.getName()).log(Level.INFO, null, "Illegal" + ex);
            throw (ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(HasId.class.getName()).log(Level.INFO, null, "Instanciation " + ex);
            throw (ex);
        } catch (SQLException ex) {
            Logger.getLogger(HasId.class.getName()).log(Level.INFO, null, "SQL " + ex);
            throw (ex);

        } finally {
            disconnect(stmt, rs);
        }

        return result;

    }

    static void setHasId(HasId hasId, ResultSet rs) throws IllegalAccessException, SQLException, InstantiationException, Exception {
        for (Field field : hasId.getClass().getFields()) {

            String name = field.getName();
            if (field.getType().isAssignableFrom(String.class)) {
                Logger.getLogger(HasId.class.getName()).log(Level.INFO, "Set " + name + " " + rs.getString(name));
                field.set(hasId, rs.getString(name));
            } else if (field.getType().isAssignableFrom(int.class)) {
                Logger.getLogger(HasId.class.getName()).log(Level.INFO, "Set " + name + " " + rs.getString(name));
                field.set(hasId, rs.getInt(name));
            } else if (field.getType().isAssignableFrom(float.class)) {
                Logger.getLogger(HasId.class.getName()).log(Level.INFO, "Set " + name + " " + rs.getString(name));
                field.set(hasId, rs.getFloat(name));
            } else if (field.getType().isAssignableFrom(double.class)) {
                Logger.getLogger(HasId.class.getName()).log(Level.INFO, "Set " + name + " " + rs.getString(name));
                field.set(hasId, rs.getDouble(name));
            } else if (isHasId(field)) {
                Logger.getLogger(DBUtils.class.getName()).log(Level.WARNING, "**** Found hasChild  ");
                if (!buildChild(hasId, field, rs)) {
                    Logger.getLogger(DBUtils.class.getName()).log(Level.WARNING, "**** Was not able to build child for  ");
                }
            } else {
                Logger.getLogger(DBUtils.class.getName()).log(Level.WARNING, "**** Cannot set anything for  " + name + " " + field.getType().getName());

            }
        }
    }

    static boolean isHasId(Field field) {
        Type[] interfaces = field.getType().getInterfaces();
        boolean found = false;
        if (interfaces.length != 0) {
            for (Type val : interfaces) {

                if (val.toString().equals("interface cms_sio.model.generic.HasId")) {
                    Logger.getLogger(DBUtils.class.getName()).log(Level.WARNING, "****** " + field + " interface of  " + val.toString());
                    return true;
                }
            }
        }
        return false;
    }

    static boolean buildChild(HasId hasId, Field field, ResultSet rs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SQLException {
        boolean found = false;
        Constructor[] allConstructors = field.getType().getDeclaredConstructors();
        for (Constructor ctor : allConstructors) {
            Class<?>[] pType = ctor.getParameterTypes();
            if (pType.length == 1) {
                if (pType[0].isPrimitive()) {
                    Logger.getLogger(DBUtils.class.getName()).log(Level.WARNING, "****** Build child  ");
                    Object child = ctor.newInstance(new Object[]{rs.getInt(field.getName() + "_id")});
                    field.set(hasId, child);
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Met à jour la base de donnée en fonction des informations de l'objet hasId
     * @param hasId
     * @return
     */
    public static boolean updateDB(HasId hasId) {

        boolean row_exists = checkRowExists(hasId);
        if (!row_exists) {

            return insertRow(hasId);
        } else {

            return updateRow(hasId);
        }

    }

    /**
     * Connecte à la base de donnée à l'aide d'un chemin d'accès trouvé dans un fichier (getDatabasePathFromFile)
     * @return
     */
    public static boolean connect() {
        Logger.getLogger("BDUtils").log(Level.INFO, "a");
        String databasePath = getDatabasePathFromFile();
        Logger.getLogger("BDUtils").log(Level.INFO, "b");
        if (databasePath!=null) {
            Logger.getLogger("BDUtils").log(Level.INFO, "database != null");
            try{
                Logger.getLogger("BDUtils").log(Level.INFO, "make connection");
                connection = DriverManager.getConnection("jdbc:sqlite:"+databasePath);
                Logger.getLogger("BDUtils").log(Level.INFO, "connection made");
                return true;
            }catch(SQLException ex){
                Logger.getLogger(HasId.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }                        
        } else {
            return false;
        }
    }

    /**
     *  Récupère le chemin d'accès a la base de donnée
     * @return
     */
    public static String getDatabasePathFromFile() {
        Logger.getLogger("Setting").log(Level.INFO, "a1");
        try {
            File databasePathFile = new File("databasePath.txt");
            Logger.getLogger("Setting").log(Level.INFO, "Read in file:" + databasePathFile.getCanonicalPath());
            BufferedReader buffer = new BufferedReader(new FileReader(databasePathFile));
            Logger.getLogger("Setting").log(Level.INFO, "a3");
            return buffer.readLine();

        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Vérifie l'existence des tables
     * @param object
     * @return
     */
    public static boolean checkTableExists(Object object) {

        boolean result = false;
        Statement stmt = null;

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='" + object.getClass().getSimpleName() + "'");
            result = rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(PageDataElement.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } finally {
            disconnect(stmt, null);
        }

        return result;
    }

    /**
     * Vérifie l'existance des lignes
     * @param hasId
     * @return
     */
    public static boolean checkRowExists(HasId hasId) {
        boolean result = false;
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "";

        try {
            stmt = connection.createStatement();
            sql = "SELECT id from " + hasId.getClass().getSimpleName() + " where id=" + hasId.getId() + ";";
            Logger.getLogger(PageDataElement.class.getName()).log(Level.INFO, sql);
            rs = stmt.executeQuery(sql);
            result = rs.next();
        } catch (SQLException ex) {

            Logger.getLogger(PageDataElement.class.getName()).log(Level.WARNING, sql + ex);
            result = false;
        } finally {
            disconnect(stmt, rs);
        }

        return result;
    }

}
