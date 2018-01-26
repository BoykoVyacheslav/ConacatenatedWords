import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// Helper data structure for class LongestCompoundWord
// each node, which contains a letter as its value,
// in Tree may have a list of children nodes
// Tree is also able to find all suffixes indices of a word

 class Tree {

    // inner class, only for the use of Tree
    private class TrieNode {
        @SuppressWarnings("unused")
        private char val;			// character stored in the node
        private HashMap<Character, TrieNode> children;	// map name of string to the node
        // which has the string as value
        private boolean isWord;		// if the node is at the end of a word

        // constructor
        TrieNode(char val) {
            this.val = val;
            children = new HashMap<>();
            isWord = false;
        }

        // add child to treenode
        void addChild(char child) {
            children.put(child, new TrieNode(child));
        }

        // get child of treenode that has the same character as the give one
        TrieNode getChild(char child) {
            if (!children.keySet().contains(child)) {
                return null;
            }

            return children.get(child);
        }

        // return true if child exists
        boolean containsChild(char child) {
            return children.keySet().contains(child);
        }
    }

    private TrieNode root;
    private TrieNode curr;

    Tree() {
        root = new TrieNode(' ');	// root
        curr = root;
    }

    // insert a word to Tree
    void insert(String s) {
        char letter;
        curr = root;

        // traverse every letter of a word
        // update Tree if a letter is not in the structure
        for (int i = 0; i < s.length(); i++) {
            letter = s.charAt(i);

            if (!curr.containsChild(letter)) {
                curr.addChild(letter);
            }

            curr = curr.getChild(letter);
        }

        // mark last letter as the end of a word
        curr.isWord = true;
    }

    // return starting indices of all suffixes of a word
    List<Integer> getSuffixesStartIndices(String s) {
        List<Integer> indices = new LinkedList<>();	// store indices
        char letter;
        curr = root;	// start from root

        for (int i = 0; i < s.length(); i++) {
            letter = s.charAt(i);

            // if the current letter doesn't have one letter as child
            // which means Tree currently doesn't have the relationship
            // returns indices of suffixes
            if (!curr.containsChild(letter))
                return indices;

            // move on to the child node
            curr = curr.getChild(letter);

            // if the letter is an end to a word, it means after the letter is a suffix
            // update indices
            if (curr.isWord)
                indices.add(i + 1);
        }

        return indices;
    }

}
