package com.codegym.quanlinhanvien.service;

import com.codegym.quanlinhanvien.models.Branch;
import com.codegym.quanlinhanvien.repository.IBranchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BranchService {
    @Autowired
    IBranchRepo iBranchRepo;

    public List<Branch> getAll(){
        return (List<Branch>) iBranchRepo.findAll();
    }
    public Branch findById(long id){
        return iBranchRepo.findById(id).get();
    }
}
