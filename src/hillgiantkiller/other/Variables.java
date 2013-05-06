package hillgiantkiller.other;

import org.powerbot.game.api.wrappers.Tile;

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
    GUI Variables
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

}
