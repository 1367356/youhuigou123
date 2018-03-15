package browserTest;

import java.util.List;

import com.background.dao.mapper.DetailDisplayMapper;
import data.pojo.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DetailDisplayServiceImpl implements DetailDisplayService {

	@Autowired
	private DetailDisplayMapper detailDisplayMapper;
	
	public Product getProductByUid(String uid){
		Product product = detailDisplayMapper.getProductByUid(uid);
		System.out.println(product.getDisherf()+""+product.getName());
		return product;
	}
	private List<Product> selectRecProduct(int nextInt, int offset) {
		// TODO Auto-generated method stub
		return detailDisplayMapper.selectRecProduct(nextInt,offset);
	}

	/**
	 * 根据uid查询商品和推荐商品。
	 */
	public List<Product> selectProductAndRecByUid(String uid) {
		System.out.println(uid);
		// TODO Auto-generated method stub
		Product product = getProductByUid(uid);
		int uidInt = Integer.parseInt(uid.substring(0, 5));
		uidInt=uidInt%9900;
		List<Product> listProduct=selectRecProduct(uidInt,15);
		listProduct.add(0, product);
		return listProduct;
	}
}
