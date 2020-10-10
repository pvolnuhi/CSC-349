import java.util.*;
import java.io.*;

public class MyGraphSampleTest {

   public static void main(String[] args) {
      MyGraph myGraph = new MyGraph();
      boolean isBipartite;
      ArrayList<HashSet<Integer>> connectCheckResult;


      try {
         System.out.println("\n-------Test 1--------");
         myGraph.readfile_graph("Bfstest1.txt");
         isBipartite = myGraph.bipartiteCheck();
         connectCheckResult = myGraph.connectCheck();
         System.out.println(isBipartite);
         System.out.println(connectCheckResult);

      }
      catch (FileNotFoundException e) {
         System.out.print("FILE NOT FOUND");
      }
      
   }
}
