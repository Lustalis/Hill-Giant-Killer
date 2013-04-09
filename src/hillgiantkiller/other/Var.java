package hillgiantkiller.other;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.util.net.GeItem;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 3/27/13
 * Time: 8:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class Var {
    /*
     Map and walking stuff
     */
    public static final Tile insideSafeZone = new Tile(3109,9848,0);

    public static final Area DUNG_AREA =  new Area(new Tile(3107, 3457, 0), new Tile(3108, 3442, 0), new Tile(3124, 3442, 0),
            new Tile(3124, 3458, 0));

    public static final Area BANK_AREA = new Area(new Tile(3140, 3491, 0), new Tile(3140, 3472, 0), new Tile(3151, 3465, 0),
            new Tile(3158, 3465, 0), new Tile(3157, 3490, 0));

    public static final Area INSIDE_DUNG_AREA = new Area(new Tile(3115,9854,0), new Tile(3117,9854,0), new Tile(3117,9850,0),
            new Tile(3115,9850,0));

    public static final Tile[] PATH_1 = new Tile[] { new Tile(3150, 3474, 0), new Tile(3146, 3464, 0), new Tile(3136, 3456, 0),
            new Tile(3127, 3455, 0), new Tile(3114, 3446, 0) };

    public static final Tile[] PATH_2 = new Tile[] { new Tile(3115, 3445, 0), new Tile(3124, 3451, 0), new Tile(3131, 3455, 0),
            new Tile(3139, 3456, 0), new Tile(3145, 3456, 0), new Tile(3152, 3456, 0),
            new Tile(3158, 3460, 0), new Tile(3150, 3474, 0) };

    /*
    Combat stuff
     */

    public static final int[] NPC_IDS = {117, 4689, 4690, 4691, 4692, 4693};
    public static NPC theGiant = null;
    public static Tile deathLocation = null;
    public static final int HEAL_PERCENT = 50;
    public static final int DEATH_ID = 4653;


    /*
    Looting stuff
     */
    public static boolean isLooting = false;
    public static boolean isAdding = false;
    //public static int[] lootIds = {532, 225} ;  //just hill giant bones right now
    public static ArrayList<Integer> lootIds =  new ArrayList<>();
    public static ArrayList<Tile> lootLocations = new ArrayList<>();
    public static Hashtable<Integer, Integer> priceTable = new Hashtable<>();
    public static boolean waitingForLoot = false;


    /*
    Scene Entity stuff
     */

    public static final int DOOR_ID = 1804;
    public static final int STAIRS_DOWN = 12389;
    public static final int STAIRS_UP = 29355;

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
            return n.getName().equalsIgnoreCase("hill giant") && (n.getInteracting() == null || n.getInteracting().equals(Players.getLocal()))
                    && n.getAnimation() != DEATH_ID;
        }
    };
    public static Filter<NPC> priorityNPCFilter = new Filter<NPC>() {
        @Override
        public boolean accept(NPC n) {
            return n.getInteracting() != null && n.getInteracting().equals(Players.getLocal());
        }
    };

    private final Filter<GroundItem> LootFilter = new Filter<GroundItem>() {

        private final Hashtable<Integer, Integer> PriceTable = new Hashtable<>();

        private int MIN_PRICE = 1000;

        public boolean accept(GroundItem loot) {
            final Item item = loot.getGroundItem();

            if (item == null)
                return false;

            Integer price = PriceTable.get(item.getId());
            if (price == null) {
                new PriceLoader(item.getId()).start();
                PriceTable.put(item.getId(), 0);
                price = 0;
            }
            return price * item.getStackSize() >= MIN_PRICE;
        }

        class PriceLoader extends Thread {

            private final int ItemId;

            public PriceLoader(final int ItemId) {
                this.ItemId = ItemId;
            }

            @Override
            public void run() {
                GeItem geInfo = GeItem.lookup(ItemId);
                if(geInfo == null) {
                    geInfo = GeItem.lookup(ItemId-1);
                }
                PriceTable.put(ItemId, geInfo != null ? geInfo.getPrice() : 0);
            }

        }
    };







}
