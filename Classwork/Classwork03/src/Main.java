import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws Exception {
        Image03_1("classwork03_1.svg");
        Image03_2("classwork03_2.svg");
    }

    public static void Image03_1(String fileName) throws IOException{

        FileWriter writer = new FileWriter(fileName);

        writer.write("""
            <svg width="800" height="600" viewBox="0 0 800 600" xmlns="http://www.w3.org/2000/svg">
    
            <polygon points="0,0 800,600 0,600" fill="#0000FF" />
            <polygon points="0,0 800,600 800,0" fill="#FF0000" />

            </svg>
        """);
        writer.close();

    }

    public static void Image03_2(String fileName) throws IOException{

        FileWriter writer = new FileWriter(fileName);

        writer.write("""
            <svg width="800" height="600" viewBox="0 0 800 600" xmlns="http://www.w3.org/2000/svg">
            <rect width="800" height="600" fill="#FFFFFF" />
            <line
              x1="75" y1="185"
              x2="225" y2="55"
              stroke="#FF5000"
              stroke-width="1" />
        
            <line
              x1="75" y1="55"
              x2="225" y2="185"
              stroke="#FF5000"
              stroke-width="1" />
        
            <line
              x1="150" y1="10"
              x2="150" y2="230"
              stroke="#FF5000"
              stroke-width="1" />
        
            <line
              x1="30" y1="120"
              x2="270" y2="120"
              stroke="#FF5000"
              stroke-width="1" />  
        
            <circle cx="150" cy="120" r="70" fill="#FFFF00" />
        
            <path d="
              M 0 450
              Q 40 510 70 450
              T 130 450
              T 190 450
              T 250 450
              T 310 450
              T 370 450
              T 430 450
              T 490 450
              T 550 450
              T 610 450
              T 670 450
              T 730 450
              T 790 450
              T 850 450
              L 800 600
              L 0 600
              Z
              "
              stroke="#00FF00" fill="#00FF00" stroke-width="3" />
            
            </svg>
        """);
        writer.close();

    }



}
