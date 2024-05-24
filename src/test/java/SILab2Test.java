import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {
    private List<Item> naprajLista(Item... elementi) {
        return new ArrayList<>(Arrays.asList(elementi));
    }

    @Test
    void everyBranchTest() {
        //da bide null i frla isklucok
        RuntimeException ex;
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 2000));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        Item item1 = new Item(null, "0224", 600, 20f);
        Item item2 = new Item("Kupaci", "10224", 299, 0);
        assertTrue(SILab2.checkCart(naprajLista(item2, item2), 12000));

        //neavliden barcode frla isklucok
        Item item3 = new Item("Casa", "#0224", 600, 20f);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(naprajLista(item3), 850));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        //barcode null frla isklucok
        Item item4 = new Item("Peskir", null, 200, 0);
        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(naprajLista(item4), 1000));
        assertTrue(ex.getMessage().contains("No barcode!"));

        //sumata da bide pogolema od payment
        Item item5 = new Item("GumaZaPlivanje", "123123", 200, 0f);
        assertFalse(SILab2.checkCart(naprajLista(item5), 100));
    }

    @Test
    void MultipleConiditionTest(){

        //if (item.getPrice() > 300 && item.getDiscount() > 0 && item.getBarcode().charAt(0) == '0')
        //sum -= 30;
        //TTT
        Item item6 = new Item("Papuci", "0123", 400, 20f);//suma = 7970
        assertFalse(SILab2.checkCart(naprajLista(item6), 6500));

        //TTF
        Item item7 = new Item("KremaZaSuncanje", "123", 400, 20f); //suma 7970
        assertFalse(SILab2.checkCart(naprajLista(item7), 6500));

        //TFX
        Item item8 = new Item("Maica", "123123", 400, 0);
        assertTrue(SILab2.checkCart(naprajLista(item8), 6500));

        //FXX
        Item item9 = new Item("Papuci", "0123", 200, 20f);
        assertTrue(SILab2.checkCart(naprajLista(item9), 6500));
    }
}