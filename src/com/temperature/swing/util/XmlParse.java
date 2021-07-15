package com.temperature.swing.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * @author Lin
 * @version 1.0
 * @date 2020/6/30 15:17
 */
public class XmlParse
{
	public static <T> T xmlToJava(Class resourceRoot, Class type, String url) throws JAXBException
	{
		if (url == null)
		{
			return null;
		}
		JAXBContext context = JAXBContext.newInstance(type);

		Unmarshaller createUnmarshaller = context.createUnmarshaller();
		if (createUnmarshaller == null)
		{
			return null;
		}
		if (resourceRoot.getResource(url) == null)
		{
			return null;
		}

		Object unmarshal = createUnmarshaller.unmarshal(

				resourceRoot.getResource(url));
		return (T) unmarshal;
	}
}
