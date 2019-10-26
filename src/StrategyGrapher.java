import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class StrategyGrapher {
    final static int diameter = 5;
    JFrame frame;
    StrategyGrapherPanel panel;

    BattleshipGame game;

    int[] gameData;
    int numGames;
    int numDots;

    int loadingPos;
    int loadingPos2;

    public StrategyGrapher() {
        numDots = 0;
        numGames = 0;
        loadingPos = loadingPos2 = 0;
        gameData = new int[100];
        // gameData[0] = 1;
        // for (int i = 0; i < gameData.length; i++) {
        // gameData[i] = i;
        // }
        frame = new JFrame();
        panel = new StrategyGrapherPanel();

        panel.setPreferredSize(new Dimension(500, 500));

        frame.setContentPane(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
    }

    public void startGameThread() {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (;;/* int i = 0; i < 1000; i++ */) {
                    game = new BattleshipGame(new NicholasMarthinussStrategy());
                    int turns = game.play();
                    numGames++;
                    synchronized (gameData) {
                        gameData[--turns] += 4;
                    }
                    panel.repaint();
                    try {
                        Thread.sleep(5);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
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
            Font normalFont = new Font("Arial", Font.PLAIN, 9);
            g2d.setFont(normalFont);
            // FontMetrics fm = g2d.getFontMetrics();
            AffineTransform rot = new AffineTransform();
            rot.rotate(Math.toRadians(270));
            Font rotatedFont = new Font("Arial", Font.BOLD, 16).deriveFont(rot);
            g2d.setFont(rotatedFont);
            String vLabel = "% of games ||**";
            g2d.drawString(vLabel, 20, getHeight() / 2);
            g2d.setFont(new Font("Arial", Font.BOLD, 14));
            g2d.drawString("Number of turns", getWidth() / 2 - g2d.getFontMetrics().stringWidth("Number of turns") / 2,
                    getHeight() - 10);
            g2d.setFont(normalFont);
            // g2d.fillRect(0, 0, 10, 10);
            for (int i = 0; i < col; i++) {
                g2d.drawLine(i * width + 40, 40, i * width + 40, getHeight() - 40);
                // g2d.drawString("" + (10 * i), i * width + 40 - 5 - fm.stringWidth((10 * i) +
                // ""), 40);
                g2d.drawString((100 - 10 * i) + "", 30, i * height + 40);
            }
            for (int i = 0; i <= row; i++) {
                g2d.drawLine(40, i * height + 40, getWidth(), i * height + 40);
                g2d.drawString("" + (10 * i), i * width + 35 - (i == 10 ? 8 : 0), getHeight() - 28);
            }
            for (int i = 0; i < gameData.length; i++) {
                if (i == 17) {
                    g2d.setColor(Color.RED);
                    g2d.drawLine(40 + (i - 1) * diameter, 40, 40 + (i - 1) * diameter, getHeight() - 40);
                    g2d.drawString("perfect game line", 40 + i * diameter, 30);
                    g2d.setColor(Color.BLACK);
                }
                g2d.fillOval(40 - diameter / 2 + i * diameter, (getHeight() - 40) - diameter / 2
                        - (int) (((float) gameData[i] / numGames) * (getHeight() - 80)), diameter, diameter);
            }
            // g2d.setFont(new Font("Arial", Font.BOLD, 20));
            // String dots = "";
            // for (int i = 0; i < numDots; i++) {
            // dots += ".";
            // }
            // g2d.drawString(dots, getWidth() - 80, 20);

            g2d.setColor(new Color(66, 135, 245));
            g2d.setStroke(new BasicStroke(8f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.drawLine(getWidth() / 2 + loadingPos, 20, getWidth() / 2 + loadingPos2, 20);
        }
    }

    public void animationThread() {
        new Thread(() -> {
            for (;;) {
                int iter = 15;
                int timeout = 100;
                for (int i = 0; i < iter; i++) {
                    try {
                        Thread.sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    panel.repaint();
                    loadingPos += 2;
                }
                for (int i = 0; i < iter * 2; i++) {
                    try {
                        Thread.sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    panel.repaint();
                    loadingPos -= 2;
                }
                for (int i = 0; i < iter; i++) {
                    try {
                        Thread.sleep(timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    panel.repaint();
                    loadingPos += 2;
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StrategyGrapher s = new StrategyGrapher();
            s.startGameThread();
            s.animationThread();
        });
    }
}