import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;

public class WgtIntScheduler {
    public static void swapWgts(int[] stime, int[] ftime, int[] weight){
        int time = ftime.length + 1; /*want to know the total duration */
        ArrayList<Integer> totalJobs = new ArrayList<Integer>();
        int[] table = new int[time];
        int[] convertArray = new int[time];
        for (int i = 0; i < ftime.length; i++) { //sort by ftime in ascending order
            for (int j = i; j < ftime.length; j++) {
                if (ftime[j] < ftime[i]) {
                    int temp;
                    temp = convertArray[j + 1];
                    convertArray[j + 1] = convertArray[i + 1];
                    convertArray[i + 1] = temp;
                    temp = ftime[j];
                    ftime[j] = ftime[i];
                    ftime[i] = temp;
                    temp = stime[j];
                    stime[j] = stime[i];
                    stime[i] = temp;
                    temp = weight[j];
                    weight[j] = weight[i];
                    weight[i] = temp;
                }
            }
        }
    }
    
    public static int[] getOptSet(int[] stime, int[] ftime, int[] weight) {
        int time = ftime.length + 1; /*want to know the total duration */
        ArrayList<Integer> totalJobs = new ArrayList<Integer>();
        int[] jobs;
        int[] convertArray = new int[time];
        for (int i = 0; i < time; i++) {
            convertArray[i] = i; //convert the tiem duration into an array format WgtIntScheduler.java.
        }

        int[] table = new int[time];
        for (int i = 0; i < ftime.length; i++) { //sort by ftime in ascending order
            for (int j = i; j < ftime.length; j++) {
                if (ftime[j] < ftime[i]) {
                    int temp;
                    temp = convertArray[j + 1];
                    convertArray[j+1] = convertArray[i+1];
                    convertArray[i+1] = temp;
                    temp = ftime[j];
                    ftime[j] = ftime[i];
                    ftime[i] = temp;
                    temp = stime[j];
                    stime[j] = stime[i];
                    stime[i] = temp;
                    temp = weight[j];
                    weight[j] = weight[i];
                    weight[i] = temp;
                }
            }
        }

        for (int i = 1; i < table.length; i++) {
            int compatible = i;
            while (compatible > 0) {
                if (ftime[compatible - 1] <= stime[i - 1]) {
                    break;
                } else {
                    compatible--;
                }
            }
            table[i] = Math.max(weight[i - 1] + table[compatible], table[i - 1]);
        }

        for (int i = table.length - 1; i > 0; i--) {
            if (table[i] > table[i - 1]) {
                totalJobs.add(convertArray[i]);
                int compatible = i;
                while (compatible > 0) {
                    if (ftime[compatible - 1] <= stime[i - 1]) {
                        break;
                    } else {
                        compatible--;
                    }
                }
                i = compatible + 1;
            }
        }
    
        totalJobs.sort(null);
        jobs = new int[totalJobs.size()];
        for (int i = 0; i < jobs.length; i++) {
            jobs[i] = totalJobs.get(i);
        }
        return jobs;
    }

    

    /*public static void buildTable(WgtIntScheduler[] jobs, int[] compatible){ 
        for (int i = 1; i < table.length; i++) {
            // find latest job compatible with job i
            int compatible = i;
            while (compatible > 0) {
                if (ftime[compatible - 1] <= stime[i - 1]) {
                    break;
                } else {
                    compatible--;
                }
            }
            // compatible is the latest job compatible with job i. may be 0
            table[i] = Math.max(weight[i - 1] + table[compatible], table[i - 1]);
        }
        // traceback
        for (int i = table.length - 1; i > 0; i--) {
            // if table[i] is less than table[i-1], job selected
            // set new i
            if (table[i] > table[i - 1]) {
                totalJobs.add(convertArray[i]);
                // set i to latest compatible job
                int compatible = i;
                while (compatible > 0) {
                    if (ftime[compatible - 1] <= stime[i - 1]) {
                        break;
                    } else {
                        compatible--;
                    }
                }
                // set i to compatible +1 to deal with the i--
                i = compatible + 1;
            }
        }
    }
    */



   /* public static void main(String[] args) {
    
        int[] a1 = {4,3,2,10,7};
        int[] a2 = {7,10,6,13,9};
        int[] a3 = {6,6,5,2,8};

        int[] input = getOptSet(a1,a2,a3);
        System.out.println(input);
    } */
}