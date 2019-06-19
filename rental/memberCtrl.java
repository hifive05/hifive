package com.rental;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class memberCtrl {
	//AddressBook에서 사용자가 선택한 버튼이나 메뉴에 따라 그 값을 AddressBookCtrl에서
	//분기하는 필요한 정보임
	private static final String _SEL = "select";
	private static final String _INS = "insert";
	private static final String _UPD = "update";
	private static final String _DEL = "delete";
	private static final String _DET = "detail";
	private static final String _SEA = "search";
	
	public memberVO send(memberVO paVO) throws Exception {
		memberVO reVO = null;
		String command = paVO.getCommand();
		//////// 입력
		if(_INS.equals(command)) {//입력
			M_registerLogic regLogic = new M_registerLogic();
			reVO = regLogic.memInsert(paVO);
		//////// 수정
		}else if(_UPD.equals(command)) {//수정
			M_modifyLogic modLogic = new M_modifyLogic();
			reVO = modLogic.memUpdate(paVO);
		//////// 상세조회
		}else if(_DET.equals(command)) {//수정
			M_modifyLogic modLogic = new M_modifyLogic();
			reVO = modLogic.memDetail(paVO);			
		/////// 삭제
		}else if(_DEL.equals(command)) {//삭제
			M_deleteLogic delLogic = new M_deleteLogic();
			reVO = delLogic.memDelete(paVO);
		}
		else {
			throw new Exception("잘못된 command명 입니다.");
		}
		return reVO;
	}

	
	/////// 전체조회
	public List<memberVO> send(String command) {
		List<memberVO> mlist = null;
		if(_SEL.equals(command)) {
			M_retrieveLogic retLogic = new M_retrieveLogic();
			mlist = retLogic.getmemberList();
		}
		return mlist;}
		////// 검색
	public List<memberVO> send(String command,memberVO paVO){
		List<memberVO> mlist = null;
		if(_SEA.equals(command)) {//삭제
			M_searchLogic seaLogic = new M_searchLogic();
			mlist = seaLogic.getmemSearch(paVO);
		}
		return mlist;
	}
}
