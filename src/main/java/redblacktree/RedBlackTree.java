package redblacktree;


public class RedBlackTree <V> {
    Node <V> node;
    public RedBlackTree (){

    }
    public RedBlackTree  (Node <V> node){
        this.node = node;
    }
    public void add(int key, V value) {
        recursiveAdd(node, key, value);
    }
    private void recursiveAdd(Node <V> curr, int key, V value) {
        if (curr != null) {
            if (curr.key < key) {
                if (curr.rightChild != null) {
                    recursiveAdd(curr.rightChild, key, value);
                } else {
                    curr.rightChild = new Node <V>(key, value, curr);
                    checkRedBlackTreeConditions(curr);
                }
            }
            if (curr.key > key) {
                if (curr.leftChild != null) {
                    recursiveAdd(curr.leftChild, key, value);
                } else {
                    curr.leftChild = new Node<>(key, value, curr);
                    checkRedBlackTreeConditions(curr);
                }
            }
            if(curr.key == key){
                curr.value = value;
            }
        } else {
            node = new Node<>(key, value,null);
            checkRedBlackTreeConditions(node);
        }
    }

    private void leftTurn(Node<V> curr) {
        var tmp = curr.rightChild;
        tmp.parent = null;
        curr.setParent(tmp);
        tmp.color = curr.color;
        curr.color = Color.RED;
        curr.setRightChild(tmp.leftChild);
        tmp.setLeftChild(curr);
        node = node.toFinal();
    }
    private void rightTurn(Node <V> curr) {
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
    private void checkRedBlackTreeConditions(Node <V> curr) {

        if (curr.rightChildIsRed() && curr.leftChildIsRed()) {
            curr.setColorAsRedAndChildsColorAsBlack();
        }
        if (curr.leftChildIsBlack() && curr.rightChildIsRed()) {
            leftTurn(curr);
        }
        //only black node can have red childs
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
    private StringBuilder getAllNodesAsString(Node<V> curr) {
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
    private Node <V> recursiveFind(Node<V> curr, int key) {
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
    public V delete(int key) {
        var deleted = recursiveFind(node, key);
        var value = deleted.value;
        if (deleted != null) {
            deleteNode(deleted);
            return value;
        }
        return null;
    }
    private void deleteNode(Node <V> deleted) {
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
    private void convertToNodeWithoutChild(Node<V> deleted) {
        var min = getMinNode(deleted.rightChild);
        var max = getMaxNode(deleted.leftChild);
        if (min != null) {
            deleted.swapKeysAndValues(min);
            deleteNode(min);
        } else {
            deleted.swapKeysAndValues(max);
            deleteNode(max);
        }
    }
    private Node <V>  getMinNode(Node<V> curr) {
        if (curr != null && curr.leftChild != null) {
            return getMinNode(curr.leftChild);
        }
        return curr;

    }
    private Node <V> getMaxNode(Node<V> curr) {
        if (curr != null && curr.rightChild != null) {
            return getMaxNode(curr.rightChild);
        }
        return curr;
    }
    private void balancingAfterDelete(Node <V> brother) {
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

    private void balancingNodeAfterDeleteWithLeftBrother(Node<V> leftBrother) {
        if (leftBrother.leftChildIsRed()) {
            rightTurn(leftBrother.parent);
            leftBrother.leftChild.color = Color.BLACK;
            leftBrother.rightChild.color = Color.BLACK;
        } else if (leftBrother.leftChildIsBlack() && leftBrother.rightChildIsBlack()) {
            balancingNodeAfterDeleteWithBlackChildsBrother(leftBrother);
        }
    }

    private void balancingNodeAfterDeleteWithRightBrother(Node<V> rightBrother) {
        if (rightBrother.leftChildIsRed()) {
            rightTurn(rightBrother);
            leftTurn(rightBrother.parent.parent);
            rightBrother.color = Color.BLACK;
            rightBrother.getBrother().color = Color.BLACK;
        } else if (rightBrother.leftChildIsBlack() && rightBrother.rightChildIsBlack()) {
            balancingNodeAfterDeleteWithBlackChildsBrother(rightBrother);
        }
    }

    private void balancingNodeAfterDeleteWithBlackChildsBrother(Node<V> brother) {
        brother.color = Color.RED;
        if (brother.parent.color.equals(Color.RED)) {
            brother.parent.color = Color.BLACK;
        } else if (brother.parent.parent != null) {
                balancingAfterDelete(brother.parent.getBrother());
        }
        //right brother must be always black!
        if (brother.isRightChild()) {
            leftTurn(brother.parent);
        }
    }

    public Integer getTreeHeigh(){
        if(node != null){
            return node.getBlackHeight();
        }
        return null;
    }

    RedBlackTree<V> tailMap(int key){

        if( recursiveFind(node, key) != null) {
            var tree = new RedBlackTree<V>();
            split(key, tree);
            var elem = recursiveFind(node,key);
            splitLeft(elem, tree);
            return tree;
        }
        return null;
    }
    RedBlackTree<V> headMap(int key){

        if( recursiveFind(node, key) != null) {
            var tree = new RedBlackTree<V>();
            split(key, tree);
            var elem = recursiveFind(node, key);
            splitLeft(elem, tree);
            var tmp = node;
            node = tree.node;
            tree.node = tmp;
            elem = recursiveFind(node, key);
            tree.add(elem.key, elem.value);
            deleteNode(elem);
            return tree;
        }
        return null;
    }

    RedBlackTree<V> subMap(int firstKey, int secondKey){
        if(recursiveFind(node, firstKey) != null && recursiveFind(node, secondKey) != null && firstKey >= secondKey) {
            var tree = new RedBlackTree <V> ();
            split(firstKey, tree);
            var elem = recursiveFind(node, firstKey);
            splitLeft(elem, tree);
            RedBlackTree<V> subTree = tree.headMap(secondKey);
            concate(tree);
            return subTree;
        }
        return null;
    }
    private void concate(RedBlackTree<V> tree){
        if(tree.node != null) {
            add(tree.node.key, tree.node.value);
            tree.delete(tree.node.key);
            concate(tree);
        }
    }

    private void splitLeft(Node <V> splitNode, RedBlackTree<V> tree){
        if(splitNode.leftChild != null){
            tree.add(splitNode.leftChild.key, splitNode.leftChild.value);
            deleteNode(splitNode.leftChild);

        }

        if(splitNode.leftChild == null && splitNode.rightChild == null){
            tree.add(splitNode.key, splitNode.value);
            deleteNode(splitNode);
        }else {
            splitLeft(splitNode, tree);
        }
    }
    private void split(int key, RedBlackTree<V> tree){
        if(node.key < key){
            tree.add(node.key, node.value);
            deleteNode(node);
            split(key,tree);
        }
    }

}
