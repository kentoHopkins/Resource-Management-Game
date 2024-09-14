import java.util.*;
public class Money extends Resource{
    
    private double totalMoney = 0.0;
    Scanner input = new Scanner(System.in);
    int choice;
    Scanner subInput = new Scanner(System.in);
    int subChoice;

    /**
     * Creates a new Money with super constructor.
     */
    public Money(){
        super("Money");
    }

    //getter

    /**
     * Gets the total money
     *
     * @return total money
     */
    public double getTotalMoney(){
        return totalMoney;
    }

    //setter

    /**
     * set the total money
     *
     * @param double new total money
     */
    public void setTotalMoney(double money){
        this.totalMoney = money;
    }
    
    /**
     * checking money is positive or not(if it's 0 or less return ture)
     *
     * @return money is less or equal to 0 or not
     */
    public boolean isCritical(){
        if(totalMoney <= 0){
            return true;
        }
        return false;
    }
}
