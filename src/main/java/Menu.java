/**
 * @author Nourish Khan (nkhan2@students.towson.edu)
 */

import java.util.List;
import java.util.ArrayList;

public class Menu {
    public static final boolean HEART_HEALTHY = true;
    public static final boolean NOT_HEART_HEALTHY = false;

    private List<MenuItem> menu;
    private MenuItem firstItem;    //constructor sets to null; updated after first append

    //methods
    public Menu() {
        menu = new ArrayList<MenuItem>();
        setFirstItem(null);
    }

    public MenuItem getFirstItem() {
        return firstItem;
    }

    public void setFirstItem(MenuItem firstItem) {
        this.firstItem = firstItem;
    }

    public void add(MenuItem m) {
        menu.add(m);
        if (menu.size() == 1) setFirstItem(m);
    }

    public void delete(MenuItem m) {
        if (firstItem == m) {
            if (menu.size() > 1) {
                int i = menu.indexOf(m);
                firstItem = menu.get(++i);
            } else
                firstItem = null;
        }
        menu.remove(m);
    }

    //Factory Methods

    public MenuIterator getAllItemsIterator() {
        return new AllItemsIterator(firstItem);
    }

    public MenuIterator getHeartHealthyIterator() {
        return new HeartHealthyIterator(firstItem);
    }

    public MenuIterator getItemIterator(int n) {
        return new ItemIterator(firstItem, n);
    }

    public MenuIterator getPriceIterator(String p) {
        return new PriceIterator(firstItem, Double.parseDouble(p));
    }

    //inner classes
    private class AllItemsIterator implements MenuIterator {
        int indexCurrent;
        int size;

        public AllItemsIterator(MenuItem m) {
            indexCurrent = menu.indexOf(m);
            size = menu.size();
        }

        public boolean hasNext() {
            return (indexCurrent < size);
        }

        public MenuItem next() {
            return menu.get(indexCurrent++);
        }

    }//end class AllItemsIterator


    private class HeartHealthyIterator implements MenuIterator {
        MenuItem item;
        int indexCurrent;
        int size;

        public HeartHealthyIterator(MenuItem m) {
            indexCurrent = menu.indexOf(m);
            size = menu.size();
        }

        public boolean hasNext() {
            if (indexCurrent < size) {
                do {
                    item = menu.get(indexCurrent++);
                } while (!item.getHeartHealthy() && indexCurrent < size);

                if (item.getHeartHealthy() == Menu.HEART_HEALTHY)
                    return true;
            }
            return false;
        }

        public MenuItem next() {
            return item;
        }
    }//end class HeartHealthyIterator

    private class ItemIterator implements MenuIterator {
        MenuItem item;
        int indexCurrent;
        int size;
        int category;

        public ItemIterator(MenuItem m, int category) {
            indexCurrent = menu.indexOf(m);
            size = menu.size();
            this.category = category;
        }

        public boolean hasNext() {
            if (indexCurrent < size) {
                do {
                    item = menu.get(indexCurrent++);
                } while (item.getCategory() != category && indexCurrent < size);
                if (item.getCategory() == category)
                    return true;
            }
            return false;
        }

        public MenuItem next() {
            return item;
        }

    }//end class ItemIterator

    private class PriceIterator implements MenuIterator {
        MenuItem item;
        int indexCurrent;
        int size;
        double price;

        public PriceIterator(MenuItem m, double price) {
            indexCurrent = menu.indexOf(m);
            size = menu.size();
            this.price = price;
        }

        public boolean hasNext() {
            if (indexCurrent < size) {
                do {
                    item = menu.get(indexCurrent++);
                } while (item.getPrice() >= price && indexCurrent < size);
                if (item.getPrice() < price)
                    return true;
            }
            return false;
        }

        public MenuItem next() {
            return item;
        }

    }//end PriceIterator

}//end class Menu