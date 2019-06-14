package com.rental;

public class M_deleteLogic {
	MemberInterface mDao = new memDao();
	
	public memberVO memDelete(memberVO paVO) {
		memberVO reVO = new memberVO();
		reVO = mDao.memberDelete(paVO);
		return reVO;
	}

}
