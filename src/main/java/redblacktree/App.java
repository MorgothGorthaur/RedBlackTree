package redblacktree;


public class App {
    public static void main(String[] args) {
        var tree = new RedBlackTree<String>();
        tree.put(20, "20");
        tree.put(25, "25");
        tree.put(23, "23");
        tree.put(30, "30");
        tree.put(10, "10");
        tree.put(4, "4");
        tree.put(2, "2");
        tree.put(5, "5");
        tree.put(3, "3");
        tree.put(16, "16");
        tree.put(14, "14");
        tree.put(12, "12");
        tree.put(15, "15");
        tree.put(11, "11");
        tree.put(17, "17");
        tree.put(19, "19");


        System.out.println(tree.toString());
        System.out.println(tree.getTreeHeigh());
        System.out.println(tree.subMap(23, true, 14, true));
        System.out.println(tree.headMap(16));
        System.out.println(tree.toString());

    }
}
