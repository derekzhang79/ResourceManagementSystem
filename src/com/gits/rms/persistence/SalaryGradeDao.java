
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.SalaryGradeVO;

public interface SalaryGradeDao {

    List getAllSalaryGrade();

    SalaryGradeVO getSalaryGrade(Integer id);

    void insertSalaryGrade(SalaryGradeVO sal);

    void updateSalaryGrade(SalaryGradeVO sal);

    void deleteSalaryGrade(SalaryGradeVO salgra);

    List salaryGradeSearchResult(SalaryGradeVO salgra);

    List checkSalaryGradeInJobTitle(SalaryGradeVO salgra);
}