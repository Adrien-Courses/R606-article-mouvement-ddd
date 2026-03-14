package fr.adriencaubel.ddd.application;

import fr.adriencaubel.ddd.domain.Mouvement;
import fr.adriencaubel.ddd.domain.MouvementType;
import fr.adriencaubel.ddd.domain.Stock;
import fr.adriencaubel.ddd.domain.repository.MouvementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final InventoryUseCase inventoryUseCase;

    private final MouvementRepository mouvementRepository;

    public void create(Long articleId, Long providerId, int quantity) {
        Stock stock = inventoryUseCase.stock(articleId, providerId);

        stock.reserve(quantity);

        // Création d'une commande à base de donnée
        System.out.println("Création d'une commande à base de donnée");

        Mouvement mouvement = new Mouvement(articleId, MouvementType.OUTPUT, quantity);
        mouvementRepository.save(mouvement);
    }
}
