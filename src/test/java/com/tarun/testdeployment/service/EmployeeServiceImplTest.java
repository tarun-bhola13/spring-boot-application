package com.tarun.testdeployment.service;

import com.tarun.testdeployment.entity.EmployeeDTO;
import com.tarun.testdeployment.response.RequestException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceImplTest {

    @Autowired
    private EmployeeServiceImpl employeeService;

    static EmployeeDTO employeeDTO;

    @BeforeAll
    static void initialize(){
        employeeDTO = new EmployeeDTO();
        employeeDTO.setEmail("test@email.com");
        employeeDTO.setFirstName("First");
        employeeDTO.setLastName("Test");
    }
    @Test
    @Order(1)
    void save() throws RequestException {

        int id = employeeService.save(employeeDTO);
        Assertions.assertTrue(id != 0);
        employeeDTO.setId(id);
    }

    @Test
    @DisplayName("Find By Id Method")
    @Order(2)
    void findById() throws RequestException {

        Optional<EmployeeDTO> employee = employeeService.findById(employeeDTO.getId());
        assertTrue(employee.isPresent());
    }

    @Test
    @Order(4)
    void deleteById() throws RequestException{

        EmployeeDTO employee = employeeService.deleteById(employeeDTO.getId());
        assertTrue(employee instanceof EmployeeDTO);
    }

    @Test
    @Order(3)
    void updateEmployee() throws RequestException {

       EmployeeDTO employee =  employeeService.updateEmployee(employeeDTO);
       assertTrue(employee.getId() == employeeDTO.getId());
    }

    @Test
    @Order(5)
    void findAll() throws RequestException{

        assertTrue(employeeService.findAll().size() > 0);
    }
}