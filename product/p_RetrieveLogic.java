package com.product;

import java.util.List;


public class p_RetrieveLogic {
	p_Interface aDao = new p_dao();
	
	public p_VO p_Insert(p_VO paVO) {
		p_VO raVO = null;
		raVO = aDao.getproDetail(paVO);
		return raVO;
	}
	
	public List<p_VO> getproList() {
		List<p_VO> list =null;
		list = aDao.getpro();
		return list;
	}

}
