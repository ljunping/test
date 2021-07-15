package com.temperature.swing;

import com.temperature.swing.beautyeye.BeautyEyeLNFHelper;
import com.temperature.swing.pojo.SettingTreeNode;
import com.temperature.swing.pojo.TreeNode;
import com.temperature.swing.util.XmlParse;
import com.temperature.swing.wrapJComponent.MyJFrame;

import javax.xml.bind.JAXBException;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 23:11
 */
public class App
{
	public Class<? extends App> resourceRoot;
	public static Font defaultFont = new Font("楷体", Font.PLAIN, 20);
	private final MyJFrame jFrame;
	private final String url = "setting.xml";
	public final static Color defaultColor = new Color(77, 180, 193);

	public static SettingTreeNode settingTreeNodeRoot;

	public App()
	{
		this.jFrame = MyJFrame.getInstance();
		this.isUseBeauty();
		resourceRoot = this.getClass();
	}

	public void isUseBeauty()
	{
		try
		{
			BeautyEyeLNFHelper.launchBeautyEyeLNF();

		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
	}


	public static void main(String[] args) throws  JAXBException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
	{

		App app = new App();
		app.loadData();
		settingTreeNodeRoot.getEventObj().eventActive(settingTreeNodeRoot);

		app.jFrame.setVisible(true);
		app.jFrame.setLocationRelativeTo(null);
	}

	private void loadData() throws JAXBException
	{
		TreeNode node = XmlParse.<TreeNode>xmlToJava(App.class, TreeNode.class, url);
		settingTreeNodeRoot = node.toSettingTreeNode();
	}
}
