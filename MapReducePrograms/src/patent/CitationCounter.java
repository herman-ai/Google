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

public class CitationCounter extends Configured implements Tool{
	public static class ImTheMap extends Mapper<LongWritable, Text, Text, Text> {
		@Override
	    public void map(LongWritable key, Text value, Context context) 
	    		throws IOException, InterruptedException {
	    	String temp = value.toString();
			String[] x = temp.split(",");
			if(x[1].contains("CITED")) {
//				System.out.println(x[1]);
				return;
			}
	    	context.write(new Text(x[1]), new Text(x[0]));
	}
	}
	public static class Reducto extends Reducer<Text, Text, Text, IntWritable> {
		
		@Override
		protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {	
			IntWritable numCitations = getCommaSeparatedList(values);
			context.write(key, numCitations);
		}

		private IntWritable getCommaSeparatedList(Iterable<Text> values) {
			int k = 0;
			Iterator<Text> i = values.iterator();
			while (i.hasNext()){
				k++;
				i.next();
			}
			return new IntWritable(k);
		}
	}
	
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		Job job = new Job(conf);
		job.setJarByClass(CitationCounter.class);
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		FileSystem.get(conf).delete(out, true);
		
		
		FileInputFormat.addInputPath(job, in);
		FileOutputFormat.setOutputPath(job, out);
		
		job.setJobName("MyJob");
		job.setJarByClass(CitationCounter.class);

		job.setMapperClass(ImTheMap.class);
		job.setReducerClass(Reducto.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.waitForCompletion(true);
		return 0;
	}
	
	public static void main (String [] args) throws Exception {
		args = new String[] { "/Users/hermansahota/Downloads/cite75_99BACK.txt", "/Users/hermansahota/Downloads/output" };
		ToolRunner.run(new Configuration(), new CitationCounter(), args);
	}

	
	}