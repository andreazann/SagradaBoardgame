package repolezanettiperuzzi.model.actions;

import org.junit.Test;
import repolezanettiperuzzi.model.*;
import repolezanettiperuzzi.model.publiccards.*;

import java.util.HashMap;

import static org.junit.Assert.*;

public class CalculateScoreTest {

    CalculateScore testCalculateScore=new CalculateScore();
    GameBoard board=new GameBoard();
    PublicCard card1=new RowShadeVariety(); //valori diversi nella riga
    PublicCard card2=new LightShades();//coppie 1,2
    PublicCard card3=new ColorDiagonals();//numero caselle con stesso colore vicine in diagonale
    PublicCard card4=new RowColorVariety();// numero righe con tutti colori diversi
    PublicCard card5=new ColumnShadeVariety();
    PublicCard card6=new ColumnColorVariety();
    PublicCard card7=new DeepShades();
    PublicCard card8=new MediumShades();
    PublicCard card9=new ColorVariety();
    PublicCard card10=new ShadeVariety();
    HashMap<String,Integer> ranking=new HashMap<>();

    @Test
    public void testDoAction() {

        board.setPublicCards(card1,0);
        board.setPublicCards(card2,1);
        board.setPublicCards(card3,2);

        Box[][] boxes = new Box[4][5];

        for ( int i = 0; i < 4; i++){

            for ( int j = 0; j < 5; j++){

                boxes[i][j]= new Box();

            }
        }

        String name = "Virtus";
        Window window = new Window(name,4, boxes,"test");

        Die d=new Die(Colour.RED);
        Die d1=new Die(Colour.RED);
        Die d2=new Die(Colour.RED);
        Die d3=new Die(Colour.BLUE);
        Die d4=new Die(Colour.BLUE);
        Die d5=new Die(Colour.BLUE);
        Die d6=new Die(Colour.BLUE);
        Die d7=new Die(Colour.YELLOW);
        Die d8=new Die(Colour.YELLOW);
        Die d9=new Die(Colour.YELLOW);
        Die d10=new Die(Colour.GREEN);
        Die d11=new Die(Colour.GREEN);
        Die d12=new Die(Colour.GREEN);
        Die d13=new Die(Colour.GREEN);
        Die d14=new Die(Colour.PURPLE);
        Die d15=new Die(Colour.PURPLE);

        d1.setValue(Value.TWO);
        d2.setValue(Value.TWO);
        d3.setValue(Value.FOUR);
        d4.setValue(Value.SIX);
        d6.setValue(Value.TWO);
        d7.setValue(Value.SIX);
        d9.setValue(Value.SIX);
        d10.setValue(Value.FIVE);
        d11.setValue(Value.TWO);
        d12.setValue(Value.FOUR);
        d13.setValue(Value.FIVE);
        d14.setValue(Value.SIX);

        window.insertDie(d,0,0,"none");
        window.insertDie(d1,1,1,"none");
        window.insertDie(d2,2,2,"none");
        window.insertDie(d3,0,1,"none");
        window.insertDie(d4,1,0,"none");
        window.insertDie(d5,1,2,"none");
        window.insertDie(d6,0,2,"none");
        window.insertDie(d7,0,3,"none");
        window.insertDie(d8,2,3,"none");
        window.insertDie(d9,1,3,"none");
        window.insertDie(d10,0,4,"none");
        window.insertDie(d11,3,4,"none");
        window.insertDie(d12,1,4,"none");
        window.insertDie(d13,2,4,"none");
        window.insertDie(d14,2,1,"none");
        window.insertDie(d15,3,0,"none");

        board.addPlayer("jerry","RMI","CLI","127.0.0.1",8008);
        board.getPlayer(0).setWindow(window);
        board.getPlayer(0).setSecretColour(Colour.GREEN);
        board.getPlayer(0).setFavorTokens(window.getFTokens());

        ranking=testCalculateScore.doAction(board);
        int score=ranking.get("jerry");
        assertEquals(34,score);


        board.setPublicCards(card4,0);
        board.setPublicCards(card5,1);
        board.setPublicCards(card6,2);

        ranking=testCalculateScore.doAction(board);
        score=ranking.get("jerry");
        assertEquals(16,score);


        board.setPublicCards(card7,0);
        board.setPublicCards(card8,1);
        board.setPublicCards(card9,2);

        ranking=testCalculateScore.doAction(board);
        score=ranking.get("jerry");
        assertEquals(28,score);


        board.setPublicCards(card1,0);
        board.setPublicCards(card2,1);
        board.setPublicCards(card10,2);

        ranking=testCalculateScore.doAction(board);
        score=ranking.get("jerry");
        assertEquals(29,score);
    }
}