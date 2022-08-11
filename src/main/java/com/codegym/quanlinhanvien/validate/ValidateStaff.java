import com.codegym.quanlinhanvien.models.Staff;
import com.codegym.quanlinhanvien.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import javax.validation.Validator;
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
        Optional<Staff> optional = staffService.findByStaffCode(staff.getStaffCode());
        if (optional.isPresent()){
            errors.rejectValue("staffCode", "", "Employee duplicate code");
        }

    }
}
