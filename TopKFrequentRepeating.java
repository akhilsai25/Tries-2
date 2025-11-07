// This solution uses approch to divide each element into its count based groups and them gathering the required number of top frequent elements
// First we create a map with number to count relation
// Then we create a separate map to have count to list of numbers relation
// Then we iterate from max frequent to min and gather until the k elements is reached
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> numberToCount = new HashMap();
        int max = 0, min=1;
        for(int i:nums) {
            numberToCount.put(i, numberToCount.getOrDefault(i, 0)+1);
            max = Math.max(max, numberToCount.get(i));
            min = Math.min(min, numberToCount.get(i));
        }

        HashMap<Integer, List<Integer>> countToNumbers = new HashMap();
        for(int i:numberToCount.keySet()) {
            int count = numberToCount.get(i);
            if(!countToNumbers.containsKey(count)) {
                countToNumbers.put(count, new ArrayList());
            }
            countToNumbers.get(count).add(i);
        }

        List<Integer> list = new ArrayList();
        for(int i=max;i>=min;i--) {
            if(!countToNumbers.containsKey(i)) continue;
            List<Integer> countList = countToNumbers.get(i);
            if(countList.size()==k) {
                list.addAll(countList);
                break;
            } else if(countList.size()<k) {
                list.addAll(countList);
                k-=countList.size();
            }
            if(k==0) break;
        }
        int[] response = new int[list.size()]; int i=0;
        for(int j:list) {
            response[i++] = j;
        }
        return response;
    }
}
