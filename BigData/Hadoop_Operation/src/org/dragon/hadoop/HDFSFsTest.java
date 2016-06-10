package org.dragon.hadoop;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;
import org.dragon.hadoop.utils.HDFSUtils;
import org.junit.BeforeClass;
import org.junit.Test;
/*
 *HDFS JAVA API ��Ҫ�����ļ���������
 * */
public class HDFSFsTest {
	 private static FileSystem hdfs;
	 @BeforeClass
	 public static void init() {
		 hdfs = HDFSUtils.getFileSystem();
	 }
	//��ȡ�ļ�����
	@Test
	public void testReadFile() throws IOException { 
		Path path = new Path("/hdfs/data/02.data");
		//���ļ�������
		FSDataInputStream inStream = hdfs.open(path);
		//��ȡ�ļ�������̨��ʾ
		IOUtils.copyBytes(inStream, System.out,4096, false);
		//�ر���
		IOUtils.closeStream(inStream); 
		
	}
	
	//�鿴Ŀ¼
	@Test
	public void testList() 	throws Exception { 
		Path path = new Path("/hdfs");
		FileStatus[] fileStatus = hdfs.listStatus(path);
		for(FileStatus fs : fileStatus) {
			Path p = fs.getPath();
			String info = fs.isDir()?"Ŀ¼":"�ļ�";
			System.out.println(info + ":" + p);
		}
	}
	
		//����Ŀ¼
		@Test
		public void testCreateDirectory() throws IOException { 
			Path path = new Path("/hdfs/data/dir");
			boolean isSuccess = hdfs.mkdirs(path);
			String info = isSuccess ? "�ɹ�":"ʧ��";
			System.out.println("����Ŀ¼" + path + info);
		}
		
		//�ϴ��ļ�
		@Test
		public void testPut() throws IOException {
			//�����ļ�
			Path srcPath = new Path("d:/SQL/bbs.sql");
			//HDFS�ϴ�·��
			Path dstPath = new Path("/hdfs/data");
			hdfs.copyFromLocalFile(srcPath, dstPath);
			
		}
		
		//����HDFS�ļ���д������
		@Test
		public void testCreateFile() throws IOException {
			Path path = new Path("/hdfs/data/02.data");
			FSDataOutputStream fs = hdfs.create(path);
			//ͨ�������д������
			fs.writeUTF("hello this is a test from windows");
			fs.close();
		}
		
		//��HDFS ���ļ�����������
		@Test
		public void testRename() throws IOException {
			Path srcPath = new Path("/hdfs/data/02.data");
			Path dstPath = new Path("/hdfs/data/02.data.bak");
			boolean flag = hdfs.rename(srcPath, dstPath);
			System.out.println(flag);
		}
		
		//ɾ���ļ�
		@Test
		public void testDeleteFile() throws IOException {
			Path dstPath = new Path("/hdfs/data/02.data");
			boolean flag = false;
			//�ڶ��������趨Ϊtrueʱ��ʹ�õݹ�ɾ��Ŀ¼����Ŀ¼�������ļ�
			flag = hdfs.delete(dstPath,false);
			System.out.println(flag);
		}
		
		//����ĳ���ļ��ڼ�Ⱥ��λ��
		@Test
		public void testLocation() throws IOException {
			Path dstPath = new Path("/hdfs/hadoop-2.7.1.tar.gz");
			FileStatus fs = hdfs.getFileStatus(dstPath);
			BlockLocation[] bls = hdfs.getFileBlockLocations(fs, 0, fs.getLen());
			for(BlockLocation bs : bls) {
				String[] hosts = bs.getHosts();
				for(String host : hosts) {
					System.out.print(host+"|");
				}
			}
		}
		
		//��ȡ��Ⱥ���нڵ����Ϣ
		@Test
		public void testCluster() throws IOException {
			DistributedFileSystem dfs = (DistributedFileSystem) hdfs;
			DatanodeInfo[] infos =  dfs.getDataNodeStats();
			for(DatanodeInfo info : infos) {
				System.out.println(info.getHostName());
			}
		}

}
