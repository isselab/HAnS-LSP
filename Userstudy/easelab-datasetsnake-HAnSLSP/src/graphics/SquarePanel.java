package graphics;

import java.awt.Color;
import java.io.Serial;
import javax.swing.JPanel;

public class SquarePanel extends JPanel {

    @Serial
    private static final long serialVersionUID = 1L;

    public SquarePanel(Color d) {
        this.setBackground(d);
    }

    // &begin[Update]
    public void ChangeColor(Color d) {
        this.setBackground(d);
        this.repaint();
    }
    // &end[Update]
}

