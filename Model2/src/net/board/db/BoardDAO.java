package net.board.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	private Connection getConnection() throws Exception {
		Connection con = null;
		/*
		Class.forName("com.mysql.jdbc.Driver");
		String dbUrl = "jdbc:mysql://localhost:3306/jspdb2";
		String dbUser = "jspid";
		String dbPass = "jsppass";
		con = DriverManager.getConnection(dbUrl, dbUser, dbPass); // 객체생성해서
																	// 연결정보를
																	// 담았음.

		return con;
		*/
		
		
		// 교재 443p
		// 컨넥션 풀 (Connection Pool)
		// 프로그램 설치 Java Naming DI API
		// DBCP API
		
		// tomcat-dbcp.jar설치
		// 1. WebContent/META-INF/context.xml (xml에 1-2단계 자원을 미리만들어둠)
		// 2. WebContent/WEB-IMF/web.xml (설정)
		// Context(javax.naming)
		// DataSource (javax.sql)
		Context init = new InitialContext();
		//init~이 Object형으로 저장이 되어있으니까 DataSource형태로 다운캐스팅해준다.
		DataSource ds= (DataSource)init.lookup("java:comp/env/jdbc/MysqlDB"); 
		//Datasource에 이는 것을 가져와서 getConnection을하면 Connection형태로 변해서 con변수에 담을 수 있다.
			con = ds.getConnection(); 
			return con;
	}

	public void insertBoard(BoardBean bb) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = getConnection();
			String sql = "select max(num) from board;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("max(num)") + 1;
			}
			pstmt.close();
			sql = "insert into board(num,name,pass,subject,content,file,re_ref,re_lev,re_seq,readcount,date) values(?,?,?,?,?,?,?,?,?,?,now());";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setString(6, bb.getFile());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, bb.getReadcount());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {rs.close();} 
				catch (SQLException e) {}}
			if (pstmt != null) {
				try {pstmt.close();} 
				catch (SQLException e) {}}
			if (con != null) {
				try {con.close();} 
				catch (SQLException e) {}}
		}
	}
	
	public int getBoardCount(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try{
			con = getConnection();
			String sql="select count(*) from board;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			count = rs.getInt("count(*)");
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null){
				try {rs.close();}
				catch (SQLException e){}}
			if (pstmt !=null){
				try{pstmt.close();}
				catch(SQLException e){}}
			if (con !=null){
				try {con.close();}
				catch (SQLException e){}}
		}return count;
	}
	
	public List<BoardBean> getBoardList(int startRow, int pageSize){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardBean> boardList = new ArrayList<>();
		try{
			con = getConnection();
			String sql = "select * from board order by re_ref desc, re_seq asc limit ?,?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardBean bb = new BoardBean();
				bb.setName(rs.getString("name")); 
				bb.setPass(rs.getString("pass"));
				bb.setNum(rs.getInt("num"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setFile(rs.getString("file"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setName(rs.getString("name"));
				bb.setDate(rs.getDate("date"));
				bb.setReadcount(rs.getInt("readcount"));
				
				boardList.add(bb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null){
				try {rs.close();}
				catch (SQLException e){}}
			if (pstmt !=null){
				try{pstmt.close();}
				catch(SQLException e){}}
			if (con !=null){
				try {con.close();}
				catch (SQLException e){}}
		}return boardList;
	}
	
	public BoardBean getContent(int num){
		BoardBean bb = new BoardBean();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getConnection();
			String sql = "select * from board where num=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				bb.setName(rs.getString("name")); 
				bb.setPass(rs.getString("pass"));
				bb.setNum(rs.getInt("num"));
				bb.setSubject(rs.getString("subject"));
				bb.setContent(rs.getString("content"));
				bb.setFile(rs.getString("file"));
				bb.setRe_ref(rs.getInt("re_ref"));
				bb.setRe_lev(rs.getInt("re_lev"));
				bb.setRe_seq(rs.getInt("re_seq"));
				bb.setDate(rs.getDate("date"));
				bb.setReadcount(rs.getInt("readcount"));
			}	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException e){}
			}if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}
			}if(con != null){
				try{con.close();}catch(SQLException e){}
			}
		}
		return bb;
	}
	
	public void updateReadcount(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getConnection();
			String sql ="update board set readcount=readcount+1 where num=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,	num);
			pstmt.executeUpdate();
			
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException e){}}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}}
			if(con != null){
				try{con.close();}catch(SQLException e){}}
		}
	}
	
	public void updateContent(BoardBean bb){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getConnection();
			String sql = "update board set subject=?, content=? where num=? and pass=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bb.getSubject());
			pstmt.setString(2, bb.getContent());
			pstmt.setInt(3, bb.getNum());
			pstmt.setString(4, bb.getPass());
			
			pstmt.executeUpdate();
		}catch(Exception e	){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException e){}}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}}
			if(con != null){
				try{con.close();}catch(SQLException e){}}
		}
	}
	
	public int passCheck(String pass, int num){
		int result=0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getConnection();
			String sql = "select * from board where num=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				if(rs.getString("pass").equals(pass)){result=1;}
				else{result=0;}
			}
		}catch(Exception e	){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException e){}}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}}
			if(con != null){
				try{con.close();}catch(SQLException e){}}
		}return result;
	}

	public void deleteContent(String pass, int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getConnection();
			String sql = "delete from board where num=? and pass=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, pass);
			
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException e){}}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}}
			if(con != null){
				try{con.close();}catch(SQLException e){}}
		}
	}
	
	public void reinsertBoard(BoardBean bb) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = 0;
		try {
			con = getConnection();
			String sql = "select max(num) from board;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt("max(num)") + 1;
			}
			pstmt.close();
			// re_seq 순서값 수정 => 기존의 답글 순서 재배치
			// update re_seq = re_seq+1 기존의 답글의 순서 1씩 증가
			//			조건) 같은 그룹이면서 re_seq 순서값이 자기자신보다 큰값 
			sql = "update board set re_seq=re_seq+1 where re_ref=? and re_seq>?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bb.getRe_ref());
			pstmt.setInt(2, bb.getRe_seq());
			
			pstmt.executeUpdate();
			// 3단계 update구문만들기
			// 4단계 실행
			sql = "insert into board(num,name,pass,subject,content,file,re_ref,re_lev,re_seq,readcount,date) values(?,?,?,?,?,?,?,?,?,?,now());";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, bb.getName());
			pstmt.setString(3, bb.getPass());
			pstmt.setString(4, bb.getSubject());
			pstmt.setString(5, bb.getContent());
			pstmt.setString(6, bb.getFile());
			pstmt.setInt(7, bb.getRe_ref());
			pstmt.setInt(8, bb.getRe_lev()+1);
			pstmt.setInt(9, bb.getRe_seq()+1);
			pstmt.setInt(10, bb.getReadcount());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {rs.close();} 
				catch (SQLException e) {}}
			if (pstmt != null) {
				try {pstmt.close();} 
				catch (SQLException e) {}}
			if (con != null) {
				try {con.close();} 
				catch (SQLException e) {}}
		}
	}
}
