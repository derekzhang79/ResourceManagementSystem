
package com.gits.rms;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

    private static final long serialVersionUID = 5338238353030984071L;
    private Integer iEmployeeId;

    @Override
    public String execute() throws Exception {

        // retrieve the login status from the session by key name.
        Map session = ActionContext.getContext().getSession();
        iEmployeeId = (Integer) session.get("EMPLOYEE_OBJECT");
        // if the employee id is non null, the user is logged in.
        if (iEmployeeId != null) {
            return super.execute();
        }
        return "notLoggedIn";

    }

}
