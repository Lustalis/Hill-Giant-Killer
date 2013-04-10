package hillgiantkiller.sk.action.ability;

import hillgiantkiller.sk.action.Ability;
import hillgiantkiller.sk.general.Completion;
import hillgiantkiller.sk.tab.MainTabs;
import hillgiantkiller.sk.tab.Tab;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

public enum Prayer implements Ability {
	;

	@Override
	public boolean show() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public boolean available() {
		return false;
	}

	public Tab getTab() {
		return MainTabs.PRAYER;
	}

	@Override
	public WidgetChild getChild() {
		return null;
	}

	@Override
	public Completion getChange() {
		return null;
	}

	@Override
	public int getAbilityId() {
		return 0;
	}

}
