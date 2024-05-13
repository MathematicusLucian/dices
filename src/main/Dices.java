/**
 Write a class with a score() method that accepts an array of dice values (up to 6). Scoring rules are as follows:
•	A single one (100)
•	A single five (50)
•	Triple ones [1,1,1] (1000)
•	Triple twos [2,2,2] (200)
•	Triple threes [3,3,3] (300)
•	Triple fours [4,4,4] (400)
•	Triple fives [5,5,5] (500)
•	Triple sixes [6,6,6] (600)
Note that the scorer should work for any number of dice up to 6.
•	Four-of-a-kind (Multiply Triple Score by 2)
•	Five-of-a-kind (Multiply Triple Score by 4)
•	Six-of-a-kind (Multiply Triple Score by 8)
•	Three Pairs [2,2,3,3,4,4] (800)
•	Straight [1,2,3,4,5,6] (1200)
 */
package main;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Dices {
    public static Integer numberOfDices = 0;
    static Integer maxDiceValueLimit = 6;
    static Integer totalScore = 0;
    static Map<Integer, Integer> ofKindMapping = new HashMap<>();
    static Map<Integer, Integer> triplesMapping = new HashMap<>();
    static Map<Integer, Integer> singlesMapping = new HashMap<>();
        
    public Dices(Integer numberOfDices) {
        Dices.numberOfDices = numberOfDices;
        Dices.ofKindMapping.put(6, 8);
        Dices.ofKindMapping.put(5, 4);
        Dices.ofKindMapping.put(4, 2);
        // Triple sixes [6,6,6] (600)
        // Triple fives [5,5,5] (500)
        // Triple fours [4,4,4] (400)
        // Triple threes [3,3,3] (300)
        // Triple twos [2,2,2] (200)
        // Triple ones [1,1,1] (1000)
        Dices.triplesMapping.put(6, 600);
        Dices.triplesMapping.put(5, 500);
        Dices.triplesMapping.put(4, 400);
        Dices.triplesMapping.put(3, 300);
        Dices.triplesMapping.put(2, 200);
        Dices.triplesMapping.put(1, 1000);
        // A single one (100)
        // A single five (50)
        Dices.singlesMapping.put(1, 100);
        Dices.singlesMapping.put(5, 50);
    }

    public static <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
        return map.entrySet()
                .stream()
                .filter(x -> predicate.test(x.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static <K, V> Map<K, V> filterByKeyValue(Map<K, V> map, Predicate<K> predicateK, Predicate<V> predicateV) {
        return map.entrySet()
                .stream()
                .filter(x -> predicateK.test(x.getKey()))
                .filter(y -> predicateV.test(y.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Integer score(Integer[] dices) {
        if(dices.length != Dices.numberOfDices) {
            return 0;
        }
        return Dices.calculateScore(dices);
    }

    static Map<Integer, Integer> buildDiceMap(Integer[] dices) {
        Map<Integer, Integer> diceValues = new HashMap<>();
        for (int i = 0; i < (Dices.numberOfDices); i++) {
            diceValues.put(i, 0);
        }
        for (Integer d : dices) {
            if(d > Dices.maxDiceValueLimit) {
                return diceValues; 
            }
            Integer diceValIndex = d-1;
            Integer val = diceValues.get(diceValIndex);
            val = val+1;
            diceValues.put(diceValIndex, val);
        }
        return diceValues;
    }

    static Integer calculateScore(Integer[] dices) {
        Dices.totalScore = 0;

        // Straight [1,2,3,4,5,6] (1200)
        Integer[] expected = new Integer[] {1,2,3,4,5,6};
        Arrays.sort(dices);
        if(Arrays.equals(dices, expected)){
            Dices.totalScore = 1200;
            return 1200;
        }

        Map<Integer, Integer> diceValues = buildDiceMap(dices);

        // Three Pairs [2,2,3,3,4,4] (800)
        Map<Integer, Integer> filteredMap = filterByValue(diceValues, x -> (x == 2));
        if(filteredMap.size() == 3) {
            Dices.totalScore = 800;
            return 800;
        }
        
        // Of-a-kind
        Integer[] kinds = new Integer[] {4,5,6};
        for (Integer k : kinds ) {
            Integer ofK = ofKind(diceValues, k);
            if(ofK != 0) {
                Dices.totalScore = ofK;
                return ofK;
            }
        }

        // Triple
        filteredMap = filterByValue(diceValues, x -> (x == 3));
        if(filteredMap.size() >= 1) {
            for (Map.Entry entry : filteredMap.entrySet()) {
                Integer entryKey = Integer.valueOf(entry.getKey().toString());
                Integer diceVal = entryKey + 1;
                Dices.totalScore = Dices.totalScore + Dices.calculateTriplesScore(diceVal);
            }
            return Dices.totalScore;
        }

        // Single
        Dices.totalScore = Dices.totalScore + Dices.calculateSinglesScore(diceValues);

        return Dices.totalScore;
    }

    // Six-of-a-kind (Multiply Triple Score by 8)
    // Five-of-a-kind (Multiply Triple Score by 4)
    // Four-of-a-kind (Multiply Triple Score by 2)
    static Integer ofKind(Map<Integer, Integer> diceValues, Integer val) {
        Map<Integer, Integer> filteredMap = filterByValue(diceValues, x -> (Objects.equals(x, val)));
        Map<Integer, Integer> ofKMapRow = filterByKeyValue(Dices.ofKindMapping, x -> (Objects.equals(x, val)), y -> (y > 0));
        if(filteredMap.size() >= 1 && ofKMapRow.size() >= 1) {
            Map.Entry<Integer,Integer> entry = filteredMap.entrySet().iterator().next();
            Integer firstKey = entry.getKey();
            Integer diceVal = firstKey + 1;
            Map.Entry<Integer,Integer> entryOKM = ofKMapRow.entrySet().iterator().next();
            Integer multiplier = entryOKM.getValue();
            Integer s = Dices.calculateTriplesScore(diceVal);
            return s * multiplier;
        } else {
            return 0;
        }
    }

    // Triple sixes [6,6,6] (600)
    // Triple fives [5,5,5] (500)
    // Triple fours [4,4,4] (400)
    // Triple threes [3,3,3] (300)
    // Triple twos [2,2,2] (200)
    // Triple ones [1,1,1] (1000)
    static Integer calculateTriplesScore(Integer diceValue) {
        Integer triplesScore = 0;
        Map<Integer, Integer> tripleMappingRow = filterByKeyValue(Dices.triplesMapping, x -> (Objects.equals(x, diceValue)), y -> (y > 0));
        if(tripleMappingRow.size() >= 1) {
            Map.Entry<Integer,Integer> entry = tripleMappingRow.entrySet().iterator().next();
            triplesScore = entry.getValue();
        }
        return triplesScore;
    }

    // A single one (100)
    // A single five (50)
    static Integer calculateSinglesScore(Map<Integer, Integer> diceValues) {
        Integer singlesScore = 0;
        for (Map.Entry<Integer,Integer> s : Dices.singlesMapping.entrySet()) {
            Integer k = s.getKey() - 1;
            Map<Integer, Integer> filteredMap = filterByKeyValue(diceValues, x -> (Objects.equals(x, k)), y -> (y == 1));
            if(filteredMap.size() == 1) {
                String v = s.getValue().toString();
                singlesScore = singlesScore + Integer.valueOf(v);
            }
        }
        return singlesScore;
    }
}