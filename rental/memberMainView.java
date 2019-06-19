package com.rental;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

public class memberMainView extends JFrame{
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
	
	JMenu jm_home = new JMenu("HOME");
	JMenu jm_mem = new JMenu("회원");
	JMenu jm_rent = new JMenu("대여");
	JMenu jm_pro = new JMenu("상품");
	
	
	/*
	 *  JMenuItem jmi_mins = new JMenuItem("회원등록");
	 * JMenuItem jmi_mupd = new JMenuItem("회원수정"); JMenuItem jmi_mdel = new
	 * JMenuItem("회원삭제");
	 * 
	 *  JMenuItem jmi_rins = new JMenuItem("대여등록");
	 * JMenuItem jmi_rupd = new JMenuItem("대여수정"); JMenuItem jmi_rdel = new
	 * JMenuItem("대여삭제");
	 * 
	 *  JMenuItem jmi_pdam = new JMenuItem("파손");
	 * JMenuItem jmi_pas = new JMenuItem("A/S"); JMenuItem jmi_pcha = new
	 * JMenuItem("변경"); JMenuItem jmi_pplus = new JMenuItem("추가"); JMenuItem
	 * jmi_pdel = new JMenuItem("삭제");
	 * 
	 * JMenu jm_sales = new JMenu("매출"); JMenuItem jmi_sd = new JMenuItem("일별매출");
	 * JMenuItem jmi_sm = new JMenuItem("월별매출"); JMenuItem jmi_sy = new
	 * JMenuItem("년별매출");
	 */
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
	//JButton jbtn_mem = new JButton("회원");
	//JButton jbtn_rent = new JButton("대여");
	//JButton jbtn_pro = new JButton("상품");
	//JButton jbtn_home = new JButton("HOME");
	String cols[] = {"회원명","회원ID","주소","전화번호","가입일자","비밀번호"};
	//////////////////////////////버튼추가끝///////////////////////////////////////////
//////////////////////검색기 추가 시작//////////////////////////////////////////////
String searchLabel[] = {"전체","회원명","회원ID","주소","전화번호","가입일자","비밀번호"};
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
		jtf_keyword.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==10) {
				enterActionPerformed(e);
			}}
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
		});
		jtf_keyword.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clickActionPerformed(arg0);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
			}
			@Override
			public void mousePressed(MouseEvent arg0) {
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
		});
		jbtn_ins.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertActionPerformed(e);}	});
		
		jbtn_upd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateActionPerformed(e);}	});
		
		jbtn_del.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);}	});
		jbtn_all.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				allActionPerformed(e);}		});
		
		jbtn_sel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchActionPerformed(e);}	});
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
		jp_north_first.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		jp_north_first.add(jcb_search);
		jp_north_first.add(jtf_keyword);
		jp_north_first.add(jbtn_sel);
		
		jp_north_second.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		jp_north_second.add(jbtn_ins);
		jp_north_second.add(jbtn_upd);
		jp_north_second.add(jbtn_del);
		jp_north_second.add(jbtn_all);
		
//////////////////////////////////////////메뉴바시작////////////////////////////////////////		
		jm_file.add(jmi_fsave);
		jm_file.add(jmi_fserch);
		jm_file.add(jmi_fprint);
		jm_file.add(jmi_fback);
		jm_file.add(jmi_fexit);
		jmb_mem.add(jm_file);
		
		jmb_mem.add(jm_home);
		jmb_mem.add(jm_mem);
		jmb_mem.add(jm_rent);
		jmb_mem.add(jm_pro);
		this.setJMenuBar(jmb_mem);
//////////////////////////////////////////메뉴바끝////////////////////////////////////////
		jp_north.add(jp_north_first);
		jp_north.add(jp_north_second);
		this.add("North",jp_north);
		this.add("Center",jsp_mem);
		this.setSize(850, 500);
		this.setVisible(true);
		refreshData();
	}

	protected void clickActionPerformed(MouseEvent arg0) {
		 	jtf_keyword.setText(null);
}
	/////////////////////////////////[[initDispaly 끝]]/////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////	
	protected void enterActionPerformed(KeyEvent e) {
		String keyword = jtf_keyword.getText();
		String comboboxlabel = (String)jcb_search.getSelectedItem();
			if("검색할 키워드를 입력하세요.".equals(jtf_keyword.getText())) {
				JOptionPane.showMessageDialog(this, "새로 입력하세요.");
				return;
			}else {
				try {
					memberVO paVO = new memberVO();
					paVO.setCombobox(comboboxlabel);
					paVO.setKeyword(keyword);
					memberCtrl mCtrl = new memberCtrl();
					List<memberVO> mlist = mCtrl.send("search",paVO);
					while(dtm_mem.getRowCount()>0) {
						dtm_mem.removeRow(0);
					}
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
				} catch (Exception e2) {
					System.out.println(e2.toString());
				}
			}
			
		}		
	
	/////////////검색
	protected void searchActionPerformed(ActionEvent e) {
		String label = e.getActionCommand(); 
		String keyword = jtf_keyword.getText();
		String comboboxlabel = (String)jcb_search.getSelectedItem();
		if("검색".equals(label)) {
			if("검색할 키워드를 입력하세요.".equals(jtf_keyword.getText())) {
				JOptionPane.showMessageDialog(this, "새로 입력하세요.");
				return;
			}else {
				try {
					memberVO paVO = new memberVO();
					paVO.setCombobox(comboboxlabel);
					paVO.setKeyword(keyword);
					memberCtrl mCtrl = new memberCtrl();
					List<memberVO> mlist = mCtrl.send("search",paVO);
					while(dtm_mem.getRowCount()>0) {
						dtm_mem.removeRow(0);
					}
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
				} catch (Exception e2) {
					System.out.println(e2.toString());
				}
			}
		}
	}
	//////////////입력
	protected void insertActionPerformed(ActionEvent e) {
		String label = e.getActionCommand(); 
		sView = null;
		sView = new memberSubView();
		sView.set(null,label,mView,true);
	}
	/////////////수정
	protected void updateActionPerformed(ActionEvent e) {
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
	//////////삭제
	protected void deleteActionPerformed(ActionEvent e) {
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
	////////전체조회
	protected void allActionPerformed(ActionEvent e) {
		refreshData();
}
/////////////////////////////////////////////////////////////////////////////////////////////	
	public static void main(String[] args) {
		if(mView==null) {
			mView = new memberMainView();
		}
		mView.initDisplay();
	}
}
