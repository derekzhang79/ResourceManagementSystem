
package com.gits.rms;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class Logout extends ActionSupport {
    private static final long serialVersionUID = 1L;

    public String doLogout() {
        Map session = ActionContext.getContext().getSession();
        session.clear();
        return SUCCESS;
    }
}
