package com.luv2code.springboot.cruddemo.controller;

import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployee(Model model) {

        List<Employee> employeeList = employeeService.findAll();

        model.addAttribute("employees", employeeList);
        return "employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String addEmployee(Model model) {

        Employee employee = new Employee();

        model.addAttribute("employee", employee);


        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String update(@RequestParam("employeeId") int id, Model model) {

        Employee theEmployee = employeeService.findById(id);

        model.addAttribute("employee", theEmployee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String addNewEmployee(@ModelAttribute("employee") Employee employee) {

        employeeService.save(employee);

        return "redirect:/employees/list";


    }

    @GetMapping("/showFormForDelete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {

        employeeService.deleteById(id);

        return "redirect:/employees/list";


    }
}
