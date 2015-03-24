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
public class VariableElement implements HasId{
    public String configurationType;
    public String multiplicity;
    public String element_id;
    public int id;
    
    public VariableElement() {
    }
    public VariableElement(String configurationType,String mulitplicity,String element_id) {
        this.configurationType=configurationType;
        this.multiplicity=mulitplicity;
        this.element_id=element_id;
    }
    public VariableElement(int id)  throws Exception{
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

    /**
     * @return the configurationType
     */
    public String getConfigurationType() {
        return configurationType;
    }

    /**
     * @param configurationType the configurationType to set
     */
    public void setConfigurationType(String configurationType) {
        this.configurationType = configurationType;
    }

    /**
     * @return the multiplicity
     */
    public String getMultiplicity() {
        return multiplicity;
    }

    /**
     * @param multiplicity the multiplicity to set
     */
    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }

    /**
     * @return the element_id
     */
    public String getElement_id() {
        return element_id;
    }

    /**
     * @param element_id the element_id to set
     */
    public void setElement_id(String element_id) {
        this.element_id = element_id;
    }

    
}
