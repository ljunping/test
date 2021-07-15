package com.temperature.swing;


import com.temperature.swing.pojo.TreeNode;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/1 16:40
 */
public class Test2
{
	public static void main(String[] args) throws JAXBException
	{

		JAXBContext context = JAXBContext.newInstance(TreeNode.class);

		Unmarshaller createUnmarshaller = context.createUnmarshaller();

		Object unmarshal = createUnmarshaller.unmarshal(

				Test2.class.getResource("setting.xml"));

//		SettingTreeNode em = (SettingTreeNode) unmarshal;

	}
}
