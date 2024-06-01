package com.example.demoproject.Service;

import com.example.demoproject.Entity.Employee;
import com.example.demoproject.Repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public List<Employee> getAllEmployee(){
        return employeeRepo.findAll();
    }

    public Optional<Employee>getEmployeeById(Long id){
        return employeeRepo.findById(id);
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public void deleteEmployee(Long id)
    {
        employeeRepo.deleteById(id);
    }

}
