package com.lit.lms.dto;

import java.util.Date;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by kashifroshen on 11/3/17.
 */
public class ModulesDTO {

    public Long getModulesid() {
        return modulesid;
    }

    public void setModulesid(Long modulesid) {
        this.modulesid = modulesid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContenturl() {
        return contenturl;
    }

    public void setContenturl(String contenturl) {
        this.contenturl = contenturl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getcId() {
        return cId;
    }

    public void setcId(Long cId) {
        this.cId = cId;
    }
    public Long getTcreator() {
        return tcreator;
    }

    public void setTcreator(Long tcreator) {
        this.tcreator = tcreator;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    private Long modulesid;


    private String title;
    private String contenturl;
    private String description;

    private Long cId;
    private Long tcreator;
    private String status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;


    public ModulesDTO() {

    }
}
