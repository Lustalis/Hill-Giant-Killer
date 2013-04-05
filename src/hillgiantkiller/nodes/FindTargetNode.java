package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Var;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.NPC;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/28/13
 * Time: 10:07 AM
 * To change this template use File | Settings | File Templates.
 */
public class FindTargetNode extends Node {
    @Override
    public boolean activate() {
        NPC x = NPCs.getNearest(Var.NPC_IDS);
        return  !Methods.needToLoot() && Methods.haveFood(Var.foodId) && !Methods.fullInv() && (interacting() == null || !interacting().validate())
                && (x != null) && Players.getLocal().getAnimation() == -1 && Players.getLocal().getAnimation() != Var.EATING_ID
                && !Players.getLocal().isMoving();
    }

    @Override
    public void execute() {
        Var.theGiant = Methods.getMonster();
        Var.isLooting = false;

        if(Var.theGiant != null){
            System.out.println("Killed this many nigguhs: "+ Paint.giantsKilled);
            System.out.println("Found npc!!!");
            if(!Methods.isOnScreen(Var.theGiant)){
                Camera.turnTo(Var.theGiant);
                if(!Methods.isOnScreen(Var.theGiant)){
                    Walking.walk(Var.theGiant);
                }
                Methods.waitForOnScreen(Var.theGiant);
            }
            System.out.println("Attacking");
            Var.theGiant.interact("Attack");
            Methods.waitForCombat();



        }


    }

    private org.powerbot.game.api.wrappers.interactive.Character interacting(){
        return Players.getLocal().getInteracting() != null && Players.getLocal().getInteracting().validate() ?
                Players.getLocal().getInteracting() : null;
    }
}
