import java.util.*;
//This class is for making general methods to make class TextManagementGame simpler
public class GameMethods {
    //default setting
    private int population;
    private boolean continuity = true;
    private double minPay = 30.0;
    private int minGrain = 30;
    private int minWood = 10;
    private double priviousPay = minPay;
    private double currentPay;
    private int priviousGrain = minGrain;
    private int currentGrain;
    private int priviousWood = minWood;
    private int currentWood;
    private double persentChenge;
    private int totalScore;
    private int currentBonusScore;
    private int currentBasicScore;
    private int currentPopulationScore;
    private int currentEventScore;
    private boolean choseMinimumMoneyPayment = false;
    private boolean choseMinimumGrainPayment = false;
    private boolean choseMinimumWoodPayment = false;

    Scanner scanner = new Scanner(System.in);
    Scanner input = new Scanner(System.in);
    int choice = 0;
    Scanner subInput = new Scanner(System.in);
    double subChoice = 0;
    Scanner ynAsnwer = new Scanner(System.in);
    String ynAns;

    //constructor
    /**
     * Creates a new Forest with super constructor.
     *
     * @param int population
     */
    public GameMethods(int p){
        population = p;
    }

    //getters

    /**
     * Gets the population
     *
     * @return population
     */
    public int getPopulation() {
        return population;
    }
    
    //setters

    /**
     * Set the population
     *
     * @param int new population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * collecting resources(gold, grain and wood)
     *
     * @param int turn of the game
     * @param Generator generator
     * @param Resource resource of generator
     */
    public void collectResources(int turn, Generator generator, Resource resource){
        //collect gold
        if(generator instanceof GoldMine){
            GoldMine GM = (GoldMine)generator;
            Gold gold = (Gold) resource;
            GM.goldMining(gold);
        }
        //collect grain
        else if(generator instanceof Farm){
            Farm FM = (Farm)generator;
            Grain grain = (Grain) resource;
            FM.harvestGrain(turn, grain);
        }
        //collect non processed wood
        else if(generator instanceof Forest){
            Forest FR = (Forest)generator;
            Wood wood = (Wood) resource;
            FR.getWoods(wood);
        }
    }

    /**
     * managing resources(converting gold to money or processing wood)
     *
     * @param Generator generator
     * @param Resource resource of generator
     * @param int use choice from scanner
     * @param Money money(earn by converting gold)
     */
    public void manageResources(Generator generator, Resource resource, int userChoice, Money money){
        //converting gold to money
        if(userChoice == 1 && resource instanceof Gold){
            Gold gold = (Gold) resource;
            gold.displayGold();
            convertingGold cg = new convertingGold(gold, money);
            Economics economics = new Economics(gold);
            economics.reflectEconomics();
            //set choice to 1 for while loop
            choice = 1;
            while(0 < choice){
                System.out.println("How much gold do you want to convert?");
                System.out.println("If you don't want to convert or finish converting, chose 0.");
                System.out.println("\t0. Non of gold./Finish converting");
                System.out.println("\t1. All of gold.");
                System.out.println("\t2. Choose amount.");
                choice = input.nextInt();
                cg.convertToMoney(choice);
            }
        }
        //processing wood
        else if(userChoice == 2){
            Forest fr = (Forest)generator;
            Wood wood = (Wood) resource;
            fr.processWood(wood);
        }
    }

    /**
     * adding generator
     *
     * @param Generator generator to add
     * @param Money money(user need pay money when they add generator)
     */
    public void addGenerator(Generator generator, Money m){
        String name = generator.getName();
        System.out.print("Do you want to add 1 " + name + "? (Y or N):");
        String input_text = scanner.nextLine();
        input_text = input_text.toLowerCase();
        if(input_text.equals("y")){
            if(generator instanceof GoldMine){
                GoldMine tempGenerator = (GoldMine)generator;
                String tempName = tempGenerator.getName();
                System.out.println("Trying to add 1 more " + tempName + ".");
                generator.addGenerator(m);
            }
            else if(generator instanceof Farm){
                Farm tempGenerator = (Farm)generator;
                String tempName = tempGenerator.getName();
                System.out.print("Trying to add 1 more " + tempName + ".");
                generator.addGenerator(m);
            }
            else if(generator instanceof Forest){
                Forest tempGenerator = (Forest)generator;
                String tempName = tempGenerator.getName();
                System.out.print("Trying to add 1 more " + tempName + ".");
                generator.addGenerator(m);
            }
        }
        else{
            System.out.print(name + "was not added.");
        }
    }

    /**
     * upgrading generator
     *
     * @param Generator generator to upgrade
     * @param Money money(user need pay money when they upgrade generator)
     */
    public void upgradeGenerator(Generator generator, Money m){
        String name = generator.getName();
        System.out.print("Do you want to upgrade " + name + "? (Y or N):");
        String input_text = scanner.nextLine();
        input_text = input_text.toLowerCase();
        if(input_text.equals("y")){
            if(generator instanceof GoldMine){
                GoldMine tempGenerator = (GoldMine)generator;
                String tempName = tempGenerator.getName();
                System.out.println("Trying to upgrade " + tempName + ".");
                generator.upgrading(m);
            }
            else if(generator instanceof Farm){
                Farm tempGenerator = (Farm)generator;
                String tempName = tempGenerator.getName();
                System.out.print("Trying to upgrade " + tempName + ".");
                generator.upgrading(m);
            }
            else if(generator instanceof Forest){
                Forest tempGenerator = (Forest)generator;
                String tempName = tempGenerator.getName();
                System.out.print("Trying to upgrade " + tempName + ".");
                generator.upgrading(m);
            }
        }
        else{
            System.out.print(name + "was not added.");
        }
    }

    /**
     * paying resources
     *
     * @param int turn of this game
     * @param Resource resource to pay (take parents class which is Resource)
     */
    public void payResources(int turn, Resource resource){
        //paying money
        if(resource instanceof Money){
            continuity = true;
            System.out.println();
            System.out.println("How much money do you want to spent?");
            while (continuity){
                Money money = (Money) resource;
                double tempMoney = money.getTotalMoney();
                System.out.println("\t1. All of money.(leave only $1.00)");
                System.out.println("\t2. Minimum amount of money.");
                System.out.println("\t3. Choose amount by yourself.");
                choice = input.nextInt();
                switch(choice){
                    //Pay all of money but leave 1 to avoid critical
                    case 1:
                        choseMinimumMoneyPayment = false;
                        String moneyString = String.format("%.2f",tempMoney);
                        System.out.println("You have $" + moneyString + ". Are you sure you spent all of them?(leave only $1.00)(y/n)");
                        input.nextLine();
                        ynAns = input.nextLine();
                        ynAns = ynAns.toLowerCase();
                        if(ynAns.equals("y")){
                            tempMoney = money.getTotalMoney() - 1.00;
                            moneyString = String.format("%.2f",tempMoney);
                            System.out.println("You spend $" + moneyString); 
                            money.setTotalMoney(1);
                            continuity = false;
                            if (turn == 1){
                                currentPay = tempMoney;
                                priviousPay = currentPay;
                            }
                            else{
                                priviousPay = currentPay;
                                currentPay = tempMoney;
                            }
                        }
                        else{
                            System.out.println("Chose how much money do you spent?");
                        }
                        break;

                    //Pay minimum amount of money
                    case 2:
                    priviousPay = currentPay;
                    if(turn == 1){
                        String minPayString = String.format("%.2f",minPay);
                        System.out.println("Minimum pay is $" + minPayString + ".");
                        tempMoney = tempMoney - minPay;
                        if(tempMoney >= 0){
                            System.out.println("You paid $" + minPayString + ".");
                            choseMinimumMoneyPayment = true;
                        }
                        else{
                            double paidMoney = minPay + tempMoney;
                            String minPayPaidString = String.format("%.2f",paidMoney);
                            System.out.println("You didn't have enough money to make minimum payment.");
                            System.out.println("You paid $" + minPayPaidString + ".");
                            tempMoney = 0.00;
                            choseMinimumMoneyPayment = false;

                        }
                        String paidString = String.format("%.2f",tempMoney);
                        System.out.println("You have $" + paidString + " now.");
                        money.setTotalMoney(tempMoney);
                        continuity = false;
                        currentPay = tempMoney - (tempMoney - minPay);
                        priviousPay = currentPay;
                    }
                    else{
                        if(minPay > priviousPay){
                            minPay = minPay * 1.1;
                        }
                        else{
                            minPay = priviousPay * 1.1;
                        }
                        String minPayString = String.format("%.2f",minPay);
                        System.out.println("Minimum $" + minPayString + ".");
                        tempMoney = tempMoney - minPay;
                        if(tempMoney >= 0){
                            System.out.println("You paid $" + minPayString + ".");
                            choseMinimumMoneyPayment = true;
                        }
                        else{
                            double paidMoney = minPay + tempMoney;
                            String minPayPaidString = String.format("%.2f",paidMoney);
                            System.out.println("You didn't have enough money to make minimum payment.");
                            System.out.println("You paid $" + minPayPaidString + ".");
                            tempMoney = 0.00;
                            choseMinimumMoneyPayment = false;
                        }
                        String paidString = String.format("%.2f",tempMoney);
                        System.out.println("You have $" + paidString + " now.");
                        money.setTotalMoney(tempMoney);
                        continuity = false;
                        priviousPay = currentPay;
                        currentPay = tempMoney - (tempMoney - minPay);
                    }
                    break;

                    //User chose amount of money to pay
                    case 3:
                    choseMinimumMoneyPayment = false;
                    System.out.println("Choose amount: ");
                    subChoice = subInput.nextDouble();
                    double payAmount = subChoice;
                    tempMoney = tempMoney - payAmount;
                    if(tempMoney >= 0){
                        if(priviousPay > payAmount){
                            System.out.println("You pay less than previous round.");
                        }
                        String paidString = String.format("%.2f",payAmount);
                        System.out.println("You paid $" + paidString + ".");
                        
                        money.setTotalMoney(tempMoney);
                        if(turn == 1){
                            currentPay = tempMoney - (tempMoney - payAmount);
                            priviousPay = currentPay;
                        }
                        else{
                            priviousPay = currentPay;
                            currentPay = tempMoney - (tempMoney - payAmount);
                        }
                        continuity = false;
                    }
                    else{
                        payAmount = payAmount + tempMoney;
                        System.out.println("You don't have enough money to make a payment.");
                        if(priviousPay > payAmount){
                            System.out.println("You pay less than previous round.");
                        }
                        String paidString = String.format("%.2f",payAmount);
                        System.out.println("You paid $" + paidString + ".");
                        tempMoney = 0;
                        money.setTotalMoney(tempMoney);
                        if(turn == 1){
                            currentPay = tempMoney - (tempMoney - payAmount);
                            priviousPay = currentPay;
                        }
                        else{
                            priviousPay = currentPay;
                            currentPay = tempMoney - (tempMoney - payAmount);
                        }
                        continuity = false;
                    }
                    break;
                }
            }
        }

        //use grain
        else if(resource instanceof Grain){
            continuity = true;
            System.out.println();
            System.out.println("How many grain do you want to spent?");
            while (continuity){
                Grain grain = (Grain) resource;
                int tempGrain = grain.getQuantity();
                System.out.println("How many grain do you want to spent?");
                System.out.println("\t1. All of grain.(leave only 1 grain)");
                System.out.println("\t2. Minimum amount of grain.");
                System.out.println("\t3. Choose amount.");
                choice = input.nextInt();
                switch(choice){
                    //Use all of grain but leave 1 to avoid critical
                    case 1:
                    choseMinimumGrainPayment = false;
                    System.out.println("You have " + tempGrain + " of grain. Are you sure you spent all of them?(leave only 1 grain)(y/n)");
                    input.nextLine();
                    ynAns = input.nextLine();
                    ynAns = ynAns.toLowerCase();
                    if(ynAns.equals("y")){
                        tempGrain = grain.getQuantity() - 1;
                        System.out.println("You used  " + tempGrain + " of grain."); 
                        grain.setQuantity(1);
                        continuity = false;
                        if(turn == 1){
                            currentGrain = tempGrain;
                            priviousGrain = currentGrain;
                        }
                        else{
                            priviousGrain = currentGrain;
                            currentGrain = tempGrain;
                        }
                    }
                    else{
                        System.out.println("Chose how many of grain do you spent?");
                    }
                    break;

                    //Use minimum amount of grain
                    case 2:
                    priviousGrain = currentGrain;
                    if(turn == 1){
                        System.out.println("Minimum grain payment amount is " + minGrain + ".");
                        tempGrain = (int)(tempGrain - minGrain);
                        if(tempGrain>=0){
                            System.out.println("You used " + minGrain + " of grain.");
                            choseMinimumGrainPayment = true;
                        }
                        else{
                            int paindAmount = (int)(minGrain + tempGrain);
                            System.out.println("You didn't have enough amount of grain to make minimum payment.");
                            System.out.println("You used " + paindAmount + " of grain.");
                            tempGrain = 0;
                            choseMinimumGrainPayment = false;
                        }
                        System.out.println("You have " + tempGrain + " of grain now.");
                        grain.setQuantity(tempGrain);
                        continuity = false;
                        currentGrain = (int)(tempGrain - (tempGrain - minGrain));
                        priviousGrain = currentGrain;
                    }
                    else{
                        if(minGrain > priviousGrain){
                            minGrain = (int)(minGrain * 1.1);
                        }
                        else{
                            minGrain = (int)(priviousGrain * 1.1);
                        }
                        System.out.println("Minimum grain amount is " + minGrain + ".");
                        tempGrain = (int)(tempGrain - minGrain);
                        if(tempGrain>=0){
                            System.out.println("You used " + minGrain + " of grain.");
                            choseMinimumGrainPayment = true;
                        }
                        else{
                            int paiedAmount = (int)(minGrain + tempGrain);
                            System.out.println("You didn't have enough amount of grain to make minimum payment.");
                            System.out.println("You used " + paiedAmount + " of grain.");
                            tempGrain = 0;
                            choseMinimumGrainPayment = false;
                        }
                        System.out.println("You have " + tempGrain + " of grain now.");
                        grain.setQuantity(tempGrain);
                        continuity = false;
                        
                        priviousGrain = currentGrain;
                        currentGrain = (int)(tempGrain - (tempGrain - minGrain));
                    }
                    break;

                    //User chose amount of grain to use
                    case 3:
                    choseMinimumGrainPayment = false;
                    System.out.println("Choose amount: ");
                    subChoice = subInput.nextDouble();
                    int usedGrainAmount = (int)subChoice;
                    tempGrain = tempGrain - usedGrainAmount;
                    if(tempGrain >= 0){
                        if(priviousGrain > usedGrainAmount){
                            System.out.println("You used less grain than previous round.");
                        }
                        System.out.println("You used " + usedGrainAmount + " of grain.");
                        
                        grain.setQuantity(tempGrain);
                        if(turn == 1){
                            currentGrain = tempGrain - (tempGrain - usedGrainAmount);
                            priviousGrain = currentGrain;
                        }
                        else{
                            priviousGrain = currentGrain;
                            currentGrain = tempGrain - (tempGrain - usedGrainAmount);
                        }
                        continuity = false;
                    }
                    else{
                        usedGrainAmount = usedGrainAmount + tempGrain;
                        System.out.println("You didn't have enough amount of grain to make payment.");
                        if(priviousGrain > usedGrainAmount){
                            System.out.println("You used less grain than previous round.");
                        }
                        System.out.println("You used " + usedGrainAmount + " of grain.");
                        tempGrain = 0;
                        grain.setQuantity(tempGrain);
                        if(turn == 1){
                            currentGrain = tempGrain - (tempGrain - usedGrainAmount);
                            priviousGrain = currentGrain;
                        }
                        else{
                            priviousGrain = currentGrain;
                            currentGrain = tempGrain - (tempGrain - usedGrainAmount);
                        }
                        continuity = false;
                    }
                    break;
                }
            }
        }

        //use woods
        else if(resource instanceof Wood){
            continuity = true;
            System.out.println();
            System.out.println("How many processed woods do you want to spent?");
            while (continuity){
                Wood wood = (Wood) resource;
                int tempWood = wood.getProcessedWood();
                System.out.println("\t1. All of woods.");
                System.out.println("\t2. Minimum amount of woods.");
                System.out.println("\t3. Choose amount.");
                choice = input.nextInt();
                switch(choice){
                    //Use all of processed wood
                    case 1:
                    choseMinimumWoodPayment = false;
                    System.out.println("You have " + tempWood + " of processed wood. Are you sure you spent all of them? (y/n)");
                    input.nextLine();
                    ynAns = input.nextLine();
                    ynAns = ynAns.toLowerCase();
                    if(ynAns.equals("y")){
                        System.out.println("You used " + tempWood + " of processed wood."); 
                        wood.setProcessedWood(0);
                        wood.setQuantity(0);
                        continuity = false;
                        if(turn == 1){
                            currentWood = tempWood;
                            priviousWood = currentWood;
                        }
                        else{
                            priviousWood = currentWood;
                            currentWood = tempWood;
                        }
                    }
                    else{
                        System.out.println("Chose how many processed wood do you spent?");
                    }
                    break;

                    //Use minimum amount of processed wood
                    case 2:
                    priviousWood = currentWood;
                    if(turn == 1){
                        System.out.println("Minimum wood payment amount is " + minWood + ".");
                        tempWood = (int)(tempWood - minWood);
                        if(tempWood>=0){
                            System.out.println("You used " + minWood + " of processed wood.");
                            choseMinimumWoodPayment = true;
                        }
                        else{
                            int usedWoodAmount = (int)(minWood+tempWood);
                            System.out.println("You didn't have enough amount of wood to make minimum payment.");
                            System.out.println("You used " + usedWoodAmount + " of processed wood.");
                            tempWood = 0;
                            choseMinimumWoodPayment = false;
                        }
                        System.out.println("You have " + tempWood + " of processed wood now.");
                        wood.setProcessedWood(tempWood);
                        wood.setQuantity(tempWood);
                        continuity = false;
                        currentWood = (int)(tempWood - (tempWood - minWood));
                        priviousWood = currentWood;
                    }
                    else{
                        if(minWood > priviousWood){
                            minWood = (int)(minWood * 1.1);
                        }
                        else{
                            minWood = (int)(priviousWood * 1.1);
                        }
                        System.out.println("Minimum wood payment amount is " + minWood + ".");
                        tempWood = (int)(tempWood - minWood);
                        if(tempWood>=0){
                            System.out.println("You used " + minWood + " of processed wood.");
                            choseMinimumWoodPayment = true;
                        }
                        else{
                            int usedWoodAmount = (int)(minWood+tempWood);
                            System.out.println("You didn't have enough amount of wood to make minimum payment.");
                            System.out.println("You used " + usedWoodAmount + " of processed wood.");
                            tempWood = 0;
                            choseMinimumWoodPayment = false;
                        }
                        System.out.println("You have " + tempWood + " of processed wood now.");
                        wood.setProcessedWood(tempWood);
                        wood.setQuantity(tempWood);
                        continuity = false;
                        priviousWood = currentWood;
                        currentWood = (int)(tempWood - (tempWood - minWood));
                    }
                    break;

                    //User chose amount of processed wood to use
                    case 3:
                    choseMinimumWoodPayment = false;
                    System.out.println("Choose amount: ");
                    subChoice = subInput.nextDouble();
                    int usedWoodAmount = (int)subChoice;
                    tempWood = tempWood - usedWoodAmount;
                    if(tempWood >= 0){
                        if(priviousWood > usedWoodAmount){
                            System.out.println("You used less processed wood than previous round.");
                        }
                        System.out.println("You used " + usedWoodAmount + " of processed wood.");
                        wood.setProcessedWood(tempWood);
                        wood.setQuantity(tempWood);
                        if(turn == 1){
                            currentWood = tempWood - (tempWood - usedWoodAmount); 
                            priviousWood = currentWood;                          
                        }
                        else{
                            priviousWood = currentWood;
                            currentWood = tempWood - (tempWood - usedWoodAmount);                            
                        }
                        continuity = false;
                    }
                    else{
                        usedWoodAmount = usedWoodAmount + tempWood;
                        System.out.println("You didn't have enough amount of wood to make payment.");
                        if(priviousWood > usedWoodAmount){
                            System.out.println("You used less processed wood than previous round.");
                            System.out.println("You used " + usedWoodAmount + " of processed wood.");
                            tempWood = 0;
                            wood.setProcessedWood(tempWood);
                            wood.setQuantity(tempWood);
                            if(turn == 1){
                                currentWood = tempWood - (tempWood - usedWoodAmount); 
                                priviousWood = currentWood;                          
                            }
                            else{
                                priviousWood = currentWood;
                                currentWood = tempWood - (tempWood - usedWoodAmount);                            
                            }
                            continuity = false;
                        }
                    }
                    break;
                }
            }
        }
        else{
            System.out.println("error");
        }
    }

    /**
     * calculate % of chenge of money payment
     *
     * @return rate of chenge of money payment. If its bigger than 1, payment increased
     */
    public double calculatePercentageOfChengeForMoney(){
        if(priviousPay == 0){
            return 1.1;
        }
        else{
            if(currentPay == 0){
                double rate = (1/priviousPay);
                return rate;
            }
            else{
                double rate = (currentPay/priviousPay);
                return rate;
            }
        }
    }

    /**
     * calculate % of chenge of grain payment
     *
     * @return rate of chenge of grain payment. If its bigger than 1, payment increased
     */
    public double calculatePercentageOfChengeForGrain(){
        if(priviousGrain == 0){
            return 1.1;
        }
        else{
            if(currentGrain == 0){
                double rate = (1/priviousGrain);
                return rate;
            }
            else{
                double rate = (currentGrain/priviousGrain);
                return rate;
            }
        }
    }

    /**
     * calculate % of chenge of wood payment
     *
     * @return rate of chenge of wood payment. If its bigger than 1, payment increased
     */
    public double calculatePercentageOfChengeForWood(){
        if(priviousWood == 0){
            return 1.1;
        }
        else{
            if(currentWood == 0){
                double rate = (1/priviousWood);
                return rate;
            }
            else{
                double rate = (currentWood/priviousWood);
                return rate;
            }
        }
    }

    /**
     * calculate average of % of chenge of above % chenges(money payment, grain payment and wood payment)
     *
     * @return average of % of chenge of money payment, grain payment and wood payment. If its bigger than 1, payment increased
     */
    public double calculatePercentageOfChenge(int turn){
        if(turn == 1){
            persentChenge = 1.1;
        }
        else if(choseMinimumMoneyPayment && choseMinimumGrainPayment && choseMinimumWoodPayment){
            persentChenge = 1.1;
        }
        else{
            persentChenge = (calculatePercentageOfChengeForMoney()+calculatePercentageOfChengeForGrain()+calculatePercentageOfChengeForWood())/3;
        }
        return persentChenge;
    }

    /**
     * Chenge population based of average of % of chenge
     *
     * @param int turn of this game
     */
    public void populationChenge(int turn){
        int currentPopulation = this.getPopulation();
        double tempNewPopulation = currentPopulation * calculatePercentageOfChenge(turn);
        if(calculatePercentageOfChenge(turn) > 0 && currentPopulation < 10){
            tempNewPopulation = currentPopulation + 1 ;
        }
        this.setPopulation((int)tempNewPopulation);
        int newPopulation = this.getPopulation();
        if(newPopulation > currentPopulation){
            System.out.println("population increased.");
        }
        else if(newPopulation < currentPopulation){
            System.out.println("population decreased.");
        }
        else{
            System.out.println("stay the same.");
        }
        setPopulation(newPopulation);
    }

    /**
     * method when it's used when round ends
     *
     * @param int turn of this game
     * @param Resource Resource to pay
     */
    public void endRound(int turn, Resource resource){
        payResources(turn, resource);
    }

    /**
     * display resources and score
     *
     * @param double total money
     * @param int total grain
     * @param int total non processed wood
     * @param int total processed wood
     */
    public void display(double money, int grain, int npwood, int pwood){
        String moneyString = String.format("%.2f",money);
        System.out.println("------------------------------");
        System.out.println("The population is " + population + ".");
        System.out.println("The total abount of money is " + moneyString + ".");
        System.out.println("The total abount of grain is " + grain + ".");
        System.out.println("The total abount of non processed wood is " + npwood + ".");
        System.out.println("The total abount of processed wood is " + pwood + ".");
        System.out.println("Your current score is " + totalScore + ".");
        System.out.println("------------------------------");
    }

    /**
     * display only score when game finished
     */
    public void finalDisplay(){
        System.out.println("------------------------------");
        System.out.println("Your final score is " + totalScore + ".");
        System.out.println("------------------------------");
    }

    /**
     * Calculate score with out event
     *
     * @param Generator Generator that is used get score based on adding and upgrading bonus
     * @param Resource Resource that is used get score based on quontity
     */
    public void calcScore(Generator g, Resource r){
        int bonusScore = g.scoreImpact();
        currentBonusScore = currentBonusScore + bonusScore;
        int basicScore = r.scoreImpact();
        currentBasicScore = currentBasicScore + basicScore;
        int populationScore = population;
        currentPopulationScore = currentPopulationScore + populationScore;
        totalScore = totalScore + bonusScore + basicScore + populationScore;
    }

    /**
     * Calculate score with  event
     *
     * @param boolean Event is occured or not
     * @param Event Event that is used get score
     */
    public void calcScore(boolean event, Event e){
        int eventScore = e.scoreImpact();
        currentEventScore = currentEventScore + eventScore;
        if(event){
            totalScore = totalScore + eventScore;
        }
        else{
            ;
        }
        eventScore = 0;
    }

    /**
     * displaying detail of scores
     */
    public void displayScore(){
        System.out.println("---------Scores Detail--------");
        System.out.println("Basic Resource Score: " + currentBasicScore);
        System.out.println("Basic Population Score: " + currentPopulationScore);
        System.out.println("Generator Bonus Score: " + currentBonusScore);
        System.out.println("Event Point Deduction: " + currentEventScore);
        System.out.println("Total Score: " + totalScore);
        System.out.println("------------------------------");
        currentBasicScore = 0;
        currentPopulationScore = 0;
        currentBonusScore = 0;
        currentEventScore = 0;
    }

    /**
     * checking pupulation is positive or not(if it's 0 or less return ture)
     */
    public boolean isCritical(){
        if(population <= 0){
            return true;
        }
        return false;
    }

    /**
     * calculate worker number based on current population
     */
    public int workerNumUpdate(){
        int returnNum = (int)(population/3);
        return returnNum;
    }
}

