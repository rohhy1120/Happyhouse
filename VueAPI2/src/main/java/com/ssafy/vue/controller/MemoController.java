package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.MemoDto;
import com.ssafy.vue.model.service.BoardService;
import com.ssafy.vue.model.service.MemoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//http://localhost:9999/vue/swagger-ui.html
//@CrossOrigin(origins = { "*" }, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.POST} , maxAge = 6000)
@RestController
@RequestMapping("/memo")
@Api("답변 컨트롤러  API V1")
public class MemoController {

	private static final Logger logger = LoggerFactory.getLogger(MemoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MemoService memoService;

	@ApiOperation(value = "게시판 답변작성", notes = "새로운 게시글 답변을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeMemo(@RequestBody @ApiParam(value = "답변 정보.", required = true) MemoDto memoDto) throws Exception {
		logger.info("writeMemo - 호출");
		if (memoService.writeMemo(memoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 답변보기", notes = "글번호에 해당하는 게시글의 답변을 반환한다.", response = BoardDto.class)
	@GetMapping("/{articelno}")
	public ResponseEntity<MemoDto> getMemo(@PathVariable("articelno") @ApiParam(value = "얻어올 답변의 답변번호.", required = true) int articelno) throws Exception {
		logger.info("getArticle - 호출 : " + articelno);
		return new ResponseEntity<MemoDto>(memoService.getMemo(articelno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 답변삭제", notes = "글번호에 해당하는 게시글의 답변을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteMemo(@PathVariable("articleno") @ApiParam(value = "살제할 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("deleteMemo - 호출");
		if (memoService.deleteMemo(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 글수정", notes = "새로운 게시글 정보를 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyMemo(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) MemoDto memoDto) throws Exception {
		logger.info("modifyMemo - 호출");
		
		if (memoService.modifyMemo(memoDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
}