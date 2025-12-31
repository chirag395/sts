package com.sortings;

import java.util.Arrays;

public class SelectionSort {
	public static void sortElements(int[] arr) {
		int n = arr.length;
		
		for(int i = 0; i < n; i++) {
			int minIndex = i;
			
			for(int j = i + 1; j < n; j++) {
				if(arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}
			
			if(minIndex != i) {
				int t = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = t;
			}
		}
	}

	public static void main(String[] args) {
		
		int[] arr = {5,2,-4,6,12,34,-6};
		
		System.out.println(Arrays.toString(arr));
		
        
	}

}
