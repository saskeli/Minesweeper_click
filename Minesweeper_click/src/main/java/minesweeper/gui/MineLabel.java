package minesweeper.gui;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Dimension;
/**
 *
 * @author dongesa
 */
public class MineLabel  extends JLabel {

    public MineLabel() {
        super();
        setOpaque(true);
        setBackground(Color.WHITE);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setPreferredSize(new Dimension(25, 25));
    }

    @Override
    public void setText(String string) {
        if (string.equals("0")) {
            return;
        }
        if (string.equals("9")) {
            super.setText("*");
            setBackground(Color.RED);
            return;
        }
        super.setText(string);
    }
}
