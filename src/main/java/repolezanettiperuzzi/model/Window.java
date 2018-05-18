package repolezanettiperuzzi.model;

public class Window {

    private final String NAME;
    private Box[][] boardBox;
    private final int FLAVORTOKENS;

    public Window(String name, int ft, Box[][] board) {

        this.NAME = name;
        this.FLAVORTOKENS = ft;

        this.boardBox = new Box[4][5];

        for(int i = 0; i< 4; i++) {

            System.arraycopy(board[i], 0, boardBox[i], 0, 5);

        }
    }

    //copy constructor
    public Window(Window w){
        this.NAME = w.NAME;
        this.boardBox = new Box[4][5];
        for(int i = 0; i<w.boardBox.length; i++){
            for(int j = 0; j<w.boardBox[0].length; j++){
                this.boardBox[i][j] = w.boardBox[i][j];
            }
        }
        this.FLAVORTOKENS = w.FLAVORTOKENS;
    }

    public void insertDie(Die d, int x, int y, String restriction){

        this.boardBox[x][y].setDie(d,restriction);

    }



    public void moveDie(int xIn,int yIn, int xEnd, int yEnd, String restriction) {

            this.boardBox[xEnd][yEnd].setDie(this.boardBox[xIn][yIn].removeDie(), restriction);

    }

    public boolean thereIsDie(int x, int y){

        if(boardBox[x][y].die!=null){

             return true;

        }

        return false;
    }

     public Die removeDie(int x, int y){

        if((boardBox[x][y].die!=null)){
            return boardBox[x][y].removeDie();
        }

        else return null;
    }

    public String getName(){

        return this.NAME;

    }

    public int getFTokens(){

        return this.FLAVORTOKENS;

    }

    public Die getDieFromBoardBox(int x, int y)
    {
        return this.boardBox[x][y].die;
    }

    public Value getDieValue(int x, int y){

        return this.boardBox[x][y].die.getValueDie();
    }

    public Colour getDieColour(int x, int y){

        return this.boardBox[x][y].die.getColourDie();

    }

    public boolean controlAdjacences(int x, int y){

        boolean thereIsDieNextTo;

        if(x-1>=0 && boardBox[x-1][y].die!=null) {

            thereIsDieNextTo=true;

        }else if(x-1>=0 && (y-1>=0)&&(boardBox[x-1][y-1].die!=null)){

            thereIsDieNextTo=true;

        }else if(x-1>=0 && (y+1<boardBox[0].length)&&(boardBox[x-1][y+1].die!=null)){

            thereIsDieNextTo=true;

        }else if(x+1<boardBox.length && boardBox[x+1][y].die!=null){

            thereIsDieNextTo=true;

        }else if(x+1<boardBox.length && (y-1>=0)&&(boardBox[x+1][y-1].die!=null)){

            thereIsDieNextTo=true;

        }else if(x+1<boardBox.length && (y+1<boardBox[0].length)&&(boardBox[x+1][y+1].die!=null)){

            thereIsDieNextTo=true;

        }else if((y-1>=0)&&(boardBox[x][y-1].die!=null)){

            thereIsDieNextTo=true;

        }else if((y+1<boardBox[0].length)&&(boardBox[x][y+1].die!=null)){

            thereIsDieNextTo=true;

        }else{

            thereIsDieNextTo=false;

        }

        return thereIsDieNextTo;
    }

    public boolean controlColourBoundAdjacences(Die d, int x, int y){

        if (x - 1 >= 0 && boardBox[x - 1][y].die != null && boardBox[x - 1][y].die.getColourDie().equals(d.getColourDie())) {

            return true;

        } else if (x + 1 < boardBox.length && boardBox[x + 1][y].die != null && boardBox[x + 1][y].die.getColourDie().equals(d.getColourDie())) {

            return true;

        } else if ((y - 1 >= 0) && (boardBox[x][y - 1].die != null && boardBox[x][y - 1].die.getColourDie().equals(d.getColourDie()))) {

            return true;

        } else if ((y + 1 < boardBox[0].length) && (boardBox[x][y + 1].die != null) && boardBox[x][y + 1].die.getColourDie().equals(d.getColourDie())) {

            return true;

        } else {

            return false;

        }
    }

    public boolean controlValueBoundAdjacences(Die d, int x, int y){

        if (x - 1 >= 0 && boardBox[x - 1][y].die != null && boardBox[x - 1][y].die.getValueDie().equals(d.getValueDie())) {

            return true;

        } else if (x + 1 < boardBox.length && boardBox[x + 1][y].die != null && boardBox[x + 1][y].die.getValueDie().equals(d.getValueDie())) {

            return true;

        } else if ((y - 1 >= 0) && (boardBox[x][y - 1].die != null && boardBox[x][y - 1].die.getValueDie().equals(d.getValueDie()))) {

            return true;

        } else if ((y + 1 < boardBox[0].length) && (boardBox[x][y + 1].die != null) && boardBox[x][y + 1].die.getValueDie().equals(d.getValueDie())) {

            return true;

        } else {

            return false;

        }
    }

    public boolean controlAllBoundAdjacences(Die d, int x, int y){

        if(controlColourBoundAdjacences(d,x,y)){

            return true;

        }else if(controlValueBoundAdjacences(d,x,y)){

            return true;

        }else{

            return false;

        }
    }

    public boolean isEmpty(){

        for (Box[] aBoardBox : boardBox) {
            for (int j = 0; j < boardBox[0].length; j++) {
                if (aBoardBox[j].die != null) {
                    return false;
                }
            }
        }

        return true;
    }

    public int calculeteSecretScore(Colour whichSecretColour){

        int secretScore=0;

        for (Box[] aBoardBox : boardBox) {

            for (int j = 0; j < boardBox[0].length; j++) {

                if (aBoardBox[j].die!=null && aBoardBox[j].die.getColourDie()== whichSecretColour) {

                    secretScore+=aBoardBox[j].die.getValueDie().getNumber();

                }
            }
        }

        return secretScore;
    }

    public boolean controlAllBoundBox(int x, int y, Die d){

        return boardBox[x][y].controlBounds(d);

    }

    public boolean controlColourBoundBox(int x, int y, Die d){

        return boardBox[x][y].controlColour(d);

    }

    public boolean controlValueBoundBox(int x, int y, Die d){

        return boardBox[x][y].controlValue(d);

    }

    //deep clone method
    public Window copy(){
        return new Window(this);
    }

}