import javax.sound.sampled.Port;
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {



    String csvpathname = "C:\\Users\\Mühle\\Downloads\\export_0.01(8).csv";

    Portfolio portfolio1 = new Portfolio();

    System.out.println(portfolio1.import_custom_IBKR_portfolio_query(csvpathname));

    List<String> testlist = new ArrayList<>();
    testlist.add("5BUS");
    testlist.add("QQQS");
    testlist.add("SEU3");
    testlist.add("SJP3");
    portfolio1.set_symbols_to_true_shorts(testlist);
    portfolio1.calculate_main_metrics();
    portfolio1.print_metrics();
    portfolio1.print_category_weights();





    }
}