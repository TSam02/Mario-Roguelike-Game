package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.BuyableItem;
import game.wallet.WalletManager;

/**
 * TradeAction to buy item
 */
public class TradeAction extends Action {
    /**
     * BuyableItem to be bought
     */
    private BuyableItem buyableItem;

    /**
     * Constructor for TradeAction
     *
     * @param buyableItem buyable item that can be bought
     */
    public TradeAction(BuyableItem buyableItem) {
        this.buyableItem = buyableItem;
    }

    /**
     * Execute tradeAction
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string to describe if the trade is successful
     */
    // 2 question where to prompt toad, and how to add item to inventory
    @Override
    public String execute(Actor actor, GameMap map) {
        if (WalletManager.getInstance().getWallet(actor).getBalance() >= this.buyableItem.getBuyableItemPrice()) {
            WalletManager.getInstance().getWallet(actor).deductBalance(this.buyableItem.getBuyableItemPrice());
            this.buyableItem.purchasedBy(actor);
            return actor + " buys " + this.buyableItem;
        } else {
            return "You don't have enough coins!";
        }
    }

    /**
     * Method to describe the option for actor to trade
     *
     * @param actor The actor performing the action.
     * @return string describing what item can be bought in this action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + this.buyableItem + " ($" + this.buyableItem.getBuyableItemPrice() + ")";
    }

}
