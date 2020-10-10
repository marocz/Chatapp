package com.lit.lms.controller;

import com.lit.lms.dto.QuizMarkDTO;
import com.lit.lms.model.QuizMark;
import com.lit.lms.services.QuizMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by kashifroshen on 10/21/17.
 */
@RestController
@RequestMapping(path = "/quizzes")
public class QuizMarkController {
    @Autowired
    private QuizMarkService quizMarksService;

    @GetMapping("/{quizId}/students/{studentId}/quizmarks")
    @ResponseBody()
    public QuizMark getAllQuizzes(@PathVariable("quizId") Long quizId,@PathVariable("studentId") Long studentId){
        return quizMarksService.getMark(quizId, studentId);

    }

    @PostMapping("/{quizId}/students/{studentId}/quizmarks")
    @ResponseBody
    public QuizMark createQuizMark(@RequestBody QuizMarkDTO quizMarkDTO){
       return quizMarksService.addMarks(quizMarkDTO);
    }

    @GetMapping("/marks/{quizId}")
    public List<QuizMark> getMarks(@PathVariable("quizId") Long quizId){
        return quizMarksService.getMarks(quizId);
    }
}
