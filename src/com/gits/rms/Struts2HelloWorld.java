
package com.gits.rms;

import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

public class Struts2HelloWorld extends ActionSupport {

    private static final long serialVersionUID = -1163450070407274947L;
    public static final String MESSAGE = "Struts 2 Hello TRDDC!!";

    @Override
    public String execute() throws Exception {
        setMessage(MESSAGE);
        return SUCCESS;
    }

    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCurrentTime() {
        return new Date().toString();
    }
}