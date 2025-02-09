package com.employeemanagement.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "salary-service", url = "http://localhost:8082/salary")
public interface SalaryClient {

    @PutMapping("/updateSalary")
    String updateSalary(@RequestParam Long empId, @RequestParam String newRole);
}