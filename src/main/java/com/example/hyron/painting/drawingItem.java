package com.example.hyron.painting;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

public class drawingItem implements Parcelable{
    private int shape;
    private int thickness;
    public boolean isSelected;
    public boolean isFilled;
    private int shapeColor;
    private int fillColor;
    private float x1;
    private float y1;
    private float x2;
    private float y2;

    public drawingItem(int shape, int thickness, boolean isSelected, boolean isFilled, int shapeColor, int fillColor, float x1, float y1, float x2, float y2){
        this.shape=shape;
        this.thickness=thickness;
        this.isSelected=isSelected;
        this.isFilled=isFilled;
        this.shapeColor=shapeColor;
        this.fillColor=fillColor;
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }

    public int getShape(){
        return shape;
    }

    public int getThickness(){
        return thickness;
    }

    public int getFillColor() {
        return fillColor;
    }

    public int getShapeColor() {
        return shapeColor;
    }

    public float getX1() {
        return x1;
    }

    public float getX2() {
        return x2;
    }

    public float getY1() {
        return y1;
    }

    public float getY2() {
        return y2;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public void setFillColor(int fillColor) {
        this.fillColor = fillColor;
    }

    public void setShapeColor(int shapeColor) {
        this.shapeColor = shapeColor;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(shape);
        parcel.writeInt(thickness);
        parcel.writeInt((isSelected ? 1 : 0));
        parcel.writeInt((isFilled? 1 : 0));
        parcel.writeInt(shapeColor);
        parcel.writeInt(fillColor);
        parcel.writeFloat(x1);
        parcel.writeFloat(y1);
        parcel.writeFloat(x2);
        parcel.writeFloat(y2);
    }

    private drawingItem(Parcel in) {
        shape = in.readInt();
        thickness = in.readInt();
        isSelected = in.readInt()!=0;
        isFilled = in.readInt()!=0;
        shapeColor = in.readInt();
        fillColor = in.readInt();
        x1 = in.readFloat();
        y1 = in.readFloat();
        x2 = in.readFloat();
        y2 = in.readFloat();
    }

    public static final Parcelable.Creator<drawingItem> CREATOR = new Parcelable.Creator<drawingItem>() {
        public drawingItem createFromParcel(Parcel in) {
            return new drawingItem(in);
        }

        public drawingItem[] newArray(int size) {
            return new drawingItem[size];
        }
    };
}
