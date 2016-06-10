package org.dragon.hadoop.mr;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class WordCount {
	//Mapper ����
	/*KEYIN, VALUEIN, KEYOUT, VALUEOUT
	 * �ֱ�Ϊ����key���͡�value���ͺ����key���͡�value����
	 * */
	static class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
		private Text word = new Text();
		private final static IntWritable one = new IntWritable(1);
		
		@Override
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {  
			//��ȡÿ�����ݵ�ֵ
			String lineValue = value.toString();
			//���зָ�
			StringTokenizer st = new StringTokenizer(lineValue);
			while(st.hasMoreTokens()) {
				//��ȡÿ��ֵ
				String wordValue = st.nextToken();
				//����map�����keyֵ	
				word.set(wordValue);
				//���������map��key��value
				context.write(word, one);
			}
		}
		 
	}
	
	//Reducer ����
	static class MyReducer extends Reducer<Text , IntWritable, Text, IntWritable>{
		private IntWritable result = new IntWritable();
		
		@Override
		protected void reduce(Text key, Iterable<IntWritable> values,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException { 
			int sum  = 0;
			for(IntWritable value : values) {
				sum += value.get();					
			}
			//�����ܴ���
			result.set(sum);
			context.write(key, result);
		}
		
	}
	
	//Client ����
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		args = new String[]{
				"hdfs://hadoop.dragon.org:9000/hdfs/input",
				"hdfs://hadoop.dragon.org:9000/hdfs/output"};
		Configuration conf = new Configuration();
		//���⾯��
		String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		if(otherArgs.length != 2) {
			System.err.println("Usage:WordCount <in> <out>");
			System.exit(2);
		}
		
		Job job = new Job(conf, "wc");
		//1:����Job���е���
		job.setJarByClass(WordCount.class);
		
		//2������Mapper��Reducer��
		job.setMapperClass(MyMapper.class);
		//job.setCombinerClass(MyReducer.class);   //���úϲ�
		job.setReducerClass(MyReducer.class);
				
		//3:���������ļ���Ŀ¼������ļ���Ŀ¼ 
		FileInputFormat.setInputPaths(job, new Path(otherArgs[0]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
		
		//4��������key��value������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		//5:�ύJob,�ȴ����н��������Client��ʾ������Ϣ
		boolean isSuccess = job.waitForCompletion(true);
		
		//��������
		System.exit(isSuccess ? 1 : 0);
	}
	 
}
