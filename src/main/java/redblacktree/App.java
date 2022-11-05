package redblacktree;


public class App {
    public static void main(String[] args){
       var tree = new RedBlackTree <String> ();
        tree.put(20, "20");
        tree.put(25,"25");
        tree.put(23, "23");
        tree.put(30,"30");
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
        System.out.println(tree.subMap(23, false ,14, false));
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
