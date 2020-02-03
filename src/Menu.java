import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Menu extends JPanel implements ActionListener {

    Window window;

    Boolean isActive;

    JButton newGameButton;
    JButton exitButton;
    JButton selectMapButton;

    ImageIcon newGameIcon;
    ImageIcon exitIcon;
    ImageIcon selectMapIcon;

    JFrame gameFrame;
    JFrame playerFrame;

    PlayerSelection playerSelection;
    MapSelection mapSelection;

    public Menu(Window windowp) {

        this.window = windowp;

        isActive = true;

        playerFrame = new JFrame();
        gameFrame = new JFrame();

        mapSelection = new MapSelection(window);
        playerSelection = new PlayerSelection(window);

        newGameIcon = new ImageIcon("images/newGameButton.png");
        exitIcon = new ImageIcon("images/exitButton.png");
        selectMapIcon = new ImageIcon("images/selectMapButton.png");

        setBackground(new Color(91, 24, 17));
        setLayout(null);

        newGameButton = new JButton(newGameIcon);
        newGameButton.setBounds(100, 150, 350, 130);
        add(newGameButton);
        newGameButton.setActionCommand("newGame");
        newGameButton.addActionListener(this);

        selectMapButton = new JButton(selectMapIcon);
        selectMapButton.setBounds(100, 300, 350, 130);
        add(selectMapButton);
        selectMapButton.setActionCommand("selectMap");
        selectMapButton.addActionListener(this);


        exitButton = new JButton(exitIcon);
        exitButton.setBounds(100, 450, 350, 130);
        add(exitButton);
        exitButton.setActionCommand("exit");
        exitButton.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "exit") {
            this.isActive = false;
        }
        else if (actionEvent.getActionCommand() == "newGame") {
            playerSelection.isActive = true;
            playerFrame.setTitle("Select Player");
            playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            playerFrame.setSize(550, 500);
            playerFrame.setResizable(false); //User can't resize the screen
            playerFrame.setLocationRelativeTo(null); //Center of the screen
            playerFrame.add(playerSelection);
            playerFrame.setVisible(true);
            this.isActive = false;
        }

        else if (actionEvent.getActionCommand() == "selectMap") {
            mapSelection.isActive = true;
            gameFrame.setTitle("Select Map");
            gameFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            gameFrame.setSize(550, 500);
            gameFrame.setResizable(false); //User can't resize the screen
            gameFrame.setLocationRelativeTo(null); //Center of the screen
            gameFrame.add(mapSelection);
            gameFrame.setVisible(true);
        }
    }


}