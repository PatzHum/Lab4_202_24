package lab4.uwaterloo.ca.lab4_202_24;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.TimerTask;
import java.util.Vector;

import static android.content.ContentValues.TAG;

/**
 * Created by Alex on 2017-02-21.
 */

public class GameLoopTask extends TimerTask implements GestureCallback {

    Activity myActivity;
    Context myContext;
    RelativeLayout myRL;
    int blockLayoutIncrement = 243;     // coordinate pixel constant for moving one block up or down
    Direction CurrentDirection;
    Vector<Animator> animators = new Vector<>();
    GestureCallback mainCallBack;
    GameBlock currentBlock;
    Animator animator1;


    public GameLoopTask(Activity myActivity1, RelativeLayout myRL1, Context myContext1, GestureCallback mainCallBack ){       //Constructor for gameloopTask
        myActivity = myActivity1;
        myContext = myContext1;
        myRL = myRL1;
        this.mainCallBack = mainCallBack;
        createBlock();          //instantiate block
        animator1 = new Animator(currentBlock);
        animators.add(animator1);

    }
    @Override
    public void onGestureDetect(Direction direction) {      //Stores current direction returned from FSM in Acceleration handler to Current Direction local variable
        mainCallBack.onGestureDetect(direction);        //send direction back to main to be outputted onto screen in textview
        CurrentDirection = direction;
        Log.d(TAG, "setDirection: " + CurrentDirection);    //logd onto console for testing

        //state machine to determine target coordinates for appropriate gestures
        // send target coordinates to animator method for each specific movement

        switch (CurrentDirection){
            case UP:
                animator1.setTarget(animator1.contextObject.getPixelX(),0);
                break;

            case DOWN:
                animator1.setTarget(animator1.contextObject.getPixelX(),blockLayoutIncrement*3);
                break;
            case LEFT:
                animator1.setTarget(0,animator1.contextObject.getPixelY());
                break;
            case RIGHT:
                animator1.setTarget(blockLayoutIncrement*3,animator1.contextObject.getPixelY());
                break;
        }
    }






    @Override
    public void run() {
        this.myActivity.runOnUiThread(
                new Runnable(){
                    public void run(){

                       for (int i = 0; i < animators.size(); i++) {
                           animators.get(i).tick();
                       }
                    }
                }
        );


    }

    private void createBlock(){
        GameBlock newBlock = new GameBlock(myContext,0, 0);//Instantiates new block at top left = (0,0), image scaling offset in GameBlock.
        myRL.addView(newBlock);     //add new block to relative layout
        currentBlock = newBlock;




    }


}
