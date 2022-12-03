package ru.itis.tik_semestrovka_huffman.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.tik_semestrovka_huffman.models.BWTData;
import ru.itis.tik_semestrovka_huffman.services.LZWService;
import ru.itis.tik_semestrovka_huffman.util.LzwFileReader;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/lzw")
@RequiredArgsConstructor
public class LZWController {

    private final LZWService lzwService;

    @GetMapping("/cp")
    public String getFileInputPage() {
        return "lzwfileupload";
    }

    @PostMapping("/cp")
    public String processFileInput(MultipartFile file, HttpSession session) throws IOException {
        session.setAttribute("map", LzwFileReader.readAlphabetFile(file.getInputStream()));
        return "redirect:/lzw";
    }

    @GetMapping
    public String getLzwPage(Model model, HttpSession session) {
       String[] map = (String[]) session.getAttribute("map");
        if(Objects.isNull(map)) {
            model.addAttribute("nomap", "true");
        } else {
            model.addAttribute("map", map);
            model.addAttribute("nomap", "false");
        }
        return "lzw";
    }

    @PostMapping("/encode")
    public String encode(String text, HttpSession session, Model model) {
        String[] map = (String[]) session.getAttribute("map");
        model.addAttribute("source", text);
        model.addAttribute("target", lzwService.encode(text, map));
        return "lzw_result";
    }

    @PostMapping("/decode")
    public String decode(String text, Integer pos, HttpSession session, Model model) {
        String[] map = (String[]) session.getAttribute("map");
        BWTData data = BWTData.builder().data(text).position(pos).build();
        model.addAttribute("source", data);
        model.addAttribute("target", lzwService.decode(data, map));
        return "lzw_result2";
    }
}
