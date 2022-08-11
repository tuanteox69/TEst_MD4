package com.codegym.quanlinhanvien.controllers;

import com.codegym.quanlinhanvien.models.Branch;
import com.codegym.quanlinhanvien.models.Staff;
import com.codegym.quanlinhanvien.service.BranchService;
import com.codegym.quanlinhanvien.service.StaffService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StaffController {
@Autowired
    StaffService staffService;
@Autowired
BranchService branchService;
//@Autowired
//    ValidateStaff validateStaff;
@ModelAttribute(name = "staff")
    public Staff staff(){return new Staff();}
    @ModelAttribute(name = "branch")
    public List<Branch> departments(){
        return branchService.getAll();
    }
    @GetMapping("/staffs")
    public ModelAndView show(@RequestParam(defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("staff", staffService.getAll(PageRequest.of(page, 5, Sort.by("age"))));
        return modelAndView;
    }
    @PostMapping("/search")
    public ModelAndView search(@RequestParam(defaultValue = "0") int page, @RequestParam String search) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("staff", staffService.getAllByname(PageRequest.of(page, 5, Sort.by("age")), search));
        return modelAndView;
    }
    @GetMapping("/create")
    public ModelAndView showCreate() {
        return new ModelAndView("create");
    }
    @PostMapping("/create")
    public ModelAndView create( @ModelAttribute("staff") Staff staff) {
        staffService.save(staff);
        return new ModelAndView("redirect:/staffs");
    }
    @GetMapping("/edit")
    public ModelAndView showEdit(@RequestParam long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("staff", staffService.findById(id));
        modelAndView.addObject("branch", branchService.getAll());
        return modelAndView;
    }
    @PostMapping ("/edit")
    public ModelAndView edit( @ModelAttribute("staff") Staff staff) {
        staffService.save(staff);
        return new ModelAndView("redirect:/staffs");
    }
    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("redirect:/staffs");
        staffService.delete(id);
        return modelAndView;
    }
}
