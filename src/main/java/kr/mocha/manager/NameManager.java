package kr.mocha.manager;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import kr.mocha.TagTag;

import java.io.File;
import java.util.List;

/**
 * Created by user on 17. 1. 21.
 */
public class NameManager {
    private Player player;
    private Config name, config;

    public NameManager(Player player) {
        this.player = player;
        this.name = TagTag.getInstance().name;
        this.config = new Config(new File(
                TagTag.getInstance().getDataFolder()+
                        File.separator+"player"+File.separator+player.getName()+".json"), Config.JSON);
    }

    public boolean hasName(String name) {
        List<String> list = config.getList("name");
        if(list.contains(name)) return true;
        return false;
    }

    public void addName(String... names) {
        List<String> list = config.getList("name");

        for(String s : names) {
            if (!hasName(s)) list.add(s);
        }
        config.set("name", list);
        config.save();
    }

    public void setName(String name, String tag) {
        if(hasName(name)) {
            this.name.set(player.getName(), name);
            this.name.save();
            player.setDisplayName(name+TextFormat.WHITE);
            player.setNameTag(tag + " " + player.getDisplayName() + TextFormat.WHITE);
        }
    }



}
