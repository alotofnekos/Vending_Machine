import java.util.ArrayList;
/**
 * This handles the workings of the special vending machine.
 */
public class SpcVnd extends RgVnd {
    private SpcStock spcItemStock;
    private SpcStock spcItemSold;

    /**
     * This is the constructor for the SpcVnd class that initializes the SpcStocks for
     * special items and the BillWallets for the vending machine and the user.
     */
    public SpcVnd() {
        super();
        spcItemStock = new SpcStock();
        spcItemSold = new SpcStock();
    }
    /**
     * Checks if a cake is valid
     * @param cakeIndex the index of the cake to be checked.
     * @return true if cake is valid, false otherwise
     */
    public boolean validSpcItem(int cakeIndex){
        return spcItemStock.validItem(cakeIndex);
    }
    /**
     * Checks if a cake is valid
     * @param index the index of the itwm to be checked.
     * @return the price of the item
     */
    public int getItemPrice(int index){
        return spcItemStock.getItemPrice(index);
    }
    /**
     * Process a purchase transaction for a cake and its add-ons.
     *
     * This method handles the purchase transaction for a cake and its add-ons. It takes the total amount
     * inserted by the user, the index of the selected cake, and an ArrayList of item indices representing
     * the selected add-ons. The machine then processes the transaction accordingly, then informs the user of the output. 
     * It also clears the user's wallet.
     *
     * @param totalUserBill The total amount inserted by the user.
     * @param cakeIndex The index of the selected cake.
     * @param itemIndex An ArrayList of item indices representing the selected add-ons.
     * @return A String containing the output messages for the purchase transaction.
     */
    public String purchaseItem(int totalUserBill, int cakeIndex, ArrayList<Integer> itemIndex) {
        boolean canGiveChange;
        boolean isItemValid = false;
        boolean isAddOnValid = false;
        boolean hasCakeInStock = vndItemStock.hasCakeInStock();
        String output = "";
        int totalPrice=0;

        if (!hasCakeInStock) {
            output = output.concat("Can't buy items, no items in stock.\n");
            return output;
        }

        if (vndStock.getTotalAmount() == 0) {
            output = output.concat("The Vending Machine does not have money to process change right now!\n");
            canGiveChange = false;
        } else {
            output = output.concat("The Vending Machine can process change right now!\n");
            canGiveChange = true;
        }

        output = output.concat("Total amount inserted: " +totalUserBill+"\n");
        if(totalUserBill==0){
            output = output.concat("Please insert cash first before continuing your transaction");
            return output;
        }
        if (cakeIndex == -1) {
            for (int i = 0; i < itemIndex.size(); i++) {
                int index = itemIndex.get(i);
                String itemName = spcItemStock.getItemName(index);
                if (itemName.contains("Upgrade") || itemName.contains("Ice Cream")) {
                    output = output.concat(itemName + " cannot be bought by itself. Please try again.\n");
                    return output;
                }
            }
            for (int i = 0; i < itemIndex.size(); i++) {
                int index = itemIndex.get(i);
                totalPrice += spcItemStock.getItemPrice(index);
            }
        }
        
        else{
            isItemValid = vndItemStock.validItem(cakeIndex);
            if (!isItemValid) {
                output = output.concat("Item is out of stock. Please try again.\n");
                userWallet.clearWallet();
                return output;
            }
            totalPrice += vndItemStock.getCakePrice(cakeIndex);
            // Check if all special items are valid
            for (int i = 0; i < itemIndex.size(); i++) {
                int index = itemIndex.get(i);
                isAddOnValid = (spcItemStock.validItem(index) && spcItemStock.isCompatible(index, vndItemStock.getCakeName(cakeIndex)));
                if (!isAddOnValid) {
                    output = output.concat("Special item " + spcItemStock.getItemName(index) + " is out of stock or is incompatible with the cake. Please try again.\n");
                    userWallet.clearWallet();
                    return output;
                }
            }
                for (int i = 0; i < itemIndex.size(); i++) {
                    int index = itemIndex.get(i);
                    totalPrice += spcItemStock.getItemPrice(index);
            }
        }
        if (canGiveChange == false) {
            if (totalUserBill == totalPrice) {
                output = output.concat(vndStock.pay(totalUserBill, totalPrice));
                userWallet.clearWallet();
                profit += totalPrice;
                if(cakeIndex!=-1){
                    for (int i = 0; i < itemIndex.size(); i++) {
                        int index = itemIndex.get(i);
                        spcItemStock.transferItem(spcItemSold, index);
                        output = output.concat(spcItemStock.getFT(index) + "\n");
                    }
                    output = output.concat("Payment successful. Dispensing " + vndItemStock.getCakeName(cakeIndex) + ".\n");
                    vndItemStock.transferCake(vndItemSold, cakeIndex);
                }
                else{
                    for (int i = 0; i < itemIndex.size(); i++) {
                        int index = itemIndex.get(i);
                        spcItemStock.transferItem(spcItemSold, index);
                        output = output.concat("Payment successful. Dispensing " + spcItemStock.getItemName(index) + ".\n");
                    }
                }
                
            } else {
                output = output.concat("Please pay exact amount. No change available.\n");
                userWallet.clearWallet();
                return output;
            }
        } else {
            if (!vndStock.pay(totalUserBill, totalPrice).contains("Insufficient bills") && (totalUserBill >= totalPrice)) {
                output = output.concat(vndStock.pay(totalUserBill, totalPrice));
                userWallet.clearWallet();
                profit += totalPrice;
                if(cakeIndex!=-1){
                    for (int i = 0; i < itemIndex.size(); i++) {
                        int index = itemIndex.get(i);
                        spcItemStock.transferItem(spcItemSold, index);
                        output = output.concat(spcItemStock.getFT(index) + "\n");
                    }
                    output = output.concat("Payment successful. Dispensing " + vndItemStock.getCakeName(cakeIndex) + ".\n");
                    vndItemStock.transferCake(vndItemSold, cakeIndex);
                }
                else{
                    for (int i = 0; i < itemIndex.size(); i++) {
                        int index = itemIndex.get(i);
                        spcItemStock.transferItem(spcItemSold, index);
                        output = output.concat("Payment successful. Dispensing " + spcItemStock.getItemName(index) + ".\n");
                    }
                }
            } 
            else if (totalUserBill < totalPrice) {
                output = output.concat("Insufficient funds. Please add more bills.\n");
                userWallet.clearWallet();
            } 
            else{
                output = output.concat("Please pay exact amount. No change available.\n");
                userWallet.clearWallet();
            }
        }
        return output;
    }

    /**
     * Sets the defaults (10 for every item, cake, and bill)
     */
    @Override
    public void setDefaults(){
        //sets stock of ten for all bills
        int defaultStock = 10;
        System.out.println("Setting defaults...");
        for (int i = 0; i < vndStock.getBills().length; i++) {
                int billValue = vndStock.getBills()[i].getValue();
                System.out.println(defaultStock + " " + billValue + " Pesos added to the vending machine by setting defaults!");
                vndStock.addBill(new Bill(defaultStock, billValue));
        }
        //set a preset stock for cakes
        vndItemStock.setStock(defaultStock);
        spcItemStock.setStock(defaultStock);
        System.out.println("Defaults set!");
    }
    /**
     * Gets the list of cakes and items bought before a profit is collected
     */
    @Override
    public String receipt(){
        String totalReceipt="";
        totalReceipt = totalReceipt.concat(vndItemSold.getReceipt());
        totalReceipt = totalReceipt.concat(spcItemSold.getReceipt());
        return totalReceipt;
    }
    /**
     * Display item details
     * @param index the index of the item to be checked
     * @return item details
     */
    public String getItemDetails(int index){
        return spcItemStock.displayDetails(index);
    }
/**
 * Collect the accumulated profit and reset the vending machine's statistics.
 *
 * The method retrieves the current profit value, prints a message displaying the total profit obtained,
 * and then clears the profit by setting it to zero. Additionally, it sets the stocks of both regular
 * and special items to zero, indicating that all items have been sold have been taken note of.
 *
 * @return The total profit obtained from the vending machine.
 */
    @Override
    public int collectProfit(){
        int vndProfit = profit;
        System.out.println("Total Profit obtained: "+profit+ ". Clearing wallet.");
        profit=0;
        vndItemSold.setStock(0);
        spcItemSold.setStock(0);
        return vndProfit;
    }
    /**
     * Adds a new item to the vending machine's stock. Reciept's copy also adds the new cake to prevent discrepancies, but the quantity is set to zero since its not bought.
     * @param strName the name of the item
     * @param strDesc the description of the item
     * @param intCalorie the calorie amount of the item
     * @param intPrice the price of the item
     * @param intQuantity the amount of item to be added
     * @param strTag the item tag
     * @param strFT the Flavor Text of the item
     * @return the results of the transaction
     */
    public String addNewItem(String strName, String strDesc, int intCalorie, int intPrice, int intQuantity,String strTag, String strFT){
        String output="";
        if(profit!=0){
            output=output.concat("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            output=output.concat(strName + " was added!");
            spcItemStock.addNewSpcItem(strName,strDesc, intCalorie,intPrice, intQuantity, strTag, strFT);
            spcItemSold.addNewSpcItem(strName,strDesc, intCalorie,intPrice, intQuantity, strTag, strFT);
        }
        return output;
       
    }
    /**
     * Deletes the item at index. Reciept's copy also deletes the cake to prevent discrepancies.
     * @param index the index of the item to be deleted
     * @return the results of the transaction
     */
    public String deleteAnItem(int index){
        String output="";
        if(profit!=0){
            output=output.concat("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            output=output.concat(spcItemStock.getItemName(index) +" was deleted.");
            spcItemStock.deleteItem(index);
            spcItemSold.deleteItem(index);
        }
        return output;
    }
    /**
     * Edits the cake at cakeIndex, based on choice, with an integer value. Some features are also passed down to the reciept's copy of the inventory, unless it pertains about the quantity.
     * @param choice tells which parameter to edit (13 for Calorie, 14 for Price, 15 for adding Stock, 16 for removing Stock)
     * @param index the index of the index to be edited
     * @param value the new value
     * @return the entire transaction dialog
     */
    public String editItem(int choice, int index, int value){
        String output="";
        if(profit!=0){
            output = output.concat("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            if(choice==13){
                output = output.concat("Calorie of " + spcItemStock.getItemName(index) + " changed to " + value +"!");
                spcItemStock.editCalorie(index, value);
                spcItemSold.editCalorie(index, value);
            }
            else if(choice==14){
                output = output.concat("Price of " + spcItemStock.getItemName(index) +  " changed to " + value +"!");
                spcItemStock.editPrice(index, value);
                spcItemSold.editPrice(index, value);
            }
            else if(choice ==15){
                output = output.concat(spcItemStock.addInt(index, value));
            } 
            else if(choice==16){
                output = output.concat(spcItemStock.subInt(index, value));
            }
            else{
                output = output.concat("Invalid...");
            }
        }
        return output;
    }
    /**
     * Edits the iten at index, based on choice, with a String value. These features are also passed down to the reciept's copy of the inventory.
     * @param choice tells which parameter to edit (11 for name, 12 for description)
     * @param index the index of the item to be edited
     * @param value the new value
     * @return the transaction message
     */
    public String editItem(int choice, int index, String value){
        String output="";
        if(profit!=0){
            output = output.concat("Get the profit first before editing the cake parameters. Operation aborted");
        }
        else{
            if(choice==11){
                output = output.concat("Name of " + spcItemStock.getItemName(index)+" changed to " + value +"!");
                spcItemStock.editName(index, value);
                spcItemSold.editName(index, value);
            }
            else if(choice == 12){
                output = output.concat("Description of " +spcItemStock.getItemName(index) +" changed to " + value +"!");
                spcItemStock.editDesc(index, value);
                spcItemSold.editDesc(index, value);
            }
        }
        return output;
    }
}
