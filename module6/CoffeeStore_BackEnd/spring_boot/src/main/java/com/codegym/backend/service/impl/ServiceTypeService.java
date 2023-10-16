package com.codegym.backend.service.impl;

import com.codegym.backend.model.ServiceType;
import com.codegym.backend.repository.IServiceTypeRepository;
import com.codegym.backend.service.IServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceTypeService implements IServiceTypeService {

    @Autowired
    IServiceTypeRepository iServiceTypeRepository;
    @Override
    public List<ServiceType> findAllServiceType() {
        return iServiceTypeRepository.findAllServiceType();
    }
}
