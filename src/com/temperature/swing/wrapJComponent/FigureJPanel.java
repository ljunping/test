package com.temperature.swing.wrapJComponent;


import com.temperature.swing.event.Style;
import com.temperature.swing.pojo.*;
import sun.font.FontDesignMetrics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;


/**
 * @author Magic Book
 */
public class FigureJPanel extends JLayeredPane
{
	public static BasicStroke lineStroke = new BasicStroke(2.0f);
	public static BasicStroke cycleStroke = new BasicStroke(2.0f);
	public static BasicStroke recStroke = new BasicStroke(2.0f);
	public static BasicStroke textStroke = new BasicStroke(2.0f);
	public static BasicStroke arrowStroke = new BasicStroke(2.0f);
	public static BasicStroke dottedStroke = new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 3.5f, new float[]{15, 10}, 0f);
	public static Color rectColor = new Color(40, 184, 184);
	public static Color LineColor = new Color(0, 0, 0);
	public static Color imageColor = new Color(255, 255, 255);
	public static Color cycleColor = new Color(0, 0, 0);
	public static Color dottedColor = new Color(0, 0, 0);
	public static Color arrowColor = new Color(0, 0, 0);
	public static Color textColor = new Color(0, 0, 0);
	public static Color recMoveColor = new Color(208, 137, 29);
	public static List<FigureJPanel> figureJPanelList = new ArrayList<>();
	public List<RecButton> recButtons;
	public ImageInfos imageInfos = null;
	public RectangleInfos rectangleInfos = null;
	private final double defaultEmptyWidth = 100;
	private final double defaultEmptyHeight = 20;
	private double defaultWidth = CenterJPanel.bodyWidth - defaultEmptyWidth;
	private double defaultHeight = CenterJPanel.bodyHeight - defaultEmptyHeight;
	private double jPanelWidth;
	private double jPanelheight;
	private double emptyWidth;
	private double emptyHeight;
	private Range[] ranges = null;
	private Set<String> set = new HashSet<>();
	private boolean isText = false;
	private boolean upDateSwitch = true;

	public FigureJPanel()
	{
		super();
		this.setLayout(null);
		this.setBackground(Color.white);
		figureJPanelList.add(this);
	}
	public FigureJPanel(JPanelInfo jPanelInfo)
	{
		super();
		this.setLayout(null);
		this.setBackground(Color.white);
		this.imageInfos = jPanelInfo.getImageInfos();
		figureJPanelList.add(this);
	}

	private boolean isUpdate()
	{

		double x = this.getParent().getWidth()*1.0 / CenterJPanel.bodyWidth;
		double y = this.getParent().getHeight()*1.0 / CenterJPanel.bodyHeight;
		double cWith = x * defaultWidth;
		double cHeight = y * defaultHeight;
		if (norm(jPanelheight - cHeight, cWith - jPanelWidth) > 1)
		{
			emptyWidth = defaultEmptyWidth * x;
			emptyHeight = defaultEmptyHeight * y;
			this.jPanelWidth = cWith;
			this.jPanelheight = cHeight;
			return true;
		} else
		{
			return false;
		}
	}

	private double norm(double v, double v1)
	{
		double norm = Math.pow(Math.pow(v, 2) + Math.pow(v1, 2), 0.5);
		return norm;
	}


//	public static void allRevise(int jPanelWidth, int jPanelheight)
//	{
//		double x = jPanelWidth * 1.0 / CenterJPanel.bodyWidth;
//		double y = jPanelheight * 1.0 / CenterJPanel.bodyHeight;/**/
//		for (FigureJPanel figureJPanel : figureJPanelList)
//		{
//			figureJPanel.setSize((int) (x * figureJPanel.getDefaultWidth()), (int) (y * figureJPanel.getDefaultHeight()));
//			allRevise(figureJPanel.imageInfos);
//			allRevise(figureJPanel.recButtons);
//		}
//	}

	public void upDate(int jPanelWidth, int jPanelheight)
	{
		setSize(jPanelWidth, jPanelheight);
		update();
	}

	public void setIsText(boolean tag)
	{
		isText = tag;
	}

	public Set<String> getSet()
	{
		return set;
	}

	public void setSet(Set<String> set)
	{
		this.set = set;
	}

	public boolean isUpDateSwitch()
	{
		return upDateSwitch;
	}

	public void setUpDateSwitch(boolean upDateSwitch)
	{
		this.upDateSwitch = upDateSwitch;
	}

	public double getJPanelWidth()
	{
		return jPanelWidth;
	}

	public void setJPanelWidth(int panelWidth)
	{
		jPanelWidth = panelWidth;
	}

	public double getJPanelheight()
	{
		return jPanelheight;
	}

	public void setJPanelheight(int panelheight)
	{
		jPanelheight = panelheight;
	}

	public double getDefaultWidth()
	{
		return defaultWidth;
	}

	public void setDefaultWidth(double defaultWidth)
	{
		this.defaultWidth = defaultWidth;
	}

	public double getDefaultHeight()
	{
		return defaultHeight;
	}

	public void setDefaultHeight( double defaultHeight)
	{
		this.defaultHeight = defaultHeight;
	}

	public double getEmptyWidth()
	{
		return emptyWidth;
	}

	public void setEmptyWidth(double emptyWidth)
	{
		this.emptyWidth = emptyWidth;
	}

	public double getEmptyHeight()
	{
		return emptyHeight;
	}

	public void setEmptyHeight(double emptyHeight)
	{
		this.emptyHeight = emptyHeight;
	}

	class Range
	{
		double originX;
		double originY;
		double endX;
		double endY;

		public Range(double originX, double originY, double endX, double endY)
		{
			this.originX = originX;
			this.originY = originY;
			this.endX = endX;
			this.endY = endY;

		}

		public double getHeight()
		{
			return endY - originY;
		}

		public double getWidth()
		{
			return endX - originX;
		}
	}


	private static void allRevise(List<RecButton> recButtons)
	{
		if (recButtons != null)
		{
			for (RecButton recButton : recButtons)
			{
				recButton.setUpdate(false);
			}
		}
	}

	private static void allRevise(ImageInfos imageInfos)
	{
		if (imageInfos != null)
		{
			for (int i = 0; i < imageInfos.getImageInfos().size(); i++)
			{
				imageInfos.getImageInfos().get(i).setUpdateAll(false);
			}
		}
	}

	public void update()
	{

		reviseRanges();
		locRevise();
	}


	@Override
	public void paint(Graphics graphics)
	{

		super.paint(graphics);
		Graphics2D g2d = (Graphics2D) graphics;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (isUpdate())
		{
			update();
		}

		if (imageInfos != null && imageInfos.getImageInfos() != null)
		{

			for (int i = 0; i < imageInfos.getImageInfos().size(); i++)
			{
				ImageInfo imageInfo = imageInfos.getImageInfos().get(i);
				if (imageInfo.getImageUrl() != null)
				{
					drawImage(g2d, imageInfo);
				}
				if (imageInfo.getRectangleInfos() != null && imageInfo.getRectangleInfos().getRectangleInfos() != null)
				{
					drawRect(g2d, imageInfo.getRectangleInfos());
				}
				if (imageInfo.getLineInfos() != null && imageInfo.getLineInfos().getLineInfos() != null)
				{
					drawLine(g2d, imageInfo.getLineInfos());
				}
				if (imageInfo.getTextInfos() != null && imageInfo.getTextInfos().getTextInfos() != null)
				{
					drawString(g2d, imageInfo.getTextInfos());
				}
				if (imageInfo.getArrowLineInfos() != null && imageInfo.getArrowLineInfos().getArrowLineInfos() != null)
				{
					drawLineArrowDirection1(g2d, imageInfo.getArrowLineInfos());
				}
				if (imageInfo.getCycleInfos() != null && imageInfo.getCycleInfos().getCycleInfos() != null)
				{
					drawCycle(g2d, imageInfo.getCycleInfos());
				}
				if (imageInfo.getDottedInfos() != null && imageInfo.getDottedInfos() != null)
				{
					drawDottedLine(g2d, imageInfo.getDottedInfos());
				}

			}
		}


		g2d.dispose();
	}


	private void drawDottedLine(Graphics2D g2d, DottedInfos dottedInfos)
	{


		g2d.setColor(dottedColor);
		g2d.setStroke(dottedStroke);
		DottedInfo line2D;
		for (int i = 0; i < dottedInfos.getDottedInfos().size(); i++)
		{
			line2D = dottedInfos.getDottedInfos().get(i);

			g2d.drawLine((int) line2D.getX1(), (int) line2D.getY1(), (int) line2D.getX2(), (int) line2D.getY2());
		}
	}

	private void drawString(Graphics2D g2d, TextInfos textInfos)
	{

		TextInfo textInfo;

		for (int i = 0; i < textInfos.getTextInfos().size(); i++)
		{
			textInfo = textInfos.getTextInfos().get(i);

			g2d.setColor(ImageInfo.getColorMap().get(textInfo.getColor()));
			g2d.setStroke(textStroke);
			g2d.setFont(textInfo.getDefaultFont());
			FontDesignMetrics metrics = FontDesignMetrics.getMetrics(textInfo.getDefaultFont());
			if (isText || !set.contains(textInfo.getText()))
			{

				g2d.drawString(textInfo.getText(), (int) textInfo.getX(), (int) (textInfo.getY() - metrics.getDescent()));

			}

		}

	}

	private void drawImage(Graphics2D g2d, ImageInfo imageInfo)
	{

		BufferedImage image = null;


		try
		{
			image = ImageIO.read(Style.class.getResource(imageInfo.getImageUrl()));
			MediaTracker mediaTracker = new MediaTracker(new Container());
			mediaTracker.addImage(image, 0);
			mediaTracker.waitForID(0);
		} catch (InterruptedException ex)
		{
			image = null;
			System.out.println("解析" + imageInfo.getImageUrl() + " 图片出错,出错原因:" + ex.getMessage());
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		if (image != null)
		{


			g2d.drawImage(image, (int) imageInfo.getX(), (int) imageInfo.getY(), (int) imageInfo.getWidth(), (int) imageInfo.getHeight(), imageColor, MyJFrame.getInstance());
		}

	}


	private void drawCycle(Graphics2D g2d, CycleInfos cycleInfos)
	{
		CycleInfo cycleInfo;
		g2d.setStroke(cycleStroke);
		for (int i = 0; i < cycleInfos.getCycleInfos().size(); i++)
		{

			cycleInfo = cycleInfos.getCycleInfos().get(i);
			g2d.setColor(ImageInfo.getColorMap().get(cycleInfo.getColor()));

			g2d.fillOval((int) (cycleInfo.getX()-cycleInfo.getR()), (int) (cycleInfo.getY()-cycleInfo.getR()), (int) (2 * cycleInfo.getR()), (int) (2 * cycleInfo.getR()));
//			g2d.fillOval(cycleInfo.getX() - cycleInfo.getR(), cycleInfo.getY() + cycleInfo.getR(), 2 * cycleInfo.getR(), 2 * cycleInfo.getR());


		}
	}

	private void drawRect(Graphics2D g2d, RectangleInfos rectangleInfos)
	{


		RectangleInfo rectangle;

		g2d.setStroke(recStroke);
		for (int i = 0; i < rectangleInfos.getRectangleInfos().size(); i++)
		{
			rectangle = rectangleInfos.getRectangleInfos().get(i);
			g2d.setColor(rectangle.getColor());

//					g2d.drawRect(rectangle.getX(), rectangle.getY(), rectangle.getWidth(), rectangle.getHeight());
			g2d.fillRect((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());


		}
	}


	private void drawLine(Graphics2D g2d, LineInfos lineInfos)
	{

		g2d.setColor(LineColor);
		g2d.setStroke(lineStroke);
		LineInfo line2D;
		for (int i = 0; i < lineInfos.getLineInfos().size(); i++)
		{
			line2D = lineInfos.getLineInfos().get(i);

			g2d.drawLine((int) line2D.getX1(), (int) line2D.getY1(), (int) line2D.getX2(), (int) line2D.getY2());


		}

	}

	/**
	 * 绘制线的“方向1”箭头
	 *
	 * @param g2d
	 */
	private void drawLineArrowDirection1(Graphics2D g2d, ArrowLineInfos arrowLineInfos)
	{
		Arrow.Attributes arrowAttributes = new Arrow.Attributes();

		g2d.setStroke(arrowStroke);
		for (int i = 0; i < arrowLineInfos.getArrowLineInfos().size(); i++)
		{
			ArrowLineInfo line2D = arrowLineInfos.getArrowLineInfos().get(i);
			g2d.setColor(ImageInfo.getColorMap().get(line2D.getColor()));

			Line2D.Double line = new Line2D.Double(line2D.getX1(), line2D.getY1(), line2D.getX2(), line2D.getY2());
			drawLine(g2d, line);

			drawArrow(g2d, arrowAttributes, line.getP1(), line.getP2());

		}

	}

	/**
	 * 绘制线的“方向2”箭头
	 *
	 * @param g2d
	 * @param arrowAttributes 箭头属性
	 * @param line2D          线
	 */
	private void drawLineArrowDirection2(Graphics2D g2d, Arrow.Attributes arrowAttributes, Line2D.Double line2D)
	{
		drawLine(g2d, line2D);
		drawArrow(g2d, arrowAttributes, line2D.getP2(), line2D.getP1());
	}

	/**
	 * 绘制线的“双向”箭头
	 *
	 * @param g2d
	 * @param arrowAttributes 箭头属性
	 * @param line2D          线
	 */
	private void drawLineArrowDirectionAll(Graphics2D g2d, Arrow.Attributes arrowAttributes, Line2D.Double line2D)
	{
		drawLine(g2d, line2D);
		drawArrow(g2d, arrowAttributes, line2D.getP1(), line2D.getP2());
		drawArrow(g2d, arrowAttributes, line2D.getP2(), line2D.getP1());
	}

	/**
	 * 绘制线
	 *
	 * @param g2d
	 * @param line2D
	 */
	private void drawLine(Graphics2D g2d, Line2D.Double line2D)
	{
//		g2d.setColor(Color.BLACK);
//		g2d.setStroke(lineStroke);
		g2d.draw(line2D);
	}

	/**
	 * 绘制箭头
	 *
	 * @param g2d
	 * @param arrowAttributes 箭头属性
	 * @param point1          线的第一个点
	 * @param point2          线的第二个点
	 */
	private void drawArrow(Graphics2D g2d, FigureJPanel.Arrow.Attributes arrowAttributes, Point2D point1, Point2D point2)
	{
		// 获取Arrow实例
		FigureJPanel.Arrow arrow = getArrow(arrowAttributes, point1, point2);

		// 构建GeneralPath
		GeneralPath arrow2D = new GeneralPath();
		arrow2D.moveTo(arrow.point1.x, arrow.point1.y);
		arrow2D.lineTo(arrow.point2.x, arrow.point2.y);
		arrow2D.lineTo(arrow.point3.x, arrow.point3.y);
		arrow2D.closePath();
		// 绘制
//		g2d.setColor(arrow.attributes.color);
		g2d.fill(arrow2D);
	}

	/**
	 * 获取箭头实体类
	 *
	 * @param arrowAttributes 箭头属性
	 * @param point1          线的第一个点
	 * @param point2          线的第二个点
	 * @return
	 */
	private FigureJPanel.Arrow getArrow(FigureJPanel.Arrow.Attributes arrowAttributes, Point2D point1, Point2D point2)
	{
		FigureJPanel.Arrow arrow = new FigureJPanel.Arrow(arrowAttributes);

		// 计算斜边
		double hypotenuse = arrow.attributes.height / Math.cos(Math.toRadians(arrow.attributes.angle / 2));

		// 计算当前线所在的象限
		int quadrant = -1;
		if (point1.getX() > point2.getX() && point1.getY() < point2.getY())
		{
			quadrant = 1;
		} else if (point1.getX() < point2.getX() && point1.getY() < point2.getY())
		{
			quadrant = 2;
		} else if (point1.getX() < point2.getX() && point1.getY() > point2.getY())
		{
			quadrant = 3;
		} else if (point1.getX() > point2.getX() && point1.getY() > point2.getY())
		{
			quadrant = 4;
		}

		// 计算线的夹角
		double linAngle = getLineAngle(point1.getX(), point1.getY(), point2.getX(), point2.getY());
		if (Double.isNaN(linAngle))
		{
			// 线与x轴垂直
			if (point1.getX() == point2.getX())
			{
				if (point1.getY() < point2.getY())
				{
					linAngle = 90;
				} else
				{
					linAngle = 270;
				}
				quadrant = 2;
			}
		}
		// 线与y轴垂直
		else if (linAngle == 0)
		{
			if (point1.getY() == point2.getY())
			{
				if (point1.getX() < point2.getX())
				{
					linAngle = 0;
				} else
				{
					linAngle = 180;
				}
				quadrant = 2;
			}
		}

		// 上侧一半箭头
		// 与x轴夹角
		double xAngle = linAngle - arrow.attributes.angle / 2;
		// 计算y方向增量
		double py0 = hypotenuse * Math.sin(Math.toRadians(xAngle));
		// 计算x方向增量
		double px0 = hypotenuse * Math.cos(Math.toRadians(xAngle));

		// 下侧一半箭头
		// 与y轴夹角
		double yAngle = 90 - linAngle - arrow.attributes.angle / 2;
		double px1 = hypotenuse * Math.sin(Math.toRadians(yAngle));
		double py1 = hypotenuse * Math.cos(Math.toRadians(yAngle));

		// 第一象限
		if (quadrant == 1)
		{
			px0 = -px0;
			px1 = -px1;

		} else if (quadrant == 2)
		{
			// do nothing
		} else if (quadrant == 3)
		{
			py0 = -py0;
			py1 = -py1;

		} else if (quadrant == 4)
		{
			py0 = -py0;
			px0 = -px0;

			px1 = -px1;
			py1 = -py1;
		}

		// build
		arrow.point1 = new Point2D.Double();
		arrow.point1.x = point1.getX();
		arrow.point1.y = point1.getY();

		arrow.point2 = new Point2D.Double();
		arrow.point2.x = point1.getX() + px0;
		arrow.point2.y = point1.getY() + py0;

		arrow.point3 = new Point2D.Double();
		arrow.point3.x = point1.getX() + px1;
		arrow.point3.y = point1.getY() + py1;

		return arrow;
	}

	/**
	 * 获取线与X轴的夹角
	 *
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	protected double getLineAngle(double x1, double y1, double x2, double y2)
	{
		double k1 = (y2 - y1) / (x2 - x1);
		double k2 = 0;
		return Math.abs(Math.toDegrees(Math.atan((k2 - k1) / (1 + k1 * k2))));
	}

	public ImageInfos getImageInfo()
	{
		return imageInfos;
	}

	public void reviseRanges()
	{

		if (imageInfos != null)
		{
			List<ImageInfo> imageInfoList = imageInfos.getImageInfos();
			if (imageInfoList != null)
			{
				int size = imageInfoList.size();
				ranges = new Range[size];

				for (int i = 0; i < imageInfoList.size(); i++)
				{
					ImageInfo imageInfo1 = imageInfoList.get(i);
					double width = imageInfo1.getWidth();
					double height = imageInfo1.getHeight();
					double originX = imageInfo1.getX();
					double originY = imageInfo1.getY();
					double endX = originX + width;
					double endY = originY + height;
					this.ranges[i] = new Range(originX, originY, endX, endY);

					if (imageInfo1.getLineInfos() != null)
					{
						lineIncrease(ranges[i], imageInfo1.getLineInfos());
					}
					if (imageInfo1.getCycleInfos() != null)
					{

						cycleIncrease(ranges[i], imageInfo1.getCycleInfos());
					}
					if (imageInfo1.getArrowLineInfos() != null)
					{

						arrowLineIncrease(ranges[i], imageInfo1.getArrowLineInfos());
					}
					if (imageInfo1.getTextInfos() != null)
					{

						textIncrease(ranges[i], imageInfo1.getTextInfos());
					}
					if (imageInfo1.getDottedInfos() != null)
					{

						dottedLineIncrease(ranges[i], imageInfo1.getDottedInfos());
					}
				}
			}
		}
	}

	private void dottedLineIncrease(Range range, DottedInfos dottedInfos)
	{
		if (dottedInfos.getDottedInfos() != null)
		{
			for (DottedInfo dottedInfo : dottedInfos.getDottedInfos())
			{
				double x1 = dottedInfo.getX1();
				double y1 = dottedInfo.getY1();
				double x2 = dottedInfo.getX2();
				double y2 = dottedInfo.getY2();
				increase(range, x1, y1);
				increase(range, x2, y2);

			}
		}
	}

	private void arrowLineIncrease(Range range, ArrowLineInfos arrowLineInfos)
	{
		if (arrowLineInfos.getArrowLineInfos() != null)
		{
			for (ArrowLineInfo arrowLineInfo : arrowLineInfos.getArrowLineInfos())
			{
				double x1 = arrowLineInfo.getX1();
				double y1 = arrowLineInfo.getY1();
				double x2 = arrowLineInfo.getX2();
				double y2 = arrowLineInfo.getY2();
				increase(range, x1, y1);
				increase(range, x2, y2);

			}
		}
	}

	private void textIncrease(Range range, TextInfos textInfos)
	{
		if (textInfos.getTextInfos() != null)
		{
			for (TextInfo textInfo : textInfos.getTextInfos())
			{
				double x1 = textInfo.getX();
				double x1_ = textInfo.getX() + textInfo.getWidth();
				double y1 = textInfo.getY();
				double y1_ = textInfo.getY() - textInfo.getHeight();
				increase(range, x1, y1);
				increase(range, x1_, y1_);

			}
		}
	}

	void increase(Range range, double x1, double y1)
	{
		if (x1 < range.originX)
		{
			range.originX = x1;
		}
		if (x1 > range.endX)
		{
			range.endX = x1;
		}
		if (y1 < range.originY)
		{
			range.originY = y1;
		}
		if (y1 > range.endY)
		{
			range.endY = y1;
		}
	}

	private void cycleIncrease(Range range, CycleInfos cycleInfos)
	{
		//空着吧
	}

	private void lineIncrease(Range range, LineInfos lineInfos)
	{
		if (lineInfos.getLineInfos() != null)
		{
			for (LineInfo lineInfo : lineInfos.getLineInfos())
			{
				double x1 = lineInfo.getX1();
				double y1 = lineInfo.getY1();
				double x2 = lineInfo.getX2();
				double y2 = lineInfo.getY2();
				increase(range, x1, y1);
				increase(range, x2, y2);

			}
		}
	}


	public void locRevise()
	{
		int n = ranges.length;
		double width;
		double height;

		width = jPanelWidth;
		height = jPanelheight;


		double gridWidth = width / n;
		for (int i = 0; i < n; i++)
		{
			ImageInfo imageInfo = imageInfos.getImageInfos().get(i);
			double startX = i * gridWidth;
			double ratio = ranges[i].getWidth() * 1.0 / ranges[i].getHeight();
			double new_width = Math.min(gridWidth, height * ratio);
			double new_height = new_width / ratio;
			double wRatio = new_width / ranges[i].getWidth();
			double hRatio = new_height / ranges[i].getHeight();

			double midX = (ranges[i].originX + ranges[i].endX) / 2;
			double midY = (ranges[i].originY + ranges[i].endY) / 2;
			double moveRight = gridWidth / 2 - midX;
			double moveDown = height / 2 - midY;
			revise(startX, gridWidth, height, moveRight, moveDown, wRatio, hRatio, imageInfo);
		}
	}

	private void revise(double startX, double gridWidth, double height, double moveRight, double moveDown, double wRatio, double hRatio, ImageInfo imageInfo)
	{

		double x0 = (gridWidth / 2 + (imageInfo.getX() - gridWidth / 2 + moveRight) * wRatio + startX + emptyWidth / 2);
		double y0 = (height / 2 + (imageInfo.getY() + moveDown - height / 2) * hRatio + emptyHeight / 2);
		double mw = (imageInfo.getWidth() * wRatio);
		double mh = (imageInfo.getHeight() * hRatio);
		imageInfo.setX(x0);
		imageInfo.setY(y0);
		imageInfo.setWidth(mw);
		imageInfo.setHeight(mh);
		if (recButtons != null)
		{
			for (int i = 0; i < recButtons.size(); i++)
			{
				RectangleInfo rectangleInfo = recButtons.get(i).getRectangleInfo();
				double x = (gridWidth / 2 + (rectangleInfo.getMidX() - gridWidth / 2 + moveRight) * wRatio + startX - rectangleInfo.getWidth() / 2.0) + emptyWidth / 2;
				double y = (height / 2 + (rectangleInfo.getMidY() + moveDown - height / 2) * hRatio - rectangleInfo.getHeight() / 2.0 + emptyHeight / 2);
				rectangleInfo.setX(x);
				rectangleInfo.setY(y);
				recButtons.get(i).update();


			}
		}
		if (imageInfo.getRectangleInfos() != null && imageInfo.getRectangleInfos().getRectangleInfos() != null)
		{
			for (int i = 0; i < imageInfo.getRectangleInfos().getRectangleInfos().size(); i++)
			{
				RectangleInfo rectangleInfo = imageInfo.getRectangleInfos().getRectangleInfos().get(i);
				double x = (gridWidth / 2 + (rectangleInfo.getMidX() - gridWidth / 2 + moveRight) * wRatio + startX - rectangleInfo.getWidth() / 2.0 + emptyWidth / 2);
				double y = (height / 2 + (rectangleInfo.getMidY() + moveDown - height / 2) * hRatio - rectangleInfo.getHeight() / 2.0 + emptyHeight / 2);
				rectangleInfo.setX(x);
				rectangleInfo.setY(y);
			}
		}

		if (imageInfo.getLineInfos() != null && imageInfo.getLineInfos().getLineInfos() != null)
		{
			List<LineInfo> lineInfoList = imageInfo.getLineInfos().getLineInfos();
			for (LineInfo lineInfo : lineInfoList)
			{
				double x1 = (gridWidth / 2 + (lineInfo.getX1() - gridWidth / 2 + moveRight) * wRatio + startX + emptyWidth / 2);
				double y1 = (height / 2 + (lineInfo.getY1() + moveDown - height / 2) * hRatio + emptyHeight / 2);
				double x2 = (gridWidth / 2 + (lineInfo.getX2() - gridWidth / 2 + moveRight) * wRatio + startX + emptyWidth / 2);
				double y2 = (height / 2 + (lineInfo.getY2() + moveDown - height / 2) * hRatio + emptyHeight / 2);
				lineInfo.setX1(x1);
				lineInfo.setX2(x2);
				lineInfo.setY1(y1);
				lineInfo.setY2(y2);
			}
		}
		if (imageInfo.getArrowLineInfos() != null && imageInfo.getArrowLineInfos().getArrowLineInfos() != null)
		{
			List<ArrowLineInfo> arrowLineInfos = imageInfo.getArrowLineInfos().getArrowLineInfos();
			for (ArrowLineInfo lineInfo : arrowLineInfos)
			{
				double x1 = (gridWidth / 2 + (lineInfo.getX1() - gridWidth / 2 + moveRight) * wRatio + startX + emptyWidth / 2);
				double y1 = (height / 2 + (lineInfo.getY1() + moveDown - height / 2) * hRatio + emptyHeight / 2);
				double x2 = (gridWidth / 2 + (lineInfo.getX2() - gridWidth / 2 + moveRight) * wRatio + startX + emptyWidth / 2);
				double y2 = (height / 2 + (lineInfo.getY2() + moveDown - height / 2) * hRatio + emptyHeight / 2);
				lineInfo.setX1(x1);
				lineInfo.setX2(x2);
				lineInfo.setY1(y1);
				lineInfo.setY2(y2);
			}
		}

		if (imageInfo.getDottedInfos() != null && imageInfo.getDottedInfos().getDottedInfos() != null)
		{
			List<DottedInfo> dottedInfos = imageInfo.getDottedInfos().getDottedInfos();
			for (DottedInfo lineInfo : dottedInfos)
			{
				double x1 = (gridWidth / 2 + (lineInfo.getX1() - gridWidth / 2 + moveRight) * wRatio + startX + emptyWidth / 2);
				double y1 = (height / 2 + (lineInfo.getY1() + moveDown - height / 2) * hRatio + emptyHeight / 2);
				double x2 = (gridWidth / 2 + (lineInfo.getX2() - gridWidth / 2 + moveRight) * wRatio + startX + emptyWidth / 2);
				double y2 = (height / 2 + (lineInfo.getY2() + moveDown - height / 2) * hRatio + emptyHeight / 2);
				lineInfo.setX1(x1);
				lineInfo.setX2(x2);
				lineInfo.setY1(y1);
				lineInfo.setY2(y2);
			}
		}

		if (imageInfo.getTextInfos() != null && imageInfo.getTextInfos().getTextInfos() != null)
		{
			List<TextInfo> textInfos = imageInfo.getTextInfos().getTextInfos();
			for (TextInfo textInfo : textInfos)
			{
				double x = (gridWidth / 2 + (textInfo.getMidX() - gridWidth / 2 + moveRight) * wRatio + startX - textInfo.getWidth() / 2.0 + emptyWidth / 2);
				double y = (height / 2 + (textInfo.getMidY() + moveDown - height / 2) * hRatio +textInfo.getHeight() / 2.0 + emptyHeight / 2);
//				double size = textInfo.getSize() * wRatio;
//				if (size > 25)
//				{
//					textInfo.setDefaultFont( 25);
//				} else if (size < 20)
//				{
//					textInfo.setDefaultFont(20);
//				} else
//				{
//
//
//				}
//				textInfo.setScale(size * 1.0 / ((int) size));
//				System.out.println(textInfo.getScale());
//
//				textInfo.setDefaultFont((int) size);
//				textInfo.setSize(size);

				textInfo.setX(x);
				textInfo.setY(y);
			}
		}

		if (imageInfo.getCycleInfos() != null && imageInfo.getCycleInfos().getCycleInfos() != null)
		{

			List<CycleInfo> cycleInfos = imageInfo.getCycleInfos().getCycleInfos();
			for (CycleInfo cycleInfo : cycleInfos)
			{
				double x = (gridWidth / 2 + (cycleInfo.getX() - gridWidth / 2 + moveRight) * wRatio + startX  + emptyWidth / 2);
				double y = (height / 2 + (cycleInfo.getY() + moveDown - height / 2) * hRatio + emptyHeight / 2);
				double r = cycleInfo.getR() * wRatio;
				cycleInfo.setX(x);
				cycleInfo.setY(y);
				cycleInfo.setR(r);
			}
		}


	}


	/**
	 * 箭头实体类
	 *
	 * @author xiangqian
	 * @date 16:06 2019/10/31
	 */
	public static class Arrow
	{
		public FigureJPanel.Arrow.Attributes attributes;
		public Point2D.Double point1;
		public Point2D.Double point2;
		public Point2D.Double point3;

		public Arrow(FigureJPanel.Arrow.Attributes attributes)
		{
			this.attributes = attributes;
		}

		/**
		 * 箭头属性
		 *
		 * @author xiangqian
		 * @date 15:41 2019/11/03
		 */
		public static class Attributes
		{
			double height; // 箭头的高度
			double angle; // 箭头角度
			Color color; // 箭头颜色

			public Attributes()
			{
				this.height = 10;
				this.angle = 30;
				this.color = Color.BLACK;
			}
		}
	}

//	class MyMouseListen implements MouseMotionListener, MouseListener
//	{
//		FigureJPanel figureJPanel;
//
//		private boolean isIN(RectangleInfo rectangleInfo, int x, int y)
//		{
//			if (rectangleInfo != null)
//			{
//				int x1 = rectangleInfo.getX();
//				int x2 = x1 + rectangleInfo.getWidth();
//				int y1 = rectangleInfo.getY();
//				int y2 = rectangleInfo.getHeight() + y1;
//				if ((x1 - x) * (x2 - x) <= 0 && (y1 - y) * (y2 - y) <= 0)
//				{
//					return true;
//				}
//				return false;
//			}
//			return false;
//		}
//
//		private void myMouseEvent(MouseEvent e, boolean tag)
//		{
//			int x = e.getX();
//			int y = e.getY();
//			int pageNum = MyJPanel.pageId;
//			int figureNum = CenterJPanel.nowId;
//			if (figureJPanel.nowRec != null && !isIN(figureJPanel.nowRec, x, y))
//			{
//				action3(figureJPanel.nowRec, figureJPanel);
//			}
//			if (figureJPanel.rectangleInfos != null)
//			{
//				List<RectangleInfo> rectangleInfoList = figureJPanel.rectangleInfos.getRectangleInfos();
//				RectangleInfo rectangleInfo;
//				if (rectangleInfoList != null)
//				{
//					for (int i = 0; i < rectangleInfoList.size(); i++)
//					{
//						rectangleInfo = rectangleInfoList.get(i);
//						if (isIN(rectangleInfo, x, y))
//						{
//							if (tag)
//							{
//								action1(rectangleInfo, pageNum);
//
//							} else
//							{
//								action2(rectangleInfo, figureJPanel);
//							}
//							break;
//						}
//					}
//				}
//
//			}
//
//		}
//
//		private void action3(RectangleInfo rectangleInfo, FigureJPanel figureJPanel)
//		{
//			rectangleInfo.setColor(FigureJPanel.rectColor);
//			figureJPanel.repaint(rectangleInfo.getRectangle());
//		}
//
//
//		private void action2(RectangleInfo rectangleInfo, FigureJPanel figureJPanel)
//		{
//			rectangleInfo.setColor(FigureJPanel.recMoveColor);
//			figureJPanel.repaint(rectangleInfo.getRectangle());
//			figureJPanel.nowRec = rectangleInfo;
//		}
//
//		private void action1(RectangleInfo rectangleInfo, int pageNum)
//		{
//			if (rectangleInfo.getId() >= 0)
//			{
//				MyJPanel.currentJPanel.idStack.add(CenterJPanel.nowId);
//			}
////			show(rectangleInfo.getId());
//			CenterJPanel.nowId = rectangleInfo.getId();
//		}
//
////		private void show(int id)
////		{
////			MyJPanel.currentJPanel.headCard.show(MyJPanel.currentJPanel.headJPanel, String.valueOf(id));
////			MyJPanel.currentJPanel.bodyCard.show(MyJPanel.currentJPanel.bodyJPanel, String.valueOf(id));
////			MyJPanel.currentJPanel.footCard.show(MyJPanel.currentJPanel.footJPanel, String.valueOf(id));
////		}
//
//		public MyMouseListen(FigureJPanel figureJPanel)
//		{
//			this.figureJPanel = figureJPanel;
//		}
//
//		@Override
//		public void mouseClicked(MouseEvent e)
//		{
//			myMouseEvent(e, true);
//
//		}
//
//		@Override
//		public void mousePressed(MouseEvent e)
//		{
//
//		}
//
//		@Override
//		public void mouseReleased(MouseEvent e)
//		{
//
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e)
//		{
//
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e)
//		{
//
//		}
//
//		@Override
//		public void mouseDragged(MouseEvent e)
//		{
//
//		}
//
//		@Override
//		public void mouseMoved(MouseEvent e)
//		{
//			myMouseEvent(e, false);
//
//		}
//	}
}


