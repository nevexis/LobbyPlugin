package dev.nevah5.mc.lobbyplugin.tools;

import dev.nevah5.mc.lobbyplugin.LobbyConfig;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class TeamTool {
    private LobbyConfig lobbyConfig;
    @Getter
    private Team group_owner;
    @Getter
    private Team group_admin;
    @Getter
    private Team group_mod;
    @Getter
    private Team group_friend;
    @Getter
    private Team group_default;

    public TeamTool(LobbyConfig lobbyConfig){
        this.lobbyConfig = lobbyConfig;
        this.initializeGroupTeams();
    }

    private void initializeGroupTeams(){
        ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
        assert scoreboardManager != null;
        Scoreboard scoreboard = scoreboardManager.getMainScoreboard();
        if(scoreboard.getTeam("group_owner") == null) {
            this.group_owner = scoreboard.registerNewTeam("group_owner");
            this.group_owner.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            this.group_owner.setPrefix(this.lobbyConfig.getConfigurationString("OwnerPrefix"));
        }
        if(scoreboard.getTeam("group_admin") == null) {
            this.group_admin = scoreboard.registerNewTeam("group_admin");
            this.group_admin.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            this.group_admin.setPrefix(this.lobbyConfig.getConfigurationString("AdminPrefix"));
        }
        if(scoreboard.getTeam("group_mod") == null){
            this.group_mod = scoreboard.registerNewTeam("group_mod");
            this.group_mod.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            this.group_mod.setPrefix(this.lobbyConfig.getConfigurationString("ModPrefix"));
        }
        if(scoreboard.getTeam("group_friend") == null){
            this.group_friend = scoreboard.registerNewTeam("group_friend");
            this.group_friend.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            this.group_friend.setPrefix(this.lobbyConfig.getConfigurationString("FriendPrefix"));
        }
        if(scoreboard.getTeam("group_default") == null) {
            this.group_default = scoreboard.registerNewTeam("group_default");
            this.group_default.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
            this.group_default.setPrefix(this.lobbyConfig.getConfigurationString("DefaultPrefix"));
        }

    }
}
