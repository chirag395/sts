package com.srch;

public class LinearSearchExp {
	public static int searchElement(int arr[], int target) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == target) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		
		int arr[] = {23,5,3,5,3,5,85,5};
		
		int target = 5;
		
		int index = searchElement(arr, target);
		
		if(index != -1) {
			System.out.println("Target element is: " + arr[index] + " at index: " + index);
		}
		else {
			System.out.println("Target value not found");
		}
	}

}


