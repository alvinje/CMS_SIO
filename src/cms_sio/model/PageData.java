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


public class PageData implements HasId {
    public Template template;
    List<PageDataElement> pageDataElement = new ArrayList<PageDataElement>();
    public String pageDataElementsIds;
    public int id;

    public PageData() {
    }

    public PageData(int id) throws Exception {
        loadFromDB(id);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public HasId loadFromDB(int id) throws Exception {
        DBUtils.loadFromDB(this, id);
        String[] ids = pageDataElementsIds.split("_");
        for (String id_:ids){
          pageDataElement.add(new PageDataElement(Integer.parseInt(id_)));
        }
        return this;
    }

    @Override
    public boolean save() {
        boolean result=true;
        for (PageDataElement dataElement : pageDataElement){
            result=result & dataElement.save();
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
        id = DBUtils.getId(this);
    }
    
    public void addPageDataElement(PageDataElement dataElement){
        pageDataElement.add(dataElement);
        buildDataElementsIds();
    }
    public void removePageDataElement(PageDataElement dataElement){
        pageDataElement.remove(dataElement);
        buildDataElementsIds();
    }
   
    public void clearPageDataElement(PageDataElement dataElement){
        pageDataElement.clear();
        buildDataElementsIds();
    }
    
    void buildDataElementsIds(){
        StringBuilder builder=new StringBuilder();
        for (PageDataElement dataElement:pageDataElement){
            builder.append(dataElement.id).append("_");
        }
        if (builder.length()>0){
            builder.setLength(builder.length()-1);
        }
        pageDataElementsIds=builder.toString();
    }

    
}
