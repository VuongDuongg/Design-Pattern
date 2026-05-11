package singletonn;


public class Singleton {
    public static void main(String[] args) {
        
        // CÁCH DÙNG ĐÚNG: Gọi trực tiếp qua tên lớp Student
        Student student1 = Student.getInstance();
        Student student2 = Student.getInstance();
        student1.setName("Alice");
        student1.setLover("Bob");
        student2.setName("Nam");
        // Kiểm tra xem 2 biến có cùng trỏ về 1 đối tượng không
        System.out.println("Student 1: " + student1.hashCode());
        System.out.println("Student 2: " + student2.hashCode());
        System.out.println("Student 1 - Name: " + student1.getName() + ", Lover: " + student1.getLover());
        System.out.println("Student 2 - Name: " + student2.getName() + ", Lover: " + student2.getLover());
        if (student1 == student2) {
            System.out.println("=> Cung la 1 doi tuong!");
        }
    }

    // 1. Chuyển thành static class để main dễ gọi
    static class Student {
        // 2. Biến static để lưu trữ thực thể duy nhất
        private static Student instance;
        private String name;
        private String lover;

        // 3. QUAN TRỌNG: Private constructor duy nhất để ngăn chặn 'new Student()' từ bên ngoài
        private Student() {
            this.name = "Default Student";
            this.lover = "No lover";
            System.out.println("Khoi tao student moi: " + this.name + ", yeu: " + this.lover);
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
        public void setLover(String lover) { this.lover = lover; }
        public String getLover() { return lover; }
    }
}