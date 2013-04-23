package hillgiantkiller.tasks;

import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.util.Random;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Timer;

/**
 * User: Stefano Tabone
 * Date: 4/22/13
 * Time: 12:46 PM
 */
public class FailSafe extends LoopTask {

    @Override
    public int loop() {
        Timer t = new Timer(5000);
        while(t.isRunning()){
            Task.sleep(500);
        }
        if(isIdle()){
            System.out.println("Moving camera");
            Camera.setAngle(Random.nextInt(0, 359));
        }
        return Random.nextInt(500, 1500);
    }

    private static boolean isIdle(){
        return Players.getLocal().isIdle() && Players.getLocal().getAnimation() == -1
                && !Players.getLocal().isMoving();
    }
}
