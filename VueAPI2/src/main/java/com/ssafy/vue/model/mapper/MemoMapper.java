package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.BoardParameterDto;
import com.ssafy.vue.model.MemoDto;

@Mapper
public interface MemoMapper {
	
	public int writeMemo(MemoDto memoDto) throws SQLException;
	public MemoDto getMemo(int articleno) throws SQLException;
	public boolean deleteMemo(int articleno) throws SQLException;
	public int modifyMemo(MemoDto memoDto) throws SQLException;
	
}