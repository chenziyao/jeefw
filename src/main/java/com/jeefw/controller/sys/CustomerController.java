package com.jeefw.controller.sys;

import com.jeefw.core.Constant;
import com.jeefw.core.JavaEEFrameworkBaseController;
import com.jeefw.model.sys.Customer;
import com.jeefw.service.sys.CustomerService;
import core.support.ExtJSBaseParameter;
import core.support.JqGridPageView;
import core.support.QueryResult;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: czy
 * @Created Date: 2018-10-17
 * @LastModifyDate:
 * @LastModifyBy:
 * @Version:
 */
@Controller
@RequestMapping("/collectdata/customer")
public class CustomerController extends JavaEEFrameworkBaseController<Customer> implements Constant {

    @Resource
    CustomerService customerService;

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping(value = "/getCustomer", method = { RequestMethod.POST, RequestMethod.GET })
    public void getCustomer(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Integer firstResult = Integer.valueOf(request.getParameter("page"));
        Integer maxResults = Integer.valueOf(request.getParameter("rows"));
        String sortedObject = request.getParameter("sidx");
        String sortedValue = request.getParameter("sord");
        String filters = request.getParameter("filters");

        Customer customer = new Customer();
        if (StringUtils.isNotBlank(filters)){
            JSONObject jsonObject = JSONObject.fromObject(filters);
            JSONArray jsonArray = (JSONArray) jsonObject.get("rules");

            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject result = (JSONObject) jsonArray.get(i);
                if (result.getString("field").equals("customerName") && result.getString("op").equals("eq")) {
                    customer.set$like_customerName(result.getString("data"));
                }
            }
            if (((String) jsonObject.get("groupOp")).equalsIgnoreCase("OR")) {
                customer.setFlag("OR");
            } else {
                customer.setFlag("AND");
            }
        }

        customer.setFirstResult((firstResult - 1) * maxResults);
        customer.setMaxResults(maxResults);
        Map<String, String> sortedCondition = new HashMap<String, String>();
        sortedCondition.put(sortedObject, sortedValue);
        customer.setSortedConditions(sortedCondition);
        QueryResult<Customer> queryResult = customerService.doPaginationQuery(customer);
        JqGridPageView<Customer> customerListView = new JqGridPageView<>();
        customerListView.setMaxResults(maxResults);
        List<Customer> customerList = customerService.queryCustomerCnList(queryResult.getResultList());
        customerListView.setRows(customerList);
        customerListView.setRecords(queryResult.getTotalCount());
        writeJSON(response,customerListView);

    }

    public void doSave(Customer entity, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ExtJSBaseParameter parameter = ((ExtJSBaseParameter) entity);
        if (CMD_EDIT.equals(parameter.getCmd())) {

            Customer customer = customerService.get(entity.getId());
            customerService.merge(entity);

        } else if (CMD_NEW.equals(parameter.getCmd())) {

            customerService.persist(entity);

        }

        parameter.setSuccess(true);
        writeJSON(response, parameter);
    }


    // 操作用户的删除、导出Excel、字段判断和保存
    @RequestMapping(value = "/operateCustomer", method = { RequestMethod.POST, RequestMethod.GET })
    public void operateSysUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String oper = request.getParameter("oper");
        String id = request.getParameter("id");
        if (oper.equals("del")) {
            String[] ids = id.split(",");
            //deleteSysUser(request, response, (Long[]) ConvertUtils.convert(ids, Long.class));
        } else if (oper.equals("excel")) {
            /*response.setContentType("application/msexcel;charset=UTF-8");
            try {
                response.addHeader("Content-Disposition", "attachment;filename=file.xls");
                OutputStream out = response.getOutputStream();
                out.write(URLDecoder.decode(request.getParameter("csvBuffer"), "UTF-8").getBytes());
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }*/
        } else {
            Map<String, Object> result = new HashMap<String, Object>();
            String customerName = request.getParameter("customerName");
            String email = request.getParameter("email");
            Customer customer = null;
            if (oper.equals("edit")) {
                customer = customerService.get(Long.valueOf(id));
            }

            if (StringUtils.isBlank(customerName) || StringUtils.isBlank(email)) {
                response.setStatus(HttpServletResponse.SC_LENGTH_REQUIRED);
                result.put("message", "请填写姓名和邮箱");
                writeJSON(response, result);
            }else {
                Customer entity = new Customer();
                entity.setCustomerName(customerName);
                entity.setSex(Short.valueOf(request.getParameter("sexCn")));
                entity.setEmail(email);
                entity.setPhone(request.getParameter("phone"));
                if (StringUtils.isNotBlank(request.getParameter("birthday"))) {
                    entity.setBirthday(dateFormat.parse(request.getParameter("birthday")));
                }
                entity.setStatusCn(request.getParameter("statusCn"));
                if (entity.getStatusCn().equals("是")) {
                    entity.setStatus(true);
                } else {
                    entity.setStatus(false);
                }
                entity.setRemark(request.getParameter("remark"));
                if (oper.equals("edit")) {
                    entity.setId(Long.valueOf(id));
                    entity.setCmd("edit");
                    doSave(entity, request, response);
                } else if (oper.equals("add")) {
                    entity.setCmd("new");
                    doSave(entity, request, response);
                }
            }
        }
    }

}
