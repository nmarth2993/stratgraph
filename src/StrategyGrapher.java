import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.tree.ExpandVetoException;

import java.awt.*;

class StrategyGrapher {
    JFrame frame;
    StrategyGrapherPanel panel;

    BattleshipGame game;

    int[] gameData;

    public StrategyGrapher() {
        gameData = new int[100];

        for (int i = 0; i < gameData.length; i++) {
            gameData[i]++;
            if (i > 20) {
                gameData[i]++;
            }
            if (i > 50) {
                gameData[i] += 2;
            }
            if (i > 90) {
                gameData[i] += 10;
            }
        }
        frame = new JFrame();
        panel = new StrategyGrapherPanel();

        panel.setPreferredSize(new Dimension(500, 500));

        frame.setContentPane(panel);

        frame.setVisible(true);
        frame.pack();
    }

    public void startGameThread() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
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
            for (int i = 0; i < col; i++) {
                g2d.drawLine(i * width + 40, 40, i * width + 40, getHeight() - 40);
            }
            for (int i = 0; i <= row; i++) {
                g2d.drawLine(40, i * height + 40, getWidth(), i * height + 40);
            }
            int gameCount = 0;
            for (int i = 0; i < gameData.length; i++) {
                gameCount += gameData[i];
            }
            for (int i = 0; i < gameData.length; i++) {
                float percent = gameData[i] / gameCount;
                g2d.fillOval(40 + i * width, (int) (40 + (percent * height * 100)), 5, 5);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StrategyGrapher s = new StrategyGrapher();
            // s.startGameThread();
            // for (int i = 0; i < s.getGameData().length; i++) {
            // System.out.println(s.getGameData()[i]);
            // }
        });
    }
}