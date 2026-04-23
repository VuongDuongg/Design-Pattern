/**
 * Concrete Strategy - Thanh toán bằng thẻ tín dụng
 */
public class ThanhToanTheCredit implements HinhThucThanhToan {
    private String soTheCredit;
    private String cvv;

    public ThanhToanTheCredit(String soTheCredit, String cvv) {
        this.soTheCredit = soTheCredit;
        this.cvv = cvv;
    }

    @Override
    public boolean thucHienThanhToan(double soTien) {
        if (soTien <= 0 || soTheCredit == null || soTheCredit.isEmpty()) {
            return false;
        }
        System.out.println("Thanh toán thẻ credit: " + soTien + " VND");
        System.out.println("Số thẻ: " + maskTheCredit(soTheCredit));
        return true;
    }

    @Override
    public String layTenHinhThuc() {
        return "Thẻ tín dụng";
    }

    @Override
    public void kiemTraDieuKien(double soTien) throws Exception {
        if (soTien <= 0) {
            throw new Exception("Số tiền phải lớn hơn 0");
        }
        if (soTheCredit == null || soTheCredit.length() < 16) {
            throw new Exception("Số thẻ không hợp lệ");
        }
        if (cvv == null || cvv.length() != 3) {
            throw new Exception("CVV không hợp lệ");
        }
    }

    private String maskTheCredit(String soThe) {
        return "****-****-****-" + soThe.substring(soThe.length() - 4);
    }
}
