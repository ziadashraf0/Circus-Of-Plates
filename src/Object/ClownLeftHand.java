package Object;

public class ClownLeftHand extends LoadingImage {
    private int x;
    private int y;
    private static int platesHeight;

    private static ClownLeftHand leftHand;
    

    private ClownLeftHand(int posX, int posY, String path) {
        super(posX, posY, path);
    }
    
    public static synchronized ClownLeftHand getInstance(int posX,int posY,String path)
    {
    	if(leftHand==null)
    		leftHand=new ClownLeftHand( posX,posY,path);
    	return leftHand;
    }

    public ClownLeftHand(int posX, int posY, String path, int type) {
        super(posX, posY, path, type);
    }

    public void incPlatesHeight() {
        platesHeight -=10;
    }
    public void decPlatesHeight() {
        platesHeight +=30;
    }


    public int getPlatesHeight() {
        return platesHeight;
    }

    @Override
    public void setY(int mX) {
    }

    @Override
    public void setX(int mX) {
    	if(mX<580)
            super.setX(mX);


    }


}
