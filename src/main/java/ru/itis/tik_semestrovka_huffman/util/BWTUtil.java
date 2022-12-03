package ru.itis.tik_semestrovka_huffman.util;

import lombok.experimental.UtilityClass;
import ru.itis.tik_semestrovka_huffman.models.BWTData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class BWTUtil {

    public BWTData encode(String input) {
        List<String> strings = new ArrayList<>();
        int n = input.length();
        String line = input;
        for(int i = 0; i < n; i++) {
            strings.add(line);
            line = line + line.charAt(0);
            line = line.substring(1);
        }
        strings.sort(String::compareTo);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) sb.append(strings.get(i).charAt(n-1));

        return BWTData.builder()
                .data(sb.toString())
                .position(strings.indexOf(input) + 1)
                .build();
    }

    public String decode(BWTData data) {
        String b = data.getData();
        String a = Arrays.stream(b.split("")).sorted(String::compareTo).collect(Collectors.joining());

        int n = b.length();
        StringBuilder result = new StringBuilder();
        int pointer = data.getPosition() - 1;
        for(int i = 0; i < n; i++) {
            Character c = a.charAt(pointer);
            int ptr = pointer;
            int pos = 1;
            result.append(c);
            while (ptr > 0 && a.charAt(ptr) == a.charAt(ptr-1)) {
                pos++;
                ptr--;
            }
            ptr = 0;
            while (b.charAt(ptr) != c || pos > 1) {
                if(b.charAt(ptr) == c) pos--;
                ptr++;
            }
            pointer = ptr;
        }

        return result.toString();
    }

}
