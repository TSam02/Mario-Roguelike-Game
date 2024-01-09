package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Coin;
import game.wallet.WalletManager;

/**
 * Action to represent picking up Coin and add Coin's value to Player's wallet
 * Actor doesn't actually put coin into inventory, instead it just adds the coin value into wallet balance
 */
public class PickUpCoinAction extends Action {
    private Coin coin;

    /**
     * Default constructor for PickUpCoinAction
     *
     * @param coin the coin that would be picked up and value would be added
     */
    public PickUpCoinAction(Coin coin) {
        this.coin = coin;
    }

    /**
     * Execution of PickUpCoinAction which adds coin value to player's wallet balance and removes the coin from map
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string showing coin along with its value being added to the respective actor's wallet
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        WalletManager.getInstance().getWallet(actor).addBalance(coin.getValue());
        map.locationOf(actor).removeItem(coin);
        return "$ " + coin.getValue() + " is added to " + actor + "'s wallet!";
    }

    /**
     * Message showing actor picked up the coin
     *
     * @param actor The actor performing the action.
     * @return message of actor picked up coin
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " picks up the " + coin + "($" + coin.getValue() + ")";
    }
}
