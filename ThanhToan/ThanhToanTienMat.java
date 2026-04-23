/**
 * Concrete Strategy - Thanh toán tiền mặt
 */
public class ThanhToanTienMat implements HinhThucThanhToan {
    
    @Override
    public boolean thucHienThanhToan(double soTien) {
        if (soTien <= 0) {
            return false;
        }
        System.out.println("Thanh toán tiền mặt: " + soTien + " VND");
        return true;
    }

    @Override
    public String layTenHinhThuc() {
        return "Tiền mặt";
    }

    @Override
    public void kiemTraDieuKien(double soTien) throws Exception {
        if (soTien <= 0) {
            throw new Exception("Số tiền phải lớn hơn 0");
        }
    }
}
