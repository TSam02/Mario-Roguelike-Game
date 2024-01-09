package game.wallet;

/**
 * Wallet of actor tracking value of money the actor has
 */
public class Wallet {

    /**
     * Balance of wallet
     */
    private int balance = 0;

    /**
     * Add balance to wallet
     *
     * @param balance value of money to be aded
     */
    public void addBalance(int balance){
        this.balance += balance;
    }

    /**
     * Deduct balance from wallet
     *
     * @param balance value of money to be deducted
     */
    public void deductBalance(int balance){
        this.balance -= balance;
    }

    /**
     * Get balance of wallet
     *
     * @return balance of wallet
     */
    public int getBalance(){
        return this.balance;
    }
}
