package com.ssafy.itda.controller;

import com.ssafy.itda.domain.Question;
import com.ssafy.itda.service.QuestionService;
import com.ssafy.itda.service.WrongQuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("QuestionController")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/qna")
public class QuestionController {
  private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
  private static final String SUCCESS = "success";
  private static final String FAIL = "fail";

  @Autowired
  private QuestionService questionService;

  // @ApiOperation(value = "문제 리스트 반환", notes = "문제 5개를 반환", response = Question.class)
  @GetMapping
  public ResponseEntity<List<Question>> getQuestion(@RequestParam("userId") @ApiParam(value = "유저 ID", required = true) int userId) throws Exception {
    logger.info("getQuestion - 호출");

    return new ResponseEntity<>(questionService.setQuestionList(userId), HttpStatus.OK);
  }

  // @ApiOperation(value = "문제 1개 반환", notes = "id가 맞는 문제 1개 반환", response = Question.class)
  @GetMapping("/one")
  public ResponseEntity<Question> getQuestionOne(@RequestParam("questionId") int questionId) throws Exception {
    logger.info("getQuestion - 호출");

    return new ResponseEntity<>(questionService.getQuestion(questionId), HttpStatus.OK);
  }

  @GetMapping("/list")
  public ResponseEntity<List<Question>> getQuestionList(@RequestParam("userId") @ApiParam(value = "유저 ID", required = true) int userId) throws Exception {
    logger.info("getQuestion - 호출");

    return new ResponseEntity<>(questionService.getQuestionList(userId), HttpStatus.OK);
  }

  // @ApiOperation(value = "문제 제출", notes = "문제 DB에 등록", response = Question.class)
  @PostMapping
  public ResponseEntity<String> postQuestion(@RequestBody Question question) throws Exception {
    logger.info("postQuestion - 호출");

    if(questionService.postQuestion(question)) {
      return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    return new ResponseEntity<>(FAIL, HttpStatus.OK);
  }

  // @ApiOperation(value = "문제 제출", notes = "문제 DB에 등록", response = Question.class)
  @PutMapping
  public ResponseEntity<String> updateQuestion(@RequestBody Question question) throws Exception {
    logger.info("updateQuestion - 호출");

    if(questionService.updateQuestion(question)) {
      return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    return new ResponseEntity<>(FAIL, HttpStatus.OK);
  }
}
