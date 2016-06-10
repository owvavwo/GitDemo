package org.dragon.hadoop.utils;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.Test;


public class HDFSUtils {

	
	public static FileSystem getFileSystem() {
		
		FileSystem  hdfs =null;
		try{
			//��ȡ�����ļ���Ϣ
			Configuration conf = new Configuration();
			//��ȡ�ļ�ϵͳ
			hdfs = FileSystem.get(conf);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return hdfs;
		
	}
	 
}
