/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cms_sio.xebparser;

import java.net.URL;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ducruet.anthony
 */
public class WebParserTest {
    
    public WebParserTest() {
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
     * Test of getDocument method, of class WebParser.
     */
    @Test
    public void testGetDocument() throws Exception {
        System.out.println("getDocument");
        URL url = new URL("http://www.cartegrisechrono.com/");
        
        WebParser instance = new WebParser();
        instance.getDocument(url);
        
//         TODO review the generated test code and remove the default call to fail.
        
    }
    
}
