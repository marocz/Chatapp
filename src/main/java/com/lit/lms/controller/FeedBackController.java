package com.lit.lms.controller;

import com.lit.lms.dto.FeedBackAnswerDTO;
import com.lit.lms.dto.FeedBackDTO;
import com.lit.lms.dto.FeedBackQuestionDTO;
import com.lit.lms.model.*;
import com.lit.lms.services.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Jonathan on 11/1/2017.
 */

@RestController
@RequestMapping(path = "/feedbacks")
public class FeedBackController {

    @Autowired
    private FeedbackService feedBackService;

    @GetMapping("/")
    @ResponseBody()
    public Iterable<Feedback> getAllFeedbacks(){
        return feedBackService.getFeedBackSessions();
    }

    @PostMapping("/")
    @ResponseBody()
    public  ResponseEntity<String> createFeedBacks(@RequestBody FeedBackDTO feedbackDTO){
        return feedBackService.createFeedBack(feedbackDTO);
    }

    @PostMapping("/feedback-answers")
    @ResponseBody()
    public ResponseEntity<String> storeFeedBacksAnswers(@RequestBody List<FeedBackAnswerDTO> feedbackDTO){
        return feedBackService.storeFeedBack(feedbackDTO);
    }

    @GetMapping("/{fId}/questions")
    @ResponseBody()
    public Iterable<FeedBackQuestionDTO> getFeedbackQuestions(@PathVariable("fId") Long fId){
        return feedBackService.getFeedbackQuestions(fId);
    }


}