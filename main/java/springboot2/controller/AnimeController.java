package main.java.springboot2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import main.java.springboot2.domain.Anime;
import main.java.springboot2.util.DateUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("anime")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
    private final DateUtil dateUtil;

    // localhost:8080/anime/list
    @GetMapping(path = "list")
    public List<Anime> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new Anime("Boku no Hero"), new Anime("Berzerk"));
    }
}
