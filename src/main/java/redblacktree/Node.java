package redblacktree;

class Node {
    Node parent;
    Node leftChild;
    Node rightChild;
    int value;
    Color color;

    Node(int value, Node parent) {
        this.value = value;
        this.color = Color.RED;
        this.parent = parent;
    }

    Node(Node another) {
        this.value = another.value;
        this.color = another.color;
        if (another.rightChild != null) {
            setRightChild(new Node(another.rightChild));
        }
        if (another.leftChild != null) {
            setLeftChild(new Node(another.leftChild));
        }
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

    void swapParent(Node tmp) {
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

        //parent = tmp;

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

}
