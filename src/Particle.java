import java.awt.*;
import java.util.ArrayList;

public class Particle
{
    private double mass;
    private double radius;

    private Position position;
    private Vector velocity;

    public Particle(Position position)
    {
        this(Constants.AVERAGE_PARTICLE_MASS, Constants.AVERAGE_PARTICLE_RADIUS, position, new Vector(0.0, 0.0));
    }

    public Particle(double mass, double radius, Position position, Vector velocity)
    {
        this.mass = mass;
        this.radius = radius;
        this.position = position;
        this.velocity = velocity;
    }

    public void collide(Particle other)
    {
        if (this == other) return;
        double distance = this.getDistance(other);
        if (distance <= 0.0)
        {
            double forceMagnitue = distance * (this.getSpringConstant() + other.getSpringConstant());
            Vector force = this.position.getVectorTo(other.position).unit().scale(forceMagnitue);
            this.velocity.update(force.scale(1 / this.mass), Constants.UPDATES_PER_SECOND);
        }
    }

    public void collide(Wall wall)
    {
        Vector toWall = this.position.getVectorTo(wall);
        if (toWall != null && toWall.magnitude() <= this.radius)
        {
            double forceMagnitude = (toWall.magnitude() - this.radius) * this.getSpringConstant();
            Vector force = toWall.unit().scale(forceMagnitude);
            this.velocity.update(force.scale(1 / this.mass), Constants.UPDATES_PER_SECOND);
        }
    }

    public void update(ArrayList<Particle> particles, ArrayList<Wall> walls)
    {
        for (Particle other : particles) collide(other);
        for (Wall wall : walls) collide(wall);
        this.velocity.update(Constants.GRAVITY, Constants.UPDATES_PER_SECOND);
        this.position.update(this.velocity, Constants.UPDATES_PER_SECOND);
    }

    public double getSpringConstant()
    {
        return Constants.BASE_SPRING_CONSTANT * this.mass / (this.radius * this.radius);
    }

    public double getDistance(Particle other)
    {
        return this.position.getDistance(other.position) - this.radius - other.radius;
    }

    public Position getPosition()
    {
        return this.position;
    }

    public Vector getVelocity()
    {
        return this.velocity;
    }

    public void setVelocity(Vector velocity)
    {
        this.velocity = velocity;
    }

    public void draw(Graphics graphics)
    {
        int leftCornerX = (int) (this.position.getxPos() - this.radius);
        int leftCornerY = (int) (this.position.getyPos() - this.radius);
        graphics.drawOval(leftCornerX, leftCornerY, (int) (this.radius * 2), (int) (this.radius * 2));
    }
}
