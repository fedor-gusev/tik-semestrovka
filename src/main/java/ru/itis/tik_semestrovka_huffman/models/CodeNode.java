package ru.itis.tik_semestrovka_huffman.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeNode {

    private String letter;

    private Double p;

    private CodeNode parent;

    private Integer parentSide;

    private CodeNode leftChild;

    private CodeNode rightChild;

}

