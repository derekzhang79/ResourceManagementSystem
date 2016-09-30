package com.gits.rms.persistence;
//package com.gits.rms.persistence;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import org.hibernate.Query;
//import org.hibernate.Session;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.gits.rms.vo.ChildrenVO;
//import com.gits.rms.vo.EducationVO;
//import com.gits.rms.vo.EmployeesVO;
//import com.gits.rms.vo.LeaveHistoryVO;
//import com.gits.rms.vo.ReportsVO;
//
//public class RepotsHibernateDao implements ReportsDao{
//	
//	private List<LeaveHistoryVO> reportLeaveHistList;
//	private List<EducationVO> reportEduList;
//	private List<ChildrenVO> reportChildList;
//
//	 public List generateLeaveHistReports(ReportsVO report){
//		 
//			/*Map msession = ActionContext.getContext().getSession();
//			EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
//			Session session = HibernateUtil.getSession();
//			try {
//				session.beginTransaction();
//					Query query = session.createQuery(
//					"from LeaveHistoryVO as lev left join fetch lev.empIdObj as emp left join fetch lev.leaveTypeIdObj where lev.isActive=:IsActive and (lev.leaveDate >=:fromDate and lev.leaveDate<=:toDate) and lev.empIdObj=:empIdObj");
//						query.setInteger("IsActive", 1);
//						query.setInteger("empIdObj",report.getEmpObj().getEmployeeId());
//						query.setDate("fromDate", report.getFromDate());
//						query.setDate("toDate",report.getToDate());
//						reportLeaveHistList = query.list();*/
//						return reportLeaveHistList;
////				} finally {
////					session.flush();session.close();
////			}
// 		}
//	 
//	 public List generateEducationReports(ReportsVO report){
//		/*Map msession = ActionContext.getContext().getSession();
//		EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
//		Session session = HibernateUtil.getSession();
//		 try{ 
//			 Query query1 = session.createQuery(
//			"from EducationVO as edu left join fetch edu.empIdObj where edu.isActive=:IsActive and (edu.created >=:fromDate and edu.created<=:toDate) and edu.empIdObj=:empIdObj");
//				query1.setInteger("empIdObj",report.getEmpObj().getEmployeeId());
//				query1.setDate("fromDate", report.getFromDate());
//				query1.setDate("toDate",report.getToDate());
//				query1.setInteger("IsActive", 1);
//				reportEduList = query1.list();
//				*/
//				return reportEduList;
////	 	} finally {
////			session.flush();session.close();
////	 	}
//	 }
//	 
//	 
//	 public List generateChildrenReports(ReportsVO report){
//			/*Map msession = ActionContext.getContext().getSession();
//			EmployeesVO oEmp = (EmployeesVO) msession.get("EMPLOYEE_OBJECT");
//			Session session = HibernateUtil.getSession();
//		 try{
//			Query query2 = session.createQuery(
//			"from ChildrenVO as chi left join fetch chi.empIdObj where chi.isActive=:IsActive and (chi.created >=:fromDate and chi.created<=:toDate) and chi.empIdObj=:empIdObj");
//					query2.setInteger("empIdObj",report.getEmpObj().getEmployeeId());
//					query2.setDate("fromDate", report.getFromDate());
//					query2.setDate("toDate",report.getToDate());
//					query2.setInteger("IsActive", 1);
//					reportChildList = query2.list();
//					*/
//					return reportChildList;
////		 } finally {
////				session.flush();session.close();
////		 	}
//	 }
//	 
// }
