import java.awt.*;

public class Vector
{
    private double x;
    private double y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector scale(double scalar)
    {
        return new Vector(this.x * scalar, this.y * scalar);
    }

    public void update(Vector derivative, double updateRate)
    {
        this.x += derivative.getX() / updateRate;
        this.y += derivative.getY() / updateRate;
    }

    public double magnitude()
    {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector unit()
    {
        return new Vector(this.x / magnitude(), this.y / magnitude());
    }

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public static Vector getRandomUnitVector()
    {
        double angle = 2 * Math.PI * Math.random();
        return new Vector(Math.cos(angle), Math.sin(angle));
    }

    public void drawFrom(Graphics graphics, Position position)
    {
        graphics.setColor(Color.WHITE);
        graphics.drawLine((int) position.getxPos(), (int) position.getyPos(), (int) (position.getxPos() + this.x), (int) (position.getyPos() + this.y));
        graphics.setColor(Color.BLACK);
    }
}
