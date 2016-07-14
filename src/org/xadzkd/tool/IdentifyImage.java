package org.xadzkd.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class IdentifyImage {
	private static final long serialVersionUID = 1L;
    public static final char[] CHARS ={
    		'2','3','4','5','6','7','8','9',
    		'A','B','C','D','E','F','G','H','J',
    		'k','L','M','N','P','Q','L','S','T',
    		'U','V','W','X','Y','Z'
    };
   
    public static Random random ;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public final static int length = 4;
    
    public static String getRandomString(){
    	random = new Random(new Date().getTime());
    	StringBuffer buffer = new StringBuffer();
    	for(int i = 0; i < length;i++){
    		buffer.append(CHARS[random.nextInt(CHARS.length)]);
    	}
    	return buffer.toString();
    }
    
    public static Color getRandomColor(){
    	return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
    	
    }
    public static Color getReverseColor(Color c){
    	return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
    }
    private static String addSpace(String randomString){
    	String tmp = "";
    	 for(int i=0; i < randomString.length() - 1; i++){
    		 tmp += randomString.charAt(i);
    		 tmp += " ";
    	 }
    	 tmp += randomString.charAt(randomString.length() - 1);
    	 return tmp;
    }
    public static BufferedImage IdentifyImage(HttpServletRequest request){
    	int width = 100;
		int height = 30;
		String randomString = getRandomString();
		String orginString = randomString;
		randomString = addSpace(randomString);
		Color color = getRandomColor();
		Color reverse = getReverseColor(color);
		
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
		g.setColor(reverse);
		g.drawString(randomString, 18, 20);
		for(int i=0, n = random.nextInt(100); i < n; i++){
			g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
		}
		request.getSession().setAttribute("Identify", orginString);
		return bi;
    }
}
