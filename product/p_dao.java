package com.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.util.DBConnectionHIFIVE;
import com.util.DBConnectionMgr;

public class p_dao implements p_Interface{
	java.sql.Connection 	   con = null;
	java.sql.PreparedStatement pstmt = null;
	java.sql.ResultSet 		   rs = null;//import안한건 경로 알려주려고
	com.util.DBConnectionHIFIVE   dbMgr=null;
	p_mainView pmain = new p_mainView();
	//////////////////////////////////////상세조회//////////////////////////////
	@Override
	public p_VO getproDetail(p_VO paVO) {
		dbMgr = DBConnectionHIFIVE.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select pro_cost,pro_quan,brand,pro_code,category,model_no from product  ");
		sql.append(" where pro_code=?");
		p_VO raVO=null;
		System.out.println("2");
		int i = 0;
		try {
			con = dbMgr.getConnection();
	    	pstmt = con.prepareStatement(sql.toString());
	    	pstmt.setString(1, paVO.getPro_code());
	    	rs = pstmt.executeQuery();//select ref커서일때 쓴다
	    	//rs.previous() 오라클은 커서가 항상 top에 가있어서 안쓴다
	    	//VO는 한행만 담을 수 있는 장애를 가지고 있다.
	    	System.out.println("1");
	    	if(rs.next()) {
	    		System.out.println("if");
	    		raVO = new p_VO();
	    		raVO.setPro_cost(rs.getString("pro_cost"));
				raVO.setPro_quan(rs.getString("pro_quan"));
				raVO.setBrand(rs.getString("brand"));
				raVO.setPro_code(rs.getString("pro_code"));
				raVO.setCategory(rs.getString("category"));
				raVO.setModel_no(rs.getString("model_no"));
	    	  	}	    	
	    } catch (Exception e) {
	    	System.out.println(e.toString());
	    } finally {//사용한 자원 반납하기 con,pstmt,re
	    	dbMgr.freeConnection(con, pstmt, rs);	    	
	    }
		return raVO;
	}
	////////////////////////////////////////////데이터 추가///////////////////
	@Override
	public p_VO proInsert(p_VO paVO) {
		p_VO raVO = new p_VO();
		dbMgr=com.util.DBConnectionHIFIVE.getInstance();
		StringBuilder sql = new StringBuilder();
		int status = 0; //0이면 실패 1이면 성공
		try {
		sql.append("insert into product"); 
		sql.append(" select max(pro_cost), max(pro_quan),max(brand), max(pro_code)+1,max(category),max(model_no) "); 
		sql.append(" from product"); 
		sql.append(" where category = ?"); 
		sql.append(" and brand = ?");
        con=dbMgr.getConnection();
        pstmt = con.prepareStatement(sql.toString());
        int i = 0;
        pstmt.setString(++i,paVO.getIncategory());
        pstmt.setString(++i,paVO.getInbrand());
        pstmt.setString(++i,paVO.getIncategory());
        pstmt.setString(++i,paVO.getInbrand());
       
        
        status=pstmt.executeUpdate();
        raVO.setStatus(status);//AddressVO status변수에 1이 저장
		} catch (SQLException se) {//ORA-XXXXXX
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		finally {
			dbMgr.freeConnection(con, pstmt);
		}
		return raVO;
	}
//////////////////////////////수정/////////////////////////////////////////////
	@Override
	public p_VO proUpdata(p_VO paVO) {
		p_VO raVO = new p_VO();
		dbMgr=DBConnectionHIFIVE.getInstance();
		StringBuilder sql = new StringBuilder();
		int status = 0; //0이면 실패 1이면 성공
		try {
		sql.append("update product set pro_cost = ? , pro_quan = ?, brand = ? , pro_code =? ,category =?, model_no =? "); 
		sql.append("   where pro_code = ?              ");
	    con=dbMgr.getConnection();
        pstmt = con.prepareStatement(sql.toString());
        pstmt.setString(1, paVO.getPro_code());
    	
		 int i = 0;
      	 pstmt.setString(++i,paVO.getPro_cost());
         pstmt.setString(++i,paVO.getPro_quan());
         pstmt.setString(++i,paVO.getBrand());
         pstmt.setString(++i,paVO.getPro_code());
         pstmt.setString(++i,paVO.getCategory());
         pstmt.setString(++i,paVO.getModel_no());
         pstmt.setString(++i,paVO.getPro_code());
        status=pstmt.executeUpdate();
        raVO.setStatus(status);//AddressVO status변수에 1이 저장
		} catch (SQLException se) {//ORA-XXXXXX
			System.out.println(sql.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		finally {
			dbMgr.freeConnection(con, pstmt);
		}
		return raVO;
	}
//////////////////////////////삭제/////////////////////////////
	@Override
	public p_VO proDelete(p_VO paVO) {
		StringBuilder sql = new StringBuilder();
		p_VO raVO = new p_VO();
		sql.append(" delete from product where pro_code=? ");
		dbMgr=DBConnectionHIFIVE.getInstance();
		int status = 0;
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, paVO.getPro_code());
			status = pstmt.executeUpdate();
			raVO.setStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
		return raVO;
	}
///////////////////////////////기본 조회화면////////
	@Override
	public List<p_VO> getpro() {
		List<p_VO> list = new ArrayList<p_VO>();
		dbMgr = DBConnectionHIFIVE.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select pro_cost,pro_quan,brand,pro_code,category,model_no from product  ");
		try {
			con = dbMgr.getConnection();
	    	pstmt = con.prepareStatement(sql.toString());
	    	rs = pstmt.executeQuery();//select ref커서일때 쓴다
	    	//rs.previous() 오라클은 커서가 항상 top에 가있어서 안쓴다
	    	//VO는 한행만 담을 수 있는 장애를 가지고 있다.
	    	p_VO raVO=null;
	    	while(rs.next()) {
	    		raVO = new p_VO();
	    		raVO.setPro_cost(rs.getString("pro_cost"));
				raVO.setPro_quan(rs.getString("pro_quan"));
				raVO.setBrand(rs.getString("brand"));
				raVO.setPro_code(rs.getString("pro_code"));
				raVO.setCategory(rs.getString("category"));
				raVO.setModel_no(rs.getString("model_no"));
	    		list.add(raVO);
	    	}	    	
	    } catch (Exception e) {
	    	System.out.println(e.toString());
	    } finally {//사용한 자원 반납하기 con,pstmt,re
	    	dbMgr.freeConnection(con, pstmt, rs);	    	
	    }
		
		return list;
	}
	//////////////////////////검색 조회 화면/////////////////////////
	@Override
	public List<p_VO> getser(p_VO paVO) {
		List<p_VO> list = new ArrayList<p_VO>();
		dbMgr = DBConnectionHIFIVE.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("select pro_cost,pro_quan,brand,pro_code,category,model_no from product  ");
		if(paVO.getSearch()=="브랜드") {
			sql.append(" where brand LIKE '%'||?||'%' ");
		}else if(paVO.getSearch()=="상품군") {
			sql.append(" where category LIKE '%'||?||'%' ");
		}else if(paVO.getSearch()=="상품코드") {
			sql.append(" where pro_code LIKE '%'||?||'%' ");
		}else if(paVO.getSearch()=="전체") {
			sql.append(" where brand LIKE '%'||?||'%' ");
			sql.append(" or category LIKE '%'||?||'%' ");
			sql.append(" or pro_code LIKE '%'||?||'%' ");
			sql.append(" or pro_cost LIKE '%'||?||'%' ");
			sql.append(" or pro_quan LIKE '%'||?||'%' ");
			sql.append(" or model_no LIKE '%'||?||'%' ");
		}
		p_VO raVO = null;
		try {
			con = dbMgr.getConnection();
	    	pstmt = con.prepareStatement(sql.toString());
	    	int i = 0;
	    	if(paVO.getSearch()=="전체") {
	    		pstmt.setString(++i,paVO.getKeyword() );
				pstmt.setString(++i,paVO.getKeyword() );
				pstmt.setString(++i,paVO.getKeyword() );
				pstmt.setString(++i,paVO.getKeyword() );
				pstmt.setString(++i,paVO.getKeyword() );
				pstmt.setString(++i,paVO.getKeyword() );
	    	}else {
	    		i=0;
				pstmt.setString(++i,paVO.getKeyword() );
	    	}
	    	rs = pstmt.executeQuery();//select ref커서일때 쓴다
	    	//rs.previous() 오라클은 커서가 항상 top에 가있어서 안쓴다
	    	//VO는 한행만 담을 수 있는 장애를 가지고 있다.
	    	while(rs.next()) {
	    		raVO = new p_VO();
	    		raVO.setPro_cost(rs.getString("pro_cost"));
				raVO.setPro_quan(rs.getString("pro_quan"));
				raVO.setBrand(rs.getString("brand"));
				raVO.setPro_code(rs.getString("pro_code"));
				raVO.setCategory(rs.getString("category"));
				raVO.setModel_no(rs.getString("model_no"));
	    		list.add(raVO);
	    	}	    	
	    } catch (Exception e) {
	    	System.out.println(e.toString());
	    } finally {//사용한 자원 반납하기 con,pstmt,re
	    	dbMgr.freeConnection(con, pstmt, rs);	    	
	    }
		
		return list;
	}

	@Override
	public p_VO proSearch(p_VO paVO) {
		// TODO Auto-generated method stub
		return null;
	}



	
}
