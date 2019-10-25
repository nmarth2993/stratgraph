import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.tree.ExpandVetoException;

import java.awt.*;

class StrategyGrapher {
    final static int diameter = 5;
    JFrame frame;
    StrategyGrapherPanel panel;

    BattleshipGame game;

    int[] gameData;

    public StrategyGrapher() {
        gameData = new int[100];
        // gameData[0] = 1;
        // for (int i = 0; i < gameData.length; i++) {
        // gameData[i] = i;
        // }
        frame = new JFrame();
        panel = new StrategyGrapherPanel();

        panel.setPreferredSize(new Dimension(500, 500));

        frame.setContentPane(panel);

        frame.setVisible(true);
        frame.pack();
    }

    public void startGameThread() {
        new Thread(() -> {
            for (;;/* int i = 0; i < 1000; i++ */) {
                game = new BattleshipGame(new NicholasMarthinussStrategy());
                int turns = game.play();
                gameData[--turns]++;
                panel.repaint();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public int[] getGameData() {
        return gameData;
    }

    class StrategyGrapherPanel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            // offset of 40, 40

            int col = 10;
            int row = 10;
            int width = (getWidth() - 40) / col;
            int height = (getHeight() - 80) / row;
            g2d.setFont(new Font("Arial", Font.PLAIN, 8));
            FontMetrics fm = g2d.getFontMetrics();
            for (int i = 0; i < col; i++) {
                g2d.drawLine(i * width + 40, 40, i * width + 40, getHeight() - 40);
                g2d.drawString("" + (10 * i), i * width + 40 - 5 - fm.stringWidth((10 * i) + ""), 40);
            }
            for (int i = 0; i <= row; i++) {
                g2d.drawLine(40, i * height + 40, getWidth(), i * height + 40);
                g2d.drawString("" + (10 * i), i * width + 40 - 5 - fm.stringWidth((10 * i) + ""), 40);
            }
            for (int i = 0; i < gameData.length; i++) {
                g2d.fillOval(40 - diameter / 2 + i * diameter,
                        (getHeight() - 40) - diameter / 2 - (int) (((float) gameData[i] / 100f) * (getHeight() - 80)),
                        diameter, diameter);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StrategyGrapher s = new StrategyGrapher();
            s.startGameThread();
        });
    }
}