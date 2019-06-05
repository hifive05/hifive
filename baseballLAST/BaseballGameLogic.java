package com.baseball;

import java.util.Random;

public class BaseballGameLogic {
	
	
	int com[] = new int[3]; 
	int my[] = new int[3]; 
	int count =0;
	
	//BaseballGameEvent event = new BaseballGameEvent(this);
	BaseballGameEvent event;
	//BaseballGameView view;
	BaseballGameLogic(){
	}
	BaseballGameLogic(BaseballGameEvent event){
		this.event = event;
	}
	//BaseballGameLogic(BaseballGameView view){
	//	this.view = view;
	//}
	
	public void ranCom() {		
		 Random r = new Random();
	     com[0] = r.nextInt(10);
	     do {
	    	 com[1] = r.nextInt(10);
	     }		
		while(com[0]==com[1]);
	     do { 
	    	  com[2] = r.nextInt(10);
	    	 }
	     while((com[0]==com[2]) | (com[1]==com[2]));
	}
	
	
	public String account(String user) { 
		int strike = 0;
		int ball = 0;
		int temp = 0;
		try {
			temp = Integer.parseInt(user);
		} catch (NumberFormatException e) {
			return "숫자만 입력해라~~~~";
		}
		my[0] = temp/100;
	    my[1] = (temp%100)/10;
	    my[2] = temp%10;

	    for (int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				if(com[i]==my[j]) {
					if(i==j)  { 
						 strike++;
						 } ///end of strike
					else {
					ball++;
				}////end of 볼판정
			} if(strike==3) {
				return "정답입니다. 축하합니다!";
			}
			}
			}return "스트라이크 : "+strike+"볼 : "+ball; ////////end of inner for

	}///////////end of outter for	
}
