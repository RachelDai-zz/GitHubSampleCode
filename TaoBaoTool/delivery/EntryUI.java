package delivery;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.omg.CORBA.portable.InputStream;

import resource.BusinessCard.*;
import resource.OutputImage.*;
import resource.UploadedImage.*;



public class EntryUI extends Frame{
	
	private JComboBox txtName;
	private JComboBox txtSize;
	private JTextField txtColor;
	private JTextField txtRef;
	private JTextField txtSellPrice;
	private JTextField txtSellTran;
	private JTextField txtPrice;
	private JTextField txtAddress;
	private JTextField txtPicPath;
	private JFrame fr = new JFrame();
	private JLabel lblWarning = new JLabel("必填项未填或者生成图片路径不对或者路径没有权限");
	private JTextField txtQuantity;
	private ShowImage si = new ShowImage();
	private JTextField txtPicPathTo;
	private String temp = "./temp/";
	private String outputPath = temp+"OutputImage/";
	private String uploadedPath = temp+"UploadedImage/";
	private String businessCardPath = "/resource/BusinessCard/";
	private String fileUploadedProduct1 = uploadedPath + "product1.jpg";
	private String fileAddress = outputPath + "Address.jpg";
	private String fileReference = outputPath + "Reference.jpg";
	private String path3 = null;
	
	
	
	public EntryUI() {
		File file1 = new File(uploadedPath);
    	if (!file1.isDirectory()) {
    		try {
    			file1.mkdirs();
    		} catch (Exception e1) {
    			e1.printStackTrace();
    			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    		}
    	}
    	
    	File file2 = new File(outputPath);
    	if (!file2.isDirectory()) {
    		try {
    			file2.mkdirs();
    		} catch (Exception e2) {
    			e2.printStackTrace();
    			JOptionPane.showMessageDialog(null, e2.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
    		}
    	}
	}
	
	
	public void mainFreamWindow() {
		Panel p = new Panel();
		
		fr.setLayout(null);
		fr.setTitle("发货管理 (*为必填项)");
		fr.setBounds(300, 100, 650, 500);
		fr.setResizable(false);
	
		
		//客户名片
		JLabel lblName = new JLabel("*厂家");
		fr.add(lblName);
		lblName.setBounds(50,20,80,25);
		txtName = new JComboBox();
		txtName.addItem("请选择");
		txtName.addItem("ALF");
		txtName.addItem("JNH");
		txtName.addItem("MSXG");
		txtName.addItem("QML");
		txtName.addItem("SJML");
		txtName.addItem("SP");
		txtName.addItem("WZX");
		txtName.addItem("XL");
		txtName.addItem("XW");
		txtName.addItem("YLL");
		txtName.addItem("ZS4024");
		txtName.addItem("ZS4937");
		txtName.addItem("ZS4113");
		txtName.addItem("QT");
		txtName.addItem("ZS4082");
		txtName.addItem("ZS4078");
		txtName.addItem("YZL");
		txtName.addItem("XLK");
		txtName.addItem("XR");
		fr.add(txtName);
		txtName.setBounds(110,20,150,25);
		
		//Size
		JLabel lblSize = new JLabel("*尺寸");
		fr.add(lblSize);
		lblSize.setBounds(50,60,80,25);
		txtSize = new JComboBox();
		txtSize.addItem("请选择");
		txtSize.addItem("L");
		txtSize.addItem("XL");
		txtSize.addItem("2XL");
		txtSize.addItem("3XL");
		txtSize.addItem("4XL");
		txtSize.addItem("5XL");
		fr.add(txtSize);
		txtSize.setBounds(110,60,150,25);
		
		//Date
		Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd");
        String date = DATE_FORMAT.format(today);
        
		JLabel lblDate = new JLabel(date);
		fr.add(lblDate);
		lblDate.setBounds(500,10,120,50);
		Font font = new Font("Dialog",1,20);
		lblDate.setFont(font);
		
		
		//颜色
		JLabel lblColor = new JLabel("*颜色");
		fr.add(lblColor);
		lblColor.setBounds(50,100,80,25);
		txtColor = new JTextField();
		fr.add(txtColor);
		txtColor.setBounds(110,100,150,25);
		
		
		//货号
		JLabel lblRef = new JLabel("货号");
		fr.add(lblRef);
		lblRef.setBounds(400,100,80,25);
		txtRef = new JTextField();
		fr.add(txtRef);
		txtRef.setBounds(460,100,150,25);
		
		//卖出单价
		JLabel lblSellPrice = new JLabel("卖出单价");
		fr.add(lblSellPrice);
		lblSellPrice.setBounds(400,140,80,25);
		txtSellPrice = new JTextField();
		fr.add(txtSellPrice);
		txtSellPrice.setBounds(460,140,150,25);
		
		//卖出运费
		JLabel lblSellTran = new JLabel("卖出运费");
		fr.add(lblSellTran);
		lblSellTran.setBounds(400,180,80,25);
		txtSellTran = new JTextField();
		fr.add(txtSellTran);
		txtSellTran.setBounds(460,180,150,25);

		//数量
		JLabel lblQuantity = new JLabel("*数量");
		fr.add(lblQuantity);
		lblQuantity.setBounds(50,140,80,25);
		txtQuantity = new JTextField();
		fr.add(txtQuantity);
		txtQuantity.setBounds(110,140,150,25);
		
		//价格
		JLabel lblPrice = new JLabel("*拿货价格");
		fr.add(lblPrice);
		lblPrice.setBounds(50,180,80,25);
		txtPrice = new JTextField();
		fr.add(txtPrice);
		txtPrice.setBounds(110,180,150,25);
		
		//地址
		JLabel lblAddress = new JLabel("*送货地址");
		fr.add(lblAddress);
		lblAddress.setBounds(50,220,80,25);
		txtAddress = new JTextField();
		fr.add(txtAddress);
		txtAddress.setBounds(110,220,500,25);
		
		//图片上传
		JLabel lblPic = new JLabel("*产品图片");
		fr.add(lblPic);
		lblPic.setBounds(50,260,80,25);
		JLabel lblDes1 = new JLabel("只支持jpg和png格式");
		fr.add(lblDes1);
		lblDes1.setBounds(510,240,120,50);
		JLabel lblDes2 = new JLabel("背景为白色的图片");
		fr.add(lblDes2);
		lblDes2.setBounds(510,260,120,50);
		txtPicPath = new JTextField();
		fr.add(txtPicPath);
		txtPicPath.setBounds(110,260,300,25);
		txtPicPath.setEditable(false);
		JButton picPathBtn = new JButton("浏览");
		fr.add(picPathBtn);
		picPathBtn.setBounds(420,260,80,25);
		picPathBtn.addActionListener(new ActionListener(){   
            public void actionPerformed(ActionEvent arg0) {
            	//Each time trigger this button, set label invisible first
            	lblWarning.setVisible(false);
            	
            	
            	//初始化文件选择框
            	JFileChooser fDialog = new JFileChooser();
            	//设置文件选择框的标题 
            	fDialog.setDialogTitle("请选择要上传的图片");
            	//弹出选择框
            	int returnVal = fDialog.showOpenDialog(null);
            	// 如果是选择了文件
            	if(JFileChooser.APPROVE_OPTION == returnVal){       
            		//把路径值 写到 textField 中	
            		txtPicPath.setText(fDialog.getSelectedFile().toString());
            	}
            	String path = txtPicPath.getText().toString();
            	//Limit this field to jpg and png and 
            	//Force this field must be filled 
            	if (!path.isEmpty() && !path.equals("") && !path.equals(null) 
            			&& (path.endsWith("jpg") || path.endsWith("png"))) {
            		
                   	File upload = new File(path);
                   	
	            	try{ 
		            	FileOutputStream fos1 = new FileOutputStream(fileUploadedProduct1); 
		               	FileInputStream fis=new FileInputStream(upload);  
		            	byte[] buffer=new byte[1024];  
		            	int len=0;  
		            	while((len=fis.read(buffer))>0){  
		            		fos1.write(buffer,0,len);  
		            		
		            	}  
		            	fos1.close();  
		            	fis.close();
	            } catch (Exception e) {  
	            	e.printStackTrace(); 
	            	JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
	            }  
            } 
            else {
            	//For warning msg
            	//lblWarning = new JLabel("上传图片的格式或者路径不对");
            	fr.add(lblWarning);
        		//Font font = new Font("Dialog",1,16);
        		//lblWarning.setFont(font);
        		lblWarning.setVisible(true);
        		//lblWarning.setBounds(150,400,400,50);
            }
            }    
        });   
		
		
		//确定按钮
		JButton okBtn = new JButton("确定");
		fr.add(okBtn);
		okBtn.setBounds(150,350,80,25);
		okBtn.addActionListener(new ActionListener(){   
            public void actionPerformed(ActionEvent arg0) {   
              //TODO	
            	//Each time trigger this button, set label invisible first
            	lblWarning.setVisible(false);
            	
               	String businessCardName = txtName.getSelectedItem().toString();
            	String fileBusinessCard = businessCardPath + businessCardName + ".jpeg";
            	String size = txtSize.getSelectedItem().toString();
            	String color = txtColor.getText().toString();
            	String price = txtPrice.getText().toString();
            	String address = txtAddress.getText().toString();
            	String picPath = txtPicPath.getText().toString();
            	String quantity = txtQuantity.getText().toString();
            	String resultPath = txtPicPathTo.getText().toString();
            	String ref = txtRef.getText().toString();
            	
            	List <BufferedImage> list = new ArrayList<BufferedImage> ();
            	BufferedImage DestImage = null;
            	BufferedImage bi0 = null;
            	BufferedImage bi1 = null;
            	BufferedImage bi2 = null;
            	BufferedImage bi3 = null;
            	BufferedImage bi4 = null;
	            
            	//For final generated image
            	File file = new File(resultPath);
	            if (!businessCardName.equals("请选择")  && !size.equals("请选择") && !color.equals("") && !price.equals("") 
	            		&& !address.equals("") && !picPath.equals("") && !quantity.equals("") && !resultPath.equals("") 
	            		&& file.isDirectory() && file.canWrite()) {
		
                	if ((!resultPath.endsWith("\\")) && (!resultPath.endsWith("/"))) {
                		resultPath = resultPath + "/";
                	}
            		 
            		
	            	String[] list1 = {size, color, price, address, quantity, ref};
	            	si.paintString(list1, "Reference");
	            	si.paintString(list1, "Address");
	            	
	                try { 
	                	bi0 = si.getBufferedImageFromStream(fileBusinessCard);
	                	bi1 = si.getBufferedImage(fileReference);
	                	//For uploaded the image of product
	                	bi2 = si.getBufferedImage(fileUploadedProduct1);
	                	if (path3 != null) {
	                		bi3 = si.getBufferedImage(path3);
	                	}
	                	bi4 = si.getBufferedImage(fileAddress);
	                	
	                } catch (Exception e) { 
	                    e.printStackTrace(); 
	                    JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
	                } 
	                
	                list.add(bi4);
	                list.add(bi1);
	                list.add(bi2);
	                list.add(bi3);
	                list.add(bi0);
	                //list.add(bi5);
	
	
	                try { 
	                	DestImage = si.mergeImage(list); 
	                	//new ShowImage((Image)destImg);
	                } catch (IOException e) { 
	                    e.printStackTrace(); 
	                } 
	                
	                
	                //Save the result image
	                Random random = new Random();
	                int ran = random.nextInt(10000);
	                String fileResult = "Result" + ran + ".jpg";
	                si.saveImage(DestImage, resultPath, fileResult); 
	                DestImage.flush();
	                JOptionPane.showMessageDialog(null, "图像已生成！", "消息", JOptionPane.INFORMATION_MESSAGE);
	                
	            }   else {
	            	//For warning msg
	            	//lblWarning = new JLabel("必填项未填或者生成图片路径不对或者路径没有权限");
	        		fr.add(lblWarning);
	        		//Font font = new Font("Dialog",1,18);
	        		//lblWarning.setFont(font);
	        		lblWarning.setVisible(true);
	        		//lblWarning.setBounds(150,400,400,50);
	            }
            }
		});
               
		
		//重置按钮
		JButton resetBtn = new JButton("重置");
		fr.add(resetBtn);
		resetBtn.setBounds(250,350,80,25);
		resetBtn.addActionListener(new ActionListener(){   
            public void actionPerformed(ActionEvent arg0) {   
            	txtName.setSelectedIndex(0);
            	txtSize.setSelectedIndex(0);
            	txtColor.setText("");
            	txtPrice.setText("");
            	txtAddress.setText("");
            	txtPicPath.setText("");
            	txtQuantity.setText("");
            	txtPicPathTo.setText("");
            	
            	//For clean up files
            	si.cleanUpFiles(fileAddress);
            	si.cleanUpFiles(fileReference);
            	si.cleanUpFiles(fileUploadedProduct1);
            	
            	//For warning msg
        		//fr.add(lblWarning);
        		lblWarning.setVisible(false);
        		lblWarning.setBounds(150,400,400,50);
            }    
        });  
		
		
	
		//For close window
		fr.addWindowListener(new java.awt.event.WindowAdapter() { 
			public void windowClosing(java.awt.event.WindowEvent e) { 
				si.cleanUpFiles(fileAddress);
				si.cleanUpFiles(fileReference);
				si.cleanUpFiles(fileUploadedProduct1);
				//si.cleanUpFiles(fileResultImage);
				
				File file1 = new File(temp);
            	if (file1.isDirectory()) {
            		try {
            			file1.delete();
            		} catch (Exception e1) {
            			e1.printStackTrace();
            			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            		}
            	}
				System.exit(0); 
			} 
		}); 
		
		
		//图片生成到
		JLabel lblPicTo = new JLabel("*图片生成到");
		fr.add(lblPicTo);
		lblPicTo.setBounds(35,300,80,25);
		txtPicPathTo = new JTextField();
		fr.add(txtPicPathTo);
		txtPicPathTo.setBounds(110,300,300,25);
		JLabel lblPicToRes = new JLabel("不要选C盘");
		fr.add(lblPicToRes);
		lblPicToRes.setBounds(420,300,80,25);
		lblPicToRes.setVisible(true);
		
    			
		fr.setVisible(true);
		
		//For lblWarning 
		Font ft = new Font("Dialog",1,14);
		lblWarning.setFont(ft);
		lblWarning.setVisible(false);
		lblWarning.setBounds(100,400,400,50);
	}
	
	    
    public static void main(String[] args) { 
    	EntryUI  frame = new EntryUI();
    	frame.mainFreamWindow();   
	
    }   
}
