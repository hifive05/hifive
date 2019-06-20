package com.product;

public class p_RegisterLogic {

		p_Interface aDao = new p_dao();
	public p_VO p_Insert(p_VO paVO) {
		// TODO Auto-generated method stub
		p_VO raVO = new p_VO();
		raVO = aDao.proInsert(paVO);
		return raVO;
	}

}
