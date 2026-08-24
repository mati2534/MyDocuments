package pl.Documents.MyDocuments.views;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static java.lang.System.out;

import org.springframework.core.io.Resource;

public class Menu {

    private ResourcemenuFile = null;

    public ResourcegetMenuFile(){
        return menuFile;
    }

    public void setMenuFile(ResourcemenuFile){
        this.menuFile = menuFile();
    }

    public void printMenu(){
        try{
            InputStream stream = getMenuFile().getInputStream();
            Scanner scanner = new Scanner(stream);
            while(scanner.hasNext()){
                out.println(scanner.nextLine());
            }
            scanner.close();
            stream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
