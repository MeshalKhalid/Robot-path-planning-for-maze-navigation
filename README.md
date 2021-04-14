## Robot-path-planning-for-maze-navigation

![](logo.png)

# Introduction
A java program that solves mazes, using these algorithms:  
1- Best first search  
2- Depth first search  
3- Greedy search  
4- A* search  

# How to use 
In the PathDrawingTool class  
1- set the path of your maze image  
     
     `BufferedImage image = ImageIO.read(new File("src/smallMaze.jpg"));`
     
2- set the end and start points by:  
        
          
          `
          int[] initialLocationCoordinates = {0, 40};
          int[] targetLocationCoordinates = {420, 850};
          `  
and run.  

# how to change the algorithms
you can choose the preferred  algorithm from last parameter  

`
 Agent agent = new Agent(initialLocationCoordinates, targetLocationCoordinates, image, Agent.Strategies.AStarManhattan);
`  
 


