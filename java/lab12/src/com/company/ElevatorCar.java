package com.company;

public class ElevatorCar extends Thread{
    int floor=0;

    public int getFloor() {
        return floor;
    }

    enum Tour {UP, DOWN};
    Tour tour = Tour.UP;
    enum Movement {STOP,MOVING};
    Movement movementState = Movement.STOP;

    public void run(){
        for(;;){
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (tour == Tour.UP && movementState == Movement.MOVING){
                if (floor<ElevatorStops.get().getMaxSetFloor()){
                    ++floor;
                    System.out.println("UP Floor "+ floor);
                }
                else {
                    movementState = Movement.STOP;
                }
                if (ElevatorStops.get().whileMovingUpSholudStopAt(floor)){
                    ElevatorStops.get().clearStopUp(floor);
                    movementState = Movement.STOP;
                    System.out.println("STOP Floor "+ floor);
                }
                if (floor == ElevatorStops.get().getMaxSetFloor()){
                    ElevatorStops.get().clearStopUp(floor);
                    movementState = Movement.STOP;
                    System.out.println("STOP Floor "+ floor);
                }
                continue;
            }

            if (tour == Tour.UP && movementState == Movement.STOP){
                if(!ElevatorStops.get().hasStopAbove(floor))tour = Tour.DOWN;
                else movementState = Movement.MOVING;
                continue;
            }

            if (tour == Tour.DOWN && movementState == Movement.STOP){
                if(!ElevatorStops.get().hasStopBelow(floor))tour = Tour.UP;
                else movementState = Movement.MOVING;
                continue;
            }

            if (movementState == Movement.MOVING && tour == Tour.DOWN) {
                if (floor > ElevatorStops.get().getMinSetFloor()) {
                    floor--;
                    System.out.println("DOWN Floor " + floor);
                } else {
                    movementState = Movement.STOP;
                    tour = Tour.UP;
                }

                if (ElevatorStops.get().whileMovingDownSholudStopAt(floor)) {
                    movementState = Movement.STOP;
                    ElevatorStops.get().clearStopDown(floor);
                    System.out.println("STOP Floor "+floor);
                }
                if (floor == ElevatorStops.get().getMinSetFloor()){
                    movementState = Movement.STOP;
                    ElevatorStops.get().clearStopDown(floor);
                    System.out.println("STOP Floor "+floor);
                }
            }
        }
    }
}
