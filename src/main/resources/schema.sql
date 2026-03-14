DROP TABLE IF EXISTS mouvement;
DROP TABLE IF EXISTS article_provider;
DROP TABLE IF EXISTS provider;
DROP TABLE IF EXISTS article;

CREATE TABLE article (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         description TEXT
);

CREATE TABLE provider (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL
);

-- table de jointure many-to-many
CREATE TABLE article_provider (
                                  article_id BIGINT NOT NULL,
                                  provider_id BIGINT NOT NULL,
                                  PRIMARY KEY (article_id, provider_id),
                                  CONSTRAINT fk_article_provider_article
                                      FOREIGN KEY (article_id)
                                          REFERENCES article(id)
                                          ON DELETE CASCADE,
                                  CONSTRAINT fk_article_provider_provider
                                      FOREIGN KEY (provider_id)
                                          REFERENCES provider(id)
                                          ON DELETE CASCADE
);

CREATE TABLE mouvement (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           article_id BIGINT NOT NULL,
                           provider_id BIGINT NOT NULL,
                           type ENUM('INPUT','OUTPUT') NOT NULL,
                           quantity INT NOT NULL,
                           date DATETIME NOT NULL,

                           CONSTRAINT fk_mouvement_article
                               FOREIGN KEY (article_id)
                                   REFERENCES article(id),

                           CONSTRAINT fk_mouvement_provider
                               FOREIGN KEY (provider_id)
                                   REFERENCES provider(id)
);