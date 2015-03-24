/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

import cms_sio.model.generic.database.DBUtils;
import java.sql.SQLException;
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
public class TemplateTest {
    
    public TemplateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        DBUtils.clear();
    }
    
    @AfterClass
    public static void tearDownClass() throws SQLException {
         DBUtils.close();
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

  

   
    @Test
    public void testCreateNewTemplate() throws Exception {

        
        Template template = new Template();
        template.setName("template_test");
        template.setPathOrigin("/bla");
        Configuration configuration =new Configuration();
        
        VariableElement variableElement_1=new VariableElement(
                            ConfigurationType.TEXT.toString(),
                            Multiplicity._1.toString(),
                            "id_title");
        
        
        DBUtils.updateDB(variableElement_1);
        
        VariableElement variableElement_2=new VariableElement(
                            ConfigurationType.DataUrl.toString(),
                            Multiplicity._1.toString(),
                            "id_image_centrale");
        
        DBUtils.updateDB(variableElement_2);
        
        configuration.addVariableElement(variableElement_1 );
        configuration.addVariableElement(variableElement_2 );
              
        template.setConfiguration(configuration);
        
        DBUtils.updateDB(configuration);
        DBUtils.updateDB(template);
        
        Template template_test=new Template();
        template_test.setName("template_test");
        template_test=(Template) DBUtils.loadFromDB(template_test, template_test.getClass().getField("name"));
        
        System.out.println("Name is "+template_test.getName());
        assertTrue(template_test.getName().equals("template_test"));
        assertTrue(template_test.getPathOrigin().equals("/bla"));
        
        
        assertTrue(null !=template_test.getConfiguration() );
          
        assertTrue( template_test.getConfiguration().variableElements.size()==2);
      
       assertTrue(template_test.getConfiguration().variableElements.get(0).getElement_id().equals("id_title"));
       assertTrue(template_test.getConfiguration().variableElements.get(1).getElement_id().equals("id_image_centrale"));
        
    }
  
    @Test
    public void testSaveNewTemplate() throws Exception {
        Template template = new Template();
        template.setName("template_test_1");
        template.setPathOrigin("/bla_1");
        Configuration configuration =new Configuration();
        
        VariableElement variableElement_1=new VariableElement(
                            ConfigurationType.TEXT.toString(),
                            Multiplicity._1.toString(),
                            "id_title_1");
        
        
        DBUtils.updateDB(variableElement_1);
        
        VariableElement variableElement_2=new VariableElement(
                            ConfigurationType.DataUrl.toString(),
                            Multiplicity._1.toString(),
                            "id_image_centrale_1");
        
        DBUtils.updateDB(variableElement_2);
        
        configuration.addVariableElement(variableElement_1 );
        configuration.addVariableElement(variableElement_2 );
              
        template.setConfiguration(configuration);
        
        assertTrue(template.save());
        
  
        Template template_test=new Template("template_test_1");

        assertTrue(template_test.getName().equals("template_test_1"));
        assertTrue(template_test.getPathOrigin().equals("/bla_1"));
        
        
        assertTrue(null !=template_test.getConfiguration() );
          
        assertTrue( template_test.getConfiguration().variableElements.size()==2);
      
       assertTrue(template_test.getConfiguration().variableElements.get(0).getElement_id().equals("id_title_1"));
       assertTrue(template_test.getConfiguration().variableElements.get(1).getElement_id().equals("id_image_centrale_1"));
        
        
    }
 
    
}
