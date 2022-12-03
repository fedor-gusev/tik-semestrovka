package ru.itis.tik_semestrovka_huffman.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.tik_semestrovka_huffman.exceptions.huffman.HuffmanException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class HuffmanExceptionHandler {

    @ExceptionHandler(value = HuffmanException.class)
    public String handleHuffmanException(HuffmanException e, HttpServletRequest request, Model model) {
        model.addAttribute("error", e.getMessage());
        model.addAttribute("path", request.getRequestURI());
        return "huffman_error";
    }
}
