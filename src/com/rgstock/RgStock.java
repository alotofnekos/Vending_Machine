package com.rgstock;

import java.util.Scanner;
import com.rgitem.RgItem;

/**
 * The class Rg stock
 */ 
public class RgStock{
/**
 * Main
 *
 * @param args the args. 
 */
    public void stockMenu(){ 

        int intLoop;
        int intChosen;
        int intBoolean = 0;
        RgItem[] Item = new RgItem[100];
        Scanner intMenu = new Scanner(System.in);
        Scanner strEdit = new Scanner(System.in);
        
        for(intLoop=0;intLoop<100;intLoop++){
            Item[intLoop] = new RgItem();
            Item[intLoop].setName(" ");
        }

        while(intBoolean == 0){
            DisplayStockMenu();
            intChosen = intMenu.nextInt();
            switch(intChosen){
                case 1:
                    System.out.println("Initialized 10 Base Cakes...");
                    System.out.println("------------------------");
                    Initialize(Item);
                    break;
                case 2:
                    editMenu(Item, intMenu, strEdit);
                    break;
                case 3:
                    AddCake(Item, intMenu, strEdit);
                    break;
                case 4:
                    System.out.println("Returning...");
                    System.out.println("------------------------");
                    intBoolean = 1;
                    break;
                default:
                    System.out.println("Invalid Input...");
                    break;
            }
        }

        intMenu.close();
        strEdit.close();

    }
/**
 * Edit menu
 *
 * @param Item  The Item Array. 
 * @param intMenu  the Integer Scanner. 
 * @param strEdit  the String Scanner. 
 */
    public void editMenu(RgItem[] Item, Scanner intMenu, Scanner strEdit){ 


        int intChosen;
        int intEditMenu;
        int intChange;
        int intCounter = CountCakes(Item);
        int intBooleanEdit = 0;
        String strLetter;

        System.out.print("Choose a cake to edit [1 - " + intCounter + "]: ");
        intChosen = intMenu.nextInt();
        System.out.println("------------------------");

        if((intChosen > 0)&&(intChosen <= intCounter)){
            intChosen = intChosen - 1;
                
            while(intBooleanEdit == 0){
                Item[intChosen].displayCake();
                DisplayEditMenu();
                intEditMenu = intMenu.nextInt();

                switch(intEditMenu) {
                    case 1:
                        intMenu.nextLine();
                        System.out.print("Enter new Name: ");
                        strLetter = strEdit.nextLine();
                        Item[intChosen].setName(strLetter);
                        break;
                    case 2:
                        System.out.print("Enter new Description: ");
                        strLetter = strEdit.nextLine();
                        Item[intChosen].setDesc(strLetter);
                        break;
                    case 3:
                        System.out.print("Enter new Calorie: ");
                        intChange = intMenu.nextInt();
                        Item[intChosen].setCalorie(intChange);
                        break;
                    case 4:
                        System.out.print("Enter new Price: ");
                        intChange = intMenu.nextInt();
                        Item[intChosen].setPrice(intChange);
                        break;
                    case 5:
                        System.out.print("Enter Inventory to Add: ");
                        intChange = intMenu.nextInt();
                        if(Item[intChosen].getQty() + intChange <= 10){
                            Item[intChosen].changeQtyAdd(intChange);
                        }
                        else{
                            System.out.println("Invalid Number. Exceeds Total Capacity of 10");
                        }
                        break;
                    case 6:
                        System.out.print("Enter Inventory to Subtract: ");
                        intChange = intMenu.nextInt();
                        if(Item[intChosen].getQty() - intChange >= 0){
                            Item[intChosen].changeQtySub(intChange);
                        }
                        else{
                            System.out.println("Invalid Number. Quantity Results to Less Than 0");
                        }
                        break;
                    case 7:
                        System.out.println("Cake Deleted...");
                        Item[intChosen].deleteCake();
                        intBooleanEdit = 1;
                        break;
                    case 8:
                        System.out.println("Returned...");
                        intBooleanEdit = 1;
                        break;
                    default:
                        System.out.println("Invalid Input...");
                        break;
                    }
                    System.out.println("------------------------");
                }
            }
            else{
                System.out.println("Invalid Cake...");
                System.out.println("------------------------");
            }
    }
/**
 * Add cake
 *
 * @param Item  The Item Array. 
 * @param intMenu  The Integer Scanner. 
 * @param strEdit  the String Scanner.
 */
    public void AddCake(RgItem[] Item, Scanner intMenu, Scanner strEdit){ 


        int intCalorie;
        int intPrice;
        int intQty;
        int intCounter = CountCakes(Item);
        String strName;
        String strDesc;

        System.out.print("Enter Name: ");
        strName = strEdit.nextLine();
        System.out.println("------------------------");

        System.out.print("Enter Description: ");
        strDesc = strEdit.nextLine();
        System.out.println("------------------------");
        
        System.out.print("Enter Calorie Count: ");
        intCalorie = intMenu.nextInt();
        System.out.println("------------------------");
        
        System.out.print("Enter Price Count: ");
        intPrice = intMenu.nextInt();
        System.out.println("------------------------");
        
        System.out.print("Enter Quantity Count [Max 10]: ");
        intQty = intMenu.nextInt();
        if(intQty > 10){
            intQty = 0;
            System.out.println("Invalid Quantity. Setting Quantity to 0.");
        }
        System.out.println("------------------------");
        System.out.println("Cake Registration Done");
        System.out.println("------------------------");

        Item[intCounter].rgItem(strName, intQty, intPrice, intCalorie, strDesc);
    }
/**
 * Count cakes
 *
 * @param Item  The Item Array. 
 * @return int  Number of Cakes in Item.
 */
    public int CountCakes(RgItem[] Item){ 


        int intLoop;
        int intCounter = 0;

        for(intLoop=0;intLoop<100;intLoop++){
            if(Item[intLoop].getName() != " "){
                intCounter = intCounter + 1;
            }
        }

        return intCounter;
    }
/**
 *
 * Display stock menu
 *
 */
    public void DisplayStockMenu(){ 

        System.out.println("[1] Initialize Standard 10 Cakes");
        System.out.println("[2] Edit Cake");
        System.out.println("[3] Add Cake");
        System.out.println("[4] Return");
        System.out.println("------------------------");
        System.out.print("Enter Choice: ");
    }
/*
 * Display edit menu
 */
    public void DisplayEditMenu(){ 

        System.out.println("[1] Edit Name");
        System.out.println("[2] Edit Description");
        System.out.println("[3] Edit Calorie");
        System.out.println("[4] Edit Price");
        System.out.println("[5] Add Inventory");
        System.out.println("[6] Subtract Inventory");
        System.out.println("[7] Delete Cake");
        System.out.println("[8] Return");
        System.out.println("------------------------");
        System.out.print("Enter Choice: ");
    }
/**
 * Initialize
 *
 * @param Item  The Item Array. 
 */
    public void Initialize(RgItem[] Item){ 

        int intCounter = CountCakes(Item);

        Item[intCounter].setName("Apple Crumble");
        Item[intCounter].setDesc("Treat yourself with our irresistible apple crumble! Indulge in layers of tender apples, delicately spiced with cinnamon, and crowned with a mouthwatering crumbly crust.");
        Item[intCounter].setCalorie(156);
        Item[intCounter].setPrice(160);
        Item[intCounter].setQty(0);

        Item[intCounter+1].setName("Black Forest Cake");
        Item[intCounter+1].setDesc("Savor the taste of the classic Black Forest Cake, where the tangy sweetness of cherries perfectly complements the rich chocolate layers, all brought together with light and fluffy cream.");
        Item[intCounter+1].setCalorie(282);
        Item[intCounter+1].setPrice(140);
        Item[intCounter+1].setQty(0);
        
        Item[intCounter+2].setName("Premium Chocolate Cake");
        Item[intCounter+2].setDesc("Treat yourself with this moist, rich, and bittersweet Chocolate cake, adorned with a lucious dark chocolate ganache.");
        Item[intCounter+2].setCalorie(389);
        Item[intCounter+2].setPrice(190);
        Item[intCounter+2].setQty(0);
        
        Item[intCounter+3].setName("Strawberry Shortcake");
        Item[intCounter+3].setDesc("Indulge in the freshness of Benguet's finest strawberries with our Strawberry Bliss Short Cake. Enjoy the perfect blend of delicate sponge cake, heavenly cream, and juicy strawberries, sourced directly from the strawberry capital of the Philippines.");
        Item[intCounter+3].setCalorie(247);
        Item[intCounter+3].setPrice(250);
        Item[intCounter+3].setQty(0);

        Item[intCounter+4].setName("Lemon Blueberry Cake");
        Item[intCounter+4].setDesc("Enjoy the vibrant combination of zesty lemon-infused cake, bursting blueberries, and the smooth cream cheese frosting that creates a perfect balance of flavors with this Lemon Blueberry cake.");
        Item[intCounter+4].setCalorie(244);
        Item[intCounter+4].setPrice(240);
        Item[intCounter+4].setQty(0);

        Item[intCounter+5].setName("Chocolate Mousse Cake");
        Item[intCounter+5].setDesc("Revel in joy with a decadent dessert featuring layers of moist chocolate cake and creamy chocolate mousse, altogether forming a rich and indulgent treat.");
        Item[intCounter+5].setCalorie(349);
        Item[intCounter+5].setPrice(220);
        Item[intCounter+5].setQty(0);

        Item[intCounter+6].setName("Butterscotch Cake");
        Item[intCounter+6].setDesc("A delectable dessert with a moist and fluffy base infused with the rich and caramel-like flavor of butterscotch, topped with creamy butterscotch frosting and caramel sauce.");
        Item[intCounter+6].setCalorie(379);
        Item[intCounter+6].setPrice(280);
        Item[intCounter+6].setQty(0);

        Item[intCounter+7].setName("Mocha Delight Cake");
        Item[intCounter+7].setDesc("Enjoy a heavenly dessert featuring layers of moist chocolate cake infused with the bold flavors of coffee and espresso, complemented by a velvety mocha frosting for a delightful and indulgent treat.");
        Item[intCounter+7].setCalorie(308);
        Item[intCounter+7].setPrice(210);
        Item[intCounter+7].setQty(0);

        Item[intCounter+8].setName("Mango Surprise Cake");
        Item[intCounter+8].setDesc("A tropical delight that combines layers of moist mango-infused cake with a luscious mango filling, providing a delightful surprise of fruity sweetness in every bite.");
        Item[intCounter+8].setCalorie(228);
        Item[intCounter+8].setPrice(230);
        Item[intCounter+8].setQty(0);

        Item[intCounter+9].setName("Carrot Cake");
        Item[intCounter+9].setDesc("Treat yourself with a moist and flavorful dessert made with grated carrots, warm spices, and a rich cream cheese frosting, offering a delightful combination of sweet and spiced flavors.");
        Item[intCounter+9].setCalorie(266);
        Item[intCounter+9].setPrice(180);
        Item[intCounter+9].setQty(0);
    }
}
