package com.ssafy.vue.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.util.PageNavigation;
import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.MemoDto;

public interface MemoService {
	public boolean writeMemo(MemoDto memoDto) throws Exception;
	public MemoDto getMemo(int articleno) throws Exception;
	public boolean deleteMemo(int articleno) throws Exception;
	public boolean modifyMemo(MemoDto memoDto) throws Exception;
}
