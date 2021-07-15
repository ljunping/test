package com.temperature.swing.event;

import com.temperature.swing.pojo.*;
import com.temperature.swing.util.GenerateKey;
import com.temperature.swing.util.PaddingUnit;
import com.temperature.swing.util.ReadInfo;
import com.temperature.swing.wrapJComponent.*;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 19:24
 */
public class HeadStyleToolBar extends HeadStyle
{

	public ToolJPanel toolJPanel;
	private SettingTreeNode parent;
	private FigureJPanel figureJPanel;
	private final int headId;
	private final int pageId;
	private int key;
	public static Map<Integer, ToolJPanel> map = new HashMap<>();
	Box leftBox = Box.createHorizontalBox();

	public HeadStyleToolBar(int headId, int pageId, SettingTreeNode settingTreeNode)
	{
		this.settingTreeNode = settingTreeNode;
		this.jPanel = new JPanel(new GridLayout(1, 1));
		jPanel.setBackground(Color.YELLOW);
		this.headId = headId;
		this.pageId = pageId;
		this.key = GenerateKey.generateKey(headId, pageId);
	}

	@Override
	public void createStyle() throws JAXBException, ClassNotFoundException
	{
		createHeadStyle();
	}

	@Override

	public void createHeadStyle() throws JAXBException, ClassNotFoundException
	{
		if (map.containsKey(this.key))
		{
			toolJPanel = map.get(key);
		} else
		{
			toolJPanel = new ToolJPanel();
			parent = settingTreeNode;
			List<SettingTreeNode> settingTreeNodes = settingTreeNode.getChildren();

			ButtonInfos buttonInfos = ReadInfo.readButtonInfo(headId, pageId);
			if (buttonInfos != null)
			{
				int id = 0;
				if (buttonInfos.getButtonInfos() != null)
				{
					for (int i = 0; i < buttonInfos.getButtonInfos().size(); i++)
					{
						ButtonInfo buttonInfo = buttonInfos.getButtonInfos().get(i);

						if (buttonInfo.getType() == 0)
						{
							createButton(settingTreeNodes.get(id++), buttonInfo);
						}
						if (buttonInfo.getType() == 1)
						{
							createComBox(settingTreeNodes.get(id++), buttonInfo);
						}
						if (buttonInfo.getType() == 2)
						{
							createDatePick(settingTreeNodes.get(id++), buttonInfo);
						}
						if (buttonInfo.getType() == 3)
						{
							createTextArea(buttonInfo);
						}
						//label
						if (buttonInfo.getType() == 4)
						{
							createlabel(buttonInfo);
						}
						if (buttonInfo.getType() == 5)
						{
							createCheck(buttonInfo);
						}
						if (buttonInfo.getType() == 6)
						{
							createCombine(settingTreeNodes.get(id++),buttonInfo,id);
						}
					}
				}
				if (buttonInfos.getRectangleInfos() != null)
				{
					createDateRec(settingTreeNodes, buttonInfos.getRectangleInfos(), id);
				}

			}
//			if (settingTreeNode.getPageId() != 2)
//			{
//				PaddingUnit.setMargin(toolJPanel.leftToolJPanel, new int[]{5, 0, 0, 0}, 0);
//				PaddingUnit.setMargin(toolJPanel.rightToolJPanel, new int[]{0, 0, 0, 0}, 0);
//			}


			map.put(key, toolJPanel);
		}
//		figureJPanel.update();

		this.jPanel.add(toolJPanel);
		this.jPanel.setBackground(Color.red);

	}

	private void createCombine(SettingTreeNode settingTreeNode, ButtonInfo buttonInfo,int id)
	{
		JPanel LeftToolJPanel = toolJPanel.leftToolJPanel;
		Box hBox = Box.createHorizontalBox();
		SettingTreeNode settingTreeNode1 = settingTreeNode;
		SettingTreeNode settingTreeNode2 = this.settingTreeNode.getChildren().get(id);
		List<Item> list = buttonInfo.getItems().getItem();
		Vector<Item> vector1 = new Vector<>();
		for (int i = 0; i < list.size(); i++)
		{
			vector1.add(list.get(i));
		}
		WithJComBoxCombine withJComBoxCombine = new WithJComBoxCombine(settingTreeNode1, settingTreeNode2, vector1);
		hBox.add(withJComBoxCombine.headJComponent);
		hBox.add(Box.createHorizontalStrut(40));
		hBox.add(withJComBoxCombine.tailLabel);
		hBox.add(Box.createHorizontalStrut(10));
		hBox.add(withJComBoxCombine.tailJComponent);
		LeftToolJPanel.add(hBox);
	}

	private void createCheck(ButtonInfo buttonInfo)
	{
		JPanel LeftToolJPanel = toolJPanel.leftToolJPanel;
		JPanel rightToolJPanel = toolJPanel.rightToolJPanel;


		WrapCheckBox wrapCheckBox = new WrapCheckBox(buttonInfo);
		if (buttonInfo.getIn() == 0)
		{
			LeftToolJPanel.add(wrapCheckBox);
		} else
		{
			rightToolJPanel.add(wrapCheckBox);
		}

	}

	private void createButton(SettingTreeNode settingTreeNode, ButtonInfo buttonInfo) throws ClassNotFoundException
	{
		JPanel LeftToolJPanel = toolJPanel.leftToolJPanel;
		JPanel rightToolJPanel = toolJPanel.rightToolJPanel;
		ToolBarButton button = new ToolBarButton(buttonInfo, "ToolBarButton");
		settingTreeNode.setButton(button);

		button.setSettingTreeNode(settingTreeNode);
		if (buttonInfo.getIn() == 0)
		{
			LeftToolJPanel.add(button);
		} else
		{
			rightToolJPanel.add(button);
		}
	}

	private void createlabel(ButtonInfo buttonInfo)
	{
		JPanel LeftToolJPanel = toolJPanel.leftToolJPanel;
		JPanel rightToolJPanel = toolJPanel.rightToolJPanel;
		UpLabel myLabel = new UpLabel(buttonInfo.getText());
		if (settingTreeNode.getPageId() == 2)
		{
			if (buttonInfo.getText().startsWith("截面"))
			{
				PaddingUnit.setMargin(myLabel, new int[]{10, 10, 10, 10}, 0);
				myLabel.setOpaque(true);
				myLabel.setForeground(Color.BLACK);

				myLabel.setBackground(ToolBarButton.defaultColor);

			}
		}
		if (buttonInfo.getIn() == 0)
		{
			LeftToolJPanel.add(myLabel);
		} else
		{
			rightToolJPanel.add(myLabel);
		}

	}

	private void createDatePick(SettingTreeNode settingTreeNode, ButtonInfo buttonInfo)
	{
		JPanel LeftToolJPanel = toolJPanel.leftToolJPanel;
		JPanel rightToolJPanel = toolJPanel.rightToolJPanel;
		DateComponent datePick = new DateComponent(settingTreeNode);
		JPanel tp = rightToolJPanel;

		if (buttonInfo.getIn() == 0)
		{
			tp = LeftToolJPanel;
		}
		tp.add(datePick.getSelectCombox());
		tp.add(datePick.getYearComBox());
		tp.add(datePick.getYearLabel());
		tp.add(datePick.getMonthComBox());
		tp.add(datePick.getMonLabel());
		tp.add(datePick.getDayComBox());
		tp.add(datePick.getDayLabel());
		datePick.init();

	}

	private void createComBox(SettingTreeNode settingTreeNode, ButtonInfo buttonInfo)
	{
		JPanel LeftToolJPanel = toolJPanel.leftToolJPanel;
		JPanel rightToolJPanel = toolJPanel.rightToolJPanel;
		List<Item> items = buttonInfo.getItems().getItem();
		Vector<Item> itemVector = new Vector<>();
		if (items != null && !items.isEmpty())
		{
			for (int j = 0; j < items.size(); j++)
			{
				itemVector.add(items.get(j));
			}
		}
		WrapJComBox jComboBox = new WrapJComBox(settingTreeNode, itemVector);

		if (buttonInfo.getIn() == 0)
		{

			LeftToolJPanel.add(jComboBox);
		} else
		{
			rightToolJPanel.add(jComboBox);
		}
	}

	private void createDateRec(List<SettingTreeNode> settingTreeNodes, RectangleInfos rectangleInfos, int id) throws ClassNotFoundException
	{
		BodyStyleFigure bodyStyle = (BodyStyleFigure) this.getBodyStyle();
		FigureJPanel figureJPanel = bodyStyle.getFigureJPanel();
		this.figureJPanel = figureJPanel;
		JLayeredPane jLayeredPane = bodyStyle.getjLayeredPane();
		JPanel buttonJPanel = new JPanel(null);
		buttonJPanel.setOpaque(false);
		buttonJPanel.setBackground(Color.ORANGE);
		buttonJPanel.setBounds(0, 0, 10000, 10000);
		//figureJPanel.rectangleInfos = rectangleInfos;
		if (rectangleInfos != null)
		{
			List<RectangleInfo> rectangleInfoList = rectangleInfos.getRectangleInfos();
			if (rectangleInfoList != null)
			{
				figureJPanel.recButtons = new ArrayList<>();
				for (int i = 0; i < rectangleInfoList.size(); i++)
				{
					SettingTreeNode settingTreeNode = settingTreeNodes.get(id++);

					RecButton recButton = new RecButton(rectangleInfoList.get(i), "RecButton");
					settingTreeNode.setButton(recButton);
					recButton.setSettingTreeNode(settingTreeNode);
					buttonJPanel.add(recButton);
					figureJPanel.recButtons.add(recButton);
					figureJPanel.getSet().add(recButton.getRectangleInfo().getText());


				}
			}
			jLayeredPane.add(buttonJPanel, 0);

		}

	}

	private void createTextArea(ButtonInfo buttonInfo)
	{
		JPanel LeftToolJPanel = toolJPanel.leftToolJPanel;
		JPanel rightToolJPanel = toolJPanel.rightToolJPanel;
		UpTextArea myJTextArea = new UpTextArea(buttonInfo.getText());
		if (buttonInfo.getIn() == 0)
		{
			LeftToolJPanel.add(myJTextArea);
		} else
		{
			rightToolJPanel.add(myJTextArea);
		}
	}

	@Override
	public void update()
	{

	}


	public int getKey()
	{
		return key;
	}

	public void setKey(int key)
	{
		this.key = key;
	}
}
