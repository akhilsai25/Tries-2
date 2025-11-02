// This solution uses a two pointer approach between each query and pattern
// If both are upper case, compare the characters if they are same or not. If its a non match otherwise increment both
// If pattern is upper case and query is lower, just increment query pointer
// If pattern is lower case and query is lower, compare the characters if they are same or not. If its a non match, continue otherwise increment both
class Solution {
    public List<Boolean> camelMatch(String[] queries, String pattern) {

        List<Boolean> response = new ArrayList();
        for(int i=0;i<queries.length;i++) {
            response.add(checkPattern(queries[i], pattern));
        }
        return response;
    }

    private boolean checkPattern(String query, String pattern) {
        int i=0, j=0;
        for(;i<query.length();i++) {

            if(j==pattern.length()) {
                while(i<query.length() && Character.isLowerCase(query.charAt(i++))) {}
                return i==query.length();
            }

            char a = query.charAt(i);
            char b = pattern.charAt(j);
            
            if(Character.isUpperCase(b)) {
                if(Character.isLowerCase(a)) {
                    continue;
                }
                else if(a!=b) {
                    return false;
                } else if(a==b) {
                    j++;
                }
            } else {
                if(Character.isUpperCase(a)) {
                    return false;
                } else {
                    if(a==b) {
                        j++;
                    } else {
                        continue;
                    }
                }
            }
        }

        return i==query.length() && j==pattern.length();
    }
}
