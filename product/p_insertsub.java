package com.product;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class p_insertsub extends JDialog implements ActionListener{
	private JLabel labelproBrand;
	private JComboBox comboproBrand;
	private JLabel labelproCategory;
	private JComboBox comboproCategory;
	JPanel jp_south = new JPanel();
	JPanel jp_center = new JPanel();
	JButton jbtn_save= new JButton("저장");
	JButton jbtn_can= new JButton("취소");
	String title = null;
	p_VO pVO = null;
	p_mainView pmain = null;
	public p_insertsub() {
		initdisplay();
	}
	public void set(Object object, String label, p_mainView pmain, boolean b) {
		this.pVO=pVO;
		this.title = title;
		this.pmain= pmain;
		this.setTitle(title);
		this.setVisible(true);		
	}
	


	public void initdisplay() {
		labelproBrand = new JLabel("브랜드");
		labelproCategory = new JLabel("상품명");
		String[] brand = {"LG","Samsung","HANIL","DAEWOO","SAMSUNG","COWAY"};
		comboproBrand = new JComboBox(brand);
		String[] Category = {"공기청정기","로봇청소기"
				,"에어컨","정수기","냉장고","가습기","세탁기","건조기"};
		comboproCategory = new JComboBox(Category);
		labelproBrand.setBounds(20,20,300,20);
		comboproBrand.setBounds(150,20,150,20);
		labelproCategory.setBounds(20,45,300,20);
		comboproCategory.setBounds(150,45,150,20);
		
		
		jp_center.add(labelproBrand);
		jp_center.add(comboproBrand);
		jp_center.add(labelproCategory);
		jp_center.add(comboproCategory);
		this.setLayout(new BorderLayout());
		
		jbtn_save.addActionListener(this);
		jbtn_can.addActionListener(this);
		
		jp_south.add(jbtn_save);
		jp_south.add(jbtn_can);
		this.add("South",jp_south);
		this.add("Center",jp_center);
		this.setSize(300,300);
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		p_insertsub psub = new p_insertsub();
		//psub.initdisplay();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String label = e.getActionCommand();
		if("저장".equals(label)) {
			comboproBrand.getSelectedItem();
			comboproCategory.getSelectedItem();
			try {
				p_VO paVO = new p_VO();
				paVO.setCommand("insert");
				paVO.setInbrand((String)comboproBrand.getSelectedItem());
				paVO.setIncategory((String)comboproCategory.getSelectedItem());
				p_ctrl aCtrl=new p_ctrl();
				p_VO raVO=aCtrl.send(paVO);
				if(raVO!=null) {
					if(raVO.getStatus()==1) {//입력성공
						JOptionPane.showMessageDialog(pmain, "입력성공");
						this.dispose();
						pmain.refreshData();
					}else {
						JOptionPane.showMessageDialog(pmain, "해당상품이 존재하지 않습니다");
						return;
					}					
					
					
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}else if("취소".equals(label)) {
			this.dispose();//닫기
			//dispose는 창만 닫게 해줄 뿐 메모리에 대해서까지 영향력이 없다.
			//JOptionPane.showMessageDialog(aBook, "aBook:"+aBook); //주소값보기
		
		}
		
	}



}
