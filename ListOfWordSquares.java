// This solution uses a trie + DP based approach
// Intuition is that we can form a transpose safe matrix by only choosing accepted string at each row instead of exploring all the strings
// For this we can form a Trie that can be used to search for prefixes and only take that particular string
// If we are able to reach to depth equalling to size of string, we will be able to form a valid matrix
class Solution {

    Trie root = new Trie();
    List<List<String>> result = new ArrayList();
    
    public List<List<String>> wordSquares(String[] words) {
        
        // Build trie
        for(String word:words) {
            insertWord(word);
        }

        // DFS with backtracking
        for(String word:words) {
            dfs(new ArrayList(List.of(word)));
        }
        return result;
    }

    private void insertWord(String word) {
        Trie curr = root;
        // Insert word
        for(char ch:word.toCharArray()) {

            if(curr.children[ch-'a']==null) {
                Trie trie = new Trie();
                curr.children[ch-'a'] = trie;
                
            }
            curr = curr.children[ch-'a'];
            curr.strings.add(word);
        }
    }

    private List<String> getPrefix(String word) {
        Trie curr = root;

        for(char ch:word.toCharArray()) {
            if(curr.children[ch-'a']!=null) {
                curr = curr.children[ch-'a'];
            } else {
                return new ArrayList();
            }

        }
        return curr.strings;
    }

    private void dfs(List<String> words) {
        // base case
        if(words.size()==words.get(0).length()) {
            result.add(new ArrayList(words));
            return;
        }

        int index = words.size();
        // Build prefix
        StringBuilder prefix = new StringBuilder("");
        for(String word: words) {
            prefix.append(word.charAt(index));
        }

        // Fetch list of strings for the prefixes
        List<String> prefixes = getPrefix(prefix.toString());

        for(String match:prefixes) {
            words.add(match);
            dfs(words);
            words.remove(words.size()-1);
        }
    }
}

class Trie {
    Trie[] children;
    List<String> strings;

    public Trie() {
        children = new Trie[26];
        strings = new ArrayList();
    }
}
