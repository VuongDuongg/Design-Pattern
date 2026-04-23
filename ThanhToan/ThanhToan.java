/**
 * Model class cho thanh toán - sử dụng Builder Pattern
 * Immutable object để đảm bảo tính toàn vẹn của dữ liệu thanh toán
 */
public class ThanhToan {
    private final String maThanhToan;
    private final String maHoaDon;
    private final HinhThucThanhToan hinhThucThanhToan;
    private final double soTienThanhToan;
    private final String trangThai;
    private final long thoiGianThanhToan;

    // Constructor private - chỉ được gọi bởi Builder
    protected ThanhToan(ThanhToanBuilder builder) {
        this.maThanhToan = builder.getMaThanhToan();
        this.maHoaDon = builder.getMaHoaDon();
        this.hinhThucThanhToan = builder.getHinhThucThanhToan();
        this.soTienThanhToan = builder.getSoTienThanhToan();
        this.trangThai = builder.getTrangThai();
        this.thoiGianThanhToan = builder.getThoiGianThanhToan();
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

    /**
     * Thực hiện thanh toán sử dụng Strategy Pattern
     */
    public boolean thucHienThanhToan() throws Exception {
        hinhThucThanhToan.kiemTraDieuKien(soTienThanhToan);
        return hinhThucThanhToan.thucHienThanhToan(soTienThanhToan);
    }

    /**
     * Lấy tên hình thức thanh toán
     */
    public String getTenHinhThucThanhToan() {
        return hinhThucThanhToan.layTenHinhThuc();
    }

    @Override
    public String toString() {
        return "ThanhToan{" +
                "maThanhToan='" + maThanhToan + '\'' +
                ", maHoaDon='" + maHoaDon + '\'' +
                ", hinhThucThanhToan=" + hinhThucThanhToan.layTenHinhThuc() +
                ", soTienThanhToan=" + soTienThanhToan +
                ", trangThai='" + trangThai + '\'' +
                ", thoiGianThanhToan=" + thoiGianThanhToan +
                '}';
    }
}
