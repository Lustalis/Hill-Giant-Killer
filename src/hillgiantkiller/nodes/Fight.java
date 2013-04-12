package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.interactive.Character;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Fight extends Node {
    public static NPC theGiant = null;


    @Override
    public boolean activate() {

        return isInteracting() == null && !Players.getLocal().isMoving() && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {
        System.out.println("Finding npc.....");
        theGiant = Methods.getMonster();

        if(theGiant != null){
            new MoveCamera(theGiant).start();
            if(!Methods.isOnScreen(theGiant) || !theGiant.interact("Attack")){
                Walking.walk(theGiant);
                Methods.waitForOnScreen(theGiant);
            }
            if(!theGiant.isInCombat()){
                theGiant.interact("Attack");
                Methods.waitForCombat();
            }
        }

    }//End of Execute

    private Character isInteracting(){
        return Players.getLocal().getInteracting() != null && Players.getLocal().getInteracting().validate() ?
                Players.getLocal().getInteracting() : null;
    }

    private class MoveCamera extends Thread{
        private NPC n;

        public MoveCamera(final NPC npc){
            this.n = npc;
        }

        public void run(){
            Camera.turnTo(n);
        }
    }



} //End of Node