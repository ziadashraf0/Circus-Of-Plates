package Object;

public class ClownRightHand extends LoadingImage {
    private int x;
    private int y;
	private int platesHeight;

	private static ClownRightHand rightHand;
	    

	    private ClownRightHand(int posX, int posY, String path) {
	        super(posX, posY, path);
	    }
	    
	    public static synchronized ClownRightHand getInstance(int posX,int posY,String path)
	    {
	    	if(rightHand==null)
	    		rightHand=new ClownRightHand( posX,posY,path);
	    	return rightHand;
	    }

    public ClownRightHand(int posX, int posY, String path, int type) {
        super(posX, posY, path, type);
    }

    public void incPlatesHeight() {
        this.platesHeight -= 10;
    }
	public void decPlatesHeight() {
		this.platesHeight +=30;
	}
	public int getPlatesHeight() {
		return platesHeight;
	}


	@Override
    public void setY(int mX) {

	}

    @Override
    public void setX(int mX) {
    	if(mX>120&&mX<700)
            super.setX(mX);


    }
}
