package hillgiantkiller.other;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;

import java.util.ArrayList;

/**
 * User: Stefano Tabone
 * Date: 4/8/13
 * Time: 11:00 PM
 */
public class Variables {


    /*
    Combat stuff
     */

    public static final int[] NPC_IDS = {117, 4689, 4690, 4691, 4692, 4693};
    public static Tile deathLocation = null;
    public static final int DEATH_ID = 4653;


    /*
    Looting stuff
     */
    public static ArrayList<Tile> lootLocations = new ArrayList<>();


    /*
    Scene Entity stuff
     */

    public static final int DOOR_ID = 1804;



    /*
    GUI Variables
     */

    public static boolean useAbilities = false;
    public static boolean useMomentum = false;
    public static boolean useRejuvenate = false;

    public static boolean eatFood = false;
    public static int foodId = 373;
    public static int withdrawFoodAmount = 15;

    public static int skillTraining = 1;

    public static boolean shouldLoot = false;
    public static boolean burryBones = false;
    public static boolean eatFoodForSpace = false;
    public static boolean lootByPrice = false;
    public static int minPriceToLoot = 1000;
    public static int lootAfter = 1;
    public static boolean shouldWaitForLoot = false;





    /*
    Filters
     */


    public static Filter<NPC> npcFilter = new Filter<NPC>() {
        @Override
        public boolean accept(NPC n) {
            return n.getName().equalsIgnoreCase("hill giant") && (n.getInteracting() == null )
                    && n.getAnimation() != DEATH_ID;
        }
    };
    public static Filter<NPC> priorityNPCFilter = new Filter<NPC>() {
        @Override
        public boolean accept(NPC n) {
            return n.getInteracting() != null && n.getInteracting().equals(Players.getLocal());
        }
    };



}
