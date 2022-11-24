import java.util.*;
public class Portfolio {



    public List<Position> getPositions() {
        return positions;
    }



    List<Position> positions;           // Vielleicht HashMap ?
    double net_liquid;

    String name;
    double gross_exposure;
    double net_exposure;
    double short_exposure;
    double long_exposure;
    double long_short_ratio;
    double leverage;

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public void calculate_main_metrics(){

        calculate_short_exposure_from_real_shorts();
        calculate_long_exposure_from_real_longs();
        calculate_gross_exposure();
        calculate_net_exposure();
        calculate_short_ratio();
        calculate_leverage();
        calculate_net_liquid();
    }

    public void print_metrics(){
        System.out.println("net liquid          : " + this.net_liquid);
        System.out.println("Long exposure       : " + this.long_exposure);
        System.out.println("Short exposure      :     " + this.short_exposure);
        System.out.println("Long-Short ratio    : " + this.long_short_ratio);
        System.out.println("Gross exposure      : " + this.gross_exposure);
        System.out.println("net exposure in amt : " + this.net_exposure);
        System.out.println("net exposure in %   : " + this.gross_exposure/this.net_exposure);
        System.out.println("leverage            : " + this.leverage);

    }
    public double calculate_short_exposure_from_real_shorts(){
        double short_summary;
        short_summary = 0.0;
        for (Position pos : this.positions) {
            if(pos.is_true_short){
                //
                double root_pos_value = Math.abs(pos.position_value);
                short_summary = short_summary + root_pos_value;
            }
        }
        this.short_exposure = short_summary;
        return short_summary;
    }

    public void set_symbols_to_true_shorts(List<String> new_true_short_symbols){

        for (Position pos: this.positions) {
            if(new_true_short_symbols.contains(pos.symbol)){
                pos.is_true_short = true;
            }
        }
        calculate_main_metrics();
    }


    public double calculate_long_exposure_from_real_longs(){
        double long_summary;
        long_summary = 0.0;
        for (Position pos : this.positions) {
            if(pos.is_true_short == false){
                //
                double root_pos_value = Math.abs(pos.position_value);
                long_summary = long_summary + root_pos_value;
            }
        }
        this.long_exposure = long_summary;
        return long_summary;
    }

    public void calculate_gross_exposure(){
        this.gross_exposure = this.short_exposure + this.long_exposure;
    }

    public int set_trueShorts_from_negative_quantity(){
        int counter = 0;

        for (Position pos : this.getPositions()) {
            if(pos.getQuantity() < 0){
                pos.setIs_true_short(true);
                counter +=1;
            }
        }
        return counter;
    }

    public void calculate_net_exposure(){
        this.net_exposure = this.long_exposure - this.short_exposure;
    }

    public void calculate_short_ratio(){
        this.long_short_ratio = this.short_exposure / this.gross_exposure;
    }

    public void calculate_leverage(){
        this.leverage = this.gross_exposure / this.net_exposure;
    }

    public void calculate_net_liquid(){
        double sum_book_shorts = 0.0;
        double sum_book_longs = 0.0;
        for (Position pos : positions) {
            if(pos.getPosition_value() < 0){
                sum_book_shorts += pos.getPosition_value();
            } else if (pos.getPosition_value() > 0) {
                sum_book_longs += pos.getPosition_value();
            }
        }
        System.out.println("summe_buch_shorts : "+sum_book_longs);
        System.out.println("summe_buch_longs : "+sum_book_shorts);
        net_liquid = sum_book_shorts + sum_book_longs;
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
                temp_pos.setIs_true_short(false);
                new_pos_list.add(temp_pos);
                counter +=1;
            }
            catch(Exception e){
                e.getMessage();
                System.out.println("kein Import der Position : " + stingray[1]);
            }
        }

        this.positions = new_pos_list;
        set_trueShorts_from_negative_quantity();

        return counter + " positions imported";
    }

}
