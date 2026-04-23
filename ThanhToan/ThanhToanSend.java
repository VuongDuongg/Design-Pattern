/**
 * Service class - Xử lý và gửi thanh toán
 * Quản lý quy trình thanh toán từ yêu cầu đến hoàn tất
 */
public class ThanhToanSend {
    private static final String LOG_PREFIX = "[ThanhToanSend]";
    
    /**
     * Gửi (xử lý) một yêu cầu thanh toán
     * @param thanhToan - Đối tượng thanh toán cần xử lý
     * @return true nếu thanh toán thành công, false nếu thất bại
     */
    public boolean guiThanhToan(ThanhToan thanhToan) {
        if (thanhToan == null) {
            System.err.println(LOG_PREFIX + " Lỗi: Đối tượng thanh toán không được null");
            return false;
        }

        try {
            // Bước 1: Kiểm tra dữ liệu
            if (!kiemTraDuLieu(thanhToan)) {
                System.err.println(LOG_PREFIX + " Lỗi: Dữ liệu thanh toán không hợp lệ");
                return false;
            }

            // Bước 2: Log thông tin thanh toán
            inThongTinThanhToan(thanhToan);

            // Bước 3: Thực hiện thanh toán
            boolean ketQua = thanhToan.thucHienThanhToan();
            
            if (ketQua) {
                System.out.println(LOG_PREFIX + " ✓ Thanh toán thành công!");
                System.out.println(LOG_PREFIX + " Mã thanh toán: " + thanhToan.getMaThanhToan());
                System.out.println(LOG_PREFIX + " Thời gian: " + layThoiGianDinhDang(thanhToan.getThoiGianThanhToan()));
            } else {
                System.err.println(LOG_PREFIX + " ✗ Thanh toán thất bại!");
            }

            return ketQua;

        } catch (Exception e) {
            System.err.println(LOG_PREFIX + " Lỗi khi xử lý thanh toán: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gửi thanh toán với kiểm tra lại
     * @param thanhToan - Đối tượng thanh toán
     * @param lanThuTu - Lần thứ mấy (tối đa 3 lần)
     * @return true nếu thành công
     */
    public boolean guiThanhToanLaiLan(ThanhToan thanhToan, int lanThuTu) {
        if (lanThuTu > 3) {
            System.err.println(LOG_PREFIX + " Lỗi: Vượt quá số lần thử (tối đa 3 lần)");
            return false;
        }

        System.out.println(LOG_PREFIX + " Lần thứ " + lanThuTu + " gửi thanh toán...");
        boolean ketQua = guiThanhToan(thanhToan);

        if (!ketQua && lanThuTu < 3) {
            System.out.println(LOG_PREFIX + " Sẽ thử lại...\n");
            try {
                Thread.sleep(2000); // Chờ 2 giây trước khi thử lại
                return guiThanhToanLaiLan(thanhToan, lanThuTu + 1);
            } catch (InterruptedException e) {
                System.err.println(LOG_PREFIX + " Bị gián đoạn: " + e.getMessage());
                return false;
            }
        }

        return ketQua;
    }

    /**
     * Kiểm tra tính hợp lệ của dữ liệu thanh toán
     */
    private boolean kiemTraDuLieu(ThanhToan thanhToan) {
        if (thanhToan.getMaThanhToan() == null || thanhToan.getMaThanhToan().isEmpty()) {
            return false;
        }
        if (thanhToan.getMaHoaDon() == null || thanhToan.getMaHoaDon().isEmpty()) {
            return false;
        }
        if (thanhToan.getSoTienThanhToan() <= 0) {
            return false;
        }
        if (thanhToan.getHinhThucThanhToan() == null) {
            return false;
        }
        return true;
    }

    /**
     * In thông tin chi tiết thanh toán
     */
    private void inThongTinThanhToan(ThanhToan thanhToan) {
        System.out.println(LOG_PREFIX + " Thông tin thanh toán:");
        System.out.println(LOG_PREFIX + "   - Mã thanh toán: " + thanhToan.getMaThanhToan());
        System.out.println(LOG_PREFIX + "   - Mã hóa đơn: " + thanhToan.getMaHoaDon());
        System.out.println(LOG_PREFIX + "   - Hình thức: " + thanhToan.getTenHinhThucThanhToan());
        System.out.println(LOG_PREFIX + "   - Số tiền: " + String.format("%.0f VND", thanhToan.getSoTienThanhToan()));
        System.out.println(LOG_PREFIX + "   - Trạng thái: " + thanhToan.getTrangThai());
    }

    /**
     * Định dạng thời gian
     */
    private String layThoiGianDinhDang(long thoiGian) {
        return new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(new java.util.Date(thoiGian));
    }

    /**
     * Gửi nhiều thanh toán cùng lúc
     * @param danhSachThanhToan - Danh sách thanh toán
     * @return số lượng thanh toán thành công
     */
    public int guiDanhSachThanhToan(java.util.List<ThanhToan> danhSachThanhToan) {
        if (danhSachThanhToan == null || danhSachThanhToan.isEmpty()) {
            System.err.println(LOG_PREFIX + " Danh sách thanh toán trống");
            return 0;
        }

        int soThanhCong = 0;
        int soThatBai = 0;

        System.out.println(LOG_PREFIX + " Bắt đầu xử lý " + danhSachThanhToan.size() + " thanh toán...\n");

        for (int i = 0; i < danhSachThanhToan.size(); i++) {
            System.out.println(LOG_PREFIX + " [" + (i + 1) + "/" + danhSachThanhToan.size() + "]");
            if (guiThanhToan(danhSachThanhToan.get(i))) {
                soThanhCong++;
            } else {
                soThatBai++;
            }
            System.out.println();
        }

        // In báo cáo tổng hợp
        System.out.println(LOG_PREFIX + " ========== BÁO CÁO TỔNG HỢP ==========");
        System.out.println(LOG_PREFIX + " Tổng số lượng: " + danhSachThanhToan.size());
        System.out.println(LOG_PREFIX + " Thành công: " + soThanhCong);
        System.out.println(LOG_PREFIX + " Thất bại: " + soThatBai);
        System.out.println(LOG_PREFIX + " Tỷ lệ thành công: " + 
                          String.format("%.2f%%", (soThanhCong * 100.0 / danhSachThanhToan.size())));
        System.out.println(LOG_PREFIX + " ======================================");

        return soThanhCong;
    }
}
