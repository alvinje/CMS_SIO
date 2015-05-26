/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

import cms_sio.model.generic.HasId;
import cms_sio.model.generic.database.DBUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Mise en place des variables éléments dans une liste VariableElement
 * @author sgoyet
 */
public class Configuration implements HasId{
    public int id;
    List<VariableElement> variableElements=new ArrayList<VariableElement>();
    public String variableElementsIds;

   public Configuration(){
       
   }
   public Configuration(int id)  throws Exception{
     loadFromDB( id);
   }
   
    @Override
    public HasId loadFromDB(int id) throws Exception {
      DBUtils.loadFromDB(this, id);
      String[] ids=variableElementsIds.split("_");
      for (String id_:ids){
          variableElements.add(new VariableElement(Integer.parseInt(id_)));
      }
      return this;
    }

    @Override
    public boolean save() {
        boolean result=true;
        for (VariableElement variableElement:variableElements){
            result=result & variableElement.save();
            if (!result){
                break;
            }
        }
        if (result){
             return DBUtils.updateDB(this);
        }
        return result;
       
    }
    
    
    @Override
    public void setId() throws SQLException {
        id=DBUtils.getId(this);
    }
    
    @Override
    public int getId() {
        return id;    
    }
    public void addVariableElement(VariableElement variableElement){
        variableElements.add(variableElement);
        buildvariableElementsIds();
    }
    public void removeVariableElement(VariableElement variableElement){
        variableElements.remove(variableElement);
        buildvariableElementsIds();
    }
   
    public void clear(VariableElement variableElement){
        variableElements.clear();
        buildvariableElementsIds();
    }
    
    void buildvariableElementsIds(){
        StringBuffer buffer=new StringBuffer();
        for (VariableElement variableElement:variableElements){
            buffer.append(variableElement.id).append("_");
        }
        if (buffer.length()>0){
            buffer.setLength(buffer.length()-1);
        }
        variableElementsIds=buffer.toString();
    }



}
