    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.util.Scanner;

    public class MapSelection extends JPanel implements ActionListener {

        Window window;

        Boolean isActive;

        JButton map1Button;
        JButton map2Button;
        ImageIcon map1Icon;
        ImageIcon map2Icon;

        public MapSelection (Window windowp) {
            isActive = true;
            this.window = windowp;
            map1Icon = new ImageIcon("images/map1Button.png");
            map2Icon = new ImageIcon("images/map2Button.png");

            setBackground(new Color(91, 24, 17));
            setLayout(null);

            map1Button = new JButton(map1Icon);
            map1Button.setBounds(100, 70, 350, 130);
            add(map1Button);
            map1Button.setActionCommand("map1");
            map1Button.addActionListener(this);

            map2Button = new JButton(map2Icon);
            map2Button.setBounds(100, 220, 350, 130);
            add(map2Button);
            map2Button.setActionCommand("map2");
            map2Button.addActionListener(this);

        }


        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getActionCommand() == "map1") {
                String mapInput = new String ("map1.in");
                try {
                    Scanner inputMap = new Scanner(new File(mapInput));
                    window.ground.height = inputMap.nextInt();
                    window.ground.width = inputMap.nextInt();
                    inputMap.nextLine();
                    int i = 0;
                    while (inputMap.hasNextLine()) {
                        String line = inputMap.nextLine();
                        for (int j = 0; j < line.length(); j++)
                            window.ground.map[i][j] = line.charAt(j);
                        i++;
                    }
                }
                catch (FileNotFoundException ex) {}
                this.isActive = false;
            }
            else if (actionEvent.getActionCommand() == "map2") {
                String mapInput = new String ("map2.in");
                try {
                    Scanner inputMap = new Scanner(new File(mapInput));
                    window.ground.height = inputMap.nextInt();
                    window.ground.width = inputMap.nextInt();
                    inputMap.nextLine();
                    int i = 0;
                    while (inputMap.hasNextLine()) {
                        String line = inputMap.nextLine();
                        for (int j = 0; j < line.length(); j++)
                            window.ground.map[i][j] = line.charAt(j);
                        i++;
                    }
                }
                catch (FileNotFoundException ex) {}
                this.isActive = false;
            }
        }
    }