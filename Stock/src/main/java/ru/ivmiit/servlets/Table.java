package ru.ivmiit.servlets;

import java.util.ArrayList;

public class Table {
    private int id;
    private int countOfLegs;
    private int weight;
    

    public Table(int id, int countOfLegs, int weight) {
        this.id = id;
        this.weight = weight;
        this.countOfLegs = countOfLegs;
    }

    public String toString(){
        return "Id = " + id  + ";" + "   Count of Legs = " + countOfLegs + ";" + "  Weight= " + weight + ";";
        
    }

}
