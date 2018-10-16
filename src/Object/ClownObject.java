package Object;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.image.BufferedImage;

/**
 * Created by AHMED ESSAM on 5/8/2017.
 */
public class ClownObject extends LoadingImage {
	
	

    private int x;
    private int y;
    
    private static ClownObject Clown;
    

    private ClownObject(int posX, int posY, String path) {
        super(posX, posY, path);
    }
    
    public static synchronized ClownObject getInstance(int posX,int posY,String path)
    {
    	if(Clown==null)
    		Clown=new ClownObject( posX,posY,path);
    	return Clown;
    }
    

    public ClownObject(int posX, int posY, String path, int type) {
        super(posX, posY, path, type);
    }


    @Override
    public void setY(int mX) {
    }
    public void setX(int mX) {
    	if(mX>10&&mX<590)
    	super.setX(mX);
    
	
}

}
