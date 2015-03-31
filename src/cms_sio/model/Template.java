/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

import cms_sio.model.generic.HasId;
import cms_sio.model.generic.database.DBUtils;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author sgoyet
 */
public class Template implements HasId{
    public String name="";
    public String pathOrigin="";
    public TemplateConfiguration templateConfiguration;
    public int id;
    
    
    public Template() {
    }
    
  
     public Template(File file) throws IOException {
        this.pathOrigin=file.getCanonicalPath();
        this.name=file.getName();
    }
     
    public Template(int id)  throws Exception{
       loadFromDB( id);
    }
    
    public Template(String name)  throws Exception{
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
        return templateConfiguration.save()&& DBUtils.updateDB(this);
    }
    
    @Override
    public int getId() {
        return id;    
    }

       @Override
    public void setId() throws SQLException {
        id=DBUtils.getId(this);
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
     * @return the pathOrigin
     */
    public String getPathOrigin() {
        return pathOrigin;
    }

    /**
     * @param pathOrigin the pathOrigin to set
     */
    public void setPathOrigin(String pathOrigin) {
        this.pathOrigin = pathOrigin;
    }

    /**
     * @return the configuration
     */
    public TemplateConfiguration getConfiguration() {
        return templateConfiguration;
    }

    /**
     * @param configuration the configuration to set
     */
    public void setConfiguration(TemplateConfiguration configuration) {
        this.templateConfiguration = configuration;
    }

 
}
