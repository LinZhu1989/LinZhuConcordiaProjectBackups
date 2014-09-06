package GUI.mainView;

import java.awt.Canvas;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import GUI.battleView.BattleDraw;
import GUI.characterView.CharacterDraw;
import GUI.mapView.MapDraw;

class frameMouseListener extends MouseAdapter{
//	int status;
//	Canvas c;
//	private MainDraw mainD;
//	private MapDraw  mapD;
//	private BattleDraw battleD;
//	frameMotionListener fml;
//	private CharacterDraw characterD;
//	frameMouseListener(Canvas c, MainDraw mainD2, MapDraw mapD2, CharacterDraw characterD2, BattleDraw battleD2){
//		mainD = mainD2;
//		mapD =mapD2;
//		characterD =characterD2;
//		battleD=battleD2;
//		this.c=c;
//		status=0;
//		fml=new frameMotionListener();
//	}
//	public void mousePressed(MouseEvent e){
//		if(status==1)
//			mapD.eHandle.mapPressedListener(e);
//	}
//	public void mouseReleased(MouseEvent e){
//		if(status==1)
//			mapD.eHandle.mapReleasedLisener(e);
//	}
//	public void mouseClicked(MouseEvent e) {
//		switch(status){
//		case 0:
//			mainD.mainListener(e);
//			break;
//		case 1: 
//			mapD.mapListener(e);
//			break;
//		case 2:
//			characterD.characterListener(e);
//			break;
//		case 3:
//			battleD.battleListener(e);
//			break;
//		
//		}
//		
//	}
//	private class frameMotionListener extends MouseMotionAdapter{
//		
//		public void mouseDragged(MouseEvent e){
//			if(status==1)
//			mapD.eHandle.mapDraggerListener(e);
//			
//		}
//	}
//	
//	
//	
//	public int getStatus() {
//		// TODO Auto-generated method stub
//		return status;
//	}
//	public void setStatus(int i){
//		this.status =i;
//	}
}
