package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
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
    private int npcHash = 0;
    @Override
    public boolean activate() {
        return Fight.theGiant.getAnimation() == Variables.DEATH_ID;
    }

    @Override
    public void execute() {
        if(npcHash == 0 || npcHash != Fight.theGiant.hashCode()){
            System.out.println("Giant is deeeead");
            Paint.giantsKilled++;
            npcHash = Fight.theGiant.hashCode();
            if (Variables.shouldLoot) {
                Variables.deathLocation = Fight.theGiant.getLocation();

                if (Variables.lootAfter == 1) {
                    System.out.println("Wait for loot");
                    Methods.waitForDrop();
                }else{
                    Methods.droppedLoot();
                }
            }

        }
    }
}
