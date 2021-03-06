package net.member.db;

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

public class MemberDAO {
	
	private Connection getConnection() throws Exception{
		Connection con = null;
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
		con = ds.getConnection();
		return con;
	}
	//public 리턴할형 함수이름(받을값){}
	
	
	//회원가입 insertMember() 함수
	public void insertMember(MemberBean mb){ //mb는 값이 아닌 주소값을 들고 있다.
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			 con = getConnection();
			 String sql="insert into member(id,pass,name,reg_date) values(?,?,?,now())";
			 pstmt=con.prepareStatement(sql); //객체생성 실행정보를 담음(?)
			 pstmt.setString(1,mb.getId());//물음표순서,값
			 pstmt.setString(2,mb.getPass());
			 pstmt.setString(3,mb.getName());
			 //4단계 실행
			 pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 예외 발생여부 상관없이 마무리 작업(필수)
			// 객체생성해서 사용한 기억공간을 정리하는 작업 -> .close();
			//con.close();
			//pstmt.close();
			if(rs != null){
				try{rs.close();}catch(SQLException e){}}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}}
			if(con != null){
				try{con.close();}catch(SQLException e){}}
		}
	}
	
	//getMember() 함수
	public MemberBean getMember(String id){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberBean mb = new MemberBean();
		try{
			con = getConnection();
			String sql="select * from member where id=?;";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs=pstmt.executeQuery();
			rs.next();
			mb.setName(rs.getString("name"));
			mb.setId(rs.getString("id"));
			mb.setPass(rs.getString("pass"));
			mb.setReg_date(rs.getTimestamp("reg_date"));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException e){}}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}}
			if(con != null){
				try{con.close();}catch(SQLException e){}}		
		}return mb;/*리턴할 주소값*/
	}

	//userCheck() 함수
	public int userCheck (String id, String pass){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try{
			con = getConnection();
			String sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			//4단계 결과 저장 <= 실행
			rs=pstmt.executeQuery();
			//4단계 결과 첫행으로 이동 데이터 있는 경우 "아이디 있음"
			if(rs.next()){
				if(rs.getString("pass").equals(pass)){result=1;}
				else{result=0;}
			}else{result=-1;}
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
		return result;
	}

	public void updateMember (MemberBean mb){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = getConnection();
			String sql = "update member set name=? where id=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getName());
			pstmt.setString(2, mb.getId());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}}
			if(con != null){
				try{con.close();}catch(SQLException e){}}
		}
	}
	
	public void deleteMember(MemberBean mb){
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = getConnection();
			String sql = "delete from member where id=?;";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mb.getId());
			pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}}
			if(con != null){
				try{con.close();}catch(SQLException e){}}
		}
	}
	
	public List getMemberList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//List list = new ArrayList<>(); 	// List를 직접적으로 받을 수 없어서 List의 상속을 받는 list중 하나인 Array List를 써준다.
										// 부모 list = new 자식 list 
										// => 코드수정을 최소화하기위해서, 업캐스팅을 이용한 다형성유지, 효율적이다
		List<MemberBean> memberList = new ArrayList<>();
		//MemberBean mb = new MemberBean();	// mb를 while문 밖에 선언해주면 모든사람의 정보가 들어가는게 아니고 제일마지막 사람만 저장이된다
											// while문 안에 객체를 생성해서 한사람의 정보를 저장할때마다 mb객체 생성을 해줘야 각각의 정보가
											// 다른 독립된 mb라는 저장공간에 개인의 정보가 저장된다. 
		try{
			con = getConnection();
			String sql="select * from member;";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			//5단계 저장된 결과 =>	한사람 정보 : MemberBean저장
			//				여러명 정보: 배열 List 한칸씩 저장
			while(rs.next()){
				//한사람의 정보를  MemberBean에 저장
			MemberBean mb = new MemberBean();
			mb.setId(rs.getString("id"));
			mb.setName(rs.getString("name"));
			mb.setPass(rs.getString("pass"));
			mb.setReg_date(rs.getTimestamp("reg_date"));
			// 한사람의 정보를 배열 List한칸에 저장
			memberList.add(mb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{rs.close();}catch(SQLException e){}}
			if(pstmt != null){
				try{pstmt.close();}catch(SQLException e){}}
			if(con != null){
				try{con.close();}catch(SQLException e){}}
		}return memberList;
	}
	
	public boolean idCheck(String userid){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean result = false;
		try{
			con = getConnection();
			String sql="select id from member where id=?;";
			pstmt=con.prepareStatement(sql);
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				result=true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (rs != null) {
				try {rs.close();} 
				catch (SQLException e) {}}
			if (pstmt != null) {
				try {pstmt.close();} 
				catch (SQLException e) {}}
			if (con != null) {
				try {con.close();} 
				catch (SQLException e) {}}
		}return result;
	}
}
