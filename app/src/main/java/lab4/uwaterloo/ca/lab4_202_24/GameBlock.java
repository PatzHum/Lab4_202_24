package lab4.uwaterloo.ca.lab4_202_24;

import android.content.Context;
import android.widget.ImageView;

import static android.R.attr.x;

/**
 * Created by Alex on 2017-03-05.
 */

public class GameBlock extends ImageView implements Movement {



    private float IMAGE_SCALE = 0.5f;       //custom scaling to fit image into background grid **note scaling image does not change coordinate borders
    private int myCoordX;
    private int myCoordY;
    public int bx, by;
    private int blockLayoutIncrement = 243;     // coordinate pixel constant for moving one block up or down
    public Animator animator;
    public GameBlock(Context myContext, int bx, int by) {

        super(myContext);
        this.setImageResource(R.drawable.gameblock);

        this.setScaleX(IMAGE_SCALE);
        this.setScaleY(IMAGE_SCALE);
        this.bx=bx;
        this.by=by;

        this.myCoordX = blockLayoutIncrement*bx;
        this.myCoordY = blockLayoutIncrement*by;

        setPixelX(myCoordX);
        setPixelY(myCoordY);

        animator = new Animator(this);
    }

    public void moveTo(int x, int y){
        this.animator.setTarget(x * blockLayoutIncrement, y * blockLayoutIncrement);
        this.bx = x;
        this.by = y;
    }

    @Override
    public void setPixelX(int x) {
        this.setX(x-69);                //Offset to match image coordinate to background(0,0) is actually (-69, -69)
        myCoordX = x;                   //sets new image coordinates from animator
    }

    @Override
    public void setPixelY(int y) {
        this.setY(y-69);                //Offset to match image coordinate to background(0,0) is actually (-69, -69)
        myCoordY = y;                   //sets new image coordinates from animator
    }

    @Override
    public int getPixelX() {
        return myCoordX;                //send current coordinates to movement method to be used in animator
    }

    @Override
    public int getPixelY() {
        return myCoordY;
    }
}
