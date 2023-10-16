package com.codegym.backend.controller;

import com.codegym.backend.dto.ServiceDto;
import com.codegym.backend.service.IServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/public")
@RestController
public class ServiceRestController {
    @Autowired
    IServiceService service;

    @GetMapping("body/new")
    public ResponseEntity<List<ServiceDto>> getListNewService() {
        List<ServiceDto> listNewService = service.findNewService();
        if (listNewService.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listNewService, HttpStatus.OK);
    }
    @GetMapping("body/best")
    public ResponseEntity<List<ServiceDto>> getListBestSeller() {
        List<ServiceDto> listBestSeller = service.findBestSeller();
        if (listBestSeller.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listBestSeller, HttpStatus.OK);
    }
}
