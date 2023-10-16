package com.codegym.backend.controller;

import com.codegym.backend.dto.CreateFeedback;
import com.codegym.backend.service.impl.FeedbackProcessingService;
import com.codegym.backend.validation.FeedbackCreateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/private/feedback")
public class FeedbackController {
    @Autowired
    private FeedbackProcessingService feedbackProcessingService;
    @Autowired
    private FeedbackCreateDto feedbackCreateDto;

    @PostMapping("/create")
    public ResponseEntity<Object> createFeedback(@RequestBody CreateFeedback feedbackCreate, BindingResult bindingResult) {
        feedbackCreateDto.validate(feedbackCreate, bindingResult);
        if (bindingResult.hasErrors()) {
            List<String> message = new ArrayList<>();
            bindingResult.getAllErrors().forEach(e -> message.add(e.getDefaultMessage()));
            return ResponseEntity.badRequest().body(message);
        }
        feedbackProcessingService.processFeedback(feedbackCreate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/count/{email}")
    public Integer countByEmail(@PathVariable("email") String email) {
        return feedbackProcessingService.countEmail(email);
    }
}
