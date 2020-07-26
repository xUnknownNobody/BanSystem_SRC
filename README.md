# BanSystem

Hier ist der komplette Sourcecode zu meinem Plugin

## Einrichtung

In der mysql.yml-Datei muss zwangsläufig eine Datenbank angegeben werden, ansonsten startet das PLugin nicht und es kommt eine Fehlermeldung

```bash
username: root
password: passwort
database: datenbank
host: localhost
port: '3306'

```

## Befehle

```
/ban <Spieler> <Grund>
/tempban <Spieler> <Wert> <Einheit> <Grund>
/unban <Spieler>
/check (list) <Spieler>
```
