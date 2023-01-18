package Front;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AlarmWindowPatch {
    private JButton complete;
    private JLabel errorText;
    private JPanel panel;
    private JFrame frame;

    public AlarmWindowPatch() {
        frame = new JFrame("Warning");
        frame.setPreferredSize(new Dimension(500, 300));
        frame.setResizable(false);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        complete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
}
