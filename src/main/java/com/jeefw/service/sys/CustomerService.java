package com.jeefw.service.sys;

import com.jeefw.model.sys.Customer;
import core.service.Service;

import java.util.List;

/**
 * @Description:
 * @Author: czy
 * @Created Date: 2018-10-17
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version: v1.4.0
 */
public interface CustomerService extends Service<Customer> {

    List<Customer> queryCustomerCnList(List<Customer> resultList);
}
