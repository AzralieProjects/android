package com.hasonamo_jaberlo.arkanoid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

import java.util.Arrays;

public class GameView extends View {
    private Ball ball;
    private Brick brick;
    private Paddle paddle;
    private float Paddle_x;
    private BrickCollection brickCollection;
    private Paint ball_pen, brick_pen, paddle_pen;
//    private float xpaddle;
    private int canvasW, canvasH;
    int Brick_Row = 5;
    int Brick_Col = 4;
    private int startTop;
    int psteps, pleft, pright;
    int ball_speed =15;
    float ball_speed_x=15;
    float ball_speed_y=-15;
    private int limit_arr[];
    Boolean startgame = false;
    Boolean hit_brick=false;
    Boolean ball_hit_paddle=false;
    int row_limet [];
    int first_move=0;
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        ball = new Ball();
        paddle=new Paddle();
        brick=new Brick();

//        brickCollection=new BrickCollection(Brick_Row,Brick_Col);
//
//        this.limit_arr=brickCollection.getCollection_arr();
        this.limit_arr=new int[Brick_Col];
     Arrays.fill(this.limit_arr,Brick_Row);

        ball_pen = new Paint();

        ball_pen.setColor(Color.WHITE);

        ball_pen.setStyle(Paint.Style.STROKE);
        ball_pen.setStyle(Paint.Style.FILL);
////////////////////////////////////////
        brick_pen = new Paint();
        brick_pen.setColor(Color.RED);
        brick_pen.setStyle(Paint.Style.FILL);

///////////////////////////////////////////////
        paddle_pen = new Paint();
        paddle_pen.setColor(Color.BLUE);
        paddle_pen.setStyle(Paint.Style.FILL);
        paddle_pen.setStrokeWidth(20);
/////////////////////////////
//        init_arr(this.limit_arr,Brick_Row,Brick_Col);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.YELLOW);
        //  canvas.drawLine(0,0,canvasH,canvasW,paddle_pen);
        draw_Bricks(canvas);
        draw_paddle(canvas);
        draw_ball(canvas);
        if(startgame==true){
            move_ball();
       postInvalidate();
                    check_boundres();
                    check_ball_inter_paddle();
            check_ball_intersect_Brick();
            first_move=1;

        }
///////////////////////this will update the circle
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                    check_ball_intersect_Brick();
//
//
//            }
//        }).start();

//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (startgame == true) {
//                    check_boundres();
//                }
//            }
//        }).start();
    }
    private void check_ball_inter_paddle(){
        if((this.ball.getYball()+this.ball.getRadios())>(canvasH-5)){
            if(this.ball.getXball()>this.paddle.getPaddle_left()&&this.ball.getXball()<this.paddle.getPaddle_right())
            this.ball_hit_paddle=true;
        }
    }
    private void check_boundres(){
        if(ball.getYball()-ball.getRadios()<=0 ){
            Log.d("MULTIPLE", "we change here ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            ball_speed=ball_speed*-1;
//            this.ball_speed_x*=-1;
            this.ball_speed_y*=-1;
        }
        if(ball.getXball()-ball.getRadios()<=0||ball.getXball()+ball.getRadios()>=this.canvasW){
            this.ball_speed_x*=-1;
        }
    }
//    public float getAngle(Ball target) {
//        float angle = (float) Math.toDegrees(Math.atan2(target.getYball(), target.getXball()));
//
//        Log.d("MULTIPLE", "degree iis =" + angle );
//
//        if(angle < 0){
//            angle += 360;
//        }
//
//        return angle;
//    }



    private void move_ball() {

        if (this.ball_hit_paddle == true) {

            this.ball_hit_paddle = false;
            this.ball_speed_x=(this.ball.getXball()-this.paddle.getX_c_paddle())/10;
            this.ball_speed_y*=-1;
            this.ball.setYball(canvasH-60);
//            this.ball_speed_y=-10*Math.abs((float) Math.sin(distence));
            Log.d("MULTIPLE", "speed bal y is "+this.ball_speed_y+"paddele center  is sss "+this.ball_speed_x);

//            this.ball.setXball(this.ball.getXball());


        } else {
            if (first_move == 1) {

                this.ball.setXball(this.ball.getXball());
            }

            this.ball.setYball(this.ball.getYball() + ball_speed_y);
            this.ball.setXball((this.ball.getXball() + ball_speed_x));


        }


    }
    private void draw_ball(Canvas canvas) {
        canvas.drawCircle(ball.getXball(),ball.getYball(),ball.getRadios(),ball_pen);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.paddle.setX_c_paddle(event.getX());
                this.paddle.setPaddle_left((this.paddle.getX_c_paddle()-psteps/2));
                this.paddle.setPaddle_right((this.paddle.getX_c_paddle()+psteps/2));
                float y =event.getY();

        }
        Log.d("MULTIPLE", "X=" + Paddle_x + " Y=" + event.getY());
        this.startgame=true;
        postInvalidate();
        return true;
    }

    private void draw_paddle(Canvas canvas) {

//        this.paddle.setX_c_paddle();
//        pright=pleft+psteps;
//        xpaddle=onTouchEvent(MotionEvent event);

        canvas.drawRect(this.paddle.getPaddle_left(),canvasH-50, this.paddle.getPaddle_right(), canvasH -10, paddle_pen);

    }

void init_arr(int []arr,int row,int col){
        for(int i=0;i<col;i++){
            arr[col]=row;
    }


}

        private void  draw_Bricks(Canvas canvas){
        int blimit=canvasH/3;
        int temptop=startTop;

        int steps =canvasH/18;
        int tempbottom=temptop+steps;
        int left=10;
        int leftsteps=canvasW/(Brick_Col);
        int right=left+leftsteps;
        int pad=20;
//           int line=0;

        for(int col=0;col<Brick_Col;col++) {

            int line=0;
            for (int i = 0; i < this.limit_arr[line]; i++) {

                canvas.drawRect(left, temptop, right, temptop + steps, brick_pen);
                temptop += steps + 20;


            }
            line++;
            left=(right+5);
            right=(left+leftsteps);
            temptop=startTop;
//             tempbottom=temptop+steps;

        } 
    }
    private void check_ball_intersect_Brick() {
        int steps = canvasH / 18;
        for (int col = 0; col < this.Brick_Col; col++) {

            int bottm_limit_temp = startTop + this.limit_arr[col] * steps + ((limit_arr[col] - 1) * (20));
            if (this.ball.getYball() + this.ball.getRadios() < (bottm_limit_temp)) {
                this.limit_arr[col]=this.limit_arr[col]-1;
                this.hit_brick=true;
                this.ball_speed_y*=-1;
                postInvalidate();
                break;

            }

        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasW = w;
        canvasH = h;
        Log.d(getClass().getName(), String.format("value = %d", w));
        startTop=canvasH/8;
        psteps=canvasW/5;
        Paddle_x=canvasW/3;

        this.ball.setXball(canvasW/2);
        this.ball.setYball(canvasH-90);
        this.ball.setRadios(30);
        this.paddle.setX_c_paddle(canvasW/2);
        this.paddle.setY_c_paddle(canvasH-25);
//        this.paddle.setXcPaddle(this.paddle.getX_c_paddle()-psteps/2);

    }
}