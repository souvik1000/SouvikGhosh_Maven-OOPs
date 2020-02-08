package com.first.maven_oop;

import java.util.ArrayList;
import java.util.Comparator;


public class NewYearGift {

    private GiftAnalysis generator;

    private ArrayList<Sweet> newYearsList;

    private static SugarLevelComparator sugarComparator = new SugarLevelComparator();

    private static WeightComparator weightComparator = new WeightComparator();

    public SugarLevelComparator getSugarLevelComparator() {
        return sugarComparator;
    }

    public WeightComparator getWeightComparator() {
        return weightComparator;
    }

    public NewYearGift() {
        generator = new GiftAnalysis();
        newYearsList = new ArrayList<Sweet>();
    }

    public ArrayList<Sweet> generate(int times) {

        for (int i = 0; i < times; i++) {
            newYearsList.add(generator.next());
        }
        return newYearsList;
    }

    public static void generateNewYearGift(int numbers) {
        for (Sweet sweet : new GiftAnalysis(numbers))
            System.out.println(sweet);
    }

    private static class SugarLevelComparator implements Comparator<Sweet> {

        public int compare(Sweet sweetsOne, Sweet sweetsTwo) {
            return (sweetsOne.get_SugarLevel() < sweetsTwo.get_SugarLevel() ? -1
                    : (sweetsOne.get_SugarLevel() == sweetsTwo.get_SugarLevel() ? 0 : 1));
        }

    }

    private static class WeightComparator implements Comparator<Sweet> {

        public int compare(Sweet sweetsOne, Sweet sweetsTwo) {
            return (sweetsOne.get_Weight() < sweetsTwo.get_Weight() ? -1
                    : (sweetsOne.get_Weight() == sweetsTwo.get_Weight() ? 0 : 1));
        }

    }
    
}
