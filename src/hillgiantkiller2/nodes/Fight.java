package hillgiantkiller2.nodes;

import hillgiantkiller2.Methods;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Fight extends Node {
    public NPC theGiant = null;


    @Override
    public boolean activate() {

        return Players.getLocal().getInteracting() == null && !Players.getLocal().getInteracting().validate() && !Players.getLocal().isMoving()
                && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {
        theGiant = Methods.getMonster();

        if(theGiant != null){
            if(!Methods.isOnScreen(theGiant) || theGiant.interact("Attack")){
                Walking.walk(theGiant);

                Methods.waitForOnScreen(theGiant);
            }
            if(!theGiant.isInCombat()){
                theGiant.interact("Attack");
                Methods.waitForCombat();
            }
        }

    }//End of Execute

} //End of Node