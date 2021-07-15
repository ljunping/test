package com.temperature.swing.event;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/8 19:04
 */


import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.wrapJComponent.CenterJPanel;
import com.temperature.swing.wrapJComponent.MyJPanel;
import com.temperature.swing.wrapJComponent.ToolJPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 14:20
 */
public abstract class JComBoxEvent implements Event
{
	protected SettingTreeNode settingTreeNode;
	public abstract void chartUpdate();

	@Override
	public void eventActive(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JAXBException
	{

		this.settingTreeNode = settingTreeNode;
		int pageId = settingTreeNode.getPageId();
		CenterJPanel centerJPanel = MyJPanel.pageJPanel[pageId - 1];
		JPanel jPanel = (JPanel) centerJPanel.headJPanel.getComponent(0);
		ToolJPanel toolJPanel = (ToolJPanel) jPanel.getComponent(0);
		toolJPanel.setPartVisible(true);

//		if (pageId == 4 || pageId == 5)
//		{
//			PaddingUnit.setPadding(toolJPanel.leftToolJPanel, new int[]{0, 0, 0, 0}, 0);
//		}
		chartUpdate();
	}

	@Override
	public Object getImpObject() throws JAXBException, ClassNotFoundException
	{
		return null;
	}
//	private Map<String, TextInfo> map = new HashMap<>();
//	private Map<Integer, FigureJPanel> jPanelInfoMap = new HashMap<>();
//	private JPanel jPanel = null;
//	private int pageId;
//
//	public int getPageId()
//	{
//		return pageId;
//	}
//
//	public void setPageId(int pageId)
//	{
//		this.pageId = pageId;
//	}

//	class Par
//	{
//		FigureJPanel figureJPanel;
//		RectangleInfos rectangleInfos;
//
//		public Par(FigureJPanel figureJPanel, RectangleInfos rectangleInfos)
//		{
//			this.figureJPanel = figureJPanel;
//			this.rectangleInfos = rectangleInfos;
//		}
//	}
//

//
//	private void createJPanel(SettingTreeNode settingTreeNode) throws NoSuchMethodException, InstantiationException, JAXBException, IllegalAccessException, InvocationTargetException, ClassNotFoundException
//	{
//		String sensor = "通道" + MyJPanel.pageJPanel[settingTreeNode.getPageId()-2].sensorId;
//
//		SettingTreeNode root = App.settingTreeNodeRoot.getChildren().get(0);
//		LinkedList<JComBoxEvent.Par> parList = new LinkedList<>();
////		removeOldRec();
//		if (sensor != null)
//		{
//
//			chartUpdate();
//
//			FigureJPanel headJPanel = createHead(settingTreeNode);
//			JPanel jPanel = new JPanel(new GridLayout(1, 1));
//
//
//			if (!"通道all".equals(sensor))
//			{
//				dfs(root, parList, sensor);
//				if (!parList.isEmpty())
//				{
//					JPanel jPanel1 = new JPanel();
//					jPanel1.setLayout(new GridLayout(Math.max(parList.size(), 1), 1, 0, 10));
//					jPanel1.setBackground(Color.WHITE);
//
//					parList.add(new Par(headJPanel, parList.removeLast().rectangleInfos));
//					drawRec(parList);
//
//					for (int i = parList.size() - 1; i >= 0; --i)
//					{
//						FigureJPanel figureJPanel = parList.get(i).figureJPanel;
//						PaddingUnit.setMargin(figureJPanel, new int[]{10, 0, 0, 0}, 2);
//						figureJPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
//						figureJPanel.setPreferredSize(new Dimension(10000, 900));
//						jPanel1.add(figureJPanel);
//					}
//
//					JScrollPane scrollPane = new JScrollPane(jPanel1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
//					scrollPane.setBackground(Color.white);
//					JScrollBar scrollBar = scrollPane.getVerticalScrollBar();
//					scrollBar.setUnitIncrement(25);
//					jPanel.add(scrollPane);
//				}
//
//			} else
//			{
//				headJPanel.setPreferredSize(new Dimension(10000, 900));
//				headJPanel.imageInfos.getImageInfos().get(0).setRectangleInfos(null);
//				headJPanel.setBackground(Color.WHITE);
//				jPanel.setBackground(Color.WHITE);
//
//				jPanel.add(headJPanel);
//			}
//			this.jPanel = jPanel;
//
//
//		}
//	}


//	private FigureJPanel createHead(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JAXBException
//	{
//		Event event = settingTreeNode.getParent().getEventObj();
//		Class clazz = Class.forName(settingTreeNode.getParent().getEvent().getClassName());
//		Field[] fields = clazz.getDeclaredFields();
//		int bodyId = 0;
//		int pageId = 0;
//		for (int i = 0; i < fields.length; i++)
//		{
//			if ("bodyId".equals(fields[i].getName()))
//
//			{
//				fields[i].setAccessible(true);
//
//				bodyId = fields[i].getInt(clazz.cast(event));
//			}
//			if ("pageId".equals(fields[i].getName()))
//			{
//				fields[i].setAccessible(true);
//				pageId = fields[i].getInt(clazz.cast(event));
//			}
//		}
//
//		int Key = GenerateKey.generateKey(bodyId, pageId);
//		if (jPanelInfoMap.containsKey(Key))
//		{
//			return jPanelInfoMap.get(Key);
//		}
//		JPanelInfo jPanelInfo = ReadInfo.readFigureInfo(bodyId, pageId);
//		FigureJPanel figureJPanel = new FigureJPanel();
//		figureJPanel.imageInfos = jPanelInfo.getImageInfos();
//		jPanelInfoMap.putIfAbsent(Key, figureJPanel);
//		if (jPanelInfo.getImageInfos() != null && jPanelInfo.getImageInfos().getImageInfos() != null)
//		{
//			for (ImageInfo imageInfo : jPanelInfo.getImageInfos().getImageInfos())
//			{
//				if (imageInfo.getTextInfos() != null && imageInfo.getTextInfos().getTextInfos() != null)
//				{
//					for (TextInfo textInfo : imageInfo.getTextInfos().getTextInfos())
//					{
//						map.putIfAbsent(textInfo.getText(), textInfo);
//					}
//				}
//			}
//		}
//
//
//		return figureJPanel;
//	}
//

//
////	private void removeOldRec()
////	{
////		if (parList != null)
////		{
////			for (int i = 0; i < parList.size(); i++)
////			{
////				FigureJPanel figureJPanel = parList.get(i).figureJPanel;
////				figureJPanel.rectangleInfos = null;
////			}
////		}
////
////	}
//
//	private void drawRec(List<Par> parList)
//	{
//		for (int i = 0; i < parList.size(); i++)
//		{
//			FigureJPanel figureJPanel = parList.get(i).figureJPanel;
//			RectangleInfos rectangleInfos = parList.get(i).rectangleInfos;
//			figureJPanel.imageInfos.getImageInfos().get(rectangleInfos.
//					getRectangleInfos().get(0).getImageId()).setRectangleInfos(rectangleInfos);
//		}
//	}
//
//	private boolean dfs(SettingTreeNode root, List<Par> parList, String sensor) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, JAXBException
//	{
//		if (root != null)
//		{
//
//			FigureJPanel figureJPanel = getFigureJPanel(root);
//			TextInfo textInfo = isFind(figureJPanel, sensor);
//			if (textInfo != null)
//			{
//				Par par = new Par(figureJPanel, textInfo.getRecs());
//
//
//				parList.add(par);
//				return true;
//			}
//			if (root.getChildren() != null)
//			{
//				for (SettingTreeNode settingTreeNode : root.getChildren())
//				{
//
//					if (dfs(settingTreeNode, parList, sensor))
//					{
//						FigureJPanel figureJPanel2 = getFigureJPanel(root);
//
//						String text = settingTreeNode.getText();
//						parList.add(new Par(figureJPanel2, map.get(text).getRecs()));
//
//						return true;
//					}
//				}
//			}
//			return false;
//
//		}
//		return false;
//
//	}
//
//
//	private TextInfo isFind(FigureJPanel figureJPanel, String sensor)
//	{
//		List<TextInfo> textInfos = null;
//		if (figureJPanel != null)
//		{
//			if (figureJPanel.imageInfos != null)
//			{
//				for (int i = 0; i < figureJPanel.imageInfos.getImageInfos().size(); i++)
//				{
//					if (figureJPanel.imageInfos.getImageInfos().get(i).getTextInfos() != null)
//					{
//						textInfos = figureJPanel.imageInfos.getImageInfos().get(i).getTextInfos().getTextInfos();
//						if (textInfos == null)
//						{
//							return null;
//						} else
//						{
//							for (TextInfo textInfo : textInfos)
//							{
//								textInfo.setImageId(i);
//								map.putIfAbsent(textInfo.getText(), textInfo);
//								if (textInfo.getText().equals(sensor))
//								{
//									return textInfo;
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//
//		return null;
//	}
//
//	private FigureJPanel getFigureJPanel(SettingTreeNode settingTreeNode) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, JAXBException
//	{
//		Event event = settingTreeNode.getEventObj();
//		if (event == null)
//		{
//			return null;
//		}
//
//		FigureShow figureShow = (FigureShow) event.getImpObject();
//
//		if (figureShow == null)
//		{
//			return null;
//		}
//		int bodyId = figureShow.getBodyId();
//		int pageId = figureShow.getPageId();
//		int key = GenerateKey.generateKey(bodyId, pageId);
//		JPanelInfo jPanelInfo = null;
//		if (jPanelInfoMap.containsKey(key))
//		{
//			return jPanelInfoMap.get(key);
//		}
//		jPanelInfo = ReadInfo.readFigureInfo(bodyId, pageId);
//		FigureJPanel figureJPanel = new FigureJPanel();
//		figureJPanel.imageInfos = jPanelInfo.getImageInfos();
//		jPanelInfoMap.putIfAbsent(key, figureJPanel);
//
//
//		return figureJPanel;
//	}

}
