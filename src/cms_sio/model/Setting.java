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
public class Setting implements HasId{
    public int id;
    public String name;
    public String property;
    public Setting(){
        
    }
    public Setting(String name){
        this.name=name;
        property="";
    }
         
    public Setting(String name, String property)  {
      this.name=name;
      this.property=property;
    }
     
    public Setting(int id)  throws Exception{
       DBUtils.loadFromDB(this, id);
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
    return id;    }

    @Override
    public void setId() throws SQLException {
        setId(DBUtils.getId(this));   }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the property
     */
    public String getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(String property) {
        this.property = property;
    }

   
    
}
