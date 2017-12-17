package gz.itcast.dao;

import gz.itcast.entity.Product;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * �����д�Ŷ�Produt�����CRUD����
 * @author 54748
 *
 */


public class ProductDao {
	//ģ�����ݿ⣬���������Ʒ����
	private static	List<Product> data = new ArrayList<Product>();
	
	
	/**
	 * ��ʼ����Ʒ����
	 */
	static{
		//ִֻ��һ��
		for (int i = 1; i <= 10; i++) {
			data.add(new Product(""+i,"�ʼǱ�" + i,"LNoo" + i,34.0+i));
		}
	}
	
	/**
	 * �ṩ��ѯ������Ʒ�ķ���
	 */
	public List<Product> findAll() {
		return data;
	}
	
	/**
	 * ���ݱ�Ų�ѯ��Ʒ�ķ���
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
