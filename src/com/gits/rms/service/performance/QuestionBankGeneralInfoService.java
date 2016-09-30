
package com.gits.rms.service.performance;

import java.util.Date;
import java.util.List;

import com.gits.rms.vo.QuestionBankGeneralInfoVO;

public interface QuestionBankGeneralInfoService {

    void insertQuestionBankGeneralInfo(QuestionBankGeneralInfoVO quesBankGeneralInfo);

    List getAllEmployeeId(Integer departmentId, Integer teamId, Integer jobTitleId, Integer projectId, Date startDate, Date endDate);

    List getAllApprovingEmployeeId(Integer departmentId, Integer teamId, Integer jobTitleId, Integer projectId, Date startDate, Date endDate);

    List getAllPeerEmployeeId(Integer departmentId, Integer teamId, Integer jobTitleId, Integer projectId, Date startDate, Date endDate);

    List getAllApproversSubEmployeeList();

    List getAllEmployeesPeerEmployeeList(Integer EmployeeId);

    List getEmployeeListForPeerEmployee(Integer EmployeeId);

    List getPeerEmployeeQuestionBank(Integer EmployeeId);

    void updateGeneralInfoStatus(QuestionBankGeneralInfoVO quesBankGeneralInfo);

    QuestionBankGeneralInfoVO getEmployeeQuestionBankGeneralInfo(Integer questionBankGeneralInfoId, Integer questionGroupIdentifiId, Integer questionGeneralInfoGroupIdListId);

    QuestionBankGeneralInfoVO getQuestionBankGeneralInfo(Integer questionBankGeneralInfoId);

    void updateGeneralInfoaApproverComments(QuestionBankGeneralInfoVO quesBankGeneralInfo);

    QuestionBankGeneralInfoVO getAllassignedEmployee(Date startDate,Date endDate,Integer employeeId);
    
    QuestionBankGeneralInfoVO getAllAssignedAppEmployee(Date startDate,Date endDate,Integer employeeId,Integer approvingEmployeeId);
    
    QuestionBankGeneralInfoVO getAllAssignedPeerEmployee(Date startDate,Date endDate,Integer employeeId,Integer peerEmployeeId);
    
    List getAdminEmployeeQuesGeneralInfoList(Integer EmployeeId);
    
    List getAdminApprovingEmployeeQuesGeneralInfoList(Integer EmployeeId);
    
    List getAdminPeerEmployeeQuesGeneralInfoList(Integer EmployeeId);

    void updateAdminEmployeeComments(QuestionBankGeneralInfoVO quesBankGeneralInfo);
    
    List getQuestionBankGeneralInfoList(Integer questionGroupIdentifiId,Integer questionGeneralInfoGroupIdListId,Integer employeeId,Integer peerEmpId);
    
    List getAllQuestionGeneralInfoGroupId();
    
    List getAllQuestionBankGeneralInfo(Integer quesGeneralInfoGroupId);
}
