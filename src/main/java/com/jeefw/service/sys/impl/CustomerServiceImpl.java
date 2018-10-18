package com.jeefw.service.sys.impl;

import com.jeefw.dao.sys.CustomerDao;
import com.jeefw.model.sys.Customer;
import com.jeefw.service.sys.CustomerService;
import core.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: czy
 * @Created Date: 2018-10-17
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Service
public class CustomerServiceImpl extends BaseService<Customer> implements CustomerService {


    private CustomerDao customerDao;

    @Resource
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
        this.dao = customerDao;
    }

    @Override
    public List<Customer> queryCustomerCnList(List<Customer> resultList) {
        List<Customer> customers = new ArrayList<>();
        for (Customer entity:resultList){
            Customer customer = new Customer();
            customer.setId(entity.getId());
            customer.setCustomerName(entity.getCustomerName());
            customer.setBirthday(entity.getBirthday());
            customer.setSex(entity.getSex());
            if (entity.getSex() == 1) {
                customer.setSexCn("男");
            } else if (entity.getSex() == 2) {
                customer.setSexCn("女");
            }
            customer.setEmail(entity.getEmail());
            customer.setPhone(entity.getPhone());
            if (entity.isStatus() == true) {
                customer.setStatusCn("是");
            } else {
                customer.setStatusCn("否");
            }
            customer.setRemark(entity.getRemark());
            customers.add(customer);
        }
        return customers;
    }
}
