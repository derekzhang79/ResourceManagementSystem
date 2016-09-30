
package com.gits.rms.jobs;

import java.util.Iterator;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.gits.rms.persistence.HibernateUtil;
import com.gits.rms.persistence.PreDatabaseConnection;
import com.gits.rms.service.NationalityDaoService;
import com.gits.rms.service.NationalityService;
import com.gits.rms.vo.ClientInformationVO;

public class NationalityJob implements Job {

    private NationalityService natiService = new NationalityDaoService();

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        try {
            List<ClientInformationVO> clientList = HibernateUtil.getClientInfoList();

            if (!(clientList.isEmpty())) {

                for (Iterator<ClientInformationVO> natIte = clientList.iterator(); natIte.hasNext();) {
                    ClientInformationVO cliInfoObj = natIte.next();
                    natiService.getAllNationalityCronJob(String.valueOf(cliInfoObj.getClientId()));
                }

            }
            
            PreDatabaseConnection preConnection = new PreDatabaseConnection();
//            COMMENTED BY BALA
//        	int preConnectionCount = preConnection.addNewSubdomain();
//    		System.out.println("PreDatabaseConnection Checking Process :"+preConnectionCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
