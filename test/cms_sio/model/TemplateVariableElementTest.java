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
public class TemplateVariableElementTest {
    
    public TemplateVariableElementTest() {
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
     * Test of loadFromDB method, of class TemplateVariableElement.
     */
    @Test
    public void testLoadFromDB() throws Exception {
        System.out.println("loadFromDB");
        int id = 0;
        TemplateVariableElement instance = new TemplateVariableElement();
        HasId expResult = null;
        HasId result = instance.loadFromDB(id);
        assertEquals(expResult, result);

    }

    /**
     * Test of save method, of class TemplateVariableElement.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        TemplateVariableElement instance = new TemplateVariableElement();
        boolean expResult = false;
        boolean result = instance.save();
        assertEquals(expResult, result);
    }

    /**
     * Test of getId method, of class TemplateVariableElement.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        TemplateVariableElement instance = new TemplateVariableElement();
        int expResult = 0;
        int result = instance.getId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setId method, of class TemplateVariableElement.
     */
    @Test
    public void testSetId() throws Exception {
        System.out.println("setId");
        TemplateVariableElement instance = new TemplateVariableElement();


    }

    /**
     * Test of getElementType method, of class TemplateVariableElement.
     */
    @Test
    public void testGetElementType() {
        System.out.println("getElementType");
        TemplateVariableElement instance = new TemplateVariableElement();
        String expResult = "";
        String result = instance.getElementType();
        assertEquals(expResult, result);

    }

    /**
     * Test of setElementType method, of class TemplateVariableElement.
     */
    @Test
    public void testSetElementType() {
        System.out.println("setElementType");
        String elementType = "";
        TemplateVariableElement instance = new TemplateVariableElement();
        instance.setElementType(elementType);

    }

    /**
     * Test of getMultiplicity method, of class TemplateVariableElement.
     */
    @Test
    public void testGetMultiplicity() {
        System.out.println("getMultiplicity");
        TemplateVariableElement instance = new TemplateVariableElement();
        String expResult = "";
        String result = instance.getMultiplicity();
        assertEquals(expResult, result);

    }

    /**
     * Test of setMultiplicity method, of class TemplateVariableElement.
     */
    @Test
    public void testSetMultiplicity() {
        System.out.println("setMultiplicity");
        String multiplicity = "";
        TemplateVariableElement instance = new TemplateVariableElement();
        instance.setMultiplicity(multiplicity);

    }

    /**
     * Test of getElementId method, of class TemplateVariableElement.
     */
    @Test
    public void testGetElementId() {
        System.out.println("getElementId");
        TemplateVariableElement instance = new TemplateVariableElement();
        String expResult = "";
        String result = instance.getElementId();
        assertEquals(expResult, result);

    }

    /**
     * Test of setElementId method, of class TemplateVariableElement.
     */
    @Test
    public void testSetElementId() {
        System.out.println("setElementId");
        String element_id = "";
        TemplateVariableElement instance = new TemplateVariableElement();
        instance.setElementId(element_id);

    }
    
}
