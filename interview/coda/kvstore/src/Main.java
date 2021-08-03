public class Main {
    public static void main(String[] args) throws Exception {
        KVStore kvStore = new KVStore();
        kvStore.set("1", "1");
        kvStore.log("No start: ");
        kvStore.delete("1");
        kvStore.log("Clear: ");

        // block 1
        kvStore.start();
        kvStore.set("1", "1");
        kvStore.set("2", "2");
        kvStore.set("3", "3");
        kvStore.log("Outer block: ");

        // inner block
        kvStore.start();
        kvStore.set("10", "10");
        kvStore.log("Inner block");
        // rollback inner block
        kvStore.rollback();
        kvStore.log("Rollback inner block");

        // rollback block 1
        kvStore.commit();
        kvStore.log("Commit outer block: ");

        // start new one
        kvStore.clear();
        kvStore.start();
        kvStore.set("100", "100");
        kvStore.set("200", "200");
        kvStore.log("Outer block: ");

        kvStore.start();
        kvStore.set("500", "500");
        kvStore.set("600", "600");
        kvStore.commit();
        kvStore.log("Commit inner block: ");

        kvStore.rollback();
        kvStore.log("Rollback outer block");
    }
}
