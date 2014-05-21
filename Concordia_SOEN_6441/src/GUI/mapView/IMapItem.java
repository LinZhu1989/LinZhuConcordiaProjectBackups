package GUI.mapView;

import java.awt.Image;
import utility.IDrawable;
import utility.MapBlockType;

/**
 * this interface is used to describe the "items" inside one
 * map block.
 * @author Jingang.Li
 *
 */
public interface IMapItem extends IDrawable {
	MapBlockType getBlockType();
	Image getImage();
	void setOwnerBlock(MapBlock blk);
}

