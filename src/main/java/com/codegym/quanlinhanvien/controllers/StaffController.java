package com.codegym.quanlinhanvien.controllers;

import com.codegym.quanlinhanvien.models.Branch;
import com.codegym.quanlinhanvien.models.Staff;
import com.codegym.quanlinhanvien.service.BranchService;
import com.codegym.quanlinhanvien.service.StaffService;

import com.codegym.quanlinhanvien.validate.ValidateStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class StaffController {
@Autowired
    StaffService staffService;
@Autowired
BranchService branchService;
@Autowired
ValidateStaff validateStaff;
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
    public ModelAndView create(@Valid @ModelAttribute("staff") Staff staff, BindingResult bindingResult) {
        validateStaff.validate(staff, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            ModelAndView modelAndView = new ModelAndView("/create");
            return modelAndView;
        }
        staffService.save(staff);
        ModelAndView modelAndView = new ModelAndView("redirect:/staffs");
        return modelAndView;
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
    public ModelAndView delete(@RequestParam int id, RedirectAttributes redirect){
        ModelAndView modelAndView = new ModelAndView("redirect:/staffs");
        staffService.delete(id);
        redirect.addFlashAttribute("success", "Removed staff successfully!");
        return modelAndView;
    }
    @GetMapping("/view")
    public ModelAndView showblog(@RequestParam int id , Model model) {
        ModelAndView modelAndView = new ModelAndView("View");
        Optional<Staff> optional = staffService.findById(id);
        modelAndView.addObject("staffView", optional.get());
        return modelAndView;
    }
}
