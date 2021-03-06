# FileSort
Java tool to sort files by their modification date
## Installation ##
In order to run this tool, you need to install the [JRE](https://www.java.com/en/download/). You can download the latest version of the tool [here](https://github.com/MaxPlays/FileSort/releases/latest).

## Usage ##
To run the program in console, use:
```
java -jar FileSort.jar -h
```
To run the program in GUI mode, use:
```
java -jar FileSort.jar
```
You should see the following list of arguments:
```
List of arguments

        -d DIRECTORY    Path to the directory of the files
        -t TYPE         List of file types to sort (comma separated)
        -r              Sort files in sub-directories
        -m              Move the files into named folders
        -o              Only move the files into named folders but keep their names
        -f FORMAT       Change the name format of sorted files (Use [x] as a placeholder for the ascending number)
        -l              Fill the spaces before numbers with zeroes
        -h              Print this help screen
```
