package org.dragon.hadoop.mr;

import java.io.IOException;
import java.util.Comparator;
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
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.dragon.hadoop.mr.util.TopKeyWritable;
import org.dragon.hadoop.mr.util.TopWritable;

import sun.security.timestamp.TSRequest;

public class TopKeysMapReduce5_Songs_TopKey {
	public static final int KEY = 5; 
	
	//Mapper
	static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable>{ 
		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {  
			String lineValue = value.toString();
			if(lineValue == null) {
				return;
			}
			
			String[] strs = lineValue.split("\t");			   
			if(strs!=null && strs.length==5 ) {
				 String languageType = strs[0];
				 String songName = strs[1];
				 String playTimes = strs[3];
				context.write(new Text(languageType + "\t" + songName), 
						new LongWritable(Long.valueOf(playTimes)));
			}
		}

		@Override
		public void cleanup(Context context)
				throws IOException, InterruptedException {  
			super.cleanup(context);
		}

		@Override
		public void setup(Mapper<LongWritable, Text, Text, LongWritable>.Context context)
				throws IOException, InterruptedException { 
			
		}
				
	}
	
	//Reduce Code
	static class MyReducer extends Reducer<Text, LongWritable, TopKeyWritable, NullWritable> {
		private TreeSet<TopKeyWritable> topSet = new TreeSet<TopKeyWritable>();

		@Override
		public void cleanup(Reducer<Text, LongWritable, TopKeyWritable, NullWritable>.Context context)
				throws IOException, InterruptedException { 
			for(TopKeyWritable top : topSet) {
				context.write(top, NullWritable.get());
			}
		}

		@Override
		public void reduce(Text key, Iterable<LongWritable> values,
				Context context) throws IOException, InterruptedException {  
			 if(null == key) {
				 return;
			 }
			 
			 String[] splits = key.toString().split("\t");
			 if(null==splits && splits.length==0) {
				 return;
			 }
			 
			String languageType = splits[0];
			String songName = splits[1];
			Long playTimes = 0L;
			for(LongWritable value : values) {
				playTimes += value.get();
	 		}
			topSet.add(new TopKeyWritable(languageType, songName, playTimes));
			
			if(topSet.size() > KEY) {
				topSet.remove(topSet.first());
			}
			 
			 
		}

		@Override
		public void setup(Reducer<Text, LongWritable, TopKeyWritable, NullWritable>.Context context)
				throws IOException, InterruptedException { 
			
		}
		
	}
	
	//Driver Code
	public int run(String[] args) throws Exception{		
		Configuration conf = new Configuration();		
		Job job = new Job(conf, TopKeysMapReduce5_Songs_TopKey.class.getSimpleName());
		//1:����Job���е���
		job.setJarByClass(TopKeysMapReduce5_Songs_TopKey.class);
		
		//2������Map 
		job.setMapperClass(MyMapper.class); 
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(LongWritable.class);
		 
		//����Reduce
		 job.setReducerClass(MyReducer.class);
		 job.setOutputKeyClass(TopKeyWritable.class);
		 job.setOutputValueClass(NullWritable.class);
		
				
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
						"hdfs://hadoop.dragon.org:9000/hdfs/input",
						"hdfs://hadoop.dragon.org:9000/hdfs/topkeyput"};
			 int status = new TopKeysMapReduce5_Songs_TopKey().run(args);
			 System.exit(status);
		}
}

