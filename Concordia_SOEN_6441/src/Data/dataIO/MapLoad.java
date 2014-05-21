package Data.dataIO;

/**
 * @author XinShao
 * SOEN 6441 Team Project 
 * Winter 2014
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import utility.InOutType;
import utility.LandType;
import GUI.mapView.*;

import javax.swing.JFileChooser;

/**
 * this is the class load the map from XML file
 */
public class MapLoad {

	public String openFile() throws Exception {
		String path = "";
		JFileChooser fileChooser = null;

		// choose a file
		fileChooser = new JFileChooser("C:\\");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
		fileChooser.setDialogTitle("Open file");

		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			path = fileChooser.getSelectedFile().getAbsolutePath();
			System.out.println("the path of the file you choosed  is:" + path);
		}

		return path;
	}

	public boolean load(String path, MapInfor mp) {
		try {
			System.out.println("Path = " + path);

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File f = new File(path);

			Document rDoc = builder.parse(f);

			Element datasNode = rDoc.getDocumentElement();

			NodeList firstList = datasNode.getChildNodes();// point to map
			for (int i = 0; i < firstList.getLength(); ++i) {
				Node firstChildren = firstList.item(i);

				if (firstChildren instanceof Element) {
					if (((Element) firstChildren).getTagName()
							.compareToIgnoreCase(MapSave.FIELD_ROW_NUMBER) == 0) {
						mp.setRowCount(Integer.parseInt(firstChildren
								.getTextContent()));
						System.out.println("the rNumber is"
								+ firstChildren.getTextContent());
						continue;
					} else if (((Element) firstChildren).getTagName()
							.compareToIgnoreCase(MapSave.FIELD_COLUMN_NUMBER) == 0) {
						mp.setColumnCount(Integer.parseInt(firstChildren
								.getTextContent()));
						System.out.println("the cNumber is"
								+ firstChildren.getTextContent());
						continue;
					} else if (((Element) firstChildren).getTagName()
							.compareToIgnoreCase(MapSave.FIELD_BLOCK_HEIGHT) == 0) {
						mp.setBlockHeight(Integer.parseInt(firstChildren
								.getTextContent()));
						System.out.println("the blockHeight is"
								+ firstChildren.getTextContent());
						continue;
					} else if (((Element) firstChildren).getTagName()
							.compareToIgnoreCase(MapSave.FIELD_BLOCK_WIDTH) == 0) {
						mp.setBlockWidth(Integer.parseInt(firstChildren
								.getTextContent()));
						System.out.println("the blockWidth is"
								+ firstChildren.getTextContent());
						continue;
					} else if (((Element) firstChildren).getTagName()
							.compareToIgnoreCase(MapSave.FIELD_PLAY_X) == 0) {
						mp.setPlayerX(Integer.parseInt(firstChildren
								.getTextContent()));
						System.out.println("the playX is"
								+ firstChildren.getTextContent());
						continue;
					} else if (((Element) firstChildren).getTagName()
							.compareToIgnoreCase(MapSave.FIELD_PLAY_Y) == 0) {
						mp.setPlayerY(Integer.parseInt(firstChildren
								.getTextContent()));
						System.out.println("the playY is"
								+ firstChildren.getTextContent());
						continue;
					} else if (((Element) firstChildren)
							.getTagName()
							.compareToIgnoreCase(MapSave.FIELD_ROLE_SELECT_DONE) == 0) {
						mp.setRoleSelectDone(new Boolean(firstChildren
								.getTextContent()).booleanValue());
						System.out.println("the roleSelectionDone is  "
								+ firstChildren.getTextContent());
						System.out
								.println("*****************************************");
						continue;
					}
				}
			}

			mp.initBlocks(mp.getColumnCount(), mp.getRowCount());

			XPath xpath = XPathFactory.newInstance().newXPath();
			for (int c = 0; c < mp.getColumnCount(); ++c) {
				for (int r = 0; r < mp.getRowCount(); ++r) {

					String strPath = String
							.format("/maps/mapBlock[@columnNumber='%d' and @rowNumber='%d']",c, r);
					try {
						Node node = (Node) xpath.compile(strPath).evaluate(
								rDoc, XPathConstants.NODE);
						loadMapBlockNode(mp, node, c, r);
						
					} catch (Exception e) {
						System.out.println("Error on:" + Integer.toString(c)
								+ " " + Integer.toString(r));
						return false;
					}
				}
			}
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public boolean load(MapInfor mp) throws Exception {
		String filePath;
		filePath = openFile();

		if (filePath.isEmpty())
			return false;

		if(load(filePath, mp)==true){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * this is function to get the mapBlockNode
	 * 
	 * @param map
	 * @param mapNode
	 * @param cNumber
	 * @param rNumber
	 */
	private void loadMapBlockNode(MapInfor map, Node mapNode, int cNumber,
			int rNumber) {

		MapBlock blk = map.getBlock(cNumber, rNumber);

		NodeList secondList = mapNode.getChildNodes();
		for (int k = 0; k < secondList.getLength(); k++) {
			Node secondChildren = secondList.item(k);

			if (secondChildren instanceof Element) {
				if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(MapSave.FIELD_BLOCK_COLUMN_NUMBER) == 0) {
					blk.setLocationX(Integer.parseInt(secondChildren
							.getTextContent()));
					continue;
				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(MapSave.FIELD_BLOCK_ROW_NUMBER) == 0) {
					blk.setLocationY(Integer.parseInt(secondChildren
							.getTextContent()));
					continue;
				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(MapSave.FIELD_LAND_TYPE) == 0) {
					blk.setLand(LandType.valueOf(secondChildren
							.getTextContent()));
					System.out.println("the block[" + cNumber + "][" + rNumber
							+ "]'s land" + "is "
							+ secondChildren.getTextContent());
					continue;
				} else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(MapSave.FIELD_INOUT_TYPE) == 0) {
					blk.setInOut(InOutType.valueOf(secondChildren
							.getTextContent()));
//					System.out.println("the block[" + cNumber + "][" + rNumber
//							+ "]'s inOutType" + "is "
//							+ secondChildren.getTextContent());
					continue;
				}
				else if (((Element) secondChildren).getTagName()
						.compareToIgnoreCase(MapSave.FIELD_ITEM) == 0) {
					
					NodeList itemNodeList = secondChildren.getChildNodes();
					for (int i = 0; i < itemNodeList.getLength(); ++i) {
					Node children = itemNodeList.item(i);
					//children  is either a name is characters or monster or fixedItem
					if (children instanceof Element) {
						if (((Element) children).getTagName().compareToIgnoreCase("characters")==0){
							CharacterLoad load = new CharacterLoad();
				    blk.setRole(load.loadCharacter((Element)children,blk));
						}else if (((Element) children).getTagName().compareToIgnoreCase("monster")==0){
							MonsterLoad load = new MonsterLoad();
					blk.setRole(load.loadMonster((Element)children,blk));
							
						}else if (((Element) children).getTagName().compareToIgnoreCase("fixedItem")==0){
							FixedItemLoad load = new FixedItemLoad();
							load.loadFixedItem((Element)children, blk);
							
						}
					}
					
//					blk.setRole((InOutType.valueOf(secondChildren
//							.getTextContent()));
//					System.out.println("the block[" + cNumber + "][" + rNumber
//							+ "]'s inOutType" + "is "
//							+ secondChildren.getTextContent());
					}
					
					continue;
					
				}
			}
		}
	}
}
