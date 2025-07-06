package models;
import exceptions.DuplicateShelfCodeException;
import exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Inventory {

    public static volatile Inventory inventory;

    private Inventory() {this.shelves = new HashMap<>(); }
    Map<Integer, ProductShelf> shelves;

    public static Inventory getInstance() {
        if(inventory == null) {
            synchronized (Inventory.class) {
                if (inventory == null) {
                    inventory = new Inventory();
                }
            }
        }
        return inventory;
    }

    public void refillProduct(int shelfCode, int quantity) throws ProductNotFoundException {
        if(!shelves.containsKey(shelfCode)) {
            throw new ProductNotFoundException("Product not found !!!");
        } else {
            ProductShelf shelf = shelves.get(shelfCode);
            shelf.setQuantity(shelf.getQuantity() + quantity);
        }
    }

    public void addProduct(Product product, int shelfCode, int quantity) throws DuplicateShelfCodeException {
        if(shelves.containsKey(shelfCode)) {
            throw new DuplicateShelfCodeException("Product Shelf occupied with other Product, use different shef code");
        } else {
            shelves.put(shelfCode, new ProductShelf(shelfCode, product, quantity));
        }
    }

    public Product getProduct(int shelfCode) throws Exception {
        ProductShelf shelf = shelves.get(shelfCode);
        if(shelf.getQuantity() == 0) throw new Exception("Sorry, Product is sold out");

        return shelf.getProduct();
    }

    public void dispenseProducts(List<Integer> shelfCodes) {
        for(int shelfCode : shelfCodes) {
            System.out.println("Dispensing : " + shelves.get(shelfCode).getProduct().getName());
            ProductShelf shelf = shelves.get(shelfCode);
            shelf.setQuantity(shelf.getQuantity() - 1);
        }
    }
}
