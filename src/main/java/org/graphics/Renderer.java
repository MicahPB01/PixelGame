package org.graphics;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.util.Objects;

import org.constants.Constants;
import org.game.Game;
import org.input.Input;
import org.world.World;

import javax.imageio.ImageIO;

public class Renderer {

    private static Frame frame;
    private static Canvas canvas;

    private static int canvasWidth = 0;
    private static int canvasHeight = 0;

    private static final int GAME_WIDTH_DEFAULT = 400;
    private static final int GAME_HEIGHT_DEFAULT = 250;

    public static int gameWidth = 0;
    public static int gameHeight = 0;

    private static long lastFpsCheck = 0;
    private static int currentFps = 0;
    private static int totalFrames = 0;


    private static void getBestSize()   {
         Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();



    boolean done = false;

    while(!done)   {

        canvasWidth += GAME_WIDTH_DEFAULT;
        canvasHeight += GAME_HEIGHT_DEFAULT;

        System.out.println("Current dimensions: " + canvasWidth + " " + canvasHeight);

        if(canvasWidth > screenSize.width || canvasHeight > screenSize.height)   {
            canvasWidth -= GAME_WIDTH_DEFAULT;
            canvasHeight -= GAME_HEIGHT_DEFAULT;

            done = true;
            System.out.println("Setting canvas to " + canvasWidth + " x " + canvasHeight);
        }

    }


    int xDiff = screenSize.width - canvasWidth;
    int yDiff = screenSize.height - canvasHeight;
    int factor = canvasWidth / GAME_WIDTH_DEFAULT;

    gameWidth = canvasWidth / factor + xDiff / factor;
    gameHeight = canvasHeight /factor + yDiff / factor;

    canvasWidth = gameWidth * factor;
    canvasHeight = gameHeight * factor;


    }

    public static void makeFullscreen()   {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = env.getDefaultScreenDevice();

        if(gd.isFullScreenSupported())   {
            frame.setUndecorated(true);
            gd.setFullScreenWindow(frame);
        }
    }

    public static void init ()   {

        getBestSize();

        frame = new Frame();
        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        frame.add(canvas);

        makeFullscreen();

        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Game.quit();
            }
        });

        frame.setVisible(true);

        canvas.addKeyListener(new Input());
        startRendering();

    }

    private static void startRendering()   {
        Thread thread = new Thread()   {
            public void run()   {
                GraphicsConfiguration gc = canvas.getGraphicsConfiguration();
                VolatileImage vImage = gc.createCompatibleVolatileImage(gameWidth, gameHeight);

                while(true)   {
                    //fps Counter
                    totalFrames++;
                    if(System.nanoTime() > lastFpsCheck + Constants.OneSecondNanoSecond)   {
                        lastFpsCheck = System.nanoTime();
                        currentFps = totalFrames;
                        totalFrames = 0;
                    }

                    //deal with image not displaying correctly.
                    if(vImage.validate(gc) == VolatileImage.IMAGE_INCOMPATIBLE)   {
                        vImage = gc.createCompatibleVolatileImage(gameWidth, gameHeight);
                    }

                    Graphics g = vImage.getGraphics();

                    g.setColor(Color.BLACK);


                    g.fillRect(0, 0, gameWidth, gameHeight);

                    //render here
                    try {
                        World.update();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    World.render(g);

                    //draw FPS
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawString(String.valueOf(currentFps), 2, gameHeight - 2);


                    g.dispose();




                    g = canvas.getGraphics();
                    g.drawImage(vImage, 0, 0, canvasWidth, canvasHeight, null);

                    g.dispose();
                }
            }
        };
        thread.setName("Rendering Thread");
        thread.start();
    }

    public static BufferedImage loadImage (String path) throws IOException {
        BufferedImage rawImage = ImageIO.read(Objects.requireNonNull(Renderer.class.getResource(path)));
        BufferedImage finalImage = canvas.getGraphicsConfiguration()
                .createCompatibleImage(rawImage.getWidth(), rawImage.getHeight(), rawImage.getTransparency());

        finalImage.getGraphics().drawImage(rawImage, 0, 0, rawImage.getWidth(), rawImage.getHeight(),null);
        return finalImage;
    }
}
