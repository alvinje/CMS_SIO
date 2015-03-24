/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model.generic;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author sgoyet
 */
public interface  HasId {

    public int getId() ;
    public void setId() throws SQLException;
    public HasId  loadFromDB(int id) throws Exception;  
    public boolean  save() ;  
}
