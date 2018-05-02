package events;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.concurrent.ThreadLocalRandom;

public class ExplosionHandler implements EventHandler<MouseEvent> {
    private static final int PARTICLE_SIZE = 2;
    private static final int PARTICLE_AMOUNT = 200;
    private static final int DEGREES_OF_FREEDOM = 360;

    public ExplosionHandler() {
        super();
    }

    @Override
    public void handle(MouseEvent e) {
        Circle[] particles = new Circle[PARTICLE_AMOUNT];
        int[] particleDirs = new int[PARTICLE_AMOUNT];
        for (int i = 0; i < PARTICLE_AMOUNT; i++) {
            System.out.println(i);
            particles[i] = new Circle();
            particles[i].setRadius(PARTICLE_SIZE);
            particles[i].setFill(Color.BLACK);
            particles[i].setStroke(Color.BLACK);
            particles[i].setCenterX(e.getX());
            particles[i].setCenterY(e.getY());

            particleDirs[i] = ThreadLocalRandom.current().nextInt(0, DEGREES_OF_FREEDOM);

            if (e.getSource() instanceof Pane) {
                ((Pane) e.getSource()).getChildren().add(particles[i]);
            }
            if (i % ParticleRunnable.NUM_PARTICLES == ParticleRunnable.NUM_PARTICLES - 1) {
                Circle[] particleConvoy = new Circle[ParticleRunnable.NUM_PARTICLES];
                int[] particleDirConvoy = new int[ParticleRunnable.NUM_PARTICLES];
                for (int j = 0; j < ParticleRunnable.NUM_PARTICLES; j++) {
                    particleConvoy[j] = particles[i - j];
                    particleDirConvoy[j] = particleDirs[i - j];
                }
                new Thread(new ParticleRunnable(particleConvoy, particleDirConvoy)).start();
            }
        }

    }
}
