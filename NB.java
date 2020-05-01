import java.lang.*;
import java.util.*;
public class NB{
  public static void NB(ArrayList<ArrayList<Double>> training,
  ArrayList<Double> testing){
    ArrayList<ArrayList<Double>> class_yes = new ArrayList<ArrayList<Double>>();
    ArrayList<ArrayList<Double>> class_no = new ArrayList<ArrayList<Double>>();
    for (ArrayList<Double> row : training){
      if (row.get(8)==1.0){
        class_yes.add(row);
      }else{
        class_no.add(row);
      }
    }
    double p_yes = p_class_E(class_pdf(class_yes, testing));
    double p_no = p_class_E(class_pdf(class_no, testing));
    //System.out.println(p_yes);
    //System.out.println(p_no);
    if (p_yes>=p_no){
      System.out.println("yes");
    }else{
      System.out.println("no");
    }
  }


  public static double mean(ArrayList<Double> vals){
    double sum = 0;
    for (double e : vals){
      sum+=e;
    }
    return sum/vals.size();
  }

  public static double stdev(ArrayList<Double> vals){
    double mean = mean(vals);
    double sum_sq = 0;
    for (double e : vals){
      sum_sq+=(e-mean)*(e-mean);
    }
    return Math.sqrt(sum_sq/(vals.size()-1));
  }

  public static double calculatePdf(double x, ArrayList<Double> vals){
    double stdev = stdev(vals);
    double mean = mean(vals);
    //System.out.println("pdf: ");
    //System.out.println((x-mean));
    //System.out.println((Math.exp(-(x-mean)*(x-mean)/(2*stdev*stdev)))/(stdev*Math.sqrt(2*Math.PI)));
    return ((Math.exp(-(x-mean)*(x-mean)/(2*stdev*stdev)))/(stdev*Math.sqrt(2*Math.PI)));
  }

  public static double p_class_E(ArrayList<Double> pdfs){
    double product=1;
    for (double e:pdfs){
      product*=e;
    }
    return product;
  }

  public static ArrayList<Double> class_pdf(ArrayList<ArrayList<Double>> class_rows,
  ArrayList<Double> testing){
    ArrayList<Double> pdf = new ArrayList<Double>();
    // for each class calculate P(class|E)
    for (int i=0; i< 8; i++){ // iterate through each column
      ArrayList<Double> col = new ArrayList<Double>();
      for (int j=0;j<class_rows.size();j++){
        col.add(class_rows.get(j).get(i));
      }
      // for the column and testing value, clculate pdf
      pdf.add(calculatePdf(testing.get(i),col));
    }
    return pdf;
  }
}
