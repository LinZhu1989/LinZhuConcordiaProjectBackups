package Data.dataIO;
/**
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import GUI.mapView.*;
import GUI.mapView.MapFixedItemFactory.MapFixedItem;
import GUI.mapView.MapMonsterFactory.MapMonster;
import GUI.mapView.MapRoleFactory.MapRole;

import javax.swing.JFileChooser;
/**
 * this is the function of save the map to XML 
 * @author xinshao
 *
 */
public class MapSave {
	// define the basic map information
	public final static String FIELD_ROOT_MAP = "maps";
	public final static String FIELD_ROW_NUMBER = "rowNumber";
	public final static String FIELD_COLUMN_NUMBER = "columnNumber";
	public final static String FIELD_BLOCK_WIDTH = "blockWidth";
	public final static String FIELD_BLOCK_HEIGHT = "blockHeight";
	public final static String FIELD_PLAY_X = "playX";
	public final static String FIELD_PLAY_Y = "playY";
	public final static String FIELD_ROLE_SELECT_DONE = "roleSelectDone";
	public final static String FIELD_MAP_BLOCK = "mapBlock";
	//define map block information
	public final static String FIELD_LAND_TYPE = "landType";
	public final static String FIELD_INOUT_TYPE = "inOutType";
	public final static String FIELD_ITEM = "item";
	public final static String FIELD_BLOCK_COLUMN_NUMBER = "blockColumnNumber";
	public final static String FIELD_BLOCK_ROW_NUMBER = "blockRoleNumber";
	
	//define fileChooser
	JFileChooser fileChooser = null;
	
	String path;
	String fileName;
	
	/**
	 * this is the function of saving the current map to XML file
	 * @param mp
	 * @return
	 */
	public boolean save(MapInfor mp) {
		fileChooser();

		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();
			Element mapRootElement = doc.createElement(MapSave.FIELD_ROOT_MAP);
			doc.appendChild(mapRootElement);

			
			saveBasicMapInfor(mp, doc, mapRootElement);

			saveBlockInfor(mp, doc, mapRootElement);

			// construct the do-nothing transformation
			Transformer t = TransformerFactory.newInstance().newTransformer();
			// set indentation
			t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
					"2");
			// apply the do-nothing transformation and send the output to a file
			File f = new File(path);
			t.transform(new DOMSource(doc), new StreamResult(
					new FileOutputStream(f)));

		} catch (Exception e) {
			return false;
		}
		return true;

	}
	/**
	 * this is the function allow user to choose a file and save it on hard drive
	 */
	private void fileChooser() {
		// open a fileChooser ,allow user to choose a certain place
		fileChooser = new JFileChooser("C:\\");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooser.setDialogTitle("Save file");

		// new a fileChooser object
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			path = fileChooser.getSelectedFile().getAbsolutePath();			
			System.out.println(path);
		}
	}

	/**
	 * this is the method save the basic map information include 
	 * row number , column number , block width ,block height ,etc
	 * @param mp
	 * @param doc
	 * @param mapRootElement
	 */
	private void saveBasicMapInfor(MapInfor mp, Document doc,
			Element mapRootElement) {
		Element rowNumberElement = doc
				.createElement(MapSave.FIELD_ROW_NUMBER);
		Element columnNumberElement = doc
				.createElement(MapSave.FIELD_COLUMN_NUMBER);
		Element blockWidthElement = doc
				.createElement(MapSave.FIELD_BLOCK_WIDTH);
		Element blockHeightElement = doc
				.createElement(MapSave.FIELD_BLOCK_HEIGHT);
		Element playXElement = doc.createElement(MapSave.FIELD_PLAY_X);
		Element playYElement = doc.createElement(MapSave.FIELD_PLAY_Y);
		Element roleSelectionDoneElement = doc
				.createElement(MapSave.FIELD_ROLE_SELECT_DONE);
		
		// set content in the element
		rowNumberElement.setTextContent(new Integer(mp.getRowCount())
				.toString());
		columnNumberElement.setTextContent(new Integer(mp.getColumnCount())
				.toString());
		blockWidthElement.setTextContent(new Integer(mp.getBlockWidth())
				.toString());
		blockHeightElement.setTextContent(new Integer(mp.getBlockHeight())
				.toString());
		playXElement
				.setTextContent(new Integer(mp.getPlayerX()).toString());
		playYElement
				.setTextContent(new Integer(mp.getPlayerY()).toString());
		roleSelectionDoneElement.setTextContent(String.valueOf(mp
				.isRoleSelectDone()));
		
		// add element to the root
		mapRootElement.appendChild(rowNumberElement);
		mapRootElement.appendChild(columnNumberElement);
		mapRootElement.appendChild(blockWidthElement);
		mapRootElement.appendChild(blockHeightElement);
		mapRootElement.appendChild(playXElement);
		mapRootElement.appendChild(playYElement);
		mapRootElement.appendChild(roleSelectionDoneElement);
	}
	
	/**
	 * this is the function save map block information 
	 * @param mp
	 * @param doc
	 * @param mapRootElement
	 */
	private void saveBlockInfor(MapInfor mp, Document doc,
				Element mapRootElement) {
			for (int i = 0; i < mp.getColumnCount(); i++) {
				for (int j = 0; j < mp.getRowCount(); j++) {
					Element mapBlockElement = doc
							.createElement(MapSave.FIELD_MAP_BLOCK);
					mapRootElement.appendChild(mapBlockElement);
					mapBlockElement.setAttribute("columnNumber",
							new Integer(i).toString());
					mapBlockElement.setAttribute("rowNumber",
							new Integer(j).toString());
	
					Element landType = doc
							.createElement(FIELD_LAND_TYPE);
					landType.setTextContent(mp.getBlock(i, j).getLand().toString());
					mapBlockElement.appendChild(landType);
	
					Element inOutType = doc
							.createElement(FIELD_INOUT_TYPE);
					inOutType.setTextContent(mp.getBlock(i, j).getInOutType().toString());
					mapBlockElement.appendChild(inOutType);
					
					
					Element itemNode = doc.createElement(FIELD_ITEM);					
					IMapItem mapItem = mp.getBlock(i, j).getMapItem();					
					if (mapItem instanceof MapRole) {
						MapRole role = (MapRole)mapItem;
						CharacterSave.saveCharacter(doc, itemNode, role.getGameCharacter());						
					} else if (mapItem instanceof MapMonster) {
						MapMonster monster = (MapMonster)mapItem;
						MonsterSave.saveMonster(doc, itemNode,monster.getGameMonster());	
					} else if (mapItem instanceof MapFixedItem) {
						MapFixedItem fixedItem = (MapFixedItem)mapItem;
						FixedItemSave.saveFixedItem(doc, itemNode, fixedItem.getMapFixedItem());	
					}
					mapBlockElement.appendChild(itemNode);
					
					Element blockColumnNumber = doc
							.createElement(FIELD_BLOCK_COLUMN_NUMBER);
					blockColumnNumber.setTextContent(new Integer(mp.getBlock(i, j).getLocationX()).toString());
					mapBlockElement.appendChild(blockColumnNumber);
	
					Element blockRoleNumber = doc
							.createElement(FIELD_BLOCK_ROW_NUMBER);
					blockRoleNumber.setTextContent(new Integer(mp.getBlock(i, j).getLocationY()).toString());
					mapBlockElement.appendChild(blockRoleNumber);
				}
			}
		}

}
