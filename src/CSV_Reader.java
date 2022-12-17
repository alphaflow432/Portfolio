import java.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSV_Reader {


    List<String> Csv_to_String_List(String pathname) {
        /**
         *
         * @param pathname of the csv file
         * @return A List of Rows, each as one String delimited by ","
         */

        // Its supposed to REMOVE Double Quotes !



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

                System.out.println(token[0] + " | " + token[1] + " | " + token[2] + " | " + token[3]);
                // replacing double quotes !
                poslist.add(line.replace("\"", ""));

            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return poslist;

    }


    /**
     * @param pathname path of the csv file
     * @return return s the first row of the csv file as one String
     *          if not returns empty  string
     */
    public String csv_first_row_to_String(String pathname){
        String line1 = "";
        try{
            FileReader fileReader = new FileReader(pathname);
            BufferedReader reader = new BufferedReader(fileReader);
            line1 = reader.readLine();

        }
        catch (IOException e){
            e.printStackTrace();
        }
        return line1;

    }









}
