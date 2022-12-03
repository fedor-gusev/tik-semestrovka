package ru.itis.tik_semestrovka_huffman.exceptions.huffman;

public class HuffmanException extends RuntimeException {

    public HuffmanException(String s) {
        super(s);
    }

    public HuffmanException() {
        super("Unknown exception in huffman algorithm");
    }

}
