package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import hillgiantkiller.other.Variables;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.NPCs;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.interactive.Character;
import org.powerbot.game.api.wrappers.interactive.NPC;

public class Fight extends Node {
    public static NPC theGiant = null;
    public static final int[] NPC_IDS = {117,4689,4690,4691,4692,4693};
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

    private static Filter<NPC> multipleNpcFilter = new Filter<NPC>() {
        @Override
        public boolean accept(NPC n) {
             return n.getInteracting() != null && n.getInteracting().equals(Players.getLocal()) && n.getHealthPercent() < 100;
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

        return isInteracting() == null && Players.getLocal().getAnimation() == -1;
    }

    @Override
    public void execute() {
        Paint.status = "Finding a giant";
        theGiant = getMonster();

        if(theGiant != null){
            Variables.deathLocation = theGiant.getLocation();
            Paint.status = "Fighting giant";
            if(Methods.isOnScreen(theGiant) && !theGiant.isInCombat() && theGiant.interact("Attack")){
                Methods.waitForCombat();
            }else if(Calculations.distanceTo(theGiant) >= 9 ){
                Walking.walk(theGiant);
            } else{
                new MoveCamera(theGiant).start();
            }


//            if(!theGiant.isOnScreen() && !Players.getLocal().isMoving()){
//                Walking.walk(theGiant);
//                //Methods.waitForOnScreen(theGiant);
//            }
//            if(!theGiant.isInCombat() && theGiant.interact("Attack")){
//                Methods.waitForCombat();
//            }
        }

    }//End of Execute

    private Character isInteracting(){
        return Players.getLocal().getInteracting() != null && Players.getLocal().getInteracting().validate() ?
                Players.getLocal().getInteracting() : null;
    }

    private static NPC getMonster(){

        if(NPCs.getNearest(multipleNpcFilter) != null){
            return  NPCs.getNearest(multipleNpcFilter);
        }else if(NPCs.getNearest(priorityNPCFilter) != null){
            return NPCs.getNearest(priorityNPCFilter);
        }else{
            return NPCs.getNearest(npcFilter);
        }

    }


    public static class MoveCamera extends Thread{
//        private NPC n;
        private Locatable n;
        public MoveCamera(final Locatable locatable){
            this.n = locatable;
        }

        public void run(){
            Camera.turnTo(n);
        }
    }



} //End of Node