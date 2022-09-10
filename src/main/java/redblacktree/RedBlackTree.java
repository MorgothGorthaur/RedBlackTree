package redblacktree;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RedBlackTree<V> {
    Node<V> node;

    public Node<V> firstEntry() {
        return getMaxNode(node);
    }
    public Integer firstKey() {
        if (node != null) {
            var first = getMaxNode(node);
            return first.key;
        }
        return null;
    }
    public Node<V> lastEntry() {
        return getMinNode(node);
    }
    public Integer lastKey() {
        if (node != null) {
            var last = getMinNode(node);
            return last.key;
        }
        return null;
    }
    public Node<V> pollFirst() {
        var first = getMaxNode(node);
        if (first != null) {
            remove(first.key);
            return first;
        }
        return null;
    }
    public Node<V> pollLast() {
        var last = getMinNode(node);
        if (last != null) {
            remove(last.key);
            return last;
        }
        return null;
    }
    public Node <V> ceilingEntry(int key) {
        var tree = tailMap(key);
        if(tree.node != null){
            return tree.lastEntry();
        }
        return null;
    }
    public Integer ceilingKey(int key){
        var ceiling = ceilingEntry(key);
        if(ceiling != null){
            return ceiling.key;
        }
        return null;
    }
    public Node<V> floorEntry(int key){
        var tree = headMap(key);
        if(tree.node != null){
            return tree.firstEntry();
        }
        return null;
    }
    public Integer floorKey(int key){
        var floor = floorEntry(key);
        if(floor != null){
            return floor.key;
        }
        return null;
    }
    public Node <V> lowerEntry(int key){
        var lower = floorEntry(key);
        if(lower != null && lower.key != key){
            return lower;
        }
        return null;
    }
    public Integer lowerKey(int key){
        var lower = lowerEntry(key);
        if(lower != null){
            return lower.key;
        }
        return null;
    }
    public Node <V> higherEntry(int key){
        var higher = ceilingEntry(key);
        if(higher != null && higher.key != key){
            return higher;
        }
        return null;
    }
    public Integer higherKey(int key){
        var higher = higherEntry(key);
        if(higher != null){
            return higher.key;
        }
        return null;
    }
    public void put(int key, V value) {
        recursiveAdd(node, key, value);
    }
    public V get(int key) {
        var finded = recursiveFind(node, key);
        if (finded != null) {
            return finded.value;
        }
        return null;
    }
    public V remove(int key) {
        var deleted = recursiveFind(node, key);
        if (deleted != null) {
            var value = deleted.value;
            deleteNode(deleted);
            return value;
        }
        return null;
    }
    public RedBlackTree<V> tailMap(int key) {
        var tree = tailMap(key, true);
        return tree;

    }
    public RedBlackTree<V> tailMap(int key, boolean incl) {
        var tree = new RedBlackTree<V>();
        splitTail(node, key, tree);
        var keyNode = recursiveFind(node, key);
        if (incl && keyNode != null) {
            tree.put(keyNode.key, keyNode.value);
        }
        return tree;
    }
    public RedBlackTree<V> headMap(int key) {
        var tree = headMap(key, true);
        return tree;
    }
    public RedBlackTree<V> headMap(int key, boolean incl) {
        var tree = new RedBlackTree<V>();
        splitHead(node, key, tree);
        var keyNode = recursiveFind(node, key);
        if (incl && keyNode != null) {
            tree.put(keyNode.key, keyNode.value);
        }
        return tree;
    }
    public RedBlackTree<V> subMap(int firstKey, int secondKey) {
        return subMap(firstKey, true, secondKey, true);
    }
    public RedBlackTree<V> subMap(int firstKey, boolean lowIncl, int secondKey, boolean highIncl) {
        var tree = new RedBlackTree<V>();
        tree = headMap(firstKey, lowIncl);
        tree = tree.tailMap(secondKey, highIncl);
        return tree;
    }

    private void recursiveAdd(Node<V> curr, int key, V value) {
        if (curr != null) {
            if (curr.key < key) {
                if (curr.rightChild != null) {
                    recursiveAdd(curr.rightChild, key, value);
                } else {
                    curr.rightChild = new Node<V>(key, value, curr);
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
            if (curr.key == key) {
                curr.value = value;
            }
        } else {
            node = new Node<>(key, value, null);
            checkRedBlackTreeConditions(node);
        }
    }
    private Node<V> recursiveFind(Node<V> curr, int key) {
        if (curr != null) {
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
        }
        return null;
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
    private void rightTurn(Node<V> curr) {
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
    private void checkRedBlackTreeConditions(Node<V> curr) {

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
    private void deleteNode(Node<V> deleted) {
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
    private Node<V> getMinNode(Node<V> curr) {
        if (curr != null && curr.leftChild != null) {
            return getMinNode(curr.leftChild);
        }
        return curr;

    }
    private Node<V> getMaxNode(Node<V> curr) {
        if (curr != null && curr.rightChild != null) {
            return getMaxNode(curr.rightChild);
        }
        return curr;
    }
    private void balancingAfterDelete(Node<V> brother) {
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
    public Integer getTreeHeigh() {
        if (node != null) {
            return node.getBlackHeight();
        }
        return null;
    }
    private void splitTail(Node<V> splitNode, int key, RedBlackTree<V> tree) {
        if (splitNode != null) {
            if (splitNode.key > key) {
                tree.put(splitNode.key, splitNode.value);
                splitTail(splitNode.leftChild, key, tree);

            }
            splitTail(splitNode.rightChild, key, tree);
        }
    }
    private void splitHead(Node<V> splitNode, int key, RedBlackTree<V> tree) {
        if (splitNode != null) {
            if (splitNode.key < key) {
                tree.put(splitNode.key, splitNode.value);
                splitHead(splitNode.rightChild, key, tree);

            }
            splitHead(splitNode.leftChild, key, tree);

        }
    }

}
