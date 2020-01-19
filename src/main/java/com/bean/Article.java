package com.bean;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author duanbochao
 * @version 1.0
 * @date 2020/1/10 8:26
 */
public class Article {

    private Integer id;
    private String title;
    private String mdContent;
    private String htmlContent;
    private String Summary;
    private Integer cid;
    private Integer uid;
    private Timestamp publishDate;
    private Timestamp editTime;
    private Integer state;
    private Integer pageView;
    private String[] dynamicTags;
    private String nickname;
    private String cateName;
    private List<Tags> tags;
    private String stateStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMdContent() {
        return mdContent;
    }

    public void setMdContent(String mdContent) {
        this.mdContent = mdContent;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String Summary) {
        this.Summary = Summary;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Timestamp getpublishDate() {
        return publishDate;
    }

    public void setpublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getPageView() {
        return pageView;
    }

    public void setPageView(Integer pageView) {
        this.pageView = pageView;
    }

    public String[] getDynamicTags() {
        return dynamicTags;
    }

    public void setDynamicTags(String[] dynamicTags) {
        this.dynamicTags = dynamicTags;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public String getStateStr() {
        return stateStr;
    }

    public void setStateStr(String stateStr) {
        this.stateStr = stateStr;
    }
}
