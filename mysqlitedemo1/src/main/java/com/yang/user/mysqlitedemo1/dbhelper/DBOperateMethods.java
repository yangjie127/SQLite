package com.yang.user.mysqlitedemo1.dbhelper;

import java.util.List;

/**
 * Created by YangJie on 2017/4/10.
 */

public interface DBOperateMethods {
    /**
     * 插入
     * @param user
     * @return
     */
    boolean insert(User user);

    /**
     * 删除
     * @param userId
     * @return
     */

    boolean delete(int userId);

    /**
     *更新
     * @param userId
     * @param user
     * @return
     */
    boolean update(int userId, User user);

    /**
     *
     * @param userId
     * @return
     */
    User selectById(int userId);

    List<User> selectAll();

    boolean release();

    boolean deleteAll();

    List<User> query();


}
