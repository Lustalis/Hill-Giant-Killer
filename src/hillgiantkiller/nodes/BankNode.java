package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.wrappers.node.Item;

/**
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:37 AM
 */
public class BankNode extends Node {
    @Override
    public boolean activate() {
        return Var.BANK_AREA.contains(Players.getLocal()) && (!Methods.haveFood(Var.foodId) || Methods.fullInv());
    }

    @Override
    public void execute() {
        System.out.println("In bank node");
        while (!Bank.isOpen()){
            if (!Players.getLocal().isMoving()) {
                Bank.open();
                Task.sleep(500);
            }
        }

        Bank.depositInventory();
        Bank.withdraw(Var.foodId, Var.withdrawFoodAmount);
        Bank.withdraw(983, 1);
        Bank.close();
        WalkToBank.walkToBank = false;

    }
}
