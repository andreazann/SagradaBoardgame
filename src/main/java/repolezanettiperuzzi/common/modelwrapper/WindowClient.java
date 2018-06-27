package repolezanettiperuzzi.common.modelwrapper;

import java.io.Serializable;

public class WindowClient implements Serializable{

    private final String NAME;
    private BoxClient[][] boardBox;
    private final int FAVORTOKENS;

    public WindowClient(String name, int ft, BoxClient[][] board) {

        this.NAME = name;
        this.FAVORTOKENS = ft;

        this.boardBox = new BoxClient[board.length][board[0].length];

        for(int i = 0; i< board.length; i++) {

            /*for(int j = 0; j<board[0].length; j++){
                System.out.print(board[i][j].toString()+" ");
            }
            System.out.println();*/
            System.arraycopy(board[i], 0, boardBox[i], 0, board[0].length);

        }
        //System.out.println("\n");
    }

    public WindowClient(String name, int ft, String board){
        this.NAME = name;
        this.FAVORTOKENS = ft;

        String[] boxRows = board.split(" ");
        int rows = boxRows.length;
        int cols = boxRows[0].split("-").length;

        this.boardBox = new BoxClient[rows][cols];

        for(int i = 0; i<rows; i++){
            String[] box = boxRows[i].split("-");
            for(int j = 0; j<cols; j++){
                switch (box[j]){
                    case "Y":
                        boardBox[i][j] = new BoxClient(ColourClient.YELLOW);
                        break;
                    case "R":
                        boardBox[i][j] = new BoxClient(ColourClient.RED);
                        break;
                    case "P":
                        boardBox[i][j] = new BoxClient(ColourClient.PURPLE);
                        break;
                    case "G":
                        boardBox[i][j] = new BoxClient(ColourClient.GREEN);
                        break;
                    case "B":
                        boardBox[i][j] = new BoxClient(ColourClient.BLUE);
                        break;
                    case "0":
                        boardBox[i][j] = new BoxClient();
                        break;
                    case "1":
                        boardBox[i][j] = new BoxClient(ValueClient.ONE);
                        break;
                    case "2":
                        boardBox[i][j] = new BoxClient(ValueClient.TWO);
                        break;
                    case "3":
                        boardBox[i][j] = new BoxClient(ValueClient.THREE);
                        break;
                    case "4":
                        boardBox[i][j] = new BoxClient(ValueClient.FOUR);
                        break;
                    case "5":
                        boardBox[i][j] = new BoxClient(ValueClient.FIVE);
                        break;
                    case "6":
                        boardBox[i][j] = new BoxClient(ValueClient.SIX);
                        break;
                }
            }
        }
    }

    public BoxClient[][] getBoardBox(){

        return this.boardBox.clone();

    }
    public void insertDie(DieClient d, int x, int y, String restriction){

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

     public DieClient removeDie(int x, int y){

        if((boardBox[x][y].die!=null)){
            return boardBox[x][y].removeDie();
        }

        else return null;
    }

    public String getName(){

        return this.NAME;

    }

    public int getFTokens(){

        return this.FAVORTOKENS;

    }

    public DieClient getDieFromBoardBox(int x, int y)
    {
        return this.boardBox[x][y].die;
    }

    public ValueClient getDieValue(int x, int y){

        return this.boardBox[x][y].die.getValueDie();

    }

    public ColourClient getDieColour(int x, int y){

        return this.boardBox[x][y].die.getColourDie();

    }

    public boolean controlAdjacencies(int x, int y){

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

    public boolean controlColourBoundAdjacencies(DieClient d, int x, int y){

        if (x - 1 >= 0 && boardBox[x - 1][y].die !=null && boardBox[x - 1][y].die.getColourDie().equals(d.getColourDie())) {

            return true;

        } else if (x + 1 < boardBox.length && boardBox[x + 1][y].die != null && boardBox[x + 1][y].die.getColourDie().equals(d.getColourDie())) {

            return true;

        } else if ((y - 1 >= 0) && boardBox[x][y - 1].die != null && boardBox[x][y - 1].die.getColourDie().equals(d.getColourDie()) ) {

            return true;

        } else if ((y + 1 < boardBox[0].length) && (boardBox[x][y + 1].die != null) && boardBox[x][y + 1].die.getColourDie().equals(d.getColourDie())) {

            return true;

        } else {

            return false;

        }
    }

    public boolean controlValueBoundAdjacencies(DieClient d, int x, int y){

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

    public boolean controlAllBoundAdjacencies(DieClient d, int x, int y){

        if(controlColourBoundAdjacencies(d,x,y)){

            return true;

        }else if(controlValueBoundAdjacencies(d,x,y)){

            return true;

        }else{

            return false;

        }
    }

    public boolean isEmpty(){

        for (BoxClient[] aBoardBox : boardBox) {
            for (int j = 0; j < boardBox[0].length; j++) {
                if (aBoardBox[j].die != null) {
                    return false;
                }
            }
        }

        return true;
    }

    public int numBoxEmpty(){

        int nEmpty=0;

        for (BoxClient[] aBoardBox : boardBox) {

            for (int j = 0; j < boardBox[0].length; j++) {

                if (aBoardBox[j].die == null) {

                    nEmpty++;

                }
            }
        }

        return nEmpty;
    }


    public int calculateSecretScore(ColourClient whichSecretColour){

        int secretScore=0;

        for (BoxClient[] aBoardBox : boardBox) {

            for (int j = 0; j < boardBox[0].length; j++) {

                if (aBoardBox[j].die!=null && aBoardBox[j].die.getColourDie()== whichSecretColour) {

                    secretScore+=aBoardBox[j].die.getValueDie().getNumber();

                }
            }
        }

        return secretScore;
    }

    public boolean controlAllBoundBox(int x, int y, DieClient d){

        return boardBox[x][y].controlBounds(d);

    }

    public boolean controlColourBoundBox(int x, int y, DieClient d){

        return boardBox[x][y].controlColour(d);

    }

    public boolean controlValueBoundBox(int x, int y, DieClient d){

        return boardBox[x][y].controlValue(d);

    }

    public int rowSize(){

        return boardBox.length;
    }

    public int columnSize(){

        return boardBox[0].length;

    }

}