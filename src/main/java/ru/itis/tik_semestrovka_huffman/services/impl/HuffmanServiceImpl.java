package ru.itis.tik_semestrovka_huffman.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.tik_semestrovka_huffman.exceptions.huffman.HuffmanException;
import ru.itis.tik_semestrovka_huffman.exceptions.huffman.algoritm.InvalidSymbolsException;
import ru.itis.tik_semestrovka_huffman.models.CodeNode;
import ru.itis.tik_semestrovka_huffman.services.HuffmanService;
import ru.itis.tik_semestrovka_huffman.util.HuffmanFileReader;

import java.io.InputStream;
import java.util.*;

@Slf4j
@Service
public class HuffmanServiceImpl implements HuffmanService {

    @Override
    public Map<String, String> loadCodes(List<CodeNode> codes) {
        List<CodeNode> work = new ArrayList<>(codes);
        work.sort((me, other) -> {
            return me.getLetter().compareTo(other.getLetter());
        });
        while (work.size() > 1) {
            CodeNode c1 = getByMinimumP(work);
            work.remove(c1);
            CodeNode c2 = getByMinimumP(work);
            work.remove(c2);
            System.err.println("Взял для обработки ноды " + c1.getLetter() + " ... " + c2.getLetter());
            CodeNode newNode = CodeNode.builder()
                    .p(c1.getP() + c2.getP())
                    .leftChild(c1)
                    .rightChild(c2)
                    .letter(c1.getLetter() + c2.getLetter())
                    .build();
            if(c1.getP() < c2.getP()) {
                c1.setParentSide(0);
                c2.setParentSide(1);
            } else if (c1.getP() > c2.getP()) {
                c2.setParentSide(0);
                c1.setParentSide(1);
            } else {
                if(c1.getLetter().compareTo(c2.getLetter()) < 0) {
                    c1.setParentSide(0);
                    c2.setParentSide(1);
                } else {
                    c2.setParentSide(0);
                    c1.setParentSide(1);
                }
            }

            c1.setParent(newNode);
            c2.setParent(newNode);

            work.add(newNode);
        }
        CodeNode root = work.get(0);
        Map<String, String> result = new HashMap<>();
        for(CodeNode c : codes) {
            CodeNode point = c;
            StringBuilder path = new StringBuilder();
            while (point != root) {
                path.append(point.getParentSide());
                point = point.getParent();
            }
            result.put(c.getLetter(), path.reverse().toString());
        }
        return result;
    }

    @Override
    public Map<String, String> loadCodesFromFile(InputStream is) {
        if(Objects.isNull(is)) {
            throw new HuffmanException("null file given!");
        }
        return loadCodes(HuffmanFileReader.readCodePageFile(is));
    }

    @Override
    public Map<String, String> loadExistingCodesFromFile(InputStream is) {
        if(Objects.isNull(is)) {
            throw new HuffmanException("null file given!");
        }
        return HuffmanFileReader.readAlreadyCodes(is);
    }

    private CodeNode getByMinimumP(List<CodeNode> list) {
        CodeNode result = list.get(0);
        for(CodeNode c : list) {
            if(c.getP() < result.getP()) {
                result = c;
            }
        }
        return result;
    }

    @Override
    public String encode(Map<String, String> cp, String input) {
        if(!isSuitForEncoding(cp, input)) {
            throw new InvalidSymbolsException("Got invalid symbol in encoding string " + input);
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < input.length(); i++) {
            result.append(cp.get("" + input.charAt(i)));
        }
        return result.toString();
    }

    private boolean isSuitForEncoding(Map<String, String> cp, String check) {
        for(int i = 0; i < check.length(); i++) {
            if(!cp.containsKey("" + check.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String decode(Map<String, String> cp, String input) {
        if(!isSuitForDecoding(input)) {
            throw new InvalidSymbolsException("Got invalid symbol in decoding string " + input);
        }
        Map<String, String> inverse = new HashMap<>();
        for(Map.Entry<String, String> e : cp.entrySet()) {
            inverse.put(e.getValue(), e.getKey());
        }

        StringBuilder result = new StringBuilder();
        String buffer = "";
        int pointer = -1;
        while (pointer < input.length() - 1) {
            while (!inverse.containsKey(buffer)) {
                pointer++;
                if(pointer >= input.length()) {
                    throw new InvalidSymbolsException("Given string " + input + "cannot be definitely decoded");
                }
                buffer += input.charAt(pointer);
            }
            result.append(inverse.get(buffer));
            buffer = "";
        }
        return result.toString();
    }

    private boolean isSuitForDecoding(String check) {
        for(int i = 0; i < check.length(); i++) {
            if(check.charAt(i) != '0' && check.charAt(i) != '1') {
                return false;
            }
        }
        return true;
    }
}
