package me.MaxPlays.FileSort;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

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

    private String d, f;
    private List t;
    private boolean r, m, o, l;
    private long total = 0;

    public Sort(String d, String t, boolean r, boolean m, boolean o, String f, boolean l, boolean g){
        this.d = d;
        if(t.length() > 0){
            this.t = Arrays.asList(t.trim().toLowerCase().split(","));
        }else{
            this.t = null;
        }
        this.r = r;
        this.m = m;
        this.o = o;
        this.f = f;
        if(!this.f.contains("[x]"))
            this.f = this.f + "[x]";
        this.l = l;
        File file = new File(d);
        if(file.exists()){
            if(!g){
                Scanner sc = new Scanner(System.in);
                System.out.print("Do you want to sort the files in the directory " + file.getAbsolutePath() + " ? [Y/n]: ");
                if(sc.hasNextLine()){
                    String s = sc.nextLine();
                    if(!s.equalsIgnoreCase("y")){
                        System.out.println("Cancelling...");
                        return;
                    }
                }
            }
            System.out.println("Sorting...");
            if(m){
                move(file);
            }else{
                sort(d);
            }
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
                    if(!this.l) {
                        f.renameTo(new File(f.getParentFile().getAbsolutePath() + "//" + (this.f.replace("[x]", l + "")) + "." + f.getName().split("\\.")[f.getName().split("\\.").length - 1]));
                    }else{
                        String name = this.f.replace("[x]", String.format("%0" + String.valueOf(files.length).length() + "d", l));
                        f.renameTo(new File(f.getParentFile().getAbsolutePath() + "//" + name + "." + f.getName().split("\\.")[f.getName().split("\\.").length - 1]));
                    }
                    l++;
                    if(!m)
                        total++;
                }
            }
        }
    }
    public void move(File file){
        if(file.listFiles() != null){
            for(File f: file.listFiles()){
                if(f.isDirectory()){
                    if(r){
                        move(f);
                    }
                }else{
                    if(t != null && !t.contains(f.getName().split("\\.")[f.getName().split("\\.").length - 1].toLowerCase())){
                        continue;
                    }
                    Date d = new Date(f.lastModified());
                    String name = new SimpleDateFormat("yyyyMMdd EEEE").format(d);
                    File t = new File(f.getParentFile().getAbsolutePath() + "//" + name + "//" + f.getName());
                    if(!new File(f.getParentFile().getAbsolutePath() + "//" + name).exists()){
                        new File(f.getParentFile().getAbsolutePath() + "//" + name).mkdirs();
                    }
                    f.renameTo(t);
                    total++;
                    if(!o)
                        sort(t.getParentFile().getAbsolutePath());
                }
            }
        }
    }

}
