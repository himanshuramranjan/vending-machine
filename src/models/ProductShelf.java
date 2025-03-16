package models;

public class ProductShelf {
    private final int code;
    private Product product;
    private int quantity;

    public ProductShelf(int code, Product product, int quantity) {
        this.code = code;
        this.product = product;
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCode() {
        return code;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
