import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Main {

    public static void main(String[] args) {
        try {
            Color red = new Color(255, 0, 0);

            BufferedImage image = ImageIO.read(new File("src/smallMaze.jpg"));
            int[] initialLocationCoordinates = {0, 40};
            int[] targetLocationCoordinates = {400, 820};
            Agent agent = new Agent(initialLocationCoordinates, targetLocationCoordinates, image, Agent.Strategies.BFS);
            ArrayList<Node> solution = agent.findPath(agent.perceptEnvironment());


            Node parent = solution.get(solution.size() - 1);

            while (parent.getParent() != null) {
                parent = parent.getParent();
                image.setRGB(parent.getState()[1], parent.getState()[0], red.getRGB());
            }

            File file = new File("image.png");
            ImageIO.write(image, "png", file);

        } catch (IOException e) {
            // log the exception
            // re-throw if desired
        }

    }

}



