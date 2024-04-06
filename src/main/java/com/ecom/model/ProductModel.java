package com.ecom.model;

public class ProductModel {

    private int productId;
    private String productName;
    private String productBrand;
    private double price;
    private String productDescription;
    private String imageUrl;
    private ProductCategoryModel category;
    private int stock;

    public ProductModel() {
    }

    public ProductModel(int productId, String productName, String productBrand, double price, String productDescription,
            String imageUrl, ProductCategoryModel category, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.productBrand = productBrand;
        this.price = price;
        this.productDescription = productDescription;
        this.imageUrl = imageUrl;
        this.category = category;
        this.stock = stock;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductCategoryModel getCategory() {
        return category;
    }

    public void setCategory(ProductCategoryModel category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ProductModel [productId=" + productId + ", productName=" + productName + ", productBrand="
                + productBrand + ", price=" + price + ", productDescription=" + productDescription + ", imageUrl="
                + imageUrl + ", category=" + category + ", stock=" + stock + "]";
    }
}
