package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.CheckOut;
import src.Product;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckOutTest {

    CheckOut checkOut;
    List<Product> allPurchasedProducts;
    @BeforeEach
    void setCheckOutAndList(){
        checkOut = new CheckOut();
        allPurchasedProducts = new ArrayList<>();
    }
    @Test
    @DisplayName("Scan one item get the price")
    void scanOneItemReturnThePrice(){
        assertEquals(2.56, checkOut.getItemPrice("soup",1));
    }
    @Test
    @DisplayName("Scan one item get the current total")
    void scanOneItemReturnTheCurrentTotal(){
        double total = 2.56;
        assertEquals(total, checkOut.getItemPrice("soup",1));
    }
    @Test
    @DisplayName("Scan one item with weight get the current total")
    void scanOneItemWithWeightReturnTheCurrentTotal(){
        assertEquals(1.8,checkOut.getItemPrice("banana",3));
    }
    @Test
    @DisplayName("Scan one item with markdown get the current total")
    void scanOneItemWithMarkDown(){
        assertEquals(4.52, checkOut.getItemPrice("soup", 2));
        assertEquals(6.32, checkOut.getItemPrice("banana",3));
    }
    @Test
    @DisplayName("Void one item  get the current total")
    void voidOneItemReturnTotal(){
        assertEquals(2.26, checkOut.getItemPrice("soup", 1));
        assertEquals(4.52, checkOut.getItemPrice("soup", 1));
        assertEquals(2.26,checkOut.voidOneItem("soup",1));
    }
    @Test
    @DisplayName("Void one item with weight get the current total")
    void voidOneItemWithWeightReturnTotal(){
        assertEquals(1.8, checkOut.getItemPrice("banana", 3));
        assertEquals(4.8, checkOut.getItemPrice("banana", 5));
        assertEquals(1.8,checkOut.voidOneItem("banana",5));
        allPurchasedProducts.add(new Product("banana",1.8,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Scan one item imply bogo and get the current total")
    void testBuyOneGetOneFree(){
        assertEquals(2.26, checkOut.getItemPrice("soup", 1));
        assertEquals(2.26, checkOut.getItemPrice("soup", 1));
        assertEquals(4.52, checkOut.getItemPrice("soup", 1));
        assertEquals(4.52, checkOut.getItemPrice("soup", 1));
        allPurchasedProducts.add(new Product("soup",2.26,0));
        allPurchasedProducts.add(new Product("soup",0,0));
        allPurchasedProducts.add(new Product("soup",2.26,0));
        allPurchasedProducts.add(new Product("soup",0,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Expand BOGO buy 4 get 2 at 50% off")
    void testBuyFourGetTwoAt50PercentOff(){
        assertEquals(1.2, checkOut.getItemPrice("pasta", 1));
        assertEquals(2.4, checkOut.getItemPrice("pasta", 1));
        assertEquals(3.6, checkOut.getItemPrice("pasta", 1));
        assertEquals(4.8, checkOut.getItemPrice("pasta", 1));
        assertEquals(5.4, checkOut.getItemPrice("pasta", 1));
        assertEquals(6, checkOut.getItemPrice("pasta", 1));
        assertEquals(7.2, checkOut.getItemPrice("pasta", 1));
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        allPurchasedProducts.add(new Product("pasta",0.6,0));
        allPurchasedProducts.add(new Product("pasta",0.6,0));
        allPurchasedProducts.add(new Product("pasta",1.2,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Buy 3 for $ 3, assume only buy three can get the sale price and only one kind of sale available")
    void testBuyThreeForThree(){
        assertEquals(1.2, checkOut.getItemPrice("pasta", 1));
        assertEquals(2.4, checkOut.getItemPrice("pasta", 1));
        assertEquals(3, checkOut.getItemPrice("pasta", 1));
        assertEquals(4.2, checkOut.getItemPrice("pasta", 1));
        assertEquals(5.4, checkOut.getItemPrice("pasta", 1));
        allPurchasedProducts.add(new Product("pasta",1,0));
        allPurchasedProducts.add(new Product("pasta",1,0));
        allPurchasedProducts.add(new Product("pasta",1,0));
        allPurchasedProducts.add(new Product("pasta",1,0));
        allPurchasedProducts.add(new Product("pasta",1,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Buy 2 get 1 free, limit 6, assume only one kind of sale available")
    void testBuyTwoGetOneFreeLimit6(){
        assertEquals(4.2, checkOut.getItemPrice("milk", 1));
        assertEquals(8.4, checkOut.getItemPrice("milk", 1));
        assertEquals(8.4, checkOut.getItemPrice("milk", 1));
        assertEquals(12.6, checkOut.getItemPrice("milk", 1));
        assertEquals(16.8, checkOut.getItemPrice("milk", 1));
        assertEquals(16.8, checkOut.getItemPrice("milk", 1));
        assertEquals(21, checkOut.getItemPrice("milk", 1));
        assertEquals(25.2, checkOut.getItemPrice("milk", 1));
        assertEquals(29.4, checkOut.getItemPrice("milk", 1));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",0,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",0,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Void one item with the special buy n get m at x% off, test with buy 4 get 2 50% off")
    void voidOneItemWithBuyNGetMAtXPercentOff(){
        assertEquals(3, checkOut.getItemPrice("egg", 1));
        assertEquals(6, checkOut.getItemPrice("egg", 1));
        assertEquals(9, checkOut.getItemPrice("egg", 1));
        assertEquals(12, checkOut.getItemPrice("egg", 1));
        assertEquals(13.5, checkOut.getItemPrice("egg", 1));
        assertEquals(15, checkOut.getItemPrice("egg", 1));
        assertEquals(18, checkOut.getItemPrice("egg", 1));
        assertEquals(15, checkOut.voidOneItem("egg",1));
        assertEquals(13.5, checkOut.voidOneItem("egg",1));
        assertEquals(12, checkOut.voidOneItem("egg",1));
        allPurchasedProducts.add(new Product("egg",3,0));
        allPurchasedProducts.add(new Product("egg",3,0));
        allPurchasedProducts.add(new Product("egg",3,0));
        allPurchasedProducts.add(new Product("egg",3,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("void one item that has buy n for m special, test with buy 3 for 20")
    void voidOneItemWithBuyNForMSale(){
        assertEquals(9.99, checkOut.getItemPrice("rice", 1));
        assertEquals(19.98, checkOut.getItemPrice("rice", 1));
        assertEquals(20, checkOut.getItemPrice("rice", 1));
        assertEquals(29.99, checkOut.getItemPrice("rice", 1));
        assertEquals(39.98, checkOut.getItemPrice("rice", 1));
        assertEquals(40, checkOut.getItemPrice("rice", 1));
        assertEquals(39.98, checkOut.voidOneItem("rice",1));
        assertEquals(29.99, checkOut.voidOneItem("rice",1));
        allPurchasedProducts.add(new Product("rice",6.67,0));
        allPurchasedProducts.add(new Product("rice",6.67,0));
        allPurchasedProducts.add(new Product("rice",6.67,0));
        allPurchasedProducts.add(new Product("rice",9.99,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("void one item has buy n get m free limit x and invalidate the special, test with buy 3 get 1 free limit 9")
    void voidOneItemWithBuyNGetMFreeAndInvalidateSale(){
        assertEquals(4.2, checkOut.getItemPrice("milk", 1));
        assertEquals(8.4, checkOut.getItemPrice("milk", 1));
        assertEquals(12.6, checkOut.getItemPrice("milk", 1));
        assertEquals(12.6, checkOut.getItemPrice("milk", 1));

        assertEquals(16.8, checkOut.getItemPrice("milk", 1));
        assertEquals(21, checkOut.getItemPrice("milk", 1));
        assertEquals(25.2, checkOut.getItemPrice("milk", 1));
        assertEquals(25.2, checkOut.getItemPrice("milk", 1));

        assertEquals(29.4, checkOut.getItemPrice("milk", 1));

        assertEquals(25.2, checkOut.voidOneItem("milk", 1));
        assertEquals(25.2, checkOut.voidOneItem("milk", 1));
        assertEquals(21, checkOut.voidOneItem("milk", 1));
        assertEquals(16.8, checkOut.voidOneItem("milk", 1));
        assertEquals(12.6, checkOut.voidOneItem("milk", 1));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",4.2,0));
        allPurchasedProducts.add(new Product("milk",0,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Buy N, get M of equal or lesser value for X% off for weighted item, test with buy 3 pound get 2 pound 50% off")
    void scanOneWeightedItemWithBuyNGetMOrLessAtXOff(){
        assertEquals(2.52, checkOut.getItemPrice("banana", 5.2));
        assertEquals(4.08, checkOut.getItemPrice("banana", 2.6));
        allPurchasedProducts.add(new Product("banana",2.52,0));
        allPurchasedProducts.add(new Product("banana",1.56,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("Buy N, get M of equal or lesser value for X% off for weighted item, test with buy 3 pound get 2 pound 50% off")
    void scanOneWeightedItemWithBuyNGetMOrLessAtXOffAgain(){
        assertEquals(2.52, checkOut.getItemPrice("banana", 5.2));
        assertEquals(4.08, checkOut.getItemPrice("banana", 2.6));
        assertEquals(9.84, checkOut.getItemPrice("banana", 11.6));
        allPurchasedProducts.add(new Product("banana",2.52,0));
        allPurchasedProducts.add(new Product("banana",1.56,0));
        allPurchasedProducts.add(new Product("banana",5.76,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("void Buy N, get M of equal or lesser value for X% off for weighted item, test with buy 3 pound get 2 pound 50% off")
    void voidOneWeightedItemWithBuyNGetMOrLessAtXOff(){
        assertEquals(2.52, checkOut.getItemPrice("banana", 5.2));
        assertEquals(4.08, checkOut.getItemPrice("banana", 2.6));
        assertEquals(9.84, checkOut.getItemPrice("banana", 11.6));
        assertEquals(7.32, checkOut.voidOneItem("banana", 5.2));
        allPurchasedProducts.add(new Product("banana",1.56,0));
        allPurchasedProducts.add(new Product("banana",5.76,0));
        assertEquals(allPurchasedProducts,CheckOut.allPurchasedProducts);
    }
    @Test
    @DisplayName("wild test for everything")
    void testGetItemPriceAndVoidOneItemUseDifferenceSaleSpecial(){
        assertEquals(2.26, checkOut.getItemPrice("soup",1));
        assertEquals(4.52, checkOut.getItemPrice("soup",1));

        assertEquals(5.72, checkOut.getItemPrice("pasta",1));
        assertEquals(6.92, checkOut.getItemPrice("pasta", 1));

        assertEquals(9.92, checkOut.getItemPrice("egg",1));
        assertEquals(12.92, checkOut.getItemPrice("egg",1));
        assertEquals(15.92, checkOut.getItemPrice("egg",1));
        assertEquals(18.92, checkOut.getItemPrice("egg", 1));

        assertEquals(20.42, checkOut.getItemPrice("egg",1));
        assertEquals(21.92, checkOut.getItemPrice("egg",1));
        assertEquals(24.92, checkOut.getItemPrice("egg", 1));

        assertEquals(29.12, checkOut.getItemPrice("milk",1));
        assertEquals(33.32, checkOut.getItemPrice("milk",1));
        assertEquals(37.52, checkOut.getItemPrice("milk", 1));
        assertEquals(37.52, checkOut.getItemPrice("milk",1));

        assertEquals(41.72, checkOut.getItemPrice("milk",1));
        assertEquals(45.92, checkOut.getItemPrice("milk", 1));
        assertEquals(50.12, checkOut.getItemPrice("milk",1));
        assertEquals(50.12, checkOut.getItemPrice("milk",1));

        assertEquals(54.32, checkOut.getItemPrice("milk", 1));

        assertEquals(64.31, checkOut.getItemPrice("rice",1));
        assertEquals(74.3, checkOut.getItemPrice("rice",1));
        assertEquals(74.32, checkOut.getItemPrice("rice", 1));

        assertEquals(84.31, checkOut.getItemPrice("rice",1));
        assertEquals(94.3, checkOut.getItemPrice("rice", 1));
        assertEquals(94.32, checkOut.getItemPrice("rice",1));

        assertEquals(104.31, checkOut.getItemPrice("rice", 1));

        assertEquals(105.87, checkOut.getItemPrice("banana", 2.6));
        assertEquals(111.63, checkOut.getItemPrice("banana", 11.6));

        assertEquals(109.37, checkOut.voidOneItem("soup",1));
        assertEquals(108.17, checkOut.voidOneItem("pasta",1));

        assertEquals(105.17, checkOut.voidOneItem("egg",1));
        assertEquals(103.67, checkOut.voidOneItem("egg",1));
        assertEquals(102.17, checkOut.voidOneItem("egg",1));

        assertEquals(97.97, checkOut.voidOneItem("milk",1));
        assertEquals(97.97, checkOut.voidOneItem("milk",1));

        assertEquals(87.98, checkOut.voidOneItem("rice",1));
        assertEquals(87.96, checkOut.voidOneItem("rice",1));

        assertEquals(82.2, checkOut.voidOneItem("banana",11.6));
    }
    @Test
    @DisplayName("wild test for everything mixed order of two methods")
    void testGetItemPriceAndVoidOneItemUseDifferenceSaleSpecialDifferentOder(){
        assertEquals(2.26, checkOut.getItemPrice("soup",1));
        assertEquals(4.52, checkOut.getItemPrice("soup",1));
        assertEquals(4.52,checkOut.voidOneItem("rice",1));
        assertEquals(5.72, checkOut.getItemPrice("pasta",1));
        assertEquals(6.92, checkOut.getItemPrice("pasta", 1));

        assertEquals(9.92, checkOut.getItemPrice("egg",1));
        assertEquals(12.92, checkOut.getItemPrice("egg",1));
        assertEquals(15.92, checkOut.getItemPrice("egg",1));
        assertEquals(18.92, checkOut.getItemPrice("egg", 1));

        assertEquals(20.42, checkOut.getItemPrice("egg",1));

        assertEquals(18.92, checkOut.voidOneItem("egg",1));

        assertEquals(20.42, checkOut.getItemPrice("egg",1));
        assertEquals(21.92, checkOut.getItemPrice("egg", 1));

        assertEquals(26.12, checkOut.getItemPrice("milk",1));
        assertEquals(30.32, checkOut.getItemPrice("milk",1));
        assertEquals(34.52, checkOut.getItemPrice("milk", 1));
        assertEquals(34.52, checkOut.getItemPrice("milk",1));

        assertEquals(34.52, checkOut.voidOneItem("milk",1));

        assertEquals(34.52, checkOut.getItemPrice("milk",1));

        assertEquals(38.72, checkOut.getItemPrice("milk", 1));
        assertEquals(42.92, checkOut.getItemPrice("milk",1));
        assertEquals(47.12, checkOut.getItemPrice("milk",1));
        assertEquals(47.12, checkOut.getItemPrice("milk", 1));

        assertEquals(57.11, checkOut.getItemPrice("rice",1));
        assertEquals(67.1, checkOut.getItemPrice("rice",1));
        assertEquals(67.12, checkOut.getItemPrice("rice", 1));

        assertEquals(67.1, checkOut.voidOneItem("rice",1));

        assertEquals(67.12, checkOut.getItemPrice("rice",1));

        assertEquals(77.11, checkOut.getItemPrice("rice", 1));
        assertEquals(87.1, checkOut.getItemPrice("rice",1));

        assertEquals(87.12, checkOut.getItemPrice("rice", 1));

        assertEquals(88.68, checkOut.getItemPrice("banana", 2.6));
        assertEquals(94.44, checkOut.getItemPrice("banana", 11.6));

        assertEquals(92.18, checkOut.voidOneItem("soup",1));
        assertEquals(90.98, checkOut.voidOneItem("pasta",1));

        assertEquals(85.22, checkOut.voidOneItem("banana",11.6));
        assertEquals(83.72, checkOut.voidOneItem("egg",1));
        assertEquals(82.22, checkOut.voidOneItem("egg",1));

        assertEquals(82.22, checkOut.voidOneItem("milk",1));

        assertEquals(82.2, checkOut.voidOneItem("rice",1));

    }
}
