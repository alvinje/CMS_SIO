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
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alvin.jeremie
 */
public class DataPieceTest {
    
    public DataPieceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        DBUtils.connect();
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

    /**
     * Test of loadFromDB method, of class DataPiece.
     * @throws java.lang.Exception
     */
    @Test
    public void testInsert() throws Exception {
        DataPiece dataPiece=new DataPiece(new VariableElement(ConfigurationType.DataUrl.toString(),Multiplicity._0_1.toString(),"title"),"bla");
        dataPiece.save();
        
       DataPiece dataPiece_1= (DataPiece) DBUtils.loadFromDB(dataPiece, dataPiece.getClass().getField("content"));
        assertEquals(dataPiece_1.content,"bla");
        
        dataPiece.content="bli";
        dataPiece.save();
        
        DataPiece dataPiece_2= (DataPiece) DBUtils.loadFromDB(dataPiece, dataPiece.getClass().getField("content"));
        assertEquals(dataPiece_2.content,"bli");
        
       
        
    }

  
    
}
