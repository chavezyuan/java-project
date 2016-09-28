package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
	
	/**********************/
	public int[] twoSum(int[] nums, int target) {
		int[] result = {-1, -1};
		Map<Integer, Integer> map = new HashMap<>();
		for(int i=0;i<nums.length;i++) {
			if(map.containsKey(nums[i])) {
				result[0] = map.get(nums[i]);
				result[1] = i;
				break;
			} else {
				map.put(target-nums[i], i);
			}
		}
		return result;
    }
	
	private void quickSort(int[] nums, int start, int end, int[] nPos) {
		if(start >= end)
			return;
		int pos = start;
		for(int i=start; i<end; i++) {
			if(nums[i] < nums[end]) {
				if(i != pos) {
					int temp = nums[i];
					nums[i] = nums[pos];
					nums[pos] = temp;
					nPos[i] = nPos[i] - nPos[pos];
					nPos[pos] = nPos[i] + nPos[pos];
					nPos[i] = nPos[pos] - nPos[i];
				}
				pos ++;
			}
		}
		if(end != pos) {
			int temp = nums[end];
			nums[end] = nums[pos];
			nums[pos] = temp;
			nPos[end] = nPos[end] - nPos[pos];
			nPos[pos] = nPos[end] + nPos[pos];
			nPos[end] = nPos[pos] - nPos[end];
		}
		quickSort(nums, start, pos-1, nPos);
		quickSort(nums, pos+1, end, nPos);
	}
	
	
	/*************************/
	public int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0) return 0;
        TreeSet<Integer> set = new TreeSet<>();
        Set<Character> charSet = new HashSet<>();
        for(int i=0; i<s.length(); i++) {
            int j=1;
            charSet.add(s.charAt(i));
            while(i+j<s.length() && !charSet.contains(s.charAt(i+j))){
            	charSet.add(s.charAt(i+j));
                j++;
            }
            set.add(j);
            charSet.clear();
        }
        return set.last();
    }
	
	/******************************/
	private int binSearch(int[] nums, int begin, int end, int target) {
		int mid = 0;
		int ret = -1;
		while(begin <= end) {
			mid = (begin + end)/2;
			if(target < nums[mid]) {
				end = mid - 1;
			} else if(target > nums[mid]) {
				begin = mid + 1;
				ret = mid;
			} else {
				return mid;
			}
		}
		if(ret == -1)
			ret = mid - 1;
		return ret;
	}
	private int getNextElement(int[] nums1, int ia, int[] nums2, int ib) {
		int alen = nums1.length;
		int blen = nums2.length;
		int ic = -1;
		if(ia != -1) {
			ic = ia + 1 + binSearch(nums2, 0, blen-1, nums1[ia]);
		} else if(ib != -1) {
			ic = ib + 1 + binSearch(nums1, 0, alen-1, nums2[ib]);
		}
		return 0;
	}
	
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int alen = nums1.length;
		int blen = nums2.length;
		int clen = alen + blen;
		int cmid = (clen-1)/2;
		
		int bbegin = 0, bend = blen-1;
		int abegin = 0, aend = alen-1;
		int amid = -1, bmid = -1;
		int ca = -1, cb = -1;
		while (abegin <= aend || bbegin <= bend) {
			if(abegin <= aend) {
				amid = (abegin + aend)/2;
				int temp = binSearch(nums2, 0, blen-1, nums1[amid]);
				ca = amid + 1 + temp;
				if(cmid > ca) {
					abegin = amid + 1;
				} else if(cmid < ca) {
					aend = amid - 1;
				} else {
					break;
				}
			}
			if(bbegin <= bend) {
				bmid = (bbegin + bend)/2;
				int temp = binSearch(nums1, 0, alen-1, nums2[bmid]);
				cb = bmid + 1 + temp;
				if(cmid >cb) {
					bbegin = bmid + 1;
				} else if(cmid < cb){
					bend = bmid - 1;
				} else {
					break;
				}
			}
		}
		double median = -1;
		if(ca == cmid) {
			if(clen%2 == 0) {
				median = nums1[amid];
				int ba = binSearch(nums2, 0, blen-1, (int)median);
				if(ba + 1 >= blen) {
					median = (median + nums1[amid+1])/2;
				} else {
					if(amid+1 >= alen) {
						median = (median + nums2[ba+1]);
					} else {
						if(nums2[ba+1] > nums1[amid+1]) {
							median = (median + nums1[amid+1]);
						} else {
							median = (median + nums2[ba+1]);
						}
					}
				}
			} else {
				median = nums1[amid];
			}
		} else if(cb == cmid){
			if(clen%2 == 0) {
				median = nums2[bmid];
				int ab = binSearch(nums1, 0, alen-1, (int)median);
				if(ab + 1 >= alen) {
					median = (median + nums2[bmid+1])/2;
				} else {
					if(bmid+1 >= blen) {
						median = (median + nums1[ab+1]);
					} else {
						if(nums1[ab+1] > nums2[bmid+1]) {
							median = (median + nums2[bmid+1]);
						} else {
							median = (median + nums1[ab+1]);
						}
					}
				}
			} else {
				median = nums2[bmid];
			}
		} else {
			System.out.println("wrong calculation");
		}
		
		return median;
    }

    public String reverseString(String s) {
        StringBuilder ret = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            ret.append(s.charAt(s.length()-i-1));
        }
        return ret.toString();
    }

    public boolean canWinNim(int n) {
        return n%4 != 0;
    }

    public int getSum(int a, int b) {
        int c = a&b;
        if(c == 0) {
            return a^b;
        } else {
            return getSum(a^b, c<<1);
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution s = new Solution();
		/*1. Tow Sum*/
//		int[] nums = {2,7,3,0};
//		int target = 9;
//		printArr(s.twoSum(nums, target));
		
		/*2. lengthOfLongestSubstring*/
//		String ss = "acdbaertyuibbbb";
//		System.out.println(s.lengthOfLongestSubstring(ss));
		
		/*3. */
//		int[] nums1 = {1,2,3,4,5,6,7};
//		int[] nums2 = {0,1,2,3,4};
//		System.out.println(s.findMedianSortedArrays(nums1, nums2));


//        System.out.println(s.reverseString("abcdefg"));
        System.out.println(s.getSum(13, 13988));
    }

	private static void printArr(int[] arr) {
		System.out.print("[ ");
		for(int i=0; i<arr.length; i++) {
			if(i == arr.length-1)
				System.out.print(arr[i]);
			else
				System.out.print(arr[i] + ", ");
		}
		System.out.println(" ]");
	}
}
