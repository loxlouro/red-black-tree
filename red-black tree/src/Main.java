public class Main {
    public static void main(String[] args) {
        RBTree tree = new RBTree();
        tree.insertNode(3);
        tree.insertNode(5);
        tree.insertNode(6);
        tree.insertNode(7);
        tree.insertNode(4);

        tree.insertNode(1);
        tree.searchNode(7);
        tree.showTree();
        tree.insertNode(12);
        System.out.println("\n\n\n\n");
        tree.showTree();
    }
}