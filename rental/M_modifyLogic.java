package com.rental;

public class M_modifyLogic {
	MemberInterface mDao = new memDao();
	
	public memberVO memDetail (memberVO paVO) {
		memberVO reVO = null;
		reVO = mDao.getmemberDetail(paVO);
		return reVO;
	}
	
	public memberVO memUpdate(memberVO paVO) {
		memberVO reVO = null;
		reVO = mDao.memberUpdate(paVO);
		return reVO;
	}

}
