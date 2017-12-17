package gz.itcast.servlet;

import gz.itcast.dao.ProductDao;
import gz.itcast.entity.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Size;

public class DetailServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		
		//1.��ȡ���
		String id = request.getParameter("id");
		
		//2.�����ݿ��в�ѯ��Ӧ��ŵ���Ʒ
		ProductDao dao = new ProductDao();
		Product product = dao.findById(id);
		
		//3.��ʾ�������
		PrintWriter writer = response.getWriter();
		String html = "";
		
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset='UTF-8'>";
		html += "<title>��ʾ��Ʒ����ϸ</title>";
		html += "</head>";
		html += "<body>";
		
		html += "<table border='1' align='center' width='600px'>";
		
		if(product != null){
			html += "<tr><th>��ţ�</th><th>"+product.getId() +"</th></tr>";
			html += "</tr><th>��Ʒ���ƣ�</th><th>"+product.getProName() +"</th></tr>";
			html += "</tr><th>��Ʒ�ͺţ�</th><th>"+product.getProType() +"</th></tr>";
			html += "</tr><th>��Ʒ�۸�</th><th>"+product.getPrice() +"</th></tr>";
		}
		
		
		html += "</table>";
		html += "<center><a href='/day11_hist1/ListServlet'>[�����б�]</a></center>";
		html += "</body>";
		html += "</html>";
		
		
		
		/**
		 * ����cookie������
		 */
		//����cookie
		Cookie cookie = new Cookie("prodHist", createValue(request,id));
		cookie.setMaxAge(1*30*24*60*60); //һ����
		//2.����cookie
		response.addCookie(cookie);
		writer.write(html);
	}
	/**
	 * ����cookie��ֵ
	 * ������
	 * 		��ǰcookie��ֵ		������Ʒid		����cookieֵ
	 * 		null����û��prodHist		1			1
	 * 		1						2			2,1
	 * 		2,1						1			1,2
	 * 		2,1						3			3,2,1
	 * 		3,2,1					2			2,3,1
	 * 		3,2,1					4			4,3,2
	 * 
	 * @return
	 */
	private String createValue(HttpServletRequest request,String id) {
		
		Cookie[] cookies = request.getCookies();
		String prodHist = null;
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("prodHist")){
					prodHist = cookie.getValue();
					break;
				}
			}
		}
		//null��û��prodHist
		if(cookies == null || prodHist == null){
			//ֱ�ӷ��ش����id
			return id;
		}
		
		Collection<String> idsList = Arrays.asList(prodHist.split(","));
		LinkedList<String> list = new LinkedList<String>(idsList);
		if(list.size() < 3){
			if(list.contains(id)){
				list.remove(id);
				list.addFirst(id);
			}else {
				list.addFirst(id);
			}
			
		}else {
			if(list.contains(id)){
				list.remove(id);
				list.addFirst(id);
			}else {
				list.removeLast();
				list.addFirst(id);
			}
		}
		StringBuffer sb = new StringBuffer();
		for(String li:list){
			sb.append(li+",");
		}
		String result = sb.toString();
		result = result.substring(0,result.length()-1);
		return result;
	}

}
