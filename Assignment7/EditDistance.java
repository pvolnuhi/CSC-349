import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.lang.Math.*;

public class EditDistance{

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length < 1)
			System.out.println("Not a valid input file\n");
		/*take in a filename as args*/
		boolean optAlign = false;
		String filename = args[0];
		Scanner scanner = new Scanner(new File(filename));
		String str1 = scanner.nextLine();
		String str2 = scanner.nextLine();
		/*seperate whenever encountering a space*/
		dynamicEditDistance(str1.split(""), str2.split(""), optAlign);
	}

	public static void dynamicEditDistance(String[] str1, String[] str2, boolean optAlign){
		int str1Length = str1.length + 1;
		int str2Length = str2.length + 1;
		/*state the two input string parameters, tracking their size and 
		adding an extra space for storage */
		int editDist = 0;

		/*initiate table that stores the size of the two input strings */
		/*initialize first row & column of the table
		empty gene against non empty gene cost = 2*length of nonempty gene*/
		
		int[][] storage = new int[str1Length][str2Length];
		for(int i=0; i<str2Length; i++){
			storage[i][0]=2*i;
		}
		for(int j=0; j<str1Length; j++){
			storage[0][j]=2*j;
		}
		for(int i=1; i<str2Length; i++){
			/* its recurrence relation is ed(i,j)=ed(i-1,j-1)*/
			for(int j=1; j<str1Length; j++){
				if (str2[i - 1].equals(str1[j - 1])) {
					storage[i][j] = storage[i - 1][j - 1];
				}
				else{
					/*int a=Math.min(storage[i - 1][j - 1] + 1, Math.min(storage[i - 1][j] + 2), storage[i][j - 1] + 2);*/
					/*b=Math.min(storage[i - 1][j] + 2);
					c=storage[i][j - 1] + 2;*/
					storage[i][j]=Math.min(storage[i - 1][j - 1] + 1, Math.min(storage[i - 1][j] + 2, storage[i][j - 1] + 2));
				}
			}
		}
		editDist=storage[str2Length-1][str1Length-1];
		/*The first line should contain the edit distance, 
		 *preceded by the text "Edit distance = ".*/
		System.out.printf("Edit distance = %d\n", editDist);

		if (optAlign){
			int max = str1Length+str2Length-2;
			String[] backTrace = new String[max];
			int btIdx = max-1;
			String cntr="";
			int i=str2Length-1;
			int j=str1Length-1;
		/* backtracing begins with # of rows in alignment = # of chars */
		
			while(i+j>1){
				if (storage[i][j] == storage[i - 1][j - 1] && str2[i - 1].equals(str1[j - 1])) {
					cntr = str1[j - 1] + str2[i - 1];
					backTrace[btIdx] = cntr;
					i=- 1;
					j=- 1;
					btIdx=-1;
				}
				else if (storage[i][j] == storage[i][j - 1] + 2) {
					cntr = str1[j - 1];
					backTrace[btIdx] = cntr;
					j=-1;
					btIdx=-1;
				}
				else if (storage[i][j] == storage[i - 1][j] + 2) {
					cntr = str2[i - 1];
					backTrace[btIdx] = cntr;
					i=-1;
					btIdx=-1;
				}
				else if (storage[i][j] == storage[i - 1][j - 1] + 1) {
					cntr = str1[j - 1] + str2[i - 1];
					backTrace[btIdx] = cntr;
					i=- 1;
					j=- 1;
					btIdx=-1;
				} 	
			}
			while(i+j!=0){
				if(i>j){
					cntr = str2[i - 1];
					backTrace[btIdx]=cntr;
					btIdx=-1;
					i=-1;
				}
				if(j>i){
					cntr = str1[j - 1];
					backTrace[btIdx]=cntr;
					btIdx=-1;
					j=-1;
				}
			}
			for (btIdx = btIdx + 1; btIdx < max; btIdx++) {
				System.out.println(backTrace[btIdx]);
			}
		}
	}

}