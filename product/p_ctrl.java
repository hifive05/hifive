package com.product;

import java.util.List;



public class p_ctrl {
	private static final String _SEL="select";
	private static final String _DET="detail";
	private static final String _INS="insert";
	private static final String _UPD="update";
	private static final String _DEL="delete";
	private static final String _SER="search";
	
	public p_VO send(p_VO paVO) throws Exception {
		p_VO raVO = null;
		String command = paVO.getCommand();
		if(_INS.equals(command)) {//입력
			System.out.println("입력 호출 성공");
			//로직클래스를 보면 업무 프로세스를 알 수 있다.
			p_RegisterLogic regLogic = new p_RegisterLogic();
			raVO = regLogic.p_Insert(paVO);
		}else if(_UPD.equals(command)) {//수정
			p_ModifyLogic modLogic = new p_ModifyLogic();
			raVO = modLogic.p_Update(paVO);
		}else if(_DEL.equals(command)) {//삭제
			p_deleteLogic delLogic = new p_deleteLogic();
			raVO = delLogic.p_Delete(paVO);
		}else if(_DET.equals(command)) {//상세조회
			p_RetrieveLogic retLogic = new p_RetrieveLogic();
			raVO = retLogic.p_Insert(paVO);
		}else if(_SER.equals(command)) {//상세조회
			p_searchLogic serLogic = new p_searchLogic();
			raVO = serLogic.p_Search(paVO);
		}else {
			throw new Exception("잘못된 command명 입니다.");
		}
		return raVO;
	}
	public List<p_VO> send(String command){//전체조회
		List<p_VO> addrList = null;
		if(_SEL.equals(command)) {
			p_RetrieveLogic retLogic = new p_RetrieveLogic();
			addrList = retLogic.getproList();
		}
		return addrList;
	}

	public List<p_VO> search(String command, p_VO paVO) {
		List<p_VO> List = null;
		if(_SER.equals(command)) {
			p_searchLogic retLogic = new p_searchLogic();
			List = retLogic.getproList(paVO);
		}
		return List;
	}
}
