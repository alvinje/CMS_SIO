/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

import cms_sio.model.generic.HasId;
import cms_sio.model.generic.database.DBUtils;
import java.sql.SQLException;

/**
 *
 * @author sgoyet
 */
public class DataPiece implements HasId {

    VariableElement variableElement;
    String content="";
    public int id;
    public DataPiece() {
    }
    public DataPiece(VariableElement variableElement,String content) {
        this.variableElement=variableElement;
        this.content=content;
    }   
    public DataPiece(int id)  throws Exception{
          loadFromDB( id);
    }
   @Override
    public HasId loadFromDB(int id) throws Exception {
      DBUtils.loadFromDB(this, id);
      return this;
    }

    @Override
    public boolean save() {
        return DBUtils.updateDB(this);
    }
    

    @Override
    public int getId() {
        return id;    
    }

    @Override
    public void setId() throws SQLException {
        id=DBUtils.getId(this);
    }





}
