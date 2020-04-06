import acm.graphics.*;
import acm.program.*;
public class BouncingBall extends GraphicsProgram{
	private static final int PAUSE_TIME=3;
	private static final int SIZEB=40;
	public void run(){
		double x=getWidth()/2;
		double y=getHeight()/2;
		GOval bal=new GOval(x,y,SIZEB,SIZEB);
		bal.setFilled(true);
		add(bal);
		double dx=1;
		double dy=1;
		while(true){
		  bal.move(dx,dy);
		   pause(PAUSE_TIME);
		   if (bal.getX()>=getWidth()-SIZEB || bal.getX()<=0){
			 dx=-dx;
		   }
		   if ( bal.getY()>=getHeight()-SIZEB || bal.getY()<=0){
			   dy=-dy;
		   }
		}
	}
}