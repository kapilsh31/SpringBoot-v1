package com.learning.spring.service;

import com.learning.spring.dto.EmployeeDTO;
import com.learning.spring.entity.EmployeeEntity;
import com.learning.spring.exceptions.ResourceNotFoundException;
import com.learning.spring.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        Optional<EmployeeEntity> emp = employeeRepository.findById(id);
        return emp.map(entity-> modelMapper.map(entity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeEntity> emp = employeeRepository.findAll();
        return emp.stream()
                .map(e -> modelMapper.map(e ,EmployeeDTO.class ))
                .toList();
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        log.info("inside create Employees method : " + employeeDTO.toString());
        EmployeeEntity input = modelMapper.map(employeeDTO, EmployeeEntity.class);

        boolean check = existsByEmail(input.getEmail());
        log.info("value of check : " + check);
        if(!check){
            EmployeeEntity savedEntity = employeeRepository.save(input);
            return modelMapper.map(savedEntity,EmployeeDTO.class);
        }
        else{
            //case for update
            log.info("inside else : ");
            return updateEmployeesByEmail(employeeDTO);
        }

        //return employeeDTO;

    }

    private boolean existsByEmail(String email) {
        if(email==null || email.trim().isEmpty()){
            throw new NullPointerException();
        }
        return employeeRepository.existsByEmail(email);
    }

    private void existsById(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id : " + id);
    }

    public EmployeeDTO updateEmployeesById(Long id, EmployeeDTO employeeDTO) {
        existsById(id);
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setId(id);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeesByEmail(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeRepository.findByEmail(employeeDTO.getEmail());
        log.info("after fetching findByEmail method : " + employeeEntity.toString());
        employeeEntity.setName(employeeDTO.getName());
        employeeEntity.setAge(employeeDTO.getAge());
        employeeEntity.setDateOfJoining(employeeDTO.getDateOfJoining());
        log.info(" redirecting value to entity after fetching : " + employeeEntity.toString());
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public boolean deleteEmpById(Long id) {
        existsById(id);
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long employeeId, Map<String, Object> updates) {
        existsById(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.getRequiredField(EmployeeEntity.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}
