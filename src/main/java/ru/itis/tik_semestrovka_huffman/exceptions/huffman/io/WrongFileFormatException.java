package ru.itis.tik_semestrovka_huffman.exceptions.huffman.io;

import ru.itis.tik_semestrovka_huffman.exceptions.huffman.HuffmanException;

public class WrongFileFormatException extends HuffmanException {

    public WrongFileFormatException(String s) {
        super(s);
    }

    public WrongFileFormatException() {
        super("Unknown exception in file processing");
    }

}
