//상품상세정보
package kr.co.ljy.jellyshop.product.controller;

import org.apache.ibatis.session.SqlSession;

import kr.co.ljy.jellyshop.controller.Controllers;
import kr.co.ljy.jellyshop.controller.LoginMainController;
import kr.co.ljy.jellyshop.dao.ProductMapper;
import kr.co.ljy.jellyshop.db.MyAppSqlConfig;
import kr.co.ljy.jellyshop.vo.Product;

public class DetailProductController extends Controllers {
	
	private ProductMapper productmapper;
	private int selectnum;
	
	public DetailProductController(int selectnum) {
		SqlSession session = MyAppSqlConfig.getSqlSession();
		productmapper = session.getMapper(ProductMapper.class);
		this.selectnum = selectnum;
	}
	
	public void service() {
		while (true) {
			try {
				Product pd = productmapper.selectProductInfo(selectnum);
				System.out.println("품목\t상품명\t가격\t수량");
				System.out.print(pd.getProductItem()+"\t");
				System.out.print(pd.getProductName() + "\t");
				System.out.print(pd.getPrice()+ "\t");
				System.out.print(pd.getInAmount());
				
				if (LoginMainController.chk == 0) { 
					AdminDetailProductMainController ctrl = new AdminDetailProductMainController(selectnum);
					ctrl.service();
				} else {
					UserDetailProductMainController ctrl = new UserDetailProductMainController(selectnum);
					ctrl.service();
				}
			} catch (Exception e) {
				System.out.println("*잘못 입력한 값이 있습니다.*");
				System.out.println("*다시 입력해주세요.*");
				continue;
			}
		}
	}
}
	
