package com.fangcheng.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;


public class FCFileUtils extends FileUtils{

	/**
     * 将文本文件中的内容读入到buffer中
     * @param buffer buffer
     * @param filePath 文件路径
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();

    }

    /**
     * 读取文本文件内容
     * @param filePath 文件所在路径
     * @return 文本内容
     * @throws IOException 异常
     * @author cn.outofmemory
     * @date 2013-1-7
     */
    public static String readFile(String filePath) throws IOException {
        StringBuffer sb = new StringBuffer();
        FCFileUtils.readToBuffer(sb, filePath);
        return sb.toString();
    }
    
    public static ArrayList<String> splitHql(String str){
    	ArrayList<String> hqlArrayList=new ArrayList<>();
    	String[] line=str.split(";");
    	int lineN=line.length;
    	if(line[line.length-1].trim().equals("")) lineN=lineN-1;

        for(int i=0;i<lineN;i++){
        	if(!line[i].trim().substring(0, 1).equals("#"))
        		hqlArrayList.add(line[i]);       	  
        }
		return hqlArrayList;   	
    }
    public static void main(String[] args) throws IOException{
    	String filePath="E:\\workspace_2015\\fc-hive-report\\config\\hive_conf\\dp_page_hql";
    	String ret=FCFileUtils.readFile(filePath);  
        FCFileUtils.splitHql(ret);
        
    }
}
