package hillgiantkiller.other;

import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.wrappers.Locatable;
import org.powerbot.game.api.wrappers.Tile;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * User: Stefano Tabone
 * Date: 4/8/13
 * Time: 11:00 PM
 */
public class Global {

    public static ExecutorService stuffToDo = Executors.newFixedThreadPool(10);


    /*
    Combat stuff
     */

    public static final int[] NPC_IDS = {117, 4689, 4690, 4691, 4692, 4693};
    public static Tile deathLocation = null;
    public static final int DEATH_ID = 4653;


    /*
    GUI Global
     */
    public static boolean useResourceDungeon = false;
    public static int lootRadius;

    public static boolean isMage = false;
    public static boolean isRange = false;


    public static boolean useAbilities = false;
    public static boolean useMomentum = false;
    public static boolean useRejuvenate = false;

    public static boolean eatFood = false;
    public static int foodId;
    public static int withdrawFoodAmount = 15;

    public static boolean shouldLoot = false;
    public static boolean burryBones = false;
    public static boolean eatFoodForSpace = false;
    public static boolean lootByPrice = false;
    public static int minPriceToLoot = 1000;
    public static int lootAfter = 1;
    public static boolean shouldWaitForLoot = false;
    public static int gKilled = 0;

    public static class MoveCamera extends Thread{
        private Locatable n;
        public MoveCamera(final Locatable locatable){
            this.n = locatable;
        }

        public void run(){
            Camera.turnTo(n);

        }
    }
}
