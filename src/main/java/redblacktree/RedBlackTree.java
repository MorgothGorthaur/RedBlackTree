package redblacktree;

public class RedBlackTree {
    private Node node;
    public void add(int value){
        recursiveAdd(node, value);
    }
    private void recursiveAdd(Node curr, int value){
        if(curr != null){
            if(curr.value < value){
                if(curr.rightChild != null){
                    recursiveAdd(curr.rightChild, value);
                } else {
                    curr.rightChild = new Node(value, curr);
                }

            }
            if(curr.value > value){
                if(curr.leftChild != null){
                    recursiveAdd(curr.leftChild, value);
                } else {
                    curr.leftChild = new Node(value, curr);
                }
            }
            checkRedBlackTreeConditions(curr);
        } else {
            node = new Node(value, null);
        }
    }

    private void swapColors(Node curr){
        if(curr.rightChildIsRed() && curr.leftChildIsRed()){
            curr.color = Color.RED;
            curr.rightChild.color = Color.BLACK;
            curr.leftChild.color = Color.BLACK;
        }
        if(curr.parent != null){
            swapColors(curr.parent);
        } else {
            curr.color = Color.BLACK;
        }
    }
    private void leftTurn(Node curr){
        if(curr.leftChildIsBlack() && curr.rightChildIsRed()){
            var tmp = new Node(curr.rightChild);
            tmp.value = curr.value;
            curr.value = curr.rightChild.value;
            curr.rightChild = tmp.rightChild;
            tmp.rightChild = tmp.leftChild;
            tmp.leftChild = curr.leftChild;
            curr.leftChild = tmp;
        }
        if(curr.parent != null){
            leftTurn(curr.parent);
        }
    }
    private void rightTurn(Node curr){
        if(curr.leftChildIsRed() && curr.leftChild.leftChildIsRed()){
            var tmp = new Node(curr.leftChild);
            tmp.value = curr.value;
            curr.value = curr.leftChild.value;
            curr.leftChild = tmp.leftChild;
            tmp.leftChild = tmp.rightChild;
            tmp.rightChild = curr.rightChild;
            curr.rightChild = tmp;
        }
        if(curr.parent != null){
            leftTurn(curr.parent);
        }
    }
    public String toString(){
        return getAllNodesAsString(node).toString();
    }
    private void checkRedBlackTreeConditions(Node curr){
        swapColors(curr);
        leftTurn(curr);
        rightTurn(curr);
    }
    private StringBuilder getAllNodesAsString(Node curr){
        var str = new StringBuilder();
        str.append(" value " + curr.value);
        str.append(" color " + curr.color);
        if(curr.leftChild!= null){
            str.append("\nparent node " + curr.value + " left ");
            str.append(getAllNodesAsString(curr.leftChild));
        }
        if(curr.rightChild!= null){
            str.append("\nparent node " + curr.value + " right ");
            str.append(getAllNodesAsString(curr.rightChild));
        }
        return str;
    }
}
