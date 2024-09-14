/**
 * The Resource class represents a generic resource in the game.
 * Resources have a name, a quantity, and a status of critical or not critical.
 */
public abstract class Resource implements Comparable<Resource>, Score{
    private String name;
    private int quantity;
    private boolean isCritical;

    /**
     * Creates a new Resource with the given name and initializes the quantity to 0.
     *
     * @param name the name of the resource
     */
    public Resource(String name) {
        this.name = name;
        this.quantity = 0;
        this.isCritical = false;
    }

    //constructor to call grain (because starting grain quantity is fixed)
    public Resource(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.isCritical = false;
    }

    /**
     * Gets the name of the resource.
     *
     * @return the name of the resource
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the quantity of the resource.
     *
     * @return the quantity of the resource
     */
    public int getQuantity() {
        return quantity;
    }

    //setter
    /**
     * set quantity of resource
     * 
     * @param int quantity of resource
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Reports if a resource is critical. If a rsource is critical, reaching 0 ends the game.
     *
     * @return if the resource is critical
     */
    public boolean isCritical(){
        return isCritical;
    }

    /**
     * Sets if a given resource is critical.
     * 
     * @param boolean value for isCritical
     */
    public void setIsCrticial(boolean isCritical){
        this.isCritical = isCritical;
    }

    /**
     * Adds the specified amount to the quantity of the resource.
     *
     * @param int the amount to add
     */
    public void add(int amount) {
        quantity += amount;
    }

    /**
     * Consumes the specified amount of the resource if available. Sets the resource to 0 if there is not enough to consume.
     *
     * @param int the amount to consume
     */
    public void consume(int amount) {
        if (quantity >= amount) {
            quantity -= amount;
        } else {
            quantity = 0;
            System.out.println("Not enough " + name + " to consume.");
        }
    }

    //overriding compareTo method

    /**
     * comparing resources based on quantity
     *
     * @param Resource the resource
     * @return 1 if parameter's quantity is smaller, -1 if parameter's quantity is bigger, other wise 0
     */
    @Override
    public int compareTo(Resource resource){
        int oQuantity = (int)this.getQuantity();
        int sQuantity = (int)resource.getQuantity();
        return oQuantity > sQuantity ? 1: oQuantity < sQuantity ? -1: 0;
    }
    
    /**
     * calculate score based on quantity of resource (10 % of quantity)
     * 
     * @return Score that will be added
     */
    public int scoreImpact(){
        int addingScore = (int)(quantity * 0.1);
        return addingScore;
    }
}