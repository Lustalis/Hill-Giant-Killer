package hillgiantkiller.tasks;

import hillgiantkiller.other.Var;
import hillgiantkiller.sk.action.ActionBar;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.Settings;

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

            if(Var.useRejuvenate){
                if(ActionBar.getAdrenaline() == 1000 && ActionBar.getAbilityInSlot(0).available()){
                    ActionBar.useSlot(0);
                }
                for(int i = 1; i<12; i++){
                    if(ActionBar.isReady(i)){
                        ActionBar.useSlot(i);
                    }

                }
            }else{
                for(int i = 0; i<12; i++){
                    if(ActionBar.isReady(i)){
                        ActionBar.useSlot(i);
                    }

                }
            }



        }

        return Random.nextInt(50,100);
    }
}