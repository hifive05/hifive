package com.product;

public class p_deleteLogic {
	
	p_Interface aDao = new p_dao();
	public p_VO p_Delete(p_VO paVO) {
		p_VO raVO = null;
		raVO = aDao.proDelete(paVO);
		return raVO;
	}

}
