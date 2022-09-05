package redblacktree;

public class RedBlackTree {
    private Node node;

    public void add(int value) {
        recursiveAdd(node, value);
    }

    private void recursiveAdd(Node curr, int value) {
        if (curr != null) {
            if (curr.value < value) {
                if (curr.rightChild != null) {
                    recursiveAdd(curr.rightChild, value);
                } else {
                    curr.rightChild = new Node(value, curr);
                    checkRedBlackTreeConditions(curr);
                }

            }
            if (curr.value > value) {
                if (curr.leftChild != null) {
                    recursiveAdd(curr.leftChild, value);
                } else {
                    curr.leftChild = new Node(value, curr);
                    checkRedBlackTreeConditions(curr);
                }
            }

        } else {
            node = new Node(value, null);
        }
    }

    private void swapColors(Node curr) {
        curr.color = Color.RED;
        curr.rightChild.color = Color.BLACK;
        curr.leftChild.color = Color.BLACK;

    }

    private void leftTurn(Node curr) {
        var tmp = new Node(curr.rightChild);
        curr.swapParent(tmp);
        tmp.color = curr.color;
        curr.color = curr.rightChild.color;
        curr.setRightChild(tmp.leftChild);
        tmp.setLeftChild(curr);



    }


    private void rightTurn(Node curr) {
        var tmp = new Node(curr.leftChild);
        curr.swapParent(tmp);
        tmp.color = curr.color;
        curr.color = tmp.leftChild.color;
        curr.setLeftChild(tmp.rightChild);
        tmp.setRightChild(curr);

    }


    public String toString() {
        return getAllNodesAsString(node).toString();
    }

    private void checkRedBlackTreeConditions(Node curr) {
        if (curr.rightChildIsRed() && curr.leftChildIsRed()) {
            swapColors(curr);
        }
        if (curr.leftChildIsBlack() && curr.rightChildIsRed()) {
            leftTurn(curr);
        }
        if (curr.leftChildIsRed() && curr.leftChild.leftChildIsRed()) {
            rightTurn(curr);
        }
        if (curr.parent != null) {
            checkRedBlackTreeConditions(curr.parent);
        } else {
            curr.color = Color.BLACK;
            node = curr;

        }

    }

    private StringBuilder getAllNodesAsString(Node curr) {
        var str = new StringBuilder();
        str.append(" value " + curr.value);
        str.append(" color " + curr.color);
        if (curr.leftChild != null) {
            str.append("\nparent node " + curr.value + " left ");
            str.append(getAllNodesAsString(curr.leftChild));
        }
        if (curr.rightChild != null) {
            str.append("\nparent node " + curr.value + " right ");
            str.append(getAllNodesAsString(curr.rightChild));
        }
        return str;
    }
}
