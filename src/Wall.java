import java.awt.*;

public class Wall
{
    private Position start;
    private Position end;

    public Wall(Position start, Position end)
    {
        this.start = start;
        this.end = end;
    }

    public double getSlope()
    {
        return (end.getyPos() - start.getyPos()) / (end.getxPos() - start.getxPos());
    }

    public Position getStart()
    {
        return this.start;
    }

    public Position getEnd()
    {
        return this.end;
    }

    public void draw(Graphics graphics)
    {
        graphics.drawLine((int) this.start.getxPos(), (int) this.start.getyPos(), (int) this.end.getxPos(), (int) this.end.getyPos());
    }
}
