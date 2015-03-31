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
public class TemplateVariableElement implements HasId{
    public String elementType;
    public String multiplicity;
    public String elementId;
    public int id;
    
    public TemplateVariableElement() {
    }
    public TemplateVariableElement(String elementType,String mulitplicity,String elementId) {
        this.elementType=elementType;
        this.multiplicity=mulitplicity;
        this.elementId=elementId;
    }
    public TemplateVariableElement(int id)  throws Exception{
       loadFromDB( id) ;
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


    public String getElementType() {
        return elementType;
    }


    public void setElementType(String elementType) {
        this.elementType = elementType;
    }


    public String getMultiplicity() {
        return multiplicity;
    }


    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }


    public String getElementId() {
        return elementId;
    }


    public void setElementId(String element_id) {
        this.elementId = element_id;
    }

    
}
