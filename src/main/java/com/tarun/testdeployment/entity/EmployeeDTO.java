package com.tarun.testdeployment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDTO {

    private int id;
    private String firstName;
    private String lastName;
    private String email;

}
