package me.MaxPlays.FileSort;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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
public class Sort {

    private String d;
    private List t;
    private boolean r;
    private long total = 0;

    public Sort(String d, String t, boolean r){
        this.d = d;
        if(t.length() > 0){
            this.t = Arrays.asList(t.trim().toLowerCase().split(","));
        }else{
            this.t = null;
        }
        this.r = r;
        File file = new File(d);
        if(file.exists()){
            Scanner sc = new Scanner(System.in);
            System.out.print("Do you want to sort the files in the directory " + file.getAbsolutePath() + " ? [Y/n]: ");
            if(sc.hasNextLine()){
                String s = sc.nextLine();
                if(!s.equalsIgnoreCase("y")){
                    System.out.println("Cancelling...");
                    return;
                }
            }
            System.out.println("Sorting...");
            sort(d);
            System.out.println("Sorted " + total + " files");
        }else{
            System.out.println("ERROR: The specified directory does not exist");
        }
    }

    public void sort(String d){
        File file = new File(d);
        if(file.listFiles() != null){
            File[] files = file.listFiles();
            Arrays.sort(files, Comparator.comparingLong(File::lastModified));
            long l = 1;
            for(File f: files){
                if(f.isDirectory()){
                    if(r){
                        sort(f.getAbsolutePath());
                    }
                }else{
                    if(t != null && !t.contains(f.getName().split("\\.")[f.getName().split("\\.").length - 1].toLowerCase())){
                        continue;
                    }
                    f.renameTo(new File(f.getParentFile().getAbsolutePath() + "//" + l + "." + f.getName().split("\\.")[f.getName().split("\\.").length - 1]));
                    l++;
                    total++;
                }
            }
        }
    }

}
