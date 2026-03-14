package fr.adriencaubel.ddd.application;

import fr.adriencaubel.ddd.domain.Stock;
import fr.adriencaubel.ddd.domain.repository.MouvementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryUseCase {

    private final MouvementRepository repository;

    public Stock stock(Long articleId, Long providerId) {

        int inputs = repository.sumInputs(articleId, providerId);
        int outputs = repository.sumOutputs(articleId, providerId);

        return new Stock(inputs, outputs);
    }
}