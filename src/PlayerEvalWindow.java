
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

class PlayerEvalWindow {
    JFrame frame;
    JPanel panel;
    JLabel gameLabel;
    JLabel avgLabel;

    int games;
    float avg;

    public PlayerEvalWindow() {
        frame = new JFrame();
        panel = new JPanel();

        frame.setContentPane(panel);

        frame.setPreferredSize(new Dimension(300, 300));
        avg = 0;
        games = 0;

        gameLabel = new JLabel();
        avgLabel = new JLabel();

        panel.add(gameLabel);
        panel.add(avgLabel);

        frame.setVisible(true);
        frame.pack();
    }

    private void updateLabels() {
        gameLabel.setText(games + " games");
        avgLabel.setText("avg: " + avg);
    }

    public void startGamesThread(int numGames) {
        new Thread(() -> {
            int sum = 0;
            for (int i = 0; i < numGames; i++) {
                sum += new BattleshipGame(new NicholasMarthinussStrategy()).play();
                games++;
                avg = (float) sum / (float) games;
                updateLabels();
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame.setDefaultLookAndFeelDecorated(true);
            new PlayerEvalWindow().startGamesThread(1000);
        });
    }
}