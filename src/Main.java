import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main {

	public static void main(String[] args) {
		try {

			BufferedImage image = ImageIO.read(new File("src/smallMaze.jpg"));
			int[] initialLocationCoordinates = {0,20};
			int[] targetLocationCoordinates = {400,800};
			Agent agenttrue = new Agent(initialLocationCoordinates, targetLocationCoordinates, image, Agent.Strategies.BFS);
			agenttrue.findPath(agenttrue.perceptEnvironment());
						// work with the image here ...
		} catch (IOException e) {
			// log the exception
			// re-throw if desired
		}

	}

}



