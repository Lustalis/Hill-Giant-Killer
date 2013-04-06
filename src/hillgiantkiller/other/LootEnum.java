package hillgiantkiller.other;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/5/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public enum LootEnum {

    GOLD_CHARM("Gold Charm",12158),
    GREEN_CHARM("Green Charm",12159),
    CRIMSON_CHARM("Crimson Charm",12160),
    BLUE_CHARM("Blue Charm",12163),
    ALL_CHARMS("All Charms", 12158,12159,12160,12163),
    SPACE1(" ",1),
    MITHRIL_ARROW("Mithril Arrow",888),
    ADAMANT_ARROW("Adamant Arrow",890),
    BOTH_ARROWS("Both Arrows",888,890),
    SPACE2(" ",1),
    WATER_RUNE("Water Rune",555),
    FIRE_RUNE("Fire Rune",554),
    COSMIC_RUNE("Cosmic Rune",564),
    LAW_RUNE("Law Rune", 563),
    NATURE_RUNE("Nature Rune", 561),
    MIND_RUNE("Mind Rune",558),
    DEATH_RUNE("Death Rune",560),
    CHAOS_RUNE("Chaos Rune",563),
    SPACE3(" ",1),
    GUAM("Grimy Guam",199),
    MARRENTILL("Grimy Marrentill",201),
    TARROMIN("Grimy Tarromin",203),
    RANARR("Grimy Ranarr",207),
    HARRALANDER("Grimy Harralander",205),
    IRIT("Grimy irit",209),
    AVANTOE("Grimy Avantoe",211),
    KWUARM("Grimy Kwuarm",213),
    DWARF_WEED("Grimy Dwarf Weed",217),
    CADANTINE("Grimy Cadantine",215),
    LANTADYME("Lantadyme",2481),
    SPACE4(" ",1),
    LIMPWURT("Limpwurt Root",225),
    BODY_TAL("Body talisman",1446),
    CHAMPION_SCROLL("Champion scroll",6802);

    private int[] id;
    private String name;

    private LootEnum(String itemName, int... itemID){
        id = itemID;
        name = itemName;
    }

    public int[] getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static LootEnum[] allItems(){
        return LootEnum.values();
    }


}
