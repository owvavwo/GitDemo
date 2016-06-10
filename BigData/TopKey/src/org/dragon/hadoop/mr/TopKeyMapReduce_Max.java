package org.dragon.hadoop.mr;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TopKeyMapReduce_Max {
	//Mapper
	static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable>{
		
		private Text mapOutputKey = new Text();	//map out key 
		private LongWritable mapOutputValue = new LongWritable(); //map out value
		private long topValue = Long.MIN_VALUE;             //store max value
		
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, LongWritable>.Context context)
				throws IOException, InterruptedException {  
			String lineValue = value.toString();
			String[] strs = lineValue.split("\t");
			long tempValue = Long.valueOf(strs[1]);
			if(topValue < tempValue) {
				topValue = tempValue;
				mapOutputKey.set(strs[0]);
			}
		}

		@Override
		protected void cleanup(Mapper<LongWritable, Text, Text, LongWritable>.Context context)
				throws IOException, InterruptedException {
			mapOutputValue.set(topValue);
			context.write(mapOutputKey, mapOutputValue);
		}

		@Override
		protected void setup(Mapper<LongWritable, Text, Text, LongWritable>.Context context)
				throws IOException, InterruptedException { 
			
		}
				
	}
	
	//Driver Code
	public int run(String[] args) throws Exception{		
		Configuration conf = new Configuration();		
		Job job = new Job(conf, TopKeyMapReduce_Max.class.getSimpleName());
		//1:����Job���е���
		job.setJarByClass(TopKeyMapReduce_Max.class);
		
		//2������Mapper��Reducer��
		job.setMapperClass(MyMapper.class); 
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		 
		//set no reduce task 
		job.setNumReduceTasks(0);
				
		//3:���������ļ���Ŀ¼������ļ���Ŀ¼ 
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//4��������key��value������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//5:�ύJob,�ȴ����н��������Client��ʾ������Ϣ
		boolean isSuccess = job.waitForCompletion(true);
		
		//����״̬��
		return isSuccess ? 1 : 0;
	}
		 public static void main(String[] args) throws Exception {
			 args = new String[]{
						"hdfs://hadoop.dragon.org:9000/hdfs/output",
						"hdfs://hadoop.dragon.org:9000/hdfs/topkeyput"};
			 int status = new TopKeyMapReduce_Max().run(args);
			 System.exit(status);
		}
}

