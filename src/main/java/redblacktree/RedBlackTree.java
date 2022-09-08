package redblacktree;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class RedBlackTree {
    private Node node;

    public void add(int key) {
        recursiveAdd(node, key);
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
            checkRedBlackTreeConditions(node);
        }
    }

    private void leftTurn(Node curr) {
        var tmp = curr.rightChild;
        tmp.parent = null;
        curr.setParent(tmp);
        tmp.color = curr.color;
        curr.color = Color.RED;
        curr.setRightChild(tmp.leftChild);
        tmp.setLeftChild(curr);
        node = node.toFinal();
    }
    private void rightTurn(Node curr) {
        var tmp = curr.leftChild;
        tmp.parent = null;
        curr.setParent(tmp);
        tmp.color = curr.color;
        curr.color = Color.RED;
        curr.setLeftChild(tmp.rightChild);
        tmp.setRightChild(curr);
        node = node.toFinal();
    }
    public String toString() {
        if (node != null) {
            return getAllNodesAsString(node).toString();
        }
        return null;

    }
    private void checkRedBlackTreeConditions(Node curr) {
        if (curr.rightChildIsRed() && curr.leftChildIsRed()) {
            curr.setColorAsRedAndChildsColorAsBlack();
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
    private Node recursiveFind(Node curr, int key) {
        if (curr.key < key) {
            if (curr.rightChild != null) {
                return recursiveFind(curr.rightChild, key);
            }
            return null;
        }
        if (curr.key > key) {
            if (curr.leftChild != null) {
                return recursiveFind(curr.leftChild, key);
            }
            return null;
        }
        if (curr.key == key) {

            return curr;
        }
        return null;
    }
    public Integer delete(int key) {
        var deleted = recursiveFind(node, key);
        if (deleted != null) {
            deleteNode(deleted);
            return deleted.key;
        }
        return null;
    }
    private void deleteNode(Node deleted) {
        if (deleted.haveNoChild()) {
            if (deleted.color.equals(Color.BLACK) && deleted.parent != null) {
                balancingAfterDelete(deleted.getBrother());
            }
            if (deleted.parent == null) {
                node = null;
            }
            deleted.delete();
        }
        if (deleted.haveOneOrTwoChild()) {
            convertToNodeWithoutChild(deleted);
        }
    }
    private void convertToNodeWithoutChild(Node deleted) {
        var min = getMinNode(deleted.rightChild);
        var max = getMaxNode(deleted.leftChild);
        if (min != null) {
            deleted.swapKeys(min);
            deleteNode(min);
        } else {
            deleted.swapKeys(max);
            deleteNode(max);
        }
    }
    private Node getMinNode(Node curr) {
        if (curr != null && curr.leftChild != null) {
            return getMinNode(curr.leftChild);
        }
        return curr;

    }
    private Node getMaxNode(Node curr) {
        if (curr != null && curr.rightChild != null) {
            return getMaxNode(curr.rightChild);
        }
        return curr;
    }
    private void balancingAfterDelete(Node brother) {
        if (brother.color.equals(Color.BLACK)) {
            if (brother.isLeftChild()) {
                balancingNodeAfterDeleteWithLeftBrother(brother);
            } else {
                balancingNodeAfterDeleteWithRightBrother(brother);
            }
        } else {
            if (brother.isRightChild()) {
                leftTurn(brother.parent);
                balancingAfterDelete(brother.leftChild.rightChild);
            } else {
                rightTurn(brother.parent);
                balancingAfterDelete(brother.rightChild.leftChild);
            }
        }
    }

    private void balancingNodeAfterDeleteWithLeftBrother(Node leftBrother) {
        if (leftBrother.leftChildIsRed()) {
            rightTurn(leftBrother.parent);
            leftBrother.leftChild.color = Color.BLACK;
            leftBrother.rightChild.color = Color.BLACK;
        } else if (leftBrother.leftChildIsBlack() && leftBrother.rightChildIsBlack()) {
            balancingNodeAfterDeleteWithBlackChildsBrother(leftBrother);
        }
    }

    private void balancingNodeAfterDeleteWithRightBrother(Node rightBrother) {
        if (rightBrother.leftChildIsRed()) {
            rightTurn(rightBrother);
            leftTurn(rightBrother.parent.parent);
            rightBrother.color = Color.BLACK;
            rightBrother.getBrother().color = Color.BLACK;
        } else if (rightBrother.leftChildIsBlack() && rightBrother.rightChildIsBlack()) {
            balancingNodeAfterDeleteWithBlackChildsBrother(rightBrother);
        }
    }

    private void balancingNodeAfterDeleteWithBlackChildsBrother(Node brother) {
        brother.color = Color.RED;
        if (brother.parent.color.equals(Color.RED)) {
            brother.parent.color = Color.BLACK;
        } else if (brother.parent.parent != null) {
                balancingAfterDelete(brother.parent.getBrother());
        }
        if (brother.isRightChild()) {
            leftTurn(brother.parent);
        }
    }

}
