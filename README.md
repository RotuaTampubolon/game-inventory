# 🎮 Game Inventory System

Aplikasi CLI berbasis Java untuk mengelola inventory karakter dalam game.

## Anggota Kelompok
- Anggota 1 - Angga B. P. Sianipar (Foundation & Item Management)
- Anggota 2 - Geralda Natali Gultom (Item Hierarchy & Factory)
- Anggota 3 - Dianita Ginting (Player & Equip System)
- Anggota 4 - Rotua Immanuela Tampubolon(Trade System & Collection Features)

## Teknologi
- Java 17
- PostgreSQL
- JDBC
- Maven

## Setup (coming soon)

## ⚙️ Setup & Run

### 1. Prerequisites
- Java 17+
- PostgreSQL
- Maven

### 2. Setup Database
```bash
psql -U postgres -c "CREATE DATABASE game_inventory;"
psql -U postgres -d game_inventory -f sql/schema.sql
```

### 3. Konfigurasi Koneksi
Edit `src/main/java/com/gameinventory/db/DBConnection.java`:
- Ganti `USER` dan `PASS` sesuai PostgreSQL lokal kamu

### 4. Run Program
```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.gameinventory.Main"
```