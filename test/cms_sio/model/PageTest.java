/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

import cms_sio.model.generic.HasId;
import cms_sio.model.generic.database.DBUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cabaud.enzo
 */
public class PageTest {

    public PageTest() {
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
     * Test of loadFromDB method, of class Page.
     */
    @Test
    public void testLoadFromDB() throws Exception {
        System.out.println("loadFromDB");
        Page page = new Page();
        page.id = 2323232;
        page.name = "test";
        page.content = "letesttavu";
        page.data = null;
        page.template = new Template();
        DBUtils.connect();
        DBUtils.insertRow(page);

        Page page_check = new Page(2323232);

        assertEquals(page_check.name, page.name);

    }

    /**
     * Test of save method, of class Page.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        Page instance = new Page();
        boolean expResult = false;
        boolean result = instance.save();
        assertEquals(expResult, result);

    }

    /**
     * Test of getId method, of class Page.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Page instance = new Page();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class Page.
     */
    @Test
    public void testSetId() throws Exception {
        System.out.println("setId");
        Page instance = new Page();
        instance.setId();

    }

}
