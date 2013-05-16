package hillgiantkiller.enums;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 5/6/13
 * Time: 12:27 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Rune {

    AIR_RUNE("Air Rune", 556),
    WATER_RUNE("Water Rune",555),
    FIRE_RUNE("Fire Rune",554),
    COSMIC_RUNE("Cosmic Rune",564),
    LAW_RUNE("Law Rune", 563),
    NATURE_RUNE("Nature Rune", 561),
    MIND_RUNE("Mind Rune",558),
    DEATH_RUNE("Death Rune",560),
    CHAOS_RUNE("Chaos Rune",563),;

    private String name;
    private int id;

    private Rune(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getName();
    }
}

