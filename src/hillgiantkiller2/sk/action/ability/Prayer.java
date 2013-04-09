package hillgiantkiller2.sk.action.ability;

import org.powerbot.game.api.wrappers.widget.WidgetChild;

import hillgiantkiller2.sk.action.Ability;
import hillgiantkiller2.sk.general.Completion;
import hillgiantkiller2.sk.tab.MainTabs;
import hillgiantkiller2.sk.tab.Tab;

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
