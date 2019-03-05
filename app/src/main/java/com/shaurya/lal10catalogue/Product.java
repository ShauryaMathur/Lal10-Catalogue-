package com.shaurya.lal10catalogue;

public class Product {

    public String name;
    public String category;
    public String desc;
    public String price;
    public String productKey;
    public String image;

    public String getImage() {
        return image;
    }

    public Product(){

    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product(String name, String category, String desc, String price, String productKey, String image) {
        this.name = name;
        this.category = category;
        this.desc = desc;
        this.price = price;
        this.productKey = productKey;
        this.image = image;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
