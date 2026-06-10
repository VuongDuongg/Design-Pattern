package Memento;

import java.util.Date;
import java.util.Stack;

public class mementoInClass {
    public static void main(String[] args) {
        // Khởi tạo Editor (Originator) và History (Caretaker)
        Editor editor = new Editor("Phiên bản 1", 10, 20, 100);
        History history = new History();

        // Thay đổi và lưu trạng thái vào History
        editor.display();
        history.add(editor.makeSnapshot());

        editor.setText("Phiên bản 2");
        editor.setCursorPos(50, 60);
        editor.display();
        history.add(editor.makeSnapshot());

        editor.setText("Phiên bản chỉnh sửa lỗi");
        editor.display();

        System.out.println("\n--- Thực hiện hoàn tác (Undo) ---");
        
        // Xem metadata lịch sử từ phía Caretaker
        history.showHistory();

        // Khôi phục về snapshot gần nhất (Phiên bản 2)
        Memento lastSnapshot = history.pop();
        if (lastSnapshot != null) {
            editor.restore(lastSnapshot);
        }
        editor.display();

        // Khôi phục về snapshot đầu tiên (Phiên bản 1)
        Memento firstSnapshot = history.pop();
        if (firstSnapshot != null) {
            editor.restore(firstSnapshot);
        }
        editor.display();
    }

    // ==========================================
    // 1. MEMENTO INTERFACE (Caretaker chỉ nhìn thấy phần này)
    // ==========================================
    interface Memento {
        String getName();
        Date getSnapshotDate();
    }

    // ==========================================
    // 2. ORIGINATOR (Đối tượng chính)
    // ==========================================
    static class Editor {
        private String text;
        private int cursorPos;
        private int selection;
        private int currentFont;
        private int styles;

        public Editor(String text, int cursorPos, int selection, int styles) {
            this.text = text;
            this.cursorPos = cursorPos;
            this.selection = selection;
            this.styles = styles;
        }

        // Tạo bản chụp snapshot - trả về kiểu Interface
        public Memento makeSnapshot() {
            return new Snapshot(text, cursorPos, selection, currentFont, styles);
        }

        // Khôi phục trạng thái
        public void restore(Memento memento) {
            // Kiểm tra và ép kiểu ngược lại về Snapshot cụ thể để lấy full thuộc tính
            if (memento instanceof Snapshot) {
                Snapshot snapshot = (Snapshot) memento;
                this.text = snapshot.text;
                this.cursorPos = snapshot.cursorPos;
                this.selection = snapshot.selection;
                this.currentFont = snapshot.currentFont;
                this.styles = snapshot.styles;
            }
        }

        public void setText(String text) { this.text = text; }
        public void setCursorPos(int x, int y) { this.cursorPos = x + y; }

        public void display() {
            System.out.println("[Editor State] Text: '" + text + "', Cursor: " + cursorPos + ", Styles: " + styles);
        }

        // ==========================================
        // 3. CONCRETE MEMENTO (Nested class / Inner class riêng tư của Editor)
        // ==========================================
        // Đặt private/protected tĩnh để chỉ Editor có quyền truy cập vào các trường dữ liệu
        private static class Snapshot implements Memento {
            private final String text;
            private final int cursorPos;
            private final int selection;
            private final int currentFont;
            private final int styles;
            private final Date date;

            public Snapshot(String text, int cursorPos, int selection, int currentFont, int styles) {
                this.text = text;
                this.cursorPos = cursorPos;
                this.selection = selection;
                this.currentFont = currentFont;
                this.styles = styles;
                this.date = new Date(); // Tự động lưu thời gian chụp
            }

            // Hiện thực các hàm thuộc Memento Interface (Metadata công khai)
            @Override
            public String getName() {
                return "Snapshot: '" + (text.length() > 10 ? text.substring(0, 10) + "..." : text) + "'";
            }

            @Override
            public Date getSnapshotDate() {
                return date;
            }
        }
    }

    // ==========================================
    // 4. CARETAKER (Người quản lý lịch sử)
    // ==========================================
    static class History {
        // Chỉ lưu danh sách các interface hẹp Memento, không biết cấu trúc bên trong Snapshot
        private final Stack<Memento> historyStack = new Stack<>();

        public void add(Memento memento) {
            historyStack.push(memento);
        }

        public Memento pop() {
            if (historyStack.isEmpty()) return null;
            return historyStack.pop();
        }

        public void showHistory() {
            System.out.println("Danh sách các bản sao lưu hiện có:");
            for (Memento m : historyStack) {
                System.out.println(" - " + m.getName() + " | Tạo lúc: " + m.getSnapshotDate());
            }
            System.out.println();
        }
    }
}