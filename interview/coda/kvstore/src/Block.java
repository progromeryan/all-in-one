import java.util.HashMap;

public class Block {
    HashMap<String, String> curr;
    HashMap<String, Integer> currCount;

    public Block(HashMap<String, String> curr, HashMap<String, Integer> currCount) {
        this.curr = curr;
        this.currCount = currCount;
    }
}
