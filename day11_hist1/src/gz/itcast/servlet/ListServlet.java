package gz.itcast.servlet;

import gz.itcast.dao.ProductDao;
import gz.itcast.entity.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		//��ȡ���ݿ⣬��ѯ��Ʒ�б�
		ProductDao dao = new ProductDao();
		List<Product> list = dao.findAll();
		
		//2.����Ʒ��ʾ�������
		PrintWriter writer = response.getWriter();
		String html = "";
		
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset='UTF-8'>";
		html += "<title></title>";
		html += "</head>";
		html += "<body>";
		
		html += "<table border='1' align='center' width='600px'>";
		html += "<tr>";
		html += "<th>���</th><th>��Ʒ������</th><th>��Ʒ�ͺ�</th><th>��Ʒ�۸�</th>";
		html += "</tr>";
		
		//������Ʒ
		if(list != null){
			for(Product p:list){
				html += "<tr>";
				html += "<td>"+p.getId()+"</td><td><a href='"+request.getContextPath()+"/DetailServlet?id="+p.getId()+"'>"+p.getProName()+"</a></td><td>"+p.getProType()+"</td><td>"+p.getPrice()+"</td>";
				html += "</tr>";
			}
		}
		
		
		html += "</table>";
		
		/**
		 * ��ʾ���������Ʒ
		 */
		html += "������������Ʒ:<br/>";
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("prodHist")){
					String prodHist = cookie.getValue(); //3,2,1
					String[] ids = prodHist.split(",");
					for(String id:ids){
						//��ѯ����
						Product p = dao.findById(id);
						//��ʾ�������
						html += "" + p.getId()+"&nbsp;"+p.getProName()+"&nbsp;" + p.getProType() +"&nbsp;" +p.getPrice() +"<br/>";
						
					}
					
				}
				
			}
		}
		
		
		html += "</body>";
		html += "</html>";
		
		writer.write(html);
	}
	

}
