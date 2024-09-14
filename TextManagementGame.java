import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Comparator;
import java.util.Collections;

/**
 * The TextManagementGame class represents a text-based management game where the player manages resources and resource generators.
 */
public class TextManagementGame {
    // Define game variables
    private int round;
    private int maxWorkerNum;
    private ArrayList<Resource> resources = new ArrayList<Resource>();
    private ArrayList<Generator> generators = new ArrayList<Generator>();
    Gold gold = new Gold();
    Grain grain = new Grain(110);
    Wood wood = new Wood();
    Money money = new Money();
    GoldMine goldMine = new GoldMine(1, gold);
    Farm farm = new Farm(1, grain);
    Forest forest = new Forest(1, wood);
    boolean eventCheck = false;
    private boolean isFirstTimeMessage = true;
    private boolean isFirstTime = true;
    private boolean is31stTurn = false;
    private boolean firstAction = true;
    
    //instance GameMethods
    GameMethods gameMethods = new GameMethods(30);
    Economics eco = new Economics(gold);

    // Define a Scanner for user input
    private Scanner scanner;
    private Scanner subScanner;
    private Scanner manageScanner;

    /**
     * Creates a new TextManagementGame instance with initial resource and time values.
     * TODO : Add starting resources
     */
    public TextManagementGame() {
        round = 1;       // Start at time 1
        scanner = new Scanner(System.in);
        subScanner = new Scanner(System.in);
        manageScanner = new Scanner(System.in);
        resources.add(gold);
        resources.add(grain);
        resources.add(wood);
        resources.add(money);
        generators.add(goldMine);
        generators.add(farm);
        generators.add(forest);
    }

    /**
     * Check if a method should run with a 1 in number chance.
     *
     * @return true if the method should run, false otherwise
     */
    public boolean haveEventThisTurn(int number) {
        Random random = new Random();
        int chance = random.nextInt(number); // Generates a random number between 0 (inclusive) and number (exclusive)
        return chance == 0; // Returns true with a 1 in number chance
    }

    /**
    * Prints the list of resources
    */
    //print each resources basic information
    public void viewResources(){
        System.out.println();
        resourceSorting();
        Resource leastResource = resources.get(0);;
        Resource mostResource = resources.get(resources.size()-1);
        String name;
        int quantity;
        double yourMoney;
        for(Resource r : resources){
            if(r instanceof Money){
                Money rMoney = (Money)r;
                name = rMoney.getName();
                yourMoney = rMoney.getTotalMoney();
                String moneyString = String.format("%.2f",yourMoney);
                System.out.println("You have $" + moneyString + ".");
            }
            else{
                name = r.getName();
                quantity = r.getQuantity();
                System.out.println("You have " + quantity + " of " + name + ".");
            }
        }
        System.out.println("You have " + mostResource.getName() + " the most.");
        System.out.println("You have " + leastResource.getName() + " the least.");
    }

    /**
     * Sorting ArrayList named resources by using Collections.sort()
     */
    public void resourceSorting(){
        Collections.sort(resources);
    }

    /**
     * collecting resources
     */
    public void collecting(){
        System.out.println();
        System.out.println("************" + "ROUND" + round + " RESOURCES" + "************");
        gameMethods.collectResources(round,goldMine, gold);
        gameMethods.collectResources(round,farm, grain);
        gameMethods.collectResources(round,forest, wood);
        System.out.println("****************************************");

    }

    /**
    * Prints the list of Generators
    */
    //print each generators basic information
    public void viewGenerators(){
        System.out.println();
        generatorSorting();
        Generator maxGenerators = generators.get(generators.size()-1);
        Generator minGenerators = generators.get(0);
        for(Generator b : generators){
            String name = b.getName();
            int generatorAmount = b.getNumberConstructed();
            int level = b.getGeneratorLevel();
            System.out.println("You have " + generatorAmount + " of level " + level + " " + name + ".");
        }
        if(maxGenerators.getNumberConstructed() == minGenerators.getNumberConstructed()){
            System.out.println("You have the same amount of all generator.");
        }
        else{
            System.out.println("You have " + maxGenerators.getName() + " the most.");
        System.out.println("You have " + minGenerators.getName() + " the least.");
        }
    }

    /**
     * sorting ArrayList named generators by using Collections.sort()
     */
    public void generatorSorting(){
        Collections.sort(generators);
    }

    /**
     * managing some resources by using some Generators
     * @param Generator generator
     * @param Resource resource
     * @param int user choice from scanner
     * @param Money money
     */
    public void managing(Generator generator, Resource resource, int userChoice, Money money){
        gameMethods.manageResources(generator, resource, userChoice, money);
    }

    /**
     * Checks if a Generator can be constructed and then adds it to the list of Generators
     * @param Generator generator
     * @param Money money
     * TODO : ADD LOGIC
     */
    //adding new generator(you have to chose one of the generator that is exist)
    public void constructGenerator(Generator generator, Money money){
        gameMethods.addGenerator(generator, money);
    }

     /**
     * upgrading generator
     * @param Generator generator
     * @param Money money
     */
    public void upgrading(Generator generator, Money money){
        gameMethods.upgradeGenerator(generator, money);
    }

    /**
     * Upgreading worker number of each generator
     * @param Generator generator
     */
    public void workerUpdate(Generator generator){
        maxWorkerNum = generator.getMaxWorkerNum();
        int newWorkerNum = gameMethods.workerNumUpdate();
        int newMaxWorkerNum = gameMethods.workerNumUpdate();
        if(maxWorkerNum < newMaxWorkerNum){
            generator.setMaxWorkerNum(newMaxWorkerNum);
        }
        else{
            ;
        }
        generator.setWorkerNum(newWorkerNum);
    }

    /** 
     * Increments the time counter and then adds more resources based on what generators are present
     * TODO : Add calculations to generate resources for the next turn
     */
    //methods when user end round
    public void endRound(){
        //resource payment and population chenge
        gameMethods.endRound(round,money);
        gameMethods.endRound(round,grain);
        gameMethods.endRound(round,wood);
        gameMethods.populationChenge(round);
        //calculate scores
        gameMethods.calcScore(goldMine, gold);
        gameMethods.calcScore(farm, grain);
        gameMethods.calcScore(forest, wood);
        double moneyNum = money.getTotalMoney();
        int grainNum = grain.getQuantity();
        int nonProcessedWoodNum = wood.getNonProcessedWood();
        int processedWoodNum = wood.getProcessedWood();
        //display scores
        gameMethods.displayScore();
        gameMethods.display(moneyNum, grainNum, nonProcessedWoodNum, processedWoodNum);
        isFirstTime = false;
        round++;
        //chenge worker number
        workerChenge(goldMine, gameMethods);
        workerChenge(farm, gameMethods);
        workerChenge(forest, gameMethods);
    }

    /**
     * display score when game is done.
     */
    public void displayFinalScore(){
        gameMethods.finalDisplay();
    }

    /**
     * Adds a Generator object to the ArrayList of Generators.
     *
     * @param Generator the Generator object to add
     * @param Money money
     */
    //check user can add new generator or not and add if they can
    public void addGenerator(Generator generator, Money money) {
        gameMethods.addGenerator(generator, money);
        generators.add(generator);
    }

    /**
     * Adds a Resource object to the ArrayList of resources.
     *
     * @param Resource the Resource object to add
     */
    //add resource
    public void addResource(Resource resource) {
        resources.add(resource);
    }

    /**
     * Checks if we are out of any critical resources
     *
     * @return returns true if we are out of any critical resources returns false otherwise
    */
    //Check is amount of any resource is 0 or less than 0
    public boolean isCriticalResourceEmpty(){
        boolean moneyCondition = money.isCritical();
        boolean grainCondition = grain.isCritical();
        boolean peopleCondition = gameMethods.isCritical();
        if(round == 1){
            return false;
        }
        else{
            if(moneyCondition){
                System.out.println("You have no money. You can't continue manage resources.");
                return true;
            }
            if(grainCondition){
                System.out.println("You have 0 grain. You can't continue manage resources.");
                return true;
            }
            if(peopleCondition){
                System.out.println("You have no human. You can't continue manage resources.");
                return true;
            }
            else{
                return false;
            }
        }
    }

    //events 

    /**
     * events that reduce money
     */
    public void moneyEvent(){
        Event e = new Event("Depression");
        e.resourceEvent(money);
        gameMethods.calcScore(eventCheck, e);
    }

    /**
     * events that reduce grain
     */
    public void grainEvent(){
        Event e = new Event("A bad harvest");
        e.resourceEvent(grain);
        gameMethods.calcScore(eventCheck, e);
    }

    /**
     * events that reduce wood
     */
    public void woodEvent(){
        Event e = new Event("Wildfire");
        e.resourceEvent(wood);
        gameMethods.calcScore(eventCheck, e);
    }

    /**
     * events that reduce population
     */
    public void populationEvent(){
        Event e = new Event("Disease");
        e.populationEvent(gameMethods);
        gameMethods.calcScore(eventCheck, e);
    }

    /**
     * Adds a Resource object to the ArrayList of resources.
     *
     * @param Resource the Resource object to add
     */
    public void workerChenge(Generator generator, GameMethods gameMethods){
        int currentPopulation = gameMethods.getPopulation();
        int maxWorker = generator.getMaxWorkerNum();
        int possibleWorkerNum = currentPopulation / 3;
        if(maxWorker < possibleWorkerNum){
            generator.setMaxWorkerNum(possibleWorkerNum);
            maxWorker = generator.getMaxWorkerNum();
        }
        if(maxWorker > possibleWorkerNum){
            generator.setWorkerNum(possibleWorkerNum);
        }

    }

    /**
     * Starts the game and manages the game loop.
     */
    public void start() {
        if(round == 1 && isFirstTimeMessage == true){
            System.out.println("Welcome to the Text Management Game!"); //TODO: Change Text
            System.out.println("Here is main rule below");
            System.out.println("\t-This game will continue for 30 round. You can quit anytime you want and you will get final score.");
            System.out.println("\t-You need to pay resorces when round ends. You can chose to pay 0 resources but population will decrease. However if you pay too less (lass 10% of last payment) your poplutation will be decreased a lot therefore there is possibitity that your population will be 0.");
            System.out.println("\t-Population increase based on rate of chenge of resorces payment.");
            System.out.println("\t-You are inabel to continue game when population, grain or money became 0.");
            System.out.println("\t-There is 25% chance of event for every round. These event reduce resources or pupularation. Also you will get 50 points off from score.");
            System.out.println("\t-Here is fanctions of this game.");
            System.out.println("\t\t1)Sort and view resources: Sort resources class and prints basic information of resources. You can see what resources you have the most and least.");
            System.out.println("\t\t2)Sort and view generators: Sort generators class and prints basic information of generators. You can see what generators you have the most and least.");
            System.out.println("\t\t3)Manage resources: Manage money by converting gold. Manage wood by processing wood.");
            System.out.println("\t\t4)Add a new Generator: Add a new Generator. You need to pay money to add generator");
            System.out.println("\t\t5)Upgrading Generator: Upgrade a new Generator. You need to pay money to upgrade generator");
            System.out.println("\t\t6)End round: Pay resources and print information of score and your current resources informations");
            System.out.println("\t\t7)Quit game: Quit this game");
            System.out.println("*Score caluculation: (poplulation * 3) + (10% of resources) + (Bonus point for Adding Generator:50 points * added generator) + (Bonus point for Upgrading Generator:50 points * how many time you upgraded generator for this round) + (Event points off)");
            System.out.println("Enjoy the game!");
            isFirstTimeMessage = false;
        }
        int oddsOfRandomEvent = 4; //a 25% chance of a random event occuring
        // Main game loop
        collecting();

        while (!isCriticalResourceEmpty() && is31stTurn == false) {
            System.out.println("\nTime " + round);
            if(isFirstTime == false){
                if(firstAction){
                    if(haveEventThisTurn(oddsOfRandomEvent)){
                        //TODO add logic for random events
                        eventCheck = true;
                        
                        System.out.println("A random event happened!");
                        Random random = new Random();
                        int typeOfEvent = random.nextInt(4); 
                        if(typeOfEvent == 1){
                            moneyEvent();
                        }
                        else if(typeOfEvent == 2){
                            grainEvent();
                        }
                        else if (typeOfEvent == 3){
                            woodEvent();
                        }
                        else{
                            populationEvent();
                        }
                    }
                    firstAction = false;
                }
            }
            System.out.println("Options:");
            System.out.println("1. Sort and view resources");
            System.out.println("2. Sort and view generators");
            System.out.println("3. Manage resources");
            System.out.println("4. Add a new Generator");
            System.out.println("5. Upgrading Generator");
            System.out.println("6. End round");
            System.out.println("7. Quit game");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    viewResources();
                    break;

                case 2:
                    Collections.sort(generators);
                    viewGenerators();
                    break;

                case 3:
                    System.out.println("What do you want to do?");
                    System.out.println("1. Sell gold");
                    System.out.println("2. Process woods");
                    int manageChoice = manageScanner.nextInt();
                    switch(manageChoice){
                        case 1:
                            managing(goldMine, gold, 1, money);
                            break;

                        case 2:
                            managing(forest, wood, 2, money);
                            break;
                    }
                    break;

                case 4: 
                    System.out.println("What generator do you want to add?");
                    System.out.println("1. Gold Mind");
                    System.out.println("2. Farm");
                    System.out.println("3. Forest");
                    int subChoice = subScanner.nextInt();
                    switch(subChoice){
                        case 1:
                        constructGenerator(goldMine, money);
                        break;

                        case 2:
                        constructGenerator(farm, money);
                        break;

                        case 3:
                        constructGenerator(forest, money);
                        break;
                    }
                    break;
                
                case 5:
                System.out.println("What generator do you want to upgrade?");
                System.out.println("1. Gold Mind");
                System.out.println("2. Farm");
                System.out.println("3. Forest");
                int upgradeChoice = subScanner.nextInt();
                switch(upgradeChoice){
                    case 1:
                    upgrading(goldMine, money);
                    break;

                    case 2:
                    upgrading(farm, money);
                    break;

                    case 3:
                    upgrading(forest, money);
                    break;
                }
                break;

                case 6:
                    //Check next round is 31st round or not
                    if((round + 1) > 30){
                        is31stTurn = true;
                    }
                    workerUpdate(goldMine);
                    workerUpdate(farm);
                    workerUpdate(forest);
                    endRound();
                    collecting();
                    firstAction = true;
                    break;
                case 7:
                    displayFinalScore();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        if(is31stTurn){
            System.out.println();
            System.out.println("Congratulation! You complete the game.");
            System.out.println("You played for " + (round - 1) + " rounds");
            displayFinalScore();
        }
        else{
            System.out.println("Game Over! You ran out of resources.");
            System.out.println("You played for " + (round - 1)+ " rounds");
            displayFinalScore();
        }
    }

    /**
     * Main method to run the game
     *
     * @param args the command-line arguments (not used in this game)
     */
    public static void main(String[] args) {
        TextManagementGame game = new TextManagementGame();
        game.start();
    }
}