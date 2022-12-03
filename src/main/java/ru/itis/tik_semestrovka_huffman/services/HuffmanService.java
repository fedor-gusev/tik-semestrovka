package ru.itis.tik_semestrovka_huffman.services;

import ru.itis.tik_semestrovka_huffman.models.CodeNode;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface HuffmanService {

    Map<String, String> loadCodes(List<CodeNode> codes);

    Map<String, String> loadCodesFromFile(InputStream is);

    Map<String, String> loadExistingCodesFromFile(InputStream is);

    String encode(Map<String, String> cp, String input);

    String decode(Map<String, String> cp, String input);

}
