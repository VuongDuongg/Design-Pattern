package singletonn;

public class Singleton {
    public static void main(String[] args) {
        // CÁCH DÙNG ĐÚNG: Gọi trực tiếp qua tên lớp Student
        Student student1 = Student.getInstance();
        Student student2 = Student.getInstance();

        // Kiểm tra xem 2 biến có cùng trỏ về 1 đối tượng không
        System.out.println("Student 1: " + student1.hashCode());
        System.out.println("Student 2: " + student2.hashCode());
        
        if (student1 == student2) {
            System.out.println("=> Cả hai là cùng một đối tượng duy nhất!");
        }
    }

    // 1. Chuyển thành static class để main dễ gọi
    static class Student {
        // 2. Biến static để lưu trữ thực thể duy nhất
        private static Student instance;
        private String name;

        // 3. QUAN TRỌNG: Private constructor duy nhất để ngăn chặn 'new Student()' từ bên ngoài
        private Student() {
            this.name = "Default Student";
            System.out.println("Khởi tạo Student (Chỉ chạy 1 lần)");
        }

        // 4. Phương thức static để lấy thực thể duy nhất
        public static Student getInstance() {
            if (instance == null) {
                instance = new Student();
            }
            return instance;
        }

        public void setName(String name) { this.name = name; }
        public String getName() { return name; }
    }
}