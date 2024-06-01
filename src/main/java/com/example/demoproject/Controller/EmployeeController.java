package com.example.demoproject.Controller;


import com.example.demoproject.Entity.Employee;
import com.example.demoproject.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;
@RestController
@RequestMapping("/employees")
public class EmployeeController {

        @Autowired
        private EmployeeService employeeService;

        @GetMapping("/AllEmployee")
        public List<Employee> getAllEmployees() {
            return employeeService.getAllEmployee();
        }

        @GetMapping("EmployeebyId/{id}")
        public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
            Optional<Employee> employee = employeeService.getEmployeeById(id);
            return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PostMapping("/AddEmployee")
        public Employee createEmployee(@RequestBody Employee employee) {
            return employeeService.saveEmployee(employee);
        }

        @PutMapping("/Updateemployee/{id}")
        public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
            Optional<Employee> optionalEmployee = employeeService.getEmployeeById(id);
            if (optionalEmployee.isPresent()) {
                Employee employee = optionalEmployee.get();
                employee.setName(employeeDetails.getName());
                employee.setRole(employeeDetails.getRole());
                return ResponseEntity.ok(employeeService.saveEmployee(employee));
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
            if (employeeService.getEmployeeById(id).isPresent()) {
                employeeService.deleteEmployee(id);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.notFound().build();
            }
        }
    }