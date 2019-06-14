package com.rental;

public class M_registerLogic {
	
	MemberInterface mDao = new memDao();
	
	public memberVO memInsert(memberVO paVO) {
		memberVO reVO = new memberVO();
		reVO = mDao.memberInsert(paVO);
		return reVO;
	}

}
