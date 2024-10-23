# GraphLite

A simple program where the user can create vertecies which are automatically connected to the previously added vertecie.

The purpose of this project is to study graph theory in my MATH 154 class during my studies at UCSD.

Check out the demo at: [https://www.youtube.com/watch?v=EnMB6u2cEH4&ab_channel=MeruzhanSargsyan](https://youtu.be/txZ0nIcGmZ0)

## Requirements:
Gradle and Java8 or above

## Build & Run:
```
./gradlew build
./gradlew run
```

## Run With Loading:
```
# default filename is 'output'
./gradlew run -args="filename"
```

## Usage:
Press backspace to delete the most recently added line and point <br>
Press space to switch between dark mode and light mode <br>
Hold shift to create directed edges (see below) <br>
Hit S at any time to save the file to graphs/output.txt

### Directed Edges
You may now create directed edges from v1 to v2 by starting at v1 <br>
and clicking on v2 while holding down shift.

### NEW FEATURE: Saving & Loading
You may now save and load graphs which stores them in a simple text <br>
file within the app/graphs branch and then parses them to create a graph <br>
which the user can work on.

### Future goals
<ul>
<li>Make the option of creating curved lines so that more than 2 verticies <br>
in one row do now overlap in the edges.
<li>Allow the user to customize where the graphs files are written and read from.
</ul>

