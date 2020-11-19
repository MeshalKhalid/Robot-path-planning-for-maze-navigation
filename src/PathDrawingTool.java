import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class PathDrawingTool {

    public static void drawPath(ArrayList<Node> solution, BufferedImage image) {
        Color red = new Color(255, 0, 0);

        for (Node node : solution) {
            image.setRGB(node.getState()[1], node.getState()[0], red.getRGB());

        }
    }

    public static void main(String[] args) {
        try {

            BufferedImage image = ImageIO.read(new File("src/smallMaze.jpg"));
            int[] initialLocationCoordinates = {0, 40};
            int[] targetLocationCoordinates = {420, 850};
            Agent agent = new Agent(initialLocationCoordinates, targetLocationCoordinates, image, Agent.Strategies.AStarManhattan);
            ArrayList<Node> solution = agent.findPath(agent.perceptsEnvironment());
            drawPath(solution, image);

            File file = new File("output.png");
            ImageIO.write(image, "png", file);

        } catch (IOException e) {
            // log the exception
            // re-throw if desired
        }

    }

}



