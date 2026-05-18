public class ApplicationNotify {
    public static void main(String[] args) {
        INotify emailNotify = new EmailNotify();
        INotify smsNotify = new SMSNotify();

        INotify loggedEmailNotify = new LoggingNotifyDecorator(emailNotify);
        INotify urgentSMSNotify = new UrgencyNotifyDecorator(smsNotify);

        loggedEmailNotify.sendNotification("This is an email notification.");
        urgentSMSNotify.sendNotification("This is an urgent SMS notification.");
    }
}

interface INotify {
    void sendNotification(String message);
}

class EmailNotify implements INotify {
    @Override
    public void sendNotification(String message) {
        System.out.println("   -> [Email] Gửi email: " + message);
    }
}

class SMSNotify implements INotify {
    @Override
    public void sendNotification(String message) {
        System.out.println("   -> [SMS] Gửi tin nhắn: " + message);
    }
}

class NotifyDecorator implements INotify {
    protected INotify wrappedNotify;
    public NotifyDecorator(INotify notify) { this.wrappedNotify = notify; }

    @Override
    public void sendNotification(String message) {
        wrappedNotify.sendNotification(message);
    }
}

class LoggingNotifyDecorator extends NotifyDecorator {
    public LoggingNotifyDecorator(INotify notify) { super(notify); }

    @Override
    public void sendNotification(String message) {
        System.out.println("> [Log] Bắt đầu gửi thông báo...");
        super.sendNotification(message);
        System.out.println("> [Log] Kết thúc gửi thông báo.");
    }
}

class UrgencyNotifyDecorator extends NotifyDecorator {
    public UrgencyNotifyDecorator(INotify notify) { super(notify); }

    @Override
    public void sendNotification(String message) {
        String urgentMessage = "[URGENT] " + message;
        super.sendNotification(urgentMessage);
    }
}


