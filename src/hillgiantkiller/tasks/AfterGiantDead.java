package hillgiantkiller.tasks;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;

/**
 * User: Stefano Tabone
 * Date: 4/1/13
 * Time: 10:24 PM
 */
public class AfterGiantDead extends LoopTask {
    @Override
    public int loop() {

        if(Var.theGiant != null && Var.theGiant.validate()
                && Players.getLocal().getAnimation() != Var.EATING_ID && !Players.getLocal().isMoving()){
            if (Var.theGiant.getAnimation() == Var.DEATH_ID) {
                System.out.println("Giant is deeeead");
                Paint.giantsKilled++;

                if (Var.shouldLoot) {
                    Var.deathLocation = Var.theGiant.getLocation();

                    while(Var.isLooting){ //so it doesn't add loot while looting
                        Task.sleep(100, 200);
                    }

                    Var.isAdding = true;
                    Methods.waitForDrop();
                    Var.isAdding = false;
                }
            }
        }
        return Random.nextInt(500,600);
    }

}


