package org.dragon.hadoop.mr;

import java.io.IOException;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class TopKeysMapReduce2_NoValues {
	//Mapper
	static class MyMapper extends Mapper<LongWritable, Text, LongWritable, NullWritable>{
		public static final int KEY = 3; 
		private TreeSet<Long> topSet = new TreeSet<Long>(); 
		
		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {  
			String lineValue = value.toString();
			String[] strs = lineValue.split("\t");
			long tempValue = Long.valueOf(strs[1]);  
			
			topSet.add(tempValue);
			if(topSet.size() > KEY) { 
				topSet.remove(topSet.first());
			}
		}

		@Override
		protected void cleanup(Context context)
				throws IOException, InterruptedException { 
			LongWritable setKey = new LongWritable();
			for(Long top : topSet) {
				setKey.set(top);
				context.write(setKey, NullWritable.get());
			}
		}

		@Override
		protected void setup(Mapper<LongWritable, Text, LongWritable, NullWritable>.Context context)
				throws IOException, InterruptedException { 
			
		}
				
	}
	
	//Driver Code
	public int run(String[] args) throws Exception{		
		Configuration conf = new Configuration();		
		Job job = new Job(conf, TopKeysMapReduce2_NoValues.class.getSimpleName());
		//1:����Job���е���
		job.setJarByClass(TopKeysMapReduce2_NoValues.class);
		
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
			 int status = new TopKeysMapReduce2_NoValues().run(args);
			 System.exit(status);
		}
}

