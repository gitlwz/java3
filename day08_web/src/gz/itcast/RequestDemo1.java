package gz.itcast;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestDemo1 extends HttpServlet {

	/**
	 
	 
	 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//从request对象去除请求数据
		
		//1.请求行
	 	System.out.println("请求方式"+request.getMethod());	//      请求方式
	 	System.out.println("URI"+request.getRequestURI());//		请求资源
	 	System.out.println("URL"+request.getRequestURL());
		//		http协议
		
		//2.请求头
		
		//3.请求的实体内容
		
		
		
		response.setContentType("text/html");
		
	}

}
