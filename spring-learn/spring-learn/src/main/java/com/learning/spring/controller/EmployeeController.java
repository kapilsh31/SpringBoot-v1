package com.learning.spring.controller;

import com.learning.spring.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    //path variable -- parameter is essential for us
    //request param -- optional parameter
    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeByID(@PathVariable Long id) {

        return new EmployeeDTO(id,"Kapil",27,"email@email.com", LocalDate.of(2025,10,15),true);
    }

    /*this is for understanding
    @GetMapping
    public String getAll(@RequestParam(required = false, name = "inputAge") Integer age , here in url inputAge should be given
                              @RequestParam(required = false) String sortBy){
        return ("age : " + age + " sortBy: " + sortBy);
    }
     */

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return new EmployeeDTO();
    }






}
