public class Position
{
    private double xPos;
    private double yPos;

    public Position(double xPos, double yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void update(Vector velocity, double updateRate)
    {
        this.xPos += velocity.getX() / updateRate;
        this.yPos += velocity.getY() / updateRate;
    }

    public double getDistance(Position other)
    {
        return getVectorTo(other).magnitude();
    }

    public Vector getVectorTo(Position other)
    {
        return new Vector(other.xPos - this.xPos, other.yPos - this.yPos);
    }

    public Vector getVectorTo(Wall wall)
    {
        double slope = wall.getSlope();
        double intersectionX = (slope * slope * wall.getStart().getxPos() + slope * this.getyPos() - slope * wall.getStart().getyPos()
                + this.getxPos()) / (slope * slope + 1.0);
        double intersectionY = (slope * slope * this.getyPos() + slope * this.getxPos() - slope * wall.getStart().getxPos()
                + wall.getStart().getyPos()) / (slope * slope + 1.0);
        // Hack override for vertical walls. TODO: Implement cleaner fix
        if (wall.getStart().getxPos() == wall.getEnd().getxPos())
        {
            intersectionX = wall.getStart().getxPos();
            intersectionY = this.getyPos();
        }
        if (((intersectionX < wall.getStart().getxPos()) && (intersectionX > wall.getEnd().getxPos()))
                || ((intersectionX > wall.getStart().getxPos()) && (intersectionX < wall.getEnd().getxPos()))
                || ((intersectionY < wall.getStart().getyPos()) && (intersectionY > wall.getEnd().getyPos()))
                || ((intersectionY > wall.getStart().getyPos()) && (intersectionY < wall.getEnd().getyPos())))
        {
            return getVectorTo(new Position(intersectionX, intersectionY));
        }
        else return null;
    }

    public double getxPos()
    {
        return this.xPos;
    }

    public double getyPos()
    {
        return this.yPos;
    }
}
