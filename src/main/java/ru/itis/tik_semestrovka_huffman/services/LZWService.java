package ru.itis.tik_semestrovka_huffman.services;

import ru.itis.tik_semestrovka_huffman.models.BWTData;

public interface LZWService {

    BWTData encode(String input, String[] alphabet);

    String decode(BWTData input, String[] alphabet);

}
