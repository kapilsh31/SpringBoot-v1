package com.learning.spring.controller;

import com.learning.spring.dto.EmployeeDTO;
import com.learning.spring.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/employees")
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeByID(@PathVariable Long id) {
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeById(id);
        return employeeDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok( employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeesById(@PathVariable Long id , @RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(employeeService.updateEmployeesById(id,employeeDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteEmpById(@PathVariable Long id){
        boolean deleted = employeeService.deleteEmpById(id);
        return deleted ? ResponseEntity.ok(true) : ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@RequestBody Map<String, Object> updates,
                                                                 @PathVariable Long employeeId) {
        EmployeeDTO employeeDTO = employeeService.updatePartialEmployeeById(employeeId, updates);
        return employeeDTO ==null ? ResponseEntity.notFound().build() :  ResponseEntity.ok(employeeDTO);

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
