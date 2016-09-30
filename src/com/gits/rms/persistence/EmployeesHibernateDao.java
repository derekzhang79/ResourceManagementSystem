
package com.gits.rms.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;

import com.opensymphony.xwork2.ActionContext;
import com.gits.rms.constants.Constants;
import com.gits.rms.service.TimeSheetProjeectAssignedDaoService;
import com.gits.rms.service.TimeSheetProjeectAssignedService;
import com.gits.rms.vo.CustomerVO;
import com.gits.rms.vo.EmployeeExpensesVO;
import com.gits.rms.vo.EmployeesCountVO;
import com.gits.rms.vo.EmployeesVO;
import com.gits.rms.vo.ImportantNewsVO;
import com.gits.rms.vo.ProjectAssignEmpVO;
import com.gits.rms.vo.ProjectVO;
import com.gits.rms.vo.ReportsVO;

public class EmployeesHibernateDao implements EmployeesDao {

    private List<EmployeesVO> employeeList;
    private TimeSheetProjeectAssignedService projAssService = new TimeSheetProjeectAssignedDaoService();

    @Override
    public void deleteEmployees(EmployeesVO employee) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeesVO set updatedBy=:UpdatedBy,IsActive=:IsActive where employeeId=:employeeId";
            Query query = session.createQuery(sHql);
            query.setInteger("UpdatedBy", employee.getUpdatedBy().getEmployeeId());
            query.setInteger("IsActive", 0);
            query.setInteger("employeeId", employee.getEmployeeId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void deleteEmployeesPic(EmployeesVO employee) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeesVO set empPicturePath=:empPicturePath where HcmoEmployeeId=:HcmoEmployeeId";
            Query query = session.createQuery(sHql);
            query.setString("empPicturePath", null);
            query.setInteger("HcmoEmployeeId", employee.getEmployeeId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List employeeSearchResult(EmployeesVO employee) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            if (!(employee.getEmpFirstName().isEmpty())) {
                crit.add(Restrictions.like("empFirstName", employee.getEmpFirstName(), MatchMode.ANYWHERE));
            }
            if (!(employee.getEmpLastName().isEmpty())) {
                crit.add(Restrictions.like("empLastName", employee.getEmpLastName(), MatchMode.ANYWHERE));
            }

            if (employee.getEmpBirthDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((employee.getEmpBirthDate() != null)
                    && (employee.getEmpBirthDateEnds() != null)) {
                    crit.add(Restrictions.ge("empBirthDate", employee.getEmpBirthDate()));
                    employee.getMessage().add("DOB");
                } else if ((employee.getEmpBirthDate() != null)
                    && (employee.getEmpBirthDateEnds() == null)) {
                    crit.add(Restrictions.ge("empBirthDate", employee.getEmpBirthDate()));
                } else if ((employee.getEmpBirthDateEnds() != null)
                    && (employee.getEmpBirthDate() == null)) {
                    crit.add(Restrictions.ge("empBirthDate", employee.getEmpBirthDateEnds()));
                }
            } else if (employee.getEmpBirthDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((employee.getEmpBirthDate() != null)
                    && (employee.getEmpBirthDateEnds() != null)) {
                    crit.add(Restrictions.le("empBirthDate", employee.getEmpBirthDate()));
                    employee.getMessage().add("DOB");
                } else if ((employee.getEmpBirthDate() != null)
                    && (employee.getEmpBirthDateEnds() == null)) {
                    crit.add(Restrictions.le("empBirthDate", employee.getEmpBirthDate()));
                } else if ((employee.getEmpBirthDateEnds() != null)
                    && (employee.getEmpBirthDate() == null)) {
                    crit.add(Restrictions.le("empBirthDate", employee.getEmpBirthDateEnds()));
                }
            } else if (employee.getEmpBirthDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((employee.getEmpBirthDate() != null)
                    && (employee.getEmpBirthDateEnds() != null)) {
                    crit.add(Restrictions.eq("empBirthDate", employee.getEmpBirthDate()));
                    employee.getMessage().add("DOB");
                } else if ((employee.getEmpBirthDate() != null)
                    && (employee.getEmpBirthDateEnds() == null)) {
                    crit.add(Restrictions.eq("empBirthDate", employee.getEmpBirthDate()));
                } else if ((employee.getEmpBirthDateEnds() != null)
                    && (employee.getEmpBirthDate() == null)) {
                    crit.add(Restrictions.eq("empBirthDate", employee.getEmpBirthDateEnds()));
                }
            } else if (employee.getEmpBirthDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((employee.getEmpBirthDate() != null)
                    && (employee.getEmpBirthDateEnds() != null)) {
                    if (employee.getEmpBirthDate().before(employee.getEmpBirthDateEnds())) {
                        crit.add(Restrictions.between("empBirthDate", employee.getEmpBirthDate(), employee.getEmpBirthDateEnds()));
                    } else if (employee.getEmpBirthDate().after(employee.getEmpBirthDateEnds())) {
                        crit.add(Restrictions.between("empBirthDate", employee.getEmpBirthDateEnds(), employee.getEmpBirthDate()));
                    } else if (employee.getEmpBirthDate().equals(employee.getEmpBirthDateEnds())) {
                        crit.add(Restrictions.eq("empBirthDate", employee.getEmpBirthDate()));
                    }
                } else if ((employee.getEmpBirthDate() != null)
                    && (employee.getEmpBirthDateEnds() == null)) {
                    crit.add(Restrictions.eq("empBirthDate", employee.getEmpBirthDate()));
                } else if ((employee.getEmpBirthDateEnds() != null)
                    && (employee.getEmpBirthDate() == null)) {
                    crit.add(Restrictions.eq("empBirthDate", employee.getEmpBirthDateEnds()));
                }
            }

            if (employee.getEmpGender() != null) {
                crit.add(Restrictions.eq("empGender", employee.getEmpGender()));
            }

            if (!(employee.getEmpMaritalStatus().isEmpty())) {
                crit.add(Restrictions.eq("empMaritalStatus", employee.getEmpMaritalStatus()));
            }

            if (!(employee.getEmpSpace().isEmpty())) {
                crit.add(Restrictions.eq("empSpace", employee.getEmpSpace()));
            }

            if (employee.getEthnicRaceIdObj().getEthnicRaceId() != null) {
                crit.add(Restrictions.eq("ethnicRaceIdObj.ethnicRaceId", employee.getEthnicRaceIdObj().getEthnicRaceId()));
            }

            if (employee.getCountry().getHcmocountryId() != null) {
                crit.add(Restrictions.eq("country.hcmocountryId", employee.getCountry().getHcmocountryId()));
            }

            if (employee.getDepartmentIdObj().getHcmoDepartmentId() != null) {
                crit.add(Restrictions.eq("departmentIdObj.hcmoDepartmentId", employee.getDepartmentIdObj().getHcmoDepartmentId()));
            }

            if (employee.getTeamIdObj().getHcmoTeamId() != null) {
                crit.add(Restrictions.eq("teamIdObj.hcmoTeamId", employee.getTeamIdObj().getHcmoTeamId()));
            }

            if (employee.getEmpStatusIdObj().getEmpStatusId() != null) {
                crit.add(Restrictions.eq("empStatusIdObj.empStatusId", employee.getEmpStatusIdObj().getEmpStatusId()));
            }

            if (!(employee.getEmpDriLicenNum().isEmpty())) {
                crit.add(Restrictions.like("empDriLicenNum", employee.getEmpDriLicenNum(), MatchMode.ANYWHERE));
            }

            if (employee.getEmpDriLicenExpDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((employee.getEmpDriLicenExpDate() != null)
                    && (employee.getEmpDriLicenExpDateEnds() != null)) {
                    crit.add(Restrictions.ge("empDriLicenExpDate", employee.getEmpDriLicenExpDate()));
                    employee.getMessage().add("licenseExpireDate");
                    ServletActionContext.getRequest().setAttribute("Msg", "Message");
                } else if ((employee.getEmpDriLicenExpDate() != null)
                    && (employee.getEmpDriLicenExpDateEnds() == null)) {
                    crit.add(Restrictions.ge("empDriLicenExpDate", employee.getEmpDriLicenExpDate()));
                } else if ((employee.getEmpDriLicenExpDateEnds() != null)
                    && (employee.getEmpDriLicenExpDate() == null)) {
                    crit.add(Restrictions.ge("empDriLicenExpDate", employee.getEmpDriLicenExpDateEnds()));
                }
            } else if (employee.getEmpDriLicenExpDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((employee.getEmpDriLicenExpDate() != null)
                    && (employee.getEmpDriLicenExpDateEnds() != null)) {
                    crit.add(Restrictions.le("empDriLicenExpDate", employee.getEmpDriLicenExpDate()));
                    employee.getMessage().add("licenseExpireDate");
                } else if ((employee.getEmpDriLicenExpDate() != null)
                    && (employee.getEmpDriLicenExpDateEnds() == null)) {
                    crit.add(Restrictions.le("empDriLicenExpDate", employee.getEmpDriLicenExpDate()));
                } else if ((employee.getEmpDriLicenExpDateEnds() != null)
                    && (employee.getEmpDriLicenExpDate() == null)) {
                    crit.add(Restrictions.le("empDriLicenExpDate", employee.getEmpDriLicenExpDateEnds()));
                }
            } else if (employee.getEmpDriLicenExpDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((employee.getEmpDriLicenExpDate() != null)
                    && (employee.getEmpDriLicenExpDateEnds() != null)) {
                    crit.add(Restrictions.eq("empDriLicenExpDate", employee.getEmpDriLicenExpDate()));
                    employee.getMessage().add("licenseExpireDate");
                } else if ((employee.getEmpDriLicenExpDate() != null)
                    && (employee.getEmpDriLicenExpDateEnds() == null)) {
                    crit.add(Restrictions.eq("empDriLicenExpDate", employee.getEmpDriLicenExpDate()));
                } else if ((employee.getEmpDriLicenExpDateEnds() != null)
                    && (employee.getEmpDriLicenExpDate() == null)) {
                    crit.add(Restrictions.eq("empDriLicenExpDate", employee.getEmpDriLicenExpDateEnds()));
                }
            } else if (employee.getEmpDriLicenExpDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((employee.getEmpDriLicenExpDate() != null)
                    && (employee.getEmpDriLicenExpDateEnds() != null)) {
                    if (employee.getEmpDriLicenExpDate().before(employee.getEmpDriLicenExpDateEnds())) {
                        crit.add(Restrictions.between("empDriLicenExpDate", employee.getEmpDriLicenExpDate(), employee.getEmpDriLicenExpDateEnds()));
                    } else if (employee.getEmpDriLicenExpDate().after(employee.getEmpDriLicenExpDateEnds())) {
                        crit.add(Restrictions.between("empDriLicenExpDate", employee.getEmpDriLicenExpDateEnds(), employee.getEmpDriLicenExpDate()));
                    } else if (employee.getEmpDriLicenExpDate().equals(employee.getEmpDriLicenExpDateEnds())) {
                        crit.add(Restrictions.eq("empDriLicenExpDate", employee.getEmpDriLicenExpDate()));
                    }
                } else if ((employee.getEmpDriLicenExpDate() != null)
                    && (employee.getEmpDriLicenExpDateEnds() == null)) {
                    crit.add(Restrictions.eq("empDriLicenExpDate", employee.getEmpDriLicenExpDate()));
                } else if ((employee.getEmpDriLicenExpDateEnds() != null)
                    && (employee.getEmpDriLicenExpDate() == null)) {
                    crit.add(Restrictions.eq("empDriLicenExpDate", employee.getEmpDriLicenExpDateEnds()));
                }
            }

            if (employee.getNationalityIdObj().getNationalityId() != null) {
                crit.add(Restrictions.eq("nationalityIdObj.nationalityId", employee.getNationalityIdObj().getNationalityId()));
            }

            if (!(employee.getEmpZipCode().isEmpty())) {
                crit.add(Restrictions.eq("empZipCode", employee.getEmpZipCode()));
            }

            if (!(employee.getEmpCityName().isEmpty())) {
                crit.add(Restrictions.eq("empCityName", employee.getEmpCityName()));
            }

            if (!(employee.getEmpCounName().isEmpty())) {
                crit.add(Restrictions.eq("empCounName", employee.getEmpCounName()));
            }

            if (!(employee.getEmpStreet1().isEmpty())) {
                crit.add(Restrictions.eq("empStreet1", employee.getEmpStreet1()));
            }

            if (!(employee.getEmpHmTelephone().isEmpty())) {
                crit.add(Restrictions.eq("empHmTelephone", employee.getEmpHmTelephone()));
            }
            if (!(employee.getEmpMobile().isEmpty())) {
                crit.add(Restrictions.eq("empMobile", employee.getEmpMobile()));
            }

            if (employee.getRoleObj().getHcmoRoleId() != null) {
                crit.add(Restrictions.eq("roleObj.hcmoRoleId", employee.getRoleObj().getHcmoRoleId()));
            }

            if (employee.getJobTitleIdObj().getJobTitleId() != null) {
                crit.add(Restrictions.eq("jobTitleIdObj.jobTitleId", employee.getJobTitleIdObj().getJobTitleId()));
            }

            if (!(employee.getEmpWorkEmail().isEmpty())) {
                crit.add(Restrictions.like("empWorkEmail", employee.getEmpWorkEmail(), MatchMode.ANYWHERE));
            }

            if (employee.getEmpJoinedDateValue().equals(Constants.SEARCH_GREATER)) {
                if ((employee.getEmpJoineddate() != null)
                    && (employee.getEmpJoinedDateEnds() != null)) {
                    crit.add(Restrictions.ge("empJoineddate", employee.getEmpJoineddate()));
                    employee.getMessage().add("JoinedDate");
                    ServletActionContext.getRequest().setAttribute("Msg", "Message");
                } else if ((employee.getEmpJoineddate() != null)
                    && (employee.getEmpJoinedDateEnds() == null)) {
                    crit.add(Restrictions.ge("empJoineddate", employee.getEmpJoineddate()));
                } else if ((employee.getEmpJoinedDateEnds() != null)
                    && (employee.getEmpJoineddate() == null)) {
                    crit.add(Restrictions.ge("empJoineddate", employee.getEmpJoinedDateEnds()));
                }
            } else if (employee.getEmpJoinedDateValue().equals(Constants.SEARCH_LESSER)) {
                if ((employee.getEmpJoineddate() != null)
                    && (employee.getEmpJoinedDateEnds() != null)) {
                    crit.add(Restrictions.le("empJoineddate", employee.getEmpJoineddate()));
                    employee.getMessage().add("JoinedDate");
                } else if ((employee.getEmpJoineddate() != null)
                    && (employee.getEmpJoinedDateEnds() == null)) {
                    crit.add(Restrictions.le("empJoineddate", employee.getEmpJoineddate()));
                } else if ((employee.getEmpJoinedDateEnds() != null)
                    && (employee.getEmpJoineddate() == null)) {
                    crit.add(Restrictions.le("empJoineddate", employee.getEmpJoinedDateEnds()));
                }
            } else if (employee.getEmpJoinedDateValue().equals(Constants.SEARCH_EQUALS)) {
                if ((employee.getEmpJoineddate() != null)
                    && (employee.getEmpJoinedDateEnds() != null)) {
                    crit.add(Restrictions.eq("empJoineddate", employee.getEmpJoineddate()));
                    employee.getMessage().add("JoinedDate");
                } else if ((employee.getEmpJoineddate() != null)
                    && (employee.getEmpJoinedDateEnds() == null)) {
                    crit.add(Restrictions.eq("empJoineddate", employee.getEmpJoineddate()));
                } else if ((employee.getEmpJoinedDateEnds() != null)
                    && (employee.getEmpJoineddate() == null)) {
                    crit.add(Restrictions.eq("empJoineddate", employee.getEmpJoinedDateEnds()));
                }
            } else if (employee.getEmpJoinedDateValue().equals(Constants.SEARCH_BETWEEN)) {
                if ((employee.getEmpJoineddate() != null)
                    && (employee.getEmpJoinedDateEnds() != null)) {
                    if (employee.getEmpJoineddate().before(employee.getEmpJoinedDateEnds())) {
                        crit.add(Restrictions.between("empJoineddate", employee.getEmpJoineddate(), employee.getEmpJoinedDateEnds()));
                    } else if (employee.getEmpJoineddate().after(employee.getEmpJoinedDateEnds())) {
                        crit.add(Restrictions.between("empJoineddate", employee.getEmpJoinedDateEnds(), employee.getEmpJoineddate()));
                    } else if (employee.getEmpJoineddate().equals(employee.getEmpJoinedDateEnds())) {
                        crit.add(Restrictions.eq("empJoineddate", employee.getEmpJoineddate()));
                    }
                } else if ((employee.getEmpJoineddate() != null)
                    && (employee.getEmpJoinedDateEnds() == null)) {
                    crit.add(Restrictions.eq("empJoineddate", employee.getEmpJoineddate()));
                } else if ((employee.getEmpJoinedDateEnds() != null)
                    && (employee.getEmpJoineddate() == null)) {
                    crit.add(Restrictions.eq("empJoineddate", employee.getEmpJoinedDateEnds()));
                }
            }
            crit.add(Restrictions.eq("isActive", 1));
            List list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<EmployeesVO> getAdminEmpListSize(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.add(Restrictions.eq("roleObj.hcmoRoleId", id));
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEmployees(Integer clientId) {
        Session session = HibernateUtil.getSession();
        
        Log.debug("clientId : " + clientId);
        try {
//        	 session.put("CLIENT_INFO",userCheck.getEmpIdObj().getClientId());
            Criteria crit = session.createCriteria(EmployeesVO.class);
            crit.addOrder(Order.asc("empFirstName"));
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("clientId", clientId));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getAllEmployeesGrouped() {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from EmployeesVO as emp left join fetch emp.teamIdObj left join fetch emp.departmentIdObj left join fetch emp.country left join fetch emp.ethnicRaceIdObj as ethnic left join fetch emp.nationalityIdObj as nati left join fetch emp.jobTitleIdObj as job left join fetch emp.roleObj as role left join fetch emp.empStatusIdObj as empstat where emp.isActive=:IsActive");
            query.setInteger("IsActive", 1);
            employeeList = query.list();

            return employeeList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getCurrentEmployee() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();

            Query query = session.createQuery("from EmployeesVO as emp where emp.employeeId=:employeeId and emp.isActive=:IsActive");
            query.setInteger("employeeId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            employeeList = query.list();

            return employeeList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getCurrentExpensesEmployee(int expenseID) {
        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        new EmployeeExpensesVO();
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as emp where emp.hcmoExpensesId=:hcmoExpensesid and emp.isActive=:IsActive");
            query.setInteger("hcmoExpensesid", expenseID);
            query.setInteger("IsActive", 1);
            employeeList = query.list();

            return employeeList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeeExpensesVO getCurrentExpensesEmployeeForMail(int expenseID) {
        Map msession = ActionContext.getContext().getSession();
        msession.get("EMPLOYEE_OBJECT");
        new EmployeeExpensesVO();
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeeExpensesVO as emp where emp.hcmoExpensesId=:hcmoExpensesid and emp.isActive=:IsActive");
            query.setInteger("hcmoExpensesid", expenseID);
            query.setInteger("IsActive", 1);
            return (EmployeeExpensesVO) query.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getCurrentExpensesSubEmployee() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeesVO as emp where emp.isActive=:IsActive and emp.employeeId in(select ea.hcmoEmployeeId from ExpensesApproverVO as ea where ea.isActive=:IsActive and ea.approvingEmployeeId =:hcmoApprovingEmpId)");
            query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            employeeList = query.list();

            return employeeList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getCurrentSubEmployee() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeesVO as emp where emp.isActive=:IsActive and emp.employeeId " +
            		"in(select ea.hcmoEmployeeId from LeaveApproverVO as ea where ea.isActive=:IsActive and ea.hcmoApprovingEmpId=:hcmoApprovingEmpId)");
            query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            employeeList = query.list();
            return employeeList;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getCurrentTimeSheetSubEmployee() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeesVO as emp where emp.isActive=:IsActive and emp.employeeId in(select t.hcmoEmployeeId from TimeSheetApproverVO as t where t.isActive=:IsActive and t.hcmoApprovingEmpId =:hcmoApprovingEmpId)");
            query.setInteger("hcmoApprovingEmpId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            employeeList = query.list();

            return employeeList;
        } finally {
            session.flush();
            session.close();
        }
    }

    public List getEmpCountListPerDept() {
        Session session = HibernateUtil.getSession();
        List<EmployeesCountVO> empCountDeptList = new ArrayList<EmployeesCountVO>();
        try {
            session.beginTransaction();

            Query query = session.createQuery("select emp.ethnicRaceDesc, count(emp.created) from EthnicRaceVO as emp where emp.isActive=:IsActive group by emp.createdBy");
            query.setInteger("IsActive", 1);

            EmployeesCountVO empCountVO;
            for (Iterator it = query.iterate(); it.hasNext();) {
                empCountVO = new EmployeesCountVO();
                Object[] row = (Object[]) it.next();
                empCountVO.setCategory(String.valueOf(row[0]));
                empCountVO.setCount(Integer.parseInt(String.valueOf(row[1])));
                empCountDeptList.add(empCountVO);
            }

            return empCountDeptList;
        } finally {
            session.flush();
            session.close();
        }
    }

    public List getEmpCountListPerYear() {
        Session session = HibernateUtil.getSession();
        List<EmployeesCountVO> empCountYearList = new ArrayList<EmployeesCountVO>();
        try {
            session.beginTransaction();

            Query query = session.createQuery("select year(emp.created), count(emp.created) from EthnicRaceVO as emp where emp.isActive=:IsActive group by year(emp.created)");
            query.setInteger("IsActive", 1);

            EmployeesCountVO empCountVO;
            for (Iterator it = query.iterate(); it.hasNext();) {
                empCountVO = new EmployeesCountVO();
                Object[] row = (Object[]) it.next();
                empCountVO.setCategory(String.valueOf(row[0]));
                empCountVO.setCount(Integer.parseInt(String.valueOf(row[1])));
                empCountYearList.add(empCountVO);
            }

            return empCountYearList;
        } finally {
            session.flush();
            session.close();
        }
    }

    public List getEmpCountSalaryGrade() {
        Session session = HibernateUtil.getSession();
        List<EmployeesCountVO> empCountSGList = new ArrayList<EmployeesCountVO>();
        try {
            session.beginTransaction();

            Query query = session.createQuery("select sal.salaryName, count(job.salaryGradeIdObj) from JobTitleVO as job left join job.salaryGradeIdObj as sal where job.isActive=:IsActive group by job.salaryGradeIdObj");
            query.setInteger("IsActive", 1);

            EmployeesCountVO empCountVO;
            for (Iterator it = query.iterate(); it.hasNext();) {
                empCountVO = new EmployeesCountVO();
                Object[] row = (Object[]) it.next();
                empCountVO.setCategory(String.valueOf(row[0]));
                empCountVO.setCount(Integer.parseInt(String.valueOf(row[1])));
                empCountSGList.add(empCountVO);
            }

            return empCountSGList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeBirthDay(Integer clientId) {
        Date date = new Date();
        Session session = HibernateUtil.getSession();
        try {
        	
            session.beginTransaction();
            Query query = session.createQuery("from EmployeesVO as emp where month(empBirthDate)>=:EmpBirthStartDate and month(empBirthDate)<=:EmpBirthEndDate and emp.isActive=:IsActive and emp.clientId=:clientId");
            query.setString("EmpBirthStartDate", String.valueOf(date.getMonth() + 1));
            query.setString("EmpBirthEndDate", String.valueOf(date.getMonth() + 2));
            query.setInteger("IsActive", 1);
            query.setInteger("clientId",clientId);
            employeeList = query.list();
            return employeeList;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeBirthdayReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        EmployeesVO newEmployeeId = new EmployeesVO();
        Map mSession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        String sReportingToEmp = String.valueOf(mSession.get("EMPLOYEE_REPORTING_TO"));

        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);
            List list = new LinkedList();
            List empId = new LinkedList();
            if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                || (oEmp.getRoleObj().getRoleName().equals("admin"))
                || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {
                if (report.getEmpIdObjList().isEmpty()) {
                    employeeList = getAllEmployees(null);
                    for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                } else {
                    for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                }
                if (!(empId.isEmpty())) {
                    crit.add(Restrictions.in("employeeId", empId));
                }
            } else if (sReportingToEmp.equals("REPORTING-EMPLOYEE")) {
                employeeList = getReportingToSubEmpList();
                employeeList.add(oEmp);
                if (report.getEmpIdObjList().isEmpty()) {
                    for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                } else {
                    for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empId.add(newEmployeeId.getEmployeeId());
                    }
                }
                if (!(empId.isEmpty())) {
                    crit.add(Restrictions.in("employeeId", empId));
                }
            }
            crit.addOrder(Order.asc("empFirstName"));
            crit.add(Restrictions.eq("isActive", 1));
            list = crit.list();
            return list;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeesVO getEmployeeById(int id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeesVO as emp where emp.isActive=:IsActive and emp.employeeId=:employeeId");
            q.setInteger("employeeId", id);
            q.setInteger("IsActive", 1);
            return (EmployeesVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    public List getEmployeeNameCombined() {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
            Query q = session.createQuery("select CONCAT(empFirstName,empLastName) as empFirstName from EmployeesVO where isActive =:isActive");
            q.setInteger("isActive", 1);
            return q.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getEmployeeReports(ReportsVO report) {
        Session session = HibernateUtil.getSession();
        Map mSession = ActionContext.getContext().getSession();

        EmployeesVO oEmp = (EmployeesVO) mSession.get("EMPLOYEE_OBJECT");
        String sReportingToEmp = String.valueOf(mSession.get("EMPLOYEE_REPORTING_TO"));

        EmployeesVO newEmployeeId = new EmployeesVO();
        ProjectVO projectId = new ProjectVO();
        ProjectAssignEmpVO projectAssignedId = new ProjectAssignEmpVO();
        CustomerVO customerId = new CustomerVO();

        List empIdList = new LinkedList();
        List projectIdList = new LinkedList();
        List empProAssignedList = new LinkedList();
        List customerIdList = new LinkedList();

        // Create a HashSet which allows no duplicates
        HashSet finalEmpIdHashSet = new HashSet();
        // Assign the HashSet to a new ArrayList
        ArrayList finalEmpIdList = new ArrayList();

        try {
            Criteria crit = session.createCriteria(EmployeesVO.class);

            if ((oEmp.getRoleObj().getRoleName().equals("Admin"))
                || (oEmp.getRoleObj().getRoleName().equals("admin"))
                || (oEmp.getRoleObj().getRoleName().equals("ADMIN"))) {
                // If None of the employee name and project name
                // is being selected
                if ((report.getEmpIdObjList().isEmpty()) && (report.getProjIdObjList().isEmpty())
                    && (report.getCustIdObjList().isEmpty())) {
                    employeeList = getAllEmployees(null);
                    for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                    finalEmpIdHashSet.addAll(empIdList);
                }

                // If employee name is being selected
                if (!(report.getEmpIdObjList().isEmpty())) {
                    // Iterating the selected list of
                    // employees
                    for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                    finalEmpIdHashSet.addAll(empIdList);
                }

                // If Project name is being selected
                if (!(report.getProjIdObjList().isEmpty())) {
                    // Iterating the selected list of
                    // projects
                    for (Iterator<ProjectVO> it = report.getProjIdObjList().iterator(); it.hasNext();) {
                        projectId = it.next();
                        projectIdList.add(projectId.getProjectId());
                    }

                    Criteria critAssignedProject = session.createCriteria(ProjectAssignEmpVO.class);
                    critAssignedProject.add(Restrictions.in("projectName.projectId", projectIdList));
                    critAssignedProject.add(Restrictions.eq("isActive", 1));

                    for (Iterator<ProjectAssignEmpVO> it = critAssignedProject.list().iterator(); it.hasNext();) {
                        projectAssignedId = it.next();
                        empProAssignedList.add(projectAssignedId.getEmployeeName().getEmployeeId());
                    }
                    if (empProAssignedList.isEmpty()) {
                        empProAssignedList.add(0);
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    } else {
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    }
                }

                if (!(report.getCustIdObjList().isEmpty())) {
                    for (Iterator<CustomerVO> it = report.getCustIdObjList().iterator(); it.hasNext();) {
                        customerId = it.next();
                        customerIdList.add(customerId.getCustomerId());
                    }

                    // Get the Project id from by passing
                    // customer id
                    Criteria critProject = session.createCriteria(ProjectVO.class);
                    critProject.add(Restrictions.in("customerId.customerId", customerIdList));
                    critProject.add(Restrictions.eq("isActive", 1));
                    for (Iterator<ProjectVO> it = critProject.list().iterator(); it.hasNext();) {
                        projectId = it.next();
                        projectIdList.add(projectId.getProjectId());
                    }
                    if (projectIdList.isEmpty()) {
                        projectIdList.add(0);
                    }

                    Criteria critAssignedProject = session.createCriteria(ProjectAssignEmpVO.class);
                    critAssignedProject.add(Restrictions.in("projectName.projectId", projectIdList));
                    critAssignedProject.add(Restrictions.eq("isActive", 1));

                    for (Iterator<ProjectAssignEmpVO> it = critAssignedProject.list().iterator(); it.hasNext();) {
                        projectAssignedId = it.next();
                        empProAssignedList.add(projectAssignedId.getEmployeeName().getEmployeeId());
                    }
                    if (empProAssignedList.isEmpty()) {
                        empProAssignedList.add(0);
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    } else {
                        finalEmpIdHashSet.addAll(empProAssignedList);
                    }
                }

                finalEmpIdList.addAll(finalEmpIdHashSet);
                crit.add(Restrictions.in("employeeId", finalEmpIdList));

            } else if (sReportingToEmp.equals("REPORTING-EMPLOYEE")) {
                employeeList = getReportingToSubEmpList();
                employeeList.add(oEmp);
                if (report.getEmpIdObjList().isEmpty()) {
                    for (Iterator<EmployeesVO> it = employeeList.iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                } else {
                    for (Iterator<EmployeesVO> it = report.getEmpIdObjList().iterator(); it.hasNext();) {
                        newEmployeeId = it.next();
                        empIdList.add(newEmployeeId.getEmployeeId());
                    }
                }
                if (!(empIdList.isEmpty())) {
                    crit.add(Restrictions.in("employeeId", empIdList));
                }
            } else {
                crit.add(Restrictions.eq("employeeId", oEmp.getEmployeeId()));
            }
            crit.add(Restrictions.eq("isActive", 1));
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeesVO getEmployees(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeesVO as emp left join fetch emp.teamIdObj left join fetch emp.departmentIdObj left join fetch emp.country left join fetch emp.ethnicRaceIdObj ehtnic left join fetch emp.nationalityIdObj nati left join fetch emp.jobTitleIdObj job left join fetch emp.roleObj as role left join fetch emp.empStatusIdObj as empstat where emp.employeeId =:employeeId");
            q.setInteger("employeeId", id);
            return (EmployeesVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeesVO getOrgChartEmployee(Integer id) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeesVO as emp left join fetch emp.teamIdObj left join fetch emp.departmentIdObj left join fetch emp.country left join fetch emp.ethnicRaceIdObj ehtnic left join fetch emp.nationalityIdObj nati left join fetch emp.jobTitleIdObj job left join fetch emp.roleObj as role left join fetch emp.empStatusIdObj as empstat where emp.employeeId =:employeeId");
            q.setInteger("employeeId", id);
            return (EmployeesVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getReportingToSubEmpList() {
        Map msession = ActionContext.getContext().getSession();
        EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from EmployeesVO as emp where emp.isActive=:IsActive and emp.employeeId in(select rep.empIdObj.employeeId from EmployeeReportToVO as rep where rep.isActive=:IsActive and rep.supEmpNumber =:supEmpId)");
            // "from EmployeesVO as emp where emp.isActive=:IsActive and emp.employeeId in(select ea.hcmoEmployeeId from LeaveApproverVO as ea where ea.isActive=:IsActive and ea.hcmoApprovingEmpId =:hcmoApprovingEmpId)");
            query.setInteger("supEmpId", oEmp.getEmployeeId());
            query.setInteger("IsActive", 1);
            employeeList = query.list();
            return employeeList;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getSelectedImportantNotes(Integer clientId) {
        Session session = HibernateUtil.getSession();
        try {
            Criteria crit = session.createCriteria(ImportantNewsVO.class);
            crit.add(Restrictions.eq("showMessage", true));
            crit.add(Restrictions.eq("isActive", 1));
            crit.add(Restrictions.eq("clientId", clientId));
            
            return crit.list();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List getTodaysBirthDayEmployeeList() {
        Date date = new Date();
        Session session = HibernateUtil.getSession();
        try {

            session.beginTransaction();
            Query query = session.createQuery("from EmployeesVO as emp where month(empBirthDate)>=:EmpBirthStartDate and month(empBirthDate)<=:EmpBirthEndDate and emp.isActive=:IsActive");
            query.setString("EmpBirthStartDate", String.valueOf(date.getMonth() + 1));
            query.setString("EmpBirthEndDate", String.valueOf(date.getMonth() + 2));
            query.setInteger("IsActive", 1);
            employeeList = query.list();
            return employeeList;

        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public EmployeesVO insertEmployees(EmployeesVO employee) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        Boolean isUnique = false;
        try {
            tx = session.beginTransaction();
            session.save(employee);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            isUnique = true;
            throw e;

        } finally {
            if (isUnique) {
                session.close();
            } else {
                session.flush();
                session.close();
            }
        }
        return employee;
    }
    
    @Override
    public EmployeesVO updateEmployeeProfile(EmployeesVO employee) {
    	Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
    /*        String sHql = "update EmployeesVO set EmpLastName=:EmpLastName, "
                + "EmpFirstName=:EmpFirstName, " + "EmpMiddleName=:EmpMiddleName, "
                + "EmpNickName=:EmpNickName, " + "EmpFullName=:EmpFullName, "
                + "EmpBirthDay=:EmpBirthDay, "
                + "EmpGender=:EmpGender, " 
                + "EmpType=:EmpType, "
                + "EmpStreet1=:EmpStreet1, "
                + "CityName=:CityName, " + "CounName=:CounName, "
                + "EmpMobile=:EmpMobile, " 
                + "EmpWorkEmail=:EmpWorkEmail, " 
                + "HcmoRoleId=:HcmoRoleId, "
                + "departmentIdObj=:DepartmentIdObj, " + "UpdatedBy=:UpdatedBy, " 
                + "documentIdObj=:documentIdObj," + "accessType=:accessType,"
                + "rLiteAccess=:rLiteAccess "
                //+ "where HcmoEmployeeId=:HcmoEmployeeId";
                + "where employeeId=:HcmoEmployeeId";
            
                Query query = session.createQuery(sHql);
                query.setString("EmpLastName", employee.getEmpLastName());
                query.setString("EmpFirstName", employee.getEmpFirstName());
                query.setString("EmpFullName", employee.getEmpFirstName() + " "
                    + employee.getEmpLastName() + ",(" + employee.getEmpWorkEmail() + ")");
                query.setDate("EmpBirthDay", employee.getEmpBirthDate());
                query.setString("EmpGender", employee.getEmpGender());
                query.setString("EmpType", employee.getEmpType());
                query.setString("EmpStreet1", employee.getEmpStreet1());
                query.setString("CityName", employee.getEmpCityName());
                query.setString("CounName", employee.getEmpCounName());
                query.setString("EmpMobile", employee.getEmpMobile());
                query.setString("EmpWorkEmail", employee.getEmpWorkEmail());
                query.setInteger("HcmoRoleId", employee.getRoleObj().getHcmoRoleId());
                query.setInteger("DepartmentIdObj", employee.getDepartmentIdObj().getHcmoDepartmentId());
                query.setInteger("UpdatedBy", employee.getUpdatedBy().getEmployeeId());
                query.setInteger("documentIdObj", employee.getDocumentIdObj().getHcmoDocumentId());
                query.setString("accessType",employee.getAccessType());
                query.setBoolean("rLiteAccess", employee.getrLiteAccess());
                query.setInteger("HcmoEmployeeId", employee.getEmployeeId());
                query.executeUpdate();*/
            
            	session.saveOrUpdate(employee);
                tx.commit();
            } catch (RuntimeException e) {
                if (tx != null) {                
                    tx.rollback();
                }
                e.printStackTrace();
                throw e;
            } finally {
                session.flush();
                session.close();
            }
        return employee;
    }
    @Override
    public void updateEmployees(EmployeesVO employee) {

        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeesVO set EmpLastName=:EmpLastName, "
                + "EmpFirstName=:EmpFirstName, " + "EmpMiddleName=:EmpMiddleName, "
                + "EmpNickName=:EmpNickName, " + "EmpFullName=:EmpFullName, "
                + "EmpSmoker=:EmpSmoker, " + "EmpBirthDay=:EmpBirthDay, "
                + "EmpGender=:EmpGender, " + "EmpMaritalStatus=:EmpMaritalStatus, "
                + "EmpType=:EmpType, " + "EmpSSNNum=:EmpSSNNum, " + "EmpOtherId=:EmpOtherId, "
                + "EmpOtherName=:EmpOtherName, " + "EmpDriLiceNum=:EmpDriLiceNum, "
                + "EmpDriLiceExpDate=:EmpDriLiceExpDate, "
                + "EmpMilitaryService=:EmpMilitaryService, " + "EmpStreet1=:EmpStreet1, "
                + "EmpStreet2=:EmpStreet2, " + "CityName=:CityName, " + "CounName=:CounName, "
                + "EmpZipCode=:EmpZipCode, " + "EmpHmTelephone=:EmpHmTelephone, "
                + "EmpMobile=:EmpMobile, " + "EmpWorkTelephone=:EmpWorkTelephone, "
                + "EmpWorkEmail=:EmpWorkEmail, " + "JoinedDate=:JoinedDate, "
                + "EmpOthEmail=:EmpOthEmail, " + "TerminatedDate=:TerminatedDate, "
                + "TerminationReason=:TerminationReason, " + "Custom1=:Custom1, "
                + "Custom2=:Custom2, " + "HcmoEthnicRaceId=:HcmoEthnicRaceId, "
                + "HcmoVendor=:HcmoVendor, " + "HcmoNationalityId=:HcmoNationalityId, "
                + "HcmoJobTitleId=:HcmoJobTitleId, " + "HcmoRoleId=:HcmoRoleId, "
                + "HcmoEmpStatusId=:HcmoEmpStatusId, " + "employee_wage=:employee_wage, "
                + "country=:country, " + "departmentIdObj=:DepartmentIdObj, "  + "empPicturePath=:EmpPicturePath, "
                + "teamIdObj=:TeamIdObj, " + "empSpace=:EmpSpace, " + "UpdatedBy=:UpdatedBy "
                + "where HcmoEmployeeId=:HcmoEmployeeId";
            Query query = session.createQuery(sHql);
            query.setString("EmpLastName", employee.getEmpLastName());
            query.setString("EmpFirstName", employee.getEmpFirstName());
            query.setString("EmpMiddleName", employee.getEmpMidName());
            query.setString("EmpNickName", employee.getEmpNickName());
            query.setString("EmpFullName", employee.getEmpFirstName() + " "
                + employee.getEmpLastName() + ",(" + employee.getEmpWorkEmail() + ")");
            query.setBoolean("EmpSmoker", employee.isEmpSmoker());
            query.setDate("EmpBirthDay", employee.getEmpBirthDate());
            query.setString("EmpGender", employee.getEmpGender());
            query.setString("EmpMaritalStatus", employee.getEmpMaritalStatus());
            query.setString("EmpType", employee.getEmpType());
            query.setString("EmpSSNNum", employee.getEmpSSNNumber());
            query.setString("EmpOtherId", employee.getEmpOtherId());
            query.setString("EmpOtherName", employee.getEmpOtherName());
            query.setString("EmpDriLiceNum", employee.getEmpDriLicenNum());
            query.setDate("EmpDriLiceExpDate", employee.getEmpDriLicenExpDate());
            query.setString("EmpMilitaryService", employee.getEmpMilitaryService());
            query.setString("EmpStreet1", employee.getEmpStreet1());
            query.setString("EmpStreet2", employee.getEmpStreet2());
            query.setString("CityName", employee.getEmpCityName());
            query.setString("CounName", employee.getEmpCounName());
            query.setString("EmpZipCode", employee.getEmpZipCode());
            query.setString("EmpHmTelephone", employee.getEmpHmTelephone());
            query.setString("EmpMobile", employee.getEmpMobile());
            query.setString("EmpWorkTelephone", employee.getEmpWorkTelephone());
            query.setString("EmpWorkEmail", employee.getEmpWorkEmail());
            query.setDate("JoinedDate", employee.getEmpJoineddate());
            query.setString("EmpOthEmail", employee.getEmpOthEmail());
            query.setDate("TerminatedDate", employee.getEmpTerminatedDate());
            query.setString("TerminationReason", employee.getEmpTerminatedReason());
            query.setString("Custom1", employee.getEmpCustom1());
            query.setString("Custom2", employee.getEmpCustom2());
            query.setInteger("HcmoEthnicRaceId", employee.getEthnicRaceIdObj().getEthnicRaceId());
            query.setInteger("HcmoVendor", employee.getVendorIdObj().getVendorId());
            query.setInteger("HcmoNationalityId", employee.getNationalityIdObj().getNationalityId());
            query.setInteger("HcmoJobTitleId", employee.getJobTitleIdObj().getJobTitleId());
            query.setInteger("HcmoRoleId", employee.getRoleObj().getHcmoRoleId());
            query.setInteger("HcmoEmpStatusId", employee.getEmpStatusIdObj().getEmpStatusId());
            query.setInteger("country", employee.getCountry().getHcmocountryId());
            query.setInteger("DepartmentIdObj", employee.getDepartmentIdObj().getHcmoDepartmentId());
            query.setInteger("TeamIdObj", employee.getTeamIdObj().getHcmoTeamId());
            query.setString("EmpSpace", employee.getEmpSpace());
            query.setString("employee_wage", employee.getEmployeeWage());
            query.setString("EmpPicturePath", employee.getEmpPicturePath());
            query.setInteger("UpdatedBy", employee.getUpdatedBy().getEmployeeId());
            query.setInteger("HcmoEmployeeId", employee.getEmployeeId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void uploadEmployeesPic(EmployeesVO employee) {
        Session session = HibernateUtil.getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String sHql = "update EmployeesVO set empPicturePath=:empPicturePath where HcmoEmployeeId=:HcmoEmployeeId";
            Query query = session.createQuery(sHql);
            query.setString("empPicturePath", employee.getEmpPicturePath());
            query.setInteger("HcmoEmployeeId", employee.getEmployeeId());
            query.executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }
    
    @Override
    public EmployeesVO getEmployeeByEmailId(String emailId) {
        Session session = HibernateUtil.getSession();
        try {
            session.beginTransaction();
            Query q = session.createQuery("from EmployeesVO as emp where emp.isActive=:IsActive and emp.empWorkEmail=:empWorkEmail");
            q.setString("empWorkEmail", emailId);
            q.setInteger("IsActive", 1);
            return (EmployeesVO) q.uniqueResult();
        } finally {
            session.flush();
            session.close();
        }
    }

}
