package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Bank;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class BankNode extends Node {
    @Override
    public boolean activate() {
        return Var.bankArea.contains(Players.getLocal()) && !Methods.haveFood(Var.foodIds);
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
        Bank.withdraw(Var.foodIds, 15);
        Bank.withdraw(983, 1);
        Bank.close();

    }
}
