
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.vo.UserVO;

public interface UserService {

    void deleteUser(UserVO user);

    List getAllUser();

    List<UserVO> getEmployeeAllUser(Integer employeeId);

    UserVO getEmpUser(UserVO user);

    UserVO getUser(Integer id);

    void insertUser(UserVO user);

    void updateUser(UserVO user);

    List userSearchResult(UserVO user);
}
