public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        LogisticsService ls = new RoadService();
        ls.planDelivery();
        ls = new SeaService();
        ls.planDelivery();
        ls = new AirService();
        ls.planDelivery();
        ls = new RailService();
        ls.planDelivery();
    }
}

interface Transport {
    void deliver();
}

class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("The truck is delivering cargo.");
    }
}

class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("The ship is delivering cargo.");
    }
}

class Airplane implements Transport {
    @Override
    public void deliver() {
        System.out.println("The airplane is delivering cargo.");
    }
}

class Train implements Transport {
    @Override
    public void deliver() {
        System.out.println("The train is delivering cargo.");
    }
}

abstract class LogisticsService {
    Transport tp;
    abstract Transport createTransport();

    public void planDelivery() {
        tp = createTransport();
        tp.deliver();
    }
}

class RoadService extends LogisticsService {
    @Override
    Transport createTransport() {
        return new Truck();
    }
}

class SeaService extends LogisticsService {
    @Override
    Transport createTransport() {
        return new Ship();
    }
}

class AirService extends LogisticsService {
    @Override
    Transport createTransport() {
        return new Airplane();
    }
}

class RailService extends LogisticsService {
    @Override
    Transport createTransport() {
        return new Train();
    }
}