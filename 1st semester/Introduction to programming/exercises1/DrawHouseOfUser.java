import acm.graphics.*;
import acm.program.*;
public class DrawHouseOfUser extends GraphicsProgram{
public void run(){
  double x = getWidth();
  double y=getHeight();
  Defteromiso(x,y);
  Protomiso(PROTOKENO,y);
 }
private void Protomiso(double x,double y){
	drawKtirio(x,y-SKEPIMINI-NORMALEXTRAH,NORMALWIDHT,MINIHEIGHT+SKEPIMINI+NORMALEXTRAH,NORMALEXTRAH-SKEPINORMAL);
	drawKtirio(x+NORMALWIDHT+MAXWIDHT,y-SKEPIMINI-NORMALEXTRAH,NORMALWIDHT,MINIHEIGHT+SKEPIMINI+NORMALEXTRAH,NORMALEXTRAH-SKEPINORMAL);
	drawMaxKtirio(x+NORMALWIDHT,y-SKEPIMINI,MAXWIDHT,MINIHEIGHT+SKEPIMINI);
}
 
private void Defteromiso(double x,double y){
	drawKtirio(x+TRIAKENA,y,MINIWIDHT,MINIHEIGHT,0);
	drawKtirio(x+MINIWIDHT+TRIAKENA*2,y,MINIWIDHT,MINIHEIGHT,0);
	drawKtirio(x+MINIWIDHT*2+TRIAKENA*3,y,MINIWIDHT,MINIHEIGHT,0);
}
private void drawKtirio(double x,double y,double dx,double dy,double k){
	add(new GRect(x,y,dx,dy));
	add(new GLine(x,y,x+dx/2,y-SKEPIMINI-k));
	add(new GLine(x+dx/2,y-SKEPIMINI-k,x+dx,y));
}
private void drawMaxKtirio(double x,double y,double dx,double dy){
    drawKtirio(x,y,dx,dy,SKEPIMAX);
	drawKtirio(x+MAXWIDHT/2-MISIPORTA,y+MINIHEIGHT,MISIPORTA*2,SKEPIMINI,-SKEPIMINI+SKEPOULAH);
	drawParathiro(x,y);
	drawParathiro(x+MAXWIDHT/2,y);
}
 private void drawParathiro(double z,double y){
    add(new GOval(MAXWIDHT/6+z,y+35,SIZE,SIZE));
}
/*Diastaseis 3omoion ktirion*/
private static final double MINIWIDHT=40;
private static final double MINIHEIGHT=200;
private static final double  PANOMINIKENO=40; 
private static final double SKEPIMINI=100;
private static final double TRIAKENA=60;
/*Kena*/
private static final double PROTOKENO=40;
/*Diastaseis 2omoion ktirion*/
private static final double NORMALWIDHT=70;
private static final double NORMALEXTRAH=120;
private static final double SKEPINORMAL=120;
/*Diastaseis megalou ktiriou*/
private static final double MAXWIDHT=280;
private static final double SKEPIMAX=160;
private static final double MISIPORTA=30;
private static final double SKEPOULAH=60;
/*Megethos parathiron*/
private static final double SIZE=50;
 }
 
 