
package com.gits.rms.vo;

import java.io.Serializable;

public class ThemeVO implements Serializable {

    private static final long serialVersionUID = 2624263631266739586L;
    private String themeName;

    public ThemeVO() {
    }

    public ThemeVO(String themeName) {
        this.themeName = themeName;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }
}
