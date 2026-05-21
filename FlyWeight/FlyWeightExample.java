package FlyWeight;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlyWeightExample {
    public static void main(String[] args) {
        Game game = new Game();
        Unit unit1 = new Unit(game, new Point(0, 0));
        Unit unit2 = new Unit(game, new Point(10, 10));

        // Tiến hành bắn nhau 1000 lần (Sinh ra 2000 viên đạn/hạt)
        for (int i = 0; i < 1000; i++) {
            unit1.FireAt(unit2);
            unit2.FireAt(unit1);
        }

        // Kiểm tra xem hệ thống đã vẽ và quản lý hiệu quả chưa
        game.Draw();
        
        // Xem bộ nhớ thực tế: Chỉ có duy nhất 1 đối tượng chứa ảnh "bullet.png" được tạo ra!
        System.out.println("\n>>> Tổng số loại hạt (Flyweight) được lưu trữ trên RAM: " 
                            + ParticleFactory.getTypesCount());
    }

    // --- 1. FLYWEIGHT: Chứa trạng thái nội tại (Dữ liệu nặng, cố định) ---
    static class ParticleType {
        private String color;
        private String sprite; // Đường dẫn ảnh, rất nặng nếu load hàng ngàn lần

        public ParticleType(String color, String sprite) {
            this.color = color;
            this.sprite = sprite;
        }

        public void draw(Point coords, double vector, int speed) {
            // Giả lập vẽ hạt lên màn hình bằng cách dùng chung dữ liệu sprite
            System.out.println("Drawing particle at (" + coords.x + ", " + coords.y + ") using sprite: [" + sprite + "] color: [" + color + "]");
        }
    }

    // --- 2. FLYWEIGHT FACTORY: Quản lý và chia sẻ các ParticleType ---
    static class ParticleFactory {
        private static final Map<String, ParticleType> particleTypes = new HashMap<>();

        public static ParticleType getParticleType(String color, String sprite) {
            String key = color + "_" + sprite;
            if (!particleTypes.containsKey(key)) {
                particleTypes.put(key, new ParticleType(color, sprite));
                System.out.println(">>> [Factory] Tạo mới loại hạt: " + key + " (Tải ảnh từ ổ cứng vào RAM)");
            }
            return particleTypes.get(key); // Trả về đối tượng có sẵn để dùng chung
        }

        public static int getTypesCount() {
            return particleTypes.size();
        }
    }

    // --- 3. CONTEXT: Đối tượng gọn nhẹ chứa trạng thái ngoại vi (Thay đổi liên tục) ---
    static class Particle {
        private Point coords;
        private double vector;
        private int speed;
        private ParticleType type; // Tham chiếu tới Flyweight dùng chung

        public Particle(Point coords, double vector, int speed, ParticleType type) {
            this.coords = coords;
            this.vector = vector;
            this.speed = speed;
            this.type = type;
        }

        public void Move() {
            coords.translate((int)(vector * speed), 0);
        }   

        public void Draw() {
            type.draw(coords, vector, speed);
        }
    }

    // --- 4. GAME CONTEXT ---
    static class Game {
        // Thay mảng bằng List để tránh lỗi kích thước mảng tĩnh và NullPointerException
        private List<Particle> particles = new ArrayList<>();

        public void AddParticle(Point coords, double vector, int speed, String color, String sprite) {
            // Lấy loại hạt từ Factory thay vì tự tạo mới dữ liệu nặng
            ParticleType type = ParticleFactory.getParticleType(color, sprite);
            Particle particle = new Particle(coords, vector, speed, type);
            particles.add(particle);
        }   

        public void Draw() {
            // Chỉ in ra 2 phần tử đầu để tránh làm ngập màn hình console
            System.out.println("\n--- Tiến hành vẽ các hạt đang bay ---");
            if (!particles.isEmpty()) {
                particles.get(0).Draw();
                particles.get(1).Draw();
                System.out.println("... và " + (particles.size() - 2) + " hạt khác.");
            }
        }
    }

    // --- 5. UNIT GAME ---
    static class Unit {
        private Game game;
        private Point position; // Đổi tên từ vector thành position cho đúng nghĩa tọa độ bắn

        public Unit(Game game, Point position) {
            this.game = game;
            this.position = position;
        }

        public void FireAt(Unit target) {
            // Tạo bản sao tọa độ hiện tại để hạt bay độc lập
            Point spawnPoint = new Point(this.position.x, this.position.y);
            // Gọi game tạo hạt
            game.AddParticle(spawnPoint, 1.0, 10, "red", "bullet.png");
        }   
    }
}