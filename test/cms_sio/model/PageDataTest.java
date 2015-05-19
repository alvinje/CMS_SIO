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

/**
 *
 * @author cabaud.enzo
 */
public class PageDataTest {
    
    public PageDataTest() {
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
     * Test of getId method, of class PageData.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        PageData instance = new PageData();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFromDB method, of class PageData.
     */
    @Test
    public void testLoadFromDB() throws Exception {
        System.out.println("loadFromDB");
        int id = 0;
        PageData instance = new PageData();
        HasId expResult = null;
        HasId result = instance.loadFromDB(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class PageData.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        PageData instance = new PageData();
        boolean expResult = false;
        boolean result = instance.save();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class PageData.
     */
    @Test
    public void testSetId() throws Exception {
        System.out.println("setId");
        PageData instance = new PageData();
        instance.setId();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPageDataElement method, of class PageData.
     */
    @Test
    public void testAddPageDataElement() {
        System.out.println("addPageDataElement");
        PageDataElement dataElement = null;
        PageData instance = new PageData();
        instance.addPageDataElement(dataElement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removePageDataElement method, of class PageData.
     */
    @Test
    public void testRemovePageDataElement() {
        System.out.println("removePageDataElement");
        PageDataElement dataElement = null;
        PageData instance = new PageData();
        instance.removePageDataElement(dataElement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearPageDataElement method, of class PageData.
     */
    @Test
    public void testClearPageDataElement() {
        System.out.println("clearPageDataElement");
        PageDataElement dataElement = null;
        PageData instance = new PageData();
        instance.clearPageDataElement(dataElement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buildDataElementsIds method, of class PageData.
     */
    @Test
    public void testBuildDataElementsIds() {
        System.out.println("buildDataElementsIds");
        PageData instance = new PageData();
        instance.buildDataElementsIds();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPageDataElement method, of class PageData.
     */
    @Test
    public void testGetPageDataElement() throws Exception {
        System.out.println("getPageDataElement");
        TemplateVariableElement templateVariableElement_ = null;
        PageData instance = new PageData();
        PageDataElement expResult = null;
        PageDataElement result = instance.getPageDataElement(templateVariableElement_);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
