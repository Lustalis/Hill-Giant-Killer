package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.tab.Equipment;
import org.powerbot.game.api.methods.tab.Inventory;

/**
 * Created with IntelliJ IDEA.
 * User: HRphoto
 * Date: 04/06/13
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class EquipWeapon extends Node {
    public static int weaponId = 1333;
    @Override
    public boolean activate() {
        return Global.weaponSwitch && Inventory.contains(weaponId);
    }

    @Override
    public void execute() {
        System.out.println("Equiping Weapon");
        Paint.status = "Equiping Weapon";
        Equipment.equip(weaponId);
        Methods.waitFor(new Methods.Condition() {
            @Override
            public boolean accept() {
                return Equipment.containsOneOf(weaponId);
            }
        }, 5000);
    }
}
