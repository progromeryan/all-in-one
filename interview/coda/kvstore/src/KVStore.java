import java.util.HashMap;
import java.util.Stack;

public class KVStore {
    private HashMap<String, String> map;
    private HashMap<String, Integer> countMap;
    private Stack<Block> blocks;

    public KVStore() {
        this.map = new HashMap<>();
        this.countMap = new HashMap<>();
        this.blocks = new Stack<>();
    }

    public void parseCommand(String command) {
        String[] parsedCommand = command.split(" ");
        if (parsedCommand[0].compareTo("SET") == 0 && parsedCommand.length == 3) {
            this.set(parsedCommand[1], parsedCommand[2]);
            return;
        }

        if (parsedCommand[0].equals("GET")) {
            System.out.println(this.get(parsedCommand[1]));
            return;
        }

        if (parsedCommand[0].equals("COUNT")) {
            System.out.println(this.count(parsedCommand[1]));
            return;
        }

        if (parsedCommand[0].equals("DELETE")) {
            this.delete(parsedCommand[1]);
            return;
        }
        if (parsedCommand[0].equals("BEGIN")) {
            this.begin();
            return;
        }
        if (parsedCommand[0].equals("ROLLBACK")) {
            this.rollback();
            return;
        }

        if (parsedCommand[0].equals("CLEAR")) {
            this.clear();
            return;
        }


        if (parsedCommand[0].equals("COMMIT")) {
            this.commit();
            return;
        } else {
            System.out.println("Invalid command");
        }

    }


    public void set(String key, String val) {
        if (map.containsKey(key)) {
            if (map.get(key).equals(val)) return;
            String prevVal = map.get(key);
            if (countMap.containsKey(prevVal) && countMap.get(prevVal) != 0) {
                countMap.put(prevVal, countMap.get(prevVal) - 1);
            }

            if (countMap.containsKey(val)) {
                countMap.put(val, countMap.get(val) + 1);
            } else {
                countMap.put(val, 1);
            }
        } else {
            if (countMap.containsKey(val)) {
                countMap.put(val, countMap.get(val) + 1);
            } else {
                countMap.put(val, 1);
            }
        }
        map.put(key, val);
    }

    public String get(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return key + " not set";
        }
    }

    public String count(String key) {
        if (countMap.containsKey(key)) {
            return String.valueOf(countMap.get(key));
        } else {
            return "0";
        }
    }

    public void delete(String key) {
        if (map.containsKey(key)) {
            String val = map.get(key);
            map.remove(key);
            if (countMap.containsKey(val)) {
                countMap.put(val, countMap.get(val) - 1);
            }
        }
    }

    /**
     * start a new transaction
     */
    public void begin() {
        blocks.push(new Block(new HashMap<>(map), new HashMap<>(countMap)));
    }

    /**
     * Rollback a block
     */
    public void rollback() {
        if (hasOpenBlocks()) {
            Block block = blocks.pop();
            this.map = block.curr;
            this.countMap = block.currCount;
        } else {
            System.out.println("NO TRANSACTION");
        }
    }

    public void commit() {
        if (!hasOpenBlocks()) {
            System.out.println("No transaction");
        } else {
            blocks.pop();
        }
    }

    public boolean hasOpenBlocks() {
        return !blocks.empty();
    }

    public void clear() {
        map.clear();
        countMap.clear();
        blocks.clear();
    }
}
