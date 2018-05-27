package repolezanettiperuzzi.model;

public class Box {

    protected Die die;
    private final Colour BOUNDCOLOUR ;
    private final Value BOUNDVALUE ;

    public Box (){
        this.BOUNDCOLOUR=null;
        this.BOUNDVALUE=null;
    }

    public Box(Colour c) {
        this.BOUNDCOLOUR = c;
        this.BOUNDVALUE = null;
    }

    public Box(Value v){
        this.BOUNDVALUE=v;
        this.BOUNDCOLOUR=null;
    }

    public Colour getBoundColour(){

        return this.BOUNDCOLOUR;

    }

    public Value getBoundValue(){

        return this.BOUNDVALUE;

    }

    public boolean setDie(Die dice,String restriction){

        if(restriction.compareTo("both")==0){

            if(controlBounds(dice)){

                this.die=dice;
                return true;
            }

        }else if(restriction.compareTo("none")==0){

                this.die=dice;
                return true;


        }else if(restriction.compareTo("value")==0){

            if(controlValue(dice)){

                this.die=dice;
                return true;

            }

        }else if(restriction.compareTo("colour")==0){

           if(controlColour(dice)){

               this.die=dice;
               return true;

           }
        }

        return false;

    }

    public Die removeDie() {

        Die removed= die;
        this.die=null;
        return removed;

    }

    public boolean controlBounds(Die dice){

        return ((this.getBoundColour() == null) && (this.getBoundValue() == null)) ||
                ((null != this.getBoundValue()) && (this.getBoundValue().equals(dice.getValueDie()))) ||
                ((null != this.getBoundColour()) && (this.getBoundColour().equals(dice.getColourDie())));

    }

    // if there aren't bound value or value dice is equals bound value return true else return false
    public boolean controlValue(Die dice){

        return ((this.getBoundValue() == null)) || (this.getBoundValue().equals(dice.getValueDie()));

    }

    // if there aren't bound colour or colour dice is equals bound colour return true else return false
    public boolean controlColour(Die dice){

        return (this.getBoundColour() == null) || (this.getBoundColour().equals(dice.getColourDie()));

    }

}