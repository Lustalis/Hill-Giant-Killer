package hillgiantkiller.sk.map.ultimate.teleport;

import hillgiantkiller.sk.item.RequiredGroup;
import hillgiantkiller.sk.requirement.Requirement;
import org.powerbot.game.api.wrappers.Tile;

public interface Teleport {

	public abstract Tile getLocation();

	public abstract RequiredGroup getItems();

	public abstract boolean use();

	public abstract Requirement getRequirements();

	public abstract boolean canUse();

	public abstract int getWeight();

	public abstract boolean isFreeToPlay();
}
