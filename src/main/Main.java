package main;
import java.util.*;

public class Main {  
    public static void main(String[] args) {
        Dices dice = new Dices(6);

        System.out.println("\nStraight [1,2,3,4,5,6] (1200)");
        Integer[] dices = new Integer[] {1, 2, 3, 4, 5, 6};
        Integer expected = 1200;
        Integer output = dice.score(dices);
        boolean res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nThree Pairs [2,2,3,3,4,4] (800)");
        dices = new Integer[] {2, 2, 3, 3, 4, 4};
        expected = 800;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nSix-of-a-kind (Multiply Triple Score - 200 - by 8)");
        dices = new Integer[] {2, 2, 2, 2, 2, 2};
        expected = 1600;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nSix-of-a-kind (Multiply Triple Score - 500 - by 8)");
        dices = new Integer[] {5, 5, 5, 5, 5, 5};
        expected = 4000;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nFive-of-a-kind (Multiply Triple Score - 200 - by 4)");
        dices = new Integer[] {2, 2, 2, 2, 2, 4};
        expected = 800;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nFive-of-a-kind (Multiply Triple Score - 300 - by 4)");
        dices = new Integer[] {3, 3, 3, 3, 3, 4};
        expected = 1200;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nFour-of-a-kind (Multiply Triple Score - 200 - by 2)");
        dices = new Integer[] {2, 2, 2, 2, 4, 4};
        expected = 400;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nFour-of-a-kind (Multiply Triple Score  - 600 -by 2)");
        dices = new Integer[] {1, 1, 6, 6, 6, 6};
        expected = 1200;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nTriple sixes [6,6,6] and tripple fours [4,4,4] (1000)");
        dices = new Integer[] {6, 6, 6, 4, 4, 4};
        expected = 1000;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nTriple sixes [6,6,6] (600)");
        expected = 600;
        dices = new Integer[] {6, 6, 1, 6, 4, 4};
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);
        dices = new Integer[] {6, 6, 6, 2, 3, 4};
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);
        dices = new Integer[] {2, 6, 6, 6, 4, 4};
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nTriple fives [5,5,5] (500)");
        dices = new Integer[] {2, 5, 5, 5, 4, 4};
        expected = 500;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nTriple fours [4,4,4] (400)");
        dices = new Integer[] {2, 2, 3, 4, 4, 4};
        expected = 400;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nTriple threes [3,3,3] (300)");
        dices = new Integer[] {2, 3, 3, 3, 4, 4};
        expected = 300;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nTriple twos [2,2,2] (200)");
        dices = new Integer[] {1, 3, 2, 2, 2, 4};
        expected = 200;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);
        dices = new Integer[] {2, 2, 2, 3, 4, 4};
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nTriple fours [4,4,4] and ones [1,1,1] (1400)");
        dices = new Integer[] {4, 1, 1, 1, 4, 4};
        expected = 1400;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nTriple ones [1,1,1] (1000)");
        dices = new Integer[] {1, 1, 1, 3, 4, 4};
        expected = 1000;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nA single five (50)");
        dices = new Integer[] {2, 2, 5, 3, 4, 4};
        expected = 50;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);
        dices = new Integer[] {5, 2, 3, 6, 4, 2};
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);

        System.out.println("\nA single one (100)");
        dices = new Integer[] {1, 2, 3, 3, 4, 4};
        expected = 100;
        output = dice.score(dices);
        res = Objects.equals(expected, output);
        System.out.println(res+"  |  E: "+expected+" O: "+output);
    }
}