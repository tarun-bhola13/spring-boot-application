package com.tarun.testdeployment.service;

import com.tarun.testdeployment.entity.Employee;
import com.tarun.testdeployment.entity.EmployeeDTO;
import com.tarun.testdeployment.response.RequestException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    int save(EmployeeDTO employeeDTO) throws RequestException;
    Optional<EmployeeDTO> findById(int id) throws RequestException;
    EmployeeDTO deleteById(int id) throws RequestException;
    EmployeeDTO updateEmployee(EmployeeDTO employee) throws RequestException;
    List<Employee> findAll() throws RequestException;

}
