import java.awt.Point;
import java.awt.event.*;

public class PanelMouseListener implements MouseListener, MouseMotionListener {
	private BattleshipWindow w;
	private Point mPos;

	public PanelMouseListener(BattleshipWindow w) {
		this.w = w;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// XXX: mouse Clicked event. handle getting point to position

//		System.out.println(w.getClass());

//		System.out.println(w.isGameOver());
		if (w.isGameOver()) {
			return;
		}

		Ocean o = w.getOcean();
		Position pos;
//		if (w.getPlayerName().equals("Nicholas Marthinuss' Strategy")) { // XXX: This is a shit way to do this
//			pos = w.getPlayer().shoot();
//		} else {
		pos = shoot(e.getPoint());
//		}

		if (pos != null) {
			o.shootAt(pos);
			w.getPlayer().updatePlayer(p2p(e.getPoint()), o.hit(pos), o.boatInitial(pos), o.boatName(pos), o.sunk(pos),
					o.allSunk(), w.getTurns() > 100, w.getTurns());
			if (w.getOcean().allSunk()) {
				w.setGameOver(true);
			}

			// do opponent shot here as well

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mPos = e.getPoint();
	}

	public Point getPoint() {
		return mPos;
	}

	public Position shoot(Point p) {
		Position pos = p2p(p);
		if (pos != null) {
			w.turnInc();
			w.getOcean().shootAt(new Position(pos.rowIndex(), pos.columnIndex()));
			w.getGrid().shotAt(pos, w.getOcean().hit(pos), w.getOcean().boatInitial(pos));
			return p2p(p);
		}
		return null;
	}

	public Position p2p(Point p) {
		if (p == null) {
			return null;
		}
		if (p.getX() > BattleshipPanel.xOff && p.getY() > BattleshipPanel.yOff) {

			int row = (int) (p.getY() - (25 + BattleshipPanel.yOff)) / w.getPanel().getIncY();
			int col = (int) (p.getX() - BattleshipPanel.xOff) / w.getPanel().getIncX();
			if (row > 9) {
				row = 9;
			}
			if (col > 9) {
				col = 9;
			}
			Position shot = new Position(row, col);
//			System.out.println(shot);

//			w.turnInc();
			return shot;
		}
		return null;
	}

}