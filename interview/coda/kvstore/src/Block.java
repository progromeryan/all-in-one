import java.util.Stack;

public class Block {
    Stack<Action> actions;

    public Block() {
        this.actions = new Stack<>();
    }

    public void addAction(ACTION_TYPE type, String key, String val) {
        this.actions.push(new Action(type, key, val));
    }
}
