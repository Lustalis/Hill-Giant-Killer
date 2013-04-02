package hillgiantkiller.looptasks;

import hillgiantkiller.nodes.FindTargetNode;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.util.Random;

/**
 * User: Stefano Tabone
 * Date: 4/1/13
 * Time: 10:24 PM
 */
public class GiantCounter extends LoopTask {
    @Override
    public int loop() {

        if(Var.theGiant != null && Var.theGiant.validate()){
            if (Var.theGiant.getAnimation() == Var.deathID) {
                FindTargetNode.counter++;
                Task.sleep(1000);
            }
        }
        return Random.nextInt(250,300);
    }
}
