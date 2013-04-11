package hillgiantkiller.tasks;

import hillgiantkiller.nodes.Dying;
import hillgiantkiller.nodes.Fight;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;

/**
 * User: Stefano Tabone
 * Date: 4/10/13
 * Time: 9:33 PM
 */
public class CheckForDying extends LoopTask {
    private final Node dying = new Dying();
    @Override
    public int loop() {
        if(dying.activate()){
            getContainer().setPaused(true);
            dying.execute();
            while(dying.activate()){
                Task.sleep(500);
            }
            getContainer().setPaused(false);

        }
        return Random.nextInt(25, 50);
    }
}
