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
public class LzwFileReader {

    public String[] readAlphabetFile(InputStream is) {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        try {
            return br.readLine().split(" ");
        } catch (Exception e) {
            throw new WrongFileFormatException(e.getMessage());
        }
    }

}
