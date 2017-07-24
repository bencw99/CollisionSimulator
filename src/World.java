import java.awt.*;
import java.util.ArrayList;

public class World
{
    private ArrayList<Particle> particles;
    private ArrayList<Wall> walls;

    public World()
    {
        this.particles = new ArrayList<Particle>();
        this.walls = new ArrayList<Wall>();

        generateBoundaryWalls();
        generateRandomParticles();
        generateRandomWalls();
    }

    public void generateBoundaryWalls()
    {
        walls.add(new Wall(new Position(0.0, 0.0), new Position(0.0, Constants.WORLD_HEIGHT_PIXELS)));
        walls.add(new Wall(new Position(0.0, Constants.WORLD_HEIGHT_PIXELS), new Position(Constants.WORLD_WIDTH_PIXELS, Constants.WORLD_HEIGHT_PIXELS)));
        walls.add(new Wall(new Position(Constants.WORLD_WIDTH_PIXELS, Constants.WORLD_HEIGHT_PIXELS), new Position(Constants.WORLD_WIDTH_PIXELS, 0.0)));
        walls.add(new Wall(new Position(Constants.WORLD_WIDTH_PIXELS, 0.0), new Position(0.0, 0.0)));
    }

    public void generateRandomParticles()
    {
        for (int i = 0; i < Constants.NUM_RANDOM_PARTICLES; i ++)
        {
            Position position = new Position(Constants.WORLD_WIDTH_PIXELS * Math.random(), Constants.WORLD_HEIGHT_PIXELS * Math.random());
            Vector velocity = Vector.getRandomUnitVector().scale(Constants.AVERAGE_PARTICLE_VELOCITY);
            double mass = 0.5 * Constants.AVERAGE_PARTICLE_MASS + Math.random() * Constants.AVERAGE_PARTICLE_MASS;
            double radius = 0.5 * Constants.AVERAGE_PARTICLE_RADIUS + Math.random() * Constants.AVERAGE_PARTICLE_RADIUS;
            this.particles.add(new Particle(mass, radius, position, velocity));
        }
    }

    public void generateRandomWalls()
    {
        // Position start = new Position(250, 100);
        // Position end = new Position(250, 400);
        // Wall wall = new Wall(start, end);
        // this.walls.add(wall);
        for (int i = 0; i < Constants.NUM_RANDOM_WALLS; i ++)
        {
            // TODO: Implement this function
        }
    }

    public void update()
    {
        for (Particle particle : particles)
        {
            particle.update(this.particles, this.walls);
        }
    }

    public void addParticle(Particle particle)
    {
        this.particles.add(particle);
    }

    public void draw(Graphics graphics)
    {
        for (Particle particle : this.particles) particle.draw(graphics);
        for (Wall wall : this.walls) wall.draw(graphics);
    }
}
