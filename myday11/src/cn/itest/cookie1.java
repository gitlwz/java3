package cn.itest;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class cookie1 extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		

		response.setContentType("text/html;charset=utf-8");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currDate = format.format(new Date());
		
		
		//��ȡcookie
		Cookie[] co = request.getCookies();
		String lastTime = null;
		
		for(Cookie co2 : co){
			if("lastTime".equals(co2.getName())){
				lastTime = co2.getValue();
				response.addCookie(new Cookie("lastTime", currDate));
				response.getWriter().write("���ϴε�¼��ʱ��" + lastTime + "��ӭ��¼");
			}
		}
		
		if(co == null || lastTime == null){
			response.addCookie(new Cookie("lastTime", currDate));
			response.getWriter().write("���ǵ�һ�ε�¼��ϵͳ");
		}	
	}

}
