
package com.srch;

import java.util.Arrays;

public class BinarySearchExp {
    public static int searchElement(int[] arr, int target) {
        int l = 0;                 // low
        int h = arr.length - 1;    // high

        while (l <= h) {
            int mid = l + (h - l) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                l = mid + 1;
            } else {
                h = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        
        int[] arr1 = {1, 3, 5, 5, 7, 9, 11};
        int target1 = 5;
        int index1 = searchElement(arr1, target1);
        if (index1 != -1) {
            System.out.println("Found " + target1 + " at index: " + index1);
        } else {
            System.out.println("Target " + target1 + " not found");
        }
        
        int[] arr2 = new int[10000];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i + 1; // sorted ascending
        }
        int target2 = 5000;
        int index2 = searchElement(arr2, target2);
        if (index2 != -1) {
            System.out.println("Found " + target2 + " at index: " + index2);
        } else {
            System.out.println("Target " + target2 + " not found");
        }

     
        int[] arr3 = {23, 5, 3, 5, 3, 5, 85, 5};
        Arrays.sort(arr3); 
        int target3 = 5;
        int index3 = searchElement(arr3, target3);
        System.out.println("Sorted arr3: " + Arrays.toString(arr3));
        if (index3 != -1) {
            System.out.println("Found " + target3 + " at index: " + index3 + " (value: " + arr3[index3] + ")");
        } else {
            System.out.println("Target " + target3 + " not found");
        }
    }
}
