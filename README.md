# PlasmaFractalGenerator
A recursive program to randomly color the JFrame

Program execution: Initializes a frame of the hardcoded size and randomely calculates 4 colors, one for each of the corners.
After that, it calculates the midpoint of that grid by averaging all 4 corners' colors. Finally the edge is given a color 
value based on the average of the two points that edge connects. The program recursively calculates a color for each 5x5 
"pixel". The resulting image is a plasma-esque color that meets the corner colors in the middle with their average color.
When the frame is resized, the recursive function runs again to create a new image.
