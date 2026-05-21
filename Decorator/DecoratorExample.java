package Decorator;

public class DecoratorExample {
    
    public static void main(String[] args) {
        IPaymentGateway bankGateway = new BankPaymentGateway();
        IPaymentGateway pg = new BankPaymentGatewayDecorator(bankGateway);
        IPaymentGateway validatedPg = new ValidationDecorator(pg);
        IPaymentGateway loggedPg = new LoggingDecorator(validatedPg);
        IPaymentGateway invoicedPg = new InvoiceDecorator(loggedPg);
        double amount = 100.0;
        invoicedPg.processPayment(amount);


    }
}

    // ==========================================
    // KHUNG CORE VÀ CÁC DECORATOR GỐC (Giữ nguyên)
    // ==========================================

    interface IPaymentGateway {
        boolean processPayment(double amount);
    }

    class BankPaymentGateway implements IPaymentGateway {
        @Override
        public boolean processPayment(double amount) {
            System.out.println("   -> [Core Bank] Đang trừ tiền tài khoản: $" + amount);
            return true;
        }
    }

    class BankPaymentGatewayDecorator implements IPaymentGateway {
        private IPaymentGateway wrappedGateway;
        public BankPaymentGatewayDecorator(IPaymentGateway gateway) { this.wrappedGateway = gateway; }

        @Override
        public boolean processPayment(double amount) {
            return wrappedGateway.processPayment(amount);
        }
    }

    class ValidationDecorator extends BankPaymentGatewayDecorator {
        public ValidationDecorator(IPaymentGateway gateway) { super(gateway); }

        @Override
        public boolean processPayment(double amount) {
            if (amount <= 0) {
                System.out.println("> [Valid] Thất bại: Số tiền không hợp lệ!");
                return false;
            }
            System.out.println("> [Valid] Thành công: Số tiền hợp lệ.");
            return super.processPayment(amount);
        }
    }

        class LoggingDecorator extends BankPaymentGatewayDecorator {
            public LoggingDecorator(IPaymentGateway gateway) { super(gateway); }
            
            @Override
            public boolean processPayment(double amount) {
                System.out.println("> [Log] Bắt đầu giao dịch...");
                boolean result = super.processPayment(amount);
                System.out.println("> [Log] Kết thúc giao dịch.");
                return result;
            }
        }

        class InvoiceDecorator extends BankPaymentGatewayDecorator {
            public InvoiceDecorator(IPaymentGateway gateway) { super(gateway); }

            @Override
            public boolean processPayment(double amount) {
                boolean result = super.processPayment(amount);
                System.out.println("> [Invoice] Đã xuất hóa đơn cho khách hàng.");
                return result;
            }
        }
    