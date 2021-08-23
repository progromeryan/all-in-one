```java
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountWesiteInLogFile {

private static final Scanner scan = new Scanner(System.in);
   
    public static void main(String args[]) throws Exception {
        // read the string filename
        String filename;
        filename = scan.nextLine();
        helper(filename);
    }
   
    public static void helper(String path){
        
        String[] infos = path.split("\\.");
        String version = infos[0].substring(infos[0].length() - 2);
        HashMap<String, Integer> counts = new HashMap<>();
        PrintWriter pw = null;
        Scanner scanner = null;
        try {
            pw = new PrintWriter(new File("records_hosts_access_log_" + version + ".txt"));
            scanner = new Scanner(new File(path));
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] info = line.split(" - - ");
                counts.put(info[0], counts.getOrDefault(info[0], 0) + 1);
            }
            
            for(Map.Entry<String, Integer> entry : counts.entrySet()) {
                pw.println(entry.getKey() + " " + entry.getValue());
                pw.flush();
            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }finally {
            if(scanner != null) {
                scanner.close();
            }
            if(pw != null) {
                pw.close();
            }
        }
        
    }

}

```