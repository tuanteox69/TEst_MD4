package com.codegym.quanlinhanvien.repository;

import com.codegym.quanlinhanvien.models.Staff;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IStaffRepo extends PagingAndSortingRepository<Staff,Long> {
    Page<Staff> findAllBynameContaining(Pageable pageable, String name);
    Optional<Staff> findByname(String name);


}
