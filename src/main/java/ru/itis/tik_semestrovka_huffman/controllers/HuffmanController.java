package ru.itis.tik_semestrovka_huffman.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.tik_semestrovka_huffman.services.HuffmanService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/huffman")
@RequiredArgsConstructor
public class HuffmanController {

    private final HuffmanService huffmanService;

    @GetMapping("/cp")
    public String getFileInputPage() {
        return "huffmanfileupload";
    }

    @PostMapping("/cp")
    public String processFileInput(MultipartFile file, HttpSession session) throws IOException {
        session.setAttribute("map", huffmanService.loadCodesFromFile(file.getInputStream()));
        return "redirect:/huffman";
    }

    @GetMapping("/code")
    public String getCodeFileInputPage() {
        return "huffmanfileupload2";
    }

    @PostMapping("/code")
    public String processCodeFileInput(MultipartFile file, HttpSession session) throws IOException {
        session.setAttribute("map", huffmanService.loadExistingCodesFromFile(file.getInputStream()));
        return "redirect:/huffman";
    }

    @GetMapping
    public String getHuffmanPage(Model model, HttpSession session) {
        Map<String, String> map = (Map<String, String>) session.getAttribute("map");
        if(Objects.isNull(map)) {
            model.addAttribute("nomap", "true");
        } else {
            model.addAttribute("map", map);
            model.addAttribute("nomap", "false");
        }
        return "huffman";
    }

    @PostMapping("/encode")
    public String encode(String text, HttpSession session, Model model) {
        Map<String, String> map = (Map<String, String>) session.getAttribute("map");
        model.addAttribute("source", text);
        model.addAttribute("target", huffmanService.encode(map, text));
        return "huffman_result";
    }

    @PostMapping("/decode")
    public String decode(String text, HttpSession session, Model model) {
        Map<String, String> map = (Map<String, String>) session.getAttribute("map");
        model.addAttribute("source", text);
        model.addAttribute("target", huffmanService.decode(map, text));
        return "huffman_result";
    }
}
