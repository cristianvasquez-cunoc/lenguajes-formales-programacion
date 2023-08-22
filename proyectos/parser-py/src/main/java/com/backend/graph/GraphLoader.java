package com.backend.graph;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


public class GraphLoader extends Thread {

    ImageView imageView;
    long time;

    final String PATH = "./src/main/resources/com/frontend/graphs/graph";


    public GraphLoader(ImageView imageView, long time) {
        this.imageView = imageView;
        this.time = time;
    }


    @Override
    public void run() {

        try {
            imageView.setImage(new Image("loading.gif"));
            Thread.sleep(800);
            Image img = new Image(new File(PATH + time + ".png").toURI().toString());
            imageView.setImage(img);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
