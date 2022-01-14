package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.MemoDto;
import com.ssafy.vue.model.mapper.BoardMapper;
import com.ssafy.vue.model.mapper.MemoMapper;

@Service
public class MemoServiceImpl implements MemoService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean writeMemo(MemoDto memoDto) throws Exception {
		if(memoDto.getUserid() == null || memoDto.getComment() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(MemoMapper.class).writeMemo(memoDto) == 1;
	}


	@Override
	public MemoDto getMemo(int articleno) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).getMemo(articleno);
	}
	
	@Override
	@Transactional
	public boolean deleteMemo(int articleno) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).deleteMemo(articleno);
	}
	
	@Override
	@Transactional
	public boolean modifyMemo(MemoDto memoDto) throws Exception {
		return sqlSession.getMapper(MemoMapper.class).modifyMemo(memoDto) == 1;
	}
}