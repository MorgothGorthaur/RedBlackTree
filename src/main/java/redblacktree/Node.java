package redblacktree;

public class Node <V> {
    Node<V> parent;
    Node<V> leftChild;
    Node<V> rightChild;
    public int key;
    public V value;
    Color color;

    Node(int key, V value, Node<V> parent) {
        this.key = key;
        this.value = value;
        this.color = Color.RED;
        this.parent = parent;
    }

    Node<V> getBrother() {
        if (isLeftChild()) {
            return parent.rightChild;
        }
        return parent.leftChild;
    }

    Node<V> toFinal() {
        if (parent != null) {
            return parent.toFinal();
        }
        return this;
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

    boolean rightChildIsBlack() {
        return rightChild == null || rightChild.color.equals(Color.BLACK);
    }

    boolean isLeftChild() {
        return parent != null && parent.leftChild != null && parent.leftChild.equals(this);
    }

    boolean isRightChild() {
        return parent != null && parent.rightChild != null && parent.rightChild.equals(this);
    }

    boolean haveOneOrTwoChild() {
        return (leftChild != null || rightChild != null);
    }

    boolean haveNoChild() {
        return leftChild == null && rightChild == null;
    }

    void setParent(Node<V> tmp) {
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

    void setLeftChild(Node<V> left) {
        if (left != null) {
            leftChild = left;
            left.parent = this;
        } else {
            leftChild = null;
        }
    }

    void setRightChild(Node<V> right) {
        if (right != null) {
            rightChild = right;
            right.parent = this;
        } else {
            rightChild = null;
        }
    }
    void setColorAsRedAndChildsColorAsBlack() {
        color = Color.RED;
        rightChild.color = Color.BLACK;
        leftChild.color = Color.BLACK;
    }
    void delete() {
        if (isRightChild()) {
            parent.rightChild = null;
        }
        if (isLeftChild()) {
            parent.leftChild = null;
        }
        parent = null;
    }

    void swapKeysAndValues(Node<V> max) {
        var tmpKey = key;
        var tmpValue = value;
        key = max.key;
        value =  max.value;
        max.key = tmpKey;
        max.value = tmpValue;
    }

    int getBlackHeight(){
       var height = 0;
       if(color.equals(Color.BLACK)){
           height += 1;
       }
       if(leftChild != null){
           height += leftChild.getBlackHeight();
       }
       return height;
    }
}
