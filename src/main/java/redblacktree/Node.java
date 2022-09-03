package redblacktree;
class Node {
    Node parent;
    Node leftChild;
    Node rightChild;
    int value;
    Color color;

    Node(int value, Node parent ) {
        this.value = value;
        this.color = Color.RED;
        this.parent = parent;
    }
    Node(Node another){
        this.value = another.value;
        this.color = another.color;
        this.leftChild = another.leftChild;
        this.rightChild = another.rightChild;
        this.parent = another.parent;
    }

    boolean leftChildIsRed(){
        return leftChild != null && leftChild.color.equals(Color.RED);
    }
    boolean leftChildIsBlack(){
        return leftChild == null || leftChild.color.equals(Color.BLACK);
    }
    boolean rightChildIsRed(){
        return rightChild != null && rightChild.color.equals(Color.RED);
    }
}
