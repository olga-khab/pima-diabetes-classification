import java.util.*;
import java.io.*;
import java.lang.Math.*;

public class MyClassifier {
  // read in a file as a 2d arraylist
  public static ArrayList<ArrayList<double>> readCSV(String filename){
    BufferedReader br = null;
    String line = "";
    ArrayList<ArrayList<double>> file = new ArrayList<ArrayList<double>>();
    try {
      br = new BufferedReader(new FileReader(filename));
      while ((line = br.readLine()) != null) {
        ArrayList<double> double_line = new ArrayList<double>()
        String[] str_line = line.split(",");
        // write line to ArrayList
        for (String s:str_line){
          double_line.add(Double.parseDouble(s));
        }
        file.add(double_line);
      }
    }catch (IOException e){
      e.printStackTrace();
    }
    return file;
  }

  public static void main(String[] args){
    ArrayList<ArrayList<double>> training = readCSV(args[0])
    ArrayList<ArrayList<double>> testing = readCSV(args[1])

    if (args[2].compareTo("NB")){
      for (ArrayList<double> line : testing){
        // prints determined class
        NB.NB(training, line);
      }
    }else{
      //kNN.kNN(training, testing, Integer.parseInt(args[2].charAt(0)));
    }
  }
}
