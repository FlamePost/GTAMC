package me.darthteddy1.gta.drugs;

public enum Drug {

    WEED("§2§lWeed"),
    COCAINE("§r§lCocaine"),
    HEROIN("§b§lHeroin"),
    METH("§e§lMeth");

    String _n;

    Drug(String name) {
        _n = name;
    }

    public String getName() {
        return _n;
    }

}
