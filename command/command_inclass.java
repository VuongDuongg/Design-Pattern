package command;

import java.util.LinkedList;
import java.util.Queue;

public class command_inclass {
    public static void main(String[] args) {
        // 1. Tạo receiver (Đầu bếp)
        DauBep dauBep = new DauBep();

        // 2. Tạo invoker (Phục vụ)
        PhucVu phucVu = new PhucVu();

        // 3. Khách hàng gọi món (Tạo các Command)
        ICommand order1 = new OrderPhoCommand(dauBep);
        ICommand order2 = new OrderBunCommand(dauBep);
        ICommand order3 = new OrderComCommand(dauBep);

        // 4. Phục vụ tiếp nhận các order cho vào hàng đợi
        System.out.println("--- Phục vụ nhận order từ khách ---");
        phucVu.ThemOrder(order1);
        phucVu.ThemOrder(order2);
        phucVu.ThemOrder(order3);

        // 5. Chuyển order xuống bếp xử lý lần lượt
        System.out.println("\n--- Chuyển order cho bếp xử lý ---");
        phucVu.ChuyenOrderChoBep();
    }

    // Interface Command
    interface ICommand {
        void execute();
    }

    // Các Concrete Command (Thêm static để gọi được trong main)
    static class OrderBunCommand implements ICommand {
        private DauBep dauBep;

        public OrderBunCommand(DauBep dauBep) {
            this.dauBep = dauBep;
        }

        @Override
        public void execute() {
            dauBep.nauBun();
        }
    }

    static class OrderPhoCommand implements ICommand {
        private DauBep dauBep;

        public OrderPhoCommand(DauBep dauBep) {
            this.dauBep = dauBep;
        }

        @Override
        public void execute() {
            dauBep.nauPho();
        }
    }

    static class OrderComCommand implements ICommand {
        private DauBep dauBep;

        public OrderComCommand(DauBep dauBep) {
            this.dauBep = dauBep;
        }

        @Override
        public void execute() {
            dauBep.nauCom();
        }
    }

    // Receiver (Người thực hiện hành động thực tế)
    static class DauBep {
        public void nauPho() {
            System.out.println("Đầu bếp: Đang nấu Phở...");
        }
        
        public void nauBun() {
            System.out.println("Đầu bếp: Đang nấu Bún...");
        }
        
        public void nauCom() {
            System.out.println("Đầu bếp: Đang làm Cơm...");
        }
    }

    // Invoker (Người yêu cầu thực hiện hành động)
    static class PhucVu {
        // Sửa lỗi khởi tạo Queue bằng LinkedList
        private Queue<ICommand> commandQueue = new LinkedList<>();

        public void ThemOrder(ICommand command) {
            // Trong Java dùng offer() hoặc add() thay vì Enqueue
            commandQueue.offer(command); 
            System.out.println("Phục vụ: Đã ghi nhận 1 món ăn.");
        }

        public void ChuyenOrderChoBep() {
            while (!commandQueue.isEmpty()) {
                // Trong Java dùng poll() thay vì Dequeue
                ICommand command = commandQueue.poll(); 
                command.execute();
            }
        }
    }
}