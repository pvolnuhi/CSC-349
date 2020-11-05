import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse; //needed to ask permission. static import
import static org.junit.Assert.fail;
import org.junit.Test;

public class testcases{
	public static void main(String[] args) {
		WgtIntScheduler tester = new WgtIntScheduler();
   
  
   	  	int[] output1 = {1,4,5};
   	  	int[] output2 = {2,4}; /*actually just {2,4} */

   	  	int[] a1 = {4,3,2,10,7};
   	  	int[] a2 = {7,10,6,13,9};
   	  	int[] a3 = {6,6,5,2,8};

   	  	int[] a4 = {3,3,1,10,8};
   	  	int[] a5 = {7,10,4,13,11};
   	  	int[] a6 = {6,9,5,8,10};



   	  	int[] input1 = tester.getOptSet(a1,a2,a3);
      	assertArrayEquals(input1, output1);

      	int[] input2 = tester.getOptSet(a4,a5,a6);
      	assertArrayEquals(input2, output2);
   }
}