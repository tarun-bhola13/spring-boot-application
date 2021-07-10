package com.tarun.testdeployment.controller;

import com.tarun.testdeployment.entity.Employee;
import com.tarun.testdeployment.entity.EmployeeDTO;
import com.tarun.testdeployment.response.RequestException;
import com.tarun.testdeployment.response.Response;
import com.tarun.testdeployment.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    @PostMapping("/employee")
    public String save(@RequestBody EmployeeDTO employee) throws RequestException {
        int id = employeeService.save(employee);
        return "Employee Created Successfully with Id : " + id;
    }

    @GetMapping("/employeeById")
    public EmployeeDTO findById(@RequestParam("empId") int empId) throws RequestException {
        Optional<EmployeeDTO> employee = employeeService.findById(empId);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new RequestException(
                new Response("Employee Not Found with Id " + empId, LocalDate.now()),
                HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public EmployeeDTO deleteById(@RequestParam("empId") int empId) throws RequestException {
        return employeeService.deleteById(empId);
    }

    @PutMapping("/employee")
    public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employee) throws RequestException {
        return employeeService.updateEmployee(employee);
    }

    @GetMapping("/employees")
    public List<Employee> findAll() throws RequestException {
        return employeeService.findAll();
    }


}
