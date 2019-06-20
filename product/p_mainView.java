package com.product;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class p_mainView extends JFrame{
	p_insertsub insub = null;
	p_sub psub = null;
	static p_mainView pmain = null;
	JMenuBar jmd_pro = new JMenuBar();	
	JMenu jm_move = new JMenu("이동");
	JMenu jbtn_mem = new JMenu("회원");
	JMenu jbtn_rent = new JMenu("대여");
	JMenu jbtn_pro = new JMenu("상품");
	JMenu jbtn_home = new JMenu("HOME");
	
	String searchLabel[]= {"전체","브랜드","상품군","상품코드"};
	JComboBox jcb_search = new JComboBox(searchLabel);
	JTextField jtf_keyword = new JTextField("검색할 키워드를 입력하세요",14);
	JButton jbtn_searchbutton = new JButton("검색");
	
	JPanel jp_north= new JPanel();//GriLayout써서 두개 영역으로 쪼갬
	JPanel jp_north_second= new JPanel();//검색기 추가
	JPanel jp_north_first= new JPanel();//기존버튼 4개 이관
	JButton jbtn_ins = new JButton("입력");
	JButton jbtn_upd = new JButton("수정");
	JButton jbtn_del = new JButton("삭제");
	JButton jbtn_det = new JButton("상세조회");
	JButton jbtn_all = new JButton("전체조회");
	JFrame JF_pro = new JFrame();
	
	String cols[]= {"상품가격","상품수량","브랜드명","상품코드","상품군","모델번호"};
	String data[][]=new String[0][6];
	DefaultTableModel dtm_pro=new DefaultTableModel(data,cols);
	JTable jt_pro = new JTable(dtm_pro);
	JScrollPane jsp_pro = new JScrollPane(jt_pro);
	JTableHeader jth_pro= jt_pro.getTableHeader();
	public p_mainView() {
		
	}

	public void initDisplay() {
		psub = new p_sub();
		jtf_keyword.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==10) {
					enterActionPerformed(e);
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		jtf_keyword.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				clickActionPerformed(e);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		jbtn_searchbutton.addActionListener(new ActionListener() {//구현체 클래스가 필요하면 그 안에서 구현해줄 수 있다.
		                                        	@Override
		                                        	public void actionPerformed(ActionEvent e) {
		                                        		searchActionPerformed(e);
		                                        	}			
		                                        });
		jbtn_ins.addActionListener(new ActionListener() {//구현체 클래스가 필요하면 그 안에서 구현해줄 수 있다.
		                                        	@Override
		                                        	public void actionPerformed(ActionEvent e) {
		                                        		insertActionPerformed(e);
		                                        	}			
		                                        });
		jbtn_upd.addActionListener(new ActionListener() {//구현체 클래스가 필요하면 그 안에서 구현해줄 수 있다.
		                                        	@Override
		                                        	public void actionPerformed(ActionEvent e) {
		                                        		updataActionPerformed(e);
		                                        	}			
		                                        });
		jbtn_del.addActionListener(new ActionListener() {//구현체 클래스가 필요하면 그 안에서 구현해줄 수 있다.
		                                        	@Override
		                                        	public void actionPerformed(ActionEvent e) {
		                                        		deleteActionPerformed(e);
		                                        	}
		                                        });
		jbtn_det.addActionListener(new ActionListener() {//구현체 클래스가 필요하면 그 안에서 구현해줄 수 있다.
		                                        	@Override
		                                        	public void actionPerformed(ActionEvent e) {
		                                        		detailActionPerformed(e);
		                                        	}
		                                        });
		jbtn_all.addActionListener(new ActionListener() {//구현체 클래스가 필요하면 그 안에서 구현해줄 수 있다.
			@Override
			public void actionPerformed(ActionEvent e) {
				allActionPerformed(e);
			}
		});
		jp_north.setLayout(new GridLayout(2,1));//변수선언할때 대문자라면 상수
		jp_north_second.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north_second.add(jcb_search);
		jp_north_second.add(jtf_keyword);
		jp_north_second.add(jbtn_searchbutton);
		jp_north_first.setLayout(new FlowLayout(FlowLayout.LEFT));
		jp_north_first.add(jbtn_all);
		jp_north_first.add(jbtn_ins);
		jp_north_first.add(jbtn_upd);
		jp_north_first.add(jbtn_all);
		jp_north_first.add(jbtn_del);
		jp_north_first.add(jbtn_det);
		
	
		
		JF_pro.setTitle("상품관리 시스템");
		jmd_pro.add(jm_move);
		jmd_pro.add(jbtn_home);
		jmd_pro.add(jbtn_mem);
		jmd_pro.add(jbtn_rent);
		jmd_pro.add(jbtn_pro);		
		JF_pro.setJMenuBar(jmd_pro);
		jp_north.add(jp_north_second);
		jp_north.add(jp_north_first);
		JF_pro.add("North",jp_north);
		JF_pro.add("Center",jsp_pro);
		JF_pro.setSize(700, 550);
		JF_pro.setVisible(true);
		jth_pro.setFont(new Font("맑은고딕",Font.BOLD,12));
		jth_pro.setBackground(new Color(22,22,22));
		jth_pro.setForeground(Color.white);
		jth_pro.setReorderingAllowed(false);//컬럼들 이동금지시키기
		jth_pro.setResizingAllowed(false);//크기변경도 막아버림
		//싱글선택만 가능하도록 하기
		jt_pro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jt_pro.setGridColor(Color.blue);
		jt_pro.getColumnModel().getColumn(0).setPreferredWidth(80);
		jt_pro.getColumnModel().getColumn(1).setPreferredWidth(100);
		jt_pro.getColumnModel().getColumn(2).setPreferredWidth(100);
		jt_pro.getColumnModel().getColumn(3).setPreferredWidth(100);
		jt_pro.getColumnModel().getColumn(4).setPreferredWidth(100);
		jt_pro.getColumnModel().getColumn(5).setPreferredWidth(100);
		jt_pro.repaint();//다시쓰기
		refreshData();
	}

//////////////////////////////////////////////////클릭시 검색창 텍스트 없애기///////////////////////////////////
	protected void clickActionPerformed(MouseEvent e) {
		jtf_keyword.setText(null);
	}
/////////////////////////////////////////////////검색어 입력후 엔터시 검색하기//////////////////////////
	protected void enterActionPerformed(KeyEvent e) {
		String search =	(String)jcb_search.getSelectedItem();
		String keyword = jtf_keyword.getText();
			if("검색할 키워드를 입력하세요".equals(jtf_keyword.getText())) {
				JOptionPane.showMessageDialog(this, "새로 입력하세요.");
				return;
			}else {
				try {
					p_VO paVO = new p_VO();
					paVO.setSearch(search);
					paVO.setKeyword(keyword);
					p_ctrl pctrl = new p_ctrl();
					List<p_VO> list = pctrl.search("search",paVO);
					while(dtm_pro.getRowCount()>0) {
						dtm_pro.removeRow(0);
					}
					for(int i=0;i<list.size();i++) {
						p_VO raVO=list.get(i);
						Vector rowData = new Vector();
						rowData.add(0,raVO.getPro_cost());
						rowData.add(1,raVO.getPro_quan());
						rowData.add(2,raVO.getBrand());
						rowData.add(3,raVO.getPro_code());
						rowData.add(4,raVO.getCategory());
						rowData.add(5,raVO.getModel_no());
						dtm_pro.addRow(rowData);
					}
				} catch (Exception e1) {
					System.out.println(e1.toString());
					}
			}
	}
////////////////////////////////////////////검색라벨 인식후 검색//////////////////////////////////
	protected void searchActionPerformed(ActionEvent e) {
		String laber = e.getActionCommand();
		String search =	(String)jcb_search.getSelectedItem();
		String keyword = jtf_keyword.getText();
		if("검색".equals(laber)) {if("검색할 키워드를 입력하세요".equals(jtf_keyword.getText())) {
			JOptionPane.showMessageDialog(this, "새로 입력하세요.");
			return;
		}else {
			p_VO paVO = new p_VO();
			paVO.setSearch(search);
			paVO.setKeyword(keyword);
			p_ctrl pctrl = new p_ctrl();
			List<p_VO> list = pctrl.search("search",paVO);
			while(dtm_pro.getRowCount()>0) {
				dtm_pro.removeRow(0);
			}
			for(int i=0;i<list.size();i++) {
				p_VO raVO=list.get(i);
				//Vector을 생성한 이유는 DB에서 꺼낸 값을 행 단위로 dtm_address에
				//추가 할수 있는 addRow(Vector|Object[])라는 메소드에 파라미터로 넣기 위함이다
				Vector rowData = new Vector();
				rowData.add(0,raVO.getPro_cost());
				rowData.add(1,raVO.getPro_quan());
				rowData.add(2,raVO.getBrand());
				rowData.add(3,raVO.getPro_code());
				rowData.add(4,raVO.getCategory());
				rowData.add(5,raVO.getModel_no());
				dtm_pro.addRow(rowData);
			}
			}
	}
	}
	//////////////////////////////입력창 액션 서브 insertsub로 넘어감////////////////////
	protected void insertActionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		insub = null;
		insub = new p_insertsub();
		insub.set(null,label,pmain,true);
	}
	///////////////////////////삭제하기////////////////////////////
	protected void deleteActionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		int index = jt_pro.getSelectedRow();
		if(index<0) {
			JOptionPane.showMessageDialog(this, "삭제할 데이터를 선택하세요");
			return;
		}else {try {
			//paVO에 담을 정보는 command=delete and id=u_id
			p_VO paVO = new p_VO();
			p_VO raVO = null;
			//String u_id = (String)dtm_address.getValueAt(index, 0);
			String u_code = null;
			for(int i=0;i<dtm_pro.getRowCount();i++) {
				if(jt_pro.isRowSelected(i)) {
					u_code=(String)dtm_pro.getValueAt(i, 0);
					p_ctrl aCtrl = new p_ctrl();
					paVO.setCommand("delete");
					paVO.setPro_code(u_code);
					raVO = aCtrl.send(paVO);
					System.out.println("삭제");
				} 
			}
			if(raVO.getStatus()==1) {//삭제 성공
				refreshData();
			}else {
				JOptionPane.showMessageDialog(this,"삭제 실패");
				return;				
			}
		} catch (Exception e2) {
			System.out.println(e2.toString());
			System.out.println(e2.getMessage());
			e2.printStackTrace();
		}//////////end of try - catch
			
		}//////////end of if
	}//////////////end of deleteAction
	
////////////////////////////수정하기/////////////////////////////
	protected void updataActionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		//이벤트를 어디에 걸지? JTable(폼,이벤트) DefaultTableModel(값을 저장)
		int index = jt_pro.getSelectedRow();
		//로그를 출력할때 주의사항
		//main를 가진 클래스는 sysout
		//main가 없는 클래스는 	JOptionPane.showMessageDialog(this, "데이터가 없습니다");
		//System.out.println("index:"+index);
		if(index<0) {
			JOptionPane.showMessageDialog(this, "조회할 데이터를 한 건만 선택하세요.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			try {
				jt_pro.clearSelection();
				p_VO paVO=new p_VO();
				String u_code = (String)dtm_pro.getValueAt(index, 3);
				paVO.setPro_code(u_code);
				paVO.setCommand("detail");
				p_ctrl aCtrl = new p_ctrl();
				p_VO raVO = aCtrl.send(paVO);
				psub = null;
				psub = new p_sub();
				psub.set(raVO,label,pmain,true);	
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

	}
	/////////////////////////상세정보//////////////////////////////
	protected void detailActionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		//이벤트를 어디에 걸지? JTable(폼,이벤트) DefaultTableModel(값을 저장)
		int index = jt_pro.getSelectedRow();
		//로그를 출력할때 주의사항
		//main를 가진 클래스는 sysout
		//main가 없는 클래스는 	JOptionPane.showMessageDialog(this, "데이터가 없습니다");
		//System.out.println("index:"+index);
		if(index<0) {
			JOptionPane.showMessageDialog(this, "조회할 데이터를 한 건만 선택하세요.","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			try {
				jt_pro.clearSelection();
				p_VO paVO=new p_VO();
				String u_code = (String)dtm_pro.getValueAt(index, 3);
				paVO.setPro_code(u_code);
				paVO.setCommand("detail");
				p_ctrl aCtrl = new p_ctrl();
				p_VO raVO = aCtrl.send(paVO);
				psub = null;
				psub = new p_sub();
				psub.set(raVO,label,pmain,false);	
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		//선택한 후에 상세조회 화면이 열리면 기존에 선택한 로우는 clear처리
		
		
	}
	///////////////////////////기본 화면 전체 조회////////////////////////////
	protected void allActionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		refreshData();
	}

	///////////////////////////전체조회 알고리즘///////////////////////////
	public void refreshData() {
		//이미 테이블에 있던 데이터는 삭제한다.
		while(dtm_pro.getRowCount()>0) {
			dtm_pro.removeRow(0);
		}		
		p_ctrl aCtrl = new p_ctrl();
		List<p_VO> list = aCtrl.send("select");
		if((list==null)||(list.size()==0)) {
			JOptionPane.showMessageDialog(this, "데이터가 없습니다");
		}
		else {
			for(int i=0;i<list.size();i++) {
				p_VO raVO=list.get(i);
				//Vector을 생성한 이유는 DB에서 꺼낸 값을 행 단위로 dtm_address에
				//추가 할수 있는 addRow(Vector|Object[])라는 메소드에 파라미터로 넣기 위함이다
				Vector rowData = new Vector();
				rowData.add(0,raVO.getPro_cost());
				rowData.add(1,raVO.getPro_quan());
				rowData.add(2,raVO.getBrand());
				rowData.add(3,raVO.getPro_code());
				rowData.add(4,raVO.getCategory());
				rowData.add(5,raVO.getModel_no());
				dtm_pro.addRow(rowData);
			}
		}
	}
	//
	public static void main(String[] args) {
		if(pmain==null) {
			pmain = new p_mainView();
		}
		pmain.initDisplay();
	}

	
}
