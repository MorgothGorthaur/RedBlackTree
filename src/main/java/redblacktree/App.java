package redblacktree;

public class App {
    public static void main(String[] args){
        RedBlackTree tree = new RedBlackTree();
        tree.add(20);
        //tree.add(25);
        tree.add(23);
        tree.add(30);
        tree.add(10);
        tree.add(4);
        tree.add(2);
        tree.add(5);
        tree.add(3);
        tree.add(16);
        tree.add(14);
        tree.add(12);
        tree.add(15);
        tree.add(11);
        tree.add(17);
        tree.add(19);


        System.out.println(tree.toString());
        System.out.println(tree.delete(30));
        System.out.println(tree.delete(5));
        System.out.println(tree.delete(15));
      //  System.out.println(tree.delete(11));
        System.out.println(tree.delete(17));
        System.out.println(tree.delete(3));
        System.out.println(tree.delete(10));
        //tree.delete(16);
        System.out.println(tree.delete(12));
        System.out.println(tree.delete(2));
         //tree.delete(2);
        //tree.delete(16);
        System.out.println(tree.toString());
    }
}
