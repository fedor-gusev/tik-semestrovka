package ru.itis.tik_semestrovka_huffman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itis.tik_semestrovka_huffman.models.CodeNode;
import ru.itis.tik_semestrovka_huffman.services.HuffmanService;
import ru.itis.tik_semestrovka_huffman.services.impl.HuffmanServiceImpl;
import ru.itis.tik_semestrovka_huffman.util.HuffmanFileReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class TikSemestrovkaHuffmanApplication {

    public static void main(String[] args) {
        SpringApplication.run(TikSemestrovkaHuffmanApplication.class, args);
    }

}
