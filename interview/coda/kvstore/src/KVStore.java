import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class KVStore {
    private HashMap<String, String> map;
    private Stack<Block> blocks;

    public KVStore() {
        this.map = new HashMap<>();
        this.blocks = new Stack<>();
    }

    public void set(String key, String val) {
        if (hasOpenBlocks()) {
            Block block = blocks.peek();
            if (map.containsKey(key)) {
                block.addAction(ACTION_TYPE.SET, key, map.get(key));
            } else {
                block.addAction(ACTION_TYPE.ADD, key, val);
            }
        }

        map.put(key, val);
    }

    public String get(String key) {
        return map.get(key);
    }

    public void delete(String key) {
        if (map.containsKey(key)) {
            if (hasOpenBlocks()) {
                Block block = blocks.peek();
                block.addAction(ACTION_TYPE.DELETE, key, map.get(key));
            }

            map.remove(key);
        }
    }

    /**
     * start a new transaction
     */
    public void start() {
        blocks.push(new Block());
    }

    /**
     * Rollback a block
     */
    public void rollback() throws Exception {
        if (hasOpenBlocks()) {
            Block block = blocks.pop();
            Stack<Action> actions = block.actions;
            while (!actions.isEmpty()) {
                Action action = actions.pop();
                if (action.type == ACTION_TYPE.ADD) {
                    map.remove(action.key);
                } else if (action.type == ACTION_TYPE.SET) {
                    map.put(action.key, action.val);
                } else if (action.type == ACTION_TYPE.DELETE) {
                    map.put(action.key, action.val);
                }
            }
        } else {
            throw new Exception("Invalid rollback");
        }
    }

    public void commit() throws Exception {
        if (!hasOpenBlocks()) {
            throw new Exception("Invalid commit");
        } else {
            blocks.pop();
        }
    }

    public boolean hasOpenBlocks() {
        return !blocks.empty();
    }

    public void clear() {
        map.clear();
        blocks.clear();
    }

    public void log(String message) {
        System.out.println(message + Arrays.asList(map));
    }

    public void log() {
        System.out.println(Arrays.asList(map));
    }
}
