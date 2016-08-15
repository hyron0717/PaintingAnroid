package com.example.hyron.painting;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyCanvas myCanvas;
    private ImageButton selectButton;
    private ImageButton lineButton;
    private ImageButton ovalButton;
    private ImageButton rectButton;
    private ImageButton fillButton;
    private ImageButton eraseButton;
    private Button yellowButton;
    private Button redButton;
    private Button greenButton;
    private Button blueButton;
    private Button whiteButton;
    private Button blackButton;
    private ImageButton thickness_1;
    private ImageButton thickness_2;
    private ImageButton thickness_3;
    private ImageButton thickness_4;
    int shape;
    int thickness;
    Color curColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectButton = (ImageButton)findViewById(R.id.select_btn);
        selectButton.setOnClickListener(this);

        lineButton = (ImageButton)findViewById(R.id.line_btn);
        lineButton.setOnClickListener(this);

        ovalButton = (ImageButton)findViewById(R.id.oval_btn);
        ovalButton.setOnClickListener(this);

        rectButton = (ImageButton)findViewById(R.id.rect_btn);
        rectButton.setOnClickListener(this);

        fillButton = (ImageButton)findViewById(R.id.fill_btn);
        fillButton.setOnClickListener(this);

        eraseButton = (ImageButton)findViewById(R.id.erase_btn);
        eraseButton.setOnClickListener(this);
        eraseButton.setOnLongClickListener(LongClick);


        yellowButton =(Button)findViewById(R.id.yellow_btn);
        yellowButton.setOnClickListener(this);

        redButton =(Button)findViewById(R.id.red_btn);
        redButton.setOnClickListener(this);

        greenButton =(Button)findViewById(R.id.green_btn);
        greenButton.setOnClickListener(this);

        blueButton =(Button)findViewById(R.id.blue_btn);
        blueButton.setOnClickListener(this);

        whiteButton =(Button)findViewById(R.id.white_btn);
        whiteButton.setOnClickListener(this);

        blackButton =(Button)findViewById(R.id.black_btn);
        blackButton.setOnClickListener(this);

        thickness_1 =(ImageButton)findViewById(R.id.thickness_1);
        thickness_1.setOnClickListener(this);

        thickness_2 =(ImageButton)findViewById(R.id.thickness_2);
        thickness_2.setOnClickListener(this);

        thickness_3 =(ImageButton)findViewById(R.id.thickness_3);
        thickness_3.setOnClickListener(this);

        thickness_4 =(ImageButton)findViewById(R.id.thickness_4);
        thickness_4.setOnClickListener(this);

        myCanvas = (MyCanvas)findViewById(R.id.canvas);
        myCanvas.setOnClickListener(this);

        if(savedInstanceState!=null){
            myCanvas.Items=savedInstanceState.getParcelableArrayList("ItemsList");
            myCanvas.setShape(savedInstanceState.getInt("Shape"));
            myCanvas.invalidate();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("ItemsList", myCanvas.Items);
        outState.putInt("Shape", myCanvas.getShape());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.select_btn){
            myCanvas.setShape(1);
        }
        else if(view.getId()==R.id.line_btn){
            myCanvas.setShape(2);
        }
        else if(view.getId()==R.id.oval_btn){
            myCanvas.setShape(3);
        }
        else if(view.getId()==R.id.rect_btn){
            myCanvas.setShape(4);
        }
        else if(view.getId()==R.id.fill_btn){
            myCanvas.setShape(6);
        }
        else if(view.getId()==R.id.erase_btn) {
            myCanvas.setShape(5);
        }
        else if(view.getId()==R.id.red_btn){
            myCanvas.setCurColor(Color.RED);
        }
        else if(view.getId()==R.id.yellow_btn){
            myCanvas.setCurColor(Color.YELLOW);
        }
        else if(view.getId()==R.id.green_btn){
            myCanvas.setCurColor(Color.GREEN);
        }
        else if(view.getId()==R.id.blue_btn){
            myCanvas.setCurColor(Color.BLUE);
        }
        else if(view.getId()==R.id.white_btn){
            myCanvas.setCurColor(Color.WHITE);
        }
        else if(view.getId()==R.id.black_btn){
            myCanvas.setCurColor(Color.BLACK);
        }
        else if(view.getId()==R.id.thickness_1){
            myCanvas.setThickness(3);
        }
        else if(view.getId()==R.id.thickness_2){
            myCanvas.setThickness(6);
        }
        else if(view.getId()==R.id.thickness_3){
            myCanvas.setThickness(9);
        }
        else if(view.getId()==R.id.thickness_4){
            myCanvas.setThickness(12);
        }

        if(myCanvas.selected && myCanvas.getShape()==1){
            if(view.getId()==R.id.red_btn){
                myCanvas.Items.get(myCanvas.Items.size()-1).setShapeColor(Color.RED);
            }
            else if(view.getId()==R.id.green_btn){
                myCanvas.Items.get(myCanvas.Items.size()-1).setShapeColor(Color.GREEN);
            }
            else if(view.getId()==R.id.yellow_btn){
                myCanvas.Items.get(myCanvas.Items.size()-1).setShapeColor(Color.YELLOW);
            }
            else if(view.getId()==R.id.blue_btn){
                myCanvas.Items.get(myCanvas.Items.size()-1).setShapeColor(Color.BLUE);
            }
            else if(view.getId()==R.id.black_btn){
                myCanvas.Items.get(myCanvas.Items.size()-1).setShapeColor(Color.BLACK);
            }
            else if(view.getId()==R.id.white_btn){
                myCanvas.Items.get(myCanvas.Items.size()-1).setShapeColor(Color.WHITE);
            }
            else if(view.getId()==R.id.thickness_1){
                myCanvas.Items.get(myCanvas.Items.size()-1).setThickness(3);
            }
            else if(view.getId()==R.id.thickness_2){
                myCanvas.Items.get(myCanvas.Items.size()-1).setThickness(6);
            }
            else if(view.getId()==R.id.thickness_3){
                myCanvas.Items.get(myCanvas.Items.size()-1).setThickness(9);
            }
            else if(view.getId()==R.id.thickness_4){
                myCanvas.Items.get(myCanvas.Items.size()-1).setThickness(12);
            }
        }

        if(myCanvas.getShape()==1){
            selectButton.setImageResource(R.drawable.select2);
            lineButton.setImageResource(R.drawable.line);
            ovalButton.setImageResource(R.drawable.oval);
            rectButton.setImageResource(R.drawable.rect);
            eraseButton.setImageResource(R.drawable.earse);
            fillButton.setImageResource(R.drawable.fill);
        }
        else if(myCanvas.getShape()==2){
            selectButton.setImageResource(R.drawable.select);
            lineButton.setImageResource(R.drawable.line2);
            ovalButton.setImageResource(R.drawable.oval);
            rectButton.setImageResource(R.drawable.rect);
            eraseButton.setImageResource(R.drawable.earse);
            fillButton.setImageResource(R.drawable.fill);
        }
        else if(myCanvas.getShape()==3){
            selectButton.setImageResource(R.drawable.select);
            lineButton.setImageResource(R.drawable.line);
            ovalButton.setImageResource(R.drawable.oval2);
            rectButton.setImageResource(R.drawable.rect);
            eraseButton.setImageResource(R.drawable.earse);
            fillButton.setImageResource(R.drawable.fill);
        }
        else if(myCanvas.getShape()==4){
            selectButton.setImageResource(R.drawable.select);
            lineButton.setImageResource(R.drawable.line);
            ovalButton.setImageResource(R.drawable.oval);
            rectButton.setImageResource(R.drawable.rect2);
            eraseButton.setImageResource(R.drawable.earse);
            fillButton.setImageResource(R.drawable.fill);
        }
        else if(myCanvas.getShape()==5){
            selectButton.setImageResource(R.drawable.select);
            lineButton.setImageResource(R.drawable.line);
            ovalButton.setImageResource(R.drawable.oval);
            rectButton.setImageResource(R.drawable.rect);
            eraseButton.setImageResource(R.drawable.earse2);
            fillButton.setImageResource(R.drawable.fill);
        }
        else if(myCanvas.getShape()==6){
            selectButton.setImageResource(R.drawable.select);
            lineButton.setImageResource(R.drawable.line);
            ovalButton.setImageResource(R.drawable.oval);
            rectButton.setImageResource(R.drawable.rect);
            eraseButton.setImageResource(R.drawable.earse);
            fillButton.setImageResource(R.drawable.fill2);
        }

        if(myCanvas.getThickness()==3){
            thickness_1.setImageResource(R.drawable.thickness_1_2);
            thickness_2.setImageResource(R.drawable.thickness_2);
            thickness_3.setImageResource(R.drawable.thickness_3);
            thickness_4.setImageResource(R.drawable.thickness_4);
        }
        else if(myCanvas.getThickness()==6){
            thickness_1.setImageResource(R.drawable.thickness_1);
            thickness_2.setImageResource(R.drawable.thickness_2_2);
            thickness_3.setImageResource(R.drawable.thickness_3);
            thickness_4.setImageResource(R.drawable.thickness_4);
        }
        else if(myCanvas.getThickness()==9){
            thickness_1.setImageResource(R.drawable.thickness_1);
            thickness_2.setImageResource(R.drawable.thickness_2);
            thickness_3.setImageResource(R.drawable.thickness_3_2);
            thickness_4.setImageResource(R.drawable.thickness_4);
        }
        else if(myCanvas.getThickness()==12){
            thickness_1.setImageResource(R.drawable.thickness_1);
            thickness_2.setImageResource(R.drawable.thickness_2);
            thickness_3.setImageResource(R.drawable.thickness_3);
            thickness_4.setImageResource(R.drawable.thickness_4_2);
        }

        myCanvas.invalidate();
    }

    View.OnLongClickListener LongClick = new View.OnLongClickListener() {
        public boolean onLongClick(View v) {
            myCanvas.Items = new ArrayList<drawingItem>();
            myCanvas.invalidate();
            selectButton.setImageResource(R.drawable.select);
            lineButton.setImageResource(R.drawable.line);
            ovalButton.setImageResource(R.drawable.oval);
            rectButton.setImageResource(R.drawable.rect);
            eraseButton.setImageResource(R.drawable.earse2);
            fillButton.setImageResource(R.drawable.fill);
            return true;
        }
    };


}
