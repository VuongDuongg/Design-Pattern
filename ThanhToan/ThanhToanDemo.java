/**
 * Demo - Minh họa cách sử dụng các Design Pattern
 */
public class ThanhToanDemo {
    public static void main(String[] args) {
        try {
            // ===== 1. SỬ DỤNG FACTORY PATTERN =====
            System.out.println("===== 1. Factory Pattern - Tạo hình thức thanh toán =====");
            
            HinhThucThanhToan tienMat = ThanhToanFactory.taoHinhThucThanhToan("tien_mat");
            HinhThucThanhToan theCredit = ThanhToanFactory.taoHinhThucThanhToan("the_credit", 
                                                                                   "4532111111111111", "123");
            HinhThucThanhToan chuyenKhoan = ThanhToanFactory.taoHinhThucThanhToan("chuyen_khoan", 
                                                                                     "123456789", "Vietcombank");

            // ===== 2. SỬ DỤNG BUILDER PATTERN =====
            System.out.println("\n===== 2. Builder Pattern - Xây dựng đối tượng ThanhToan =====");
            
            ThanhToan thanhtoan1 = new ThanhToanBuilder()
                    .maMaThanhToan("TT001")
                    .maMaHoaDon("HD001")
                    .maHinhThucThanhToan(tienMat)
                    .maSoTienThanhToan(1000000)
                    .maTrangThai("DA_HOAN_TAT")
                    .xay();

            ThanhToan thanhtoan2 = new ThanhToanBuilder()
                    .maMaThanhToan("TT002")
                    .maMaHoaDon("HD002")
                    .maHinhThucThanhToan(theCredit)
                    .maSoTienThanhToan(2500000)
                    .maTrangThai("CHO_XU_LY")
                    .xay();

            ThanhToan thanhtoan3 = new ThanhToanBuilder()
                    .maMaThanhToan("TT003")
                    .maMaHoaDon("HD003")
                    .maHinhThucThanhToan(chuyenKhoan)
                    .maSoTienThanhToan(5000000)
                    .maTrangThai("CHO_XU_LY")
                    .xay();

            // ===== 3. SỬ DỤNG STRATEGY PATTERN =====
            System.out.println("\n===== 3. Strategy Pattern - Thực hiện thanh toán =====");
            
            System.out.println("\nThông tin thanh toán 1: " + thanhtoan1);
            boolean result1 = thanhtoan1.thucHienThanhToan();
            System.out.println("Kết quả: " + (result1 ? "Thành công" : "Thất bại"));

            System.out.println("\nThông tin thanh toán 2: " + thanhtoan2);
            boolean result2 = thanhtoan2.thucHienThanhToan();
            System.out.println("Kết quả: " + (result2 ? "Thành công" : "Thất bại"));

            System.out.println("\nThông tin thanh toán 3: " + thanhtoan3);
            boolean result3 = thanhtoan3.thucHienThanhToan();
            System.out.println("Kết quả: " + (result3 ? "Thành công" : "Thất bại"));

            // ===== 4. SỬ DỤNG SERVICE (Send) - Gửi thanh toán =====
            System.out.println("\n\n===== 4. Service Pattern - ThanhToanSend (Gửi thanh toán) =====");
            ThanhToanSend send = new ThanhToanSend();

            // 4.1: Gửi một thanh toán
            System.out.println("\n--- 4.1: Gửi thanh toán đơn lẻ ---");
            send.guiThanhToan(thanhtoan1);

            // 4.2: Gửi thanh toán với chế độ thử lại
            System.out.println("\n\n--- 4.2: Gửi thanh toán với chế độ thử lại (nếu thất bại) ---");
            send.guiThanhToanLaiLan(thanhtoan2, 1);

            // 4.3: Gửi danh sách thanh toán
            System.out.println("\n\n--- 4.3: Gửi danh sách thanh toán ---");
            java.util.List<ThanhToan> danhSach = new java.util.ArrayList<>();
            danhSach.add(thanhtoan1);
            danhSach.add(thanhtoan2);
            danhSach.add(thanhtoan3);
            send.guiDanhSachThanhToan(danhSach);

        } catch (Exception e) {
            System.err.println("Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
