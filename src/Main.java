import java.util.concurrent.Semaphore;

public class Main
{
    public static void main(String args[])
    {
        Semaphore semaphore = new Semaphore(1);
        World world = new World();
        GUI gui = new GUI(world);

        Thread simulationThread = new Thread(new Simulation(world, semaphore));
        Thread displayThread = new Thread(new Display(gui, semaphore));

        simulationThread.start();
        displayThread.start();
    }

    private static class Simulation implements Runnable
    {
        private World world;
        private Semaphore semaphore;

        public Simulation(World world, Semaphore semaphore)
        {
            this.world = world;
            this.semaphore = semaphore;
        }

        @Override
        public void run()
        {
            while (true)
            {
                try
                {
                    semaphore.acquire();
                    try
                    {
                        world.update();
                    }
                    finally
                    {
                        semaphore.release();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                try { Thread.sleep(1000 / Constants.UPDATES_PER_SECOND);}
                catch (InterruptedException e) { e.printStackTrace();}
            }
        }
    }

    private static class Display implements Runnable
    {
        private GUI gui;
        private Semaphore semaphore;

        public Display(GUI gui, Semaphore semaphore)
        {
            this.gui = gui;
            this.semaphore = semaphore;
        }

        @Override
        public void run()
        {
            while (true)
            {
                try
                {
                    semaphore.acquire();
                    try
                    {
                        gui.update();
                    }
                    finally
                    {
                        semaphore.release();
                    }
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                try { Thread.sleep(1000 / Constants.GUI_UPDATES_PER_SECOND);}
                catch (InterruptedException e) { e.printStackTrace();}
            }
        }
    }
}
