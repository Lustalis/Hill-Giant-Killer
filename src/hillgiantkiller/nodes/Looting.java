package hillgiantkiller.nodes;

import hillgiantkiller.other.Global;
import hillgiantkiller.other.Methods;
import hillgiantkiller.other.Paint;
import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.GroundItems;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.util.Filter;
import org.powerbot.game.api.wrappers.Tile;
import org.powerbot.game.api.wrappers.node.GroundItem;
import org.powerbot.game.api.wrappers.node.Item;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

public class Looting extends Node {
    /*
    Map and walking stuff
    */
    public static final Tile insideSafeZone = new Tile(3109,9848,0);
    public static int arrowId;
    public static List<Integer> lootIds = new ArrayList<>();
    public static Map<Integer, Integer> priceTable = new HashMap<>();
    public static List<Tile> lootLocations = new ArrayList<>();

    private Filter<Item> arrowFilter = new Filter<Item>() {
        @Override
        public boolean accept(Item item) {
            return item.getId() == arrowId;
        }
    };

    @Override
    public boolean activate() {

        return Global.shouldLoot && Methods.needToLoot() ;
    }

    @Override
    public void execute() {
        Paint.status = "Looting";
        System.out.println("In the loot node");
        System.out.println("Tiles in list: "+ lootLocations.size());
        Collections.reverse(lootLocations);
        for(Tile tile: lootLocations){
            if (!Inventory.isFull()) {
                final GroundItem[] item = GroundItems.getLoadedAt(tile.getX(), tile.getY());//get all items on tile
                for(GroundItem i: item){
                    if (lootIds.contains(i.getId())) {//if its a preset loot id
                        Global.stuffToDo.execute(new Global.MoveCamera(i));
//                        new Fight.MoveCamera(i);
                        if(!i.isOnScreen()){//Making sure item is on screen
                            Task.sleep(500);
                            if(!Methods.isOnScreen(i)){
                                Walking.walk(i);
                                Methods.waitForOnScreen(i);
                            }
                        }
                        if(!priceTable.containsKey(i.getId())){
                            priceTable.put(i.getId(), getPrice(i.getId())); //temp place
                        }
                        i.interact("Take", i.getGroundItem().getName());
                        Paint.profit += priceTable.get(i.getId());
                        Methods.waitForInvChange();
                        System.out.println("Picked up: " +i.getGroundItem().getName());
                    } else if(Global.lootByPrice && !priceTable.containsKey(i.getId())){//if its not a preset id but looting by price is enabled
                                                                                         //and price has not been cached
                        System.out.println("Item not in list: " + i.getGroundItem().getName() + ". Looking up price");
                        priceTable.put(i.getId(), getPrice(i.getId())); //temp place
                        final int price = priceTable.get(i.getId());
                        System.out.println("Item price: " + priceTable.get(i.getId()));
                        if((price != 0) && price*i.getGroundItem().getStackSize() >= Global.minPriceToLoot){
                            System.out.println("Added to list");
                            lootIds.add(i.getId());
                            Global.stuffToDo.execute(new Global.MoveCamera(i));
//                            new Fight.MoveCamera(i);
                            if(!i.isOnScreen()){
                                Task.sleep(500);
                                if(!i.isOnScreen()){
                                    Walking.walk(i);
                                    Methods.waitForOnScreen(i);
                                }
                            }
                            i.interact("Take", i.getGroundItem().getName());
                            Paint.profit += priceTable.get(i.getId());
                            Methods.waitForInvChange();
                            System.out.println("Picked up: " +i.getGroundItem().getName());

                        }
                    }
                }
            } else {
                break;
            }
        }
        Global.gKilled = 0;
        lootLocations.clear();
        System.out.println("Checking for loot another time");
        Methods.droppedLoot();

        //Eat food for space
        if(Global.eatFoodForSpace && Inventory.isFull() && Inventory.getCount(Global.foodId) != 0){
            Inventory.getItem(Global.foodId).getWidgetChild().interact("Eat");
        }

        //Bone Burying
        if(Global.burryBones && Inventory.isFull()){

            for(Item x: Inventory.getItems()){
                if(x.getId() == 532){
                    x.getWidgetChild().interact("Bury");
                    Methods.waitForInvChange();
                }
            }
        }

        //Arrows

        if(Global.isRange && Inventory.contains(arrowId)){
            for(Item i: Inventory.getItems(arrowFilter)){
                i.getWidgetChild().interact("Wield");
                Methods.waitForInvChange();
            }
        }


    }//End of Execute

    private static int getPrice(final int id) {
        final String add = "http://scriptwith.us/api/?return=text&item=" + id;
        try (final BufferedReader in = new BufferedReader(new InputStreamReader(
                new URL(add).openConnection().getInputStream()))) {
            final String line = in.readLine();
            return Integer.parseInt(line.split("[:]")[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

} //End of Node