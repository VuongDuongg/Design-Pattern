public class BaiTap {
    public static void main(String[] args) {

        NotificationService ns = new EmailService();
        ns.Notify();
        NotificationService smsService = new SMSService();
        smsService.Notify();
        NotificationService pushService = new PushService();
        pushService.Notify();

    }
}

interface Notification {
    void SendNotice();
}

class EmailNotification implements Notification {
    @Override
    public void SendNotice() {
        System.out.println("Sending email notification.");
    }
}

class SMSNotification implements Notification {
    @Override
    public void SendNotice() {
        System.out.println("Sending SMS notification.");
    }
}

class PushNotification implements Notification {
    @Override
    public void SendNotice() {
        System.out.println("Sending push notification.");
    }
}

abstract class NotificationService {
    Notification nt;
    abstract Notification createNotification();

    public void Notify() {
        nt = createNotification();
        nt.SendNotice();
    }
}

class EmailService extends NotificationService {
    @Override
    Notification createNotification() {
        return new EmailNotification();
    }
}

class SMSService extends NotificationService {
    @Override
    Notification createNotification() {
        return new SMSNotification();
    }
}

class PushService extends NotificationService {
    @Override
    Notification createNotification() {
        return new PushNotification();
    }
}