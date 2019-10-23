

public class BattleshipGUI {

	private PlayerWindow pw;
	private OpponentWindow ow;

	public BattleshipGUI(PlayerWindow pw, OpponentWindow ow) {
		this.pw = pw;
		this.ow = ow;
	}

	public BattleshipGUI(PlayerWindow pw) {
		this.pw = pw;
	}

	public PlayerWindow getPw() {
		return pw;
	}

	public OpponentWindow getOw() {
		return ow;
	}

	private static void gameOver() {
//		JOptionPane.showMessageDialog(null, "winner is someone");
	}

	private void startOpponentThread(BattleshipGUI bg) {
		
		new Thread(() -> {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			for (; pw.getTurns() < 100;) {
//				try {
//					Thread.sleep(100);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				getPw().turnInc();
				synchronized (bg) {
					while (!(bg.getOw().isGameOver() || bg.getOw().isGameOver())
							&& bg.getPw().getTurns() > bg.getOw().getTurns()) {
						Position pos = bg.getOw().getPlayer().shoot();
						//					ocean
						//					updateplayer
						Ocean o = bg.getOw().getOcean();
						o.shootAt(pos);
						bg.getOw().getPlayer().updatePlayer(pos, o.hit(pos), o.boatInitial(pos), o.boatName(pos),
								o.sunk(pos), o.allSunk(), bg.getOw().getTurns() >= 100, bg.getOw().getTurns());
						bg.getOw().turnInc();
					}
				}
			
		}).start();
	}

	public static void main(String[] args) {

//		JOptionPane.showConfirmDialog(null, "click ok to start");

		BattleshipGUI bg = new BattleshipGUI(new PlayerWindow(new BattleshipPlayer()),
				new OpponentWindow(new NicholasMarthinussStrategy()));
		bg.startOpponentThread(bg);
//		startOpponentThread(bg);

		new Thread(() -> {
			BattleshipWindow[] windows = { bg.getPw(), bg.getOw() };
			for (;;) {
				try {
					Thread.sleep(0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if ((windows[0].getTurns() >= 100 || windows[0].getOcean().allSunk())
						|| (windows[1].getTurns() >= 100 || windows[1].getOcean().allSunk())) {
					windows[0].setGameOver(true);
					windows[1].setGameOver(true);
					gameOver();
					break;
				}
			}
		}).start();

//		PlayerWindow pw = );
//		System.out.println("\n\ncreating opponent window");
//		OpponentWindow ow = );
//		System.out.println("\n\ndone.");

		bg.getPw().createPanel();
		bg.getOw().createPanel();
		
		

//		while (!bg.getPw().getOcean().allSunk() && !bg.getOw().getOcean().allSunk() && bg.getPw().getTurns() < 100
//				&& bg.getOw().getTurns() < 100) {
//			if (turn) {
//				player.shoot();
//			} else {
//				opponent.shoot();
//			}
//
//			turn = !player;

//		 need something to restrict taking a turn when it shouldn't
//		}
//		while (!allSunk)
	}
}
