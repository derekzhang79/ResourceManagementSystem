
package com.gits.rms.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuVO implements Serializable {

    private static final long serialVersionUID = 6205430409868785525L;
    private Boolean add;
    private Boolean delete;
    private String id;
    private String label;
    private Integer level;
    private Integer parent;
    private Integer position;
    private List<MenuVO> submenus = new ArrayList<MenuVO>();
    private Boolean update;
    private String url;
    private String urlAdd;
    private String urlRoot;
    private String urlView;
    private Boolean view;

    public void addMenu(MenuVO m) {
        submenus.add(m);
    }

    public Boolean getAdd() {
        return add;
    }

    public Boolean getDelete() {
        return delete;
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public Integer getLevel() {
        return level;
    }

    public Integer getParent() {
        return parent;
    }

    public Integer getPosition() {
        return position;
    }

    public List<MenuVO> getSubmenus() {
        return submenus;
    }

    public Boolean getUpdate() {
        return update;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlAdd() {
        return urlAdd;
    }

    public String getUrlRoot() {
        return urlRoot;
    }

    public String getUrlView() {
        return urlView;
    }

    public Boolean getView() {
        return view;
    }

    public void setAdd(Boolean add) {
        this.add = add;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public void setUpdate(Boolean update) {
        this.update = update;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUrlAdd(String urlAdd) {
        this.urlAdd = urlAdd;
    }

    public void setUrlRoot(String urlRoot) {
        this.urlRoot = urlRoot;
    }

    public void setUrlView(String urlView) {
        this.urlView = urlView;
    }

    public void setView(Boolean view) {
        this.view = view;
    }

}