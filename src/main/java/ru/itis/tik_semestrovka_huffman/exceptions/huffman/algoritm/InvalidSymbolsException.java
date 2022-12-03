package ru.itis.tik_semestrovka_huffman.exceptions.huffman.algoritm;

import ru.itis.tik_semestrovka_huffman.exceptions.huffman.HuffmanException;

public class InvalidSymbolsException extends HuffmanException {

    public InvalidSymbolsException(String s) {
        super(s);
    }

    public InvalidSymbolsException() {
        super("Unknown symbols in string of huffman algorithm");
    }
}
