package app.project.donate.ngolocator;

/**
 * Created by ArupPc on 18-03-2017.
 */

public class PointFloating {
    double x,y;
    String name;
    public void setXY(double x,double y,String name ){
        this.x = x;
        this.y=y;
        this.name = name;
    }
    public void setXY(double x,double y){
        this.x = x;
        this.y=y;

    }
    public PointFloating(){
        this.x=0.0;
        this.y=0.0;
        this.name = "";
    }
    public PointFloating(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }


    public double getX() {

        return x;
    }

    public String getName() {
        return name;
    }
    public void printAll(){
        System.out.println("NGO: "+ this.name + " Lat: " + this.x + " Long: " + this.y);
    }

}
