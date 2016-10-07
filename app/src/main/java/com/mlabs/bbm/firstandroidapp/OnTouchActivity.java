package com.mlabs.bbm.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class OnTouchActivity extends AppCompatActivity {
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.ontouch_activity);

                final ImageView imageTouch = (ImageView) findViewById(R.id.imageTouch);

                final EditText txtXY1 = (EditText) findViewById(R.id.txtXY1);
                final EditText txtXY2 = (EditText) findViewById(R.id.txtXY2);
                final EditText txtMinusX = (EditText) findViewById(R.id.txtMinusX);
                final EditText txtMinusY = (EditText) findViewById(R.id.txtMinusY);
                final EditText txtAction = (EditText) findViewById(R.id.txtAction);
                final EditText txtQuadrant = (EditText) findViewById(R.id.txtQuadrant);

                imageTouch.setOnTouchListener(new View.OnTouchListener() {
                        float x,y,x1,y1;

                        @Override
                        public boolean onTouch(View view, MotionEvent e) {

                                String actionX = "";
                                String actionY = "";
                                String quadrant = "";

                                switch (e.getAction()) {
                                        case MotionEvent.ACTION_DOWN:
                                                x = e.getX();
                                                y = e.getY();
                                                return true;
                                        case MotionEvent.ACTION_UP:
                                                float X = imageTouch.getRight()/2;
                                                float Y = imageTouch.getBottom()/2;

                                                x1=e.getX();
                                                y1=e.getY();

                                                actionX = "";
                                                actionY = "";
                                                quadrant = "";

                                                if (x<x1){
                                                        actionX = "Swiped right. ";
                                                }
                                                if (x>x1){
                                                        actionX = "Swiped left. ";
                                                }
                                                if (y<y1){
                                                        actionY = "Swiped down. ";
                                                }
                                                if (y>y1)
                                                {
                                                        actionY = "Swiped up. ";
                                                }

                                                if(x1>X && y1>Y){
                                                        quadrant = "Quadrant 4";
                                                }
                                                if(x1<X && y1>Y){
                                                        quadrant = "Quadrant 3";
                                                }
                                                if(x1<X && y1<Y){
                                                        quadrant = "Quadrant 2";
                                                }
                                                if(x1>X && y1<Y){
                                                        quadrant = "Quadrant 1";
                                                }

                                                txtXY1.setText(x + ", " + x1);
                                                txtXY2.setText(y + ", " + y1);
                                                txtMinusX.setText("X1-X2: " + (Math.abs(x1-x)));
                                                txtMinusY.setText("Y1-Y2:" + (Math.abs(y1-y)));
                                                txtAction.setText(actionX + actionY);
                                                txtQuadrant.setText(quadrant);

                                }
                                return  false;
                        }

                });
        }

}
