package com.rental;

import java.util.List;

public class M_retrieveLogic {
	MemberInterface mDao = new memDao();
	memberVO paVO = new memberVO();
	public memberVO memDetail(memberVO paVO) {
		memberVO reVO = null;
		reVO = mDao.getmemberDetail(paVO);
		return reVO;
	}
	public List<memberVO> getmemberList(){
		List<memberVO> mlist = null;
		mlist = mDao.getmemberAll();
		return mlist;
	}
	
	
}
