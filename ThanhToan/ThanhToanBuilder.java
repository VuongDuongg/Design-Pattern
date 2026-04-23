/**
 * Builder Pattern - Xây dựng đối tượng ThanhToan
 */
public class ThanhToanBuilder {
    private String maThanhToan;
    private String maHoaDon;
    private HinhThucThanhToan hinhThucThanhToan;
    private double soTienThanhToan;
    private String trangThai;
    private long thoiGianThanhToan;

    public ThanhToanBuilder() {
        this.trangThai = "CHO_XU_LY";
        this.thoiGianThanhToan = System.currentTimeMillis();
    }

    public ThanhToanBuilder maMaThanhToan(String maThanhToan) {
        this.maThanhToan = maThanhToan;
        return this;
    }

    public ThanhToanBuilder maMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
        return this;
    }

    public ThanhToanBuilder maHinhThucThanhToan(HinhThucThanhToan hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
        return this;
    }

    public ThanhToanBuilder maSoTienThanhToan(double soTienThanhToan) {
        this.soTienThanhToan = soTienThanhToan;
        return this;
    }

    public ThanhToanBuilder maTrangThai(String trangThai) {
        this.trangThai = trangThai;
        return this;
    }

    public ThanhToanBuilder maThoiGianThanhToan(long thoiGianThanhToan) {
        this.thoiGianThanhToan = thoiGianThanhToan;
        return this;
    }

    public ThanhToan xay() throws Exception {
        if (maThanhToan == null || maThanhToan.isEmpty()) {
            throw new Exception("Mã thanh toán không được để trống");
        }
        if (maHoaDon == null || maHoaDon.isEmpty()) {
            throw new Exception("Mã hóa đơn không được để trống");
        }
        if (hinhThucThanhToan == null) {
            throw new Exception("Hình thức thanh toán không được để trống");
        }
        if (soTienThanhToan <= 0) {
            throw new Exception("Số tiền phải lớn hơn 0");
        }
        
        return new ThanhToan(this);
    }

    public String getMaThanhToan() {
        return maThanhToan;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public HinhThucThanhToan getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public double getSoTienThanhToan() {
        return soTienThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public long getThoiGianThanhToan() {
        return thoiGianThanhToan;
    }
}
