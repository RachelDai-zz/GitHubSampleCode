package delivery;

import java.awt.*; 
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import resource.*;

 
 
 
public class ShowImage extends Frame { 
	private String temp = "./temp/";
	private String outputDir = temp + "OutputImage/";
	//private static String saveURL = null;
	
	public ShowImage(){
		File file1 = new File(outputDir);
    	if (!file1.isDirectory()) {
    		try {
    			file1.mkdirs();
    		} catch (Exception e) {
    			e.printStackTrace();
    			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    		}
    	}
	}
	
	
	public BufferedImage getBufferedImage(String fileName) {
		BufferedImage bf = null;
        try {
        	File f = new File(fileName);
        	bf = ImageIO.read(f);
        } catch (Exception e) {
        	e.printStackTrace();
        	JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
        return bf; 
    } 
	
	
	public BufferedImage getBufferedImageFromStream(String fileName){
		BufferedImage bf = null;
		InputStream is = this.getClass().getResourceAsStream(fileName);
		try {
			bf = ImageIO.read(is);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
		}
		return bf;
	}
	
/*	public  Image getImage(String imageFullPath) {  
	    InputStream inputStream = null;  
	    ByteArrayOutputStream outputStream = null;  
	    try {  
	        inputStream = this.getClass().getResourceAsStream(imageFullPath);  
	        outputStream = new ByteArrayOutputStream();  
	        byte buffer[] = new byte[1024];  
	        int len = 0;  
	        while ((len = inputStream.read(buffer)) != -1)  
	            outputStream.write(buffer, 0, len);  
	        return Toolkit.getDefaultToolkit().createImage(outputStream.toByteArray());  
	        
	    } catch (Throwable th) {  
	        th.printStackTrace();  
	        JOptionPane.showMessageDialog(null, th.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
	    }  
	    return null;  
	} */ 
	
/*	public void displayImage(String imageFullPath) {  
		 JPanel panel=new JPanel(new BorderLayout());  
		 JLabel label=new JLabel(new ImageIcon(imageFullPath));  
		 panel.add(label,BorderLayout.CENTER);  
	     this.setSize(800, 800);  
	     //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	     this.set错误("显示图像");  
	     this.setVisible(true);  
	     
	}*/

	
    
    public BufferedImage mergeImage(List<BufferedImage> list) throws IOException { 	
    	int w3 = 0;
    	int h3 = 0 ;
    	
    	int[] ImageArray3 = null;
    	//address
    	int w0 = list.get(0).getWidth();
    	//reference info
    	int w1 = list.get(1).getWidth();
    	//product image
    	int w2 = list.get(2).getWidth();
    	if (list.get(3) != null) {
    		w3 = list.get(3).getWidth();
    	}
    	//business card
    	int w4 = list.get(4).getWidth(); 
    	
    	int h0 = list.get(0).getHeight();
    	int h1 = list.get(1).getHeight();
    	int h2 = list.get(2).getHeight();
    	if (list.get(3) != null) {
    		h3 = list.get(3).getHeight();
    	}
    	int h4 = list.get(4).getHeight();
    	
    	int[] ImageArray0 = new int[w0 * h0];
    	int[] ImageArray1 = new int[w1 * h1];
    	int[] ImageArray2 = new int[w2 * h2];
    	if (list.get(3) != null) {
    		ImageArray3 = new int[w3 * h3];
    	}
    	int[] ImageArray4 = new int[w4 * h4];
    	BufferedImage DestImage  = null;
    	DestImage = new BufferedImage(w0, h0+h1+h2+h3+h4, BufferedImage.TYPE_INT_RGB);
    	//DestImage = new BufferedImage(w0, h0+h1+h2+h3+h4, BufferedImage.TYPE_INT_BGR);
    	try {
	    	ImageArray0 = list.get(0).getRGB(0, 0, w0, h0, ImageArray0, 0, w0);
	    	ImageArray1 = list.get(1).getRGB(0, 0, w1, h1, ImageArray1, 0, w1);
	    	ImageArray2 = list.get(2).getRGB(0, 0, w2, h2, ImageArray2, 0, w2);
	    	if (list.get(3) != null) {
	    		ImageArray3 = list.get(3).getRGB(0, 0, w3, h3, ImageArray3, 0, w3);
	    	}
	    	ImageArray4 = list.get(4).getRGB(0, 0, w4, h4, ImageArray4, 0, w4);
	    	
	    	DestImage.setRGB(0, 0, w0, h0, ImageArray0, 0, w0);	
	    	DestImage.setRGB(0, h0, w1, h1, ImageArray1, 0, w1);	
	    	if (w0 < w2) {
	    		DestImage.setRGB(0, h1+h0, w0, h2, ImageArray2, 0, w2);
	    	} else {
	    		DestImage.setRGB(0, h1+h0, w2, h2, ImageArray2, 0, w2);
	    	}
	    	if (list.get(3) != null) {
	    		DestImage.setRGB(0, h2+h1+h0, w3, h3, ImageArray3, 0, w3);
	    		DestImage.setRGB(0, h3+h2+h1+h0, w3, h4, ImageArray4, 0, w4);
	    	} else {
	    		DestImage.setRGB(0, h2+h1+h0, w4, h4, ImageArray4, 0, w4);
	    	}
	    } catch (Exception e) {
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    	}
        return DestImage; 
    } 

    public boolean saveImage(BufferedImage savedImg, String saveDir, 
            String fileName) { 
        boolean flag = false; 

        String saveURL = saveDir + fileName; 
        File file = new File(saveURL); 
        try { 
            flag = ImageIO.write(savedImg, "jpg", file); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        } 
        return flag; 
    } 
    
    public void cleanUpFiles(String path){
    	File f = new File(path);
    	try {
	    	 if (f.exists()) {
	    		 boolean b = f.delete();
	             if (!b) {
	                 System.out.println("Delete failed");
	             } else {
	                 System.out.println(f.getName()+" was deleted successfully");
	             }
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    	}

    }
     
 
    public void paintString (String[] info, String cat) { 
    	BufferedImage image = new BufferedImage(800, 200, BufferedImage.TYPE_INT_RGB);  
    	Graphics graphics = image.getGraphics(); 
    	String Information = " ";
    	String Information1 = " ";
    	String Information2 = " ";
    	
    	String MoreInfo = " ";
    	String[] strList = null;
    	int lenInfo = 0;
    	switch (cat){
    		case "Reference":
    			String Size = "尺寸:  " + info[0] + "    ";
    	    	String Color = "颜色:  " + info[1] + "     ";
    	    	String Price = "拿货单价:  " + info[2] + "     ";
    	    	String Quantity = "数量: " + info[4] +  "     ";
    	    	String Ref = "";
    	    	
    	    	if (!info[5].equals("") && !info[5].equals(null)) {
    	    		Ref = "货号：" + info[5];
    	    	}
    	    	
    	    	Information2 = "拿货信息：";
    	    	Information = Size + Color  + Quantity + Price + Ref;
    			break;
    		case "Address":
    			strList = info[3].split("，");
    			lenInfo = strList.length;
    			String Contact = " ";
    			String Address = " ";
    			int i = 0;
    			while (i < lenInfo-1) {
    				if (strList[i] == "") {
    					strList[i] = " ";
    				}
    				i++;
    			}
    			if (lenInfo == 1) {
    				Contact = strList[0].toString() + " ";
    			}
    			
    			if (lenInfo == 2) {
    				Contact = strList[0].toString() + " " + strList[1].toString();
    			}
    			
    			if (lenInfo == 3 ) {
    				Contact = strList[0].toString() + " " + strList[1].toString();
    				Address = strList[2].toString();
    			}
    			if (lenInfo == 4) {
    				Contact = strList[0].toString() + " " + strList[1].toString();
    				Address = strList[2].toString() + " " + strList[3].toString();
    			}
    			if (lenInfo == 5) {
    				Contact = strList[0].toString() + " " + strList[1].toString();
    				Address = strList[2].toString() + " " + strList[3].toString();
    				MoreInfo = strList[4].toString();
    			}
    			if (lenInfo == 6) {
    				Contact = strList[0].toString() + " " + strList[1].toString();
    				Address = strList[2].toString() + " " + strList[3].toString();
    				MoreInfo = strList[4].toString()+ " " + strList[5].toString();
    			}
    			Information = Contact;
    			Information1 = Address;
    			Information2 = "送货地址：";
    			break;
    	}
    	try {
	    	Font font = new Font("Dialog",1,22);
	    	graphics.setFont(font);
	    	graphics.drawString(Information2, 0, 30);
	    	graphics.drawString(Information, 0, 60);
	    	graphics.drawString(Information1, 0, 90);
	    	graphics.drawString(MoreInfo, 0, 120);
	    	//graphics.dispose();
	    	ImageIO.write(image, "jpg", new File(outputDir + cat + ".jpg"));
	    	graphics.dispose();
    	} catch (Exception e) {
    		e.printStackTrace();
    		JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    	}
    } 
    

 
    
    
    public static void main(String[] args) { 
    	ShowImage si = new ShowImage();
         
		//si.displayImage("C:\\Users\\rudai\\Desktop\\TBD\\Delivery\\Result61.jpg");
        

    } 
    
}
