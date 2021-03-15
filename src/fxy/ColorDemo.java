package fxy;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ColorDemo extends JFrame {
    public ColorDemo() {
        setTitle("绘制几何图形");
        setBounds(100, 200, 500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        Container c = getContentPane();
        MyCanvas canvas = new MyCanvas();
        c.add(canvas);
    }

    private class MyCanvas extends Canvas {
        @Override
        public void paint(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;//新绘图类

            Image img = new ImageIcon("src/a.png").getImage();//获取图片来源
            g2.drawImage(img, 50, 0, this);

            java.awt.Color c = java.awt.Color.RED;//设置颜色属性
            Stroke stroke = new BasicStroke(8);//设置画笔属性
            Font font = new Font("隶书", Font.BOLD, 16);
            g2.setFont(font);//加载字体
            g2.drawString("你好", 20, 30);//

//			g2.setStroke(stroke);//加载画笔

            /*BasicStroke.CAP_ROUND : 圆角末端
             *BasicStroke.CAP_BUTT :无修饰末端
             *BasicStroke.CAP_SQUARE :正方形末端
             *
             *BasicStroke.JOIN_BEVEL :平角
             *BasicStroke.JOIN_MITER :尖角（默认）
             *BasicStroke.JOIN_ROUND :圆角
             * */
            stroke = new BasicStroke(12, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_ROUND);
            g2.setStroke(stroke);//加载画笔


//			g2.setColor(c);
            g2.setColor(java.awt.Color.BLUE);
            g2.drawOval(60, 20, 30, 30);// 画了一个圆
            g2.setColor(c);
            g2.fillRect(60, 20, 20, 20);// 画矩形
            g2.setColor(java.awt.Color.BLUE);

            Shape shape1 = new Rectangle2D.Double(110, 5, 100, 100);// 矩形圆形对象
            g2.fill(shape1);// 画这个图形

            Shape shape2 = new Rectangle2D.Double(220, 15, 80, 80);// 矩形圆形对象
            g2.fill(shape2);// 画这个图形

            g2.drawArc(320, 25, 100, 50, 270, 200);// 弧形

            g2.drawLine(5, 120, 100, 120);//横线
            g2.drawLine(50, 120, 50, 200);//垂直线

            g2.drawRoundRect(120, 120, 100, 50, 10, 10);// 圆角矩形

            int x[] = { 250, 300, 250, 300 };
            int y[] = { 130, 130, 200, 200 };
//			g2.drawPolygon(x, y, 4);// 多边形

            g2.drawPolyline(x, y, 4);// 多边线


        }
    }

    public static void main(String args[]) {
        new ColorDemo();

    }
}

