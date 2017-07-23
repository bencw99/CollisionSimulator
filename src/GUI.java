import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JPanel implements MouseListener, MouseMotionListener
{
    private JFrame frame;
    private World world;

    public enum MouseState
    {
        IDLE,
        PLACING_PARTICLE,
        SETTING_VELOCITY
    }
    private MouseState state;
    private Particle currentParticle;

    public GUI(World world)
    {
        super();
        this.world = world;

        this.frame = new JFrame();
        this.frame.setSize(Constants.WORLD_WIDTH_PIXELS, Constants.WORLD_HEIGHT_PIXELS + Constants.GUI_HEIGHT_BUFFER);
        this.frame.setTitle("Collision Simulator");
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.frame.add(this);
        this.frame.setVisible(true);

        this.state = MouseState.IDLE;
    }

    public void update()
    {
        this.frame.repaint();
    }

    public MouseState getState()
    {
        return state;
    }

    @Override
    public void paintComponent(Graphics graphics)
    {
        this.world.draw(graphics);
        if (this.state != MouseState.IDLE)
        {
            this.currentParticle.draw(graphics);
            if (this.state == MouseState.SETTING_VELOCITY)
            {
                this.currentParticle.getVelocity().drawFrom(graphics, this.currentParticle.getPosition());
            }
        }
    }

    public void setCurrentParticle(MouseEvent e)
    {
        Position currentMousePosition = new Position(e.getX(), e.getY());
        if (this.state == MouseState.PLACING_PARTICLE)
        {
            this.currentParticle = new Particle(currentMousePosition);
        }
        if (this.state == MouseState.SETTING_VELOCITY)
        {
            this.currentParticle.setVelocity(this.currentParticle.getPosition().getVectorTo(currentMousePosition));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        switch(this.state)
        {
            case IDLE:
                this.state = MouseState.PLACING_PARTICLE;
                setCurrentParticle(e);
                break;
            case PLACING_PARTICLE:
                this.state = MouseState.SETTING_VELOCITY;
                setCurrentParticle(e);
                break;
            case SETTING_VELOCITY:
                this.state = MouseState.IDLE;
                this.world.addParticle(this.currentParticle);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {

    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        setCurrentParticle(e);
    }
}
