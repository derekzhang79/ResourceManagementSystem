
package com.gits.rms;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.constants.Constants;

public class ApplyTheme extends ActionSupport {

    private static final long serialVersionUID = -9109082478150136864L;
    private String theme;

    @Override
    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.put(Constants.APPL_THEME, ServletActionContext.getRequest().getParameter("theme"));
        return SUCCESS;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

}
