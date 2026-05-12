package Adapter;

public class Shape {
    public static void main(String[] args) {
        RoundHole hole = new RoundHole(5);
        RoundPeg roundPeg = new RoundPeg(5);
        SquarePeg squarePeg = new SquarePeg(5);

        System.out.println("Round peg fits round hole: " + hole.fits(roundPeg));

        SquarePegAdapter squarePegAdapter = new SquarePegAdapter(squarePeg);
        System.out.println("Square peg fits round hole: " + hole.fits(squarePegAdapter));
    }
}

class RoundHole {
    private final double radius;

    public RoundHole(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public boolean fits(RoundPeg peg) {
        return this.getRadius() >= peg.getRadius();
    }
}

class RoundPeg {
    private final double radius;

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}

class SquarePeg {
    private final double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }
}

class SquarePegAdapter extends RoundPeg {

    public SquarePegAdapter(SquarePeg peg) {
        super(peg.getWidth() * Math.sqrt(2) / 2);
    }
}
