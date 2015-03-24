/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio;

import javafx.stage.Stage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sgoyet
 */
public class CMS_SIOTest {
    
    public CMS_SIOTest() {
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
     * Test of start method, of class CMS_SIO.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        Stage stage = null;
        CMS_SIO instance = new CMS_SIO();
        instance.start(stage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class CMS_SIO.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        CMS_SIO.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
