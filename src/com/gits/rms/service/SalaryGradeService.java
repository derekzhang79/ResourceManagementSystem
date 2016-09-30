
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.SalaryGradeVO;

public interface SalaryGradeService {

    List checkSalaryGradeInJobTitle(SalaryGradeVO salgra);

    void deleteSalaryGrade(SalaryGradeVO salgra);

    List getAllSalaryGrade();

    SalaryGradeVO getSalaryGrade(Integer id);

    void insertSalaryGrade(SalaryGradeVO salgra);

    List salaryGradeSearchResult(SalaryGradeVO salgra);

    void updateSalaryGrade(SalaryGradeVO salgra);
}