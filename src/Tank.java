import java.awt.*;

public class Tank {

    public int hp = 100;
    public int money = 0;
    public int x;
    public int y;
    public int looleAngle;
    public boolean isComp;
    public Image looleImage;
    public int howManyRocketsType2 = 0;
    public int howManyRocketsType3 = 0;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHP () {
        return hp;
    }

    public Image getLooleImage () {
        return looleImage;
    }

    public void setLooleImage (Image im) {
        looleImage = im;
    }

    public int getLooleAngle () {
        return looleAngle;
    }

    public void setLooleAngle (int angle) {
        looleAngle = angle;
    }

    public int getMoney () {
        return money;
    }

    public void setCoor(int x1, int y1) {
        x = x1;
        y = y1;
    }

}
