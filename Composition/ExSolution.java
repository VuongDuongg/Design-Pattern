package Composition;
import java.util.ArrayList;
import java.util.List;

public class ExSolution {
    public static void main(String[] args) {
        
    }

    interface IOder {
        double CaculateTotal();
    }

    class Product implements IOder {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }

        @Override
        public double CaculateTotal() {
            return price;
        }
    }

    class Oder {
        private List<Product> products;

        public Oder() {
            this.products = new ArrayList<>();
        }

        public void addProduct(Product product) {
            products.add(product);
        }

        public double CaculateTotal() {
            double total = 0;
            for(Product product : products) {
                total += product.CaculateTotal();
            }
            
            return total;
        }
    }
    class Box implements IOder {
        private List<IOder> items = new ArrayList<>();


        public Box() {
            this.items = new ArrayList<>();
        }

        public void addProduct(Product product) {
            items.add(product);
        }

        @Override
        public double CaculateTotal() {
            double total = 0;
            for(IOder item : items) {
                total += item.CaculateTotal();
            }
            return total;
        }
}
}
