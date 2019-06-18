package com.rental;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.util.DBConnectionMgrhifive;

public class memDao implements MemberInterface {
	Connection 		con 			 = null;
	PreparedStatement  pstmt	     = null;
	ResultSet 			rs			 = null;
	DBConnectionMgrhifive dbMgr		 = null;

	
	@Override
	public memberVO getmemberDetail(memberVO paVO) {
		dbMgr = DBConnectionMgrhifive.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("	SELECT mem_id, name, address, pho_no  " );
		sql.append("        , join_date ,zipcode , mem_pw     " );
		sql.append("   FROM member                            " );
		sql.append("   WHERE mem_id = ?                       " );
		memberVO reVO = null;
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, paVO.getMEM_ID());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reVO = new memberVO();
				reVO.setMEM_ID(rs.getString("mem_id"));
				reVO.setNAME(rs.getString("name"));
				reVO.setADDRESS(rs.getString("address"));
				reVO.setPHO_NO(rs.getString("pho_no"));
				reVO.setJOIN_DATE(rs.getString("join_date"));
				reVO.setZIPCODE(rs.getString("zipcode"));
				reVO.setMEM_PW(rs.getString("mem_pw"));
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
		
		return reVO;
	}
	
	@Override
	public memberVO memberInsert(memberVO paVO) {
		//System.out.println("dao insert");
		memberVO reVO = new memberVO();
		dbMgr = DBConnectionMgrhifive.getInstance();
		StringBuilder sql = new StringBuilder();
		int status = 0;
		try {
		sql.append("	INSERT INTO member(mem_id, name, address  					  ");
	    sql.append("            , pho_no, join_date,mem_pw)       					  ");
		sql.append("	VALUES(	? , ? , ? , ? , TO_CHAR(SYSDATE,'YYYY-MM-DD') , ? )	  ");
		con = dbMgr.getConnection();
	    pstmt = con.prepareStatement(sql.toString());
	    int i =0;
	    pstmt.setString(++i, paVO.getMEM_ID());
	    pstmt.setString(++i, paVO.getNAME());
	    pstmt.setString(++i, paVO.getADDRESS());
	    pstmt.setString(++i, paVO.getPHO_NO());
	    pstmt.setString(++i, paVO.getMEM_PW());
	    status = pstmt.executeUpdate();
	    reVO.setStatus(status);
			
		} catch (SQLException se) {
			System.out.println(se.toString());
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
		
		return reVO;
	}

	@Override
	public memberVO memberUpdate(memberVO paVO) {
		dbMgr = DBConnectionMgrhifive.getInstance();
		StringBuilder sql = new StringBuilder();
		int status = 0;
		sql.append("	UPDATE member 							  " );
		sql.append("        SET  name=?,address=?        " );
		sql.append("        ,pho_no=?,zipcode=?,mem_pw=?    	  " );
		sql.append("   WHERE mem_id = ?                           " );
		memberVO reVO = new memberVO();
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			int i = 0;
			pstmt.setString(++i,paVO.getNAME() );
			pstmt.setString(++i,paVO.getADDRESS() );
			pstmt.setString(++i,paVO.getPHO_NO() );
			pstmt.setString(++i,paVO.getZIPCODE() );
			pstmt.setString(++i,paVO.getMEM_PW() );
			pstmt.setString(++i,paVO.getMEM_ID() );
			status = pstmt.executeUpdate();
		    reVO.setStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
		return reVO;
	}

	@Override
	public memberVO memberDelete(memberVO paVO) {
		StringBuilder sql = new StringBuilder();
		memberVO reVO = new memberVO();
		sql.append(" DELETE from member where mem_id = ? " );
		dbMgr = DBConnectionMgrhifive.getInstance();
		int status = 0;
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, paVO.getMEM_ID());
			status = pstmt.executeUpdate();
			reVO.setStatus(status);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbMgr.freeConnection(con, pstmt);
		}
		return reVO;
	}

	@Override
	public List<memberVO> getmemberAll() {
		List<memberVO> mlist = new ArrayList<memberVO>();
		dbMgr=DBConnectionMgrhifive.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append("	SELECT mem_id, name, address, pho_no  " );
		sql.append("        , join_date ,zipcode , mem_pw     " );
		sql.append("   FROM member ORDER BY name              " );
		try {
			con = dbMgr.getConnection();
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			memberVO reVO = null;
			while(rs.next()) {
				reVO = new memberVO();
				reVO.setMEM_ID(rs.getString("mem_id"));
				reVO.setNAME(rs.getString("name"));
				reVO.setADDRESS(rs.getString("address"));
				reVO.setPHO_NO(rs.getString("pho_no"));
				reVO.setJOIN_DATE(rs.getString("join_date"));
				reVO.setZIPCODE(rs.getString("zipcode"));
				reVO.setMEM_PW(rs.getString("mem_pw"));
				mlist.add(reVO);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			dbMgr.freeConnection(con, pstmt,rs);
		}
		return mlist;
	}


}
