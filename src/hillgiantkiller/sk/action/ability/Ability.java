package hillgiantkiller.sk.action.ability;

import hillgiantkiller.sk.action.BookDraggable;

public interface Ability extends BookDraggable {

	public int getCooldown();

	public AbilityType getType();
	
	public int getComboLength();

	public AbilityStyle getStyle();

}
