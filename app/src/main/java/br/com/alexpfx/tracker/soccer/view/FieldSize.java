package br.com.alexpfx.tracker.soccer.view;

/**
 * Created by alexandre on 07/08/2016.
 */
public class FieldSize {

    private float length;
    private float width;

    public FieldSize(float length, float width) {
        this.length = length;
        this.width = width;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public float getArea (){
        return length * width;
    }
}
