package com.temperature.swing.pojo;

import com.temperature.swing.event.Event;
import com.temperature.swing.wrapJComponent.Button;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 15:56
 */
//@XmlRootElement(name = "node")
public class SettingTreeNode
{
	private int id;
	private String text;
	private List<SettingTreeNode> children;
	private SettingTreeNode parent;
	private TreeNode.EventInfo event;
	private Button button;
	private Event eventObj;
	private int deep;
	private int pageId;
	private static final Logger logger = Logger.getLogger(SettingTreeNode.class);

	public SettingTreeNode(int id, String text, TreeNode.EventInfo event)
	{
		this.id = id;
		this.text = text;
		this.event = event;
	}

	public boolean eventIsExist()
	{
		return eventObj != null;
	}

	public List<SettingTreeNode> getChildren()
	{
		return children;
	}

	//	@XmlElement(name = "node")
	public void setChildren(List<SettingTreeNode> children)
	{
		this.children = children;
	}

	public SettingTreeNode getParent()
	{
		return parent;
	}

	public void setParent(SettingTreeNode parent)
	{
		this.parent = parent;
	}

	public int getId()
	{
		return id;
	}

	//	@XmlAttribute(name = "id")
	public void setId(int id)
	{
		this.id = id;
	}

	public TreeNode.EventInfo getEvent()
	{
		return event;
	}

	//	@XmlElement(name = "event")
	public void setEvent(TreeNode.EventInfo event)
	{
		this.event = event;
	}


	public String getText()
	{
		return text;
	}

	//	@XmlAttribute(name = "text")
	public void setText(String text)
	{
		this.text = text;
	}

	public Button getButton()
	{
		return button;
	}

	public void setButton(Button button)
	{
		this.button = button;
	}

	public Event getEventObj() throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		if (eventObj == null)
		{

			createEvent();
		}
		return eventObj;
	}

	public void createEvent() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException
	{
		if (eventObj == null&&this.getEvent()!=null)
		{
			logger.debug("开始创建" + event.getClassName() + "类");
			String className = this.getEvent().getClassName();
			java.util.List<String> params = this.getEvent().getParams();
			Event event = null;
			if (params == null)
			{
				Constructor constructor = Class.forName(className).getConstructor();
				event = (com.temperature.swing.event.Event) constructor.newInstance();
			}else
			{
				Constructor constructor = Class.forName(className).getConstructor(List.class);
				event = (com.temperature.swing.event.Event) constructor.newInstance(params);
			}
			logger.debug("创建" +className + "类成功");
			this.setEventObj(event);
		}
	}

	public void setEventObj(Event eventObj)
	{
		this.eventObj = eventObj;
	}

	public int getDeep()
	{
		return deep;
	}

	public void setDeep(int deep)
	{
		this.deep = deep;
	}

	public int getPageId()
	{
		return pageId;
	}

	public void setPageId(int pageId)
	{
		this.pageId = pageId;
	}
}
