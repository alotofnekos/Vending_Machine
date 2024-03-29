

 /**
 * The class  RgItem defines the regular items to be sold in the vending machine.
 * These include the name, description, quantity, calorie amount, and price of a cake. These features may also be displayed and edited accordingly, and a full summary can be obtained with display cake.  
 */ 
public class RgItem{
    
    private int intQty;
    private int intPrice;
    private int intCalorie;
    private String strDesc;
    private String strName;
/**
 * This is a constructor for a known item
 *
 * @param strName  The String Name of the item. 
 * @param intQty  The Integer Quantity of the item. 
 * @param intPrice  The Integer Price of the item.
 * @param intCalorie  The Integer Calorie of the item. 
 * @param strDesc  The String Description of the item. 
 */
    public RgItem(String strName, int intQty, int intPrice, int intCalorie, String strDesc){
        this.strName = strName;
        this.intQty = intQty;
        this.intPrice = intPrice;
        this.intCalorie=intCalorie;
        this.strDesc=strDesc;
   }
/**
 * This is a constructor for an empty item, which can be filled up if addCake() function is used.
 */
    public RgItem(){ 
        intQty = 0;
        intPrice = 0;
        intCalorie = 0;
        strDesc = " ";
        strName = " ";
    }
/**
 * Sets the name of the item.
 *
 * @param strName  The String Name of the item.  
 */
    public void setName(String strName){ 
        this.strName = strName;
    }
/**
 * Sets the description of the item.
 *
 * @param strDesc  The String Description of the item.  
 */
    public void setDesc(String strDesc){ 
        this.strDesc = strDesc;
    }
/**
 * Sets the calorie of the item.
 *
 * @param intCalorie  The Integer Calorie of the item.  
 */
    public void setCalorie(int intCalorie){ 
        this.intCalorie = intCalorie;
    }
/**
 * Sets the price of the item.
 *
 * @param intPrice  The Integer Price of the item.  
 */
    public void setPrice(int intPrice){ 
        this.intPrice = intPrice;
    }
/**
 * Sets the quantity of the item.
 *
 * @param intQty  The Integer Quantity of the item.  
 */
    public void setQty(int intQty){ 
        this.intQty = intQty;
    }
/**
 * Gets the name of the item.
 *
 * @return  The String Name of the item. 
 */
    public String getName(){ 
        return strName;
    }
/**
 * Gets the description of the item.
 *
 * @return  The String Description of the item. 
 */
    public String getDesc(){ 
        return strDesc;
    }
/**
 * Gets the calorie of the item.
 *
 * @return  The Integer Calorie of the item. 
 */
    public int getCalorie(){ 
        return intCalorie;
    }
/**
 * Gets the price of the item.
 *
 * @return  The Integer Price of the item. 
 */
    public int getPrice(){ 
        return intPrice;
    }
/**
 * Gets the quantity of the item.
 *
 * @return  The Integer Quantity of the item. 
 */
    public int getQty(){ 
        return intQty;
    }
/**
 * Changes the quantity of the item through subtraction.
 *
 * @param intQty  The Integer quantity to be subtracted. 
 */
    public void changeQtySub(int intQty){ 
        this.intQty = this.intQty - intQty;
    }
/**
 * Changes the quantity of the item through addition.
 *
 * @param intQty  The Integer quantity to be added.
 */
    public void changeQtyAdd(int intQty){ 
        this.intQty = this.intQty + intQty;
    }
/**
 * Deletes cake.
 */
    public void deleteCake(){ 
        intQty = 0;
        intPrice = 0;
        intCalorie = 0;
        strDesc = " ";
        strName = " ";
    }
/**
 * Generates a formatted string representing the details of a cake item.
 *
 * This method constructs a formatted string containing the name, description, calorie count,
 * price, and available quantity of the cake item. The details are displayed in separate lines
 * for easy readability.
 *
 * @return A formatted string containing the name, description, calorie count, price, and available
 *         quantity of the cake item.
 */
public String displayItem() {
    return  "Name: " + strName
            + "\nDescription: " + strDesc
            + "\nCalorie Count: " + intCalorie
            + "\nPrice: " + intPrice
            + "\nAvailable Quantity: " + intQty;
}

}
