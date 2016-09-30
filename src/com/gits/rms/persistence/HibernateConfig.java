
package com.gits.rms.persistence;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {

    public static SessionFactory setup(String sDbName, String sUserName, String sPass) {
        Properties prop = new Properties();
        prop.put("hibernate.connection.useUnicode", "true");
        prop.put("hibernate.connection.characterEncoding", "UTF-8");
        prop.put("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        prop.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/" + sDbName);
        prop.put("hibernate.connection.username", sUserName);
        prop.put("hibernate.connection.password", sPass);
        prop.put("hibernate.connection.provider_class", "org.hibernate.connection.C3P0ConnectionProvider");

        prop.put("hibernate.c3p0.min_size", 5);
        prop.put("hibernate.c3p0.max_size", 30);
        prop.put("hibernate.c3p0.timeout", 1800);
        prop.put("hibernate.c3p0.max_statements", 50);
        prop.put("hibernate.c3p0.idle_test_period", 3000);

        prop.put("hibernate.show_sql", "false");
        prop.put("hibernate.connection.isolation", "2");
        prop.put("hibernate.connection.isolation.level", "Serializable");

        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.put("hibernate.current_session_context_class", "thread");
        prop.put("hibernate.transaction.factory_class", "org.hibernate.transaction.JDBCTransactionFactory");

        Configuration config = new Configuration();
        config.setProperties(prop);
        
        config.addResource("com/gits/rms/vo/Configuration.hbm.xml");
        config.addResource("com/gits/rms/vo/EmployeeStatus.hbm.xml");
        config.addResource("com/gits/rms/vo/Nationality.hbm.xml");
        config.addResource("com/gits/rms/vo/EthnicRace.hbm.xml");
        config.addResource("com/gits/rms/vo/License.hbm.xml");
        config.addResource("com/gits/rms/vo/Education.hbm.xml");
        config.addResource("com/gits/rms/vo/Project.hbm.xml");
        config.addResource("com/gits/rms/vo/Customer.hbm.xml");
        config.addResource("com/gits/rms/vo/ProjectActivity.hbm.xml");
        config.addResource("com/gits/rms/vo/JobTitle.hbm.xml");
        config.addResource("com/gits/rms/vo/Children.hbm.xml");
        config.addResource("com/gits/rms/vo/DirectDebit.hbm.xml");
        config.addResource("com/gits/rms/vo/Document.hbm.xml");
        config.addResource("com/gits/rms/vo/BenefitsType.hbm.xml");
        
        
        
        config.addResource("com/gits/rms/vo/Employees.hbm.xml");
        config.addResource("com/gits/rms/vo/Country.hbm.xml");
        config.addResource("com/gits/rms/vo/Region.hbm.xml");
        config.addResource("com/gits/rms/vo/Location.hbm.xml");
        config.addResource("com/gits/rms/vo/Organization.hbm.xml");
        config.addResource("com/gits/rms/vo/OrganizationType.hbm.xml");
        config.addResource("com/gits/rms/vo/Salary.hbm.xml");
        config.addResource("com/gits/rms/vo/SalaryGrade.hbm.xml");
        config.addResource("com/gits/rms/vo/Signature.hbm.xml");
        config.addResource("com/gits/rms/vo/Client.hbm.xml");
        config.addResource("com/gits/rms/vo/WorkExperience.hbm.xml");
        config.addResource("com/gits/rms/vo/EmployeeReportTo.hbm.xml");
        config.addResource("com/gits/rms/vo/Holiday.hbm.xml");
        config.addResource("com/gits/rms/vo/EmpUSTax.hbm.xml");
        config.addResource("com/gits/rms/vo/EmpPassport.hbm.xml");
        config.addResource("com/gits/rms/vo/Role.hbm.xml");
        config.addResource("com/gits/rms/vo/User.hbm.xml");
        config.addResource("com/gits/rms/vo/Benefits.hbm.xml");
        config.addResource("com/gits/rms/vo/EmpLocationHistory.hbm.xml");
        config.addResource("com/gits/rms/vo/Vendor.hbm.xml");
        config.addResource("com/gits/rms/vo/Message.hbm.xml");
        config.addResource("com/gits/rms/vo/Department.hbm.xml");
        config.addResource("com/gits/rms/vo/Team.hbm.xml");
        config.addResource("com/gits/rms/vo/EmpSpace.hbm.xml");
        config.addResource("com/gits/rms/vo/MailConfiguration.hbm.xml");
        config.addResource("com/gits/rms/vo/Currency.hbm.xml");
        config.addResource("com/gits/rms/vo/ImportantNews.hbm.xml");
        config.addResource("com/gits/rms/vo/Support.hbm.xml");
        config.addResource("com/gits/rms/vo/SaasContract.hbm.xml");
        config.addResource("com/gits/rms/vo/Targets.hbm.xml");
        config.addResource("com/gits/rms/vo/Goal.hbm.xml");
        config.addResource("com/gits/rms/vo/EmpAssets.hbm.xml");
        config.addResource("com/gits/rms/vo/EmployeeShift.hbm.xml");
        config.addResource("com/gits/rms/vo/Assets.hbm.xml");
        
        /* Start of Events Module */
        config.addResource("com/gits/rms/vo/Events.hbm.xml");
        /* End of Events Module */

        /* Start of TimeSheet Module */
        config.addResource("com/gits/rms/vo/TimeSheetApprover.hbm.xml");
        config.addResource("com/gits/rms/vo/TimeSheetAttachment.hbm.xml");
        config.addResource("com/gits/rms/vo/TimeTrack.hbm.xml");
        config.addResource("com/gits/rms/vo/TimesheetCategory.hbm.xml");
        config.addResource("com/gits/rms/vo/TimesheetCategoryEmp.hbm.xml");
        config.addResource("com/gits/rms/vo/TimeSheetCategoryAssign.hbm.xml");
        config.addResource("com/gits/rms/vo/TimeSheetProjectAssign.hbm.xml");
        config.addResource("com/gits/rms/vo/ProjectAssignEmp.hbm.xml");
        config.addResource("com/gits/rms/vo/EmpTargetAndGoal.hbm.xml");
        config.addResource("com/gits/rms/vo/TimesheetNotes.hbm.xml");
        config.addResource("com/gits/rms/vo/TimesheetAssignProjectTarget.hbm.xml");
        config.addResource("com/gits/rms/vo/TimesheetAchievedTarget.hbm.xml");
        config.addResource("com/gits/rms/vo/TargetType.hbm.xml");
        /* End of TimeSheet Module */

        /* Start of Expenses Module */
        config.addResource("com/gits/rms/vo/ExpensesType.hbm.xml");
        config.addResource("com/gits/rms/vo/ExpensesDetails.hbm.xml");
        config.addResource("com/gits/rms/vo/EmployeeExpenses.hbm.xml");
        config.addResource("com/gits/rms/vo/ExpensesApprover.hbm.xml");
        config.addResource("com/gits/rms/vo/ExpensesAccountantApprover.hbm.xml");
        config.addResource("com/gits/rms/vo/ExpensesAttachment.hbm.xml");
        config.addResource("com/gits/rms/vo/ExpenseStatusTracker.hbm.xml");
        /* End of Expenses Module */

        /* Start of Leave Module */
        config.addResource("com/gits/rms/vo/LeaveApprover.hbm.xml");
        config.addResource("com/gits/rms/vo/EmployeeLeaveQuota.hbm.xml");
        config.addResource("com/gits/rms/vo/LeaveType.hbm.xml");
        config.addResource("com/gits/rms/vo/LeaveHistory.hbm.xml");
        config.addResource("com/gits/rms/vo/LeaveReqsApproval.hbm.xml");
        /* End of Leave Module */

        /* Start of Paystub Module */
        config.addResource("com/gits/rms/vo/Deductions.hbm.xml");
        config.addResource("com/gits/rms/vo/PayStub.hbm.xml");
        config.addResource("com/gits/rms/vo/PayStubDeductions.hbm.xml");
        config.addResource("com/gits/rms/vo/PayStubArchive.hbm.xml");
        /* End of Paystub Module */

        /* Start of performance Module */
        config.addResource("com/gits/rms/vo/Category.hbm.xml");
        config.addResource("com/gits/rms/vo/SubCategory.hbm.xml");
        config.addResource("com/gits/rms/vo/Question.hbm.xml");
        config.addResource("com/gits/rms/vo/QuestionBank.hbm.xml");
        config.addResource("com/gits/rms/vo/QuestionBankGeneralInfo.hbm.xml");
        config.addResource("com/gits/rms/vo/QuestionGroupNameIdentification.hbm.xml");
        config.addResource("com/gits/rms/vo/Answer.hbm.xml");
        config.addResource("com/gits/rms/vo/QuestionGeneralInfoGroup.hbm.xml");
        /* End of performance Module */
        
        /*Start of client registration module */
        config.addResource("com/gits/rms/vo/ClientReg.hbm.xml");
        config.addResource("com/gits/rms/vo/EmployeeEEO.hbm.xml");
        return config.buildSessionFactory();

    }
}
