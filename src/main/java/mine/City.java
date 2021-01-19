package mine;

public class City {
    int label;
    double x;
    double y;

    public City(int label, double x, double y) {
        super();
        this.label = label;
        this.x = x;
        this.y = y;
    }

    public int getLabel() {
        return label;
    }

    public double distance(City that) {
//        System.out.println("cost from " + this.label + " to " + that.label + " = " + Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)));
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
    }

}
