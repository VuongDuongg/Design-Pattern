package Composition;

import java.util.ArrayList;
import java.util.List;

public class ExSolution {
    public static void main(String[] args) {
        // Khởi tạo đơn hàng (Cũng là một dạng Composite chứa được mọi IOder)
        Order order = new Order();
        
        // Thêm các sản phẩm đơn lẻ (Leaf) vào đơn hàng
        order.add(new Product("Product 1", 10.0));
        order.add(new Product("Product 2", 20.0));
        
        // Tạo một chiếc hộp (Composite) và thêm sản phẩm vào hộp
        Box box = new Box();
        box.add(new Product("Product 3", 30.0));
        
        // Tạo thêm một hộp nhỏ khác bỏ vào bên trong hộp lớn (Tính đệ quy của Composite)
        Box smallBox = new Box();
        smallBox.add(new Product("Product 4", 15.0));
        box.add(smallBox); // Thêm hộp nhỏ vào hộp lớn
        
        // Thêm hộp lớn vào đơn hàng
        order.add(box);
        
        // Tính tổng tiền một cách đồng nhất
        System.out.println("Total: " + order.calculateTotal());
    }

    // 1. Component Interface
    interface IOder {
        double calculateTotal();
    }

    // 2. Leaf (Thành phần lá - không chứa thành phần con)
    static class Product implements IOder {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
  
        @Override
        public double calculateTotal() {
            return price;
        }
    }

    // 3. Composite 1 (Hộp chứa - có thể chứa cả Product và Box khác)
    static class Box implements IOder {
        private List<IOder> items = new ArrayList<>();

        public void add(IOder item) {
            items.add(item);
        }

        public void remove(IOder item) {
            items.remove(item);
        }

        @Override
        public double calculateTotal() {
            double total = 0;
            for (IOder item : items) {
                total += item.calculateTotal(); // Gọi đệ quy không cần biết là Product hay Box
            }
            return total;
        }
    }

    // 4. Composite 2 (Đơn hàng - quản lý toàn bộ danh sách)
    static class Order implements IOder {
        private List<IOder> items = new ArrayList<>();

        public void add(IOder item) {
            items.add(item);
        }

        public void remove(IOder item) {
            items.remove(item);
        }

        @Override
        public double calculateTotal() {
            double total = 0;
            for (IOder item : items) {
                total += item.calculateTotal();
            }
            return total;
        }
    }
}