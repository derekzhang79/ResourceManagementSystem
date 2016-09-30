
package com.gits.rms.jobs;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.service.PayStubDaoService;
import com.gits.rms.service.PayStubDeductionDaoService;
import com.gits.rms.service.PayStubDeductionService;
import com.gits.rms.service.PayStubService;
import com.gits.rms.vo.ClientInformationVO;
import com.gits.rms.vo.PayStubDeductionsVO;
import com.gits.rms.vo.PayStubVO;

public class PayStubJob implements Job {

    private PayStubDeductionService payStubDeductionService = new PayStubDeductionDaoService();
    private PayStubService payStubService = new PayStubDaoService();
    private List<PayStubDeductionsVO> payStubDeductionList;
    private PayStubVO paystubObj;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        try {
            List<ClientInformationVO> clientList = HibernateUtil.getClientInfoList();

            for (Iterator<ClientInformationVO> it = clientList.iterator(); it.hasNext();) {
                ClientInformationVO cliInfoObj = it.next();

                PayStubDeductionsVO newPaystubObj;
                List<PayStubDeductionsVO> newPayStubDeductionList = new LinkedList<PayStubDeductionsVO>();

                boolean todayHasDeduction = payStubDeductionService.checkTodaysPayStubsDeduction(String.valueOf(cliInfoObj.getClientId()));

                if (todayHasDeduction == true) {
                    payStubDeductionList = payStubDeductionService.getAllTodaysPayStubsDeduction(String.valueOf(cliInfoObj.getClientId()));

                    for (Iterator<PayStubDeductionsVO> ite = payStubDeductionList.iterator(); ite.hasNext();) {
                        newPaystubObj = ite.next();

                        paystubObj = payStubService.getPayStubCronJob(newPaystubObj.getPayStub().getPayStubId(), String.valueOf(cliInfoObj.getClientId()));
                        double grossSalary = paystubObj.getGrossSalary();

                        payStubDeductionList = payStubDeductionService.getAllEmployeePayStubsDeductionCronJob(newPaystubObj.getPayStub().getPayStubId(), String.valueOf(cliInfoObj.getClientId()));

                        if (!(payStubDeductionList.isEmpty())) {

                            for (Iterator<PayStubDeductionsVO> iteratorOne = payStubDeductionList.iterator(); iteratorOne.hasNext();) {
                                newPaystubObj = iteratorOne.next();

                                if (newPaystubObj.getDeduction().getDeductionType().equals("PreTax")) {
                                    newPayStubDeductionList.add(newPaystubObj);
                                }
                            }

                            for (Iterator<PayStubDeductionsVO> iteratorTwo = payStubDeductionList.iterator(); iteratorTwo.hasNext();) {
                                newPaystubObj = iteratorTwo.next();

                                if (newPaystubObj.getDeduction().getDeductionType().equals("Tax")) {
                                    newPayStubDeductionList.add(newPaystubObj);
                                }
                            }

                            for (Iterator<PayStubDeductionsVO> iteratorThree = payStubDeductionList.iterator(); iteratorThree.hasNext();) {
                                newPaystubObj = iteratorThree.next();

                                if (newPaystubObj.getDeduction().getDeductionType().equals("PostTax")) {
                                    newPayStubDeductionList.add(newPaystubObj);
                                }
                            }

                            for (Iterator<PayStubDeductionsVO> iteratorFour = newPayStubDeductionList.iterator(); iteratorFour.hasNext();) {
                                newPaystubObj = iteratorFour.next();

                                if (newPaystubObj.getDeduction().getDeductionMode().equals("Percentage")) {
                                    double tempSalary = ((newPaystubObj.getDeductionAmount() / 100) * grossSalary);
                                    grossSalary = grossSalary - tempSalary;

                                } else if (newPaystubObj.getDeduction().getDeductionMode().equals("Actuals")) {
                                    grossSalary = grossSalary - newPaystubObj.getDeductionAmount();
                                }
                            }
                            payStubDeductionService.updatePayStubNetSalaryCronJob(paystubObj.getPayStubId(), grossSalary, String.valueOf(cliInfoObj.getClientId()));
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
