import java.util.ArrayList;

import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String[] arr = {"apple","mango","orange","orange","mango","juce","ramu"};
        System.out.println(samesequece(arr));
    }


    public static List<String> samesequece(String[] arr) {
        List<String> strings = new ArrayList<>();
        for (String val : arr){
            if (!strings.contains(val)){
                strings.add(val);
            }
        }

        return strings;
    }
}