import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSV_Reader {

    List<String> Csv_to_List(String pathname) {


        //String csvpathname = "C:\\Users\\Mühle\\Downloads\\export_0.01(5).csv";
        String csvpathname = pathname;
        String line = "";
        final String delimiter = ",";
        List<String> poslist = new ArrayList<String>() {};
        try {
            String filePath = csvpathname;
            FileReader fileReader = new FileReader(filePath);

            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null)   //loops through every line until null found
            {

                String[] token = line.split(delimiter);    // separate every token by comma


                poslist.add(line.replace("\"", ""));

            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return poslist;

    }






}
