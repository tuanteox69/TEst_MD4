package com.codegym.quanlinhanvien.service;

import com.codegym.quanlinhanvien.models.Staff;
import com.codegym.quanlinhanvien.repository.IStaffRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class StaffService {
    @Autowired
    IStaffRepo iStaffRepo;

    public Page<Staff> getAll(Pageable pageable) {
        return  iStaffRepo.findAll(pageable);
    }
    public Page<Staff> getAllByname(Pageable pageable, String name) {
        return  iStaffRepo.findAllBynameContaining(pageable,name);
    }

    public void save(Staff staff) {
        iStaffRepo.save(staff);
    }

    public void delete(long id) {
        iStaffRepo.deleteById(id);
    }

    public Optional<Staff> findById(long id) {
        return iStaffRepo.findById(id);
    }

    public Optional<Staff> findByname(String name) {
        return iStaffRepo.findByname(name);
    }
    public Optional<Staff> findByStaffCode(String name) {
        return iStaffRepo.findByStaffCode(name);
    }
}
