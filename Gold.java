public class Gold extends Resource{
    public int totalQuantity;
    private int quantity24;
    private int quantity22;
    private int quantity18;
    private int quantity14;
    private int quantity10;
    private double sellPrice24 = 100.00;
    private double sellPrice22 = 80.00;
    private double sellPrice18 = 60.00;
    private double sellPrice14 = 40.00;
    private double sellPrice10 = 20.00;

    /**
     * Creates a new Gold with super constructor.
     */
    public Gold(){
        super("Gold");
    }

    //getter

    /**
     * Gets the quantity of gold 24
     *
     * @return quantity of gold 24
     */
    public int getQuantity24() {
        return quantity24;
    }

    /**
     * Gets the quantity of gold 22
     *
     * @return quantity of gold 22
     */
    public int getQuantity22() {
        return quantity22;
    }

    /**
     * Gets the quantity of gold 18
     *
     * @return quantity of gold 18
     */
    public int getQuantity18() {
        return quantity18;
    }

    /**
     * Gets the quantity of gold 14
     *
     * @return quantity of gold 14
     */
    public int getQuantity14() {
        return quantity14;
    }

    /**
     * Gets the quantity of gold 10
     *
     * @return quantity of gold 10
     */
    public int getQuantity10() {
        return quantity10;
    }

    /**
     * Gets the sell price of gold 24
     *
     * @return sell price of gold 24
     */
    public double getSellPrice24() {
        return sellPrice24;
    }

    /**
     * Gets the sell price of gold 22
     *
     * @return sell price of gold 22
     */
    public double getSellPrice22() {
        return sellPrice22;
    }

    /**
     * Gets the sell price of gold 18
     *
     * @return sell price of gold 18
     */
    public double getSellPrice18() {
        return sellPrice18;
    }

    /**
     * Gets the sell price of gold 14
     *
     * @return sell price of gold 14
     */
    public double getSellPrice14() {
        return sellPrice14;
    }

    /**
     * Gets the sell price of gold 10
     *
     * @return sell price of gold 10
     */
    public double getSellPrice10() {
        return sellPrice10;
    }

    /**
     * Gets the total quantity of gold
     *
     * @return sell price of gold 24
     */
    public int getTotalQuantity() {
        return totalQuantity;
    }

    //setter

    /**
     * Set the quantity of gold 24
     *
     * @param int quantity of gold 24
     */
    public void setQuantity24(int purity24) {
        this.quantity24 = purity24;
    }

    /**
     * Set the quantity of gold 22
     *
     * @param int quantity of gold 22
     */
    public void setQuantity22(int purity22) {
        this.quantity22 = purity22;
    }

    /**
     * Set the quantity of gold 18
     *
     * @param int quantity of gold 18
     */
    public void setQuantity18(int purity18) {
        this.quantity18 = purity18;
    }

    /**
     * Set the quantity of gold 14
     *
     * @param int quantity of gold 14
     */
    public void setQuantity14(int purity14) {
        this.quantity14 = purity14;
    }

    /**
     * Set the quantity of gold 10
     *
     * @param int quantity of gold 10
     */
    public void setQuantity10(int purity10) {
        this.quantity10 = purity10;
    }

    /**
     * Set the sell price of gold 24
     *
     * @param double sell price of gold 24
     */
    public void setSellPrice24(double sellPrice24) {
        this.sellPrice24 = sellPrice24;
    }

    /**
     * Set the sell price of gold 22
     *
     * @param double sell price of gold 22
     */
    public void setSellPrice22(double sellPrice22) {
        this.sellPrice22 = sellPrice22;
    }

    /**
     * Set the sell price of gold 18
     *
     * @param double sell price of gold 18
     */
    public void setSellPrice18(double sellPrice18) {
        this.sellPrice18 = sellPrice18;
    }

    /**
     * Set the sell price of gold 14
     *
     * @param double sell price of gold 14
     */
    public void setSellPrice14(double sellPrice14) {
        this.sellPrice14 = sellPrice14;
    }

    /**
     * Set the sell price of gold 10
     *
     * @param double sell price of gold 10
     */
    public void setSellPrice10(double sellPrice10) {
        this.sellPrice10 = sellPrice10;
    }

    /**
     * Set total quantity
     *
     * @param int new total quantity
     */
    public void setTotalQuantity(int t) {
        this.totalQuantity = t;
        int quantity = this.getTotalQuantity();
        super.setQuantity(quantity);
    }

    /**
     * display amount of each gold
     */
    public void displayGold(){
        System.out.println();
        System.out.println("----------Gold Amount----------");
        System.out.println("Gold 24: " + quantity24 + "\tGold 22: " + quantity22 + "\tGold 18: " + quantity18 + "\tGold 14: " + quantity14 + "\tGold 10: " + quantity10);
        System.out.println("-------------------------------");
        System.out.println();
    }
}