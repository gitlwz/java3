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
		
		//��request����ȥ����������
		
		//1.������
	 	System.out.println("����ʽ"+request.getMethod());	//      ����ʽ
	 	System.out.println("URI"+request.getRequestURI());//		������Դ
	 	System.out.println("URL"+request.getRequestURL());
		//		httpЭ��
		
		//2.����ͷ
		
		//3.�����ʵ������
		
		
		
		response.setContentType("text/html");
		
	}

}
