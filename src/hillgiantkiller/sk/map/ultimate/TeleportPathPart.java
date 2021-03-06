package hillgiantkiller.sk.map.ultimate;

import hillgiantkiller.sk.item.ItemCache;
import hillgiantkiller.sk.map.ultimate.teleport.Teleport;
import hillgiantkiller.sk.map.ultimate.teleport.TeleportUtil;
import org.powerbot.game.api.wrappers.Tile;

public class TeleportPathPart implements PathPart {

	private Teleport tele;

	public TeleportPathPart(Teleport t) {
		this.tele = t;
	}

	@Override
	public boolean validate() {
		return tele.getRequirements().meets() && ItemCache.getItems().contains(tele.getItems());
	}

	@Override
	public void traverse() {
		tele.use();
	}

	@Override
	public boolean ready() {
		return tele.canUse() && !TeleportUtil.atEnd(tele);
	}

	public Teleport getTeleport() {
		return tele;
	}

	@Override
	public Tile getDestination() {
		return tele.getLocation();
	}

	@Override
	public String toString() {
		return "TeleportPathPart: " + tele;
	}

}
