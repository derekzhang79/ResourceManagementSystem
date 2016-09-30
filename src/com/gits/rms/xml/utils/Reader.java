/*-- 

 Copyright (C) 2000 Brett McLaughlin & Jason Hunter.
 All rights reserved.
 
 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:
 
 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions, and the following disclaimer.
 
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions, and the disclaimer that follows 
    these conditions in the documentation and/or other materials 
    provided with the distribution.

 3. The name "JDOM" must not be used to endorse or promote products
    derived from this software without prior written permission.  For
    written permission, please contact license@jdom.org.
 
 4. Products derived from this software may not be called "JDOM", nor
    may "JDOM" appear in their name, without prior written permission
    from the JDOM Project Management (pm@jdom.org).
 
 In addition, we request (but do not require) that you include in the 
 end-user documentation provided with the redistribution and/or in the 
 software itself an acknowledgement equivalent to the following:
     "This product includes software developed by the
      JDOM Project (http://www.jdom.org/)."
 Alternatively, the acknowledgment may be graphical using the logos 
 available at http://www.jdom.org/images/logos.

 THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED.  IN NO EVENT SHALL THE JDOM AUTHORS OR THE PROJECT
 CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.

 This software consists of voluntary contributions made by many 
 individuals on behalf of the JDOM Project and was originally 
 created by Brett McLaughlin <brett@jdom.org> and 
 Jason Hunter <jhunter@jdom.org>.  For more information on the 
 JDOM Project, please see <http://www.jdom.org/>.
 
 */

package com.gits.rms.xml.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jdom.CDATA;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.gits.rms.vo.MenuVO;
import com.gits.rms.vo.RoleVO;

/**
 * Tests DocumentReader
 * 
 * @author joe.bowbeer
 */
public class Reader {

    private static void addContent(Element rootElement, MenuVO menuVO) {
        rootElement.addContent(new Element("MenuItem").addContent(new Element("None").addContent(new Element("Label").setContent(new CDATA(String.valueOf(menuVO.getLabel())))).addContent(new Element("Add").setContent(new CDATA(String.valueOf(menuVO.getAdd())))).addContent(new Element("View").setContent(new CDATA(String.valueOf(menuVO.getView())))).addContent(new Element("Update").setContent(new CDATA(String.valueOf(menuVO.getUpdate())))).addContent(new Element("Delete").setContent(new CDATA(String.valueOf(menuVO.getDelete())))).addContent(new Element("UrlRoot").setContent(new CDATA(String.valueOf(menuVO.getUrlRoot())))).addContent(new Element("UrlAdd").setContent(new CDATA(menuVO.getUrlAdd()))).addContent(new Element("UrlView").setContent(new CDATA(menuVO.getUrlView())))));
    }

    public static void deleteRoleXml(RoleVO role, String sXmlPath) {
        File f = new File(sXmlPath + role.getRoleName() + ".xml");
        try {
            FileUtils.forceDelete(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) throws Exception {

        // XMLWriter for viewing SAX events.

        XMLWriter echo = new XMLWriter();

        // Build document from xml file.

        SAXBuilder builder = new SAXBuilder();
        builder.setXMLFilter(echo);
        InputStream in = Filter.class.getResourceAsStream("menu.xml");

        Document doc = builder.build(in);

        XMLReader parser = new DocumentReader(doc);
        echo.setParent(parser);
        StringWriter writer = new StringWriter();
        parser = new XMLWriter(echo, writer);
        parser.parse((InputSource) null);

        // Reconstitute document from regurgitated string.

        builder = new SAXBuilder();
        builder.setXMLFilter(echo);
        writer.toString();

        File f = new File("D:/Running/HCMOne_2.1.6/src/com/rooster/em/xml/utils/men3.xml");
        doc = builder.build(new StringReader(FileUtils.readFileToString(f)));

        XMLOutputter outputter = new XMLOutputter();
        outputter.output(doc, System.out);

        File file = new File("d:/job.xml");
        new XMLOutputter(Format.getPrettyFormat()).output(doc, new FileOutputStream(file));

    }

    public static RoleVO populateRoleFromXml(RoleVO role, String sXmlPath) {
        SAXBuilder builder = new SAXBuilder();
        File f = new File(sXmlPath + role.getRoleName() + ".xml");

        // InputStream in = Filter.class.getResourceAsStream("men3.xml");
        Document doc = null;
        try {
            doc = builder.build(new StringReader(FileUtils.readFileToString(f)));
            Element element = doc.getRootElement();
            Iterator iFirst = element.getChildren().listIterator();

            while (iFirst.hasNext()) {
                MenuVO oMenuVO = new MenuVO();
                String sLabel = "";
                Element element1 = (Element) iFirst.next();
                Iterator iSecond = element1.getChildren().listIterator();
                while (iSecond.hasNext()) {
                    Element element2 = (Element) iSecond.next();
                    Iterator iThird = element2.getChildren().listIterator();
                    while (iThird.hasNext()) {
                        Element element3 = (Element) iThird.next();
                        if (element3.getName().equals("Label")) {
                            sLabel = String.valueOf(element3.getValue());
                        }
                        if (element3.getName().equals("Add")) {
                            oMenuVO.setAdd(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("Update")) {
                            oMenuVO.setUpdate(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("Delete")) {
                            oMenuVO.setDelete(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("View")) {
                            oMenuVO.setView(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("UrlRoot")) {
                            oMenuVO.setUrlAdd(String.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("UrlAdd")) {
                            oMenuVO.setUrlAdd(String.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("UrlView")) {
                            oMenuVO.setUrlView(String.valueOf(element3.getValue()));
                        }
                    }
                    if (sLabel.equals("Home")) {
                        role.setHomemenu(oMenuVO);
                    }
                    if (sLabel.equals("Country")) {
                        role.setCountrymenu(oMenuVO);
                    }
                    if (sLabel.equals("Nationality")) {
                        role.setNationalitymenu(oMenuVO);
                    }
                    if (sLabel.equals("EthnicRace")) {
                        role.setEthnicracemenu(oMenuVO);
                    }
                    if (sLabel.equals("Project")) {
                        role.setProjectmenu(oMenuVO);
                    }
                    if (sLabel.equals("Customer")) {
                        role.setCustomermenu(oMenuVO);
                    }
                    if (sLabel.equals("Holiday")) {
                        role.setHolidaymenu(oMenuVO);
                    }
                    if (sLabel.equals("SalaryGrade")) {
                        role.setSalarygrademenu(oMenuVO);
                    }
                    if (sLabel.equals("JobTitle")) {
                        role.setJobtitlemenu(oMenuVO);
                    }
                    if (sLabel.equals("Client")) {
                        role.setClientmenu(oMenuVO);
                    }
                    if (sLabel.equals("LeaveType")) {
                        role.setLeavetypemenu(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota")) {
                        role.setEmpleavequotamenu(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_leavetype")) {
                        role.setEmpleavequotamenu_leavetype(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_year")) {
                        role.setEmpleavequotamenu_year(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_preleavecarryforward")) {
                        role.setEmpleavequotamenu_preleavecarryforward(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_empleavepending")) {
                        role.setEmpleavequotamenu_empleavepending(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_leaveallotteddays")) {
                        role.setEmpleavequotamenu_leaveallotteddays(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_empleaverequest")) {
                        role.setEmpleavequotamenu_empleaverequest(oMenuVO);
                    }
                    if (sLabel.equals("LeaveApprover")) {
                        role.setLeaveapprovermenu(oMenuVO);
                    }
                    if (sLabel.equals("Region")) {
                        role.setRegionmenu(oMenuVO);
                    }
                    if (sLabel.equals("Employee")) {
                        role.setEmployeesmenu(oMenuVO);
                    }
                    if (sLabel.equals("Department")) {
                        role.setDepartmentsmenu(oMenuVO);
                    }
                    if (sLabel.equals("Team")) {
                        role.setTeamsmenu(oMenuVO);
                    }
                    if (sLabel.equals("Currency")) {
                        role.setCurrencymenu(oMenuVO);
                    }
                    if (sLabel.equals("Deduction")) {
                        role.setDeductionmenu(oMenuVO);
                    }
                    if (sLabel.equals("Paystub")) {
                        role.setPaystubmenu(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_grosssalary")) {
                        role.setPaystubmenu_grosssalary(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_decdate")) {
                        role.setPaystubmenu_decdate(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_netsalary")) {
                        role.setPaystubmenu_netsalary(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductionname")) {
                        role.setPaystubmenu_deductionname(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductiontype")) {
                        role.setPaystubmenu_deductiontype(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductionmode")) {
                        role.setPaystubmenu_deductionmode(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductionamount")) {
                        role.setPaystubmenu_deductionamount(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductioneffdate")) {
                        role.setPaystubmenu_deductioneffdate(oMenuVO);
                    }
                    if (sLabel.equals("License")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("License_licensenumber")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("License_licensedate")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("License_licenserenewaldate")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("License_licenseDesc")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("Education")) {
                        role.setEducationmenu(oMenuVO);
                    }
                    if (sLabel.equals("Education_edumajor")) {
                        role.setEducationmenu_edumajor(oMenuVO);
                    }
                    if (sLabel.equals("Education_eduyear")) {
                        role.setEducationmenu_eduyear(oMenuVO);
                    }
                    if (sLabel.equals("Education_edugrade")) {
                        role.setEducationmenu_edugrade(oMenuVO);
                    }
                    if (sLabel.equals("Education_edustartdate")) {
                        role.setEducationmenu_edustartdate(oMenuVO);
                    }
                    if (sLabel.equals("Education_eduenddate")) {
                        role.setEducationmenu_eduenddate(oMenuVO);
                    }
                    if (sLabel.equals("Education_eduuniversity")) {
                        role.setEducationmenu_eduuniversity(oMenuVO);
                    }
                    if (sLabel.equals("Education_edudegree")) {
                        role.setEducationmenu_edudegree(oMenuVO);
                    }
                    if (sLabel.equals("ProjectActivity")) {
                        role.setProjectactivitymenu(oMenuVO);
                    }
                    if (sLabel.equals("Children")) {
                        role.setChildrenmenu(oMenuVO);
                    }
                    if (sLabel.equals("Children_dob")) {
                        role.setChildrenmenu_dob(oMenuVO);
                    }
                    if (sLabel.equals("Children_childName")) {
                        role.setChildrenmenu_childName(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit")) {
                        role.setDirectdebitmenu(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_routingnum")) {
                        role.setDirectdebitmenu_routingnum(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_account")) {
                        role.setDirectdebitmenu_account(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_amount")) {
                        role.setDirectdebitmenu_amount(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_accounttype")) {
                        role.setDirectdebitmenu_accounttype(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_transactiontype")) {
                        role.setDirectdebitmenu_transactiontype(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_preAccountvalue")) {
                        role.setDirectdebitmenu_preAccountvalue(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience")) {
                        role.setWorkexperiencemenu(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_employeername")) {
                        role.setWorkexperiencemenu_employeername(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_empjobtitle")) {
                        role.setWorkexperiencemenu_empjobtitle(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_fromdate")) {
                        role.setWorkexperiencemenu_fromdate(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_todate")) {
                        role.setWorkexperiencemenu_todate(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_comments")) {
                        role.setWorkexperiencemenu_comments(oMenuVO);
                    }
                    if (sLabel.equals("ReportTo")) {
                        role.setEmpreporttomenu(oMenuVO);
                    }
                    if (sLabel.equals("ReportTo_supempname")) {
                        role.setEmpreporttomenu_supempname(oMenuVO);
                    }
                    if (sLabel.equals("ReportTo_reportingmodevalue")) {
                        role.setEmpreporttomenu_reportingmodevalue(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory")) {
                        role.setEmplocationhistorymenu(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory_locationname")) {
                        role.setEmplocationhistorymenu_locationname(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory_companyname")) {
                        role.setEmplocationhistorymenu_companyname(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory_startdate")) {
                        role.setEmplocationhistorymenu_startdate(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory_enddate")) {
                        role.setEmplocationhistorymenu_enddate(oMenuVO);
                    }
                    if (sLabel.equals("EmployeeSchedule")) {
                        role.setEmpschedulermenu(oMenuVO);
                    }
                    if (sLabel.equals("Role")) {
                        role.setRolemenu(oMenuVO);
                    }
                    if (sLabel.equals("User")) {
                        role.setUsermenu(oMenuVO);
                    }
                    if (sLabel.equals("ExpensesApprover")) {
                        role.setExpensesapprovermenu(oMenuVO);
                    }
                    if (sLabel.equals("TimeSheetApprover")) {
                        role.setTimesheetapprovermenu(oMenuVO);
                    }
                    if (sLabel.equals("Targets")) {
                        role.setTargetsmenu(oMenuVO);
                    }
                    if (sLabel.equals("TargetsType")) {
                        role.setTargetstypemenu(oMenuVO);
                    }
                    if (sLabel.equals("TargetModule")) {
                        role.setTargetmodulemenu(oMenuVO);
                    }
                    if (sLabel.equals("ESS")) {
                        role.setEss(oMenuVO);
                    }
                    if (sLabel.equals("ExpenseModule")) {
                        role.setExpensemodule(oMenuVO);
                    }
                    if (sLabel.equals("LeaveModule")) {
                        role.setLeavemodule(oMenuVO);
                    }
                    if (sLabel.equals("TimeSheetModule")) {
                        role.setTimesheetmodule(oMenuVO);
                    }
                    if (sLabel.equals("OrgChart")) {
                        role.setOrgChart(oMenuVO);
                    }
                    if (sLabel.equals("Reports")) {
                        role.setReports(oMenuVO);
                    }
                    if (sLabel.equals("Messaging")) {
                        role.setMessaging(oMenuVO);
                    }
                    if (sLabel.equals("EmployeeSpace")) {
                        role.setEmployeeSpace(oMenuVO);
                    }
                    if (sLabel.equals("Location")) {
                        role.setLocationmenu(oMenuVO);
                    }
                    if (sLabel.equals("ExpenseType")) {
                        role.setExpensetypemenu(oMenuVO);
                    }
                    if (sLabel.equals("EmployeeStatus")) {
                        role.setEmployeestatusmenu(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport")) {
                        role.setEmployeepassportmenu(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_passportno")) {
                        role.setEmployeepassportmenu_passportno(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_passportissuedate")) {
                        role.setEmployeepassportmenu_passportissuedate(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_passportexpiredate")) {
                        role.setEmployeepassportmenu_passportexpiredate(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_passporttypeflg")) {
                        role.setEmployeepassportmenu_passporttypeflg(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_l9Status")) {
                        role.setEmployeepassportmenu_l9Status(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_l9reviewdate")) {
                        role.setEmployeepassportmenu_l9reviewdate(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_countryname")) {
                        role.setEmployeepassportmenu_countryname(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_comments")) {
                        role.setEmployeepassportmenu_comments(oMenuVO);
                    }
                    if (sLabel.equals("Benefits")) {
                        role.setBenefitsmenu(oMenuVO);
                    }
                    if (sLabel.equals("Benefits_year")) {
                        role.setBenefitsmenu_year(oMenuVO);
                    }
                    if (sLabel.equals("Benefits_benefitsname")) {
                        role.setBenefitsmenu_benefitsname(oMenuVO);
                    }
                    if (sLabel.equals("Benefits_attachfile")) {
                        role.setBenefitsmenu_attachfile(oMenuVO);
                    }
                    if (sLabel.equals("ExpensesAccountant")) {
                        role.setExpensesaccountantmenu(oMenuVO);
                    }
                    if (sLabel.equals("PerformanceCategory")) {
                        role.setPerformancemenucategory(oMenuVO);
                    }
                    if (sLabel.equals("PerformanceSubCategory")) {
                        role.setPerformancemenusubcategory(oMenuVO);
                    }
                    if (sLabel.equals("PerformancemenuKpiQuestion")) {
                        role.setPerformancemenukpiquestion(oMenuVO);
                    }
                    if (sLabel.equals("PerformancemenuKpiQuestionGroup")) {
                        role.setPerformancemenukpiquestiongroup(oMenuVO);
                    }
                    if (sLabel.equals("PerformancemenuKpiAssignkpi")) {
                        role.setPerformancemenukpiassignkpi(oMenuVO);
                    }
                    if (sLabel.equals("Logout")) {
                        role.setLogoutmenu(oMenuVO);
                    }
                }
            }

        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return role;
    }
    
    // To create a xml document for menus 
    public static Document createDocument(RoleVO role,String clientId) {
    	Element rootElement = new Element("Menu");
        Document xmlDocument = new Document(rootElement);
        try {
            if (role != null) {
                addContent(rootElement, role.getHomemenu());
                addContent(rootElement, role.getCountrymenu());
                addContent(rootElement, role.getNationalitymenu());
                addContent(rootElement, role.getEthnicracemenu());
                addContent(rootElement, role.getProjectmenu());
                addContent(rootElement, role.getCustomermenu());
                addContent(rootElement, role.getHolidaymenu());
                addContent(rootElement, role.getSalarygrademenu());
                addContent(rootElement, role.getJobtitlemenu());
                addContent(rootElement, role.getClientmenu());
                addContent(rootElement, role.getLeavetypemenu());
                addContent(rootElement, role.getEmpleavequotamenu());
                addContent(rootElement, role.getEmpleavequotamenu_leavetype());
                addContent(rootElement, role.getEmpleavequotamenu_year());
                addContent(rootElement, role.getEmpleavequotamenu_empleavepending());
                addContent(rootElement, role.getEmpleavequotamenu_empleaverequest());
                addContent(rootElement, role.getEmpleavequotamenu_leaveallotteddays());
                addContent(rootElement, role.getEmpleavequotamenu_preleavecarryforward());
                addContent(rootElement, role.getLeaveapprovermenu());
                addContent(rootElement, role.getRegionmenu());
                addContent(rootElement, role.getEmployeesmenu());
                addContent(rootElement, role.getDepartmentsmenu());
                addContent(rootElement, role.getTeamsmenu());
                addContent(rootElement, role.getCurrencymenu());
                addContent(rootElement, role.getDeductionmenu());
                addContent(rootElement, role.getPaystubmenu());
                addContent(rootElement, role.getPaystubmenu_decdate());
                addContent(rootElement, role.getPaystubmenu_grosssalary());
                addContent(rootElement, role.getPaystubmenu_netsalary());
                addContent(rootElement, role.getPaystubmenu_deductionname());
                addContent(rootElement, role.getPaystubmenu_deductionamount());
                addContent(rootElement, role.getPaystubmenu_deductioneffdate());
                addContent(rootElement, role.getPaystubmenu_deductionmode());
                addContent(rootElement, role.getPaystubmenu_deductiontype());
                addContent(rootElement, role.getLicensemenu());
                addContent(rootElement, role.getLicensemenu_licensedate());
                addContent(rootElement, role.getLicensemenu_licenseDesc());
                addContent(rootElement, role.getLicensemenu_licensenumber());
                addContent(rootElement, role.getLicensemenu_licenserenewaldate());
                addContent(rootElement, role.getEducationmenu());
                addContent(rootElement, role.getEducationmenu_edudegree());
                addContent(rootElement, role.getEducationmenu_eduenddate());
                addContent(rootElement, role.getEducationmenu_edugrade());
                addContent(rootElement, role.getEducationmenu_edumajor());
                addContent(rootElement, role.getEducationmenu_edustartdate());
                addContent(rootElement, role.getEducationmenu_eduuniversity());
                addContent(rootElement, role.getEducationmenu_eduyear());
                addContent(rootElement, role.getProjectactivitymenu());
                addContent(rootElement, role.getChildrenmenu());
                addContent(rootElement, role.getChildrenmenu_childName());
                addContent(rootElement, role.getChildrenmenu_dob());
                addContent(rootElement, role.getDirectdebitmenu());
                addContent(rootElement, role.getDirectdebitmenu_account());
                addContent(rootElement, role.getDirectdebitmenu_accounttype());
                addContent(rootElement, role.getDirectdebitmenu_amount());
                addContent(rootElement, role.getDirectdebitmenu_preAccountvalue());
                addContent(rootElement, role.getDirectdebitmenu_routingnum());
                addContent(rootElement, role.getDirectdebitmenu_transactiontype());
                addContent(rootElement, role.getWorkexperiencemenu());
                addContent(rootElement, role.getWorkexperiencemenu_comments());
                addContent(rootElement, role.getWorkexperiencemenu_empjobtitle());
                addContent(rootElement, role.getWorkexperiencemenu_employeername());
                addContent(rootElement, role.getWorkexperiencemenu_fromdate());
                addContent(rootElement, role.getWorkexperiencemenu_todate());
                addContent(rootElement, role.getEmpreporttomenu());
                addContent(rootElement, role.getEmpreporttomenu_reportingmodevalue());
                addContent(rootElement, role.getEmpreporttomenu_supempname());
                addContent(rootElement, role.getEmplocationhistorymenu());
                addContent(rootElement, role.getEmplocationhistorymenu_companyname());
                addContent(rootElement, role.getEmplocationhistorymenu_enddate());
                addContent(rootElement, role.getEmplocationhistorymenu_locationname());
                addContent(rootElement, role.getEmplocationhistorymenu_startdate());
                addContent(rootElement, role.getEmpschedulermenu());
                addContent(rootElement, role.getRolemenu());
                addContent(rootElement, role.getUsermenu());
                addContent(rootElement, role.getExpensesapprovermenu());
                addContent(rootElement, role.getExpensesaccountantmenu());
                addContent(rootElement, role.getTimesheetapprovermenu());
                addContent(rootElement, role.getTargetsmenu());
                addContent(rootElement, role.getEss());
                addContent(rootElement, role.getExpensemodule());
                addContent(rootElement, role.getLeavemodule());
                addContent(rootElement, role.getTimesheetmodule());
                addContent(rootElement, role.getLocationmenu());
                addContent(rootElement, role.getExpensetypemenu());
                addContent(rootElement, role.getEmployeestatusmenu());
                addContent(rootElement, role.getEmployeepassportmenu());
                addContent(rootElement, role.getEmployeepassportmenu_comments());
                addContent(rootElement, role.getEmployeepassportmenu_countryname());
                addContent(rootElement, role.getEmployeepassportmenu_l9reviewdate());
                addContent(rootElement, role.getEmployeepassportmenu_l9Status());
                addContent(rootElement, role.getEmployeepassportmenu_passportexpiredate());
                addContent(rootElement, role.getEmployeepassportmenu_passportissuedate());
                addContent(rootElement, role.getEmployeepassportmenu_passportno());
                addContent(rootElement, role.getEmployeepassportmenu_passporttypeflg());
                addContent(rootElement, role.getBenefitsmenu());
                addContent(rootElement, role.getBenefitsmenu_attachfile());
                addContent(rootElement, role.getBenefitsmenu_benefitsname());
                addContent(rootElement, role.getBenefitsmenu_year());
                addContent(rootElement, role.getOrgChart());
                addContent(rootElement, role.getReports());
                addContent(rootElement, role.getMessaging());
                addContent(rootElement, role.getEmployeeSpace());
                addContent(rootElement, role.getTargetmodulemenu());
                addContent(rootElement, role.getTargetstypemenu());
                addContent(rootElement, role.getPerformancemenucategory());
                addContent(rootElement, role.getPerformancemenusubcategory());
                addContent(rootElement, role.getPerformancemenukpiquestion());
                addContent(rootElement, role.getPerformancemenukpiquestiongroup());
                addContent(rootElement, role.getPerformancemenukpiassignkpi());
                addContent(rootElement, role.getLogoutmenu());
            }
          }
            catch (Exception e) {
                e.printStackTrace();
            }
    	return xmlDocument;
    }
    
    // To populate role from mongo document
    public static RoleVO populateRoleFromDocument(RoleVO role, Document xmlDocument) {
        Document doc = null;
        try {
        	doc = xmlDocument;
            Element element = doc.getRootElement();
            Iterator iFirst = element.getChildren().listIterator();

            while (iFirst.hasNext()) {
                MenuVO oMenuVO = new MenuVO();
                String sLabel = "";
                Element element1 = (Element) iFirst.next();
                Iterator iSecond = element1.getChildren().listIterator();
                while (iSecond.hasNext()) {
                    Element element2 = (Element) iSecond.next();
                    Iterator iThird = element2.getChildren().listIterator();
                    while (iThird.hasNext()) {
                        Element element3 = (Element) iThird.next();
                        if (element3.getName().equals("Label")) {
                            sLabel = String.valueOf(element3.getValue());
                        }
                        if (element3.getName().equals("Add")) {
                            oMenuVO.setAdd(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("Update")) {
                            oMenuVO.setUpdate(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("Delete")) {
                            oMenuVO.setDelete(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("View")) {
                            oMenuVO.setView(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("UrlRoot")) {
                            oMenuVO.setUrlAdd(String.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("UrlAdd")) {
                            oMenuVO.setUrlAdd(String.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("UrlView")) {
                            oMenuVO.setUrlView(String.valueOf(element3.getValue()));
                        }
                    }
                    if (sLabel.equals("Home")) {
                        role.setHomemenu(oMenuVO);
                    }
                    if (sLabel.equals("Country")) {
                        role.setCountrymenu(oMenuVO);
                    }
                    if (sLabel.equals("Nationality")) {
                        role.setNationalitymenu(oMenuVO);
                    }
                    if (sLabel.equals("EthnicRace")) {
                        role.setEthnicracemenu(oMenuVO);
                    }
                    if (sLabel.equals("Project")) {
                        role.setProjectmenu(oMenuVO);
                    }
                    if (sLabel.equals("Customer")) {
                        role.setCustomermenu(oMenuVO);
                    }
                    if (sLabel.equals("Holiday")) {
                        role.setHolidaymenu(oMenuVO);
                    }
                    if (sLabel.equals("SalaryGrade")) {
                        role.setSalarygrademenu(oMenuVO);
                    }
                    if (sLabel.equals("JobTitle")) {
                        role.setJobtitlemenu(oMenuVO);
                    }
                    if (sLabel.equals("Client")) {
                        role.setClientmenu(oMenuVO);
                    }
                    if (sLabel.equals("LeaveType")) {
                        role.setLeavetypemenu(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota")) {
                        role.setEmpleavequotamenu(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_leavetype")) {
                        role.setEmpleavequotamenu_leavetype(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_year")) {
                        role.setEmpleavequotamenu_year(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_preleavecarryforward")) {
                        role.setEmpleavequotamenu_preleavecarryforward(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_empleavepending")) {
                        role.setEmpleavequotamenu_empleavepending(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_leaveallotteddays")) {
                        role.setEmpleavequotamenu_leaveallotteddays(oMenuVO);
                    }
                    if (sLabel.equals("LeaveQuota_empleaverequest")) {
                        role.setEmpleavequotamenu_empleaverequest(oMenuVO);
                    }
                    if (sLabel.equals("LeaveApprover")) {
                        role.setLeaveapprovermenu(oMenuVO);
                    }
                    if (sLabel.equals("Region")) {
                        role.setRegionmenu(oMenuVO);
                    }
                    if (sLabel.equals("Employee")) {
                        role.setEmployeesmenu(oMenuVO);
                    }
                    if (sLabel.equals("Department")) {
                        role.setDepartmentsmenu(oMenuVO);
                    }
                    if (sLabel.equals("Team")) {
                        role.setTeamsmenu(oMenuVO);
                    }
                    if (sLabel.equals("Currency")) {
                        role.setCurrencymenu(oMenuVO);
                    }
                    if (sLabel.equals("Deduction")) {
                        role.setDeductionmenu(oMenuVO);
                    }
                    if (sLabel.equals("Paystub")) {
                        role.setPaystubmenu(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_grosssalary")) {
                        role.setPaystubmenu_grosssalary(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_decdate")) {
                        role.setPaystubmenu_decdate(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_netsalary")) {
                        role.setPaystubmenu_netsalary(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductionname")) {
                        role.setPaystubmenu_deductionname(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductiontype")) {
                        role.setPaystubmenu_deductiontype(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductionmode")) {
                        role.setPaystubmenu_deductionmode(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductionamount")) {
                        role.setPaystubmenu_deductionamount(oMenuVO);
                    }
                    if (sLabel.equals("Paystub_deductioneffdate")) {
                        role.setPaystubmenu_deductioneffdate(oMenuVO);
                    }
                    if (sLabel.equals("License")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("License_licensenumber")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("License_licensedate")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("License_licenserenewaldate")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("License_licenseDesc")) {
                        role.setLicensemenu(oMenuVO);
                    }
                    if (sLabel.equals("Education")) {
                        role.setEducationmenu(oMenuVO);
                    }
                    if (sLabel.equals("Education_edumajor")) {
                        role.setEducationmenu_edumajor(oMenuVO);
                    }
                    if (sLabel.equals("Education_eduyear")) {
                        role.setEducationmenu_eduyear(oMenuVO);
                    }
                    if (sLabel.equals("Education_edugrade")) {
                        role.setEducationmenu_edugrade(oMenuVO);
                    }
                    if (sLabel.equals("Education_edustartdate")) {
                        role.setEducationmenu_edustartdate(oMenuVO);
                    }
                    if (sLabel.equals("Education_eduenddate")) {
                        role.setEducationmenu_eduenddate(oMenuVO);
                    }
                    if (sLabel.equals("Education_eduuniversity")) {
                        role.setEducationmenu_eduuniversity(oMenuVO);
                    }
                    if (sLabel.equals("Education_edudegree")) {
                        role.setEducationmenu_edudegree(oMenuVO);
                    }
                    if (sLabel.equals("ProjectActivity")) {
                        role.setProjectactivitymenu(oMenuVO);
                    }
                    if (sLabel.equals("Children")) {
                        role.setChildrenmenu(oMenuVO);
                    }
                    if (sLabel.equals("Children_dob")) {
                        role.setChildrenmenu_dob(oMenuVO);
                    }
                    if (sLabel.equals("Children_childName")) {
                        role.setChildrenmenu_childName(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit")) {
                        role.setDirectdebitmenu(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_routingnum")) {
                        role.setDirectdebitmenu_routingnum(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_account")) {
                        role.setDirectdebitmenu_account(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_amount")) {
                        role.setDirectdebitmenu_amount(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_accounttype")) {
                        role.setDirectdebitmenu_accounttype(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_transactiontype")) {
                        role.setDirectdebitmenu_transactiontype(oMenuVO);
                    }
                    if (sLabel.equals("DirectDebit_preAccountvalue")) {
                        role.setDirectdebitmenu_preAccountvalue(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience")) {
                        role.setWorkexperiencemenu(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_employeername")) {
                        role.setWorkexperiencemenu_employeername(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_empjobtitle")) {
                        role.setWorkexperiencemenu_empjobtitle(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_fromdate")) {
                        role.setWorkexperiencemenu_fromdate(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_todate")) {
                        role.setWorkexperiencemenu_todate(oMenuVO);
                    }
                    if (sLabel.equals("WorkExperience_comments")) {
                        role.setWorkexperiencemenu_comments(oMenuVO);
                    }
                    if (sLabel.equals("ReportTo")) {
                        role.setEmpreporttomenu(oMenuVO);
                    }
                    if (sLabel.equals("ReportTo_supempname")) {
                        role.setEmpreporttomenu_supempname(oMenuVO);
                    }
                    if (sLabel.equals("ReportTo_reportingmodevalue")) {
                        role.setEmpreporttomenu_reportingmodevalue(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory")) {
                        role.setEmplocationhistorymenu(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory_locationname")) {
                        role.setEmplocationhistorymenu_locationname(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory_companyname")) {
                        role.setEmplocationhistorymenu_companyname(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory_startdate")) {
                        role.setEmplocationhistorymenu_startdate(oMenuVO);
                    }
                    if (sLabel.equals("LocationHistory_enddate")) {
                        role.setEmplocationhistorymenu_enddate(oMenuVO);
                    }
                    if (sLabel.equals("EmployeeSchedule")) {
                        role.setEmpschedulermenu(oMenuVO);
                    }
                    if (sLabel.equals("Role")) {
                        role.setRolemenu(oMenuVO);
                    }
                    if (sLabel.equals("User")) {
                        role.setUsermenu(oMenuVO);
                    }
                    if (sLabel.equals("ExpensesApprover")) {
                        role.setExpensesapprovermenu(oMenuVO);
                    }
                    if (sLabel.equals("TimeSheetApprover")) {
                        role.setTimesheetapprovermenu(oMenuVO);
                    }
                    if (sLabel.equals("Targets")) {
                        role.setTargetsmenu(oMenuVO);
                    }
                    if (sLabel.equals("TargetsType")) {
                        role.setTargetstypemenu(oMenuVO);
                    }
                    if (sLabel.equals("TargetModule")) {
                        role.setTargetmodulemenu(oMenuVO);
                    }
                    if (sLabel.equals("ESS")) {
                        role.setEss(oMenuVO);
                    }
                    if (sLabel.equals("ExpenseModule")) {
                        role.setExpensemodule(oMenuVO);
                    }
                    if (sLabel.equals("LeaveModule")) {
                        role.setLeavemodule(oMenuVO);
                    }
                    if (sLabel.equals("TimeSheetModule")) {
                        role.setTimesheetmodule(oMenuVO);
                    }
                    if (sLabel.equals("OrgChart")) {
                        role.setOrgChart(oMenuVO);
                    }
                    if (sLabel.equals("Reports")) {
                        role.setReports(oMenuVO);
                    }
                    if (sLabel.equals("Messaging")) {
                        role.setMessaging(oMenuVO);
                    }
                    if (sLabel.equals("EmployeeSpace")) {
                        role.setEmployeeSpace(oMenuVO);
                    }
                    if (sLabel.equals("Location")) {
                        role.setLocationmenu(oMenuVO);
                    }
                    if (sLabel.equals("ExpenseType")) {
                        role.setExpensetypemenu(oMenuVO);
                    }
                    if (sLabel.equals("EmployeeStatus")) {
                        role.setEmployeestatusmenu(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport")) {
                        role.setEmployeepassportmenu(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_passportno")) {
                        role.setEmployeepassportmenu_passportno(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_passportissuedate")) {
                        role.setEmployeepassportmenu_passportissuedate(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_passportexpiredate")) {
                        role.setEmployeepassportmenu_passportexpiredate(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_passporttypeflg")) {
                        role.setEmployeepassportmenu_passporttypeflg(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_l9Status")) {
                        role.setEmployeepassportmenu_l9Status(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_l9reviewdate")) {
                        role.setEmployeepassportmenu_l9reviewdate(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_countryname")) {
                        role.setEmployeepassportmenu_countryname(oMenuVO);
                    }
                    if (sLabel.equals("EmployeePassport_comments")) {
                        role.setEmployeepassportmenu_comments(oMenuVO);
                    }
                    if (sLabel.equals("Benefits")) {
                        role.setBenefitsmenu(oMenuVO);
                    }
                    if (sLabel.equals("Benefits_year")) {
                        role.setBenefitsmenu_year(oMenuVO);
                    }
                    if (sLabel.equals("Benefits_benefitsname")) {
                        role.setBenefitsmenu_benefitsname(oMenuVO);
                    }
                    if (sLabel.equals("Benefits_attachfile")) {
                        role.setBenefitsmenu_attachfile(oMenuVO);
                    }
                    if (sLabel.equals("ExpensesAccountant")) {
                        role.setExpensesaccountantmenu(oMenuVO);
                    }
                    if (sLabel.equals("PerformanceCategory")) {
                        role.setPerformancemenucategory(oMenuVO);
                    }
                    if (sLabel.equals("PerformanceSubCategory")) {
                        role.setPerformancemenusubcategory(oMenuVO);
                    }
                    if (sLabel.equals("PerformancemenuKpiQuestion")) {
                        role.setPerformancemenukpiquestion(oMenuVO);
                    }
                    if (sLabel.equals("PerformancemenuKpiQuestionGroup")) {
                        role.setPerformancemenukpiquestiongroup(oMenuVO);
                    }
                    if (sLabel.equals("PerformancemenuKpiAssignkpi")) {
                        role.setPerformancemenukpiassignkpi(oMenuVO);
                    }
                    if (sLabel.equals("Logout")) {
                        role.setLogoutmenu(oMenuVO);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 
        return role;
    }
    
    public static String saveRoleXml(RoleVO role, String sXmlPath) {
        String sDestFileName = new String(sXmlPath + "_" + role.getRoleName() + ".xml");
        String sDestFilePath = "";
        Element rootElement = new Element("Menu");
        Document xmlDocument = new Document(rootElement);
        try {
            if (role != null) {
                addContent(rootElement, role.getHomemenu());
                addContent(rootElement, role.getCountrymenu());
                addContent(rootElement, role.getNationalitymenu());
                addContent(rootElement, role.getEthnicracemenu());
                addContent(rootElement, role.getProjectmenu());
                addContent(rootElement, role.getCustomermenu());
                addContent(rootElement, role.getHolidaymenu());
                addContent(rootElement, role.getSalarygrademenu());
                addContent(rootElement, role.getJobtitlemenu());
                addContent(rootElement, role.getClientmenu());
                addContent(rootElement, role.getLeavetypemenu());
                addContent(rootElement, role.getEmpleavequotamenu());
                addContent(rootElement, role.getEmpleavequotamenu_leavetype());
                addContent(rootElement, role.getEmpleavequotamenu_year());
                addContent(rootElement, role.getEmpleavequotamenu_empleavepending());
                addContent(rootElement, role.getEmpleavequotamenu_empleaverequest());
                addContent(rootElement, role.getEmpleavequotamenu_leaveallotteddays());
                addContent(rootElement, role.getEmpleavequotamenu_preleavecarryforward());
                addContent(rootElement, role.getLeaveapprovermenu());
                addContent(rootElement, role.getRegionmenu());
                addContent(rootElement, role.getEmployeesmenu());
                addContent(rootElement, role.getDepartmentsmenu());
                addContent(rootElement, role.getTeamsmenu());
                addContent(rootElement, role.getCurrencymenu());
                addContent(rootElement, role.getDeductionmenu());
                addContent(rootElement, role.getPaystubmenu());
                addContent(rootElement, role.getPaystubmenu_decdate());
                addContent(rootElement, role.getPaystubmenu_grosssalary());
                addContent(rootElement, role.getPaystubmenu_netsalary());
                addContent(rootElement, role.getPaystubmenu_deductionname());
                addContent(rootElement, role.getPaystubmenu_deductionamount());
                addContent(rootElement, role.getPaystubmenu_deductioneffdate());
                addContent(rootElement, role.getPaystubmenu_deductionmode());
                addContent(rootElement, role.getPaystubmenu_deductiontype());
                addContent(rootElement, role.getLicensemenu());
                addContent(rootElement, role.getLicensemenu_licensedate());
                addContent(rootElement, role.getLicensemenu_licenseDesc());
                addContent(rootElement, role.getLicensemenu_licensenumber());
                addContent(rootElement, role.getLicensemenu_licenserenewaldate());
                addContent(rootElement, role.getEducationmenu());
                addContent(rootElement, role.getEducationmenu_edudegree());
                addContent(rootElement, role.getEducationmenu_eduenddate());
                addContent(rootElement, role.getEducationmenu_edugrade());
                addContent(rootElement, role.getEducationmenu_edumajor());
                addContent(rootElement, role.getEducationmenu_edustartdate());
                addContent(rootElement, role.getEducationmenu_eduuniversity());
                addContent(rootElement, role.getEducationmenu_eduyear());
                addContent(rootElement, role.getProjectactivitymenu());
                addContent(rootElement, role.getChildrenmenu());
                addContent(rootElement, role.getChildrenmenu_childName());
                addContent(rootElement, role.getChildrenmenu_dob());
                addContent(rootElement, role.getDirectdebitmenu());
                addContent(rootElement, role.getDirectdebitmenu_account());
                addContent(rootElement, role.getDirectdebitmenu_accounttype());
                addContent(rootElement, role.getDirectdebitmenu_amount());
                addContent(rootElement, role.getDirectdebitmenu_preAccountvalue());
                addContent(rootElement, role.getDirectdebitmenu_routingnum());
                addContent(rootElement, role.getDirectdebitmenu_transactiontype());
                addContent(rootElement, role.getWorkexperiencemenu());
                addContent(rootElement, role.getWorkexperiencemenu_comments());
                addContent(rootElement, role.getWorkexperiencemenu_empjobtitle());
                addContent(rootElement, role.getWorkexperiencemenu_employeername());
                addContent(rootElement, role.getWorkexperiencemenu_fromdate());
                addContent(rootElement, role.getWorkexperiencemenu_todate());
                addContent(rootElement, role.getEmpreporttomenu());
                addContent(rootElement, role.getEmpreporttomenu_reportingmodevalue());
                addContent(rootElement, role.getEmpreporttomenu_supempname());
                addContent(rootElement, role.getEmplocationhistorymenu());
                addContent(rootElement, role.getEmplocationhistorymenu_companyname());
                addContent(rootElement, role.getEmplocationhistorymenu_enddate());
                addContent(rootElement, role.getEmplocationhistorymenu_locationname());
                addContent(rootElement, role.getEmplocationhistorymenu_startdate());
                addContent(rootElement, role.getEmpschedulermenu());
                addContent(rootElement, role.getRolemenu());
                addContent(rootElement, role.getUsermenu());
                addContent(rootElement, role.getExpensesapprovermenu());
                addContent(rootElement, role.getExpensesaccountantmenu());
                addContent(rootElement, role.getTimesheetapprovermenu());
                addContent(rootElement, role.getTargetsmenu());
                addContent(rootElement, role.getEss());
                addContent(rootElement, role.getExpensemodule());
                addContent(rootElement, role.getLeavemodule());
                addContent(rootElement, role.getTimesheetmodule());
                addContent(rootElement, role.getLocationmenu());
                addContent(rootElement, role.getExpensetypemenu());
                addContent(rootElement, role.getEmployeestatusmenu());
                addContent(rootElement, role.getEmployeepassportmenu());
                addContent(rootElement, role.getEmployeepassportmenu_comments());
                addContent(rootElement, role.getEmployeepassportmenu_countryname());
                addContent(rootElement, role.getEmployeepassportmenu_l9reviewdate());
                addContent(rootElement, role.getEmployeepassportmenu_l9Status());
                addContent(rootElement, role.getEmployeepassportmenu_passportexpiredate());
                addContent(rootElement, role.getEmployeepassportmenu_passportissuedate());
                addContent(rootElement, role.getEmployeepassportmenu_passportno());
                addContent(rootElement, role.getEmployeepassportmenu_passporttypeflg());
                addContent(rootElement, role.getBenefitsmenu());
                addContent(rootElement, role.getBenefitsmenu_attachfile());
                addContent(rootElement, role.getBenefitsmenu_benefitsname());
                addContent(rootElement, role.getBenefitsmenu_year());
                addContent(rootElement, role.getOrgChart());
                addContent(rootElement, role.getReports());
                addContent(rootElement, role.getMessaging());
                addContent(rootElement, role.getEmployeeSpace());
                addContent(rootElement, role.getTargetmodulemenu());
                addContent(rootElement, role.getTargetstypemenu());
                addContent(rootElement, role.getPerformancemenucategory());
                addContent(rootElement, role.getPerformancemenusubcategory());
                addContent(rootElement, role.getPerformancemenukpiquestion());
                addContent(rootElement, role.getPerformancemenukpiquestiongroup());
                addContent(rootElement, role.getPerformancemenukpiassignkpi());
                addContent(rootElement, role.getLogoutmenu());
            }
            File file = new File(sDestFileName);
            new XMLOutputter(Format.getPrettyFormat()).output(xmlDocument, new FileOutputStream(file));
                  
            sDestFilePath = file.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sDestFilePath;       
    }

    /** Creates new ReaderTest */
    public Reader() {
    }

    public List<MenuVO> getMenuItems(String sMenusXmlFilePath, String sMenusXmlDefaultFilePath) {
        List<MenuVO> liMenuItems = new ArrayList<MenuVO>();
        SAXBuilder builder = new SAXBuilder();
        File f = new File(sMenusXmlFilePath);

        // InputStream in = Filter.class.getResourceAsStream(sMenu);
        Document doc = null;

        try {
            try {
                doc = builder.build(new StringReader(FileUtils.readFileToString(f)));
            } catch (FileNotFoundException e) {
                f = new File(sMenusXmlDefaultFilePath);
                doc = builder.build(new StringReader(FileUtils.readFileToString(f)));
            }

            Element element = doc.getRootElement();

            Iterator iFirst = element.getChildren().listIterator();
            MenuVO oMenuVO = null;

            while (iFirst.hasNext()) {
                oMenuVO = new MenuVO();
                Element element1 = (Element) iFirst.next();
                Iterator iSecond = element1.getChildren().listIterator();
                while (iSecond.hasNext()) {
                    Element element2 = (Element) iSecond.next();
                    Iterator iThird = element2.getChildren().listIterator();
                    while (iThird.hasNext()) {
                        Element element3 = (Element) iThird.next();
                        if (element3.getName().equals("Label")) {
                            oMenuVO.setLabel(element3.getValue());
                        }
                        if (element3.getName().equals("Add")) {
                            oMenuVO.setAdd(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("Update")) {
                            oMenuVO.setUpdate(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("Delete")) {
                            oMenuVO.setDelete(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("View")) {
                            oMenuVO.setView(Boolean.valueOf(element3.getValue()));
                        }
                        if (element3.getName().equals("UrlRoot")) {
                            oMenuVO.setUrlRoot(element3.getValue());
                        }
                        if (element3.getName().equals("UrlAdd")) {
                            oMenuVO.setUrlAdd(element3.getValue());
                        }
                        if (element3.getName().equals("UrlView")) {
                            oMenuVO.setUrlView(element3.getValue());
                        }
                    }
                }
                liMenuItems.add(oMenuVO);
            }

        } catch (JDOMException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return liMenuItems;
    }
}
