/**
 * Strategy Pattern - Interface cho các hình thức thanh toán
 */
public interface HinhThucThanhToan {
    boolean thucHienThanhToan(double soTien);
    String layTenHinhThuc();
    void kiemTraDieuKien(double soTien) throws Exception;
}
