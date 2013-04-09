package hillgiantkiller2.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller2.Paint;
import hillgiantkiller2.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.methods.Players;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/9/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */

public class Dieing extends Node {
    private int npcHash = 0;
    @Override
    public boolean activate() {
        return Players.getLocal().getInteracting().getAnimation() == Variables.DEATH_ID;
    }

    @Override
    public void execute() {
        if(npcHash == 0 || npcHash != Variables.theGiant.hashCode()){
            System.out.println("Giant is deeeead");
            Paint.giantsKilled++;
            npcHash = Variables.theGiant.hashCode();
            if (Variables.shouldLoot) {
                Variables.deathLocation = Variables.theGiant.getLocation();

                while(Variables.isLooting){ //so it doesn't add loot while looting
                    Task.sleep(100, 200);
                }

                Variables.isAdding = true;
                Methods.waitForDrop();
                Variables.isAdding = false;
            }
            npcHash = Variables.theGiant.hashCode();
        }
    }
}
