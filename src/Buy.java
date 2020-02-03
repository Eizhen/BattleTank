import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Buy extends JPanel implements ActionListener{

    Window window;
    Boolean isActive;
    JButton buyButton;
    ImageIcon buyIcon;
    JLabel yourMoneyLabel;
    JLabel priceLabel;
    final int PRICE = 50;
    int money;

    public Buy (Window windowp) {

        window = windowp;

        isActive = true;

        if (window.ground.whichPlayer == false)
            money = window.ground.panther.money;
        else
            money = window.ground.tiger.money;

        buyIcon = new ImageIcon("images/buy2Button.png");

        setBackground(new Color(9, 9, 57));
        setLayout(null);

        priceLabel = new JLabel("PRICE: " + PRICE);
        priceLabel.setBackground(new Color(9, 9, 57));
        priceLabel.setForeground(new Color(222, 217, 255));
        priceLabel.setBounds(20, 10, 100, 100);
        add(priceLabel);


        yourMoneyLabel = new JLabel("Your Money is: " + money);
        yourMoneyLabel.setBackground(new Color(9, 9, 57));
        yourMoneyLabel.setForeground(new Color(222, 217, 255));
        yourMoneyLabel.setBounds(20, 30, 200, 100);
        add(yourMoneyLabel);


        buyButton = new JButton(buyIcon);
        buyButton.setBounds(76, 140, 200, 74);
        add(buyButton);
        buyButton.setActionCommand("buy");
        buyButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand() == "buy") {
            if (money >= PRICE) {
                if (window.ground.whichPlayer ==false )
                    window.ground.panther.howManyRocketsType2++;
                else
                    window.ground.tiger.howManyRocketsType2++;
            }
            isActive = false;
        }
    }
}
