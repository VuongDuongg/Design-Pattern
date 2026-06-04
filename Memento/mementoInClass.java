package Memento;

import java.util.Stack;

public class mementoInClass {
    public static void main(String[] args) {
        // Khởi tạo Originator và Caretaker
        TextEditor editor = new TextEditor("Hello", 0, 0, 100);
        History history = new History();

        // 1. Lưu trạng thái ban đầu và thực hiện thay đổi
        history.save(editor.createSnapshot());
        editor.insertText(" World");
        editor.display(); // Output: Text: Hello World | Cursor: (0,0) | Width: 100

        // 2. Lưu trạng thái thứ hai và thực hiện thay đổi tiếp theo
        history.save(editor.createSnapshot());
        editor.deleteText(6); // Xóa chữ " World"
        editor.setCursor(5, 0); // Di chuyển con trỏ
        editor.display(); // Output: Text: Hello | Cursor: (5,0) | Width: 100

        System.out.println("--- Tiến hành Undo ---");

        // 3. Undo lần 1: Khôi phục lại trạng thái "Hello World" ban đầu
        if (!history.isEmpty()) {
            editor.restore(history.undo());
        }
        editor.display(); // Output: Text: Hello World | Cursor: (0,0) | Width: 100

        // 4. Undo lần 2: Khôi phục lại trạng thái "Hello" gốc lúc chưa thêm gì
        if (!history.isEmpty()) {
            editor.restore(history.undo());
        }
        editor.display(); // Output: Text: Hello | Cursor: (0,0) | Width: 100
    }

    // ==========================================
    // 1. ORIGINATOR: Đối tượng chính cần lưu/khôi phục trạng thái
    // ==========================================
    static class TextEditor {
        private String _text;
        private int _index_x;
        private int _index_y;
        private int _width;

        public TextEditor(String text, int index_x, int index_y, int width) {
            this._text = text;
            this._index_x = index_x;
            this._index_y = index_y;
            this._width = width;
        }

        public void insertText(String text) {
            _text += text;
        }

        public void deleteText(int length) {
            if (length <= _text.length()) {
                _text = _text.substring(0, _text.length() - length);
            }
        }

        public void setCursor(int x, int y) {
            this._index_x = x;
            this._index_y = y;
        }

        // Tạo ra bản sao trạng thái hiện tại (Memento)
        public TextEditorSnapshot createSnapshot() {
            return new TextEditorSnapshot(_text, _index_x, _index_y, _width);
        }

        // Khôi phục lại trạng thái từ bản sao cũ
        public void restore(TextEditorSnapshot snapshot) {
            if (snapshot != null) {
                this._text = snapshot.getText();
                this._index_x = snapshot.getIndexX();
                this._index_y = snapshot.getIndexY();
                this._width = snapshot.getWidth();
            }
        }

        public void display() {
            System.out.println("Text: " + _text + " | Cursor: (" + _index_x + "," + _index_y + ") | Width: " + _width);
        }
    }

    // ==========================================
    // 2. MEMENTO: Đối tượng chứa dữ liệu snapshot (Immutable)
    // ==========================================
    static class TextEditorSnapshot {
        private final String _text;
        private final int _index_x;
        private final int _index_y;
        private final int _width;

        public TextEditorSnapshot(String text, int index_x, int index_y, int width) {
            this._text = text;
            this._index_x = index_x;
            this._index_y = index_y;
            this._width = width;
        }

        // Chỉ cung cấp các hàm Getter để đọc dữ liệu khi khôi phục, không cho phép chỉnh sửa dữ liệu snapshot
        public String getText() { return _text; }
        public int getIndexX() { return _index_x; }
        public int getIndexY() { return _index_y; }
        public int getWidth() { return _width; }
    }
    
    // ==========================================
    // 3. CARETAKER: Đối tượng quản lý lịch sử các bản sao
    // ==========================================
    static class History {
        private final Stack<TextEditorSnapshot> _snapshots = new Stack<>();

        // Lưu trữ bản sao vào bộ nhớ lịch sử
        public void save(TextEditorSnapshot snapshot) {
            _snapshots.push(snapshot);
        }

        // Lấy bản sao gần nhất ra để chuẩn bị khôi phục (Undo)
        public TextEditorSnapshot undo() {
            if (_snapshots.isEmpty()) {
                return null;
            }
            return _snapshots.pop();
        }

        public boolean isEmpty() {
            return _snapshots.isEmpty();
        }
    }
}