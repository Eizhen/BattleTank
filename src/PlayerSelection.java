import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerSelection extends JPanel implements ActionListener {

    Window window;

    Boolean isActive;

    JButton onePlayerButton;
    JButton twoPlayersButton;

    ImageIcon onePlayerIcon;
    ImageIcon twoPlayersIcon;

    JFrame difficultyFrame;
    JFrame gameFrame;

    DifficultySelection difficultySelection;

    public PlayerSelection (Window windowp) {

        this.window = windowp;

        isActive = true;

        onePlayerIcon = new ImageIcon("images/onePlayerButton.png");
        twoPlayersIcon = new ImageIcon("images/twoPlayersButton.png");

        difficultyFrame = new JFrame();
        gameFrame = new JFrame();

        difficultySelection = new DifficultySelection(window);

        setBackground(new Color(91, 24, 17));
        setLayout(null);

        onePlayerButton = new JButton(onePlayerIcon);
        onePlayerButton.setBounds(100, 70, 350, 130);
        add(onePlayerButton);
        onePlayerButton.setActionCommand("onePlayer");
        onePlayerButton.addActionListener(this);

        twoPlayersButton = new JButton(twoPlayersIcon);
        twoPlayersButton.setBounds(100, 220, 350, 130);
        add(twoPlayersButton);
        twoPlayersButton.setActionCommand("twoPlayers");
        twoPlayersButton.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "twoPlayers") {
            window.ground.isTwoPlayers = true;
            gameFrame.setTitle("BattleTank");
            gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameFrame.setSize(960 + 2, 640 + 17); //960, 1248
            gameFrame.setResizable(false); //User can't resize the screen
            gameFrame.setLocationRelativeTo(null); //Center of the screen
            gameFrame.add(window);
            gameFrame.setVisible(true);
            isActive = false;
        }
        else if (actionEvent.getActionCommand() == "onePlayer") {
            difficultySelection.isActive = true;
            window.ground.isTwoPlayers = false;
            difficultyFrame.setTitle("Select Difficulty");
            difficultyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            difficultyFrame.setSize(550, 630);
            difficultyFrame.setResizable(false);
            difficultyFrame.setLocationRelativeTo(null);
            difficultyFrame.add(difficultySelection);
            difficultyFrame.setVisible(true);
            isActive = false;
        }
    }
}