package com.codegym.backend.service.impl;

import com.codegym.backend.dto.ServiceDto;
import com.codegym.backend.model.Service;
import com.codegym.backend.repository.IServiceRepository;
import com.codegym.backend.service.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService implements IServiceService {
    @Autowired
    IServiceRepository iServiceRepository;

    @Override
    public List<ServiceDto> findBestSeller() {
        return iServiceRepository.findBestSeller();
    }

    @Override
    public List<ServiceDto> findNewService() {
        return iServiceRepository.findNewService();
    }

    @Override
    public Page<Service> findAllService(Pageable pageable) {
        return iServiceRepository.findAllService(pageable);
    }

    @Override
    public Service findById(int id) {
        return iServiceRepository.findById(id);
    }

    @Override
    public Page<Service> findByServiceTypeId(int type_id, Pageable pageable) {
        return iServiceRepository.findByServiceTypeId(type_id,pageable);
    }

    @Override
    public List<Service> findAll() {
        return iServiceRepository.findAll();
    }
}
