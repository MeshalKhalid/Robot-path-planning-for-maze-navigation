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
			int[] initialLocationCoordinates = {250,40};
			int[] targetLocationCoordinates = {400,820};
			Agent agent = new Agent(initialLocationCoordinates, targetLocationCoordinates, image, Agent.Strategies.BFS);
			ArrayList<int[]> solution =  agent.findPath(agent.perceptEnvironment());
			System.out.println(solution.size());
//			for(int[] state : solution){
//				image.setRGB(state[1],state[0],red.getRGB());
//			}

			for(int i= solution.size()-1  ;i >0;i--){
				image.setRGB(solution.get(i)[1],solution.get(i)[0],red.getRGB());
			}

			File file = new File("image.png");
			ImageIO.write(image, "png", file);

		} catch (IOException e) {
			// log the exception
			// re-throw if desired
		}

	}

}



