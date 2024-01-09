package game.wallet;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.HashMap;

/**
 * Class representing wallet manager which manages all the wallets
 */
public class WalletManager {

    /**
     * HashMap containing wallets
     */
    private HashMap<Actor,Wallet> walletCollection;

    /**
     * Instance of wallet manager
     */
    private static WalletManager instance;

    /**
     * Initialize new hashMap for walletManager
     */
    private WalletManager(){
        walletCollection = new HashMap<>();
    }

    /**
     * Get instance of wallet manager if instance exist, else create one
     *
     * @return instance of walletManager
     */
    public static WalletManager getInstance() {
        if (instance == null) {
            instance = new WalletManager();
        }
        return instance;
    }

    /**
     * Add wallet to hashMap of walletManager
     *
     * @param actor actor whom the wallet is belongs to
     * @param wallet wallet to be added to hashmap
     */
    public void addWallet(Actor actor, Wallet wallet){
        walletCollection.put(actor,wallet);
    }

    /**
     * Method to obtain wallet of actor
     *
     * @param actor actor holding the wallet
     * @return wallet of actor
     */
    public Wallet getWallet(Actor actor){
        return walletCollection.get(actor);
    }
}
