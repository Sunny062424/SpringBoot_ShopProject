package com.mall.shop;

import java.util.HashMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("create")
	public String createTable() {
		DB db = new DB();
		db.createTable();
		return "카트 테이블이 생성되었습니다.";
	}

	@GetMapping("/insert_cart")
	public HashMap<String, String> insertCart(@RequestParam(value="product", defaultValue="") String product, @RequestParam(value="price", defaultValue="")String price ) {
		DB db = new DB();
		db.insertData(product, price);
		HashMap<String, String> result = new HashMap<String, String>();
		result.put("message", "success");
		return result;
	}

	
}
