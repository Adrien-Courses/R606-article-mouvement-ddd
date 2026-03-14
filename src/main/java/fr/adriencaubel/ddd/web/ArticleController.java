package fr.adriencaubel.ddd.web;

import fr.adriencaubel.ddd.application.ArticleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleUseCase articleUseCase;

    @GetMapping("{id}/stock")
    public int stock(@PathVariable("id") Long id, @RequestParam("providerId") Long providerId) {
        return articleUseCase.stock(id, providerId);
    }
}
