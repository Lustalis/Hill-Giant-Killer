package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.interactive.Players;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/9/13
 * Time: 10:51 AM
 * To change this template use File | Settings | File Templates.
 */

public class Dying extends Node {

    @Override
    public void execute() {
        System.out.println("Giant is deeeead");
        Variables.deathLocation = Fight.theGiant.getLocation();

        Paint.giantsKilled++;
        if (Variables.shouldLoot) {

            if (Variables.lootAfter == 1) {
                System.out.println("Wait for loot");
                Methods.waitForDrop();
            }else{
                Methods.droppedLoot();
            }
        }

        //return Players.getLocal().getInteracting() != null && Fight.theGiant.getAnimation() == Variables.DEATH_ID;

    }

    @Override
    public boolean activate() {
        return Players.getLocal().getInteracting() != null && Fight.theGiant.getAnimation() == Variables.DEATH_ID;    }
}
