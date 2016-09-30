
package com.gits.rms.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class NationalityVO implements Serializable {

    private static final long serialVersionUID = 94093382370013155L;
    private int isActive;
    private Integer nationalityId;
    private String nationalityName;
    

    public NationalityVO() {
    }

    public NationalityVO(Integer nationalityId, String nationalityName,int isActive) {
        this.nationalityId = nationalityId;
        this.nationalityName = nationalityName;
      
        this.isActive = isActive;
    }

   

    public int getIsActive() {
        return isActive;
    }

    public Integer getNationalityId() {
        return nationalityId;
    }

    public String getNationalityName() {
        return nationalityName;
    }

   

   

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public void setNationalityId(Integer nationalityId) {
        this.nationalityId = nationalityId;
    }

    public void setNationalityName(String nationalityName) {
        this.nationalityName = nationalityName;
    }

  

}
