package com.mypack.controller;

import com.mypack.domain.Employee;
import com.mypack.mapper.EmployeeMapper;
import com.mypack.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService service;

    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        Employee emp = service.getEmp(id);
        return emp;
    }

    @GetMapping("/emp")
    public Employee update(Employee employee) {
        Employee update = service.update(employee);

        return update;


    }

    @GetMapping("/delemp")
    public String deleEmp(Integer id) {
        service.delete(id);
        return "success";
    }
}
