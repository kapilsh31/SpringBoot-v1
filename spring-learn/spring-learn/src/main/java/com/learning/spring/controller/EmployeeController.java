package com.learning.spring.controller;

import com.learning.spring.dto.EmployeeDTO;
import com.learning.spring.exceptions.ResourceNotFoundException;
import com.learning.spring.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
        return employeeDTO.map(ResponseEntity::ok)
                .orElseThrow(()-> new ResourceNotFoundException("Employee not found with id : " + id));
    }

    @GetMapping("/sort")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesSorted(@RequestParam(defaultValue = "id") String sortBy ,
                                                                @RequestParam(defaultValue = "asc") String direction){
        List<EmployeeDTO> employeeDTO = employeeService.getEmployeesSorted(sortBy,direction);
        return !employeeDTO.isEmpty() ? ResponseEntity.ok(employeeDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/sort-new")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesSortedDifferentWay(@RequestParam(defaultValue = "id") String sortBy ,
                                                                @RequestParam(defaultValue = "asc") String direction){
        List<EmployeeDTO> employeeDTO = employeeService.getEmployeesSortedWithMultipleFields(sortBy,direction);
        return !employeeDTO.isEmpty() ? ResponseEntity.ok(employeeDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/sort-page")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesSortedAndPaginated(@RequestParam(defaultValue = "id") String sortBy ,
                                                                            @RequestParam(defaultValue = "asc") String direction,
                                                                            @RequestParam(defaultValue = "0") Integer pageNumber){
        List<EmployeeDTO> employeeDTO = employeeService.getEmployeesSortedAndPaginated(sortBy,direction,pageNumber);
        return !employeeDTO.isEmpty() ? ResponseEntity.ok(employeeDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-name-email")
    public ResponseEntity<EmployeeDTO> getEmployeeByNameAndEmail(@RequestParam String name, @RequestParam String email){
        EmployeeDTO employeeDTO = employeeService.getEmployeeByNameAndEmail(name, email);
        return employeeDTO != null ? ResponseEntity.ok(employeeDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping("/by-name-age")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByNameOrAge(@RequestParam String name, @RequestParam Integer age){
        List<EmployeeDTO> employeeDTO = employeeService.getEmployeeByNameOrAge(name, age);
        return !employeeDTO.isEmpty() ? ResponseEntity.ok(employeeDTO) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
        return ResponseEntity.ok( employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid  @RequestBody EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.createEmployee(employeeDTO) , HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployeesById(@PathVariable Long id , @RequestBody @Valid EmployeeDTO employeeDTO){
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





}
