package com.lit.lms.services;

import com.lit.lms.dto.QuizMarkDTO;
import com.lit.lms.model.AnsweredQuestion;
import com.lit.lms.model.Quiz;
import com.lit.lms.model.QuizMark;
import com.lit.lms.model.Student;
import com.lit.lms.repository.*;
import com.lit.lms.model.*;
import com.lit.lms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by kashifroshen on 10/21/17.
 */
@Service
public class QuizMarkService {
    @Autowired
    QuizMarkRepository quizMarkRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    AnsweredQuestionRepository answeredQuestionRepository;

    QuizMark quizMark;

    public QuizMark addMarks(QuizMarkDTO quizMarkDTO){

        QuizMark quizMark = new QuizMark();

        Optional<Student> student = studentRepository.findById(quizMarkDTO.getStudent());
        Optional<Quiz> quiz = quizRepository.findById(quizMarkDTO.getQuiz());

       List<AnsweredQuestion> answeredQuestions = new ArrayList<>();
       AnsweredQuestion answeredQuestion;

        Long questionId;
        String selectedAnswer;

        for(int i=0;i<quizMarkDTO.getAnsweredQuestions().size();i++) {
            questionId = quizMarkDTO.getAnsweredQuestions().get(i).getQuestion();
            selectedAnswer = quizMarkDTO.getAnsweredQuestions().get(i).getSelectedAnswer();

            answeredQuestion = new AnsweredQuestion(questionRepository.findById(questionId).get(),selectedAnswer);
            answeredQuestion = answeredQuestionRepository.save(answeredQuestion);
            answeredQuestions.add(answeredQuestion);
        }

        quizMark.setMarks(quizMarkDTO.getMarks());
        quizMark.setStudent(student.get());
        quizMark.setQuiz(quiz.get());
        quizMark.setAnsweredQuestions(answeredQuestions);
        quizMark = quizMarkRepository.save(quizMark);

        return quizMark;
    }


    public QuizMark getMark(Long quizId,Long studentId){
        quizMark = new QuizMark();

        quizMarkRepository.findByStudent(studentRepository.findById(studentId).get()).forEach(quizm -> {
            if (quizm.getQuiz().getqId() == quizId) {
               quizMark = quizm;
            }
        });
        return quizMark;
    }


    public Student getQuizMark(QuizMarkDTO quizMarkDTO) {
        return studentRepository.findById(quizMarkDTO.getStudent()).get();
    }

    public List<QuizMark> getMarks(Long quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        return quizMarkRepository.findByQuiz(quiz.get());
    }


}
