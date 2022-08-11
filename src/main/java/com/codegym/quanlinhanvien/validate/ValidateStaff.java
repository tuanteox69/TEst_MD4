package com.codegym.quanlinhanvien.validate;

import com.codegym.quanlinhanvien.models.Staff;
import com.codegym.quanlinhanvien.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;
@Component
public class ValidateStaff implements Validator {
    @Autowired
    StaffService staffService;

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
    @Override
    public void validate(Object target, Errors errors) {
        Staff staff = (Staff) target;
        Optional<Staff> optional = staffService.findByname(staff.getName());
        if(staff.getName().equals("Tuan")){
            errors.rejectValue("name", "", "name bị cấm");
        }
    }
}
