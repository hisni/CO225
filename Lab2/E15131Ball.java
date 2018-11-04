/*
    Author: Hisni Mohammed M.H.  (E/15/131)
    Date: 01/11/2018
    Lab 02 | behavior of a ball on a 2-D plane
*/
public class E15131Ball {
    public static void main(String[] args) {
        Ball b1 = new Ball(0.0, 1.0, 10.0, 45.0);           //Creating object B1
        Ball.updateTime(5);                                 //Updating Global time by +5 units

        Ball b2 = new Ball(0.0, 1.0, 20.0, 45.0);           //Creating object B1
        Ball.updateTime(5);                                 //Updating Global time by +5 units

        if ( b1.willCollide(b2) ) {                         //Checking whether B1 and B2 are colliding
            System.out.println("B1 and B2 will collide");
        } else {
            System.out.println("B1 and B2 won't collide");
        }

        Ball b3 = new Ball(-10.0, 5.0, 3.0, 30.0);          //Creating object B1
        Ball.updateTime(20);                                //Updating Global time by +20 units

        if ( b2.willCollide(b3) ) {
            System.out.println("B2 and B3 will collide");
        } else {
            System.out.println("B2 and B3 won't collide");
        }
    }
}

class Ball {
    private double initialX, initialY, speed, angle, startTime, currentX, currentY;
    static double globalTime = 0.0;

    Ball(double x, double y, double speed, double angleOfSpeedWithX) {      //Constructor
        this.initialX = x;
        this.initialY = y;
        this.speed = speed;
        this.angle = angleOfSpeedWithX;
        this.startTime = globalTime;
    }

    public static void updateTime(double time) {            //Methode for Updating Global time
        globalTime += time;
    }

    private void updatePostion() {                           //Methode for Updating current position
        this.currentX = initialX + (globalTime - this.startTime) * this.speed * Math.cos(Math.toRadians(this.angle));  //Current X position of ball
        this.currentY = initialY + (globalTime - this.startTime) * this.speed * Math.sin(Math.toRadians(this.angle));  //Current Y position of ball
    }

    public boolean willCollide(Ball b) {          //Methode to check whether balls are colliding in current time
        this.updatePostion();
        b.updatePostion();
        //Compare Coordinates of both balls and Return true if balls are colliding otherwise return false
        if ( (this.currentX == b.currentX) && (this.currentY == b.currentY) ) {
            return true;
        } else {
            return false;
        }
    }
}