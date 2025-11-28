package com.learning.spring.controller;

import com.learning.spring.dto.EmployeeDTO;
import com.learning.spring.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeByID(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return employeeService.createEmployee(employeeDTO);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployeesById(@PathVariable Long id , @RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployeesById(id,employeeDTO);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmpById(@PathVariable Long id){
        return employeeService.deleteEmpById(id);
    }

    @PatchMapping(path = "/{employeeId}")
    public EmployeeDTO updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                                 @PathVariable Long employeeId) {
        return employeeService.updatePartialEmployeeById(employeeId, updates);
    }



    /*this is for understanding
    @GetMapping
    public String getAll(@RequestParam(required = false, name = "inputAge") Integer age , here in url inputAge should be given
                              @RequestParam(required = false) String sortBy){
        return ("age : " + age + " sortBy: " + sortBy);
    }

      //path variable -- parameter is essential for us
    //request param -- optional parameter
     */





}
