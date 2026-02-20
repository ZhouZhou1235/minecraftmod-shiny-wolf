package com.pinkcandy.myplugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

// 插件主类
public class Main extends JavaPlugin implements Listener {
    // 启用时执行
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this,this);
        getLogger().info("ShinyWolf 已加载 作者 ZhouZhou1235");
    }
    // 关闭时执行
    @Override
    public void onDisable(){}
    // 玩家加入时执行
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        String playerName = player.getName();
        player.sendMessage("欢迎"+playerName+"，周周正在移植ShinyWolf模组到Spigot。");
    }
    // 玩家离开时执行
    @EventHandler
    public void onPlayerQuit(org.bukkit.event.player.PlayerQuitEvent event){}
}
