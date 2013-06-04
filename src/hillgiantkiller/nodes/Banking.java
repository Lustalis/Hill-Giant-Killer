package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import hillgiantkiller.other.Paint;
import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Stefano Tabone
 * Date: 6/3/13
 * Time: 8:26 PM
 */
public class Banking extends Node {
    public static List<Integer> runesToKeep = new ArrayList<>();
    private final int KEY_ID = 983;
    @Override
    public boolean activate() {
        return Bank.isOpen();
    }

    @Override
    public void execute() {
        Paint.status = "Banking";
        ActionBar.expand(false);
        if(!Players.getLocal().isMoving() && Bank.open()){
            Bank.depositInventory();
            Task.sleep(500, 900);
            if(Global.eatFood){
                Bank.withdraw(Global.foodId, Global.withdrawFoodAmount);
                Task.sleep(250, 750);
            }
            if(Global.isMage){
                for (int i: runesToKeep){
                    Bank.withdraw(i, Bank.Amount.ALL);
                    Task.sleep(250, 750);
                }
            }
            Bank.withdraw(983, 1);
            Bank.close();
            Task.sleep(250, 750);
        }

        if(!Inventory.contains(Global.foodId) || !Inventory.contains(KEY_ID)){
            Game.logout(true);
        }
    }
}
