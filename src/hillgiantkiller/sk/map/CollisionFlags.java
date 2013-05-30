package hillgiantkiller.sk.map;

import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;

public class CollisionFlags extends Obstacles {

	protected final Tile base = null;
	protected final Tile offset = null;
	protected final int[][] flags = null;

	public CollisionFlags() {

	}


    public CollisionFlags(final Tile base) {

    }

    public CollisionFlags(final Tile base, final Tile offset, final int[][] flags) {

    }


	public boolean walkable(final Tile t) {
		return walkable(toPoint(t));
	}

	public boolean blocked(final Tile p, final Tile t) {
		return blocked(new Node(new Node(toPoint(p)), toPoint(t)));
	}

	public Tile getOffset() {
		return offset;
	}

	public int[][] getFlags() {
		return flags;
	}

	public Tile getBase() {
		return base;
	}

	public int getFlag(final OffsetPoint n) {
		final int x = n.getX() - offset.getX();
		final int y = n.getY() - offset.getY();
		if (x >= 0 && y >= 0 && x < flags.length && y < flags[x].length) {
			return flags[x][y];
		} else {
			return -1;
		}
	}

	public boolean checkPoint(final OffsetPoint n) {
		return checkPoint(n, 0);
	}

	public boolean checkPoint(final OffsetPoint n, int extra) {
		int pad = PADDING + extra;
		final int x = n.getX() - offset.getX();
		final int y = n.getY() - offset.getY();
		return x >= pad && y >= pad && x < flags.length - pad && y < flags[x].length - pad;
	}

	@Override
	public boolean blocked(final Node n) {
		if (n == null || !walkable(n)) {
			return true;
		}
		if (n.getPrev() == null) {
			return false;
		}
		if (!walkable(n.getPrev())) {
			return true;
		}
		final Node p = n.getPrev();
		return checkBlocked(n, p) || checkBlocked(p, n);
	}

	private boolean checkBlocked(final OffsetPoint n, final OffsetPoint b) {

		return false;
	}



	@Override
	public boolean walkable(final OffsetPoint n) {
		if (n == null) {
			return false;
		}
		final int x = n.getX() - offset.getX();
		final int y = n.getY() - offset.getY();
		return x >= PADDING && y >= PADDING && x < flags.length - PADDING && y < flags[x].length - PADDING
				&& (flags[x][y] & BLOCKED) == 0;
	}

	public OffsetPoint pullUntilWalkable(OffsetPoint n, final OffsetPoint me) {
		if (n == null || me == null) {
			return null;
		}
		int x = n.getX() - offset.getX();
		int y = n.getY() - offset.getY();
		while (x < PADDING) {
			x++;
		}
		while (x >= flags.length - PADDING) {
			x--;
		}
		while (y < PADDING) {
			y++;
		}
		while (y >= flags[x].length - PADDING) {
			y--;
		}
		n = new OffsetPoint(x + offset.getX(), y + offset.getY());
		while (!walkable(n) && !n.equals(me)) {
			int dx = 0;
			if (n.getX() > me.getX()) {
				dx = -1;
			} else if (n.getX() < me.getX()) {
				dx = 1;
			}
			int dy = 0;
			if (n.getY() > me.getY()) {
				dy = -1;
			} else if (n.getY() < me.getY()) {
				dy = 1;
			}
			n.shift(dx, dy);
		}
		return n;
	}

	public OffsetPoint toPoint(final Tile t) {
		if (t == null || base == null) {
			return null;
		}
		return new OffsetPoint(t.getX() - base.getX(), t.getY() - base.getY());
	}

	public Tile toTile(final OffsetPoint o) {
		if (o == null || base == null) {
			return null;
		}
		return new Tile(base.getX() + o.getX(), base.getY() + o.getY(), base.getPlane());
	}

	private static final int PADDING = 5, BLOCKED = 0x260000;

}
