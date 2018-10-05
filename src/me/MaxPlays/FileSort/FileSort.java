package me.MaxPlays.FileSort;

/**
    Copyright 2018 Maximilian Negedly

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
 */
public class FileSort {

    public static final String version = "1.3.0";

    public static void main(String[] args){
        if(args.length == 0){
            new GUI();
        }else if(args.length == 1 && args[0].equalsIgnoreCase("-h")) {
            printHelp();
        }else {
            String d = "", t = "", f = "[x]";
            boolean r = false, m = false, o = false, l = false;
            for(int i = 0; i < args.length; i++){
                String s = args[i];
                if(s.startsWith("-")){
                    if(s.equalsIgnoreCase("-d") && (i + 1) < args.length){
                        if(!args[i + 1].startsWith("-")){
                            d = args[i + 1];
                        }else{
                            printHelp();
                            return;
                        }
                    }else if(s.equalsIgnoreCase("-t") && (i + 1) < args.length){
                        if(!args[i + 1].startsWith("-")){
                            f = args[i + 1];
                        }else{
                            printHelp();
                            return;
                        }
                    }else if(s.equalsIgnoreCase("-f") && (i + 1) < args.length){
                        if(!args[i + 1].startsWith("-")){
                            f = args[i + 1];
                        }else{
                            printHelp();
                            return;
                        }
                    }else if(s.equalsIgnoreCase("-r")){
                        r = true;
                    }else if(s.equalsIgnoreCase("-m")){
                        m = true;
                    }else if(s.equalsIgnoreCase("-o")){
                        o = true;
                    }else if(s.equalsIgnoreCase("-l")){
                        l = true;
                    }else{
                        printHelp();
                        return;
                    }
                }
            }
            Sort s = new Sort(d, t, r, m, o, f, l, false);
        }
    }

    public static void printHelp(){

        System.out.println("\n\nFileSort v." + version + " by Maximilian Negedly\n\n");
        System.out.println("List of arguments\n");
        System.out.println("\t-d DIRECTORY\tPath to the directory of the files");
        System.out.println("\t-t TYPE     \tList of file types to sort (comma separated)");
        System.out.println("\t-r          \tSort files in sub-directories");
        System.out.println("\t-m          \tMove the files into named folders");
        System.out.println("\t-o          \tOnly move the files into named folders but keep their names");
        System.out.println("\t-f FORMAT   \tChange the name format of sorted files (Use [x] as a placeholder for the ascending number)");
        System.out.println("\t-l          \tFill the spaces before numbers with zeroes");
        System.out.println("\t-h          \tPrint this help screen");
        System.out.println("\n\nExample usage\n");
        System.out.println("\tjava -jar FileSort.jar -d Photos -t png,jpg -r");

    }

}
