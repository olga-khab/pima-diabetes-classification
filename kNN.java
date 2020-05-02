import java.lang.*;
import java.util.*;
public class kNN{
  public static void kNN(ArrayList<ArrayList<Double>> training,
  ArrayList<Double> testing, int k){
    ArrayList<ArrayList<Double>> weight_class = new ArrayList<ArrayList<Double>>();
    // calcualte neighbours and sort them into sort of a priority queue
    for (ArrayList<Double> row:training){
      ArrayList<Double> temp = new ArrayList<Double>();
      temp.add(eucledianDist(row, testing));
      temp.add(row.get(8));
      if (weight_class.size()==0){
        // add first element
        weight_class.add(temp);
      }else{
        for (int i=0; i<weight_class.size(); i++){
          if (weight_class.get(i).get(0)>temp.get(0)){
            weight_class.add(i,temp);
            break;
          }
        }
        // add at the end
        weight_class.add(temp);
      }
    }

    // get k nearest neighbours and see which class
    double class_yes = 0;
    double class_no = 0;
    for (int i=0; i< k; i++){
      double cl = weight_class.get(i).get(1);
      if (cl == 1.0){
        class_yes++;
      }else{
        class_no++;
      }
    }
    if (class_yes>=class_no){
      System.out.println("yes");
    }else{
      System.out.println("no");
    }
  }

  public static double eucledianDist(ArrayList<Double> neighbour, ArrayList<Double> x){
    double sum_sq = 0;
    for (int i=0; i<8; i++){
      sum_sq+=(neighbour.get(i)-x.get(i))*(neighbour.get(i)-x.get(i));
    }
    return Math.sqrt(sum_sq);
  }
}
