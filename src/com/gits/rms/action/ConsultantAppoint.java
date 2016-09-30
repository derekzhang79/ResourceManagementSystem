
package com.gits.rms.action;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class ConsultantAppoint extends ActionSupport {

    private static final long serialVersionUID = 2905770696979680795L;
    private Date appDate;
    private Date dueDate;

    @Override
    public String execute() throws Exception {

        return SUCCESS;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

}
