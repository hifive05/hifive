package com.rental;

public class memberVO {
	private String MEM_ID     =null;
	private String NAME       =null;
	private String ADDRESS    =null;
	private String PHO_NO     =null;
	private String JOIN_DATE  =null;
	private String ZIPCODE    =null;               
	private String MEM_PW     =null;
	private String command    =null;
	private int    status     = -1;
	private String combobox    =null;
	private String keyword    =null;
	
	
	public String getMEM_ID() {
		return MEM_ID;
	}
	public void setMEM_ID(String mEM_ID) {
		MEM_ID = mEM_ID;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getPHO_NO() {
		return PHO_NO;
	}
	public void setPHO_NO(String pHO_NO) {
		PHO_NO = pHO_NO;
	}
	public String getJOIN_DATE() {
		return JOIN_DATE;
	}
	public void setJOIN_DATE(String jOIN_DATE) {
		JOIN_DATE = jOIN_DATE;
	}
	public String getZIPCODE() {
		return ZIPCODE;
	}
	public void setZIPCODE(String zIPCODE) {
		ZIPCODE = zIPCODE;
	}
	public String getMEM_PW() {
		return MEM_PW;
	}
	public void setMEM_PW(String mEM_PW) {
		MEM_PW = mEM_PW;
	}
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCombobox() {
		return combobox;
	}
	public void setCombobox(String combobox) {
		this.combobox = combobox;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


}
