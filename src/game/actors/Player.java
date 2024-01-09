package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.ResetAction;
import game.reset.ResetManager;
import game.reset.Resettable;
import game.wallet.Wallet;
import game.wallet.WalletManager;
import game.wallet.Walletable;


/**
 * Class representing the Player.
 */
public class Player extends Actor implements Walletable, Resettable {
    private final Menu menu = new Menu();
    private IntrinsicWeapon intrinsicWeapon;

    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */
    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addToWalletManager(this, new Wallet());
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.registerInstance();
        this.intrinsicWeapon = super.getIntrinsicWeapon();
    }

    /**
     * Increase damage of intrinsic weapon by 15 and set this as new intrinsic weapon
     */
    public void empowerIntrinsicWeapon() {
        this.intrinsicWeapon = new IntrinsicWeapon(this.intrinsicWeapon.damage() + 15, "punches");
    }

    /**
     * Get the intrinsic weapon
     *
     * @return the intrinsic weapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return intrinsicWeapon;
    }

    /**
     * Figure out what to do next.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        if (ResetManager.getAbleToReset()) {
            actions.add(new ResetAction());
        }

        Location playerLocation = map.locationOf(this);
        // Prints player's HP and location in console
        display.println(this + this.printHp() + " at " + playerLocation.getGround().toString() + "(" + playerLocation.x() + ", " + playerLocation.y() + ")");
        // Prints player's wallet balance in console
        display.println("Wallet balance: $" + WalletManager.getInstance().getWallet(this).getBalance());
        display.println("Inventory: " + this.getInventory());

        // Prints player's status in console accordingly
        if (this.hasCapability(Status.INVINCIBLE)) {
            display.println("MARIO IS INVINCIBLE!!");
        }
        if (this.hasCapability(Status.SUPER)) {
            display.println("MARIO IS FEELING SUPER!!");
        }
        // return/print the console menu
        return menu.showMenu(this, actions, display);
    }

    /**
     * Gets display character of player, usually is m, but after consuming Super Mushroom, display character becomes M
     *
     * @return display character of player when it has SUPER capability and when it doesn't
     */
    @Override
    public char getDisplayChar() {
        return this.hasCapability(Status.SUPER) ? Character.toUpperCase(super.getDisplayChar()) : super.getDisplayChar();
    }

    /**
     * Method to reset player
     */
    @Override
    public void resetInstance() {
        this.removeCapability(Status.SUPER);
        this.heal(this.getMaxHp());
        this.removeCapability(Status.RESET);
    }
}
