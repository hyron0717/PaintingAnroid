package com.example.hyron.painting;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Hyron on 2016/7/6.
 */
public class MyCanvas extends View {
    Paint paint = new Paint();
    public ArrayList<drawingItem> Items = new ArrayList<drawingItem>();
    private int shape;
    private int thickness;
    private int curColor;
    public drawingItem temp;
    public boolean selected = false;
    public float prevX;
    public float prevY;
    public float startX=0;
    public float startY=0;
    private PathEffect effects;
    float EndLen;
    float startLen;
    float prevLen;


    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.shape=1;
        this.thickness=3;
        curColor=Color.BLACK;

    }

    public void setShape(int shape){
        this.shape=shape;
    }
    public  void setCurColor(int curColor){
        this.curColor=curColor;
    }
    public void setThickness(int thickness){
        this.thickness=thickness;
    }
    public int getShape(){
        return shape;
    }
    public int getThickness(){
        return thickness;
    }


    protected void onDraw(Canvas canvas) {
        for(int i=0; i< Items.size(); i++){
            temp = Items.get(i);
            if(temp.isSelected){
                paint.setStrokeWidth(temp.getThickness()+5);
            }
            else{
                paint.setStrokeWidth(temp.getThickness());
            }

            if(temp.getShape()==2){
                paint.setColor(temp.getShapeColor());
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawLine(temp.getX1(), temp.getY1(), temp.getX2(), temp.getY2(), paint);
            }
            else if(temp.getShape()==3){
                if(temp.isFilled) {
                    paint.setColor(temp.getFillColor());
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawOval(temp.getX1(), temp.getY1(), temp.getX2(), temp.getY2(), paint);
                }
                paint.setColor(temp.getShapeColor());
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawOval(temp.getX1(),temp.getY1(),temp.getX2(),temp.getY2(),paint);
            }
            else if(temp.getShape()==4){
                if(temp.isFilled) {
                    paint.setColor(temp.getFillColor());
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(temp.getX1(), temp.getY1(), temp.getX2(), temp.getY2(), paint);
                }
                paint.setColor(temp.getShapeColor());
                paint.setStyle(Paint.Style.STROKE);
                canvas.drawRect(temp.getX1(),temp.getY1(),temp.getX2(),temp.getY2(),paint);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        float endX;
        float endY;
        drawingItem newshape;
        drawingItem selectshape;
        float distance;
        double normallength;
        float radius;
        float coordX;
        float coordY;
        float moveX;
        float moveY;

        int nCnt = event.getPointerCount();

        if(nCnt==1) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                startX = event.getX();
                startY = event.getY();

                if (shape == 2 || shape == 3 || shape == 4) {
                    newshape = new drawingItem(shape, thickness, false, false, curColor, curColor, startX, startY, startX, startY);
                    selected = false;
                    Items.add(newshape);
                } else if (shape == 1) {
                    selected = false;
                    for (int i = 0; i < Items.size(); i++) {
                        Items.get(i).isSelected = false;
                    }
                    prevX = startX;
                    prevY = startY;
                    for (int i = Items.size() - 1; i >= 0; i--) {
                        selectshape = Items.get(i);

                        if (selectshape.getShape() == 2) {
                            normallength = Math.sqrt((selectshape.getX2() - selectshape.getX1()) * (selectshape.getX2() - selectshape.getX1()) + (selectshape.getY2() - selectshape.getY1()) * (selectshape.getY2() - selectshape.getY1()));
                            distance = (float) (Math.abs((startX - selectshape.getX1()) * (selectshape.getY2() - selectshape.getY1()) - (startY - selectshape.getY1()) * (selectshape.getX2() - selectshape.getX1())) / normallength);
                            if (distance <= 10) {
                                thickness = Items.get(i).getThickness();
                                selectshape.isSelected = true;
                                Items.remove(i);
                                Items.add(selectshape);
                                selected = true;
                                break;
                            }
                        } else if (selectshape.getShape() == 3) {
                            radius = (selectshape.getX2() - selectshape.getX1()) / 2;
                            coordX = selectshape.getX1() + radius;
                            coordY = selectshape.getY1() + radius;
                            distance = (float) Math.sqrt((coordY - startY) * (coordY - startY) + (coordX - startX) * (coordX - startX));
                            if (distance <= radius) {
                                thickness = Items.get(i).getThickness();
                                selectshape.isSelected = true;
                                Items.remove(i);
                                Items.add(selectshape);
                                selected = true;
                                break;
                            }
                        } else if (selectshape.getShape() == 4) {
                            if ((startX >= selectshape.getX1()) && (startX <= selectshape.getX2()) && (startY >= selectshape.getY1()) && (startY <= selectshape.getY2())) {
                                selectshape.isSelected = true;
                                thickness = Items.get(i).getThickness();
                                Items.remove(i);
                                Items.add(selectshape);
                                selected = true;
                                break;
                            }
                        }
                        invalidate();
                    }
                } else if (shape == 5) {
                    selected = false;
                    for (int i = Items.size() - 1; i >= 0; i--) {
                        selectshape = Items.get(i);

                        if (selectshape.getShape() == 2) {
                            normallength = Math.sqrt((selectshape.getX2() - selectshape.getX1()) * (selectshape.getX2() - selectshape.getX1()) + (selectshape.getY2() - selectshape.getY1()) * (selectshape.getY2() - selectshape.getY1()));
                            distance = (float) (Math.abs((startX - selectshape.getX1()) * (selectshape.getY2() - selectshape.getY1()) - (startY - selectshape.getY1()) * (selectshape.getX2() - selectshape.getX1())) / normallength);
                            if (distance <= 10) {
                                Items.remove(i);
                                break;
                            }
                        } else if (selectshape.getShape() == 3) {
                            radius = (selectshape.getX2() - selectshape.getX1()) / 2;
                            coordX = selectshape.getX1() + radius;
                            coordY = selectshape.getY1() + radius;
                            distance = (float) Math.sqrt((coordY - startY) * (coordY - startY) + (coordX - startX) * (coordX - startX));
                            if (distance <= radius) {
                                Items.remove(i);
                                break;
                            }
                        } else if (selectshape.getShape() == 4) {
                            if ((startX >= selectshape.getX1()) && (startX <= selectshape.getX2()) && (startY >= selectshape.getY1()) && (startY <= selectshape.getY2())) {
                                Items.remove(i);
                                break;
                            }
                        }
                        invalidate();
                    }
                } else if (shape == 6) {
                    selected = false;
                    for (int i = Items.size() - 1; i >= 0; i--) {
                        selectshape = Items.get(i);

                        if (selectshape.getShape() == 2) {
                            normallength = Math.sqrt((selectshape.getX2() - selectshape.getX1()) * (selectshape.getX2() - selectshape.getX1()) + (selectshape.getY2() - selectshape.getY1()) * (selectshape.getY2() - selectshape.getY1()));
                            distance = (float) (Math.abs((startX - selectshape.getX1()) * (selectshape.getY2() - selectshape.getY1()) - (startY - selectshape.getY1()) * (selectshape.getX2() - selectshape.getX1())) / normallength);
                            if (distance <= 10) {
                                Items.get(i).isFilled = true;
                                Items.get(i).setFillColor(curColor);
                                break;
                            }
                        } else if (selectshape.getShape() == 3) {
                            radius = (selectshape.getX2() - selectshape.getX1()) / 2;
                            coordX = selectshape.getX1() + radius;
                            coordY = selectshape.getY1() + radius;
                            distance = (float) Math.sqrt((coordY - startY) * (coordY - startY) + (coordX - startX) * (coordX - startX));
                            if (distance <= radius) {
                                Items.get(i).isFilled = true;
                                Items.get(i).setFillColor(curColor);
                                break;
                            }
                        } else if (selectshape.getShape() == 4) {
                            if ((startX >= selectshape.getX1()) && (startX <= selectshape.getX2()) && (startY >= selectshape.getY1()) && (startY <= selectshape.getY2())) {
                                Items.get(i).isFilled = true;
                                Items.get(i).setFillColor(curColor);
                                break;
                            }
                        }
                        invalidate();
                    }
                }

            } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                endX = event.getX();
                endY = event.getY();

                if (shape == 2) {
                    Items.get(Items.size() - 1).setX2(endX);
                    Items.get(Items.size() - 1).setY2(endY);
                }

                if (shape == 4) {
                    if (endX < startX) {
                        Items.get(Items.size() - 1).setX1(endX);
                        Items.get(Items.size() - 1).setX2(startX);
                    }
                    if (endX >= startX) {
                        Items.get(Items.size() - 1).setX1(startX);
                        Items.get(Items.size() - 1).setX2(endX);
                    }
                    if (endY < startY) {
                        Items.get(Items.size() - 1).setY1(endY);
                        Items.get(Items.size() - 1).setY2(startY);
                    }
                    if (endY >= startY) {
                        Items.get(Items.size() - 1).setY1(startY);
                        Items.get(Items.size() - 1).setY2(endY);
                    }
                }

                if (shape == 3) {
                    if (endX < startX) {
                        Items.get(Items.size() - 1).setX1(endX);
                        Items.get(Items.size() - 1).setX2(startX);
                    }
                    if (endX >= startX) {
                        Items.get(Items.size() - 1).setX1(startX);
                        Items.get(Items.size() - 1).setX2(endX);
                    }
                    if (endY < startY) {
                        Items.get(Items.size() - 1).setY1(startY - Math.abs(startX - endX));
                        Items.get(Items.size() - 1).setY2(startY);
                    }
                    if (endY >= startY) {
                        Items.get(Items.size() - 1).setY1(startY);
                        Items.get(Items.size() - 1).setY2(startY + Math.abs(startX - endX));
                    }
                }

                if (shape == 1) {
                    if (selected) {
                        moveX = endX - prevX;
                        moveY = endY - prevY;
                        Items.get(Items.size() - 1).setX1(Items.get(Items.size() - 1).getX1() + moveX);
                        Items.get(Items.size() - 1).setX2(Items.get(Items.size() - 1).getX2() + moveX);
                        Items.get(Items.size() - 1).setY1(Items.get(Items.size() - 1).getY1() + moveY);
                        Items.get(Items.size() - 1).setY2(Items.get(Items.size() - 1).getY2() + moveY);

                        prevX = endX;
                        prevY = endY;
                    }
                }
                invalidate();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                invalidate();
            }
        }
        if(nCnt>=2) {
            if (shape == 1) {
                if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_POINTER_DOWN && 2 == nCnt) {
                    float x = event.getX(0) - event.getX(1);
                    float y = event.getY(0) - event.getY(1);
                    startLen = (float) Math.sqrt(x * x + y * y);
                    prevLen=startLen;
                }
                else if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_MOVE && 2 == nCnt) {
                    float x2 = event.getX(0) - event.getX(1);
                    float y2 = event.getY(0) - event.getY(1);
                    EndLen = (float) Math.sqrt(x2 * x2 + y2 * y2);

                    if (EndLen > prevLen) {
                        Items.get(Items.size() - 1).setX1(Items.get(Items.size() - 1).getX1() - 2);
                        Items.get(Items.size() - 1).setY1(Items.get(Items.size() - 1).getY1() - 2);
                        Items.get(Items.size() - 1).setX2(Items.get(Items.size() - 1).getX2() + 2);
                        Items.get(Items.size() - 1).setY2(Items.get(Items.size() - 1).getY2() + 2);
                    } else if (EndLen < prevLen) {
                        Items.get(Items.size() - 1).setX1(Items.get(Items.size() - 1).getX1() + 2);
                        Items.get(Items.size() - 1).setY1(Items.get(Items.size() - 1).getY1() + 2);
                        Items.get(Items.size() - 1).setX2(Items.get(Items.size() - 1).getX2() - 2);
                        Items.get(Items.size() - 1).setY2(Items.get(Items.size() - 1).getY2() - 2);
                    }
                    prevLen=EndLen;
                    invalidate();

                }
            }


            }
            return true;
    }


}
