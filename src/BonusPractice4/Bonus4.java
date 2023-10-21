package BonusPractice4;

import java.util.HashSet;

public class Bonus4 {

    static HashSet<String> CS210 = new HashSet<String>();
    static HashSet<String> CS211 = new HashSet<String>();
    static HashSet<String> CS212 = new HashSet<String>();

    // Name: Loc, Dao
    // sID: 201875743
    // Date: 6 Oct, 2023
    public static void Initialize() {
        CS210.add("Bill");
        CS210.add("Eva");
        CS210.add("Olivia");

        CS211.add("Eva");
        CS211.add("Olivia");
        CS211.add("Kim");

        CS212.add("Chloe");
        CS212.add("Jane");
        CS212.add("Olivia");
        CS212.add("Bill");
    }

    public static void atLeastOneClass() {
        HashSet<String> all = new HashSet<>(CS210);
        all.addAll(CS211);
        all.addAll(CS212);

        System.out.println("At least one class: " + all);
    }


    public static void allClasses() {
        HashSet<String> result = new HashSet<>(CS210);

        result.retainAll(CS211);
        result.retainAll(CS212);

        System.out.println("CS210 and CS211 and CS212: " + result);
    }


    public static void violation() {
        HashSet<String> result = new HashSet<>(CS211);
        result.removeAll(CS210);
        System.out.println("CS211 without taking CS210: " + result);
    }

    public static void CS210andCS211notCS212Classes() {
        HashSet<String> result = new HashSet<>(CS210);
        result.retainAll(CS211);
        result.removeAll(CS212);
        System.out.println("CS210 and CS211 but not CS212: " + result);
    }


    public static void main(String[] args) {

        Initialize();
        atLeastOneClass();
        allClasses();
        violation();
        CS210andCS211notCS212Classes();
    }
}






