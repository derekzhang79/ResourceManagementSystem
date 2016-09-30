
package com.gits.rms.service;

import java.util.List;

import com.gits.rms.persistence.UserDao;
import com.gits.rms.persistence.UserHibernateDao;
import com.gits.rms.vo.UserVO;

public class UserDaoService implements UserService {
    private UserDao dao;

    public UserDaoService() {
        dao = new UserHibernateDao();
    }

    @Override
    public void deleteUser(UserVO user) {
        dao.deleteUser(user);
    }

    @Override
    public List getAllUser() {
        return dao.getAllUser();
    }

    @Override
    public List<UserVO> getEmployeeAllUser(Integer employeeId) {
        return dao.getEmployeeAllUser(employeeId);
    }

    @Override
    public UserVO getEmpUser(UserVO user) {
        return dao.getEmpUser(user);
    }

    @Override
    public UserVO getUser(Integer id) {
        return dao.getUser(id);
    }

    @Override
    public void insertUser(UserVO user) {
        dao.insertUser(user);
    }

    @Override
    public void updateUser(UserVO user) {
        dao.updateUser(user);
    }

    @Override
    public List userSearchResult(UserVO user) {
        return dao.userSearchResult(user);
    }
}
