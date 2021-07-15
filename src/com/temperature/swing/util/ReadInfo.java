package com.temperature.swing.util;

import com.temperature.swing.event.Style;
import com.temperature.swing.pojo.ButtonInfos;
import com.temperature.swing.pojo.JPanelInfo;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/7/6 2:45
 */
public class ReadInfo
{
	public static Map<Integer, JPanelInfo> jPanelInfoMap = new HashMap<>();

	public static Map<Integer, ButtonInfos> buttonInfosHashMap = new HashMap<>();
	public static JPanelInfo readFigureInfo(int bodyId, int pageId) throws JAXBException
	{
		int key = GenerateKey.generateKey(bodyId, pageId);
		if (jPanelInfoMap.containsKey(key))
		{
			return jPanelInfoMap.get(key).clone();
		}
		String url = "resource/info/page" + (pageId) + "/" + bodyId + "/figureJPanelInfo.xml";
		JPanelInfo jPanelInfo = XmlParse.xmlToJava(Style.class, JPanelInfo.class, url);
		if (jPanelInfo == null)
		{
			return null;
		}
		jPanelInfoMap.put(key, jPanelInfo);
		JPanelInfo cl = jPanelInfo.clone();
		return jPanelInfo.clone();
	}

	public static ButtonInfos readButtonInfo(int headId, int pageId) throws JAXBException
	{
		int key = GenerateKey.generateKey(headId, pageId);
		if (buttonInfosHashMap.containsKey(key))
		{
			return buttonInfosHashMap.get(key).clone();
		}
		String url = "resource/info/page" + (pageId) + "/" + headId + "/toolJPanelInfo.xml";
		ButtonInfos buttonInfos = XmlParse.xmlToJava(Style.class, ButtonInfos.class, url);
		buttonInfosHashMap.put(key, buttonInfos);
		return buttonInfos.clone();
	}

}
