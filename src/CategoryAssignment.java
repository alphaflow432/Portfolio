import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CategoryAssignment {

    Collection<String> longCategories = new HashSet<>();
    Collection<String> shortCategories = new HashSet<>();
    HashMap<String,String[]> assignmentList = new HashMap<>();

    public String assignFromConfig(List<Position> positions){
        String AssignmentsFileName = "config\\CategoriesAssignments.csv";
        String results = "";


        final String delimiter = ",";
        String line;
        try {
            String filePath = AssignmentsFileName;
            FileReader fileReader = new FileReader(AssignmentsFileName);

            BufferedReader reader = new BufferedReader(fileReader);
            while ((line = reader.readLine()) != null) {
                String[] token = line.split(delimiter);
                String[] returnTokenField = {token[1],token[2]};
                assignmentList.put(token[0],returnTokenField);

            }
        }
        catch(Exception e){
            return e.getMessage();
        }

        for (Position pos : positions){
            if(assignmentList.containsKey(pos.getSymbol())){
                try{
                    String type = assignmentList.get(pos.getSymbol())[0];
                    String category = assignmentList.get(pos.getSymbol())[1];
                    boolean isValidCategory = longCategories.contains(category) || shortCategories.contains(category);
                    if(type == "Long" && isValidCategory){
                        pos.setCategory(category);
                        pos.setIs_true_short(false);
                    }
                    else if(type.equals("Short") && isValidCategory){
                        pos.setCategory(category);
                        pos.setIs_true_short(true);
                    }
                }
                catch (Exception e){
                    System.out.println(pos.getSymbol()+" Assignment went wrong");
                    return (e.getMessage());

                }
            }

        }
        return "Everything went right";
    }

    public void loadCategoriesFromConfigFolder(){
        String shortCategoriesFileName = "C:\\Users\\Mühle\\IdeaProjects\\Portfolio\\config\\ShortCategories.csv";
        String longCategoriesFileName = "config\\LongCategories.csv";

        // Reading in Short categories
        CSV_Reader temp_reader = new CSV_Reader();
        String temp_short_categories = temp_reader.csv_first_row_to_String(shortCategoriesFileName);
        Collections.addAll(this.shortCategories, temp_short_categories.split(","));

        // Reading in Long categories
        String temp_long_categories = temp_reader.csv_first_row_to_String(longCategoriesFileName);
        Collections.addAll(this.longCategories, temp_long_categories.split(","));










    }
    HashMap<String, String> getSavedAssignments(){
        //TODO load saved assignments
        return null;
    }
    void saveAssignments(){
        //TODO save assignments
    }

}
