package hr.gyro.deathban;

import com.mojang.authlib.GameProfile;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.UserBanList;
import net.minecraft.server.players.UserBanListEntry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;


public class ModEvent {
    @Mod.EventBusSubscriber(modid = DeathbanMod.MOD_ID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void playerDeath(LivingDeathEvent event) {
            Entity entity = event.getEntity();
            if (entity instanceof ServerPlayer player) {
                String deathMessage = event.getSource().getLocalizedDeathMessage(player).getString();
                CommandSourceStack commandSource = player.getServer().createCommandSourceStack();
                commandSource.sendSuccess(new TextComponent("Banning the player " + player.getName().getString() + " because he died!" ), false);
                commandSource.getServer().getCommands().performCommand(commandSource, "ban " + player.getName().getString() + " " + deathMessage);
            }
        }
    }
}
