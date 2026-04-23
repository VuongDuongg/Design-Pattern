/**
 * Concrete Strategy - Thanh toán chuyển khoản ngân hàng
 */
public class ThanhToanChuyenKhoan implements HinhThucThanhToan {
    private String soTaiKhoan;
    private String tenNganHang;

    public ThanhToanChuyenKhoan(String soTaiKhoan, String tenNganHang) {
        this.soTaiKhoan = soTaiKhoan;
        this.tenNganHang = tenNganHang;
    }

    @Override
    public boolean thucHienThanhToan(double soTien) {
        if (soTien <= 0 || soTaiKhoan == null || soTaiKhoan.isEmpty()) {
            return false;
        }
        System.out.println("Thanh toán chuyển khoản: " + soTien + " VND");
        System.out.println("Ngân hàng: " + tenNganHang);
        System.out.println("Tài khoản: " + maskTaiKhoan(soTaiKhoan));
        return true;
    }

    @Override
    public String layTenHinhThuc() {
        return "Chuyển khoản ngân hàng";
    }

    @Override
    public void kiemTraDieuKien(double soTien) throws Exception {
        if (soTien <= 0) {
            throw new Exception("Số tiền phải lớn hơn 0");
        }
        if (soTaiKhoan == null || soTaiKhoan.length() < 8) {
            throw new Exception("Số tài khoản không hợp lệ");
        }
        if (tenNganHang == null || tenNganHang.isEmpty()) {
            throw new Exception("Tên ngân hàng không hợp lệ");
        }
    }

    private String maskTaiKhoan(String soTK) {
        return "****" + soTK.substring(soTK.length() - 4);
    }
}
