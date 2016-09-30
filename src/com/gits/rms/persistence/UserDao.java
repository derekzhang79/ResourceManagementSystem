
package com.gits.rms.persistence;

import java.util.List;

import com.gits.rms.vo.UserVO;

public interface UserDao {

    List getAllUser();

    UserVO getUser(Integer id);

    void insertUser(UserVO user);

    void updateUser(UserVO user);

    void deleteUser(UserVO user);

    UserVO getEmpUser(UserVO user);

    List<UserVO> getEmployeeAllUser(Integer employeeId);

    List userSearchResult(UserVO user);
}