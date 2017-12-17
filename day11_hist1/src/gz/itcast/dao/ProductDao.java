package gz.itcast.dao;

import gz.itcast.entity.Product;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 该类中存放对Produt对象的CRUD方法
 * @author 54748
 *
 */


public class ProductDao {
	//模拟数据库，存放所有商品数据
	private static	List<Product> data = new ArrayList<Product>();
	
	
	/**
	 * 初始化商品数据
	 */
	static{
		//只执行一次
		for (int i = 1; i <= 10; i++) {
			data.add(new Product(""+i,"笔记本" + i,"LNoo" + i,34.0+i));
		}
	}
	
	/**
	 * 提供查询所有商品的方法
	 */
	public List<Product> findAll() {
		return data;
	}
	
	/**
	 * 根据编号查询商品的方法
	 */
	public Product findById(String id) {
		for (Product p:data) {
			if(p.getId().equals(id)){
				return p;
			}
		}
		return null;
	}
}
