import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// this program calculate the longest and the second longest words in a file
// that can be constructed by concatenating copies of shorter words
// also found in the file.
// total amount of compound words can also be calculated

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // change file name accordingly
        File file = new File("C:\\Users\\Admin\\Desktop\\words.txt");

        // Tree
        Tree Tree = new Tree();
        LinkedList<Pair<String>> queue = new LinkedList<>();

        // used to calculate the total amount of compound words
        HashSet<String> compoundWords = new HashSet<>();

        // scan the file
        Scanner s = new Scanner(file);

        String word;				// a word
        List<Integer> sufIndices;	// indices of suffixes of a word

        // read words from the file
        // fill up the queue with words which have suffixes, who are
        // candidates to be compound words
        // insert each word in Tree
        while (s.hasNext()) {
            word = s.next();
            sufIndices = Tree.getSuffixesStartIndices(word);

            for (int i : sufIndices) {
                if (i >= word.length())		// if index is out of bound
                    break;					// it means suffixes of the word has
                // been added to the queue if there is any
                queue.add(new Pair<>(word, word.substring(i)));
            }
            Tree.insert(word);
        }

        Pair<String> p;				// a pair of word and its remaining suffix
        int maxLength = 0;			// longest compound word length
        String longest = "";		    // longest compound word
        String sec_longest = "";	// second longest compound word

        while (!queue.isEmpty()) {
            p = queue.removeFirst();
            word = p.second();

            sufIndices = Tree.getSuffixesStartIndices(word);

            // if no suffixes found, which means no prefixes found
            // discard the pair and check the next pair
            if (sufIndices.isEmpty()) {
                continue;
            }


            for (int i : sufIndices) {
                if (i > word.length()) { // sanity check
                    break;
                }

                if (i == word.length()) { // no suffix, means it is a compound word
                    // check if the compound word is the longest
                    // if it is update both longest and second longest
                    // words records
                    if (p.first().length() > maxLength) {
                        sec_longest = longest;
                        maxLength = p.first().length();
                        longest = p.first();
                    }

                    compoundWords.add(p.first());	// the word is compound word

                } else {
                    queue.add(new Pair<>(p.first(), word.substring(i)));
                }
            }
        }

        // print out the results
        System.out.println("Longest Compound Word: " + longest +" - "+longest.length());
        System.out.println("Second Longest Compound Word: " + sec_longest+" - "+sec_longest.length());
        System.out.println("Total Number of Compound Words: " + compoundWords.size());
    }
}