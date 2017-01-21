package kr.mocha.manager;

import cn.nukkit.Player;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import kr.mocha.TagTag;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 17. 1. 21.
 */
public class TagManager extends Manager {
    public Config tag;

    public TagManager(Player player) {
        super(player);
        this.tag = TagTag.getInstance().tag;
        String c = File.separator;
        this.config = new Config(
                TagTag.getInstance().getDataFolder()+ c + "player"+c+player.getName()+".json", Config.JSON
        );
    }

    @Override
    public void make() {
        List<String> list = new ArrayList<String>();
        list.add(TextFormat.YELLOW+"[USER]");
        tag.set(player.getName(), TextFormat.YELLOW+"[USER]");
        config.set("tags", list);
        tag.save();
        config.save();
        set(TextFormat.YELLOW+"[USER]");
    }

    @Override
    public void add(String... args) {
        List<String> list = config.getList("tags");

        for(String s : args) {
            if(!has(s)) list.add(s);
        }

        config.set("tags", list);
        config.save();
    }

    @Override
    public void del(int arg) throws ArrayIndexOutOfBoundsException{
        List<String> list = config.getList("tags");
        list.remove(arg);
        config.set("tags", list);
        config.save();
        if(tag.getString(player.getName()).equals(arg)) {
            tag.set(player.getName(), list.get(0));
            tag.save();
        }
    }

    @Override
    public boolean has(String arg) {
        List<String> list = config.getList("tags");
        return list.contains(arg);
    }

    @Override
    public void set(String arg) {
        if(has(arg)) {
            tag.set(player.getName(), arg);
            tag.save();
            player.setDisplayName(arg+" "+player.getDisplayName()+TextFormat.WHITE);
            player.setNameTag(arg+" "+player.getDisplayName()+TextFormat.WHITE);
        }
    }

    public void set(int index) throws ArrayIndexOutOfBoundsException {
        List<String> list = config.getList("tags");
        tag.set(player.getName(), list.get(index));
        tag.save();
        player.setDisplayName(list.get(index)+" "+player.getDisplayName()+TextFormat.WHITE);
        player.setNameTag(list.get(index)+" "+player.getDisplayName()+TextFormat.WHITE);
    }

    @Override
    public String get() {
        return tag.getString(player.getName());
    }

    @Override
    public List<String> gets() {
        return config.getList("tags");
    }

}
