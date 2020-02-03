import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Window extends JPanel implements ActionListener, ChangeListener, ItemListener {

    Ground ground;
    JButton FireButton;
    JTextField power;
    JTextField angle;
    JLabel angleLabel;
    JLabel PowerLabel;
    JPanel panel;
    JSlider angleSlider;
    JButton buyButton;
    JFrame buyFrame;
    Buy buyBullet;
    final int BOARDWIDTH = 960 + 2;
    final int BOARDHEIGHT = 640 + 17;
    final int TANKLOOLEWIDTH = 50;
    final int TANKLOOLEHEIGHT = 10;

    int rocketType;

    public boolean animating = false;
    private long animStartTime;
    public int explosionX, explosionY;

    boolean coinMark[][] = new boolean[BOARDHEIGHT][BOARDWIDTH];


    public Window() {

        initWin();
    }

    private void initWin() {
        setLayout(new BorderLayout());

        ground = new Ground();
        panel = new JPanel();
        panel.setBackground(new Color (51, 52, 52) );

        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JPanel buyPanel = new JPanel();
        buyPanel.setBackground(new Color (51, 52, 52));
        Icon buyIcon = new ImageIcon ("images/buyButton.png");
        buyButton = new JButton(buyIcon);
        buyButton.setBackground(new Color (51, 52, 52));
        buyButton.setActionCommand("buyButton");
        buyButton.addActionListener(this);
        buyPanel.add(buyButton);
        panel.add (buyPanel);

        JPanel centerPanel = new JPanel ();
        centerPanel.setBackground(new Color (51, 52, 52));
        PowerLabel = new JLabel("Power:");
        PowerLabel.setForeground(Color.white);
        centerPanel.add (PowerLabel);
        panel.add (centerPanel);


        power = new JTextField("0", 3);
        power.setBackground(Color.black);
        power.setForeground(Color.white);
        power.addActionListener(this);
        centerPanel.add (power);
        panel.add (centerPanel);


        JPanel sliderPanel = new JPanel();
        sliderPanel.setBackground(new Color(51, 52, 52));
        angleSlider = new JSlider(JSlider.HORIZONTAL, 0, 180, 0);
        angleSlider.addChangeListener(this);
        angleSlider.setName("angle");
        sliderPanel.add (angleSlider);

        String[] rockets = new String[]{"Type1", "Type2", "Type3"};
        JComboBox rocketBox = new JComboBox(rockets);
        rocketBox.setActionCommand("box");
        rocketBox.addActionListener(this);
        sliderPanel.add(rocketBox);





        panel.add (sliderPanel);



        JPanel anglePanel = new JPanel();
        anglePanel.setBackground(new Color(51, 52, 52));
        angleLabel = new JLabel("Angle:");
        angleLabel.setForeground(Color.white);
        anglePanel.add(angleLabel);
        panel.add(anglePanel);

        angle = new JTextField("0", 3);
        angle.setBackground(Color.black);
        angle.setForeground(Color.white);
        angle.addActionListener(this);
        anglePanel.add(angle);
        panel.add(anglePanel);

        JPanel firePanel = new JPanel();
        firePanel.setBackground(new Color (51, 52, 52));
        Icon fireIcon = new ImageIcon ("images/fireButton.png");
        FireButton = new JButton(fireIcon);
        FireButton.setBackground (new Color (51, 52, 52));
        FireButton.setActionCommand("fire");
        FireButton.addActionListener(this);
        firePanel.add (FireButton);
        panel.add (firePanel);

        buyFrame = new JFrame();
        buyBullet = new Buy(this);

        add(panel, BorderLayout.SOUTH);
        add(ground, BorderLayout.CENTER);

    }


    public void actionPerformed (ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "fire") {
            if (ground.isTwoPlayers == true || ground.whichPlayer == false) {
                shoot(Integer.parseInt(this.power.getText()), Integer.parseInt(this.angle.getText()));
                repaint();
            }
            else {
                computerShoot();
                repaint();
            }
            /*if (ground.whichPlayer == false) {
                angleSlider.setValue(180);
            }
            if (ground.whichPlayer == true) {
                angleSlider.setValue(0);
            }*/
        }

        else if (actionEvent.getActionCommand() == "buyButton") {
            buyBullet.isActive = true;
            buyFrame.setTitle("Buy");
            buyFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            buyFrame.setSize(350, 300);
            buyFrame.setResizable(false); //User can't resize the screen
            buyFrame.setLocationRelativeTo(null);
            buyFrame.add (buyBullet);
            buyFrame.setVisible(true);
        }
    }



    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JSlider source  = (JSlider)changeEvent.getSource() ;
        if(source.getName() == "angle") {

            if (ground.whichPlayer == false)
                ground.panther.setLooleAngle(angleSlider.getValue());
            else
                ground.tiger.setLooleAngle(angleSlider.getValue());

            angle.setText("" + this.angleSlider.getValue());
            repaint();
        }

    }


    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getStateChange() == ItemEvent.SELECTED) {

        }
    }



    public void shoot (int pow, int angle) {
        ground.cannon1 = new Cannon1();
        if (ground.whichPlayer == false) {
            ground.cannon1.setX0 (ground.panther.x + 5 + (int) (Math.cos(Math.toRadians(ground.panther.getLooleAngle())) * TANKLOOLEWIDTH));
            ground.cannon1.setY0(ground.panther.y - 35 - (int) (Math.sin(Math.toRadians(ground.panther.getLooleAngle())) * TANKLOOLEHEIGHT));
            System.out.println(ground.cannon1.x0 + "\t" + ground.cannon1.y0);
            ground.cannon1.setSpeed(pow);
            ground.cannon1.setAngle(angle);
            ground.cannon1.setShootTime(System.currentTimeMillis());
            power.setText("0");
        }
        else {
            ground.cannon1.setX0 (ground.tiger.x -5 + (int) (Math.cos(Math.toRadians(ground.tiger.getLooleAngle())) * TANKLOOLEWIDTH));
            ground.cannon1.setY0(ground.tiger.y -35- (int) (Math.sin(Math.toRadians(ground.tiger.getLooleAngle())) * TANKLOOLEHEIGHT));
            ground.cannon1.setSpeed(pow);
            ground.cannon1.setAngle(angle);
            ground.cannon1.setShootTime(System.currentTimeMillis());
            power.setText("0");
        }
    }




    public void computerShoot () {
        ground.cannon1 = new Cannon1();
        //Solve the Problem here!!!
    }



    boolean isCoin (int x, int y) {
        int tmpx = x/ground.xpix , tmpy = y/ground.ypix;
        if (tmpx >= 0 && tmpy >=0 && tmpx < BOARDWIDTH && tmpy < BOARDHEIGHT && coinMark[tmpy][tmpx] == false) {
                    coinMark[tmpx][tmpy] = true;
                    return true;
                }
        return false;
    }


    void resetCoinMark () {
        for (int i = 0; i < BOARDWIDTH; i++)
            for (int j = 0; j < BOARDHEIGHT; j++)
                coinMark[j][i] = false;
    }


    private void playAnimation() throws IOException
    {
        BufferedImage exp = ImageIO.read(new File("images/explosion_anim.png")) ;
        //public Animation(BufferedImage animImage, int frameWidth, int frameHeight, int numberOfFrames, long frameTime, boolean loop, int x, int y, long showDelay)
        ground.expAnim    =   new Animation(exp , 134 , 134 ,12 , 45 , true , explosionX - 40, explosionY - 50, 0 );
        this.animStartTime  =   System.currentTimeMillis()  ;


    }



    public static void main(String[] args) throws IOException {

        Window window = new Window();

        JFrame gameMenu = new JFrame();
        gameMenu.setTitle("Menu");
        gameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameMenu.setSize(550, 680); //960, 1248
        gameMenu.setResizable(false); //User can't resize the screen
        gameMenu.setLocationRelativeTo(null); //Center of the screen
        Menu menu = new Menu(window);
        gameMenu.add(menu);
        gameMenu.setVisible(true);

        Buy buyBullet = window.buyBullet;

        long timefromFiring = 0;

        while (true) {

            if (menu.isActive == false) {
                gameMenu.setVisible(false);
                gameMenu.dispose();
            }
            if (menu.mapSelection.isActive == false) {
                menu.gameFrame.setVisible(false);
                menu.gameFrame.dispose();
            }

            if (menu.playerSelection.isActive == false) {
                menu.playerFrame.setVisible(false);
                menu.playerFrame.dispose();
            }

            if (menu.playerSelection.difficultySelection.isActive == false) {
                menu.playerSelection.difficultyFrame.setVisible(false);
                menu.playerSelection.difficultyFrame.dispose();
            }

            if (buyBullet.isActive == false) {
                window.buyFrame.setVisible(false);
                window.buyFrame.dispose();
            }

            if (window.animating == true) {
                if (System.currentTimeMillis() - timefromFiring >= 540) {
                    window.animating = false;
                    window.ground.expAnim = null;
                    if (window.ground.whichPlayer == false)
                        window.angleSlider.setValue(window.ground.panther.getLooleAngle());
                    else
                        window.angleSlider.setValue(window.ground.tiger.getLooleAngle());
                    window.ground.windSpeed = window.ground.random.nextInt() % 40;
                    if (window.ground.random.nextInt() % 2 == 0)
                        window.ground.windSpeed *= -1;
                    window.resetCoinMark();

                }

                window.repaint();


            } else {
                if (window.ground.cannon1 != null) {
                    window.ground.cannon1.move(window.ground.windSpeed);
                    if (window.ground.cannon1.getX() > window.BOARDWIDTH || window.ground.cannon1.getX() < 0) {
                        window.ground.windSpeed = window.ground.random.nextInt() % 40;
                        if (window.ground.random.nextInt() % 2 == 0)
                            window.ground.windSpeed *= -1;
                        window.resetCoinMark();
                        window.ground.cannon1 = null;
                        if (window.ground.whichPlayer == false)
                            window.angleSlider.setValue(window.ground.panther.getLooleAngle());
                        else
                            window.angleSlider.setValue(window.ground.tiger.getLooleAngle());
                        window.ground.whichPlayer = !window.ground.whichPlayer;

                    }



                    else if (window.ground.cannon1.getX() > window.ground.panther.getX() && window.ground.cannon1.getX() < window.ground.panther.getX() + window.ground.PANTHER_WIDTH
                            && window.ground.cannon1.getY() > window.ground.panther.getY() && window.ground.cannon1.getY() < window.ground.panther.getY() + window.ground.PANTHER_HEIGHT) {

                        window.explosionX = window.ground.cannon1.getX();
                        window.explosionY = window.ground.cannon1.getY();

                        window.playAnimation();
                        window.animating = true;
                        timefromFiring = System.currentTimeMillis();
                        window.ground.panther.hp -= 10;
                        window.ground.cannon1 = null;
                        window.ground.whichPlayer = !window.ground.whichPlayer;
                    }




                    else if (window.ground.cannon1.getX() > window.ground.tiger.getX() && window.ground.cannon1.getX() < window.ground.tiger.getX() + window.ground.TIGER_WIDTH
                            && window.ground.cannon1.getY() > window.ground.tiger.getY() && window.ground.cannon1.getY() < window.ground.tiger.getY() + window.ground.TIGER_HEIGHT) {

                        window.explosionX = window.ground.cannon1.getX();
                        window.explosionY = window.ground.cannon1.getY();

                        window.playAnimation();
                        window.animating = true;
                        timefromFiring = System.currentTimeMillis();
                        window.ground.tiger.hp -= 10;
                        window.ground.cannon1 = null;
                        window.ground.whichPlayer = !window.ground.whichPlayer;
                    }




                    else if (window.ground.cannon1.getY() >= 0 && window.ground.cannon1.getX() >= 0)
                        if (window.ground.map[window.ground.cannon1.getY() / window.ground.xpix][window.ground.cannon1.getX() / window.ground.ypix] == '0'
                                || window.ground.map[window.ground.cannon1.getY() / window.ground.xpix][window.ground.cannon1.getX() / window.ground.ypix] == '1'
                                || window.ground.map[window.ground.cannon1.getY() / window.ground.xpix][window.ground.cannon1.getX() / window.ground.ypix] == '2'
                                || window.ground.map[window.ground.cannon1.getY() / window.ground.xpix][window.ground.cannon1.getX() / window.ground.ypix] == '3'
                                || window.ground.map[window.ground.cannon1.getY() / window.ground.xpix][window.ground.cannon1.getX() / window.ground.ypix] == '4') {
                            window.explosionX = window.ground.cannon1.getX() - 7;
                            window.explosionY = window.ground.cannon1.getY() - 20;
                            window.playAnimation();
                            window.animating = true;
                            timefromFiring = System.currentTimeMillis();
                            window.ground.cannon1 = null;
                            window.ground.whichPlayer = !window.ground.whichPlayer;
                        }
                    if (window.ground.cannon1 != null && window.isCoin(window.ground.cannon1.getX(), window.ground.cannon1.getY())) {
                        if (window.ground.whichPlayer == false)
                            window.ground.panther.money += 20;
                        else
                            window.ground.tiger.money += 20;
                    }
                }



                window.repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                }
            }
        }
    }
}
