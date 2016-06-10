package org.dragon.hadoop;

import java.io.InputStream;
import java.net.URL;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;
import org.junit.Test;

public class HDFSUrlTest {
	
	//��JAVA����ʶ��HDFS��URL
	static {
		URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
	}
	
	//�鿴�ļ�����
	@Test
	public void testRead() throws Exception{
		InputStream in = null;
		//�ļ�·��
		String fileUrl = "hdfs://hadoop.dragon.org:9000/hdfs/01.data";
		try {
			//��ȡ�ļ�������
			in = new URL(fileUrl).openStream();
			//���ļ����ݶ�ȡ�������������̨
			IOUtils.copyBytes(in, System.out, 4096, false);
		} finally {
			IOUtils.closeStream(in);
		}
	}
}
