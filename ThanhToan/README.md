# Tách Code Theo Design Pattern - Hệ Thống Thanh Toán

## Cấu Trúc Dự Án

```
d:\Các vấn đề tiên tiến
├── ThanhToan.java                    # Model class (Immutable)
├── HinhThucThanhToan.java            # Interface (Strategy Pattern)
├── ThanhToanTienMat.java             # Thanh toán tiền mặt
├── ThanhToanTheCredit.java           # Thanh toán thẻ credit
├── ThanhToanChuyenKhoan.java         # Thanh toán chuyển khoản
├── ThanhToanFactory.java             # Factory Pattern
├── ThanhToanBuilder.java             # Builder Pattern
└── ThanhToanDemo.java                # Ví dụ sử dụng
```

## Design Pattern Được Áp Dụng

### 1. **Strategy Pattern** 
**Mục đích:** Cho phép chọn thuật toán (hình thức thanh toán) tại runtime

**Các thành phần:**
- `HinhThucThanhToan` - Interface định nghĩa contract
- `ThanhToanTienMat` - Chiến lược thanh toán tiền mặt
- `ThanhToanTheCredit` - Chiến lược thanh toán thẻ credit
- `ThanhToanChuyenKhoan` - Chiến lược chuyển khoản

**Lợi ích:**
- Dễ dàng thêm hình thức thanh toán mới
- Khách hàng có thể lựa chọn hình thức thanh toán tại runtime
- Mỗi hình thức có logic riêng, không ảnh hưởng nhau

### 2. **Factory Pattern**
**Mục đích:** Tạo đối tượng hình thức thanh toán mà không tiết lộ logic tạo

**Thành phần:**
- `ThanhToanFactory` - Factory tạo các đối tượng HinhThucThanhToan

**Phương thức:**
```java
HinhThucThanhToan taoHinhThucThanhToan(String loaiThanhToan, String... params)
```

**Lợi ích:**
- Tập trung logic tạo đối tượng
- Dễ bảo trì khi thêm hình thức mới
- Khách hàng chỉ cần gọi factory, không cần biết chi tiết

### 3. **Builder Pattern**
**Mục đích:** Xây dựng đối tượng `ThanhToan` phức tạp một cách linh hoạt

**Thành phần:**
- `ThanhToanBuilder` - Class xây dựng ThanhToan

**Phương thức:**
```java
ThanhToan thanhToan = new ThanhToanBuilder()
    .maMaThanhToan("TT001")
    .maMaHoaDon("HD001")
    .maHinhThucThanhToan(hinhThuc)
    .maSoTienThanhToan(1000000)
    .maTrangThai("DA_HOAN_TAT")
    .xay();
```

**Lợi ích:**
- Dễ đọc, dễ bảo trì
- Có thể set các thuộc tính tùy chọn
- Validation được thực hiện trước khi tạo object
- Immutable object đảm bảo tính toàn vẹn

### 4. **Immutable Pattern**
**Mục đích:** Đảm bảo dữ liệu thanh toán không bị thay đổi sau khi tạo

**Đặc điểm:**
- Tất cả thuộc tính là `final`
- Không có setter methods
- Chỉ có getter methods

## Cách Sử Dụng

### Ví dụ 1: Tạo hình thức thanh toán
```java
HinhThucThanhToan tienMat = ThanhToanFactory.taoHinhThucThanhToan("tien_mat");
HinhThucThanhToan theCredit = ThanhToanFactory.taoHinhThucThanhToan("the_credit", 
                                                                      "4532111111111111", "123");
```

### Ví dụ 2: Tạo đối tượng thanh toán
```java
ThanhToan thanhtoan = new ThanhToanBuilder()
    .maMaThanhToan("TT001")
    .maMaHoaDon("HD001")
    .maHinhThucThanhToan(tienMat)
    .maSoTienThanhToan(1000000)
    .maTrangThai("DA_HOAN_TAT")
    .xay();
```

### Ví dụ 3: Thực hiện thanh toán
```java
try {
    boolean result = thanhtoan.thucHienThanhToan();
    System.out.println("Kết quả: " + (result ? "Thành công" : "Thất bại"));
} catch (Exception e) {
    System.err.println("Lỗi: " + e.getMessage());
}
```

## Lợi Ích Của Cấu Trúc Này

1. **Dễ mở rộng:** Thêm hình thức thanh toán mới chỉ cần tạo class mới implement HinhThucThanhToan
2. **Dễ bảo trì:** Mỗi class có trách nhiệm riêng (Single Responsibility)
3. **An toàn:** Immutable object đảm bảo dữ liệu không bị thay đổi ngoài ý muốn
4. **Dễ test:** Mỗi component có thể test độc lập
5. **Linh hoạt:** Có thể thay đổi hình thức thanh toán tại runtime
6. **Rõ ràng:** Code dễ đọc và hiểu được ý định

## Chạy Demo

Để chạy ví dụ:
```bash
javac *.java
java ThanhToanDemo
```

## Kết Quả Dự Kiến

```
===== 1. Factory Pattern - Tạo hình thức thanh toán =====

===== 2. Builder Pattern - Xây dựng đối tượng ThanhToan =====

===== 3. Strategy Pattern - Thực hiện thanh toán =====

Thông tin thanh toán 1: ThanhToan{maThanhToan='TT001', maHoaDon='HD001', hinhThucThanhToan=Tiền mặt, soTienThanhToan=1000000.0, trangThai='DA_HOAN_TAT', thoiGianThanhToan=...}
Thanh toán tiền mặt: 1000000.0 VND
Kết quả: Thành công

... (chi tiết khác)
```
