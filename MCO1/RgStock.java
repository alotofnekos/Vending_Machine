
/**
 * The class RgStock handles everything pertaining to a set of items, including transferring, deleting, validating, and getting cakes. 
 * Individual cakes may also be edited here, if supplied with the correct index.
 */ 
public class RgStock{
    private RgItem[] item;
/**
 * This is a constructor that initializes everything for the default cakes except the quantity for the cakes
 *
 */
    public RgStock() {
        item = new RgItem[20]; // Initialize the item array with 20 elements

        item[0] = new RgItem("Angel's Apple Crumble", 0, 160, 156, "Treat yourself with our irresistible apple crumble! Indulge in layers of tender apples, delicately spiced with cinnamon, and crowned with a mouthwatering crumbly crust.");
        item[1] = new RgItem("Black Forest Cake", 0, 140, 282, "Savor the taste of the classic Black Forest Cake, where the tangy sweetness of cherries perfectly complements the rich chocolate layers, all brought together with light and fluffy cream.");
        item[2] = new RgItem("Premium Chocolate Cake", 0, 190, 389, "Treat yourself with this moist, rich, and bittersweet Chocolate cake, adorned with a lucious dark chocolate ganache.");
        item[3] = new RgItem("Strawberry Shortcake", 0, 250, 247, "Indulge in the freshness of Benguet's finest strawberries with our Strawberry Bliss Short Cake. Enjoy the perfect blend of delicate sponge cake, heavenly cream, and juicy strawberries, sourced directly from the strawberry capital of the Philippines.");
        item[4] = new RgItem("Lemon Blueberry Cake", 0, 240, 244, "Enjoy the vibrant combination of zesty lemon-infused cake, bursting blueberries, and the smooth cream cheese frosting that creates a perfect balance of flavors with this Lemon Blueberry cake.");
        item[5] = new RgItem("Chocolate Mousse Cake", 0, 220, 349, "Revel in joy with a decadent dessert featuring layers of moist chocolate cake and creamy chocolate mousse, altogether forming a rich and indulgent treat.");
        item[6] = new RgItem("Butterscotch Cake", 0, 280, 379, "A delectable dessert with a moist and fluffy base infused with the rich and caramel-like flavor of butterscotch, topped with creamy butterscotch frosting and caramel sauce.");
        item[7] = new RgItem("Mocha Delight Cake", 0, 210, 308, "Enjoy a heavenly dessert featuring layers of moist chocolate cake infused with the bold flavors of coffee and espresso, complemented by a velvety mocha frosting for a delightful and indulgent treat.");
        item[8] = new RgItem("Mango Surprise Cake", 0, 230, 228, "A tropical delight that combines layers of moist mango-infused cake with a luscious mango filling, providing a delightful surprise of fruity sweetness in every bite.");
        item[9] = new RgItem("Tristan's Carrot Cake", 0, 180, 266, "Treat yourself with a moist and flavorful dessert made with grated carrots, warm spices, and a rich cream cheese frosting, offering a delightful combination of sweet and spiced flavors.");

        for (int i = 10; i < item.length; i++) {
            item[i] = new RgItem(); // Initialize the remaining elements with default RgItem objects
        }
    }
/**
 * Sets a quantity of intQty for all named cakes. If intQty = 0 the output text is muted as its for clearing the receipt [shouldn't be told to the end user]. 
 *  @param intQty the quantity of cakes to be set
 * 
 */
    public void setStock(int intQty){
        if(intQty!=0){
            for(int i = 0;i<item.length;i++){
                if(!item[i].getName().equals(" ")){
                    System.out.println(intQty + " " + item[i].getName() + "s added!");
                    item[i].setQty(intQty);
                }
                
            }
        }
        else{
            for(int i = 0;i<item.length;i++){
                if(!item[i].getName().equals(" ")){
                    item[i].setQty(intQty);
                }
            }            
        }
    }
/**
 * @deprecated
 * Displays the cake menu, to be shown when purchasing an item. An item with quantity - 0 will not be shown.
 *   
 */
    public void displayMenu(){
        for(int i = 0;i<item.length;i++){
            if(validItem(i)){
                int j = i+1;
                System.out.println("Cake number: "+ j);
                item[i].displayItem();
            }
        }        
    }
/**
 * Displays the details of a cake item at the specified index.
 *
 * If the item at the given index exists, its details will be returned in a formatted string.
 * If the item at the given index does not exist, a default message with placeholders for the
 * item's name, description, calorie count, price, and available quantity will be returned.
 *
 * @param cakeIndex The index of the cake item to display details for.
 * @return A formatted string containing the details of the cake item at the specified index, or
 *         a default message if the item does not exist.
 */
    public String displayDetails(int cakeIndex){
        if(!item[cakeIndex].getName().equals(" ")){
            return item[cakeIndex].displayItem();
        }
        else{
            return  "Name: " 
            + "\nDescription: " 
            + "\nCalorie Count: " 
            + "\nPrice: " 
            + "\nAvailable Quantity: ";
        }
        
    }
/**
 * Deletes a cake.
 * @param cakeIndex the index of the cake to be deleted
 * 
 */
    public void deleteCake(int cakeIndex){
        item[cakeIndex].deleteCake();
    }
/**
 * Edits the calorie of the cake at cakeIndex with value.
 * @param cakeIndex the cakeIndex of the cake to be edited
 * @param intCalorie the calorie amount
 */
    public void editCalorie(int cakeIndex, int intCalorie){
        item[cakeIndex].setCalorie(intCalorie);
    }
/**
 * Edits the price of the cake at cakeIndex with value.
 * @param cakeIndex the cakeIndex of the cake to be edited
 * @param intPrice the new Price
 */
    public void editPrice(int cakeIndex, int intPrice){
        item[cakeIndex].setPrice(intPrice);
    }
/**
 * Increases the stock quantity of a cake item at the specified index by the given value.
 * If the resulting quantity exceeds the maximum capacity of 10, no changes will be made, and
 * an error message indicating an invalid number will be returned.
 *
 * @param cakeIndex The index of the cake item to increase the stock quantity for.
 * @param value The value by which to increase the stock quantity of the cake item.
 * @return A message indicating the result of the operation. If the stock quantity was increased
 *         successfully, the message will confirm the new stock quantity. If the operation failed
 *         due to exceeding the maximum capacity, the message will indicate an invalid number.
 */
    public String addInt(int cakeIndex, int value){
        String output="";
        if(item[cakeIndex].getQty() + value <= 10){
            item[cakeIndex].changeQtyAdd(value);
            output=output.concat("Stock of "+ item[cakeIndex].getName() +  " increased by " + value +"!");
        }
        else{
            output=output.concat("Invalid Number. Exceeds Total Capacity of 10");
        }
        return output;
    }
/**
 * Decreases the stock quantity of a cake item at the specified index by the given value.
 * If the resulting quantity would be less than 0, no changes will be made, 
 * and an error message indicating an invalid number will be returned.
 *
 * @param cakeIndex The index of the cake item to decrease the stock quantity for.
 * @param value The value by which to decrease the stock quantity of the cake item.
 * @return A message indicating the result of the operation. If the stock quantity was decreased
 *         successfully, the message will confirm the new stock quantity. If the operation failed
 *         due to a resulting quantity less than 0, the message will indicate an invalid number.
 */
    public String subInt(int cakeIndex, int value){
        String output="";
        if(item[cakeIndex].getQty() - value >= 0){
            item[cakeIndex].changeQtySub(value);
            output=output.concat("Stock of "+ item[cakeIndex].getName() + " decreased by " + value +"!");
        }
        else{
            output=output.concat("Invalid Number. Quantity Results to Less Than 0");
        }
        return output;
    }
/**
 * Edits the name of the Cake at cakeIndex.
 * @param cakeIndex the cakeIndex of the cake to be edited
 * @param strName the new name of the cake
 */
    public void editName(int cakeIndex, String strName){
        item[cakeIndex].setName(strName);
    }
/**
 * Edits the description of the Cake at cakeIndex.
 * @param cakeIndex the cakeIndex of the cake to be edited
 * @param value the new description of the cake
 */
    public void editDesc(int cakeIndex, String value){
        item[cakeIndex].setDesc(value);
    }
/**
 * Adds a New Cake.
 * @param strName the name of the cake
 * @param strDesc the description of the cake
 * @param intCalorie the calorie amount of the cake
 * @param intPrice the price of the cake
 * @param intQuantity the amount of cakes to be added
 * 
 */
    public void addNewCake(String strName, String strDesc, int intCalorie, int intPrice, int intQuantity){
        int cakeIndex = countCakes();
        item[cakeIndex] = new RgItem(strName, intQuantity, intPrice, intCalorie, strDesc);
    }
/**
 * Provides the index number of named cakes. Cakes set by default are still counted, even though they are deleted.
 * @return int  Number of named cakes [not necessarily on stock] in rgStock
 * 
 */

        public int countCakes() {
            int intLoop;
            int intCounter = 9;
            for (intLoop = 9; intLoop < 20; intLoop++) {
                if (!item[intLoop].getName().equals(" ")) {
                    intCounter = intCounter + 1;
                }
        }
        return intCounter;
    }
/**
 * Transfer a cake to another inventory when its bought
 *
 * @param rgStock Where the cake is to be transferred. 
 * @param cakeIndex The position of the cake in the item[] array
 *
 */
    public void transferCake(RgStock rgStock, int cakeIndex) {
        rgStock.item[cakeIndex].changeQtyAdd(1);
        this.item[cakeIndex].changeQtySub(1);
    }

/**
 * Check if the cake at item[cakeIndex] is valid (exists and is not out of stock)
 *  @param cakeIndex  The index of the cake in the item array.
 *  @return boolean  Returns true if the cake is valid, false otherwise.
 */
    public boolean validItem(int cakeIndex) {
        return (cakeIndex >= 0 && cakeIndex < item.length && !item[cakeIndex].getName().equals(" ") && item[cakeIndex].getQty() > 0);
    }

/**
 * Checks if there's any cake in stock
 * @return boolean true if any cake is in stock, false otherwise
 */
    public boolean hasCakeInStock() {
        for (int i = 0; i < item.length; i++) {
            if (item[i].getQty() > 0) {
                return true; // Found a cake with stock
            }
        }
        return false; // No cakes have stock
    }

/**
 * Generates a receipt containing the names, quantities, and total prices of valid cake items in the vending machine.
 *
 * This method iterates through the cake items in the vending machine bought stock and constructs a receipt
 * showing the name, quantity, and total price of each valid cake item. A valid cake item is one
 * that is not empty or out of stock. The receipt is formatted with the cake name, quantity, and
 * total price displayed in columns for easy readability.
 *
 * @return A formatted string representing the receipt with cake names, quantities, and total
 *         prices.
 */
    public String getReceipt() {
        String output="";
        output=output.concat("Cake Name\t\t\tQty\tPrice\n");
        for (int i = 0; i < item.length; i++) {
            if (validItem(i)) {
                RgItem cake = item[i];
                int quantity = cake.getQty();
                int totalPrice = cake.getPrice() * quantity;
                output=output.concat(cake.getName() + "\t\t" + quantity + "\t" + "P" + totalPrice+"\n");
            }
        }
        return output;
    }

/**
 * Get the price of the cake at the given index.
 *
 * @param cakeIndex The index of the cake in the item array.
 * @return The price of the cake.
 */
    public int getCakePrice(int cakeIndex) {
        return item[cakeIndex].getPrice();
    }
/**
 * Gets the name of the cake.
 * @param cakeIndex the index of the cake to be named.
 * @return the name of the cake
 */
    public String getCakeName(int cakeIndex) {
        return item[cakeIndex].getName();
    }
}
