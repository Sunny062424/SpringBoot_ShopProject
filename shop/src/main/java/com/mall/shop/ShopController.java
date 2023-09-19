package com.mall.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {

    @GetMapping("/main")
    public String main(){
        return "index.html";
    }

    @GetMapping("/list")
	public String histories() {
		return "list.html";
	}

}
