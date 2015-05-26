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
 * constructeur de la classe Page en utilisant un nom, un contenu, un template, des données et un ID. 
 * Puis il va générer une page depuis la base de données.
 * @author sgoyet
 */
public class Page implements HasId{
    public String name; 
    public String content;
    public Template template;
    public Data data;
    public int id;
    
    
    public Page() {
     }
    
    public Page(int id)  throws Exception{
         loadFromDB( id);
    }

    public Page(String name)  throws Exception{
        this.name=name;
         DBUtils.loadFromDB(this, getClass().getField("name"));
    }
     
    @Override
    public HasId loadFromDB(int id) throws Exception {
      DBUtils.loadFromDB(this, id);
      return this;
    }

    @Override
    public boolean save() {
        return  data.save() && DBUtils.updateDB(this);
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
