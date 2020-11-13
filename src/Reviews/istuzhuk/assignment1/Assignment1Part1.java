package Reviews.istuzhuk.assignment1;
import com.shpp.karel.KarelTheRobot;
public class Assignment1Part1 extends KarelTheRobot{
    public void run () throws Exception{
        runToNewspaper();
        //Next method lets to pick a newspaper
        pickBeeper();
        returnBack();
    }
    //This method moves Karel to newspaper's location
    private void runToNewspaper() throws Exception {
        turnRight();
        move();
        turnLeft();
        move();
        move();
        move();
        move();
    }
    //This method returns Karel to final destination with a newspaper
    private void returnBack() throws Exception {
        turnLeft();
        turnLeft();
        move();
        move();
        move();
        move();
        turnRight();
        move();
        putBeeper();
    }
    public void turnRight() throws Exception {
        for(int i = 0; i<3; i++)
            turnLeft();
    }
}
