package com.product;

import java.util.List;

public class p_ModifyLogic {
	
	p_Interface aDao = new p_dao();
	public p_VO p_Update(p_VO paVO) {
		p_VO raVO = null;
		raVO = aDao.proUpdata(paVO);
		return raVO;
	}
	public List<p_VO> getproList() {
		List<p_VO> list =null;
		list = aDao.getpro();
		return list;
	}

}
