
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.JobTitleVO;

public interface JobTitleService {

    List checkJobTitleInEmployee(JobTitleVO jobTitle);

    void deleteJobTitle(JobTitleVO jobTitle);

    List getAllJobTitle();

    JobTitleVO getJobTitle(Integer id);

    void insertJobTitle(JobTitleVO jobTitle);

    List jobTitleSearchResult(JobTitleVO jobTitle);

    void updateJobTitle(JobTitleVO jobTitle);
}
