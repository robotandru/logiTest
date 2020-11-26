package ro.mataoanu.logitech.coding.problem1;

/*
at first glance, I thought that going for the first or second house and skipping one  and choosing between the two end sums would solve it
the I saw that maybe skipping two houses made sense.
following this reasoning, why not skip 3? but skipping 3 houses makes no sense because not robbing the one in the middle would leave money on the table

so in the end it boils down to choosing to skip one or two houses
 */

public class MasterPlan {
    public static int rob(int[] houses) {
        int currentMoney = 0;//money in the sack before deciding to rob the current house
        int prevMoney = 0;//money in the sack before deciding to rob the previous house
        int newMoney;//money in the sack after deciding on the current house

        for(int money: houses){
            newMoney = Math.max(currentMoney, prevMoney+money);
            prevMoney=currentMoney;
            currentMoney=newMoney;
        }

        return currentMoney;
    }
}
