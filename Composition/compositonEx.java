package Composition;

import java.util.ArrayList;
import java.util.List;

public class compositonEx {
    public static void main(String[] args) {
        
    }

    class Product extends Box {
        public double price;

        public Product(String name, double price) {
            super(name, price);
        }

        @Override
        public String getType() {
            return "Product";
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    class Order {
        private List<Product> products;

        public Order() {
            this.products = new ArrayList<>();
        }

        public void addProduct(Product product) {
            products.add(product);
        }

        public double CaculateTotal(List<Box> boxes) {
            double total = 0;
            for(Box box : boxes) {
                if(box.getType().equals("Product")) {
                    Product product = (Product) box;
                    total += product.getPrice();
                }
            }
            
            return total;
        }
    }

    class Box{
        private List<Product> products;
        public Box() {
            this.products = new ArrayList<>();
        }

        public Box(String name, double price) {
            this.products = new ArrayList<>();
            this.products.add(new Product(name, price));
        }

        public String getType() {
            return "Box";
        }
    }

}
