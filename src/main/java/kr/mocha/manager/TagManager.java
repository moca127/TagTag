package kr.mocha.manager;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import kr.mocha.TagTag;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by user on 17. 1. 20.
 */
public class TagManager {
    private Player player;
    public Config tag,config;

    public TagManager(Player player) {
        this.player = player;
        this.tag = TagTag.getInstance().tag;
        this.config = new Config(new File(
                TagTag.getInstance().getDataFolder()+
                        File.separator+"player"+File.separator+player.getName()+".json"), Config.JSON);
    }

    public boolean hasTag(String tag) {
        List<String> list = config.getList("tag");
        if (list.contains(tag)) return true;
        return false;
    }

    public void addTag(String... tag) {
        List<String> list = config.getList("tag");

        for(String s : tag) {
            if (!hasTag(s)) list.add(s);
        }
        config.set("tag", list);
        config.save();
    }

    public void setTag(String tag) {
        if(hasTag(tag)) {
            this.tag.set(player.getName(), tag);
            this.tag.save();
            player.setDisplayName(tag + " " + player.getDisplayName() + TextFormat.WHITE);
            player.setNameTag(tag + " " + player.getDisplayName() + TextFormat.WHITE);
        }
    }

    public void deleteTag(int index) {

    }

    public void makeTags(String... tag) {
        List<String> list = Arrays.asList(tag);

        config.set("tag", list);
        config.save();
        setTag(tag[0]);
    }

    public String getTag() {
        return tag.getString(player.getName());
    }

    public List<String> getTags() {
        return config.getList("tag");
    }

}
