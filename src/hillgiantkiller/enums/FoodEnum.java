package hillgiantkiller.enums;

/**
 * User: Stefano
 * Date: 06/04/13
 * Time: 2:38 AM
 */
public enum FoodEnum {
    LOBSTER("Lobster",379),
    SWORDFISH("Swordfish",373),
    SHARK("Shark",385),
    MONKFISH("Monkfish",7946),
    CUSTOMID("Custom ID",1);

    private int id;
    private String name;

    private FoodEnum(String n, int i){
        name = n;
        id = i;

    }

    String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return getName();
    }


}
