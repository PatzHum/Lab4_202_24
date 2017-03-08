package lab4.uwaterloo.ca.lab4_202_24;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by Alex on 2017-03-05.
 */

public class GameBlock extends ImageView implements Movement {



    private float IMAGE_SCALE = 0.5f;       //custom scaling to fit image into background grid **note scaling image does not change coordinate borders
    private int myCoordX;
    private int myCoordY;



    public GameBlock(Context myContext, int coordX, int coordY) {
        super(myContext);
        this.setImageResource(R.drawable.gameblock);

        this.setScaleX(IMAGE_SCALE);
        this.setScaleY(IMAGE_SCALE);

        setPixelX(coordX);
        setPixelY(coordY);

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
