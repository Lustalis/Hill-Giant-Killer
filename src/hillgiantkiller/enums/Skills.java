package hillgiantkiller.enums;

/**
 * User: Stefano Tabone
 * Date: 4/12/13
 * Time: 9:59 PM
 */
public enum Skills {
    ATTACK("Attack", org.powerbot.game.api.methods.tab.Skills.ATTACK),
    STRENGTH("Strnegth", org.powerbot.game.api.methods.tab.Skills.STRENGTH),
    DEFENSE("Defense", org.powerbot.game.api.methods.tab.Skills.DEFENSE),
    ASD("[A,S,D]", org.powerbot.game.api.methods.tab.Skills.ATTACK),
    RANGED("Range", org.powerbot.game.api.methods.tab.Skills.RANGE),
    MAGIC("Magic", org.powerbot.game.api.methods.tab.Skills.MAGIC);

    private String name;
    private int skillID;

    private Skills(String name, int skillID){
        this.name = name;
        this.skillID = skillID;
    }

    public String getName() {
        return name;
    }

    public int getSkillID() {
        return skillID;
    }

    @Override
    public String toString() {
        return getName();
    }

}
