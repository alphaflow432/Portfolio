import javax.sound.sampled.Port;
import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {



    String WindowsCsvpathname = "C:\\Users\\Mühle\\Downloads\\export_0.01(8).csv";
    String LinuxCsvpathname = "data/export_0.01(8).csv";
    Portfolio portfolio1 = new Portfolio();

    System.out.println(portfolio1.import_custom_IBKR_portfolio_query(LinuxCsvpathname));

        System.getProperties().list(System.out);
        System.out.println(System.getProperty("os.name"));





    }
}