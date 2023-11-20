package org.game;

import org.gameSounds.SoundPlayer;
import org.graphics.Renderer;
import org.object.*;
import org.object.Items.Sword;
import org.object.Mobs.Cow;
import org.object.Mobs.Player;
import org.object.Obstacles.Bridge;
import org.object.Obstacles.Door.Door;
import org.object.Obstacles.Door.Key;
import org.object.Obstacles.Door.SideDoor;
import org.object.Obstacles.Fence.HorizontalFence;
import org.object.Obstacles.Fence.VerticalFence;
import org.object.Mobs.Grift;
import org.object.Obstacles.House;
import org.object.Obstacles.River;
import org.world.World;

public class Game {
    public static Object quit;
    public static int worldCount = 1;
    public static void main(String[] args) {
        System.out.println("Starting!");
        Renderer.init();


        //loadWorldOne();
        //loadWorldTwo();
        loadWorldThree();
        //loadWorldFour();
        //loadWorldFive();
        //loadWorldSix();

    }

    public static void quit()   {

        System.exit(0);

    }


    public static void loadWorldOne()   {
        World.currentWorld = new World();


        int[] xPoints = {420, 390, 350, 370, 350, 520, 470};
        int[] yPoints = {0, 0, 0, 0, 305, 325, 325};
        int numPoints = 7;
        Player player = new Player(150, 150, false, false);



        World.currentWorld.addSprite(new HorizontalFence(150, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(200, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(250, 34, 50, 18));
        World.currentWorld.addSprite(new VerticalFence(125, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(125, 100 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 100 , 6, 50));
        World.currentWorld.addSprite(new River(350, 0, xPoints, yPoints, numPoints));

        World.currentWorld.addSprite(new WinArea(200, 80, 125, 80));
        World.currentWorld.addSprite(new House(225, 215,74, 79));



        World.currentWorld.addSprite(new Bridge(400, 150, 200, 50));
        World.currentWorld.addSprite(new Cow(600, 50, player));

        World.currentWorld.addSprite(player);


        SoundPlayer.playBackgroundMusic();
    }

    public static void loadWorldTwo()   {

        World.currentWorld = new World();
        Player player = new Player(150, 150, false, false);
        Cow cow = new Cow(600, 50, player);
        World.currentWorld.addSprite(new HorizontalFence(150, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(200, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(250, 34, 50, 18));
        World.currentWorld.addSprite(new VerticalFence(125, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(125, 100 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 100 , 6, 50));
        World.currentWorld.addSprite(new WinArea(200, 80, 125, 80));
        World.currentWorld.addSprite(player);
        World.currentWorld.addSprite(cow);
        World.currentWorld.addSprite(new Grift(20 ,20, cow ));
    }


    public static void loadWorldThree()   {

        World.currentWorld = new World();
        Player player = new Player(150, 150, true, false);
        Cow cow = new Cow(600, 50, player);
        World.currentWorld.addSprite(new HorizontalFence(150, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(200, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(250, 34, 50, 18));
        World.currentWorld.addSprite(new VerticalFence(125, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(125, 100 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 100 , 6, 50));



        World.currentWorld.addSprite(new WinArea(200, 80, 125, 80));
        World.currentWorld.addSprite(player);
        World.currentWorld.addSprite(cow);
        World.currentWorld.addSprite(new Grift(20 ,20, cow ));
        World.currentWorld.addSprite(new Grift(20 ,80, cow ));
        World.currentWorld.addSprite(new Grift(20 ,120, cow ));
        World.currentWorld.addSprite(new Grift(20 ,160, cow ));
        World.currentWorld.addSprite(new Grift(20 ,220, cow ));
        World.currentWorld.addSprite(new Sword(180, 180));

    }

    public static void loadWorldFour()   {
        World.currentWorld = new World();
        Player player = new Player(150, 150, true, false);
        Cow cow = new Cow(600, 50, player);

        World.currentWorld.addSprite(new HorizontalFence(150, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(200, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(250, 34, 50, 18));
        World.currentWorld.addSprite(new VerticalFence(125, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(125, 100 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 100 , 6, 50));


        World.currentWorld.addSprite(new VerticalFence(400, 0, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 50, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 100, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 150, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 200, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 250, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 300, 6, 50));

        World.currentWorld.addSprite(new HorizontalFence(0, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(50, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(100, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(150, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(200, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(250, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(300, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(350, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(400, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(450, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(500, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(550, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(600, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(650, 0, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(700, 0, 50, 18));

        World.currentWorld.addSprite(new HorizontalFence(0, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(50, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(100, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(150, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(200, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(250, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(300, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(350, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(400, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(450, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(500, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(550, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(600, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(650, 280, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(700, 280, 50, 18));

        World.currentWorld.addSprite(new WinArea(200, 80, 125, 80));
        World.currentWorld.addSprite(player);
        World.currentWorld.addSprite(cow);
    }

    public static void loadWorldFive()   {
        World.currentWorld = new World();
        Player player = new Player(150, 150, true, false);
        Cow cow = new Cow(600, 50, player);

        World.currentWorld.addSprite(new HorizontalFence(150, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(200, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(250, 34, 50, 18));
        World.currentWorld.addSprite(new VerticalFence(125, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(125, 100 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 100 , 6, 50));

        World.currentWorld.addSprite(new VerticalFence(0, 0, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(0, 50, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(0, 100, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(0, 150, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(0, 200, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(0, 250, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(0, 300, 6, 50));


        World.currentWorld.addSprite(new VerticalFence(400, 0, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 50, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 100, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 150, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 200, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 250, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(400, 300, 6, 50));

        World.currentWorld.addSprite(new VerticalFence(685, 0, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(685, 50, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(685, 100, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(685, 150, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(685, 200, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(685, 250, 6, 50));
        World.currentWorld.addSprite(new VerticalFence(685, 300, 6, 50));

        World.currentWorld.addSprite(new WinArea(200, 80, 125, 80));
        World.currentWorld.addSprite(player);
        World.currentWorld.addSprite(cow);
    }

    public static void loadWorldSix()   {
        World.currentWorld = new World();
        Player player = new Player(150, 200, true, false);
        Cow cow = new Cow(600, 50, player);

        World.currentWorld.addSprite(new HorizontalFence(150, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(200, 34, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(250, 34, 50, 18));
        World.currentWorld.addSprite(new VerticalFence(125, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 50 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(125, 100 , 6, 50));
        World.currentWorld.addSprite(new VerticalFence(275, 100 , 6, 50));

        World.currentWorld.addSprite(new HorizontalFence(147, 130, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(253, 130, 50, 18));
        World.currentWorld.addSprite(new HorizontalFence(210, 130, 12, 18));

        World.currentWorld.addSprite(new Door(183, 128));
        World.currentWorld.addSprite(new Door(205, 128));



        World.currentWorld.addSprite(player);
        World.currentWorld.addSprite(cow);
        World.currentWorld.addSprite(new Key(100, 150));
        World.currentWorld.addSprite(new Key(200, 200));
        World.currentWorld.addSprite(new WinArea(200, 80, 125, 80));



    }




    public static void loadNextWorld()   {

        worldCount++;


// Use a switch statement to handle different world counts
        switch (worldCount) {
            case 2:
                loadWorldTwo();
                break;
            case 3:
                loadWorldThree();
                break;
            case 4:
                loadWorldFour();
                break;
            case 5:
                loadWorldFive();
                break;
            case 6:
                loadWorldSix();
                break;


            default:
                // Load a default world or handle any specific requirements
                loadWorldOne();
                break;
        }


    }

    public static void resetLevel()   {
        switch (worldCount) {
            case 2:
                loadWorldTwo();
                break;
            case 3:
                // Load the third world
                break;
            // Add more cases as needed
            default:
                // Load a default world or handle any specific requirements
                loadWorldOne();
                break;
        }
    }
}