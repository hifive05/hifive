package com.rental;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.address.AddressBook;

public class memberSubView extends JDialog implements ActionListener {
	//////////////////////// 입력화면추가하기시작///////////////////////////////////

	private JLabel labelName;// 이름라벨
	private JTextField txtName;// 이름을 입력하는 컴포넌트
	private JLabel labelId;
	private JTextField txtId;
	private JLabel labelAddr;
	private JTextField txtAddr;
	private JLabel labelHp;
	private JTextField txtHp;
	private JLabel labelJoin;
	private JTextField txtJoin;
	private JLabel labelPw;
	private JTextField txtPw;
	private JScrollPane scrollPane;
	private JScrollPane scrollComment;
	//////////////////////// 입력화면추가하기끝////////////////////////////////////
	Font font = new Font("굴림", Font.PLAIN, 16);
	JPanel jp_center = new JPanel();// 입력에 필요한 화면을 배치(좌표값 배치)
	JPanel jp_south = new JPanel();
	JButton jbtn_save = new JButton("저장");
	JButton jbtn_cancel = new JButton("취소");
	JScrollPane jsp_line = new JScrollPane(jp_center);
	
	
	String title = null;
	memberMainView mView = null;
	memberVO mVO = new memberVO();
	public void set(memberVO mVO, String title, memberMainView mView, boolean isEdit) {
		this.mVO = mVO;
		this.title = title;
		this.mView = mView;
		this.setValue();
		this.setTitle(title);
		this.setVisible(true);
		this.setEditable(isEdit);
		
	}

	public memberSubView() {
		initDisplay();
	}

	public void initDisplay() {
		// jp_center속지에 레이아웃을 초기화하자.
		jp_center.setLayout(null);// FlowLayout
		// jp_center.setBackground(new Color()));
//////////////////////////////화면 객체 생성하기 시작//////////////////////////////

		labelName = new JLabel("이름(필수입력) ");
		labelId = new JLabel("ID");
		labelAddr = new JLabel("주소 ");
		labelHp = new JLabel("전화번호 ");
		labelJoin = new JLabel("가입일자(YYMMDD) ");
		labelPw = new JLabel("비밀번호 ");

		labelId.setFont(font);
		labelName.setFont(font);
		labelAddr.setFont(font);
		labelHp.setFont(font);
		labelJoin.setFont(font);
		labelPw.setFont(font);

		// 데이터를 보여줄 텍스트 필드등을 정의합니다.
		txtId = new JTextField(20);
		txtName = new JTextField(20);
		txtAddr = new JTextField(20);
		txtHp = new JTextField(20);
		txtJoin = new JTextField(20);
		txtPw = new JTextField(20);
/////////////////////////////화면 객체 배치하기 시작//////////////////////////////
		labelId.setBounds(20, 20, 300, 20);
		txtId.setBounds(180, 20, 150, 20);

		labelName.setBounds(20, 45, 300, 20);
		txtName.setBounds(180, 45, 150, 20);

		labelAddr.setBounds(20, 70, 300, 20);
		txtAddr.setBounds(180, 70, 150, 20);

		labelHp.setBounds(20, 95, 300, 20);
		txtHp.setBounds(180, 95, 150, 20);

		labelJoin.setBounds(20, 120, 300, 20);
		txtJoin.setBounds(180, 120, 150, 20);
		txtJoin.setEditable(false);

		labelPw.setBounds(20, 145, 300, 20);
		txtPw.setBounds(180, 145, 150, 20);

// 컴포넌트들을 패널에 붙입니다.
		jp_center.add(labelId);
		jp_center.add(txtId);
		jp_center.add(labelName);
		jp_center.add(txtName);
		jp_center.add(labelAddr);
		jp_center.add(txtAddr);
		jp_center.add(labelHp);
		jp_center.add(txtHp);
		jp_center.add(labelJoin);
		jp_center.add(txtJoin);
		jp_center.add(labelJoin);
		jp_center.add(txtJoin);
		jp_center.add(labelPw);
		jp_center.add(txtPw);
		// 컴포넌트들을 패널에 붙입니다.
		jp_center.add(labelId);
		jp_center.add(txtId);
		jp_center.add(labelName);
		jp_center.add(txtName);
		jp_center.add(labelAddr);
		jp_center.add(txtAddr);
		jp_center.add(labelHp);
		jp_center.add(txtHp);
		jp_center.add(labelJoin);
		jp_center.add(txtJoin);
		jp_center.add(labelPw);
		jp_center.add(txtPw);
/////////////////////////////화면 객체 배치하기 끝//////////////////////////////
		jbtn_save.addActionListener(this);
		jbtn_cancel.addActionListener(this);
		this.setLayout(new BorderLayout());
		jp_south.add(jbtn_save);
		jp_south.add(jbtn_cancel);
		this.add("South", jp_south);
		this.add("Center", jsp_line);
		this.setSize(400, 250);
		this.setVisible(false);
	}

	public void setValue() {
		if(mVO==null) {//입력
			setId("");
			setName("");
			setAddr("");
			setHp("");
			setPw("");
		}else {//수정
			setId(mVO.getMEM_ID());
			setName(mVO.getNAME());
			setAddr(mVO.getADDRESS());
			setHp(mVO.getPHO_NO());
			setJoin(mVO.getJOIN_DATE());
			setPw(mVO.getMEM_PW());
		}
	}
	
	///////////// 컴퍼넌트 활성화 or 비활성화////////////////////////////////
	public void setEditable(boolean isEdit) {
		if(mVO==null) {//입력
			txtId.setEditable(isEdit);
			txtName.setEditable(isEdit);
			txtAddr.setEditable(isEdit);
			txtHp.setEditable(isEdit);
			txtPw.setEditable(isEdit);
		}else {//수정
			txtId.setEditable(false);
			txtName.setEditable(isEdit);
			txtAddr.setEditable(isEdit);
			txtHp.setEditable(isEdit);
			txtPw.setEditable(isEdit);
		}
		
		
	}

	////////////////////// getter or setter////////////////////////////
	public String getId() {	return txtId.getText();	}
	public void setId(String Id) {	txtId.setText(Id); }

	public String getName() {	return txtName.getText();}
	public void setName(String Name) { txtName.setText(Name);}

	public String getAddr() { return txtAddr.getText();	}
	public void setAddr(String Addr) {	txtAddr.setText(Addr);	}

	public String getHp() {	return txtHp.getText();	}
	public void setHp(String Hp) {	txtHp.setText(Hp);	}

	public String getJoin() { return txtJoin.getText();	}
	public void setJoin(String Join) {	txtJoin.setText(Join);	}

	public String getPw() {	return txtPw.getText();	}
	public void setPw(String Pw) {	txtPw.setText(Pw);	}
////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		if ("저장".equals(label)) {
			if (mVO != null) {// 수정
				try {
					memberVO paVO = new memberVO();
					paVO.setCommand("update");
					paVO.setMEM_ID(getId());
					paVO.setNAME(getName());
					paVO.setADDRESS(getAddr());
					paVO.setPHO_NO(getHp());
					paVO.setJOIN_DATE(getJoin());
					paVO.setMEM_PW(getPw());
					memberCtrl mCtrl = new memberCtrl();
					memberVO reVO = mCtrl.send(paVO);
					if (reVO != null) {
						if (reVO.getStatus() == 1) {
							JOptionPane.showMessageDialog(mView, "성공!!!!");
							this.dispose();
							mView.refreshData();
							
						} else {
							JOptionPane.showMessageDialog(mView, "실패");
							return;
						}
					}
				} catch (Exception e2) {

				}

			} else {// 입력
				try {
					memberVO paVO = new memberVO();
					paVO.setCommand("insert");
					paVO.setMEM_ID(getId());
					paVO.setNAME(getName());
					paVO.setADDRESS(getAddr());
					paVO.setPHO_NO(getHp());
					paVO.setJOIN_DATE(getJoin());
					paVO.setMEM_PW(getPw());
					memberCtrl mCtrl = new memberCtrl();
					memberVO reVO = mCtrl.send(paVO);
					if (reVO != null) {
						if (reVO.getStatus() == 1) {
							//JOptionPane.showMessageDialog(mView, "성공!!!!");
							this.dispose();
							mView.refreshData();
							
						} else {
							JOptionPane.showMessageDialog(mView, "실패");
							return;
						}
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		} else if ("취소".equals(label)) {
			this.dispose();
		}
	}
	/*
	 * public static void main(String args[]) { memberSubView sBook = new
	 * memberSubView(); sBook.initDisplay(); }
	 */

}
