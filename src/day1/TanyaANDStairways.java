package day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TanyaANDStairways {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int quantity = scanner.nextInt();
        List<Integer> inputNumbers = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            inputNumbers.add(scanner.nextInt());
        }

        int stairways = 0;
        List<Integer> indices = new ArrayList<>();
        //List<Integer> stairs = new ArrayList<>();

        for(int i=0; i<inputNumbers.size(); i++){
            if(inputNumbers.get(i) == 1){
                ++stairways;
                indices.add(inputNumbers.indexOf(inputNumbers.get(i)));
            }
        }

        // Instead of 1,2,3,1,2,3,4,   take 1,2,3,1,2,3,4,1 and check the previous number of '1' occurence.
        // Sentinel/Watchman - fake number added to the beginning or the end of collection to avoid checking them with IF.

        System.out.println("indices: "  + indices);
        System.out.println("number = " + stairways);
        for (int i = 0; i < indices.size() - 1; i++) {
            System.out.print(indices.get(i + 1) - indices.get(i) + " ");
        }
        if(inputNumbers.size() == indices.get(indices.size()-1)){
            System.out.print( indices.get(indices.size()-1));
        } else{
            System.out.print(inputNumbers.size() - 1 - indices.get(indices.size()-1));
        }

        //stairs.add(1);

        /*
        for (int i = 1; i < quantity; i++) {
            if(inputNumbers.get(i) <= inputNumbers.get(i-1)) {
                stairs.add(stairways - 1, inputNumbers.get(i - 1));
                stairways++;
            }
            if (i == quantity - 1 && inputNumbers.get(i) != 1) {
                stairs.add(inputNumbers.get(i));
            }

        }

        System.out.println("number = " + stairways);
        for (Integer stair : stairs) {
            System.out.print(stair + " ");
        }*/
    }
}
