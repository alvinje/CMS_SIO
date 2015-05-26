/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

import cms_sio.model.generic.HasId;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import cms_sio.model.generic.database.DBUtils;

/**
 *
 * @author cabaud.enzo
 */
public class DataTest {

    public DataTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getId method, of class Data.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Data instance = new Data();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
    }

    /**
     * Test of loadFromDB method, of class Data.
     */
    @Test
    public void testLoadFromDB() throws Exception {
        System.out.println("loadFromDB");
        Data data = new Data();

        data.id = 6546;
        data.template = new Template();

        DBUtils.connect();
        DBUtils.insertRow(data);
        

        Data data_check = new Data(6546);

        assertEquals(data_check.template, data.template);
    }

    /**
     * Test of save method, of class Data.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Data instance = new Data();
        boolean expResult = false;
        boolean result = instance.save();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class Data.
     */
    @Test
    public void testSetId() throws Exception {
        System.out.println("setId");
        Data instance = new Data();
        instance.setId();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
