package com.examples;

public class Exp16 {

	public static void main(String[] args) {
		int[][] arr = new int[3][3];
		arr[0][0] = 101;
		
		int[][] arr1 = {{101,102,103}, {212,412,342}, {253,545,126}};
		
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr1.length; j++) {
				System.out.print(arr1[i][j] + " ");
			}
			
			System.out.println();
		}
		
	}

}
