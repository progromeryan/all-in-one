import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        KVStore kvStore = new KVStore();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isExit = false;
        while (!isExit) {
            String command = reader.readLine();
            if (command.equals("END")) {
                isExit = true;
            } else {
                kvStore.parseCommand(command);
            }
        }
    }
}
