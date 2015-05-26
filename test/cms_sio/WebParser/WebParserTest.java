/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cms_sio.WebParser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author goyet.stephane
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
     * Test of WebParser method, of class WebParser.
     */
    @Test
    public void testWebParser() throws IOException {
        System.out.println("WebParser");
        WebParser instance = new WebParser();
        instance.WebParser();
        
         URL url=new URL("http://www.creaduweb.fr/");
        String host=url.getHost();
        String fileName="home";
       
        assertTrue(Files.exists(new File(host+"/"+fileName).toPath()));
        assertTrue(Files.exists(new File(host+"/"+"references").toPath()));
        assertTrue(Files.exists(new File(host+"/"+"devis").toPath()));
         //http://www.creaduweb.fr/references/
        //http://www.creaduweb.fr/devis/
        // TODO review the generated test code and remove the default call to fail.
        
    }
    
}
