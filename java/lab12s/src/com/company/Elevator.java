package com.company;
public class Elevator {
    // tworzymy 3 wątki
    static ElevatorCar car = new ElevatorCar();
    static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

    // symulacja przywołania windy z panelu zewnętrznego
    static void makeExternalCall(int atFloor, boolean directionUp) {
        try {
            externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor, directionUp));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // symulacja wyboru pietra w panelu wewnętrznym
    static void makeInternalCall(int toFloor) {
        try {
            internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(toFloor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // uruchomienie wątków
    static void init() {
        car.start();
        externalPanelAgent.start();
        internalPanelAgent.start();
    }

    // miesjce na kod testowy
    public static void main(String[] args) {
        try {
            init();
            //makeExternalCall(4, false);
            //Thread.currentThread().sleep(100);
            //makeInternalCall(2);
            makeExternalCall(4,true);
            Thread.currentThread().sleep(100);
            makeInternalCall(7);
            Thread.currentThread().sleep(100);
            makeInternalCall(2);
            Thread.currentThread().sleep(100);
            makeExternalCall(3, false);
            Thread.currentThread().sleep(100);
            makeExternalCall(2, false);
            Thread.currentThread().sleep(100);
            makeInternalCall(1);
            Thread.currentThread().sleep(100);
            makeInternalCall(8);
            Thread.currentThread().sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
