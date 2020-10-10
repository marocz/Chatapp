package com.lit.lms.services;

import com.lit.lms.dto.QuizDTO;
import com.lit.lms.dto.QuizQuestion;
import com.lit.lms.exceptions.ResourceNotFoundException;
import com.lit.lms.model.Question;
import com.lit.lms.model.Quiz;
import com.lit.lms.repository.CourseRepository;
import com.lit.lms.repository.QuestionRepository;
import com.lit.lms.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dinukshakandasamanage on 9/23/17.
 */
@Service
public class QuizService {

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    QuestionRepository questionRepository;

    public List<Quiz> getAllQuizzes() {
        List<Quiz> quizzes = new ArrayList<>();
        quizRepository.findAll().forEach(quizzes::add);
        return quizzes;
    }

    public Quiz getAllQuestionsForQuiz(Long qId){
        return quizRepository.findById(qId).get();
    }

    public Quiz createQuiz(QuizDTO quizDTO) {
        Quiz quiz = new Quiz(quizDTO.getName(), courseRepository.findById(quizDTO.getCourse()).get());
        quiz.setDate(quizDTO.getDate());
        quiz.setDuration(quizDTO.getDuration());
        return quizRepository.save(quiz);
    }

    public void addQuestions(long quizId, List<QuizQuestion> quizQuestions) throws ResourceNotFoundException {
        Quiz quiz = quizRepository.findById(quizId).get();
        String question = "Question";
        String field = "Quiz ID";
        if (null != quiz) {
            quizQuestions.forEach(quizQuestion -> quiz.addQuestion(addQuestion(quizId, quizQuestion)));
        }
        throw new ResourceNotFoundException(question, field, quizId);

    }

    public Question addQuestion(long quizId, QuizQuestion quizQuestion) {
        Quiz quiz = quizRepository.findById(quizId).get();

        Question question = new Question();
        question.setQuiz(quiz);
        question.setAnswers(quizQuestion.getAnswers());
        question.setCorrectAnswer(quizQuestion.getCorrectAnswer());
        question.setQuestion(quizQuestion.getQuestion());

        return questionRepository.save(question);

    }

    public void deleteQuestion(Long quizId, Long questionId) {
        Question questions = questionRepository.findById(questionId).get();
        questionRepository.delete(questions);
    }

    public Question getQuestionById(Long quizId, Long questionId) {
        return questionRepository.findById(questionId).get();
    }

    public Question editQuestion(Long quizId, Long questionId, QuizQuestion quizQuestion) {
        Question question = questionRepository.findById(questionId).get();
        question.setQuiz(quizRepository.findById(quizId).get());
        question.setAnswers(quizQuestion.getAnswers());
        question.setCorrectAnswer(quizQuestion.getCorrectAnswer());
        question.setQuestion(quizQuestion.getQuestion());
        return questionRepository.save(question);
    }

    public List<Quiz> getAllQuizzesByCourseId(Long courseId) {
        return getAllQuizzes().stream().filter(quiz -> quiz.getCourse().getcId().equals(courseId)).collect(Collectors.toList());
    }

    public Quiz markQuizAsActive(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).get();
        if(quiz.isActive())
            quiz.setActive(false);
        else
            quiz.setActive(true);
        return quizRepository.save(quiz);
    }
}
