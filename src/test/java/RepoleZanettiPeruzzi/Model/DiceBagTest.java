package RepoleZanettiPeruzzi.Model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class DiceBagTest {

    private DiceBag testDiceBag;


    @Test
    public void takeDice() {

        testDiceBag= new DiceBag();
        int nDice= 2;
        int size = testDiceBag.getDiceBag().size();
        ArrayList<Die> extractDie = testDiceBag.takeDice(nDice);

        //test that the size is decreazed by number of Dice(nDice)
        assertEquals(size-nDice,testDiceBag.getDiceBag().size());

        //test that the dice is not in Dicebag yet
        assertFalse(testDiceBag.getDiceBag().contains(extractDie.get(0)));
        assertFalse(testDiceBag.getDiceBag().contains(extractDie.get(1)));

    }
}