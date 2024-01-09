package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.ground.*;

import game.ground.Dirt;
import game.ground.Floor;

/**
 * The main class for the Mario World game.
 */
public class Application {

    /**
     * Main method to run the game
     *
     * @param args String array argument
     */
    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(),
                new Sprout(), new HealthFountain(), new PowerFountain());
        FancyGroundFactory lavaGroundFactory = new FancyGroundFactory(new Dirt(), new Wall(),
                new Floor(), new Sprout(), new Lava());

        List<String> map = Arrays.asList(
                "..........................................##..........+.........................",
                "............+............+..................#...................................",
                "............................................#...................................",
                ".............................................##......................+..........",
                "...............................................#................................",
                "................................................#...............................",
                ".................+................................#.............................",
                ".................................................##.............................",
                "................................................##..............................",
                ".........+..............................+#____####.................+............",
                ".......................................+#_____###++.............................",
                ".......................................+#______###..............................",
                "........................................+#_____###..............................",
                "........................+........................##.............+...............",
                "...................................................#............................",
                "....................................................#...........................",
                "...................+.................................#..........................",
                "......................................................#.........................",
                ".......................................................##.......................");

        List<String> map1 = Arrays.asList(
                "....................L............___......##.....",
                "............+.....L......+.........__.......#....",
                "......L.........L..............L.___......L.#....",
                "...............L....L........................##..",
                "...............L...L....+......L.................",
                "..............L...L......_____...................",
                "..........+....L...........___.....L.............",
                "...................+........_....L..........L....",
                "......L.......L.+..........L....L................",
                "...............................L..L..............");

        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap lavaMap = new GameMap(lavaGroundFactory, map1);
        world.addGameMap(gameMap);
        world.addGameMap(lavaMap);

        //Initialise new WarpPipe and add it to the lava map
        WarpPipe wp = new WarpPipe();
        lavaMap.at(0, 0).setGround(wp);
        Location destination = lavaMap.at(0, 0);

        //Initialise new WarpPipe and add it to the game map
        WarpPipe wp1 = new WarpPipe(lavaMap, destination, wp);
        WarpPipe wp2 = new WarpPipe(lavaMap, destination, wp);
        WarpPipe wp3 = new WarpPipe(lavaMap, destination, wp);
        WarpPipe wp4 = new WarpPipe(lavaMap, destination, wp);
        WarpPipe wp5 = new WarpPipe(lavaMap, destination, wp);
        WarpPipe wp6 = new WarpPipe(lavaMap, destination, wp);
        WarpPipe wp7 = new WarpPipe(lavaMap, destination, wp);
        WarpPipe wp8 = new WarpPipe(lavaMap, destination, wp);

        //Add the wrap pipe to self defined location
        gameMap.at(10, 1).setGround(wp1);
        gameMap.at(5, 10).setGround(wp2);
        gameMap.at(18, 17).setGround(wp3);
        gameMap.at(53, 4).setGround(wp4);
        gameMap.at(30, 13).setGround(wp5);
        gameMap.at(37, 8).setGround(wp6);
        gameMap.at(58, 13).setGround(wp7);
        gameMap.at(39, 6).setGround(wp8);

        // Initialise health fountain and power fountain and their locations
        HealthFountain healthFountain = new HealthFountain();
        PowerFountain powerFountain =new PowerFountain();
        Location healthFountainLocation = gameMap.at(38, 10);
        Location powerFountainLocation = gameMap.at(42, 13);

        // Add fountains to map and drinkableManager
        healthFountainLocation.setGround(healthFountain);
        powerFountainLocation.setGround(powerFountain);
        DrinkableManager.getInstance().appendDrinkableGround(healthFountainLocation, healthFountain);
        DrinkableManager.getInstance().appendDrinkableGround(powerFountainLocation, powerFountain);

        Actor mario = new Player("Mario", 'm', 1000);
        Actor toad = new Toad();
        Actor princessPeach = new PrincessPeach();
        Actor bowser = new Bowser();

        gameMap.at(44, 10).addActor(toad);
        lavaMap.at(18, 7).addActor(princessPeach);
        lavaMap.at(17, 7).addActor(bowser);
        world.addPlayer(mario, gameMap.at(42, 10));

        world.run();
    }
}
