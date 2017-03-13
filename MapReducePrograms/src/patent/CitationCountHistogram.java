package patent;


import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/*
 * This is one of my first MapReduce jobs.  It outputs a patent number and all the patents that cite it from cite75_99
 * 
 */

public class CitationCountHistogram extends Configured implements Tool{
	private final static IntWritable uno = new IntWritable(1);
	public static class ImTheMap extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
		@Override
	    public void map(LongWritable key, Text value, Context context) 
	    		throws IOException, InterruptedException {
	    	String[] split = value.toString().split("[ \t\r]");
			int numCitations = Integer.parseInt(split[1]);
//	    	System.out.println(split[0] + " " + split[1] + " " + split.length);
			context.write(new IntWritable(numCitations), 
	    			uno);
	}
	}
	public static class Reducto extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
		
		@Override
		protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {	
			IntWritable occurences = getTotalOccurences(values);
			context.write(key, occurences);
		}

		private IntWritable getTotalOccurences(Iterable<IntWritable> values) {
			int count = 0;
			for (IntWritable value:values){
				count = count + value.get();
			}
			return new IntWritable(count);
		}
	}
	
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		Job job = new Job(conf);
		job.setJarByClass(CitationCountHistogram.class);
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		FileSystem.get(conf).delete(out, true);
		
		
		FileInputFormat.addInputPath(job, in);
		FileOutputFormat.setOutputPath(job, out);
//		job.setInputFormatClass(FileInputFormat.class);
		
		job.setJobName("Citation count histogram");
		job.setJarByClass(CitationCountHistogram.class);

		job.setMapperClass(ImTheMap.class);
		job.setReducerClass(Reducto.class);
		
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.waitForCompletion(true);
		return 0;
	}
	
	public static void main (String [] args) throws Exception {
		args = new String[] { "/Users/hermansahota/Downloads/output", "/Users/hermansahota/Downloads/outputHistogram" };
		ToolRunner.run(new Configuration(), new CitationCountHistogram(), args);
	}

	
	}