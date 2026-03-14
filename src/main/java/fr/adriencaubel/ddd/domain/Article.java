package fr.adriencaubel.ddd.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "article")
@Getter
@Setter
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "article_provider",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "provider_id")
    )
    private Set<Provider> providers = new HashSet<>();

    @OneToMany(mappedBy = "article")
    private List<Mouvement> mouvements = new ArrayList<>();

    public Article() {}

    public int stock(Long providerId) {

        int inputs = 0;
        int outputs = 0;

        for (Mouvement m : mouvements) {

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
