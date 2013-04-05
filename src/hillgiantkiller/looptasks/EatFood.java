package hillgiantkiller.looptasks;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.methods.tab.Inventory;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/1/13
 * Time: 10:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class EatFood extends LoopTask {
    @Override
    public int loop() {

        if(Methods.needToHeal(Var.HEAL_PERCENT) && Methods.haveFood(Var.foodId)){
            Inventory.getItem(Var.foodId).getWidgetChild().interact("Eat");
        }

        return Random.nextInt(50, 100);
    }
}
