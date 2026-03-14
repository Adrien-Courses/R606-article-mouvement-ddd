package fr.adriencaubel.ddd.web;

import fr.adriencaubel.ddd.application.MouvementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final MouvementUseCase mouvementUseCase;

    @GetMapping("{id}/stock")
    public int stock(@PathVariable("id") Long id, @RequestParam("providerId") Long providerId) {
        return mouvementUseCase.stock(id, providerId);
    }
}
