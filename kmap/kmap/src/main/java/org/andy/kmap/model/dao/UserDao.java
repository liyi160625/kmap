package org.andy.kmap.model.dao;

import org.andy.kmap.model.entity.CommonResult;
import org.andy.kmap.model.entity.User;


public interface UserDao {

    /**
     * This method adds a user.
     * @param user
     */
    CommonResult addUser(User user);


    /**
     * This method gets a user by email.
     * @param email
     * @return
     */
    User getUser(String email);

    String getUserRole(String email);

    CommonResult addUserInfo(User user);


}
