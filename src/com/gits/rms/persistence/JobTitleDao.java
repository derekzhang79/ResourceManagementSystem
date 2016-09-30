
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.JobTitleVO;

public interface JobTitleDao {

    List getAllJobTitle();

    JobTitleVO getJobTitle(Integer id);

    void insertJobTitle(JobTitleVO jobTitle);

    void updateJobTitle(JobTitleVO jobTitle);

    void deleteJobTitle(JobTitleVO jobTitle);

    List jobTitleSearchResult(JobTitleVO jobTitle);

    List checkJobTitleInEmployee(JobTitleVO jobTitle);
}
