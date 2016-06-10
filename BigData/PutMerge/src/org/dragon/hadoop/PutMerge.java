package org.dragon.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/*
 *����HDFS�ϴ��ļ� ͬʱ���кϲ� 
 * 
*/
public class PutMerge {
	//�����ϴ��ļ������ļ��ϲ� 
	public static void put(String localDir, String HDFSFile) {
		Configuration conf = new Configuration();
		Path localPath = new Path(localDir);
		Path HDFSPath = new Path(HDFSFile);
		try {
			FileSystem localFs = FileSystem.getLocal(conf);  //��ȡ�����ļ�ϵͳ			
			FileSystem hdfs = FileSystem.get(conf);			//��ȡ�����ļ�ϵͳ�е������ļ�
			FileStatus[] statuses = localFs.listStatus(localPath);
			FSDataOutputStream fsos = hdfs.create(HDFSPath);     //��HDFS�ϵ������
			
			for(FileStatus status : statuses) {			
				Path path = status.getPath();                  //��ȡ�ļ�·��
				System.out.println("�ļ�Ϊ��" + path.getName());				
				FSDataInputStream fsis = localFs.open(path);    //���ļ�������
				byte[] buffer = new byte[1024];        //��ʼ���Ķ�д����
				int len = 0;
				while((len = fsis.read(buffer)) > 0) {
					fsos.write(buffer, 0, len);
				}
				fsis.close();                         
			}
			fsos.close();							//�����ļ�������ɺ󣬹ر�HDFS�������
		}catch ( Exception e) {
			e.printStackTrace();
		}
	} 
	
	public static void main(String[] args) {
		String localDir = "d:/merge";
		String hdfsFile = "/hdfs/merge.data";
		put(localDir, hdfsFile);
	}
}
