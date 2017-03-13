package patent;


import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
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

public class CitationOrganizer extends Configured implements Tool{
	public static class ImTheMap extends Mapper<LongWritable, Text, Text, Text> {
		@Override
	    public void map(LongWritable key, Text value, Context context) 
	    		throws IOException, InterruptedException {
	    	String temp = value.toString();
			String[] x = temp.split(",");
	    	context.write(new Text(x[1]), new Text(x[0]));
	}
	}
	public static class Reducto extends Reducer<Text, Text, Text, Text> {
		
		@Override
		protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {	
			String csv = getCommaSeparatedList(values);
			context.write(key, new Text(csv));
		}

		private String getCommaSeparatedList(Iterable<Text> values) {
			String csv = "";
			Iterator<Text> i = values.iterator();
			while (i.hasNext()){
				if(csv.length() > 0){
					csv += ";";
				}
				Text next = i.next();
				csv += next.toString();
			}
			return csv;
		}
	}
	
	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = getConf();
		Job job = new Job(conf);
		job.setJarByClass(CitationOrganizer.class);
		Path in = new Path(args[0]);
		Path out = new Path(args[1]);
		FileSystem.get(conf).delete(out, true);
		
		
		FileInputFormat.addInputPath(job, in);
		FileOutputFormat.setOutputPath(job, out);
		
		job.setJobName("MyJob");
		job.setJarByClass(CitationOrganizer.class);

		job.setMapperClass(ImTheMap.class);
		job.setReducerClass(Reducto.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		job.waitForCompletion(true);
		return 0;
	}
	
	public static void main (String [] args) throws Exception {
		args = new String[] { "/Users/hermansahota/Downloads/cite75_99BACK.txt", "/Users/hermansahota/Downloads/output" };
		ToolRunner.run(new Configuration(), new CitationOrganizer(), args);
	}

	
	}