package com.domhub.api.service;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.domhub.api.model.Staff;
import com.domhub.api.repository.StaffRepository;

import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    private static final Logger logger = LoggerFactory.getLogger(StaffService.class);
    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        logger.info("Fetching all staff records");
        return staffRepository.findAll();
    }

    public Optional<Staff> getOneStaffByAccountId(Integer accountId) {
        return staffRepository.findByAccountId(accountId);
    }
}
