import java.util.*;
public class Portfolio {



    public List<Position> getPositions() {
        return positions;
    }



    List<Position> positions;           // Vielleicht HashMap ?
    public double net_liquid(){
        return long_summary() - short_summary();
    }
    String name;

    public double long_summary(){
        double summary = 0.0;
        for (Position pos : positions){
            if(!pos.is_true_short){
                summary += Math.abs(pos.position_value);
            }
        }
        return summary;
    }
    public double short_summary(){
        double summary = 0.0;
        for (Position pos : positions){
            if(pos.is_true_short){
                summary += Math.abs(pos.position_value);
            }
        }
        return summary;
    }
    public double gross_exposure(){
        return short_summary() + long_summary();
    }
    public double net_exposure(){
        return (long_summary() - short_summary());
    }

    public double short_ratio(){

        return short_summary()/gross_exposure();
    }
    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }



    public void print_metrics(){
        System.out.println();
        System.out.println("net liquid          : " + net_liquid());
        System.out.println();
        System.out.println("Gross exposure      : " + gross_exposure());
        System.out.println();
        System.out.println("net exposure        : " + net_exposure());
        System.out.println();
        System.out.println("Long exposure       : " + long_summary());
        System.out.println();
        System.out.println("Short exposure      : " + short_summary());
        System.out.println();
        System.out.println("Short ratio         : " + short_ratio());

    }


    public void set_symbols_to_true_shorts(List<String> new_true_short_symbols){

        for (Position pos: this.positions) {
            if(new_true_short_symbols.contains(pos.symbol)){
                pos.is_true_short = true;
            }
        }

    }





    public String import_custom_IBKR_portfolio_query(String csvpathname){

        List<Position> new_pos_list = new ArrayList<>();

        CSV_Reader csv_readerInst = new CSV_Reader();
        List<String> posliste = csv_readerInst.Csv_to_List(csvpathname);

        final String delemiter = ",";
        int counter = 0;
        for (int j = 1; j < posliste.size() ; j++) {
            String[] stingray =  posliste.get(j).split(delemiter);
            try{
                Position temp_pos = new Position();

                temp_pos.setSymbol(stingray[1]);
                temp_pos.setName(stingray[5]);
                temp_pos.setQuantity(Double.parseDouble(stingray[2]));
                temp_pos.setPosition_value(Double.parseDouble(stingray[3]));
                temp_pos.setBasis_price(Double.parseDouble(stingray[7]));
                temp_pos.setBasis_money(Double.parseDouble(stingray[8]));
                // if position is short set true_short token true
                if(Double.parseDouble(stingray[2]) < 0){temp_pos.setIs_true_short(true);}
                else{temp_pos.setIs_true_short(false);}

                new_pos_list.add(temp_pos);
                counter +=1;
            }
            catch(Exception e){
                System.out.println("kein Import der Position : " + stingray[1]);
                System.out.println(e.getMessage());

            }
        }

        this.positions = new_pos_list;
        // Important ! setting here the true short token for real shorts which have negative quantity


        return counter + " positions imported";
    }

}
