import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        BufferedImage image = new BufferedImage(400,300, BufferedImage.TYPE_INT_RGB);
        //image.setRGB(200,200, Color.yellow.getRGB());

        int width= image.getWidth();
        int height = image.getHeight();


        for(int x =0; x <width; x++){
            for(int y =0; y <height; y++){
                if (y* width  > x* height){
                    image.setRGB(x, y, Color.blue.getRGB());
                } else{
                    image.setRGB(x, y, Color.red.getRGB());
                }

            }
        }

        File outputImage= new File("image.jpg");
        try{
            ImageIO.write(image,"jpg", outputImage);

        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }
}