import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class DifficultySelection extends JPanel implements ActionListener {

    Window window;

    Boolean isActive;

    Random random;

    JButton easyButton;
    JButton hardButton;
    JButton mediumButton;

    ImageIcon easyIcon;
    ImageIcon hardIcon;
    ImageIcon mediumIcon;

    public DifficultySelection (Window windowp) {

        this.window = windowp;

        isActive = true;

        random = new Random();

        easyIcon = new ImageIcon("images/easyButton.png");
        mediumIcon = new ImageIcon("images/mediumButton.png");
        hardIcon = new ImageIcon("images/hardButton.png");

        setBackground(new Color(91, 24, 17));
        setLayout(null);

        easyButton = new JButton(easyIcon);
        easyButton.setBounds(100, 90, 350, 130);
        add(easyButton);
        easyButton.setActionCommand("easy");
        easyButton.addActionListener(this);

        mediumButton = new JButton(mediumIcon);
        mediumButton.setBounds(100, 240, 350, 130);
        add(mediumButton);
        mediumButton.setActionCommand("medium");
        mediumButton.addActionListener(this);

        hardButton = new JButton(hardIcon);
        hardButton.setBounds(100, 390, 350, 130);
        add(hardButton);
        hardButton.setActionCommand("hard");
        hardButton.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "easy") {

            window.ground.zaribekhata = (random.nextDouble() % 0.1) + 0.5;

            JFrame gameFrame = new JFrame();
            gameFrame.setTitle("BattleTank");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setSize(960 + 2, 640 + 17); //960, 1248
            gameFrame.setResizable(false); //User can't resize the screen
            gameFrame.setLocationRelativeTo(null); //Center of the screen
            gameFrame.add(window);
            gameFrame.setVisible(true);
            isActive = false;
        }
        else if (actionEvent.getActionCommand() == "medium") {

            window.ground.zaribekhata = (random.nextDouble() % 0.1) + 0.7;

            JFrame gameFrame = new JFrame();
            gameFrame.setTitle("BattleTank");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setSize(960 + 2, 640 + 17); //960, 1248
            gameFrame.setResizable(false); //User can't resize the screen
            gameFrame.setLocationRelativeTo(null); //Center of the screen
            gameFrame.add(window);
            gameFrame.setVisible(true);
            isActive = false;

        }
        else if (actionEvent.getActionCommand() == "hard") {

            window.ground.zaribekhata = (random.nextDouble() % 0.1) + 0.9;

            JFrame gameFrame = new JFrame();
            gameFrame.setTitle("BattleTank");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setSize(960 + 2, 640 + 17); //960, 1248
            gameFrame.setResizable(false); //User can't resize the screen
            gameFrame.setLocationRelativeTo(null); //Center of the screen
            gameFrame.add(window);
            gameFrame.setVisible(true);
            isActive = false;

        }
    }
}