package com.codegym.backend.service;

import com.codegym.backend.model.ServiceType;

import java.util.List;

public interface IServiceTypeService {
    List<ServiceType> findAllServiceType();
}
