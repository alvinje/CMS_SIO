/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cms_sio.model.generic.database;

import cms_sio.model.generic.HasId;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alvin.jeremie
 */
public class DBUtilsTest {
    
    public DBUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        DBUtils.connect();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createAllTables method, of class DBUtils.
     */
    @Test
    public void testCreateAllTables() {
        System.out.println("createAllTables");
        DBUtils.createAllTables();
        assertEquals(this, this);
    }

    /**
     * Test of clearAllTables method, of class DBUtils.
     */
    @Test
    public void testClearAllTables() {
        System.out.println("clearAllTables");
        DBUtils.clearAllTables();
    }

    /**
     * Test of disconnect method, of class DBUtils.
     */
    @Test
    public void testDisconnect_0args() throws Exception {
        System.out.println("disconnect");
        DBUtils.disconnect();
        assertTrue(DBUtils.connection.isClosed());
           
              
        
    }

    /**
     * Test of getId method, of class DBUtils.
     */
    @Test
    public void testGetId() throws Exception {
        System.out.println("getId");
        Object hasId = null;
        int expResult = 0;
        int result = DBUtils.getId(hasId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disconnect method, of class DBUtils.
     */
    @Test
    public void testDisconnect_Statement_ResultSet() {
        System.out.println("disconnect");
        Statement stmt = null;
        ResultSet rs = null;
        DBUtils.disconnect(stmt, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dropTable method, of class DBUtils.
     */
    @Test
    public void testDropTable() {
        System.out.println("dropTable");
        Object hasId = null;
        boolean expResult = false;
        boolean result = DBUtils.dropTable(hasId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTable method, of class DBUtils.
     */
    @Test
    public void testCreateTable() {
        System.out.println("createTable");
        Object hasId = null;
        boolean expResult = false;
        boolean result = DBUtils.createTable(hasId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertRow method, of class DBUtils.
     */
    @Test
    public void testInsertRow() {
        System.out.println("insertRow");
        HasId hasId = null;
        boolean expResult = false;
        boolean result = DBUtils.insertRow(hasId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRow method, of class DBUtils.
     */
    @Test
    public void testUpdateRow() {
        System.out.println("updateRow");
        HasId hasId = null;
        boolean expResult = false;
        boolean result = DBUtils.updateRow(hasId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFromDB method, of class DBUtils.
     */
    @Test
    public void testLoadFromDB_HasId_Field() throws Exception {
        System.out.println("loadFromDB");
        HasId hasId = null;
        Field field = null;
        HasId expResult = null;
        HasId result = DBUtils.loadFromDB(hasId, field);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFromDB method, of class DBUtils.
     */
    @Test
    public void testLoadFromDB_HasId_int() throws Exception {
        System.out.println("loadFromDB");
        HasId hasId = null;
        int id = 0;
        boolean expResult = false;
        boolean result = DBUtils.loadFromDB(hasId, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHasId method, of class DBUtils.
     */
    @Test
    public void testSetHasId() throws Exception {
        System.out.println("setHasId");
        HasId hasId = null;
        ResultSet rs = null;
        DBUtils.setHasId(hasId, rs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isHasId method, of class DBUtils.
     */
    @Test
    public void testIsHasId() {
        System.out.println("isHasId");
        Field field = null;
        boolean expResult = false;
        boolean result = DBUtils.isHasId(field);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildChild method, of class DBUtils.
     */
    @Test
    public void testBuildChild() throws Exception {
        System.out.println("buildChild");
        HasId hasId = null;
        Field field = null;
        ResultSet rs = null;
        boolean expResult = false;
        boolean result = DBUtils.buildChild(hasId, field, rs);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDB method, of class DBUtils.
     */
    @Test
    public void testUpdateDB() {
        System.out.println("updateDB");
        HasId hasId = null;
        boolean expResult = false;
        boolean result = DBUtils.updateDB(hasId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class DBUtils.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        boolean expResult = false;
        boolean result = DBUtils.connect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDatabasePathFromFile method, of class DBUtils.
     */
    @Test
    public void testGetDatabasePathFromFile() {
        System.out.println("getDatabasePathFromFile");
        String expResult = "";
        String result = DBUtils.getDatabasePathFromFile();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkTableExists method, of class DBUtils.
     */
    @Test
    public void testCheckTableExists() {
        System.out.println("checkTableExists");
        Object object = null;
        boolean expResult = false;
        boolean result = DBUtils.checkTableExists(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRowExists method, of class DBUtils.
     */
    @Test
    public void testCheckRowExists() {
        System.out.println("checkRowExists");
        HasId hasId = null;
        boolean expResult = false;
        boolean result = DBUtils.checkRowExists(hasId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
