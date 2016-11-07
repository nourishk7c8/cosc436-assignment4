/**
 * Created by developer on 11/7/2016.
 */
public enum MenuItemType {
    MAIN_DISH(1, "Main Dish"), APPETIZER(2, "Appetizer"), DESSERT(3, "Dessert");

    private int code;
    private String name;

    MenuItemType(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode(){
        return code;
    }

    public String getName(){
        return name;
    }

    /**
     * Given a valid code, find the corresponding MenuItemType
     * @param code int code that corresponds to a MenuItemType
     * @return MenuItemType that the code corresponds to
     *
     * @throws NullPointerException if code was invalid and no corresponding MenuItemType exists
     */
    public static MenuItemType getType(int code){
        MenuItemType type = null;
        for(MenuItemType i : values()) {
            if (code == i.getCode()) {
                type = i;
                break;
            }
        }
        if(type == null)
            throw new NullPointerException();
        return type;
    }
}
