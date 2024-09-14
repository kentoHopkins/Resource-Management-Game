import java.util.*;
public class GoldMine extends Generator{
    private int goldRate;
    private int gold24Amount;
    private int gold22Amount;
    private int gold18Amount;
    private int gold14Amount;
    private int gold10Amount;
    private double producingRate;
    private int producedGold;
    private double amountOfGoldMine;
    private int totalQuantity;
    private int workerNum;

    //constructor
    /**
     * Creates a new GoldMine with super constructor.
     *
     * @param int number of constructed
     * @param Gold resource
     */
    public GoldMine(int numberConstructed, Gold gold){
        super("Gold mine", numberConstructed, gold);
    }

    /**
     * Get % that is used to determine what type of gold user get.
     */
    public int goldPersentage(){
        int rate;
        Random rand = new Random();
        rate = rand.nextInt(100);
        return rate;
    }
    
    /**
     * Mining gold
     *
     * @param Gold resource
     */
    public void goldMining(Gold g){
        producingRate = super.getProducingRatePerPerson();
        workerNum = super.getWorkerNum();
        amountOfGoldMine = super.getAmountOfGenerator();
        producedGold = (int)((double)producingRate * workerNum * amountOfGoldMine);
        totalQuantity = g.getTotalQuantity() + producedGold;
        g.setTotalQuantity(totalQuantity);
        for (int i = 1; i <= producedGold; i++){
            goldRate = goldPersentage();
            if (goldRate <= 3){
                gold24Amount = g.getQuantity24() + 1;
                g.setQuantity24(gold24Amount);
                gold24Amount = 0;
            }
            else if ((goldRate > 3) && (goldRate <= 13)){
                gold22Amount = g.getQuantity22() + 1;
                g.setQuantity22(gold22Amount);
                gold22Amount = 0;
            }
            else if ((goldRate > 13) && (goldRate <= 28)){
                gold18Amount = g.getQuantity18() + 1;
                g.setQuantity18(gold18Amount);
                gold18Amount = 0;
            }
            else if ((goldRate > 28) && (goldRate <= 53)){
                gold14Amount = g.getQuantity14() + 1;
                g.setQuantity14(gold14Amount);
                gold14Amount = 0;
            }
            else{
                gold10Amount = g.getQuantity10() + 1;
                g.setQuantity10(gold10Amount);
                gold10Amount = 0;
            }
        }
        g.displayGold();
    }
}
