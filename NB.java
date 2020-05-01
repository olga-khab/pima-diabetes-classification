import java.lang.*;
public class NB{
  public static void NB(ArrayList<ArrayList<double>> training,
  <ArrayList<double> testing){
    ArrayList<ArrayList<double>> class_yes = new ArrayList<ArrayList<double>>();
    ArrayList<ArrayList<double>> class_no = new ArrayList<ArrayList<double>>();
    for (ArrayList<double> row : training){
      if row.get(8).compareTo("yes"){
        class_yes.add(row);
      }else{
        class_no.add(row);
      }
    }
    double p_yes = p_class_E(class_pdf(class_yes));
    double p_no = p_class_E(class_pdf(class_no));
    if (p_yes>=p_no){
      System.out.println("yes");
    }else{
      System.out.println("no");
    }
  }


  public static double mean(ArrayList<double> vals){
    double sum = 0;
    for (double e : vals){
      sum+=e;
    }
    return sum/vals.size();
  }

  public static double stdev(ArrayList<double> vals){
    double mean = mean(vals);
    double sum_sq = 0;
    for (double e : vals){
      sum_sq+=(e-mean)*(e-mean);
    }
    return Math.sqrt(sum_sq/(vals.size()-1));
  }

  public static double calculatePdf(double x, ArrayList<double> vals){
    double stdev = stdev(vals);
    double mean = mean(vals);
    return (Math.exp(-(x-mean)/(2*stdev*stdev)))/(stdev*Math.sqrt(2*Math.pi));
  }

  public static double p_class_E(ArrayList<double> pdfs){
    double product=1;
    for (double e:pdfs){
      product*=e;
    }
    return product;
  }

  public static ArrayList<double> class_pdf(ArrayList<ArrayList<double>> class){
    ArrayList<double> pdf = new ArrayList<double>();
    // for each class calculate P(class|E)
    for (int i=0; i< 8; i++){ // iterate through each column
      ArrayList<double> col = new A<ArrayList<double>();
      for (int j=0;j<class.size();j++){
        col.add(class.get(j).get(i));
      }
      // for the column and testing value, clculate pdf
      pdf.add(calculatePdf(testing.get(i),col));
    }
    return pdf;
  }
}
