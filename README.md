# Airports
Small program to extract data from three csv files

Notes about the Program:

The GUI jar file should run on it's own, make sure you have java 8 runtime installed. If it's not working the main method in the GUI class can be executed.

The GUI has 4 buttons to set paths, three to the csv files and one to a output text file. The paths can also be hard coded in the GUI class. 
The button "Get airports and runways of a country" will write all the airports and corresponding runways a country has to the chosen text file. 
Make sure an existing path to a text file is chosen or nothing will happen, the name shouldn't matter.
The country can be chosen in the input field either with the countrycode or countryname. 
Autocorrect is not imlemented so make sure it is correct. The button "Get countries with the most airports in the world" will display the countries with the most 
airports in the bottom text field of the GUI.

Optimizations:

The runway could also be found with an airport identifier ("ident") instead of an airport id. A check could be placed if this one
corresponds aswell or to make it that the runway is added if either a id or an identifier is found.

The Buffered reader and file objects are now not closed which might be alright as it is in a try block.

All the information is now written to the text file at once from one string. It would be better to open the text file once then write 
to the text file one line at a time.

The button that returns the countries with the most airports could also return the number of airports each country has.

Remove the double quotes surrounding the airports names in the output of highest airports button.
