package com.lit.lms.services;

import com.lit.lms.dto.FeedBackAnswerDTO;
import com.lit.lms.dto.FeedBackDTO;
import com.lit.lms.dto.FeedBackQuestionDTO;
import com.lit.lms.model.FeedBackAnswer;
import com.lit.lms.model.FeedBackQuestion;
import com.lit.lms.model.Feedback;
import com.lit.lms.model.Teacher;
import com.lit.lms.repository.FeedBackAnswerRepository;
import com.lit.lms.repository.FeedBackQuestionRepository;
import com.lit.lms.repository.FeedbackRepository;
import com.lit.lms.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Jonathan on 11/1/2017.
 */
@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    FeedBackQuestionRepository feedBackQuestionRepository;

    @Autowired
    FeedBackAnswerRepository feedBackAnswerRepository;

    @Autowired
    TeacherRepository teacherRepository;

    public List<Feedback> getFeedBackSessions(){

        List<Feedback> feedbacks = new ArrayList<>();
        feedbackRepository.findAll().forEach(feedback -> {
            feedbacks.add(feedback);
        });
        return feedbacks;
    }

    public ResponseEntity<String> createFeedBack(FeedBackDTO feedBackDTO){
        List<FeedBackQuestion> fdqList = new ArrayList<>();
        Optional<Teacher> teacher = teacherRepository.findById(feedBackDTO.getTeacherId());
        Feedback feedback = new Feedback();
        feedback.setName(feedBackDTO.getName());
        feedback.setTeacher(teacher.get());

        feedBackDTO.getFeedBackQuestions().forEach(feedBackQuestion -> {

            FeedBackQuestion fdQ = new FeedBackQuestion();
            fdQ.setFeedBack(feedback);
            fdQ.setQuestion(feedBackQuestion);
            fdqList.add(fdQ);
        });

        feedbackRepository.save(feedback);
        feedBackQuestionRepository.saveAll(fdqList);
        return ResponseEntity.status(HttpStatus.CREATED).build();


    }

    public ResponseEntity<String> storeFeedBack(List<FeedBackAnswerDTO> feedBackAnswerDTO){

        List<FeedBackAnswer> feedBackAnswersList = new ArrayList<>();
        feedBackAnswerDTO.forEach(feedBackAnswerDTO1 -> {
            FeedBackAnswer fda = new FeedBackAnswer();
            fda.setFeedBackQuestion(feedBackQuestionRepository.findById(feedBackAnswerDTO1.getqId()).get());
            fda.setAnswer(feedBackAnswerDTO1.getAnswer());
            feedBackAnswersList.add(fda);
        });

        feedBackAnswerRepository.saveAll(feedBackAnswersList);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    public List<FeedBackQuestionDTO> getFeedbackQuestions(Long fId){

        List<FeedBackQuestionDTO> questionList = new ArrayList<>();
        Optional<Feedback> fb = feedbackRepository.findById(fId);
        feedBackQuestionRepository.findAll().forEach(fbqs->{
            if(fbqs.getFeedBack().getfId()==fId){
                FeedBackQuestionDTO feedBackQuestionDTO = new FeedBackQuestionDTO();
                feedBackQuestionDTO.setqId(fbqs.getqId());
                feedBackQuestionDTO.setQuestion(fbqs.getQuestion());
                questionList.add(feedBackQuestionDTO);
            }
        });


        return questionList;
    }


}
