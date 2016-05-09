class Node{
    public int value;
    public Node left; //less
    public Node right; //greater
    public Node(int x){
        value = x;
        left = null;
        right = null;
    }
}

class BinaryTree{
    Node root;

    /**
     * Initializes BST
     */
    public BinaryTree(){//init.
        this.root = null;
    }

    /**
     * Inserts a value into a BST
     * Since a binary search tree cannot have duplicate values, it will ignore a given value if it already exists
     *
     * @param n_value value to be inserted
     */
    public void insert(int n_value) {
        Node n_Node = new Node(n_value);
        Node pos = root;
        //Node parent = null;

        if (pos == null) {
            root = n_Node;
            return;
        }

        if(keySearch(n_value)){
            return;//already exists in tree, BST can have no duplicates
        }

        while (true) {
            if (n_value < pos.value) {
                if (pos.left == null) {
                    pos.left = n_Node;
                    return;
                }
                pos = pos.left;
            } else { //if(n_value > pos.value) is what's happening here
                if (pos.right == null) {
                    pos.right = n_Node;
                    return;
                }
                pos = pos.right;
            }
        }
    }

    /**
     * Deletes a node with the given value in a tree.
     * Searches for the node containing key, then removes it from the tree.
     * @param key Node containing this key is to be deleted.
     */
    public void delete(int key){
        Node pos = root;
        Node parent = pos;

        while(pos.value != key){//advance to position to delete
            parent = pos;
            pos = pos.value > key ? pos.left : pos.right;

            if(pos == null){
                return;
            }
        }
        /*No children on deletion node*/
        if(pos.left == null && pos.right == null){
            if(pos == root) {
                root = null;
            } else if (parent.left.value == pos.value){
                parent.left = null;
            } else {
                parent.right = null;
            }
        /*One child from node to be deleted*/
        } else if(pos.left == null ^ pos.right == null) {
            parent.left = (parent.left.value == pos.value) ? pos.right : parent.left;
            parent.right = (parent.right.value == pos.value) ? pos.right : parent.right;

        /*Two children from node to be deleted*/
        /*Go to min of rightmost child*/
        } else {
            Node r_tree = pos.right;
            /*Get min value*/
            while(r_tree.left != null){
                r_tree = r_tree.left;
            }

            parent.left = (parent.left.value == pos.value) ? r_tree : parent.left;
            parent.right = (parent.right.value == pos.value) ? r_tree : parent.right;

            r_tree.left = pos.left;
        }
    }

    /**
     * Searches for a given integer in a BST
     * @param key Int value to search for
     * @return  TRUE - Exists in the tree
     *          FALSE - Does not exist in the tree
     */
    public boolean keySearch(int key){
        Node pos = root;
        while(pos != null){
            if(pos.value == key)
                return true;
            pos = pos.value > key ? pos.left : pos.right;
        }
        return false;
    }

    /**
     * Prints all the values in a BST to stdout
     */
    public void printBST(){
        printHelp(root);
        System.out.println();
    }
    /*
    Recursive helper for printBST, just does a simple inorder traversal.
    Could make iterative too I suppose.
     */
    private void printHelp(Node pos){
        if(pos != null){
            printHelp(pos.left);
            System.out.print(pos.value + " ");
            printHelp(pos.right);
        }
    }

}

public class Main {

    /**
     * Was unsure if we needed to make a main class/method for submission here.
     *
     */
    public static void main(String[] args) {
	    int[] test = {3, 5, 2, 1, 6, 8, 4, 7, 9};

        BinaryTree tree = new BinaryTree();
        for(int i : test){
            tree.insert(i);
        }

        System.out.println(tree.keySearch(3));
        tree.printBST();
        tree.delete(5);
        tree.printBST();
        tree.insert(5);
        tree.printBST();
        tree.delete(4);
        tree.printBST();

    }
}
