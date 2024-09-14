public class Wood extends Resource{
    private int nonProcessedWood;
    private int processedWood;

    /**
     * Creates a new wood with super constructor.
     */
    public Wood(){
        super("Wood");
    }

    //getter

    /**
     * Gets the amount of non processed wood
     *
     * @return amount of non processed wood
     */
    public int getNonProcessedWood() {
        return nonProcessedWood;
    }

    /**
     * Gets the amount of processed wood
     *
     * @return amount of processed wood
     */
    public int getProcessedWood() {
        return processedWood;
    }

    //setter

    /**
     * set the amount of processed wood
     *
     * @param int amount of non processed wood
     */
    public void setNonProcessedWood(int nonProcessedWood) {
        this.nonProcessedWood = nonProcessedWood;
    }

    /**
     * set the amount of processed wood and set processed wood as a quontity
     *
     * @param int amount of processed wood
     */
    public void setProcessedWood(int processedWood) {
        super.setQuantity(processedWood);
        this.processedWood = processedWood;
    }
}