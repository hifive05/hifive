package com.product;

import java.util.List;

public class p_searchLogic {
	p_Interface aDao = new p_dao();
	public p_VO p_Search(p_VO paVO) {
		p_VO raVO = null;
		raVO = aDao.proSearch(paVO);
		return raVO;
	}

	public List<p_VO> getproList(p_VO paVO) {
		List<p_VO> list =null;
		list = aDao.getser(paVO);
		return list;
	}
}
