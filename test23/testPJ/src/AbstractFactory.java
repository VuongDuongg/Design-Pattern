class Client {
    private Chair chair;
    private Sofa sofa;
    private Table table;

    public Client(FurnitureFactory factory) {
        chair = factory.createChair();
        sofa = factory.createSofa();
        table = factory.createTable();
    }

    public void useFurniture() {
        chair.sitOn();
        chair.hasLegs();
        sofa.lieOn();
        sofa.hasCushions();
        table.use();
        table.hasSurface();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        // Example usage
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        Client client1 = new Client(victorianFactory);
        client1.useFurniture();

        System.out.println();

        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Client client2 = new Client(modernFactory);
        client2.useFurniture();
    }
}

interface Chair {
    void sitOn();
    void hasLegs();
}

interface Sofa {
    void lieOn();
    void hasCushions();
}

interface Table {
    void use();
    void hasSurface();
}

interface FurnitureFactory {
    Chair createChair();
    Sofa createSofa();
    Table createTable();
}

class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a Victorian chair.");
    }

    @Override
    public void hasLegs() {
        System.out.println("The Victorian chair has ornate legs.");
    }
}

class VictorianSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a Victorian sofa.");
    }

    @Override
    public void hasCushions() {
        System.out.println("The Victorian sofa has plush cushions.");
    }
}

class VictorianTable implements Table {
    @Override
    public void use() {
        System.out.println("Using a Victorian table.");
    }

    @Override
    public void hasSurface() {
        System.out.println("The Victorian table has a polished surface.");
    }
}

class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Sofa createSofa() {
        return new VictorianSofa();
    }

    @Override
    public Table createTable() {
        return new VictorianTable();
    }
}

class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a modern chair.");
    }

    @Override
    public void hasLegs() {
        System.out.println("The modern chair has sleek legs.");
    }
}

class ModernSofa implements Sofa {
    @Override
    public void lieOn() {
        System.out.println("Lying on a modern sofa.");
    }

    @Override
    public void hasCushions() {
        System.out.println("The modern sofa has firm cushions.");
    }
}

class ModernTable implements Table {
    @Override
    public void use() {
        System.out.println("Using a modern table.");
    }

    @Override
    public void hasSurface() {
        System.out.println("The modern table has a minimalist surface.");
    }
}

class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Sofa createSofa() {
        return new ModernSofa();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }
}
