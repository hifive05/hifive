package com.baseball;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class BaseballGameEvent implements ActionListener {

	BaseballGameLogic log = new BaseballGameLogic(this);
	BaseballGameEvent(){
	}
	BaseballGameEvent(BaseballGameLogic logic){
		this.log = logic;
	}
	BaseballGameView view;
	BaseballGameEvent(BaseballGameView view){
		this.view = view;
	}
	//BaseballGameView bbgv = new BaseballGameView();
	
	public void actionPerformed(ActionEvent e) {
	Object obj = e.getSource();//이벤트 소스(jtf_user)에 대한 주소번지를 읽기
	System.out.println("obj:"+obj);
	//너 정답을 보고싶은거야??
	if(obj==view.jbtn_dap) {
		
		view.jta_display.append("정답은"+log.com[0]+""+log.com[1]+""+log.com[2]+" 입니다.\n");
	}
	//너엔터친거니?
	else if(obj==view.jtf_user) {
		//System.out.println("엔터엔터");
	 	String userInput = view.jtf_user.getText();
	 	if(userInput.length()!=3) {
	 	JOptionPane.showMessageDialog(view, "3자리 숫자를 입력하세요"
	 								, "ERROR", JOptionPane.ERROR_MESSAGE);
	 	view.jtf_user.setText("");
	 	return;//actionPerformed 탈출
	 	}
	 	
	 	view.jta_display.append(++log.count+" . "+userInput+"  :  "+log.account(userInput)+"\n");
	 	if (log.count>9) {
	 		view.jta_display.setText("");	
		JOptionPane.showMessageDialog(view, "게임종료"
					, "END", JOptionPane.ERROR_MESSAGE);
			log.ranCom();
			log.count=0;
		}
	
	 	view.jtf_user.setText("");//엔터치면지워줌
	}
	//새게임할거니?
	else if(obj==view.jbtn_new) {
		view.jta_display.append("정답은"+log.com[0]+""+log.com[1]+""+log.com[2]+" 입니다.\n");
		log.count = 0;
		view.jta_display.setText("");
		log.ranCom();
		//jta_display.append(com[0]+""+com[1]+""+com[2]+"\n");
	}

	//너 지우기 버튼 누른거야?	ㄴ
	else if(obj==view.jbtn_clear) {
		view.jta_display.setText("");
	}
	else if(obj==view.jbtn_exit) {
		System.exit(0);
	}
	
}


}
