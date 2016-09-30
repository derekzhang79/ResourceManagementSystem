
package com.gits.rms.action.reports;

import java.util.Collection;
import java.util.List;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.gits.rms.service.MessageDaoService;
import com.gits.rms.service.MessageService;
import com.gits.rms.vo.MessageVO;
import com.gits.rms.vo.ReportsVO;

public class ReportsBroadcastMessageAction extends ActionSupport {

    private ReportsVO report;
    private List<ReportsVO> reportsList;
    private Collection<MessageVO> msgBroadcastList;
    private MessageVO broadcastMsg;
    private MessageService msgBroadcastService = new MessageDaoService();

    @Override
    public String execute() throws Exception {
        try {
            msgBroadcastList = msgBroadcastService.getBroadcastMessageReports(report);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(msgBroadcastList);
            JasperCompileManager.compileReportToFile(getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath() + getText("WebContent")
                + "resources/reports/broadcastReports/BroadcastReport.jrxml", getText("ApplicationAbsolutePath")
                + ServletActionContext.getServletContext().getContextPath()
                + getText("WebContent")
                + "resources/reports/broadcastReports/BroadcastReport.jasper");
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public ReportsVO getReport() {
        return report;
    }

    public void setReport(ReportsVO report) {
        this.report = report;
    }

    public List<ReportsVO> getReportsList() {
        return reportsList;
    }

    public void setReportsList(List<ReportsVO> reportsList) {
        this.reportsList = reportsList;
    }

    public Collection<MessageVO> getMsgBroadcastList() {
        return msgBroadcastList;
    }

    public void setMsgBroadcastList(Collection<MessageVO> msgBroadcastList) {
        this.msgBroadcastList = msgBroadcastList;
    }

    public MessageVO getBroadcastMsg() {
        return broadcastMsg;
    }

    public void setBroadcastMsg(MessageVO broadcastMsg) {
        this.broadcastMsg = broadcastMsg;
    }

    public MessageService getMsgBroadcastService() {
        return msgBroadcastService;
    }

    public void setMsgBroadcastService(MessageService msgBroadcastService) {
        this.msgBroadcastService = msgBroadcastService;
    }
}
