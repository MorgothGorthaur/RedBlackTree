package redblacktree;

public class App {
    public static void main(String[] args){
        RedBlackTree tree = new RedBlackTree();
        tree.add(5);
        tree.add(6);
        tree.add(4);
        tree.add(3);
        tree.add(9);
        tree.add(8);
        tree.add(10);
        System.out.println(tree.toString());
    }
}
