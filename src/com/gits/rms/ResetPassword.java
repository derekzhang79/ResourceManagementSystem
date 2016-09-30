
package com.gits.rms;

import java.util.StringTokenizer;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.persistence.UserHibernateDao;
import com.gits.rms.utils.DesEncrypter;
import com.gits.rms.vo.UserVO;

public class ResetPassword extends ActionSupport {

    private static final long serialVersionUID = 3741346205625591713L;
    private String sEnc;
    private String sEmail;
    private String sUserName;
    private String sPassword;
    private Integer iUserId;

    public String requestResetPassword() {
        if (getSEnc() == null) {
            return ERROR;
        } else {
            String sPassPhrase = new String("4Y2349324HH8932C4HC9UHE9RHW9EY823496723647823678C4627836478C568C6234CWGRGWEGRWEY");
            DesEncrypter oEncrypter = new DesEncrypter(sPassPhrase);
            String sEncryptedValues = oEncrypter.decrypt(sEnc);
            StringTokenizer stEncrypted = new StringTokenizer(sEncryptedValues, ",");
            int i = 0;
            while (stEncrypted.hasMoreElements()) {
                if (i == 0) {
                    sUserName = String.valueOf(stEncrypted.nextElement());
                }
                if (i == 1) {
                    sEmail = String.valueOf(stEncrypted.nextElement());
                }
                if (i == 2) {
                    iUserId = Integer.valueOf(String.valueOf(stEncrypted.nextElement()));
                }
                i++;
            }
            UserVO user = new UserVO();
            user.setUserName(sUserName);
            user.setHcmouserId(iUserId);
            UserHibernateDao oUHD = new UserHibernateDao();
            UserVO userCheck = oUHD.checkUserForgotPasswordOn(user);
            if (userCheck.getForgotPassword() == 1) {
                return SUCCESS;
            } else {
                addActionMessage(getText("message.logininfo.resetpassword.expired"));
                return ERROR;
            }

        }

    }

    public String resetPassword() {
        UserVO user = new UserVO();
        user.setUserName(getSUserName());
        user.setHcmouserId(getIUserId());
        user.setPassword(getSPassword());
        UserHibernateDao oUHD = new UserHibernateDao();
        int iResult = oUHD.resetUserPassword(user);
        if (iResult == 1) {
            addActionMessage(getText("message.logininfo.resetpassword.success"));
            return SUCCESS;
        } else {
            return ERROR;
        }

    }

    public String getSEnc() {
        return sEnc;
    }

    public void setSEnc(String enc) {
        sEnc = enc;
    }

    public String getSEmail() {
        return sEmail;
    }

    public void setSEmail(String email) {
        sEmail = email;
    }

    public String getSUserName() {
        return sUserName;
    }

    public void setSUserName(String userName) {
        sUserName = userName;
    }

    public Integer getIUserId() {
        return iUserId;
    }

    public void setIUserId(Integer userId) {
        iUserId = userId;
    }

    public String getSPassword() {
        return sPassword;
    }

    public void setSPassword(String password) {
        sPassword = password;
    }

}