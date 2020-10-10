import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Stack;
import java.util.List;

public class Inversions{

	public Inversions(){

	}

	public static int invCounter(int[] data){
		return mergeElements(data);

	}

	public static int mergeElements(int[] elements){
		//first seperate lists into 2 seperate lists
		//recurse through each of the 2 lists 
		//add 1 for every time a_i > a_j
		int inversions = 0;
		int length = elements.length;

		if(length > 1){
			int middle = (length/2);
			int[] left = new int[middle];
			left = Arrays.copyOfRange(elements, 0, middle);

			int[] right = new int[length - middle];
			right = Arrays.copyOfRange(elements, middle, length);

			inversions += mergeElements(left);
			inversions += mergeElements(right);
			inversions += mergeInversionCount(left, right, elements);
		}
		return inversions;
	}

	public static int mergeInversionCount(int[] left, int[] right, int[] elements){
		int inversionCount = 0;
		int a_i = 0;
		int a_j = 0;
		int num = 0;

		while(a_i < left.length && a_j < right.length){
			if(left[a_i] < right[a_j]){
				//nothing happens, because numbers are in correct (ascending) order from left to right 
				elements[num] = left[a_i];
				a_i +=1;
			}
			else{
				//if in incorrect order, mark it and count as inversion count
				elements[num] = right[a_j];	
				a_j +=1;
				inversionCount += (left.length - a_i);
			}
			++num;
		}
		while (a_i < left.length){
			elements[num] = left[a_i];
			a_i += 1;
			num += 1;
		}
		while (a_j < right.length) {
			elements[num] = right[a_j];
			a_j += 1;
			num += 1;
		}
		return inversionCount;
	}

}