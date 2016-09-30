
package com.gits.rms.jobs;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.gits.rms.persistence.EmployeeLeaveQuotaHibernateDao;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.EmployeeLeaveQuotaVO;
import com.gits.rms.vo.EmployeesVO;

public class AutoFillLeaveCarryFwd implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        if (DateUtils.isNextYear()) {
            try {
                Date dLastYear = DateUtils.getYesterdayDate();
                Integer iLastYear = dLastYear.getYear();
                Date dCurrentYear = new Date();
                Integer iCurrentYear = dCurrentYear.getYear() + 1900;

                EmployeeLeaveQuotaHibernateDao oEmpLeaveQuotaService = new EmployeeLeaveQuotaHibernateDao();
                List<EmployeeLeaveQuotaVO> oELQOldList = oEmpLeaveQuotaService.getAllValidEmpLeaveQuota(iLastYear);

                EmployeesVO oEmpVO = new EmployeesVO();
                oEmpVO.setEmployeeId(1);
                for (int i = 0; i < oELQOldList.size(); i++) {
                    EmployeeLeaveQuotaVO oELQVO = new EmployeeLeaveQuotaVO();

                    oELQVO.setLeaveTypeIdObj(oELQOldList.get(i).getLeaveTypeIdObj());
                    oELQVO.setEmpIdObj(oELQOldList.get(i).getEmpIdObj());
                    oELQVO.setYear(iCurrentYear);
                    oELQVO.setNoOfDays(oELQOldList.get(i).getNoOfDays());
                    oELQVO.setLeaveTaken(new BigDecimal(0));
                    oELQVO.setPrvYearCarryingForward(oELQOldList.get(i).getLeaveCarryingForward());
                    oELQVO.setLeaveCarryingForward(oELQOldList.get(i).getLeaveCarryingForward().add(oELQOldList.get(i).getNoOfDays()));
                    oELQVO.setCreated(new Timestamp(System.currentTimeMillis()));
                    oELQVO.setCreatedBy(oEmpVO);
                    oELQVO.setUpdated(new Timestamp(System.currentTimeMillis()));
                    oELQVO.setUpdatedBy(oEmpVO);
                    oELQVO.setIsActive(1);
                    oEmpLeaveQuotaService.insertEmployeeLeaveQuota(oELQVO);
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }

}
