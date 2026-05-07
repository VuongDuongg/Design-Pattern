import java.util.*;

public class PrototypeEx {
    public static void main(String[] args) {
        Circle circle = new Circle();
        System.out.printf("Circle: Perimeter = %.2f, Border Thickness = %d%n",
                circle.perimeter(), circle.getBorder().getThickness());

        Rectangle rec = new Rectangle();
        Square sq = new Square();

        ShapeRegistry reg = new ShapeRegistry();
        reg.registerShape("circle", circle);
        reg.registerShape("rectangle", rec);
        reg.registerShape("square", sq);

        List<Shape> clonedShapes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            clonedShapes.add(reg.getShape("circle"));
            clonedShapes.add(reg.getShape("rectangle"));
            clonedShapes.add(reg.getShape("square"));
        }
        clonedShapes.get(25).getBorder().setThickness(5);

        for (int i = 0; i < clonedShapes.size(); i++) {
            System.out.printf("Shape %d: Perimeter = %.2f, Border Thickness = %d%n",
                    i + 1,
                    clonedShapes.get(i).perimeter(),
                    clonedShapes.get(i).getBorder().getThickness());
        }
    }

    // ─── Border ───────────────────────────────────────────────────────────────

    static class Border {
        private int thickness;
        private int color;

        public int getThickness() { return thickness; }
        public void setThickness(int thickness) { this.thickness = thickness; }

        public int getColor() { return color; }
        public void setColor(int color) { this.color = color; }

        public Border copy() {
            Border b = new Border();
            b.thickness = this.thickness;
            b.color = this.color;
            return b;
        }
    }

    // ─── Shape (abstract) ─────────────────────────────────────────────────────

    abstract static class Shape {
        protected double x;
        protected double y;
        protected double color;
        protected Border border;

        public Shape() {
            x = 0;
            y = 0;
            color = 0;
            border = new Border();
        }

        public Shape(Shape other) {
            this.x = other.x;
            this.y = other.y;
            this.color = other.color;
            this.border = other.border.copy();
        }

        public Border getBorder() { return border; }
        public void setBorder(Border border) { this.border = border; }

        public double getX() { return x; }
        public void setX(double x) { this.x = x; }

        public double getY() { return y; }
        public void setY(double y) { this.y = y; }

        public double getColor() { return color; }
        public void setColor(double color) { this.color = color; }

        public abstract Shape copy();
        public abstract double perimeter();
    }

    // ─── Circle ───────────────────────────────────────────────────────────────

    static class Circle extends Shape {
        private double radius;

        public Circle() {
            super();
            radius = 0;
        }

        public Circle(Circle other) {
            super(other);
            this.radius = other.radius;
        }

        public double getRadius() { return radius; }
        public void setRadius(double radius) { this.radius = radius; }

        @Override
        public Shape copy() { return new Circle(this); }

        @Override
        public double perimeter() { return 2 * Math.PI * radius; }
    }

    // ─── Rectangle ────────────────────────────────────────────────────────────

    static class Rectangle extends Shape {
        private double width;
        private double height;

        public Rectangle() {
            super();
            width = 0;
            height = 0;
        }

        public Rectangle(Rectangle other) {
            super(other);
            this.width = other.width;
            this.height = other.height;
        }

        public double getWidth() { return width; }
        public void setWidth(double width) { this.width = width; }

        public double getHeight() { return height; }
        public void setHeight(double height) { this.height = height; }

        @Override
        public Shape copy() { return new Rectangle(this); }

        @Override
        public double perimeter() { return 2 * (width + height); }
    }

    // ─── Square ───────────────────────────────────────────────────────────────

    static class Square extends Shape {
        private double side;

        public Square() {
            super();
            side = 0;
        }

        public Square(Square other) {
            super(other);
            this.side = other.side;
        }

        public double getSide() { return side; }
        public void setSide(double side) { this.side = side; }

        @Override
        public Shape copy() { return new Square(this); }

        @Override
        public double perimeter() { return 4 * side; }
    }

    // ─── ShapeRegistry ────────────────────────────────────────────────────────

    static class ShapeRegistry {
        private final Map<String, Shape> shapes = new HashMap<>();

        public void registerShape(String name, Shape s) {
            shapes.put(name, s);
        }

        public Shape getShape(String name) {
            if (shapes.containsKey(name)) {
                return shapes.get(name).copy();
            }
            throw new IllegalArgumentException("Shape '" + name + "' not found in registry.");
        }
    }
}
