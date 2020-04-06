import acm.program.*;
public class Math4 extends ConsoleProgram{
	public void run(){
		double[] x=new double[10];
		double[] y=new double[10];
		x[0]=0;
		y[0]=0.5;
		euler(x,y,0.5,10);
		euler(x,y,-0.5,10);
	}
	public void euler(double[] x, double[] y,double Dx,double N){
		println("N  ||  x  ||   y     ");
		println("  "+0+"  "+x[0]+"  "+y[0]);
		for(int i=1;i<N;i++){
			x[i]=x[i-1]+Dx;
			y[i]=y[i-1]+Dx*f(x[i-1],y[i-1]);
			println("  "+i+"  "+x[i]+"  "+y[i]);
		}
	}
	private double f(double x,double y){
		return y*(1-y);
	}
}