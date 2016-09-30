
package com.gits.rms.vo;

import java.util.Date;

public class ExpensesHistoryVO {
    private Date endDate;
    private Date startDate;

    public ExpensesHistoryVO() {

    }

    public ExpensesHistoryVO(Date startDate, Date endDate) {
        super();
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
