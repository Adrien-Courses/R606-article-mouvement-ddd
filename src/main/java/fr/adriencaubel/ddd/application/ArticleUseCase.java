package fr.adriencaubel.ddd.application;

import fr.adriencaubel.ddd.domain.Article;
import fr.adriencaubel.ddd.domain.Mouvement;
import fr.adriencaubel.ddd.domain.MouvementType;
import fr.adriencaubel.ddd.domain.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleUseCase {
    private final ArticleRepository articleRepository;

    public int stock(Long id, Long providerId) {
        Article article = articleRepository.findById(id).orElseThrow();

        int inputs = 0;
        int outputs = 0;

        for (Mouvement m : article.getMouvements()) {

            if (!m.getProvider().getId().equals(providerId))
                continue;

            if (m.getType() == MouvementType.INPUT)
                inputs += m.getQuantity();
            else
                outputs += m.getQuantity();
        }

        return inputs - outputs;
    }
}
