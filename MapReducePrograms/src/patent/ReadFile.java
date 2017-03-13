package patent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class ReadFile {
	public static void main(String[] args) throws IOException {
		Path pt = new Path("file:///Users/hermansahota/ibm-eclipse-workspace/iris-lm.pmml");
		FileSystem fs = FileSystem.get(new Configuration());
        BufferedReader br=new BufferedReader(new InputStreamReader(fs.open(pt)));
        String line;
        line=br.readLine();
        while (line != null){
                System.out.println(line);
                line=br.readLine();
        }
	}

}
