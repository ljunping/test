package com.temperature.swing.pojo;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Magic Book
 */
@XmlRootElement(name = "buttonInfos")
public class ButtonInfos extends Info implements Cloneable
{

	private List<ButtonInfo> buttonInfos;
	private RectangleInfos rectangleInfos;
	@Override
	public ButtonInfos clone()
	{
		ButtonInfos buttonInfos = new ButtonInfos();
		if (this.buttonInfos != null)
		{
			List<ButtonInfo> buttonInfos1 = new ArrayList<>();
			buttonInfos.setButtonInfos(buttonInfos1);
			for (int i = 0; i < this.getButtonInfos().size(); i++)
			{
				buttonInfos1.add(this.getButtonInfos().get(i).clone());
			}
		}
		if (rectangleInfos != null)
		{
			buttonInfos.setRectangleInfos(rectangleInfos.clone());

		}
		return buttonInfos;
	}

	public List<ButtonInfo> getButtonInfos()
	{
		return buttonInfos;
	}
	@XmlElement(name = "buttonInfo")
	public void setButtonInfos(List<ButtonInfo> buttonInfos)
	{
		this.buttonInfos = buttonInfos;
	}
	public static void main(String[] args) throws JAXBException
	{

		JAXBContext context = JAXBContext.newInstance(ButtonInfos.class);

		Unmarshaller createUnmarshaller = context.createUnmarshaller();

		Object unmarshal = createUnmarshaller.unmarshal(

				new File("resource/info/2/toolJPanelInfo.xml"));

		ButtonInfos em = (ButtonInfos) unmarshal;

		List<ButtonInfo> list = em.getButtonInfos();

		for (ButtonInfo employee : list) {

			System.out.println(employee.getText());

		}

	}

	public RectangleInfos getRectangleInfos()
	{
		return rectangleInfos;
	}
	@XmlElement(name = "rectangleInfos")
	public void setRectangleInfos(RectangleInfos rectangleInfos)
	{
		this.rectangleInfos = rectangleInfos;
	}
}
