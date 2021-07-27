package Code;

import java.io.Serializable;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class MyImage implements Serializable {
    int height;
    int width;
    int [][] pixels;

    public MyImage(){
        //
    }

    public void setImage(Image image){
        height= (int) image.getHeight();
        width = ((int) image.getWidth());
        pixels = new int[width][height];

        PixelReader r = image.getPixelReader();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                pixels[i][j] = r.getArgb(i, j);
    }

    public Image getImage() {
        WritableImage image = new WritableImage(width, height);

        PixelWriter w = image.getPixelWriter();
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                w.setArgb(i, j, pixels[i][j]);

        return image;
    }
}
