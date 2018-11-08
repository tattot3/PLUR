package net.board.db;

import java.sql.Date;

public class BoardBean {
	
	/*	번호		제목			re_ref(답글그룹번호)	re_lev(들여쓰기)	re_seq(순서)
	 * 	6		제목3				6					0				0
	 * 	9		->답3_2			6					1				1
	 * 	10		->->답3_2_1		6					2				1+1=2
	 * 	7		->답3_1			6					1				1+1=2+1=3
	 * 	8		->->답3_1_1		6					2				2+1=3+1=4
	 * 
	 * 	2		제목2				2					0				0
	 * 	3		->답2_1			2					1				1
	 * 	4		->->답2_1_1		2					2				2
	 * 
	 * 	1		제목1				1					0				0
	 * 	5		->답1_1			1					1				1
	 * */
	
	private int num;
	private String name;
	private String pass;
	private String subject;
	private String content;
	private String file;
	private int re_ref; //답글의 그룹번호 = 기준번호
	private int re_lev; //들여쓰기
	private int re_seq; //순서
	private int readcount; 
	private Date date;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getRe_ref() {
		return re_ref;
	}
	public void setRe_ref(int re_ref) {
		this.re_ref = re_ref;
	}
	public int getRe_lev() {
		return re_lev;
	}
	public void setRe_lev(int re_lev) {
		this.re_lev = re_lev;
	}
	public int getRe_seq() {
		return re_seq;
	}
	public void setRe_seq(int re_seq) {
		this.re_seq = re_seq;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	} 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
