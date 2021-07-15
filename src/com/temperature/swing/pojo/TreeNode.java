package com.temperature.swing.pojo;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 15:56
 */
@XmlRootElement(name = "node")
public class TreeNode
{
	private int id;
	private String text;
	private List<TreeNode> children;
	private EventInfo event;

	public List<TreeNode> getChildren()
	{
		return children;
	}

	public SettingTreeNode toSettingTreeNode()
	{
		return dfs(this, 0, 0);
	}

	private SettingTreeNode dfs(TreeNode treeNode, int pageId, int h)
	{
		if (treeNode != null)
		{
			SettingTreeNode settingTreeNode = new SettingTreeNode(treeNode.id, treeNode.getText(), treeNode.getEvent());
			if (treeNode.getChildren() != null)
			{
				settingTreeNode.setChildren(new ArrayList<>());

				for (int i = 0; i < treeNode.getChildren().size(); i++)
				{

					TreeNode node = treeNode.getChildren().get(i);
					SettingTreeNode settingTreeNode1 = null;
					if (h == 0)
					{
						settingTreeNode1 = dfs(node, i + 1, h + 1);
						settingTreeNode1.setPageId(i + 1);
					} else
					{
						settingTreeNode1 = dfs(node, pageId, h + 1);
						settingTreeNode1.setPageId(pageId);
					}
					settingTreeNode1.setDeep(h);
					settingTreeNode1.setParent(settingTreeNode);


					settingTreeNode.getChildren().add(settingTreeNode1);
				}
			}
			return settingTreeNode;

		}
		return null;
	}

	@XmlElement(name = "node")
	public void setChildren(List<TreeNode> children)
	{
		this.children = children;
	}


	public int getId()
	{
		return id;
	}

	@XmlAttribute(name = "id")
	public void setId(int id)
	{
		this.id = id;
	}

	public EventInfo getEvent()
	{
		return event;
	}

	@XmlElement(name = "event")
	public void setEvent(EventInfo event)
	{
		this.event = event;
	}


	public String getText()
	{
		return text;
	}

	@XmlAttribute(name = "text")
	public void setText(String text)
	{
		this.text = text;
	}


	public static class EventInfo
	{
		private String className;
		private List<String> params;

		public String getClassName()
		{
			return className;
		}

		@XmlElement(name = "className")
		public void setClassName(String className)
		{
			this.className = className;
		}

		public List<String> getParams()
		{
			return params;
		}

		@XmlElement(name = "param")
		public void setParams(List<String> params)
		{
			this.params = params;
		}

	}


}
