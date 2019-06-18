package com.product;

import java.util.List;

public interface p_Interface {
	//상세조회 처리를 위한 추상메소드 선언
		public p_VO getproDetail(p_VO paVO);
		//입력처리를 위한 추상메소드 선언
		public p_VO proInsert(p_VO paVO);
		//수정처리를 위한 추상 메소드 선언
		public p_VO proUpdata(p_VO paVO);
		//삭제처리를 위한 추상 메소드 선언
		public p_VO proDelete(p_VO paVO);
		//전체조회 처리를 위한 추상메소드 선언
		public List<p_VO> getpro();
}

