package session_four_constructorsandjavakeywords.assigment_problems;

class Item {

    String itemName;
    int stock;

    // Constructor with field/parameter naming clash
    public Item(String itemName, int stock) {
        this.itemName = itemName;
        this.stock = stock;
    }

    // Method with field/parameter naming clash
    public void restock(int stock) {
        this.stock = this.stock + stock;
    }

    public void printStock() {
        System.out.println(
                itemName + " | Final Stock: " + stock
        );
    }
}

public class CanteenInventoryDemo {

    public static void main(String[] args) {

        Item[] items = {

                new Item("Samosa", 15),
                new Item("Tea Powder", 40),
                new Item("Bread", 8),
                new Item("Biscuit Packs", 25)
        };

        // Restock every item with 20 units
        for (Item item : items) {

            item.restock(20);
            item.printStock();
        }
    }
}