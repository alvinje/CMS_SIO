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
 * Implémentation des données de la page en fonction de l'id de chaque informations
 * @author sgoyet
 */
public class Data implements HasId{
    public Template template;
    public int id;
    public Data(){
        
    }
    public Data(int id)  throws Exception{
      loadFromDB( id) ;
    }
     @Override
    public int getId() {
        return id;    
    }

    @Override
    public HasId loadFromDB(int id) throws Exception {
      DBUtils.loadFromDB(this, id);
      return this;
    }

    @Override
    public boolean save() {
        if (template.save()){
               return DBUtils.updateDB(this); 
        }else{
            return false;
        }
    }
    
    @Override
    public void setId() throws SQLException {
        id=DBUtils.getId(this);
    }
}
