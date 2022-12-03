package ru.itis.tik_semestrovka_huffman.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.tik_semestrovka_huffman.exceptions.huffman.algoritm.InvalidSymbolsException;
import ru.itis.tik_semestrovka_huffman.models.BWTData;
import ru.itis.tik_semestrovka_huffman.services.LZWService;
import ru.itis.tik_semestrovka_huffman.util.BWTUtil;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Service
public class LZWServiceImpl implements LZWService {

    private static final String SEPARATOR = "~";
    private static final Integer FIRST_CODE = 0;

    private String convertToBinary(String input) {
        StringBuilder result = new StringBuilder();
        for(String s : input.split(SEPARATOR)) {
            if(s.length() < 1) continue;
            StringBuilder r = new StringBuilder(Integer.toBinaryString(Integer.parseInt(s)));
            while(r.length() < 8) r.insert(0, "0");
            result.append(r + " ");
        }
        return result.toString();
    }

    private String convertFromBinary(String input) {
        StringBuilder sb = new StringBuilder();
        if(input.length() % 8 != 0) throw new InvalidSymbolsException("неправильная закодированная строка");
        for(int i = 0; i < input.length() / 8; i++) {
            String binary = input.substring(i * 8, (i+1) * 8);
            Integer val = Integer.parseInt(binary, 2);
            sb.append(val).append(SEPARATOR);
        }
        return sb.toString();
    }

    private int fillDict(String[] data, Map<String, Integer> map) {
        int i = FIRST_CODE;
        for(String s : data) {
            if(map.containsKey(s)) {
                throw new InvalidSymbolsException("Повторяющиеся буквы алфавита!");
            }
            map.put(s, i);
            i++;
        }
        return i;
    }

    private int fillReversedDict(String[] data, Map<String, String> map) {
        int i = FIRST_CODE;
        for(String s : data) {
            if(map.containsValue(s)) {
                throw new InvalidSymbolsException("Повторяющиеся буквы алфавита!");
            }
            map.put(Integer.toString(i), s);
            i++;
        }
        return i;
    }

    @Override
    public BWTData encode(String input, String[] alphabet) {
        BWTData result = BWTUtil.encode(input);
        Map<String,Integer> dictionary = new LinkedHashMap<>();
        String[] data = result.getData().split("");
        StringBuilder out = new StringBuilder();
        ArrayList<String> temp_out = new ArrayList<>();
        String currentChar;
        String phrase = data[0];
        int code = fillDict(alphabet, dictionary);
        //System.err.println("Кодирую " + input);
        for(int i=1; i<data.length;i++){
            currentChar = data[i];
            //System.err.println("Рассматриваю " + currentChar + ". Phrase = " + phrase);
            if(dictionary.get(phrase+currentChar) != null){
                phrase += currentChar;
                //System.err.println("Есть в словаре. удлинняю phrase до " + phrase);
            }
            else{
                //System.err.print("Нет в словаре. ");
                try {
                    temp_out.add(dictionary.get(phrase).toString());
                }catch (NullPointerException e) {
                    throw new InvalidSymbolsException("Переданы символы не из алфавита!");
                }

                dictionary.put(phrase+currentChar, code);
                //System.err.println("Ставлю коды " + (phrase + currentChar) + " = " + code);
                code++;
                phrase = currentChar;
            }
        }

        temp_out.add(dictionary.get(phrase).toString());

        for(String outchar : temp_out) {
            out.append(outchar).append(SEPARATOR);
        }

        result.setData(convertToBinary(out.toString()));
        return result;
    }

    @Override
    public String decode(BWTData input, String[] alphabet){
        Map<String, String> dictionary = new LinkedHashMap<>();

        String[] data = convertFromBinary(input.getData().replaceAll(" ", "")).split(SEPARATOR);
        int code = fillReversedDict(alphabet, dictionary);
        //System.out.println(dictionary);

        String current = data[0];
        String val = dictionary.get(current);
        StringBuilder result = new StringBuilder();
        int i = 1;

        //System.out.println("Выхватил " + current + ", пишу как " + val);

        while (true) {
            result.append(val);
            if(i >= data.length) break;
            current = data[i++];
            //System.out.println("Новая итерация. current = " + current + ", result = " + result);
            String s = dictionary.get(current);
            //System.out.println("s = " + s);
            if (code == Integer.parseInt(current)) {
                s = val + val.charAt(0);   // special case hack
                //System.out.println("special case hack s = " + s);
            }
            if (code < 4096) {
                dictionary.put(code++ + "", val + s.charAt(0));
                //System.out.println("Добавил " + (code-1) + " = " + (val + s.charAt(0)));
            }
            val = s;
            //System.out.println("Заканчиваем круг. val = " + val);
        }
        input.setData(result.toString());
        return BWTUtil.decode(input);
    }

}
