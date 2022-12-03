package ru.itis.tik_semestrovka_huffman.util;

import lombok.experimental.UtilityClass;
import ru.itis.tik_semestrovka_huffman.exceptions.huffman.algoritm.InvalidSymbolsException;
import ru.itis.tik_semestrovka_huffman.exceptions.huffman.io.WrongFileFormatException;
import ru.itis.tik_semestrovka_huffman.models.CodeNode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UtilityClass
public class HuffmanFileReader {

    public List<CodeNode> readCodePageFile(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            String[] letters = br.readLine().split(" ");
            String[] probabilitiesStrings = br.readLine().split(" ");
            if(letters.length != probabilitiesStrings.length) {
                throw new WrongFileFormatException("the number of characters and frequencies does not match");
            }
            List<CodeNode> result = new ArrayList<>();
            for(int i = 0; i < letters.length; i++) {
                result.add(CodeNode.builder()
                        .letter(letters[i])
                        .p(Double.parseDouble(probabilitiesStrings[i]))
                        .parentSide(-1)
                        .build());
            }
            return result;
        } catch (Exception e) {
            throw new WrongFileFormatException(e.getMessage());
        }
    }

    public Map<String, String> readAlreadyCodes(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            Map<String, String> map = new HashMap<>();
            while (br.ready()) {
                String[] line = br.readLine().split(" ");
                for(int i = 0; i < line[1].length(); i++) {
                    if(line[1].charAt(i) != '0' && line[1].charAt(i) != '1') {
                        throw new InvalidSymbolsException("Only 0 and 1 possible code symbols!");
                    }
                }
                map.put(line[0], line[1]);
            }
            return map;
        } catch (Exception e) {
            throw new WrongFileFormatException(e.getMessage());
        }
    }

}
