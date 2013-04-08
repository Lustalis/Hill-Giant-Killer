package hillgiantkiller.tasks;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.methods.tab.Inventory;

/**
 * User: Administrator
 * Date: 4/1/13
 * Time: 10:32 AM
 */
public class EatFood extends LoopTask {
    @Override
    public int loop() {

        if(Methods.needToHeal() && Methods.haveFood(Var.foodId)){
            Inventory.getItem(Var.foodId).getWidgetChild().interact("Eat");
            Var.theGiant.interact("Attack");
        }

        return Random.nextInt(50, 100);
    }
}
