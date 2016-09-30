
package com.gits.rms.vo;

import java.io.Serializable;

public class EmployeesCountVO implements Serializable {

    private static final long serialVersionUID = -4515712479229531781L;
    private String category;
    private Integer count;

    public EmployeesCountVO() {

    }

    public EmployeesCountVO(String category, Integer count) {
        super();
        this.category = category;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public Integer getCount() {
        return count;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
