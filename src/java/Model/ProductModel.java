package Model;

import java.util.ArrayList;
import java.util.List;

import Model.Product;

public class ProductModel {

    private List<Product> products;

    public ProductModel() {
        products = new ArrayList<Product>();
        products.add(new Product("p01", "computer 1", 20));
        products.add(new Product("p02", "computer 2", 21));
        products.add(new Product("p03", "laptop 1", 22));
        products.add(new Product("p04", "laptop 2", 3));
        products.add(new Product("p05", "laptop 3", 7));
    }

    public List<String> search(String keyword) {
        List<String> names = new ArrayList<String>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(keyword.toLowerCase())) {
                names.add(product.getName());
            }
        }

        for (Product product : products) {
            System.out.println(product);
        }

        return names;
    }

}
