package com.codegym.backend.controller;

import com.codegym.backend.model.FeedbackType;
import com.codegym.backend.service.IFeedbackTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/private")
public class FeedbackTypeController {
    @Autowired
    private IFeedbackTypeService feedbackTypeService;

    @GetMapping("/type/list")
    public ResponseEntity<List<FeedbackType>> getAllType() {
        List<FeedbackType> feedbackTypeList = feedbackTypeService.selectAll();
        if (feedbackTypeList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(feedbackTypeList, HttpStatus.OK);
    }
}
