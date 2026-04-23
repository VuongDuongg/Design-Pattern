/**
 * Factory Pattern - Tạo các đối tượng hình thức thanh toán
 */
public class ThanhToanFactory {
    
    public static HinhThucThanhToan taoHinhThucThanhToan(String loaiThanhToan, String... params) 
            throws IllegalArgumentException {
        
        switch(loaiThanhToan.toLowerCase()) {
            case "tien_mat":
                return new ThanhToanTienMat();
                
            case "the_credit":
                if (params.length < 2) {
                    throw new IllegalArgumentException("Cần số thẻ và CVV");
                }
                return new ThanhToanTheCredit(params[0], params[1]);
                
            case "chuyen_khoan":
                if (params.length < 2) {
                    throw new IllegalArgumentException("Cần số tài khoản và tên ngân hàng");
                }
                return new ThanhToanChuyenKhoan(params[0], params[1]);
                
            default:
                throw new IllegalArgumentException("Loại thanh toán không được hỗ trợ: " + loaiThanhToan);
        }
    }
}
