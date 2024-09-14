public class Grain extends Resource{
    private int harvestTurn;
    private int quantity;

    /**
     * Creates a new Grain with super constructor.
     */
    public Grain(int q){
        super("Grain", q);{
            quantity = q;
        }
    }

    //getter

    /**
     * get the harves turn
     *
     * @return harves turn
     */
    public int getHarvestTurn() {
        return harvestTurn;
    }
    
    /**
     * get quantity
     *
     * @return quantity
     */
    public int getQuantity() {
        return quantity;
    }

    //setter

    /**
     * set the harves turn
     *
     * @param int new harves turn
     */
    public void setHarvestTurn(int harvestTurn) {
        this.harvestTurn = harvestTurn;
    }

    /**
     * set the total money
     *
     * @param double new total money
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    /**
     * checking quantity of grain is positive or not(if it's 0 or less return ture)
     *
     * @return grain is less or equal to 0 or not
     */
    @Override
    public boolean isCritical(){
        if(quantity <= 0){
            return true;
        }
        return false;
    }
}