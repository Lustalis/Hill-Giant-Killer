package hillgiantkiller.enums;

import org.powerbot.game.api.methods.tab.Skills;

/**
 * User: Stefano Tabone
 * Date: 4/12/13
 * Time: 9:59 PM
 */
public enum SkillsEnum {
    ATTACK("Attack", Skills.ATTACK),
    STRENGTH("Strnegth", Skills.STRENGTH),
    DEFENSE("Defense", Skills.DEFENSE),
    ASD("[A,S,D]", Skills.ATTACK),
    RANGED("Range", Skills.RANGE),
    MAGIC("Magic", Skills.MAGIC);

    private String name;
    private int skillID;

    private SkillsEnum(String name, int skillID){
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
