package Adapter;
// 1. Target Interface: Giao diện chuẩn mà hệ thống của bạn tin dùng
interface PaymentGateway {
    void processPayment(double amount);
}

// 2. Adaptee (Phía đối tác): Có các phương thức khác biệt
class StripePaymentGateway {
    public void processPayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through Stripe.");
    }
}

class BankPaymentGateway {
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through Bank.");
    }
}

// 3. Adapters: "Cáp chuyển đổi" để khớp Bank và Stripe vào hệ thống
class StripeAdapter implements PaymentGateway {
    private StripePaymentGateway stripeGateway;
    public StripeAdapter(StripePaymentGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }
    @Override
    public void processPayment(double amount) {
        stripeGateway.processPayment(amount);
    }
}

class BankAdapter implements PaymentGateway {
    private BankPaymentGateway bankGateway;
    public BankAdapter(BankPaymentGateway bankGateway) {
        this.bankGateway = bankGateway;
    }
    @Override
    public void processPayment(double amount) {
        bankGateway.makePayment(amount); // Chuyển đổi tên hàm ở đây
    }
}

// 4. Coin Service: Định nghĩa dịch vụ mua Coin
interface CoinService {
    void buyCoins(double amount);
}

// Lớp thực thi việc mua Coin (Dùng extends nếu CoinService là class, implements nếu là interface)
class CoinPayment implements CoinService, PaymentGateway {
    private PaymentGateway paymentGateway;

    public CoinPayment(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void buyCoins(double amount) {
        System.out.println(">>> [Coin Service] Đang chuẩn bị mua Coin...");
        paymentGateway.processPayment(amount); // Gọi qua Adapter
        System.out.println(">>> [Coin Service] Mua Coin thành công!");
    }

    @Override
    public void processPayment(double amount) {
        buyCoins(amount);
    }
}

// 5. Main Class
public class AdapterInClass {
    public static void main(String[] args) {
        // --- Tình huống 1: Mua Coin bằng Stripe ---
        StripePaymentGateway stripeStore = new StripePaymentGateway();
        PaymentGateway stripeAdapter = new StripeAdapter(stripeStore);
        
        CoinService serviceWithStripe = new CoinPayment(stripeAdapter);
        serviceWithStripe.buyCoins(50.0);

        System.out.println("\n------------------------------------\n");

        // --- Tình huống 2: Mua Coin bằng Bank ---
        BankPaymentGateway bankStore = new BankPaymentGateway();
        PaymentGateway bankAdapter = new BankAdapter(bankStore);
        
        CoinService serviceWithBank = new CoinPayment(bankAdapter);
        serviceWithBank.buyCoins(150.0);
    }
}