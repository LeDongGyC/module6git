package com.codegym.backend.service;

import com.codegym.backend.dto.ServiceDto;
import com.codegym.backend.model.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IServiceService {
    List<ServiceDto> findBestSeller();
    List<ServiceDto> findNewService();
    Page<Service> findAllService(Pageable pageable);
    Service findById(int id);
    Page<Service> findByServiceTypeId(int type_id,Pageable pageable);
    List<Service> findAll();
}
