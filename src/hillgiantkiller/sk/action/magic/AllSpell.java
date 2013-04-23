package hillgiantkiller.sk.action.magic;

import org.powerbot.game.api.methods.tab.Skills;
import org.powerbot.game.api.wrappers.widget.WidgetChild;

import hillgiantkiller.sk.action.BookUtil;
import hillgiantkiller.sk.item.DefinedItem;
import hillgiantkiller.sk.item.RequiredGroup;
import hillgiantkiller.sk.tab.InnerAbilityTabs;
import hillgiantkiller.sk.tab.Tab;

public enum AllSpell implements Spell {
	POLYPORE_STRIKE(InnerAbilityTabs.COMBAT_SPELL_TAB, 162, 14396, 2598, 80),
	HOME_TELEPORT(InnerAbilityTabs.TELEPORT_TAB, 155, 14333, 2491, 0),
;


	@Override
	public Spellbook getSpellbook() {
		return Spellbook.NONE;
	}

	@Override
	public WidgetChild getChild() {
		return BookUtil.getChild(this);
	}

	@Override
	public WidgetChild getCooldownChild() {
		return BookUtil.getCooldownChild(this);
	}

	@Override
	public boolean show() {
		return BookUtil.show(this);
	}

	@Override
	public boolean isVisible() {
		return BookUtil.isVisible(this);
	}
	@Override
	public int getId() {
		return abilityId;
	}

	@Override
	public Tab getTab() {
		return tab;
	}

	@Override
	public int getChildId() {
		return childId;
	}

	@Override
	public int getChildTexture() {
		return childTexture;
	}

	@Override
	public int getLevel() {
		return level;
	}

	@Override
	public int getSkill() {
		return Skills.MAGIC;
	}

	@Override
	public Rune[] getRunes() {
		return runes;
	}

	@Override
	public RequiredGroup getRuneGroup() {
		RequiredGroup ret = new RequiredGroup();
		for (Rune r : getRunes()) {
			ret.add(new DefinedItem(r.getAmount(), r.getType().getItem()));
		}
		return ret;
	}

	private final Tab tab;
	private final int childId, childTexture, abilityId, level;
	private final Rune[] runes;

	private AllSpell(Tab tab, int childId, int childTexture, int abilityId, int level, Rune... runes) {
		this.tab = tab;
		this.childId = childId;
		this.childTexture = childTexture;
		this.abilityId = abilityId;
		this.level = level;
		this.runes = runes;
	}

}
