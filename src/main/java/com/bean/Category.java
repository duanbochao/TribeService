package com.bean;

import java.sql.Timestamp;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/8 14:54
 */
public class Category {
    private Long id;
    private String cateName;
    private Timestamp date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
