package com.tarun.testdeployment.service;

import com.tarun.testdeployment.entity.Employee;
import com.tarun.testdeployment.entity.EmployeeDTO;
import com.tarun.testdeployment.repository.EmployeeRepository;
import com.tarun.testdeployment.response.RequestException;
import com.tarun.testdeployment.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public int save(EmployeeDTO employeeDTO) throws RequestException {
        Employee employee = convertDTOToEntity(employeeDTO);
        return employeeRepository.save(employee).getId();
    }

    @Override
    public Optional<EmployeeDTO> findById(int id) throws RequestException{

        Optional<Employee> employee = Optional.of(employeeRepository.findById(id).
                orElseThrow(()-> new RequestException
                        (new Response("Employee id is not valid "+ id, LocalDate.now()), HttpStatus.OK)));
        Optional<EmployeeDTO> employeeDTO;
            employeeDTO = Optional.of(convertEntityToDTO(employee.get()));


        return employeeDTO;
    }

    @Override
    public EmployeeDTO deleteById(int id) throws RequestException {
        Optional<EmployeeDTO> employeeDTO;
        Optional<Employee> employee = Optional.of(employeeRepository.findById(id).
                orElseThrow(()->new RequestException
                        (new Response("Employee Id is not Valid : "+id,LocalDate.now()),HttpStatus.OK)));
            employeeRepository.deleteById(id) ;
            employeeDTO = Optional.of(convertEntityToDTO(employee.get()));


        return employeeDTO.get();
    }

    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO) throws RequestException {
        Employee employee = convertDTOToEntity(employeeDTO);
        Optional<Employee> employee1 =
                Optional.of(employeeRepository.findById(employee.getId())
                        .orElseThrow(()->new RequestException
                                (new Response("Employee Not Found ",LocalDate.now()),
                                        HttpStatus.OK)));
            employeeRepository.save(employee1.get());

        return findById(employeeDTO.getId()).get();
    }

    @Override
    public List<Employee> findAll() throws RequestException{
        return employeeRepository.findAll();
    }

    private Employee convertDTOToEntity(EmployeeDTO employeeDTO){

        var employee = new Employee();
        employee.setEmail(employeeDTO.getEmail());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setId(employeeDTO.getId());
        return employee;
    }

    private EmployeeDTO convertEntityToDTO(Employee employee){

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setId(employee.getId());
        return employeeDTO;
    }
}
