package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.interactive.Character;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Fight extends Node {
    public static NPC theGiant = null;
    private static final int[] NPC_IDS = {117,4689,4690,4691,4692,4693};
    private static Filter<NPC> npcFilter = new Filter<NPC>() {
        @Override
        public boolean accept(NPC n) {
            for(int i: NPC_IDS){
                if(n.getId() == i && n.getInteracting() == null && n.getAnimation() != Variables.DEATH_ID){
                    return true;
                }
            }
            return false;
        }
    };
    private static Filter<NPC> priorityNPCFilter = new Filter<NPC>() {
        @Override
        public boolean accept(NPC n) {
            return n.getInteracting() != null && n.getInteracting().equals(Players.getLocal());
        }
    };


    @Override
    public boolean activate() {

        return isInteracting() == null && !Players.getLocal().isMoving() && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {
        System.out.println("Finding npc.....");
        Paint.status = "Finding a giant";
        theGiant = getMonster();

        if(theGiant != null){
            Variables.deathLocation = theGiant.getLocation();
            Paint.status = "Fighting giant";
            new MoveCamera(theGiant).start();
            if(!theGiant.isOnScreen() && !Players.getLocal().isMoving()){
                Walking.walk(theGiant);
                //Methods.waitForOnScreen(theGiant);
            }
            if(!theGiant.isInCombat() && theGiant.interact("Attack")){
                Methods.waitForCombat();
            }
        }

    }//End of Execute

    private Character isInteracting(){
        return Players.getLocal().getInteracting() != null && Players.getLocal().getInteracting().validate() ?
                Players.getLocal().getInteracting() : null;
    }

    public static NPC getMonster(){

        if(NPCs.getNearest(priorityNPCFilter) != null){
            return  NPCs.getNearest(priorityNPCFilter);
        }else{
            return NPCs.getNearest(npcFilter);
        }

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