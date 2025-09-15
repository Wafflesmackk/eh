
1\. Bányászok és építők
-------------------------------------

A feladat egy szimuláció létrehozása, ahol két féle dolgozó (ezeket szálak testesítik meg) próbál felépíteni 5 házat – a bányászok (miner) az aranybányából bányásznak aranyat, amiből az építészek (builder) tudnak házakat építeni.

*   [Configuration](assignment/Configuration.java): Ez az osztály a szimuláció konfigurációját tartalmazza, mint hogy meddig tartson egy építkezés, hány dolgozónk legyen, stb. A feladat során ehhez nem kell hozzányúlni.
*   [Resources](assignment/Resources.java): Ez az osztály felel azért, hogy nyomon kövesse, mennyi aranyat lehet még bányászni a bányából, mennyi arannyal rendelkeznek a dolgozók, illetve hány házat építettek már fel. Egyelőre ehhez sem kell hozzányúlni
*   [ThreadCraft](assignment/File): Itt található a main metódus, illetve a dolgozók implementációja. A feladat a következőkből áll:
    *   Elindítani a dolgozók (bányászok és építészek) szálait (mineAction() és buildAction())
    *   Elindítani egy logoló szálat, ami bizonyos időközönként kiírja a szimuláció haladtát (loggingAction())
    *   A main-en belül megvárni, amíg a dolgozók szálai befejezik a munkát, majd kiírni, hogy vége a szimulációnak
    *   Implementálni a sleepForMsec(int msec) metódust, ami azért felel, hogy az azt meghívó szálat “sleepeltesse” a megszabott ideig
