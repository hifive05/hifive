package com.rental;

import java.util.List;

public interface MemberInterface {
	public memberVO getmemberDetail(memberVO paVO);//상세조회
	public memberVO memberInsert(memberVO paVO);//입력
	public memberVO memberUpdate(memberVO paVO);//수정
	public memberVO memberDelete(memberVO paVO);//삭제
	public List<memberVO> getmemberAll();//전체조회
	public List<memberVO> getmemberSearch(memberVO paVO);//검색

}
