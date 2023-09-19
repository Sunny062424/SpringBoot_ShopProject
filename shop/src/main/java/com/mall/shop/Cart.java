package com.mall.shop;

public class Cart {
    int idx;
    String get_time;
    String product;
    int price;

    Cart() {

    }

    Cart(int idx, String get_time, String product, int price){
        this.idx = idx;
        this.get_time = get_time;
        this.product = product;
        this.price = price;
    }
    
    public int getIdx() {
        return this.idx;
    }
    
    public String getTime(){
        return this.get_time;
    }

    public String getProduct(){
        return this.product;
    }

    public int getPrice(){
        return this.price;
    }

}
