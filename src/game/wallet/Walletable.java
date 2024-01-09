package game.wallet;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Walletable interface to allow actor to have a wallet
 */
public interface Walletable {

    /**
     * Add the wallet to wallet manager
     *
     * @param actor actor whom the wallet is belongs to
     * @param wallet wallet hold by the actor
     */
    default void addToWalletManager(Actor actor, Wallet wallet){
        WalletManager.getInstance().addWallet(actor,wallet);
    }
}
