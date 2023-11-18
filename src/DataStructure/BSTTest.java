package DataStructure;

public class BSTTest {

    static final class BSTNode {

        int data;
        BSTNode left;
        BSTNode right;

        public BSTNode(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static final class BST {

        private BSTNode root;

        public BST() {
            this.root = null;
        }

        public BSTNode insert(BSTNode node, int data) {
            if (node == null) {
                node = new BSTNode(data);
                return node;
            }

            if (data > node.data) {
                node.right = insert(node.right, data);
            } else {
                node.left = insert(node.left, data);
            }

            return node;
        }

        public BSTNode _insert(BSTNode node, int data) {
            BSTNode newNode = new BSTNode(data);
            BSTNode runningNode = node;
            BSTNode copyRunningNode = null;
            
            while (runningNode != null) {
                copyRunningNode = runningNode;
                if (data > runningNode.data) {
                    runningNode = runningNode.right;
                } else {
                    runningNode = runningNode.left;
                }
            }

            // out of the loop
            // runningNode = null
            // copyRunningNode is previous of runningNode

            if (copyRunningNode == null) {
                copyRunningNode = newNode;
            } else if (data > copyRunningNode.data) {
                copyRunningNode.right = newNode;
            } else {
                copyRunningNode.left = newNode;
            }

            return copyRunningNode;
        }

        public void print(BSTNode node, String prefix) {
            if (node == null) return;
            print(node.left, " " + prefix);
            System.out.println(prefix + "--" + node.data);
            print(node.right, " " + prefix);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        BSTNode root = null;
        root = bst.insert(root, 3);
        root = bst.insert(root, 5);

        bst.print(root, " ");
    }
}
