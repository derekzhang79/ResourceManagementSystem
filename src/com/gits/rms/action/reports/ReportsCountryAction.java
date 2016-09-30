
package com.gits.rms.action.reports;

import java.util.Collection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.CountryDaoService;
import com.gits.rms.service.CountryService;
import com.gits.rms.vo.CountryVO;

public class ReportsCountryAction extends ActionSupport {

    private Collection<CountryVO> countryList;
    private CountryService couService = new CountryDaoService();

    @Override
    public String execute() throws Exception {

        // Normally we would provide a pre-compiled .jrxml file
        try {
            // Get All Country Reports
            countryList = couService.getAllCountry();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(countryList);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath() + getText("WebContent")
                + "resources/reports/hcmone_country_report.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/hcmone_country_report.jasper");

        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public Collection<CountryVO> getCountryList() {
        return countryList;
    }
}
