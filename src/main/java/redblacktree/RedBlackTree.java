package redblacktree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RedBlackTree {
    private Node node;
    public void add(int value) {
        recursiveAdd(node, value);
    }

    private void recursiveAdd(Node curr, int key) {
        if (curr != null) {
            if (curr.key < key) {
                if (curr.rightChild != null) {
                    recursiveAdd(curr.rightChild, key);
                } else {
                    curr.rightChild = new Node(key, curr);
                    checkRedBlackTreeConditions(curr);
                }
            }
            if (curr.key > key) {
                if (curr.leftChild != null) {
                    recursiveAdd(curr.leftChild, key);
                } else {
                    curr.leftChild = new Node(key, curr);
                    checkRedBlackTreeConditions(curr);
                }
            }
        } else {
            node = new Node(key, null);
        }
    }

    private void swapColors(Node curr) {
        curr.color = Color.RED;
        curr.rightChild.color = Color.BLACK;
        curr.leftChild.color = Color.BLACK;

    }

    private void leftTurn(Node curr) {
        var tmp = curr.rightChild;
        tmp.parent = null;
        curr.setParent(tmp);
        tmp.color = curr.color;
        curr.color = Color.RED;
        curr.setRightChild(tmp.leftChild);
        tmp.setLeftChild(curr);
    }


    private void rightTurn(Node curr) {
        var tmp = curr.leftChild;
        tmp.parent = null;
        curr.setParent(tmp);
        tmp.color = curr.color;
        curr.color = Color.RED;
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
        str.append(" value " + curr.key);
        str.append(" color " + curr.color);
        if (curr.leftChild != null) {
            str.append("\nparent node " + curr.key + " left ");
            str.append(getAllNodesAsString(curr.leftChild));
        }
        if (curr.rightChild != null) {
            str.append("\nparent node " + curr.key + " right ");
            str.append(getAllNodesAsString(curr.rightChild));
        }
        return str;
    }
    private Node recursiveFind(Node curr, int key){
        if(curr.key < key){
            if(curr.rightChild != null){
                return recursiveFind(curr.rightChild, key);
            }
            return null;
        }
        if(curr.key > key){
            if(curr.leftChild != null){
                return recursiveFind(curr.leftChild, key);
            }
            return null;
        }
        if(curr.key == key){

            return curr;
        }
        return null;
    }
}
