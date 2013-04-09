package hillgiantkiller.tasks;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Var;
import hillgiantkiller2.sk.action.ActionBar;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.methods.interactive.Players;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/1/13
 * Time: 10:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class UseAbilities extends LoopTask {
    @Override
    public int loop() {
        if ((Players.getLocal().getInteracting() != null && Players.getLocal().getInteracting().validate())
                && (Var.theGiant != null && Var.theGiant.validate()) ) {

            if (!ActionBar.isExpanded()) {
                ActionBar.setExpanded(true);
            }

            int x = Var.useRejuvenate ? 1: 0;

            if(Var.useRejuvenate && Methods.getHpPercent() <= 60 && ActionBar.getAdrenaline() == 1000
                    && ActionBar.getAbilityInSlot(0).available()){
                ActionBar.useSlot(0);
            }

            for(int i = x; i<12; i++){
                if(ActionBar.isReady(i)){
                    ActionBar.useSlot(i);
                }

            }

        }

        return Random.nextInt(50,100);
    }
}
