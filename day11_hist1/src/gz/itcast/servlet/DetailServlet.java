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
		
		//1.获取编号
		String id = request.getParameter("id");
		
		//2.到数据库中查询对应编号的商品
		ProductDao dao = new ProductDao();
		Product product = dao.findById(id);
		
		//3.显示到浏览器
		PrintWriter writer = response.getWriter();
		String html = "";
		
		html += "<!DOCTYPE html>";
		html += "<html>";
		html += "<head>";
		html += "<meta charset='UTF-8'>";
		html += "<title>显示商品的详细</title>";
		html += "</head>";
		html += "<body>";
		
		html += "<table border='1' align='center' width='600px'>";
		
		if(product != null){
			html += "<tr><th>编号：</th><th>"+product.getId() +"</th></tr>";
			html += "</tr><th>商品名称：</th><th>"+product.getProName() +"</th></tr>";
			html += "</tr><th>商品型号：</th><th>"+product.getProType() +"</th></tr>";
			html += "</tr><th>商品价格：</th><th>"+product.getPrice() +"</th></tr>";
		}
		
		
		html += "</table>";
		html += "<center><a href='/day11_hist1/ListServlet'>[返回列表]</a></center>";
		html += "</body>";
		html += "</html>";
		
		
		
		/**
		 * 创建cookie并发送
		 */
		//创建cookie
		Cookie cookie = new Cookie("prodHist", createValue(request,id));
		cookie.setMaxAge(1*30*24*60*60); //一个月
		//2.发送cookie
		response.addCookie(cookie);
		writer.write(html);
	}
	/**
	 * 生产cookie的值
	 * 分析：
	 * 		当前cookie的值		传入商品id		最终cookie值
	 * 		null或者没有prodHist		1			1
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
		//null或没有prodHist
		if(cookies == null || prodHist == null){
			//直接返回传入的id
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
