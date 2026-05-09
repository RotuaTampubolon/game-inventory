-- Hapus tabel lama jika ada (urutan penting karena ada foreign key)
DROP TABLE IF EXISTS player_inventory;
DROP TABLE IF EXISTS items;
DROP TABLE IF EXISTS players;

-- Tabel players
CREATE TABLE players (
    id           VARCHAR(36) PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    hp           INT NOT NULL DEFAULT 100,
    attack       INT NOT NULL DEFAULT 10,
    defense      INT NOT NULL DEFAULT 5,
    gold         INT NOT NULL DEFAULT 500,
    max_capacity INT NOT NULL DEFAULT 20
);

-- Tabel items
CREATE TABLE items (
    id          VARCHAR(36) PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    description TEXT,
    type        VARCHAR(20)  NOT NULL,
    rarity      VARCHAR(20)  NOT NULL,
    trade_value INT NOT NULL DEFAULT 0
);

-- Relasi player <-> item (many-to-many)
CREATE TABLE player_inventory (
    player_id VARCHAR(36) REFERENCES players(id) ON DELETE CASCADE,
    item_id   VARCHAR(36) REFERENCES items(id)   ON DELETE CASCADE,
    PRIMARY KEY (player_id, item_id)
);