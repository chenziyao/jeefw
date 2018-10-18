package com.jeefw.dao.sys.impl;

import com.jeefw.dao.sys.CustomerDao;
import com.jeefw.model.sys.Customer;
import core.dao.BaseDao;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: czy
 * @Created Date: 2018-10-17
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Repository
public class CustomerDaoImpl extends BaseDao<Customer> implements CustomerDao {

    public CustomerDaoImpl() {
        super(Customer.class);
    }
}
