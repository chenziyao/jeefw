package com.jeefw.model.sys;

import com.jeefw.model.sys.param.CustomerParameter;


import javax.persistence.*;
import java.util.Date;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * @Description:
 * @Author: czy
 * @Created Date: 2018-10-17
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Entity
@Table(name = "customer")
@Cache(region = "all",usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer extends CustomerParameter {

    @Id
    @Column(name = "id")
    private long id;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "sex")
    private Short sex;
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "status")
    private boolean status;
    @Column(name = "remark")
    private String remark;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;
        if (status != customer.status) return false;
        if (customerName != null ? !customerName.equals(customer.customerName) : customer.customerName != null)
            return false;
        if (sex != null ? !sex.equals(customer.sex) : customer.sex != null) return false;
        if (birthday != null ? !birthday.equals(customer.birthday) : customer.birthday != null) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        if (remark != null ? !remark.equals(customer.remark) : customer.remark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (status ? 1 : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        return result;
    }
}
