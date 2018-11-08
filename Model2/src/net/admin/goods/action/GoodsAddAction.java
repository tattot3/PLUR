package net.admin.goods.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.admin.goods.db.AdminGoodsDAO;
import net.admin.goods.db.GoodsBean;

public class GoodsAddAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("GoodsAddAction execute");
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		// upload 폴더 만들기
		ServletContext context = request.getServletContext();
		String filePath = context.getRealPath("/upload");
		System.out.println("업로드 폴더 만들기");
		int maxSize = 5*1024*1024; 
		// MultipartRequest multi 객체생성
		MultipartRequest multi = 
				new MultipartRequest(request,filePath,maxSize,"utf-8",new DefaultFileRenamePolicy());

		// GoodsBean gbean 자바빈 객체생성
		GoodsBean gBean=new GoodsBean();
		// set 메서드 호출 <= 파라미터 가져오기
		gBean.setCategory(multi.getParameter("category"));
		gBean.setName(multi.getParameter("name"));
		gBean.setContent(multi.getParameter("content"));
		gBean.setSize(multi.getParameter("size"));
		gBean.setColor(multi.getParameter("color"));
		gBean.setAmount(Integer.parseInt(multi.getParameter("amount")));
		gBean.setPrice(Integer.parseInt(multi.getParameter("price")));
		gBean.setBest(0);
		// setImage <= 파일,파일,파일,파일
		//gbean.setImage(multi.getFilesystemName("file1")+","+);
		gBean.setImage(multi.getFilesystemName("file1")+","+("file2")+","+("file3")+","+("file4"));
		
		// AdminGoodsDAO agdao 객체생성
		AdminGoodsDAO agdao = new AdminGoodsDAO();
		// insertGoods(gBean)메서드
		agdao.insertGoods(gBean);
		// 이동  ./GoodsList.ag
		forward.setRedirect(true);
		forward.setPath("./GoodsList.ag");
		return forward;
	}
}
