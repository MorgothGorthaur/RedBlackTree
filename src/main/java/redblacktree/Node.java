package redblacktree;

class Node {
    Node parent;
    Node leftChild;
    Node rightChild;
    int key;
    Color color;
    Node(int value, Node parent) {
        this.key = value;
        this.color = Color.RED;
        this.parent = parent;
    }

    boolean leftChildIsRed() {
        return leftChild != null && leftChild.color.equals(Color.RED);
    }

    boolean leftChildIsBlack() {
        return leftChild == null || leftChild.color.equals(Color.BLACK);
    }
    boolean rightChildIsRed() {
        return rightChild != null && rightChild.color.equals(Color.RED);
    }

    boolean isLeftChild() {
        return parent != null && parent.leftChild != null && parent.leftChild.equals(this);
    }

    boolean isRightChild() {
        return parent != null && parent.rightChild != null && parent.rightChild.equals(this);
    }
    boolean haveOneOrTwoChild(){
        return (leftChild != null || rightChild != null);
    }
    boolean haveNoChild(){
        return leftChild == null && rightChild == null;
    }
    void setParent(Node tmp) {
        if (isRightChild()) {
            parent.setRightChild(tmp);
        }
        if (isLeftChild()) {
            parent.setLeftChild(tmp);
        }
        if (parent == null) {
            tmp.parent = null;
            parent = tmp;
        }
    }

    void setLeftChild(Node left) {
        if (left != null) {
            leftChild = left;
            left.parent = this;
        } else {
            leftChild = null;
        }
    }

    void setRightChild(Node right) {
        if (right != null) {
            rightChild = right;
            right.parent = this;
        } else {
            rightChild = null;
        }
    }
    void delete(){
        if(isRightChild()){
            parent.rightChild = null;
        }
        if(isLeftChild()){
            parent.leftChild = null;
        }
        parent = null;
    }

    public void swap(Node max) {
        var tmp = key;
        key = max.key;
        max.key = tmp;
    }
    Node getBrother(){
        if(isLeftChild()){
            return parent.rightChild;
        }
        return  parent.leftChild;
    }

    public boolean rightChildIsBlack() {
        return rightChild == null || rightChild.color.equals(Color.BLACK);
    }
}
