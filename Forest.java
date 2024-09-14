import java.util.*;
public class Forest extends Generator{
    private double producingRate = 1.5;
    private int producedWood;
    private int ownedWood;
    private int amountOfForest;
    private int workerNum;

    Scanner input = new Scanner(System.in);
    int choice = 0;
    Scanner subInput = new Scanner(System.in);
    int subChoice = 0;

    //constructor
    /**
     * Creates a new Forest with super constructor.
     *
     * @param int number of constructed
     * @param Wood resource
     */
    public Forest(int numberConstructed, Wood wood){
        super("Forest", numberConstructed, wood);
    }

    /**
     * display wood amount.
     *
     * @param Wood wood to display
     */
    public void displayWood(Wood w){
        System.out.println();
        System.out.println("------------------------------");
        System.out.println("You have " + w.getNonProcessedWood() + " of non producedWood.");
        System.out.println("You have " + w.getProcessedWood() + " of producedWood.");
        System.out.println("------------------------------");
    }

    /**
     * getting wood based on woker number, producing rate and amount of generator.
     *
     * @param Wood wood to get and add wood
     */
    public void getWoods(Wood w){
        super.setProducingRatePerPerson(producingRate);
        amountOfForest = super.getAmountOfGenerator();
        producingRate = super.getProducingRatePerPerson();
        workerNum = super.getWorkerNum();
        producedWood = (int)((double)workerNum * producingRate * amountOfForest);
        ownedWood = w.getNonProcessedWood();
        ownedWood = ownedWood + producedWood;
        w.setNonProcessedWood(ownedWood);
        displayWood(w);
    }

    /**
     * process woods to processed wood to use
     *
     * @param Wood wood to process
     */
    public void processWood(Wood w){
            System.out.println("How many woods do you want to process?");
            System.out.println("\t0. Non of woods.");
            System.out.println("\t1. All of woods.");
            System.out.println("\t2. Choose amount.");
            choice = input.nextInt();
            switch(choice){
            case 0:
            System.out.println("No wood was processed.");
            displayWood(w);
            break;

            case 1:
            ownedWood = w.getNonProcessedWood();
            if(ownedWood > 0){
                System.out.println(ownedWood + " woods are processed.");
                w.setNonProcessedWood(0);
                int newProcessedWood = w.getProcessedWood() + ownedWood;
                w.setProcessedWood(newProcessedWood);
                displayWood(w);
            }
            else{
                System.out.println("You have 0 woods.");
                displayWood(w);
            }
            break;

            case 2:
            System.out.println("How many woods do you want to process?");
            subChoice = subInput.nextInt();
            int tempNonProcessedWood = w.getNonProcessedWood() - subChoice;
            if(tempNonProcessedWood > 0){
                w.setNonProcessedWood(tempNonProcessedWood);
                int newProcessedWood = w.getProcessedWood() + subChoice;
                w.setProcessedWood(newProcessedWood);
                displayWood(w);
            }
            else{
                System.out.println("You don't have enough woods to process.");
                int amount = subChoice + tempNonProcessedWood;
                int newProcessedWood = w.getProcessedWood() + amount;
                w.setProcessedWood(newProcessedWood);
                tempNonProcessedWood = w.getNonProcessedWood() - amount;
                w.setNonProcessedWood(tempNonProcessedWood);
                System.out.println("You successfully processed " + amount + " of woods.");
                displayWood(w);
            }
            break;
        }        
    }
}
