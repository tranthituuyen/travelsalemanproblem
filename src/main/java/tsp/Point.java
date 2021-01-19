package tsp;

public class Point {
    double x;
    double y;

    public Point(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public Point() {
        super();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double distance(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

}
