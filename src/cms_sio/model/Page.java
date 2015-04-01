/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

import cms_sio.model.generic.HasId;
import cms_sio.model.generic.database.DBUtils;
import java.sql.SQLException;


public class Page implements HasId {

    public String name;
    public PageData pageData;
    public int id;

    public Page() {
    }

    public Page(int id) throws Exception {
        loadFromDB(id);
    }

    public Page(String name) throws Exception {
        this.name = name;
        DBUtils.loadFromDB(this, getClass().getField("name"));
    }

    @Override
    public HasId loadFromDB(int id) throws Exception {
        DBUtils.loadFromDB(this, id);
        return this;
    }

    @Override
    public boolean save() {
        return pageData.save() && DBUtils.updateDB(this);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId() throws SQLException {
        id = DBUtils.getId(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PageData getPageData() {
        return pageData;
    }

    public void setPageData(PageData pageData) {
        this.pageData = pageData;
    }

}
