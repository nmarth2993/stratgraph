import java.awt.*;

import javax.swing.JPanel;

public class BattleshipPanel extends JPanel {

	public final static int xOff = 50;
	public final static int yOff = 60;

	private int w;
	private int h;

	private BattleshipWindow window;

	public BattleshipPanel(BattleshipWindow window) {
		this.window = window;
		startUpdateThread();
	}

	private void startUpdateThread() {
		new Thread(() -> {
			for (;;) {
				if (window.getPanel() != null) {
					repaint();
				}
			}
		}).start();
	}

	public int getIncX() {
		return w;/* getWidth() - xOff; */
	}

	public int getIncY() {
		return h;/* getHeight() - yOff; */
	}

	public void paintComponent(Graphics g) {

		w = (getWidth() - xOff) / 10;
		h = (getHeight() - yOff) / 10;

		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		g2d.setFont(new Font("Arial", Font.BOLD, 16));
		String pName = window.getPlayerName();
		String turns = "turns: " + window.getTurns();
		int xName = g2d.getFontMetrics(g2d.getFont()).stringWidth(pName);
		int xTurns = g2d.getFontMetrics(g2d.getFont()).stringWidth(turns);

		g2d.drawString(pName, getWidth() / 2 - xName / 2, 15);
		g2d.drawString(turns + "", xTurns, 18);

		int strokeWidth = 5;

		g2d.setStroke(new BasicStroke(strokeWidth));

//		g2d.drawString("THIS IS 50, 50 ", 50, 50);

		for (int i = 0; i < 11; i++) {
			if (i < 10) {
				g2d.drawString((i + 1) + "", xOff + w * i + (w / 2), h - 10);
				g2d.drawString((char) (i + 65) + "", xOff - 20, yOff + h * i + (h / 2));
			}
			g2d.drawLine(xOff + w * i, h, xOff + w * i, getHeight()); // vertical
			g2d.drawLine(xOff, yOff + h * i, getWidth(), yOff + h * i); // horizontal
		}

//		Point p = window.getMouseHandler().getPoint();

//		System.out.println();

		Position pos = window.getMouseHandler().p2p(window.getMouseHandler().getPoint());
		g2d.setColor(Color.YELLOW);

		if (pos != null) {
//			g2d.fillRect(yOff + w * pos.columnIndex(), xOff + 5 + w * pos.rowIndex(), xOff + (w / 2), yOff + (h / 2));

			g2d.fillRect((xOff + strokeWidth - 2) + (w * pos.columnIndex()),
					(yOff + strokeWidth - 2) + (h * pos.rowIndex()), 50, 56);
		}

		BattleshipGrid grid = window.getGrid();

		int fontPt = 36;
		g2d.setFont(new Font("Arial", Font.BOLD, fontPt));

		g2d.setColor(Color.BLACK);

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (grid.miss(new Position(i, j))) {
					int width = g2d.getFontMetrics().stringWidth("X");
					g2d.drawString("X", (xOff + strokeWidth - 1) + (w * j) + (width / 2),
							(yOff + strokeWidth - 2) + (h * i) + fontPt);
				} else if (grid.hit(new Position(i, j))) {
					if (window.getOcean().sunk(new Position(i, j))) {
						g2d.setColor(Color.RED);

						g2d.fillRect((xOff + strokeWidth - 2) + (w * j), (yOff + strokeWidth - 2) + (h * i), 50, 56);

						g2d.setColor(Color.BLACK);
					}
					String boatChar = "" + grid.boatInitial(new Position(i, j));
					int width = g2d.getFontMetrics().stringWidth(boatChar);
					g2d.drawString("" + boatChar, (xOff + strokeWidth - 1) + (w * j) + (width / 2),
							(yOff + strokeWidth - 2) + (h * i) + fontPt);

				}
			}
		}
		
//		System.out.println(window.getPlayer().getClass().toString());
		if (window.getPlayer() instanceof NicholasMarthinussStrategy) {
			NicholasMarthinussStrategy nms = (NicholasMarthinussStrategy) window.getPlayer();
			Position hPos = null;
			synchronized (nms) {
				try {
					Thread.sleep(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				synchronized (nms) {
					nms.genHeatmap();
					hPos = nms.heatmap.hottestPos();
				}
				
			}
			
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					int amt = nms.heatmap.getHeatmap()[i][j];
					float famt = (float) 1.7f * amt / 100f;
					if (famt > 1f) {
						famt = 1f;
					}
//					System.out.println(famt);
					g2d.setColor(new Color(0f, 0f, 0f, famt));
					g2d.fillRect((xOff + strokeWidth - 2) + (w * j), (yOff + strokeWidth - 2) + (h * i), 50, 56);
					if (hPos.rowIndex() == i && hPos.columnIndex() == j) {
						g2d.setPaint(new GradientPaint((xOff + strokeWidth - 2) + (w * j), (yOff + strokeWidth - 2) + (h * i), Color.RED, (xOff + strokeWidth - 2) + (w * (j + 1)), (yOff + strokeWidth - 2) + (h * (i + 1)), Color.BLUE));
						g2d.fillRect((xOff + strokeWidth - 2) + (w * j), (yOff + strokeWidth - 2) + (h * i), 50, 56);
						g2d.setPaint(Color.BLACK);
					}
				}
			}
		}

	}
}
