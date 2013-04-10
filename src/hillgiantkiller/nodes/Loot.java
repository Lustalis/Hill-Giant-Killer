package hillgiantkiller.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Variables;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.net.GeItem;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class Loot extends Node {
    /*
    Map and walking stuff
    */
    public static final Tile insideSafeZone = new Tile(3109,9848,0);
    //public static int[] lootIds = {532, 225} ;  //just hill giant bones right now
    public static ArrayList<Integer> lootIds =  new ArrayList<>();
    private Hashtable<Integer, Integer> priceTable = new Hashtable<>();


    @Override
    public boolean activate() {

        return Variables.shouldLoot && Methods.needToLoot() ;
    }

    @Override
    public void execute() {
        System.out.println("In the loot node");
        System.out.println("Tiles in list: "+Variables.lootLocations.size());
        Collections.reverse(Variables.lootLocations);
        for(Tile tile: Variables.lootLocations){
            GroundItem[] item = GroundItems.getLoadedAt(tile.getX(), tile.getY());//get all items on tile
            for(GroundItem i: item){
                if (lootIds.contains(i.getId())) {//if its a preset lood id

                    if(!Methods.isOnScreen(i)){//Making sure item is on screen
                        Camera.turnTo(i);
                        Task.sleep(500);
                        if(!Methods.isOnScreen(i)){
                            Walking.walk(i);
                            Methods.waitForOnScreen(i);
                        }
                    }
                    i.interact("Take", i.getGroundItem().getName());
                    Methods.waitForInvChange(i.getId());
                    System.out.println("Picked up: " +i.getGroundItem().getName());
                } else if(Variables.lootByPrice && !priceTable.containsKey(i.getId())){//if its not a preset id but looting by price is enabled
                                                                                     //and price has not been cached
                    System.out.println("Item not in list: "+i.getGroundItem().getName()+". Looking up price");
                    priceTable.put(i.getId(),0); //temp place
                    new PriceLoader(i.getId()).start();
                    while(priceTable.get(i.getId()) == 0){
                        Task.sleep(50,100);
                    }
                    int price = priceTable.get(i.getId());
                    System.out.println("Item price: " + priceTable.get(i.getId()));
                    if((price != -1) && price*i.getGroundItem().getStackSize() >= Variables.minPriceToLoot){
                        System.out.println("Added to list");
                        lootIds.add(i.getId());
                        if(!Methods.isOnScreen(i)){
                            Camera.turnTo(i);
                            Task.sleep(500);
                            if(!Methods.isOnScreen(i)){
                                Walking.walk(i);
                                Methods.waitForOnScreen(i);
                            }
                        }
                        i.interact("Take", i.getGroundItem().getName());
                        Methods.waitForInvChange(i.getId());
                        System.out.println("Picked up: " +i.getGroundItem().getName());

                    }
                }


            }


        }
        Variables.lootLocations.clear();
        Methods.droppedLoot();
        if(Variables.eatFoodForSpace && Inventory.isFull() && Inventory.getCount(Variables.foodId) != 0){
            Inventory.getItem(Variables.foodId).getWidgetChild().interact("Eat");
        }
        if(Variables.burryBones && Inventory.isFull()){
            Camera.turnTo(insideSafeZone);
            System.out.println("Inventory full; going to burry bones");
            do{
                Walking.findPath(insideSafeZone).traverse();
            }while(Calculations.distanceTo(insideSafeZone) >=2 );

            for(Item x: Inventory.getItems()){
                if(x.getId() == 532){
                    x.getWidgetChild().interact("Bury");
                    Methods.waitForInvChange(532);
                }
            }


        }

    }//End of Execute

    class PriceLoader extends Thread {

        private final int itemId;

        public PriceLoader(final int itemId) {
            this.itemId = itemId;
        }

        @Override
        public void run() {
            GeItem geInfo = GeItem.lookup(itemId);
            if(geInfo == null) {
                geInfo = GeItem.lookup(itemId -1);
            }
            priceTable.put(itemId, geInfo != null ? geInfo.getPrice() : -1);
        }

    }

} //End of Node