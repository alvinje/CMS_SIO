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
 *
 * @author sgoyet
 */
public class TemplateConfiguration implements HasId{
    public int id;
    List<TemplateVariableElement> templateVariableElement=new ArrayList<TemplateVariableElement>();
    public String templateVariableElementsIds;

   public TemplateConfiguration(){
       
   }
   public TemplateConfiguration(int id)  throws Exception{
     loadFromDB( id);
   }
   
    @Override
    public HasId loadFromDB(int id) throws Exception {
      DBUtils.loadFromDB(this, id);
      String[] ids=templateVariableElementsIds.split("_");
      for (String id_:ids){
          templateVariableElement.add(new TemplateVariableElement(Integer.parseInt(id_)));
      }
      return this;
    }

    @Override
    public boolean save() {
        boolean result=true;
        for (TemplateVariableElement variableElement:templateVariableElement){
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
    public void addVariableElement(TemplateVariableElement variableElement){
        templateVariableElement.add(variableElement);
        buildvariableElementsIds();
    }
    public void removeVariableElement(TemplateVariableElement variableElement){
        templateVariableElement.remove(variableElement);
        buildvariableElementsIds();
    }
   
    public void clearVariableElement(TemplateVariableElement variableElement){
        templateVariableElement.clear();
        buildvariableElementsIds();
    }
    
    void buildvariableElementsIds(){
        StringBuilder builder=new StringBuilder();
        for (TemplateVariableElement variableElement:templateVariableElement){
            builder.append(variableElement.id).append("_");
        }
        if (builder.length()>0){
            builder.setLength(builder.length()-1);
        }
        templateVariableElementsIds=builder.toString();
    }



}
