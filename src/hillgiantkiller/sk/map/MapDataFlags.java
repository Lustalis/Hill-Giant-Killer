package hillgiantkiller.sk.map;

import hillgiantkiller.sk.Universal;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.wrappers.Tile;

public class MapDataFlags extends Obstacles {

	private MapData map;

	private int plane = (Universal.testing ? 0 : -1);

	public MapDataFlags(MapData md, int p) {
		this.plane = p;
		this.map = md;
	}

	public MapDataFlags(MapData md) {
		this.map = md;
	}

	public MapDataFlags() {
		this(MapData.get());
	}

	@Override
	public boolean blocked(Node n) {
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

	private Tile toTile(OffsetPoint p) {
		return toTile(p.getX(), p.getY());
	}

	private Tile toTile(int x, int y) {
		return new Tile(x, y, (plane == -1 ? Game.getPlane() : plane));
	}

	private static final int[] X_WALL = {  };

	@Override
	public boolean walkable(OffsetPoint n) {
		return (map.get(toTile(n))) != 0;
	}

	public boolean walkable(Tile t) {
		return map.get(t) != 0;
	}

	public MapData getMapData() {
		return map;
	}

	public void setPlane(int plane) {
		this.plane = plane;
	}

}
