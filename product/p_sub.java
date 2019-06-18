package com.product;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.address.AddressBook;
import com.address.AddressBookCtrl;
import com.address.AddressVO;

public class p_sub extends JDialog implements ActionListener{
	private JLabel labelproCost;
	private JTextField txtproCost;
	private JLabel labelproQuan;
	private JTextField txtproQuan;
	private JLabel labelproBrand;
	private JTextField txtproBrand;
	private JLabel labelproCode;
	private JTextField txtproCode;
	private JLabel labelproCategory;
	private JTextField txtproCategory;
	private JLabel labelproModel_no;
	private JTextField txtproModel_no;
	
	Font font =new Font("돋음체",Font.PLAIN,16);//글꼴,스타일,크기
	JPanel jp_south = new JPanel();
	JPanel jp_center = new JPanel();//입력에 필요한 화면을 배치(좌표값을 가지고배치)
	JButton jbtn_save= new JButton("저장");
	JButton jbtn_can= new JButton("취소");
	
	p_mainView pmain = null;
	String title = null;
	p_VO pVO = null;
	
	public p_sub() {
		initDisplay();
	}
	public p_sub(p_mainView pmain) {
		this.pmain = pmain;
	}
	public void set(p_VO pVO, String title, p_mainView pmain, boolean isEdit) {
		this.pVO=pVO;
		this.title = title;
		this.pmain= pmain;
		this.setValue();
		this.setTitle(title);
		this.setEditable(isEdit);
		this.setVisible(true);
	}
	public void initDisplay() {
		//jp_center솟지에 레이아웃을 초기화 하자
		jp_center.setLayout(null);//기본값이  FlowLayout
		jp_center.setBackground(new Color(200,240,206));
		jbtn_save.setBackground(new Color(85,28,0));
		jbtn_save.setForeground(Color.white);
		jbtn_can.setBackground(new Color(85,28,0));
		jbtn_can.setForeground(Color.white);


		///////////////화면 객체 생성하기 시작///////////////////
		labelproCost = new JLabel("가격");
		labelproQuan = new JLabel("수량");
		labelproBrand = new JLabel("브랜드명");
		labelproCode = new JLabel("상품코드");
		labelproCategory = new JLabel("상품군");
		labelproModel_no = new JLabel("모델넘버");
		
		
		labelproCost.setFont(font);
		labelproQuan.setFont(font);
		labelproBrand.setFont(font);
		labelproCode.setFont(font);
		labelproCategory.setFont(font);
		labelproModel_no.setFont(font);
		
		txtproCost = new JTextField(20);
		txtproQuan = new JTextField(20);
		txtproBrand = new JTextField(20);
		txtproCode = new JTextField(20);
		txtproCategory = new JTextField(20);
		txtproModel_no = new JTextField(20);
		
		labelproCost.setBounds(20,20, 300,20);
		txtproCost.setBounds(150,20, 150,20);
		
		labelproQuan.setBounds(20,45, 300,20);
		txtproQuan.setBounds(150,45, 150,20);
		
		labelproBrand.setBounds(20,70, 300,20);
		txtproBrand.setBounds(150,70, 150,20);
		
		labelproCode.setBounds(20,95, 300,20);
		txtproCode.setBounds(150,95, 150,20);
		
		labelproCategory.setBounds(20,120, 300,20);
		txtproCategory.setBounds(150,120, 150,20);
		
		labelproModel_no.setBounds(20,145, 300,20);
		txtproModel_no.setBounds(150,145, 150,20);
		
		jp_center.add(labelproCost);
		jp_center.add(txtproCost);
		jp_center.add(labelproQuan);
		jp_center.add(txtproQuan);
		jp_center.add(labelproBrand);
		jp_center.add(txtproBrand);
		jp_center.add(labelproCode);
		jp_center.add(txtproCode);
		jp_center.add(labelproCategory);
		jp_center.add(txtproCategory);
		jp_center.add(labelproModel_no);
		jp_center.add(txtproModel_no);
		
			
		jbtn_save.addActionListener(this);
		jbtn_can.addActionListener(this);
		this.setLayout(new BorderLayout());
		jp_south.add(jbtn_save);
		jp_south.add(jbtn_can);
		this.add("South",jp_south);
		this.add("Center",jp_center);
		//자식창의 제목은 세가지 중 한가지가 되어야 한다
		//하나의 화면을 가지고 세가지 기능을 어떻게 해야하는가?
		//this.setTitle("입력|수정|상세조회");
		this.setSize(400,300);
		this.setVisible(false);
		
	}
	public void setValue() {
		//입력일때
		if(pVO==null) {
			//JOptionPane.showMessageDialog(aBook, "aVO:"+aVO.getId());
			setPro_cost("");
			setPro_quan("");
			setBrand("");
			setPro_code("");
			setCategory("");
			setModel_no("");
		}
		//상세조회나 수정시는 aVO에 있는 값으로 각 콤포넌트(txtId,txtname..)를 초기화한다.
		else {
			//JOptionPane.showMessageDialog(pmain, "pVO:"+pVO.getPro_code());
			//setId는 화면에 값을 출력, aVO.getId()-DB에서 가져온 값
			setPro_cost(pVO.getPro_cost());
			setPro_quan(pVO.getPro_quan());
			setBrand(pVO.getBrand());
			setPro_code(pVO.getPro_code());
			setCategory(pVO.getCategory());
			setModel_no(pVO.getModel_no());
		}
	}
	
	public void setEditable(boolean isEdit) {
		if(pVO==null) {
		txtproCost.setEditable(isEdit);
		txtproQuan.setEditable(isEdit);
		txtproBrand.setEditable(isEdit);
		txtproCode.setEditable(isEdit);
		txtproCategory.setEditable(isEdit);
		txtproModel_no.setEditable(isEdit);
		}else {
			txtproCost.setEditable(isEdit);
			txtproQuan.setEditable(isEdit);
			txtproBrand.setEditable(isEdit);
			txtproCode.setEditable(false);
			txtproCategory.setEditable(isEdit);
			txtproModel_no.setEditable(isEdit);
		}
		
		//comboGender.setEditable(isEdit);
	}
	public String getPro_cost() {return txtproCost.getText();}
	public void setPro_cost(String pro_cost) {txtproCost.setText(pro_cost);}
	public String getPro_quan() {return txtproQuan.getText();}
	public void setPro_quan(String pro_quan) {txtproQuan.setText(pro_quan);}
	public String getBrand() {return txtproBrand.getText();}
	public void setBrand(String brand) {txtproBrand.setText(brand);}
	public String getPro_code() {return txtproCode.getText();}
	public void setPro_code(String pro_code) {txtproCode.setText(pro_code);}
	public String getCategory() {return txtproCategory.getText();}
	public void setCategory(String category) {txtproCategory.setText(category);}
	public String getModel_no() {return txtproModel_no.getText();}
	public void setModel_no(String model_no) {txtproModel_no.setText(model_no);}

	public void actionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		if("저장".equals(label)) {
			if(pVO != null) {//수정인경우
				try {
					p_VO paVO = new p_VO();
					paVO.setCommand("update");
					paVO.setPro_cost(getPro_cost());
					paVO.setPro_quan(getPro_quan());
					paVO.setBrand(getBrand());
					paVO.setPro_code(getPro_code());
					paVO.setCategory(getCategory());
					paVO.setModel_no(getModel_no());
					p_ctrl aCtrl=new p_ctrl();
					System.out.println("sub");
					p_VO raVO=aCtrl.send(paVO);//p는 사용자가 입력 r은 오라클에서 가져온거
					System.out.println(raVO);
					if(raVO!=null) {
						System.out.println("sub1");
						if(raVO.getStatus()==1) {//입력성공
							JOptionPane.showMessageDialog(pmain, "입력성공");
							this.dispose();
							pmain.refreshData();
						}else {
							System.out.println("sub2");
							JOptionPane.showMessageDialog(pmain, "입력실패");
							return;
						}					
						
						
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}else {//입력인경우
				try {
					p_VO paVO = new p_VO();
					paVO.setCommand("insert");
					//다이얼로그창으로 부터 입력된 값을 읽어서 paVO에 담기
					paVO.setPro_cost(getPro_cost());
					paVO.setPro_quan(getPro_quan());
					paVO.setBrand(getBrand());
					paVO.setPro_code(getPro_code());
					paVO.setCategory(getCategory());
					paVO.setModel_no(getModel_no());
					//컨트롤계층에 데이터입력을 의뢰하고 입력이 성공되면
					//자식창은 닫고 부모창은 새로고침처리한다.
					p_ctrl aCtrl=new p_ctrl();
					System.out.println("sub");
					p_VO raVO=aCtrl.send(paVO);//p는 사용자가 입력 r은 오라클에서 가져온거
					System.out.println(raVO);
					if(raVO!=null) {
						System.out.println("sub1");
						if(raVO.getStatus()==1) {//입력성공
							JOptionPane.showMessageDialog(pmain, "입력성공");
							this.dispose();
							pmain.refreshData();
						}else {
							System.out.println("sub2");
							JOptionPane.showMessageDialog(pmain, "입력실패");
							return;
						}					
						
						
					}
				} catch (Exception e2) {
					System.out.println(e2.toString());
					// TODO: handle exception
				}
			}
			//this.dispose();//닫기
			pmain.refreshData();
			//dispose는 창만 닫게 해줄 뿐 메모리에 대해서까지 영향력이 없다.
			//JOptionPane.showMessageDialog(aBook, "aBook:"+aBook); //주소값보기

		}
		else if("취소".equals(label)) {
			this.dispose();//닫기
			//dispose는 창만 닫게 해줄 뿐 메모리에 대해서까지 영향력이 없다.
			//JOptionPane.showMessageDialog(aBook, "aBook:"+aBook); //주소값보기
		
		}
	}
	}
