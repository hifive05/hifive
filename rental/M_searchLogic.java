package com.rental;

import java.util.List;

public class M_searchLogic {
	MemberInterface mDao = new memDao();
	public List<memberVO> getmemSearch(memberVO paVO) {
		List<memberVO> mlist = null;
		mlist = mDao.getmemberSearch(paVO);
		return mlist;
	}

}
