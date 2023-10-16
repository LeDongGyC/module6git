/**
 * FeedbackResController class use to make API
 *
 * @author TuLG
 * @version 1.0
 * @since 2023-06-13
 */


package com.codegym.backend.controller;

import com.codegym.backend.dto.FeedbackDetailDto;
import com.codegym.backend.dto.IFeedbackDto;
import com.codegym.backend.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Objects;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/private")
public class FeedbackResController {
    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping("/listFeedback")
    public ResponseEntity<Page<IFeedbackDto>> getFeedbacklist(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<IFeedbackDto> feedbackList = feedbackService.findAll(pageable);
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/getListByRateDate")
    public ResponseEntity<Page<IFeedbackDto>> getListByRateDate(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int size,
                                                                @RequestParam(defaultValue = "") String rate,
                                                                @RequestParam(defaultValue = "1900-01-01") String dateF,
                                                                @RequestParam(defaultValue = "2100-01-01") String dateT) {
        Pageable pageable = PageRequest.of(page, size);
        Page<IFeedbackDto> feedbackList;
        if(Objects.equals(rate,"")) {
            feedbackList = feedbackService.findListFeedbackByDate(pageable, dateF, dateT);
        } else {
            feedbackList = feedbackService.findListFeedbackByRateAndDate(pageable, rate, dateF, dateT);
        }
        if (feedbackList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/feedbackDetail/{id}")
    public ResponseEntity<FeedbackDetailDto> getFeedbackById(@PathVariable int id){
        FeedbackDetailDto feedbackDetail = feedbackService.findFeedbackById(id);
        if(Objects.equals(feedbackDetail.getName(), "")){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(feedbackDetail,HttpStatus.OK);
    }

    @GetMapping("/feedbackImg/{id}")
    public ResponseEntity<List<String>> getImgUrlById(@PathVariable int id){
        List<String> imgList = feedbackService.findImgUrlById(id);
        if(imgList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(imgList,HttpStatus.OK);
    }
}