package hillgiantkiller2.nodes;

import hillgiantkiller.other.Methods;
import hillgiantkiller2.Variables;
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

import java.util.Collections;

public class Loot extends Node {


    @Override
    public boolean activate() {

        return Variables.lootLocations.size() >= Variables.lootAfter ;
    }

    @Override
    public void execute() {
        System.out.println("In the loot node");
        Variables.isLooting = true;
        System.out.println("Tiles in list: "+Variables.lootLocations.size());
        Collections.reverse(Variables.lootLocations);
        for(Tile tile: Variables.lootLocations){
            GroundItem[] item = GroundItems.getLoadedAt(tile.getX(), tile.getY());//get all items on tile
            for(GroundItem i: item){
                if (Variables.lootIds.contains(i.getId())) {//if its a preset lood id

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
                } else if(Variables.lootByPrice && !Variables.priceTable.containsKey(i.getId())){//if its not a preset id but looting by price is enabled
                                                                                     //and price has not been cached
                    System.out.println("Item not in list: "+i.getGroundItem().getName()+". Looking up price");
                    Variables.priceTable.put(i.getId(),0); //temp place
                    new PriceLoader(i.getId()).start();
                    while(Variables.priceTable.get(i.getId()) == 0){
                        Task.sleep(50,100);
                    }
                    int price = Variables.priceTable.get(i.getId());
                    System.out.println("Item price: "+Variables.priceTable.get(i.getId()));
                    if((price != -1) && price*i.getGroundItem().getStackSize() >= Variables.minPriceToLoot){
                        System.out.println("Added to list");
                        Variables.lootIds.add(i.getId());
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
            Camera.turnTo(Variables.insideSafeZone);
            System.out.println("Inventory full; going to burry bones");
            do{
                Walking.findPath(Variables.insideSafeZone).traverse();
            }while(Calculations.distanceTo(Variables.insideSafeZone) >=2 );

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
            Variables.priceTable.put(itemId, geInfo != null ? geInfo.getPrice() : -1);
        }

    }

} //End of Node