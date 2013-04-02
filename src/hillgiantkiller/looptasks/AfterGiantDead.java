package hillgiantkiller.looptasks;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.LoopTask;
import org.powerbot.core.script.job.Task;
import org.powerbot.game.api.util.Random;

/**
 * User: Stefano Tabone
 * Date: 4/1/13
 * Time: 10:24 PM
 */
public class AfterGiantDead extends LoopTask {
    @Override
    public int loop() {

        if(Var.theGiant != null && Var.theGiant.validate()){
            if (Var.theGiant.getAnimation() == Var.DEATH_ID) {
                Var.deathLocation = Var.theGiant.getLocation();
                Paint.giantsKilled++;
                Task.sleep(3000);

                if(Methods.droppedLoot()== true){
                    System.out.println("There is loot here");
                }else{
                    System.out.println("No loot here");

                }

            }
        }
        return Random.nextInt(250,300);
    }
}
