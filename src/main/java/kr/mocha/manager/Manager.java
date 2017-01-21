package kr.mocha.manager;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;

import java.util.List;

/**
 * Created by user on 17. 1. 21.
 */
public abstract class Manager {
    protected Player player;
    protected Config config;

    public Manager(Player player) {
        this.player = player;
    }
    public abstract void make();
    public abstract void add(String... args);
    public abstract void del(int arg);
    public abstract boolean has(String arg);
    public abstract void set(String arg);
    public abstract String get();
    public abstract List<String> gets();

}
