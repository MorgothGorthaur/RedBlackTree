package redblacktree;

public class App {
    public static void main(String[] args){
       var tree = new RedBlackTree <String> ();
        tree.add(20, "20");
        tree.add(25,"25");
        tree.add(23, "23");
        tree.add(30,"30");
        tree.add(10, "10");
        tree.add(4, "4");
        tree.add(2, "2");
        tree.add(5, "5");
        tree.add(3, "3");
        tree.add(16, "16");
        tree.add(14, "14");
        tree.add(12, "12");
        tree.add(15, "15");
        tree.add(11, "11");
        tree.add(17, "17");
        tree.add(19, "19");


        System.out.println(tree.toString());
        System.out.println(tree.getTreeHeigh());
        System.out.println(tree.delete(30));
//        tree.delete(5);
//        tree.delete(15);
//        tree.delete(11);
//        tree.delete(17);
//        tree.delete(3);
//        tree.delete(10);
//        tree.delete(16);
//        tree.delete(12);
//        tree.delete(2);
//        tree.delete(19);
//        tree.delete(23);
//        tree.delete(14);
//        tree.delete(20);
//        tree.delete(4);
//        tree.delete(25);
   //     System.out.println(tree.headMap(16));
        System.out.println(tree.toString());
    }
}
