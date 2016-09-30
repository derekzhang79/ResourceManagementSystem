
package com.gits.rms.action.utils;

import com.opensymphony.xwork2.ActionSupport;

public class ErrorsAction extends ActionSupport {

    // Error for 2 Fields.
    public String getError(RuntimeException e) {
        String sError = e.getCause().getLocalizedMessage();
        if (sError != null) {
            String sStartEnd = "'";
            int iStart = sError.indexOf(sStartEnd) + 1;
            int iNext = sError.indexOf(sStartEnd, iStart);
            if ((sError.indexOf(sStartEnd) > -1) && (sError.indexOf("Duplicate entry") > -1)) {
                return "'" + sError.substring(iStart, sError.indexOf("-")) + "'" + " "
                    + this.getText("application.error.value.exists");
            } else {
                return sError;
            }
        } else if (sError == null) {
            return "Unknown Error Occured";
        } else {
            return "Some Error Occured";
        }   
    }

    // Error for 3 Fields.
    public String getErrorMoreFields(RuntimeException e) {
        String sError = e.getCause().getLocalizedMessage();
        if (sError != null) {
            String sStartEnd = "'";
            int iStart = sError.indexOf(sStartEnd) + 1;
            int iNext = sError.indexOf(sStartEnd, iStart);
            if ((sError.indexOf(sStartEnd) > -1) && (sError.indexOf("Duplicate entry") > -1)) {
                return "'" + sError.substring(iStart, iNext - 2) + "'" + " "
                    + this.getText("application.error.value.exists");
            } else {
                return sError;
            }
        } else if (sError == null) {
            return "Unknown Error Occured";
        } else {
            return "Some Error Occured";
        }
    }
}
