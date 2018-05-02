package events;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Screen;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ParticleRunnable implements Runnable {
    private double[] changeX;
    private double[] changeY;
    private Circle[] particle;
    private int[] direction;
    public static final int NUM_PARTICLES = 200;
    private static final int MAX_SPEED = 10;


    public ParticleRunnable(Circle[] particle, int[] direction) {
        this.particle = particle;
        this.direction = direction;
        changeX = new double[NUM_PARTICLES];
        changeY = new double[NUM_PARTICLES];

        for (int i = 0; i < NUM_PARTICLES; i++) {
            while (direction[i] >= 360) {
                direction[i] -= 360;
            }

            changeX[i] = Math.cos(direction[i]) * ThreadLocalRandom.current().nextInt(0, MAX_SPEED);;
            changeY[i] = Math.sin(direction[i]) * ThreadLocalRandom.current().nextInt(0, MAX_SPEED);;
        }
    }

    @Override
    public void run() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        for (int i = 1; i < 1000; i++) {
            System.out.println(i);
            for (int j = 0; j < NUM_PARTICLES; j++) {
                particle[j].setCenterX(particle[j].getCenterX() + changeX[j]);
                particle[j].setCenterY(particle[j].getCenterY() + changeY[j]);
                if (particle[j].getCenterX() < screenBounds.getMinX() || particle[j].getCenterX() > screenBounds.getMaxX()) {
                    changeX[j] *= -1;
                }
                if (particle[j].getCenterY() < screenBounds.getMinY() || particle[j].getCenterY() > screenBounds.getMaxY()) {
                    changeY[j] = ((changeY[j] * .9) * -1);
                }
                changeY[j] += .4;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            }
            catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
