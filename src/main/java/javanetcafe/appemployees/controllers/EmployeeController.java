package javanetcafe.appemployees.controllers;

import javanetcafe.appemployees.entities.Employee;
import javanetcafe.appemployees.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("employees/index");
        List<Employee> employees = employeeRepository.findAll();
        modelAndView.addObject("employees", employees);

        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView("employees/create");
        Employee employee = new Employee();
        modelAndView.addObject("employee", employee);

        return modelAndView;
    }

    @PostMapping
    public ModelAndView store(@ModelAttribute Employee employee) {
        ModelAndView modelAndView = new ModelAndView();
        employeeRepository.save(employee);
        modelAndView.setViewName("redirect:/employees");

        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("employees/edit");
        Employee employee = employeeRepository.findById(id).get();
        modelAndView.addObject("employee", employee);

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        employeeRepository.deleteById(id);
        modelAndView.setViewName("redirect:/employees");

        return modelAndView;
    }
}
