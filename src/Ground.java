import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;


public class Ground extends JPanel {

    public static enum States {Main_Menu, Gameover, Playing, BuyMode};
    public static States gameState;
    public double zaribekhata;
    private Image Board;
    private Image cannon1Image;
    private Image coinImage;
    private Image menuImage;
    public Image pantherImage;
    public static String mapInput = "map1.in";
    public Image tigerImage;
    private BufferedImage dirt;
    private TexturePaint dirtTexture;
    public Tank panther;
    public Tank tiger;
    public boolean isTwoPlayers;
    public boolean whichPlayer;
    public static Cannon1 cannon1;
    final static int MaxSize = 500;
    public int windSpeed;
    public char [][] map = new char [MaxSize][MaxSize];
    public static Point [] coins = new Point[MaxSize];
    int coinCounter = 0;
    int height, width;
    public Random random;
    int ypix, xpix;
    public Animation expAnim;
    final int TIGER_WIDTH = 65;
    final int TIGER_HEIGHT = 34;
    final int PANTHER_WIDTH = 85;
    final int PANTHER_HEIGHT = 40;


    public Ground () {

        //this.gameState = gameState;
        initGround ();
    }

    private void initGround () {
        panther = new Tank();
        tiger = new Tank();
        tiger.setLooleAngle(180);
        loadImage();
        try {
            Scanner inputMap = new Scanner(new File(mapInput));
            height = inputMap.nextInt();
            width = inputMap.nextInt();
            inputMap.nextLine();
            int i = 0;
            while (inputMap.hasNextLine()) {
                String line = inputMap.nextLine();
                for (int j = 0; j < line.length(); j++)
                    map[i][j] = line.charAt(j);
                i++;
            }
        }
        catch (FileNotFoundException ex) {}
        whichPlayer = false;
        random = new Random();
        windSpeed = random.nextInt() % 40;
    }


    public void draw(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        drawPlaying (g);

    }



    public void drawPlaying (Graphics g) {

        ypix = 960 / width;
        xpix = 640 / height;
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(Board, 0,  0, null);

        ///if (cannon1 != null)
           // System.out.println("cannon: " + cannon1.getX() + " " + cannon1.getY());

        coinCounter = 0;

       // dirtTexture = new TexturePaint(dirt, new Rectangle(0, 0, 962, 657));//962, 657
       // g2d.setPaint(dirtTexture);
        g2d.setColor(new Color(103, 75, 17));
        for (int i = 0, y = 0; i < height; i++, y += ypix)
            for (int j = 0, x = 0; j < width; j++, x += xpix) {
                if (map[i][j] == '0')
                    g2d.fillRect(x, y, xpix, ypix);
                else if (map[i][j] == '1') {
                    GeneralPath tri = new GeneralPath();
                    tri.moveTo(x, y);
                    tri.lineTo(x, y + ypix);
                    tri.lineTo(x + xpix, y + ypix);
                    tri.closePath();
                    g2d.fill(tri);
                }
                else if (map[i][j] == '2') {
                    GeneralPath tri = new GeneralPath();
                    tri.moveTo(x + xpix, y);
                    tri.lineTo(x + xpix, y + ypix);
                    tri.lineTo(x, y + ypix);
                    tri.closePath();
                    g2d.fill(tri);
                }
                else if (map[i][j] == '3') {
                    GeneralPath tri = new GeneralPath();
                    tri.moveTo(x, y);
                    tri.lineTo(x + xpix, y);
                    tri.lineTo(x, y + ypix);
                    tri.closePath();
                    g2d.fill(tri);
                }
                else if (map[i][j] == '4') {
                    GeneralPath tri = new GeneralPath();
                    tri.moveTo(x, y);
                    tri.lineTo(x + xpix, y);
                    tri.lineTo(x + xpix, y + ypix);
                    tri.closePath();
                    g2d.fill(tri);
                }
                else if (map[i][j] == 't')
                    panther.setCoor(x, y);
                else if (map[i][j] == 'T')
                    tiger.setCoor(x, y);
                else if (map[i][j] == 'c')
                    coins[coinCounter++] = new Point (x, y);

            }

        for (int i = 0; i < coinCounter; i++)
            g2d.drawImage(coinImage, coins[i].x, coins[i].y, null);

        g2d.drawImage(pantherImage, panther.getX() - 20, panther.getY() - 28, null);
        g2d.drawImage(tigerImage, tiger.getX() - 38, tiger.getY() - 26, null);

        //Panther Loole
        AffineTransform def = g2d.getTransform();
        AffineTransform tr = AffineTransform.getTranslateInstance((panther.getX() + 5) , (panther.getY() - 17)) ;
        tr.rotate(Math.toRadians(- panther.getLooleAngle()));
        g2d.setTransform(tr);
        g2d.drawImage(panther.getLooleImage(), 0, 0, null);
        g2d.setTransform(def);

        //Tiger Loole
        tr = AffineTransform.getTranslateInstance((tiger.getX() - 15) , (tiger.getY() - 10));
        tr.rotate(Math.toRadians(- tiger.getLooleAngle()));
        g2d.setTransform(tr);
        g2d.drawImage(tiger.getLooleImage(), 0, 0, null);
        g2d.setTransform(def);


        if (cannon1 != null) {
            g2d.drawImage(cannon1Image, cannon1.getX(), cannon1.getY(), 10, 10, null);
            Toolkit.getDefaultToolkit().sync();
        }

        if (expAnim != null)
        {
            expAnim.Draw(g2d);
        }

        g2d.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 20));//Georgia, Serif, Franklin Gothic Medium
        g2d.setColor(new Color(146, 27, 15));
        g2d.drawString("Panther: " + panther.getHP(), 20, 30);
        g2d.drawString("Tiger: " + tiger.getHP(), 830, 30);

        g2d.setFont(new Font("Georgia", Font.BOLD, 20));
        g2d.setColor(new Color(197, 152, 29));
        g2d.drawString(panther.getMoney() + "$", 21, 57);
        g2d.drawString(tiger.getMoney() + "$", 832, 57);

        g2d.setFont(new Font("Georgia", Font.BOLD, 15));
        g2d.setColor(new Color(128, 24, 99));
        g2d.drawString("Rocket2: " + panther.howManyRocketsType2, 21, 83);
        g2d.drawString("Rocket2: " + tiger.howManyRocketsType3, 834, 83);
        g2d.drawString("Rocket3: " + panther.howManyRocketsType3, 21, 103);
        g2d.drawString("Rocket3: " + tiger.howManyRocketsType3, 834, 103);

        g2d.setFont(new Font("Serif", Font.BOLD, 15));
        g2d.setColor(new Color(112, 41, 108));
        if (windSpeed >= 0) {
            g2d.drawString("Wind Speed: " + windSpeed, 400, 30);
            g2d.drawString("-->", 540, 30);
        }

        else {
            g2d.drawString("Wind Speed: " + -windSpeed, 400, 30);
            g2d.drawString("<--", 540, 30);
        }


    }

    private void loadImage () {
        ImageIcon background = new ImageIcon("images/background.jpg");
        Board = background.getImage();

        ImageIcon pant = new ImageIcon("images/panther.png");
        pantherImage = pant.getImage();

        ImageIcon tig = new ImageIcon("images/tiger.png");
        tigerImage = tig.getImage();

        ImageIcon menuI = new ImageIcon("images/menu.jpg");
        menuImage = menuI.getImage();

        coinImage = new ImageIcon("images/coin.png").getImage();

        ImageIcon can1I = new ImageIcon ("images/cannon.png");
        cannon1Image = can1I.getImage();

        panther.setLooleImage (new ImageIcon ("images/pantherLoole.png").getImage());

        tiger.setLooleImage (new ImageIcon("images/tigerLoole.png").getImage() );

        try {
            dirt = ImageIO.read(new File("images/ground.jpg"));
        }
        catch (IOException ex) {}

    }

    /*public int getBoardHeight () {
        return height;
    }

    public int getBoardWidth () {
        return width;
    }

    public boolean getPlayer () { return player; }

    public void setPlayer (boolean b) { player = b; }
*/
    @Override
    public void paintComponent(Graphics g) {
        long time = System.currentTimeMillis();
        super.paintComponent(g);
        draw(g);
        //System.out.println(System.currentTimeMillis()-time);
    }

}
