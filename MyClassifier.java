import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class MyClassifier {
  // read in a file as a 2d arraylist
  public static ArrayList<ArrayList<Double>> readCSV(String filename){
    BufferedReader br = null;
    String line = "";
    ArrayList<ArrayList<Double>> file = new ArrayList<ArrayList<Double>>();
    try {
      br = new BufferedReader(new FileReader(filename));
      while ((line = br.readLine()) != null) {
        ArrayList<Double> double_line = new ArrayList<Double>();
        String[] str_line = line.split(",");
        // write line to ArrayList
        for (String s:str_line){
          if (s.compareTo("yes")==0){
            double_line.add(1.0);
          }else if (s.compareTo("no")==0){
            double_line.add(0.0);
          }else{
            double_line.add(Double.parseDouble(s));
          }
        }
        file.add(double_line);
      }
    }catch (IOException e){
      e.printStackTrace();
    }
    return file;
  }

  public static void main(String[] args){
    ArrayList<ArrayList<Double>> training = readCSV(args[0]);
    ArrayList<ArrayList<Double>> testing = readCSV(args[1]);

    if (args[2].compareTo("NB")==0){
      for (ArrayList<Double> line : testing){
        // prints determined class
        NB.NB(training, line);
      }
    }else{
      for (ArrayList<Double> line : testing){
        // prints determined class
        int k = Integer.parseInt(String.valueOf(args[2].charAt(0)));
        kNN.kNN(training, line, k);
      }
    }
  }
}
