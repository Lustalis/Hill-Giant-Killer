package hillgiantkiller.other;

import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Area;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.interactive.NPC;
import org.powerbot.game.api.wrappers.node.GroundItem;

import java.util.ArrayList;

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

    public static final Area DUNG_AREA =  new Area(new Tile[] { new Tile(3107, 3457, 0), new Tile(3108, 3442, 0), new Tile(3124, 3442, 0),
            new Tile(3124, 3458, 0) });

    public static final Area BANK_AREA = new Area(new Tile[] { new Tile(3140, 3491, 0), new Tile(3140, 3472, 0), new Tile(3151, 3465, 0),
            new Tile(3158, 3465, 0), new Tile(3157, 3490, 0) });

    public static final Area INSIDE_DUNG_AREA = new Area(new Tile[] {new Tile(3115,9854,0), new Tile(3117,9854,0), new Tile(3117,9850,0),
             new Tile(3115,9850,0)});

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
    public static ArrayList<Tile> lootLocations = new ArrayList<>();
    public static final int foodIds = 373;
    public static int[] lootIds = {532, 533, 17974, 17675, 225, 226} ;  //just hill giant bones right now
    public static final int HEAL_PERCENT = 50;
    public static final int DEATH_ID = 4653;

    /*
    Scene Entity stuff
     */

    public static final int DOOR_ID = 1804;
    public static final int STAIRS_DOWN = 12389;
    public static final int STAIRS_UP = 29355;

    /*
    Filters
     */

    public static Filter<GroundItem> lootFilter = new Filter<GroundItem>(){

        @Override
        public boolean accept(GroundItem g) {
            for(int i: Var.lootIds){
                return g.getId() == i && g.getLocation() == deathLocation;
            }
            return false;
        }
    };


    public static Filter<NPC> npcFilter = new Filter<NPC>() {
        @Override
        public boolean accept(NPC n) {
            if(n.getName().equalsIgnoreCase("hill giant") && (n.getInteracting() == null || n.getInteracting().equals(Players.getLocal()))
                    && n.getAnimation() != DEATH_ID) {
                return true;
            }
            return false;
        }
    };







}
