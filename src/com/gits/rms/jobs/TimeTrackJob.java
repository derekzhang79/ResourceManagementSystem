
package com.gits.rms.jobs;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.service.TimeSheetCategoryAssignDaoService;
import com.gits.rms.service.TimeSheetCategoryAssignService;
import com.gits.rms.service.TimeTrackDaoService;
import com.gits.rms.service.TimeTrackService;
import com.gits.rms.service.TimesheetCategoryDaoService;
import com.gits.rms.service.TimesheetCategoryService;
import com.gits.rms.utils.DateUtils;
import com.gits.rms.vo.ClientInformationVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ProjectActivityVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.TimeSheetCategoryAssignVO;
import com.gits.rms.vo.TimeSheetProjectAssignVO;
import com.gits.rms.vo.TimeTrackVO;
import com.gits.rms.vo.TimesheetCategoryVO;

public class TimeTrackJob implements Job {
    TimeTrackVO timeTrackVO;
    private List<TimeTrackVO> timetrackList;
    private TimeTrackService timeTrackService = new TimeTrackDaoService();
    private List<TimesheetCategoryVO> timesheetCategoryList;
    private TimesheetCategoryService timesheetCategoryService = new TimesheetCategoryDaoService();
    private List<TimeTrackVO> timetrackHistoryList;
    private List<TimeTrackVO> timetrackUpdateList;
    private TimeSheetCategoryAssignService timeSheetCategoryAssignService = new TimeSheetCategoryAssignDaoService();
    private TimeSheetCategoryAssignVO timeSheetCategoryAssignVO;
    private TimesheetCategoryVO timeCatObj;
    private ProjectVO proTimeObj;
    private ProjectActivityVO proActivityTimeObj;
    private double durationForHoursValue;

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

        List<ClientInformationVO> clientList = HibernateUtil.getClientInfoList();

        for (Iterator<ClientInformationVO> it = clientList.iterator(); it.hasNext();) {
            ClientInformationVO cliInfoObj = it.next();

            timeTrackVO = new TimeTrackVO();
            timetrackList = timeTrackService.resetimeTrack(timeTrackVO, String.valueOf(cliInfoObj.getClientId()));
            if (timetrackList.size() > 0) {
                for (int i = 0; i <= (timetrackList.size() - 1); i++) {
                    EmployeesVO oEmp = new EmployeesVO();
                    oEmp.setEmployeeId(timetrackList.get(i).getEmpId());
                    timeTrackVO = new TimeTrackVO();
                    int catId=timetrackList.get(i).getCategoryId();
                    try {
                        Calendar cal = Calendar.getInstance();
                        Date newdate = new Date();
                        Timestamp ts1 = new Timestamp(newdate.getTime());
                        double durationForHours;
                        double duration = 0.0;

                        //To Clock out option based on 12 hours
                        timeTrackVO.setStop(ts1);
                        java.util.Date dateForStart = new java.util.Date(timetrackList.get(i).getStart().getTime());
                        java.util.Date dateForStop = new java.util.Date(timeTrackVO.getStop().getTime());
                        long d1Ms = dateForStop.getTime();
                        long d2Ms = dateForStart.getTime();
                        long minute = Math.abs((d1Ms - d2Ms) / 60000);
                        int Hours = (int) minute / 60;
                        int Minutes = (int) minute % 60;
                        durationForHoursValue += round(Double.parseDouble(Hours + "." + Minutes),2);
                        if((durationForHoursValue>=12.00)||(durationForHoursValue>=12.0)){
                        timeTrackVO.setEmpId(oEmp.getEmployeeId());
                        timeTrackVO.setCategoryId(timetrackList.get(i).getCategoryId());
                        timeTrackVO.setProjectId(timetrackList.get(i).getProjectId());
                        timeTrackVO.setActivityId(timetrackList.get(i).getActivityId());
                        timeTrackVO.setDuration(0.0);
                        timeTrackVO.setCheckIn(1);
                        timeTrackVO.setCheckOut(1);
                        timeTrackVO.setCreated(DateUtils.getCurrentDateTime());
                        timeTrackVO.setCreatedBy(oEmp);
                        timeTrackVO.setUpdatedBy(oEmp);
                        timeTrackVO.setIsActive(0);
                        timeTrackService.updateCheckOutCronJob(timeTrackVO, String.valueOf(cliInfoObj.getClientId()));
                        timetrackHistoryList = timeTrackService.getAllTimeTrackCronJob(timeTrackVO, String.valueOf(cliInfoObj.getClientId()));

                        if (timetrackHistoryList.size() > 0) {
                            for (int j = 0; j <= (timetrackHistoryList.size() - 1); j++) {
                                try {
                                    java.util.Date date1 = new java.util.Date(timetrackHistoryList.get(j).getStart().getTime());
                                    java.util.Date date2 = new java.util.Date(timetrackHistoryList.get(j).getStop().getTime());
                                    duration += (date2.getTime() - date1.getTime())
                                        / (60 * 60 * 1000);
                                    timetrackHistoryList.get(j).setDuration(duration);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                duration = 0.0;
                            }
                        }
                        timetrackUpdateList = timeTrackService.getAllTimeTrackByIdCronJob(timeTrackVO, String.valueOf(cliInfoObj.getClientId()));
                        if (timetrackUpdateList.size() > 0) {
                            double updateDuration = 0.0;
                            for (int j = 0; j <= (timetrackUpdateList.size() - 1); j++) {
                                try {
                                    java.util.Date date1 = new java.util.Date(timetrackUpdateList.get(j).getStart().getTime());
                                    java.util.Date date2 = new java.util.Date(timetrackUpdateList.get(j).getStop().getTime());
                                    updateDuration += round((date2.getTime() - date1.getTime())
                                        / (60 * 60 * 1000), 2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            List chkExistingTimeList = new ArrayList();
                            chkExistingTimeList = timeSheetCategoryAssignService.timeSheetCategoryAssignSearchCronJob(oEmp.getEmployeeId(), timetrackList.get(i).getCategoryId(), DateUtils.getCurrentDateTime(), String.valueOf(cliInfoObj.getClientId()));
                            oEmp.setEmployeeId(timetrackList.get(i).getEmpId());


                            if(catId!=0){
                            if (chkExistingTimeList.size() > 0) {
                                timeSheetCategoryAssignVO = new TimeSheetCategoryAssignVO();
                                timeSheetCategoryAssignVO.setEmployeeName(oEmp);
                                timeSheetCategoryAssignVO.setEnterTime(durationForHoursValue);
                                timeSheetCategoryAssignVO.setUpdatedBy(oEmp);
                                timeSheetCategoryAssignVO.setUpdated(DateUtils.getCurrentTime());
                                timeTrackService.updateTimeSheetCategoryAssignCronJob(timeSheetCategoryAssignVO, oEmp.getEmployeeId(), timetrackList.get(i).getCategoryId(), String.valueOf(cliInfoObj.getClientId()));
                            } else {
                                timeSheetCategoryAssignVO = new TimeSheetCategoryAssignVO();
                                timeSheetCategoryAssignVO.setEnterDate(new Date());
                                timeSheetCategoryAssignVO.setEnterTime(durationForHoursValue);
                                timeCatObj=timesheetCategoryService.getTimesheetCategoryCronJob(catId,String.valueOf(cliInfoObj.getClientId()));
                                timeSheetCategoryAssignVO.setTimesheetCategoryName(timeCatObj);

                                timeSheetCategoryAssignVO.setEmployeeName(oEmp);
                                timeSheetCategoryAssignVO.setIsActive(1);
                                timeSheetCategoryAssignVO.setApprove(0);
                                timeSheetCategoryAssignVO.setRejected(0);
                                timeSheetCategoryAssignVO.setRework(0);
                                timeSheetCategoryAssignVO.setCreated(new Date());
                                timeSheetCategoryAssignVO.setCreatedBy(oEmp);
                                timeSheetCategoryAssignVO.setUpdatedBy(oEmp);
                                timeTrackService.insertTimeSheetCronJob(timeSheetCategoryAssignVO, oEmp.getEmployeeId(), timetrackList.get(i).getCategoryId(), String.valueOf(cliInfoObj.getClientId()));
                            }
                            }else{
                            List chkExistingProTimeList = new ArrayList();
                            proTimeObj=timeTrackService.getProjectAllDetailsCronJob(timeTrackVO.getProjectId(),String.valueOf(cliInfoObj.getClientId()));
                            proActivityTimeObj=timeTrackService.getProjectActivityAllDetailsCronJob(timeTrackVO.getActivityId(),String.valueOf(cliInfoObj.getClientId()));
                            chkExistingProTimeList = timeTrackService.timeSheetProjectAssignSearchCronJob(oEmp.getEmployeeId(),proTimeObj,proActivityTimeObj,DateUtils.getCurrentDateTime(),String.valueOf(cliInfoObj.getClientId()));

                            ProjectActivityVO activityVo = new ProjectActivityVO();
                            activityVo.setProjectActivityId(timeTrackVO.getActivityId());
                            ProjectVO projectVo = new ProjectVO();
                            projectVo.setProjectId(timeTrackVO.getProjectId());

                            if (chkExistingProTimeList.size() > 0) {
                                TimeSheetProjectAssignVO timeSheetProjectAssignVO = new TimeSheetProjectAssignVO();
                                timeSheetProjectAssignVO.setEmployeeName(oEmp);
                                timeSheetProjectAssignVO.setEnterTime(durationForHoursValue);
                                timeSheetProjectAssignVO.setProjectActivity(activityVo);
                                timeSheetProjectAssignVO.setProjectName(projectVo);
                                timeSheetProjectAssignVO.setUpdatedBy(oEmp);
                                timeSheetProjectAssignVO.setUpdated(DateUtils.getCurrentTime());
                                timeTrackService.updateTimeSheetProjectAssignCronJob(timeSheetProjectAssignVO, oEmp.getEmployeeId(),String.valueOf(cliInfoObj.getClientId()));
                            } else {
                                TimeSheetProjectAssignVO timeSheetProjectAssignVO = new TimeSheetProjectAssignVO();
                                timeSheetProjectAssignVO.setEnterDate(new Date());
                                timeSheetProjectAssignVO.setEnterTime(durationForHoursValue);
                                timeSheetProjectAssignVO.setEmployeeName(oEmp);
                                timeSheetProjectAssignVO.setProjectActivity(activityVo);
                                timeSheetProjectAssignVO.setProjectName(projectVo);
                                timeSheetProjectAssignVO.setIsActive(1);
                                timeSheetProjectAssignVO.setApprove(0);
                                timeSheetProjectAssignVO.setRejected(0);
                                timeSheetProjectAssignVO.setRework(0);
                                timeSheetProjectAssignVO.setCreated(new Date());
                                timeSheetProjectAssignVO.setCreatedBy(oEmp);
                                timeSheetProjectAssignVO.setUpdatedBy(oEmp);
                                timeTrackService.insertTimeSheetActivityCronJob(timeSheetProjectAssignVO, oEmp.getEmployeeId(),String.valueOf(cliInfoObj.getClientId()));
                            }
                           }
                        }
                    }
                    }
                     catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public double round(double d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }

	public void setTimeCatObj(TimesheetCategoryVO timeCatObj) {
		this.timeCatObj = timeCatObj;
	}

	public TimesheetCategoryVO getTimeCatObj() {
		return timeCatObj;
	}

	public void setProTimeObj(ProjectVO proTimeObj) {
		this.proTimeObj = proTimeObj;
	}

	public ProjectVO getProTimeObj() {
		return proTimeObj;
	}

	public void setProActivityTimeObj(ProjectActivityVO proActivityTimeObj) {
		this.proActivityTimeObj = proActivityTimeObj;
	}

	public ProjectActivityVO getProActivityTimeObj() {
		return proActivityTimeObj;
	}

	public void setDurationForHoursValue(double durationForHoursValue) {
		this.durationForHoursValue = durationForHoursValue;
	}

	public double getDurationForHoursValue() {
		return durationForHoursValue;
	}
}
