package repolezanettiperuzzi.controller;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class ControllerTimer extends TimerTask {

    private int currentTime;
    private Timer timer;
    private Controller controller;
    private ControllerState nextState;
    private String currentState;



    public ControllerTimer(String timerType, Controller controller){

        this.timer = new Timer();
        this.controller = controller;
        this.currentState=timerType;
        this.currentTime = 10;

        //TODO apro file configurazione durata timer

        switch(timerType){

            case "setConnection" :
                this.nextState = new FetchState();
                break;

            case "chooseWindow" :
                this.nextState = new BeginRoundState();
                break;

            case "playerTurn" :
                break;

            default :
                break;

        }
    }

    @Override
    public void run() {

        currentTime--;//aggiorna il timer ad ogni esecuzione
        if (currentTime==0){

            timer.cancel();
            timer.purge();
            controller.setIsTimerOn(false);

            switch (currentState){

                case "setConnection" :

                    SetConnectionState setConnectionState = (SetConnectionState) controller.getState();
                    try {
                        setConnectionState.notifyOnBeginChooseWindow();
                    } catch (IOException | ParseException e) {
                        e.printStackTrace();
                    }
                    break;
                case "chooseWindow" :

                    FetchState fetchState = (FetchState) controller.getState();
                    try {
                        fetchState.checkConnectedPlayers();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    break;

                default :
                    break;
            }

            /*try {     setto già lo stato successivo nel metodo notifyOnBeginConnection

                controller.setState(this.nextState);

            } catch (IOException | ParseException e) {

                e.printStackTrace();

            }*/


        }

    }

    public int getCurrentTime(){

        return this.currentTime;
        
    }

    public Timer getTimer(){

        return this.timer;

    }

}

