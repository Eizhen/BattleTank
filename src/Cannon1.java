import java.awt.*;
public class Cannon1 {

    private int x, y;
    public int x0, y0;
    private int speed;
    private int angle;
    private long shootTime;
    private int deltaT;
    private Image image;
    int type = 1;

    public void setX0 (int a) {
        x0 = a;
        x = a;
    }

    public void setY0 (int b) {
        y0 = b;
        y = b;
    }

    public int getX () {
        return x;
    }

    public int getY () {
        return y;
    }

    public Image getImage() {
        return this.image;
    }

    public void setImage(Image im) {
        this.image = im;
    }

    public void setSpeed (int s) {
        speed = s;
    }

    public void setShootTime (long s) {
        shootTime = s;
    }

    public void setAngle (int a) {
        angle = a;
    }


    public void move (int wSpeed){//, boolean wichTank) {

       // if (wichTank == false) {
            if (type == 1) {
                deltaT = (int) (System.currentTimeMillis() - shootTime) / 40;
                x = x0 + (int) (deltaT * speed * (Math.cos(Math.toRadians(angle))))  + (wSpeed * deltaT) / 10;
                y = y0 - deltaT * (int) (speed * Math.sin(Math.toRadians(angle))) + (int) ((deltaT * deltaT));
            } else if (type == 2) {
                deltaT = (int) (System.currentTimeMillis() - shootTime) / 40;
                x = x0 + (int) (deltaT * (speed/2) * (Math.cos(Math.toRadians(angle))))  + (wSpeed * deltaT) / 20;
                y = y0 - deltaT * (int) ((speed/2) * Math.sin(Math.toRadians(angle))) + (int) ((deltaT * deltaT));
            }
            else {
                deltaT = (int) (System.currentTimeMillis() - shootTime) / 40;
                x = x0 + (int) (deltaT * (speed/4) * (Math.cos(Math.toRadians(angle))))  + (wSpeed * deltaT) / 30;
                y = y0 - deltaT * (int) ((speed/4) * Math.sin(Math.toRadians(angle))) + (int) ((deltaT * deltaT));
            }
        //}
        /*else {
            if (type == 1) {
                deltaT = (int) (System.currentTimeMillis() - shootTime) / 30;
                x = x0 - (int) (deltaT * speed * (Math.cos(Math.toRadians(angle)))) + (wSpeed * deltaT);
                y = y0 - deltaT * (int) (speed * Math.sin(Math.toRadians(angle))) + (int) ((deltaT * deltaT));
            }
            else if (type == 2) {

            }
        }*/
    }

}
