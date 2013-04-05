package hillgiantkiller.other;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 4/5/13
 * Time: 11:33 AM
 * To change this template use File | Settings | File Templates.
 */
public enum LootID {

    BONES(1,"Bones");

    private int id;
    private String name;

    private LootID(int itemID, String itemName){
        id = itemID;
        name = itemName;
    }

}
