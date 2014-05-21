package GUI.mapView;
/**
 * this class is to define that the items in a map block . the items
 * include character , monster , fixed items
 * @author Jingang.Li
 *
 */
import java.awt.Point;

public  class MapItem {
	private MapBlock m_Owner;
	public enum STATUS {ATTACK,DEFEND} ;
	private STATUS status; 
	public MapItem() {
		status = STATUS.DEFEND;
	}
	public void beAttacked(){
		status = STATUS.ATTACK;
	}
	
	public void beHappy(){
		status = STATUS.DEFEND;
	}
	public STATUS getStatus(){
		return status;
	}
	/**
	 * return the block of its owner
	 * @return
	 */
	public MapBlock getOwnerBlock(){
		return  m_Owner;
		
	}
	/**
	 * set the owner of the block
	 * @param blk
	 */
	public void setOwnerBlock(MapBlock blk){
		m_Owner=blk;
		
	}

}