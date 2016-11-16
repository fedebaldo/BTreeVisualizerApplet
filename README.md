# BTreeVisualizerApplet
Applet to visualize BTree

This Applet is an attempt to provide a visualizer for BTree data structure. 
My work is the prosecution of Davide Donato's project.  

To compile from bash:

- javac $(find . -name "*.java")
- appletviewer applet.html 

The applet is divided in two parts. 
The first part helps understanding how BTree works by visualizing the data structure
The second part generates random exercise to be solved by the student redrawing the correct BTree.
In the field Draw Root you can insert the text that will represent the keys displayed in the root of 
the tree you are drawing. The keys must be integers and  divided by commas.
For Example

	4,5,6,7

The root in the drawed panel will have button-children you can use to expand the tree. Format of the input to each button is the same used to draw the root
  
To run the html file from your browser you have to change your java settings:

- find java settings
- select security field and allow java on your browser
- than add to the exception list file:///"full-path-to"/applet.html

The applet is working on firefox, still have problem in safari.
chrome doesn't support java applet.
