import java.util.*;
/**
 * The Generator class represents a generic resource generating item in the game.
 * Generators have a name, a construction cost, and a resource production rate.
 */
public abstract class Generator implements Comparable<Generator>, Score{
    private String name;
    private ArrayList<Resource> constructionCost;
    private int numberConstructed = 1;
    private Resource product;
    //additional variables
    private double upgradeCost = 50.00;
    private int maxWorkerNum = 10;
    private int workerNum = 10;
    private int generatorLevel = 1;
    private double addingCost = 500.00;
    private double producingRatePerPerson = 0.5;
    private double rate = producingRatePerPerson * workerNum;
    private int upgreadingBonus;
    private int addingBonus;
    Money m = new Money();

    /**
     * Creates a new Generator with the given name, construction cost, and resource production rate.
     *
     * @param name                  the name of the Generator
     * @param constructionCost      the cost in resources required to construct the Generator
     * @param resourceProductionRate the rate at which the Generator produces resources per unit of time
     * @param numberConstructed     the number of units of this generator constructed at this time
     * @param product               the type of resource this generator produces
     */
    public Generator(String name, int numberConstructed, Resource product) {
        this.name = name;
        this.numberConstructed = numberConstructed;
        this.product = product;
    }

    /**
     * Gets the name of the Generator.
     *
     * @return the name of the Generator
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the construction cost of the Generator.
     *
     * @return the construction cost of the Generator
     */
    public ArrayList<Resource> getConstructionCost() {
        return constructionCost;
    }
    
    /**
     * Gets the number of units constructed of this Generator.
     *
     * @return the number of units constructed of the generator
     */
    public int getNumberConstructed() {
        return numberConstructed;
    }

    //additional getter
    
    /**
     * Gets kind of resource.
     *
     * @return kind of resource
     */
    public Resource getProduct() {
        return product;
    }

    /**
     * Gets cost that is needed when upgrade ganerator
     *
     * @return upgrading cost
     */
    public double getUpgradeCost() {
        return upgradeCost;
    }

    /**
     * Gets maximum number of worker
     *
     * @return maximum number of worker
     */
    public int getMaxWorkerNum() {
        return maxWorkerNum;
    }

    /**
     * Gets current number of worker
     *
     * @return current number of worker
     */
    public int getWorkerNum() {
        return workerNum;
    }

    /**
     * Gets level of generator
     *
     * @return level of generator
     */
    public int getGeneratorLevel() {
        return generatorLevel;
    }

    /**
     * Gets producing rate per person
     *
     * @return producing rate per person
     */
    public double getProducingRatePerPerson() {
        return producingRatePerPerson;
    }

    /**
     * Gets rate of pruducing (producingRatePerPerson * workerNum)
     *
     * @return rate of pruducing
     */
    public double getRate(){
        rate = producingRatePerPerson * workerNum;
        return rate;
    }

    /**
     * Gets amount of generator
     *
     * @return amount of generator
     */
    public int getAmountOfGenerator(){
        return numberConstructed;
    }

    /**
     * Gets cost that is needed when add ganerator
     *
     * @return adding cost
     */
    public double getAddingCost() {
        return addingCost;
    }

    /**
     * Gets bonus point by upgreading generator
     *
     * @return bonus point by upgreading generator
     */
    public int getUpgreadingBonus() {
        return upgreadingBonus;
    }

    /**
     * Gets bonus point by adding generator
     *
     * @return bonus point by adding generator
     */
    public int getAddingBonus() {
        return addingBonus;
    }

    //additional setter


    /**
     * set cost that is needed when upgrade ganerator
     *
     * @param double new upgrading cost
     */
    public void setUpgradeCost(double upgradeCost) {
        this.upgradeCost = upgradeCost;
    }

    /**
     * set maximum number of worker
     *
     * @param int new maximum number of worker
     */
    public void setMaxWorkerNum(int maxWorkerNum) {
        this.maxWorkerNum = maxWorkerNum;
    }

    /**
     * set number of worker
     *
     * @param int new number of worker
     */
    public void setWorkerNum(int workerNum) {
        this.workerNum = workerNum;
    }

    /**
     * set level of generator
     *
     * @param double new level of generator
     */
    public void setGeneratorLevel(int generatorLevel) {
        this.generatorLevel = generatorLevel;
    }

    /**
     * set producing rate per person
     *
     * @param double new producing rate per person
     */
    public void setProducingRatePerPerson(double producingRatePerPerson) {
        this.producingRatePerPerson = producingRatePerPerson;
    }
    
    /**
     * set cost that is needed when add ganerator
     *
     * @param double new adding cost
     */
    public void setAddingCost(double addingCost) {
        this.addingCost = addingCost;
    }

    /**
     * set bonus points by upgrading generator
     *
     * @param double new bonus points by upgrading bonus
     */
    public void setUpgreadingBonus(int upgreadingBonus) {
        this.upgreadingBonus = upgreadingBonus;
    }

    /**
     * set bonus points by add generator
     *
     * @param double new bonus points by adding bonus
     */
    public void setAddingBonus(int addingBonus) {
        this.addingBonus = addingBonus;
    }

    /**
     * Upgrading generator
     *
     * @param Money money class to calculate payment
     */
    public void upgrading(Money m){
        double tempMoney = m.getTotalMoney();
        String tempMoneyString = String.format("%.2f",tempMoney);
        String upgradeCostString = String.format("%.2f",upgradeCost);
        System.out.println("You have $" + tempMoneyString + ".");
        System.out.println("Upgrade cost is $" + upgradeCostString + ".");
        if(tempMoney >= upgradeCost){
            if (generatorLevel <= 12){
                tempMoney = tempMoney - upgradeCost;
                addingCost = addingCost * 2;
                m.setTotalMoney(tempMoney);
                maxWorkerNum = (int)((double) maxWorkerNum * 1.5);
                //increase upgrading cost
                upgradeCost = upgradeCost * 1.5;
                producingRatePerPerson = producingRatePerPerson + 0.1;
                generatorLevel++;
                System.out.println("Upgrade complete!!");
                upgreadingBonus++;
            }
            else{
                System.out.println("It's already higest grade. You can't upgrade anymore.");
            }
        }
        else{
            System.out.println("You don't have enough money for upgrading...");
        }
    }

    /**
     * Add new generator
     *
     * @param Money money class to calculate payment
     */
    public void addGenerator(Money m){
        double tempMoney = m.getTotalMoney();
        String tempMoneyString = String.format("%.2f",tempMoney);
        String addingCostString = String.format("%.2f",addingCost);
        System.out.println("You have $" + tempMoneyString + ".");
        System.out.println("Upgrade cost is $" + addingCostString + ".");
        if(tempMoney >= addingCost){
            if (numberConstructed < 5){
                tempMoney = tempMoney - addingCost;
                m.setTotalMoney(tempMoney);
                numberConstructed++;
                System.out.println("You have " + numberConstructed + " " + name + ".");
                addingBonus++;
            }
            else{
                System.out.println("You already have maximam number of " + name + ".");
            }
        }
        else{
            System.out.println("You don't have enough money to add generator...");
        }
    }

    //overriding compareTo method

    /**
     * compare generator based on number of generator
     *
     * @param Generator  generator that will be compared
     */
    @Override
    public int compareTo(Generator generator){
        int thisGenerator = this.getNumberConstructed();
        int compGenerator = generator.getNumberConstructed();
        if(thisGenerator > compGenerator){
            return 1;
        }
        else if(thisGenerator < compGenerator){
            return -1;
        }
        else{
            return 0;
        }
    }

    /**
     * Calculate bonus points by upgreading or/and adding generators
     *
     * @return score based on upgreadingBonus and addingBonus
     */
    @Override
    public int scoreImpact(){
        int additionalPoints = (upgreadingBonus * 50)+(addingBonus * 50);
        upgreadingBonus = 0;
        addingBonus = 0;
        return additionalPoints;
    }
}