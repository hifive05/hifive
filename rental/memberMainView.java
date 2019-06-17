package com.rental;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.address.AddressBook;
import com.address.SubBook;
import com.rent.RentOracleServer;

public class memberMainView extends JFrame implements ActionListener, MouseListener{
//선언부
	memberSubView sView = new memberSubView();
	static memberMainView mView = null;
//////////////////////////////////////////메뉴바시작////////////////////////////////////////
	JMenuBar jmb_mem = new JMenuBar();
	JMenu jm_file= new JMenu("파일");
	JMenuItem jmi_fsave = new JMenuItem("저장");
	JMenuItem jmi_fserch = new JMenuItem("검색");
	JMenuItem jmi_fprint = new JMenuItem("인쇄");
	JMenuItem jmi_fback = new JMenuItem("백업");
	JMenuItem jmi_fexit = new JMenuItem("나가기");
	
	JMenu jm_mem = new JMenu("회원");
	JMenuItem jmi_mins = new JMenuItem("회원등록");
	JMenuItem jmi_mupd = new JMenuItem("회원수정");
	JMenuItem jmi_mdel = new JMenuItem("회원삭제");
	
	JMenu jm_rent = new JMenu("대여");
	JMenuItem jmi_rins = new JMenuItem("대여등록");
	JMenuItem jmi_rupd = new JMenuItem("대여수정");
	JMenuItem jmi_rdel = new JMenuItem("대여삭제");
	
	JMenu jm_pro = new JMenu("상품");
	JMenuItem jmi_pdam = new JMenuItem("파손");
	JMenuItem jmi_pas = new JMenuItem("A/S");
	JMenuItem jmi_pcha = new JMenuItem("변경");
	JMenuItem jmi_pplus = new JMenuItem("추가");
	JMenuItem jmi_pdel = new JMenuItem("삭제");
	
	JMenu jm_sales = new JMenu("매출");
	JMenuItem jmi_sd = new JMenuItem("일별매출");
	JMenuItem jmi_sm = new JMenuItem("월별매출");
	JMenuItem jmi_sy = new JMenuItem("년별매출");
//////////////////////////////////////////메뉴바끝////////////////////////////////////////
/////////////////////////////버튼추가/////////////////////////////////////////
	JPanel jp_north = new JPanel();
	JPanel jp_north_second = new JPanel();
	JPanel jp_north_first = new JPanel();
	JButton jbtn_sel = new JButton("검색");
	JButton jbtn_ins = new JButton("입력");
	JButton jbtn_upd = new JButton("수정");
	JButton jbtn_del = new JButton("삭제");
	JButton jbtn_all = new JButton("전체조회");
	JButton jbtn_mem = new JButton("회원");
	JButton jbtn_rent = new JButton("대여");
	JButton jbtn_pro = new JButton("상품");
	JButton jbtn_home = new JButton("HOME");
	String cols[] = {"회원명","회원ID","주소","전화번호","가입일자","비밀번호"};
	//////////////////////////////버튼추가끝///////////////////////////////////////////
//////////////////////검색기 추가 시작//////////////////////////////////////////////
String searchLabel[] = {"회원명","회원ID","주소","전화번호","가입일자","비밀번호"};
JComboBox jcb_search = new JComboBox(searchLabel);
JTextField jtf_keyword = new JTextField("검색할 키워드를 입력하세요.",50);	
//////////////////////검색기 추가 끝//////////////////////////////////////////////
	String data[][]=new String[0][7];
	DefaultTableModel dtm_mem = new DefaultTableModel(data,cols);
	JTable jt_mem = new JTable(dtm_mem);
	///////////////////////////////////////////////////////////////////////////
	JScrollPane jsp_mem = new JScrollPane(jt_mem,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
            ,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	public memberMainView() {}
	//RENT 목록 조회 구현

	//새로고침 처리 메소드 구현
	public void refreshData() {
		while(dtm_mem.getRowCount()>0) {
			dtm_mem.removeRow(0);
		}
		memberCtrl mCtrl = new memberCtrl();
		List<memberVO> mlist = mCtrl.send("select");
		if((mlist==null)||(mlist.size()==0)) {
			JOptionPane.showMessageDialog(this,	"데이터가 존재하지 않습니다.");
		}else {
			for(int i=0;i<mlist.size();i++) {
				memberVO reVO = mlist.get(i);
				Vector rowData = new Vector();
				rowData.add(0, reVO.getNAME());
				rowData.add(1, reVO.getMEM_ID());
				rowData.add(2, reVO.getADDRESS());
				rowData.add(3, reVO.getPHO_NO());
				rowData.add(4, reVO.getJOIN_DATE());
				rowData.add(5, reVO.getMEM_PW());
				dtm_mem.addRow(rowData);
			}
		}
		
	}
//화면처리구현
	public void initDisplay() {
		jtf_keyword.addMouseListener(this);
		jbtn_ins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertActionPerforemd(e);}	});
		
		jbtn_upd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateActionPerforemd(e);}	});
		
		jbtn_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteActionPerforemd(e);}	});
		jbtn_all.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				allActionPerforemd(e);}		});
		
		jbtn_sel.addActionListener(this);
		//테이블 헤더 변경 금지하기
		jt_mem.getTableHeader().setReorderingAllowed(false);
		//테이블 컬럼 폭지정하기
		jt_mem.getColumnModel().getColumn(0).setPreferredWidth(100);
		jt_mem.getColumnModel().getColumn(1).setPreferredWidth(100);
		jt_mem.getColumnModel().getColumn(2).setPreferredWidth(310);
		jt_mem.getColumnModel().getColumn(3).setPreferredWidth(100);
		jt_mem.getColumnModel().getColumn(4).setPreferredWidth(120);
		jt_mem.getColumnModel().getColumn(5).setPreferredWidth(120);
		//테이블 헤더 배경색 변경
		jt_mem.getTableHeader().setBackground(new Color(120,120,120));
		//테이블 헤더 글자색 변경
		jt_mem.getTableHeader().setForeground(Color.white);
		this.setTitle("회원관리");
		
		jp_north.setLayout(new GridLayout(2,1));
		jp_north_second.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		jp_north_second.add(jcb_search);
		jp_north_second.add(jtf_keyword);
		jp_north_second.add(jbtn_sel);
		jp_north_first.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		jp_north_first.add(jbtn_ins);
		jp_north_first.add(jbtn_upd);
		jp_north_first.add(jbtn_del);
		jp_north_first.add(jbtn_all);
		
//////////////////////////////////////////메뉴바시작////////////////////////////////////////		
		jm_file.add(jmi_fsave);
		jm_file.add(jmi_fserch);
		jm_file.add(jmi_fprint);
		jm_file.add(jmi_fback);
		jm_file.add(jmi_fexit);
		jmb_mem.add(jm_file);
		
		jmb_mem.add(jbtn_home);
		jmb_mem.add(jbtn_mem);
		jmb_mem.add(jbtn_rent);
		jmb_mem.add(jbtn_pro);
		this.setJMenuBar(jmb_mem);
//////////////////////////////////////////메뉴바끝////////////////////////////////////////
		
		jp_north.add(jp_north_second);
		jp_north.add(jp_north_first);
		this.add("North",jp_north);
		this.add("Center",jsp_mem);
		//this.add("center",jsp_mem);
		this.setSize(850, 500);
		this.setVisible(true);
		refreshData();
	}

/////////////////////////////////[[initDispaly]]/////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////	
	//입력
	protected void insertActionPerforemd(ActionEvent e) {
		String label = e.getActionCommand(); 
		sView = null;
		sView = new memberSubView();
		sView.set(null,label,mView,true);
	}
	//수정
	protected void updateActionPerforemd(ActionEvent e) {
		String label = e.getActionCommand(); 
		int index = jt_mem.getSelectedRow();
		if(index<0) {
			JOptionPane.showMessageDialog(this, "데이터를 선택하세요","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			try {
				jt_mem.clearSelection();
				memberVO paVO = new memberVO();
				String u_id = (String)dtm_mem.getValueAt(index , 1 );
				paVO.setMEM_ID(u_id);
				paVO.setCommand("detail");
				memberCtrl mCtrl = new memberCtrl();
				memberVO reVO = mCtrl.send(paVO);
				sView = null;
				sView = new memberSubView();
				sView.set(reVO,label,mView,true);
				
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	//삭제
	protected void deleteActionPerforemd(ActionEvent e) {
		String label = e.getActionCommand(); 
		int index = jt_mem.getSelectedRow();
		if(index<0) {
			JOptionPane.showMessageDialog(this, "삭제할 데이터를 선택하세요","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			try {
				memberVO paVO = new memberVO();
				memberVO reVO = null;
				String u_id = null;//(String)dtm_mem.getValueAt(index, 1);
				for(int i=0;i<dtm_mem.getRowCount();i++) {
					if(jt_mem.isRowSelected(i)) {
						u_id = (String)dtm_mem.getValueAt(i, 1);
						memberCtrl mCtrl = new memberCtrl();
						paVO.setCommand("delete");
						paVO.setMEM_ID(u_id);
						reVO = mCtrl.send(paVO);
					}
				}
				if(reVO.getStatus()==1) {
					refreshData();
				}else {
					JOptionPane.showMessageDialog(this, "삭제 실패","Error",JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	//전체조회
	protected void allActionPerforemd(ActionEvent e) {
		refreshData();
}
/////////////////////////////////////////////////////////////////////////////////////////////	
	public static void main(String[] args) {
		if(mView==null) {
			mView = new memberMainView();
		}
		mView.initDisplay();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String keyword = jtf_keyword.getText();
		if(e.getSource()==jbtn_sel) {
			if("검색할 키워드를 입력하세요.".equals(jtf_keyword.getText())) {
				JOptionPane.showMessageDialog(this, "새로 입력하세요.");
				return;
			}
		//	getMemList(keyword);
		}
		else if(e.getSource()==jtf_keyword) {
		//	getMemList(keyword);
		}
	}
		
	@Override
	public void mouseClicked(MouseEvent e) {
		jtf_keyword.setText(null);
		}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
