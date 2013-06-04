package hillgiantkiller.nodes;


import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.sk.action.ActionBar;
import hillgiantkiller.sk.action.ability.DefenseAbility;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 5/24/13
 * Time: 8:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class EquipShield extends Node {
    public static int shieldId = 7360;

    @Override
    public boolean activate() {
        return Global.weaponSwitch && Global.useRejuvenate && Inventory.contains(shieldId) && switchToShield();
    }

    @Override
    public void execute() {
        Paint.status = "Equiping shield";
        System.out.println("Switching to shield");

        Equipment.equip(shieldId);
        Methods.waitFor(new Methods.Condition() {
            @Override
            public boolean accept() {
                return Equipment.containsOneOf(shieldId);
            }
        }, 5000);
    }

    private boolean switchToShield(){
        return ActionBar.getAdrenaline() == 1000 && !DefenseAbility.REJUVENATE.getCooldownChild().isOnScreen()
                && Methods.getHpPercent() <= Eat.HEAL_PERCENT && Methods.getHpPercent() > Eat.HEAL_PERCENT - 10;
    }
}
